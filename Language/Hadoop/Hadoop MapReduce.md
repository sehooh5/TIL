# Hadoop MapReduce  

### 기본지식

- `reducer` 로 나가는 리턴객체는 `Key` 객체

- `mapper` 는 우리가 개발해줘야함

  `mapper` 를 상속하여 `mapper` 클래스를 만들고

- `reducer` 또한

  `reducer` 를 상속하여 `reducer` 클래스를 만든다

#### 환경설정

- `Hadoop`(하둡 내부에) 에 있는 `yan-site.xml` 에 추가(머신모두)

  - 위치 : `cd $HADOOP_HOME/etc/hadoop`

  ```
  <property>
  <name>yarn.resourcemanager.hostname</name>
  <value>master</value>
  </property>
  <property>
  <name>yarn.resourcemanager.webapp.address</name>
  <value>${yarn.resourcemanager.hostname}:8088</value>
  </property>
  <property>
  <name>yarn.nodemanager.resource.memory-mb</name>
  <value>4096</value>
  </property>
  <property>
  <name>yarn.scheduler.minimum-allocation-mb</name>
  <value>2048</value>
  </property>
  ```

-  `master`에서 `start-dfs.sh`와 `start-yarn.sh` 실행 해주면 등록 완료

  - `master` : NameNode, ResourceManager
  - `slave1` : DataNode, NodeManager, SecondaryNameNode
  - `slave2` : DataNode, NodeManager
  - `slave3` : DataNode, NodeManager



### mrexam(JAVA)

- 교육용 폴더 `hadoopexam` 에 임포트하기

#### WordCount.java

```java
package mrexam;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class WordCount {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();//configuration 객체 생성
		conf.set("fs.default.name", "hdfs://192.168.111.120:9000");//NameNode 설정
		
		//[1] conf객체 [2] (DriverClass 의 이름)
		Job job = Job.getInstance(conf, "WordCount");

		job.setJarByClass(WordCount.class);//Driver Class의 클래스 객체 지정(.class까지 지정)
		job.setMapperClass(WordCountMapper.class);//MapperClass 지정
		job.setReducerClass(WordCountReducer.class);//ReducerClass 지정

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		//String, Int 가 아닌 Text, IntWritable 을 사용함
		//Remote proceduer call로 서로 다른 머신에서 주고받기
		//객체 직렬화를 시키기 위함!
		//자바에서 만들어진 직렬화가 무겁다고 생각해서
		//compact 한 방법을 생각해서 Text, IntWritable 사용
		job.setOutputKeyClass(Text.class);//Text ..
		job.setOutputValueClass(IntWritable.class);//IntWritable 즉 숫자

		FileInputFormat.addInputPath(job, new Path("/edudata/president_moon.txt"));//어떤파일을 읽을 것인지 설정
		FileOutputFormat.setOutputPath(job, new Path("/result/wc1"));//어디로 내보낼 것인지 설정..wc1폴더가 없어야함, 폴더까지만 지정..파일은 자동으로 만들어짐

		//job 을 일던 전달하고 받음,,Resource Manager 가 job을 처리
		//Resource Manager - Node Manager (job 실제적 처리)
		job.waitForCompletion(true);
		System.out.println("처리가 완료되었습니다.");
	}
}
```



#### WordCountMapper.java

- 행단위 만큼 호출됨

```java
package mrexam;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//제너리스구문(입 출력을 동적으로 가능하게끔 만들어줌)
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
//Mapper<> 안에 args [1]읽기[2]출력 // [3]읽기[4]출력
	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();
	
	//overriding [1] key, [2] value, [3] context객체
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		
		System.out.println("MAP : key ? = "+key+" --- value ? = "+value);
		StringTokenizer itr = new StringTokenizer(value.toString());//value 를 String 후 토큰으로 나누어주는
		while (itr.hasMoreTokens()) {//Token 이 있는 만큼 반복해라
			word.set(itr.nextToken());//itr 의 tokend 을 Text객체인 word 에 세팅
			context.write(word, one);//Text 객체를 context 객체에 써준다
			
			
			//Reducer 에게 전달
		}
	}
}
```



#### WordCountReducer.java

```java
package mrexam;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	private IntWritable result = new IntWritable();
	
	
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		System.out.print("REDUCE : key ? = "+key+" --- ");
		int sum = 0;
		//Iterable 사용
		//values 가 갖고있는 Intwritable 의 개수만큼 가져와서 sum 변수에 담아서 counting
		for (IntWritable val : values) {
			int data = val.get();
			System.out.print(data+ " " );
			sum += val.get();
		}
		result.set(sum);//result 에 세팅
		context.write(key, result);//내보낼땐 context 객체로 내보냄
	}
}
```



#### FruitsResultSort.java

```java
package mrexam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class FruitsResultSort {

	public static void main(String[] args) throws Exception{
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.111.120:9000");//fs.defaultFS 가 최신버저닝다
		FileSystem hdfs = FileSystem.get(conf);
		FSDataInputStream in = hdfs.open(new Path("/result/fruit1/part-r-00000"));
		
		BufferedReader br =new BufferedReader(new InputStreamReader(in));
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		while(br.ready()){ 	  	
			String line = br.readLine();
			String data[] = line.split("\\s+");
			map.put(data[0], Integer.parseInt(data[1]));
        }	
		

		  //KeySetList 를 만듬
		  List<String> keySetList = new ArrayList<>(map.keySet());

		  // 오름차순 System.out.println("------value 오름차순------");
		  Collections.sort(keySetList, (o1, o2) ->//[1] Key
		  (map.get(o1).compareTo(map.get(o2))));// [2] 비교하고자하는 comparator 객체 전달 (람다식 사용)
		  // R 의 함수안에 함수 사용하듯이 첫번째 두번째 가지고 (o1,o2) 호출 ex) (apple,banana) -> 람다식
		  
		  for (String key : keySetList) { System.out.println("key : " + key + " / " +
		  "value : " + map.get(key)); }
		  
		  System.out.println();
		  
		  // 내림차순 System.out.println("------value 내림차순------");
		  Collections.sort(keySetList, (o1, o2) ->
		  (map.get(o2).compareTo(map.get(o1)))); for (String key : keySetList) {
		  System.out.println("key : " + key + " / " + "value : " + map.get(key)); }
		 
	}

}

```



#### ArrivalDelayCount.java

```java
package mrexam;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class ArrivalDelayCount {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.111.120:9000");

		Job job = Job.getInstance(conf, "Airline");
		if (args.length != 2) {
			System.err.println("Usage: ArrivalDelayCount <input> <output>");
			System.exit(2);
		}

		// 입출력 데이터 경로 설정
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		// Job 클래스 설정
		job.setJarByClass(ArrivalDelayCount.class);
		// Mapper 클래스 설정
		job.setMapperClass(ArrivalDelayCountMapper.class);
		// Reducer 클래스 설정
		job.setReducerClass(DelayCountReducer.class);

		// 입출력 데이터 포맷 설정
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		// 출력키 및 출력값 유형 설정
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		job.waitForCompletion(true);
	}
}

```



#### ArrivalDelayCountMapper.java

```java
package mrexam;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class ArrivalDelayCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	// map 출력값
	private final static IntWritable outputValue = new IntWritable(1);
	// map 출력키
	private Text outputKey = new Text();

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if (Integer.parseInt(key.toString()) != 0) {
			AirlinePerformanceParser parser = new AirlinePerformanceParser(value);

			// 출력키 설정
			outputKey.set(parser.getYear() + "," + parser.getMonth());

			if (parser.getArriveDelayTime() > 0) {
				// 출력 데이터 생성
				context.write(outputKey, outputValue);
			}
		}
	}
}

```



#### AirlinePerformanceParser.java

```java
package mrexam;

import org.apache.hadoop.io.Text;

public class AirlinePerformanceParser {

	private int year;
	private int month;

	private int arriveDelayTime = 0;
	private int departureDelayTime = 0;
	private int distance = 0;

	private boolean arriveDelayAvailable = true;
	private boolean departureDelayAvailable = true;
	private boolean distanceAvailable = true;

	private String uniqueCarrier;

	public AirlinePerformanceParser(Text text) {
		try {
			String[] colums = text.toString().split(",");

			// 운항 연도 설정
			year = Integer.parseInt(colums[0]);

			// 운항 월 설정
			month = Integer.parseInt(colums[1]);

			// 항공사 코드 설정
			uniqueCarrier = colums[8];

			// 항공기 출발 지연 시간 설정
			if (!colums[15].equals("NA")) {
				departureDelayTime = Integer.parseInt(colums[15]);
			} else {
				departureDelayAvailable = false;
			}

			// 항공기 도착 지연 시간 설정
			if (!colums[14].equals("NA")) {
				arriveDelayTime = Integer.parseInt(colums[14]);
			} else {
				arriveDelayAvailable = false;
			}

			// 운항 거리 설정
			if (!colums[18].equals("NA")) {
				distance = Integer.parseInt(colums[18]);
			} else {
				distanceAvailable = false;
			}
		} catch (Exception e) {
			System.out.println("Error parsing a record :" + e.getMessage());
		}
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getArriveDelayTime() {
		return arriveDelayTime;
	}

	public int getDepartureDelayTime() {
		return departureDelayTime;
	}

	public boolean isArriveDelayAvailable() {
		return arriveDelayAvailable;
	}

	public boolean isDepartureDelayAvailable() {
		return departureDelayAvailable;
	}

	public String getUniqueCarrier() {
		return uniqueCarrier;
	}

	public int getDistance() {
		return distance;
	}

	public boolean isDistanceAvailable() {
		return distanceAvailable;
	}
}

```



#### DelayCountReducer.java

```java
package mrexam;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class DelayCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	private IntWritable result = new IntWritable();

	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		for (IntWritable value : values)
			sum += value.get();
		result.set(sum);
		context.write(key, result);
	}

}

```



---

### Spring MVC에서의 MapReduce

- `springedu` 에 `HadoopDAO3, HadoopController3`, `mapred` 폴더 넣기

#### HadoopController3.java

```java
package my.spring.springedu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.HadoopDAO3;
@Controller
public class HadoopController3 {
	@Autowired
	HadoopDAO3 dao;	
	public HadoopController3() {
		System.out.println("HadoopController3");
	}
	@RequestMapping("/hadoopmr")  
	public ModelAndView put(String action){
		String result = "";
		if(action.equals("mapreduce")) 
			result = dao.mapreduce();
		else if(action.equals("result")) 
			result = dao.mrresult();
		else 
			result = "Query 문자열을 확인해 주세요.";
		ModelAndView mav = new ModelAndView();
		mav.addObject("hadooprw", result);
		mav.setViewName("hadoopView");		
		return mav;
	}	
}

```



#### HadoopDAO3.java

```java
package dao;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class HadoopDAO3 {
	@Autowired
	private Configuration conf;//bin 설정 파일에 정의된 것을 받아와서 Autowired

	public HadoopDAO3() {
		System.out.println("HadoopDAO3");
		
	}
	public String mapreduce() {
		String result = "";
		//try-catch를 사용해 FileSystem 객체생성하므로 자동 close 해준다
		try (FileSystem hdfs = FileSystem.get(conf)) {
			Path filePath = new Path("/result/springmr");
			if (hdfs.exists(filePath)) {
				hdfs.delete(filePath, true);//filePath 가 잇으면 지워버리는 구문, true 주면 폴더 아래 모든내용 지움
			}
			
			//이름 임의로 주어지지만 이름 설정해줌
			Job job = Job.getInstance(conf, "SPRINGHW");

		    job.setJarByClass(HadoopDAO3.class);//DAO 가 DriverClass
		    job.setMapperClass(mapred.MyMapper.class);//MapperClass지정 실행
		    job.setReducerClass(mapred.MyReducer.class);//ReducerClass지정 실행

		    job.setInputFormatClass(TextInputFormat.class);//Text이기때문에 Input,Output지정
		    job.setOutputFormatClass(TextOutputFormat.class);

		    job.setOutputKeyClass(Text.class);
		    job.setOutputValueClass(IntWritable.class);//횟수...숫자이므로 IntWritable

		    FileInputFormat.addInputPath(job, new Path("/edudata/product_click.log"));
		    FileOutputFormat.setOutputPath(job, new Path("/result/springmr"));

		    job.waitForCompletion(true);//job 을 보내고 수행끝날대까지 대기
	
			result = "맵리듀스작업 성공!! ";
		} catch (Exception e) {
			e.printStackTrace();
			result = "맵리듀스작업 실패!! ";
		}
		return result;
	}
	//result 에 저장된거 불러와서 읽어주는 메소드
	public String mrresult() {
		String result = "";
		try (FileSystem hdfs = FileSystem.get(conf)) {
			Path filePath = new Path("/result/springmr/part-r-00000");
			if (hdfs.exists(filePath)) {
				BufferedReader r = new BufferedReader(new InputStreamReader(hdfs.open(filePath), "utf-8"));
				String line = null;
				while ((line = r.readLine()) != null) {
					System.out.println(line);
					result += line + "<br>";
				}
				r.close();
			} else {
				result = "MR 결과가 존재하지 않습니다!!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "오류가 발생했어요";
		}
		return result;
	}
}

```



#### MyMapper, MyReducer, LogParser

- `LogParser`가 글짜들 읽어주는 기능

```java
package mapred;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	private final static IntWritable outputvalue=new IntWritable(1);
	private Text outputkey=new Text();
	
	public void map(LongWritable key, Text value, Context context)
	throws IOException, InterruptedException{		
		LogParser parser=new LogParser(value);//LogParser 를 만들어줘서 기능을 실행해줌
		outputkey.set(parser.getProduct());
		context.write(outputkey, outputvalue);
	}
}


package mapred;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	private IntWritable result = new IntWritable();

	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		result = new IntWritable();
		for (IntWritable value : values) {
			System.out.println(key.toString()+":"+value.get());
			sum += value.get();
		}
		result.set(sum);
		context.write(key, result);		
	}
}




package mapred;

import org.apache.hadoop.io.Text;

public class LogParser {
	private String product;
	
	public LogParser(Text text){
		try{
			String[] columns=text.toString().split(" ");//문장을 나눠줌
			product=columns[1];
			
		}catch(Exception e){
			System.out.println("!!"+e.getMessage());
		}
	}

	public String getProduct() {
		return product;
	}
	
}


```



#### ---같은기능인데 어플리케이션---

#### ProductCount.java (hadoopexam..not MVC)

- 전반적으로 똑같은 로직, try catch 로 디렉토리 갱신만 잡아줌

```java
package exerise3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class ProductCount {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();//configuration 객체 생성
		conf.set("fs.default.name", "hdfs://192.168.111.120:9000");
		
		try (FileSystem hdfs = FileSystem.get(conf)) {
			Path filePath = new Path("/result/spring1");
			if (hdfs.exists(filePath)) {
				hdfs.delete(filePath, true);//filePath 가 잇으면 지워버리는 구문, true 주면 폴더 아래 모든내용 지움
			}
		//[1] conf객체 [2] (DriverClass 의 이름)
		Job job = Job.getInstance(conf, "ProductCount");

		job.setJarByClass(ProductCount.class);
		job.setMapperClass(ProductMapper.class);
		job.setReducerClass(ProductReducer.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setOutputKeyClass(Text.class);//Text ..
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path("/edudata/product_click.log"));
		FileOutputFormat.setOutputPath(job, new Path("/result/exerout3"));

		job.waitForCompletion(true);
		System.out.println("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
}

```



#### ProductMapper,Reducer,LogParser

- `ProductMapper` 만 `LogParser`로 작동하게 바꿔줬다

```java
package exerise3;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ProductMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		LogParser parser=new LogParser(value);
		
		word.set(parser.getProduct());
		context.write(word, one);

	}
}


package exerise3;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ProductReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	private IntWritable result = new IntWritable();
	
	
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		for (IntWritable val : values) {
			sum += val.get();
		}

			result.set(sum);
			context.write(key, result);
	}
}




package exerise3;

import org.apache.hadoop.io.Text;

public class LogParser {
	private String product;
	
	public LogParser(Text text){
		try{
			String[] columns=text.toString().split(" ");//문장을 나눠줌
			product=columns[1];
			
		}catch(Exception e){
			System.out.println("!!"+e.getMessage());
		}
	}

	public String getProduct() {
		return product;
	}
	
}

```



---

## 실습

### exercise1(Mapper 변경)

- 내용의 글자 갯수 카운팅 해주는 경우 `Mapper`를 변경

```java
package exercise1;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		StringTokenizer itr = new StringTokenizer(value.toString());

		while (itr.hasMoreTokens()) {
			word.set(itr.nextToken());
//			System.out.println(word.toString().length());
			if(word.toString().length()>=3&&word.toString().length()<=5) {
				context.write(word, one);
			}


		}

	}
}
//System.out.println(word+" = word : "+word.getLength() + " = length");
```



### exercise2(Reducer 변경)

- 단어의 갯수를 카운팅 할때 `Reducer` 를 변경

```java
package exercise2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	private IntWritable result = new IntWritable();
	
	
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		for (IntWritable val : values) {
			int data = val.get();
			System.out.print(data+ " " );
			sum += val.get();
		}
		System.out.println("sum : "+sum);

			result.set(sum);

			if(sum>=4) {	
				context.write(key, result);
			}
	}
}
```

