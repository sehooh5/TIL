# Spark

- 한번에 수행하는 것이 아닌 `action`요청이 오면 한꺼번에 처리한다
  (빠른 이유)
- `spark master`가 `Hadoop MapReduce`의 `application master`역할
- 마스터를 `yarn`을 사용할 수도 있고 아닐 수도 있다(대용량에 용이)

## 환경설정

- 클러스터 컴퓨터를 기존 4대에서 2대로 줄여준다

- `hadoop` 의 홈디렉토리에 slaves 를 편집한다(**데이터노드 정보**)

  ```
  cd $HADOOP_HOME/etc/hadoop
  vi slaves
  ```

- `hdfs-site.xml` 의 `replication`개수 수정(**복제** 개수)

  ```
  vi hdfs-site.xml
  
  :set number (번호주기)
  :22 (22 번째 줄로 이동)
  ```

- `HDFS`데몬 기동

  ```
  start-dfs.sh
  ```

- `spark.apache.org` 에서 해당 `spark`를 다운로드 한다

- 파일은 `.tgz` 인데 리눅스 압축 프로그램이므로 `반디집`을 이용하여 압축 해제해준다

- `spark-2.4.5-bin-hadoop2.7` 폴더를 `c://` 바로 밑에 저장

- 환경 변수 추가

  ```
  SPARK_HOME	c:\spark-2.4.5-bin-hadoop2.7
  PATH		%SPARK_HOME%\bin;%SPARK_HOME%\sbin
  ```

- `SPARK_HOME`의 `conf` 디렉토리에 `log4j` 설정파일을 만들고(`log4j.properties.template`파일을 `log4j.properties`로 복사) 로그 레벨을 `INFO` 에서 `WARN`으로 변경한다



## cmd 에서 실행 

- `cmd`에서 `spark-shell`명령으로 실행

- ```
  val textFile = sc.textFile("hdfs://192.168.111.120:9000/edudata/president_moon.txt")
  //var - 일반변수
  //val - final 형
  ```

  텍스트 파일을 읽어와서 `textFile`이라는 변수에 담앗다

  (**RDD 객체!!! 중요**)

- ```
  val counts = textFile.flatMap(line=>line.split(" ")).map(word=>(word,10)).reduceByKey(_+_)
  
  // . 마다 RDD객채가 생김!!!!(중요!!)
  //(word,1) - 괄호 : key Value의 `튜플객체` 내보냄
  //reduceByKey(_+_) - key Value 로 다 합쳐줌
  ```

  `RDD`객체가 `.`**마다** 생성됨

  (word,1) 해주면 `key value` 쌍의 **튜플객체** 생성해줌(=파이썬)

- ```
  count.collect()
  ```

  `Array`객체로 만들어서 출력해준다 = **action** = 이때 `RDD`들 실행






## 프로그램으로 실행

- `SparkContext` :  

  - `Spark` 어플리케이션과 클러스터의 연결을 관리하는 객체

  - `RDD` 등 `Spark` 에서 사용되는 주요 객체는 `SparkContext` 객체를
    통해서 생성된다 .

  - `spark master` 가 **자동**으로 실행된다

    ```java
    //local 대신 yarn 을 사용해 대용량 처리 가능
    SparkConf conf = new SparkConf().setMaster("local ").setappName("xxx");
    JavaSparkContext sc = new JavaSparkContext(conf);
    ```

- `RDD (Resilient Distributed DataSet) `: (변하지 않는)

  - `Spark` 에서 처리되는 데이터는 `RDD` 객체로 생성하여 처리한다
    - `hdfs` 에서 읽어서 만들거나
    - 직접 크롤링해서 `Collection` 객체 만들어 만들 수 있다

- `RDD`특징

  - `Read Only(immutable)`
  - 1~n 개의 `partition` 으로 구성 가능
  - 병렬적 분산 처리가 가능하다
  - `RDD` 의 연산은 `Transformation` 연산과 `Action` 연산으로 나뉜다
  - `Transformation` 은 `Lazy execution` 을 지원한다
    - `RDD` 만들 때 실행 바로하는 것이 아닌 `lineage`만 만듬
    - `lineage`는 `RDD`의 생성 순서
  - `lineage` 를 통해서 `fault tolerant`(결함 내성) 를 확보한다

- `Transformations` 와 `Actions`

  - `Transformations` : `Lineage`를 만드는 과정, 기존 `RDD`로 새로운 `RDD` 만드는 과정, 리턴값 = `RDD`
  - `Action`  : `Lineage`를 실행하고 결과를 만든다, 리턴값 = `RDD`이외

- `Lazy-execution` 이란

  - `Action`연산이 실행되기 이전에 `Transformation`연산이 처리되지 않는 것을 의미



## sparkexam

#### SparkUtils

- 모든 코드에서 한번씩 실행하여 `conf` 객체 생성

```java
package sparkexam;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class SparkUtils {
	public static JavaSparkContext getSparkContext(String name) {
		SparkConf conf = new SparkConf().setAppName(name).setMaster("local");
		return new JavaSparkContext(conf);
	}
}

```



#### RDDOperate1

```java
package sparkexam;

import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class RDDOperate1 {

	public static void main(String[] args) throws Exception {

		JavaSparkContext sc = SparkUtils.getSparkContext("test1");
		JavaRDD<String> rdd = sc.textFile("hdfs://192.168.111.120:9000/edudata/fruits.txt");
		//모두 action
		System.out.println(rdd.first());//리턴갑 제너릭스
		
		System.out.println(rdd.take(3));
		
		System.out.println(rdd.collect());
		
		List<String> list = rdd.collect();
		for (String v : list) {
			System.out.println(v);
		}
		System.out.println(rdd.count());
		
		sc.stop();
	}
}
```



#### RDDOperate2(map)

```java
package sparkexam;

import java.util.Arrays;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class RDDOperate2 {
	
	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("test2");
		doMap(sc);
		sc.stop();
	}
	//map()메서드를 공부한다
	public static void doMap(JavaSparkContext sc) {
		JavaRDD<Integer> rdd1 = sc.parallelize(Arrays.asList(1, 2, 3, 4, 5));

		// Java7
		JavaRDD<Integer> rdd2 = rdd1.map(new Function<Integer, Integer>() {
			@Override
			public Integer call(Integer v1) throws Exception {
				return v1 + 10;
			}
		});

		// Java8 Lambda
		JavaRDD<Integer> rdd3 = rdd1.map(v1 -> v1 + 10);

		System.out.println("java 7 : " + rdd2.collect());
		System.out.println("java 8 : " + rdd3.collect());
	}	
	
}
```



#### RDDOperate3(for each)

```java
package sparkexam;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;

public class RDDOperate3 {

	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("test3");

		doForeach(sc);

		sc.stop();
	}
	//for each공부
	public static void doForeach(JavaSparkContext sc) {
		List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		JavaRDD<Integer> rdd = sc.parallelize(data);
		// Java7
        //VoidFunction : 기능만 수행하고 return 없다 즉 "action"
		rdd.foreach(new VoidFunction<Integer>() {
			@Override
			public void call(Integer t) throws Exception {
				System.out.println("data : " + t);
			}
		});
		// Java8
		rdd.foreach((Integer t) -> System.out.println("data : " + t));
	}	

	
}
```



#### RDDOperate4(sortBy)

```java
package sparkexam;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class RDDOperate4 {

	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("test4");
		List<Integer> data = fillToN(100);
		JavaRDD<Integer> rdd = sc.parallelize(data);
		
		JavaRDD<Integer> rddSort = rdd.sortBy(f->f, false, 1);//false DESC
		System.out.println(rddSort.take(10));
		
		Integer result1 = rdd.first();
		System.out.println(result1);
		
		List<Integer> result2 = rdd.take(5);
		System.out.println(result2);	
		
		sc.stop();
	}

	public static ArrayList<Integer> fillToN(int n) {
		ArrayList<Integer> rst = new ArrayList<>();
		for (int i = 0; i < n; i++)
			rst.add(i);
		return rst;
	}
	
}
```



#### RDDOperate5(flatmap)

```java
package sparkexam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class RDDOperate5 {

	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("test5");
		
		doMapAndFlatMap(sc);

		sc.stop();
	}
	//compare map with flatMap
	//flatMap 은 차원을 줄여서 RDD 를 만들어주는 메서드이다
	public static void doMapAndFlatMap(JavaSparkContext sc) {
		List<String> data = new ArrayList<>();
		data.add("apple,orange");
		data.add("grape,apple,mango");
		data.add("blueberry,tomato,orange");
		JavaRDD<String> rdd1 = sc.parallelize(data);

		JavaRDD<String[]> rdd2 = rdd1.map((String t) -> t.split(","));
		JavaRDD<String> rdd3 = rdd1.flatMap((String t) -> Arrays.asList(t.split(",")).iterator());//각각의 엘먼트가 아닌 한개의 객체로 만들어버린다
		
		System.out.println(rdd2.collect());
		System.out.println(rdd3.collect());
		
		JavaRDD<Integer> rdd4 = rdd1.map((String t) -> t.length());
		System.out.println(rdd4.collect());
		
	}
	
}
```



#### RDDOperate6(filter, 정규표현)

```java
package sparkexam;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class RDDOperate6 {

	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("test6");

		doFilter(sc);

		sc.stop();
	}

	public static void doFilter(JavaSparkContext sc) {
		List<String> data = Arrays.asList("가나다", "abc", "ABC", "p111", "1234", "$@!%$");
		JavaRDD<String> rdd1 = sc.parallelize(data);//병렬처리
		
		JavaRDD<String> rdd2 = rdd1.filter(w -> w.matches("p\\d{3}"));
		System.out.println(rdd2.collect());
		
		rdd2 = rdd1.filter(w -> w.matches("p\\d{4}"));
		System.out.println(rdd2.collect());
	
		rdd2 = rdd1.filter(w -> w.matches("\\d+"));
		System.out.println(rdd2.collect());
		
		rdd2 = rdd1.filter(w -> w.matches("\\p{Upper}+"));
		System.out.println(rdd2.collect());
		
		rdd2 = rdd1.filter(w -> w.matches("\\p{Punct}+"));
		System.out.println(rdd2.collect());
		
		rdd2 = rdd1.filter(w -> w.matches("\\p{Alpha}+"));
		System.out.println(rdd2.collect());
		
		rdd2 = rdd1.filter(w -> w.matches("[가-힣]+"));
		System.out.println(rdd2.collect());
	}	
}
```



#### RDDOperate7(reduce)

- 결과만 보면 전부 더한 결과

```java
package sparkexam;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;

public class RDDOperate7 {

	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("test7");

		doReduce(sc);

		sc.stop();
	}

	public static void doReduce(JavaSparkContext sc) {
		List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		JavaRDD<Integer> rdd = sc.parallelize(data, 3);//3덩어리로 나누기
		// Java7
		int result = rdd.reduce(new Function2<Integer, Integer, Integer>() {
			@Override
			public Integer call(Integer v1, Integer v2) throws Exception {
				return v1 + v2;
			}
		});
		System.out.println(result);
		// Java8
		int result2 = rdd.reduce((v1, v2) -> v1 + v2);
		System.out.println(result2);
	}

	
}
```



#### RDDOperate8(reduceByKey-wordcounting)

```java
package sparkexam;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;

import scala.Tuple2;

public class RDDOperate8 {

	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("test8");
		doReduceByKey(sc);
		sc.stop();
	}

	public static void doReduceByKey(JavaSparkContext sc) {
		List<Tuple2<String, Integer>> data = Arrays.asList(new Tuple2<>("a", 1), new Tuple2<>("b", 1), new Tuple2<>("b", 1));

		JavaPairRDD<String, Integer> rdd = sc.parallelizePairs(data);//튜플 객치 (name value 형태)

		// Java7//reduceByKey = key를 기준으로 value 를 모음..shuffle 같은 작업들이 수행된다
		JavaPairRDD<String, Integer> result = rdd.reduceByKey(new Function2<Integer, Integer, Integer>() {//첫번째 매개변수, 두번째 매개변수, 리턴값의 유형
			@Override
			public Integer call(Integer v1, Integer v2) throws Exception {
				return v1 + v2;
			}
		});
		System.out.println(result.collect());
		// Java8 Lambda
		JavaPairRDD<String, Integer> result2 = rdd.reduceByKey((v1,  v2) -> v1 + v2);
		System.out.println(result2.collect());
		//튜플객체 뽑아내는 방법
		result2.foreach(tupledata->
				System.out.println(tupledata._1+"==="+tupledata._2));
	}

}
```



#### RDDOperate9(RDD 여러개)

```java
package sparkexam;

import java.util.Arrays;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class RDDOperate9 {

	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("test9");

		doMap(sc);

		sc.stop();
	}

	public static void doMap(JavaSparkContext sc) {
		
		JavaRDD<String> rdd1 = sc.textFile("hdfs://192.168.111.120:9000/edudata/fruits.txt");
		
		JavaRDD<String> rdd2 = rdd1.flatMap((String line) -> Arrays.asList(line.split("[\\s]+")).iterator());
		// [\\s]+ 공백문자 한개이상 : 정규표현식
		
		JavaRDD<String> rdd3 = rdd2.filter(word -> word.length() > 4);
		
		JavaPairRDD<String, Integer> rdd4 = rdd3.mapToPair(word -> new Tuple2<String, Integer>(word, 1));
		//<key = String, Valuew = Integer> 맞춰줘야함
		
		JavaPairRDD<String, Integer> rdd5 = rdd4.reduceByKey((a, b) -> a + b);		
		
		System.out.println(rdd5.collect());

	}	
}
```



#### RDDOperate10(key-value 값 변경)

```java
package sparkexam;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class RDDOperate10 {
	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("test10");

		doSortByKey(sc);

		sc.stop();
	}

	public static void doSortByKey(JavaSparkContext sc) {
		List<Tuple2<String, Integer>> data = Arrays.asList(new Tuple2<>("q",3), new Tuple2<>("z",8), new Tuple2<>("a", 2));
		JavaPairRDD<String, Integer> rdd = sc.parallelizePairs(data);
		JavaPairRDD<String, Integer> result = rdd.sortByKey();
		System.out.println(result.collect());
		
		JavaPairRDD<Integer, String> rdd1 = rdd.mapToPair(x -> x.swap());//swap 을 해주면 key-value 를 바꿔준다
		JavaPairRDD<Integer, String> rdd2 = rdd1.sortByKey(false);
		JavaPairRDD<String, Integer> rdd3 = rdd2.mapToPair(x -> x.swap());
		
		rdd3.foreach(item -> System.out.println(item));		
	}
}
```



#### SparkLab1(상품 개수 count,key 값 정렬)

```java
package sparkexam;

import java.util.Arrays;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class SparkLab1 {

	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("SparkLab1");//객체 생성

		doReduceByKey(sc);
		
		sc.stop();
	}

	public static void doReduceByKey(JavaSparkContext sc) {
		JavaRDD<String> rdd1 = sc.textFile("hdfs://192.168.111.120:9000/edudata/product_click.log");
		
		JavaRDD<String> rdd2 = rdd1.flatMap((String line) -> Arrays.asList(line.split("[\\s]+")).iterator());

		JavaRDD<String> rdd3 = rdd2.filter(word -> word.matches("p\\d{3}"));
		//p로 시작하면  ^p
		JavaPairRDD<String,Integer> rdd4= rdd3.mapToPair(word -> new Tuple2<String, Integer>(word, 1));

		JavaPairRDD<String, Integer> rdd5 = rdd4.reduceByKey((a, b) -> a + b);	
		
		rdd5.foreach(item -> System.out.println(item));	
	}

}
```



#### SparkLab2(상품 개수 count,value 값 정렬)

```java
package sparkexam;

import java.util.Arrays;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class SparkLab2 {

	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("SparkLab1");//객체 생성

		doReduceByKey(sc);
		
		sc.stop();
	}

	public static void doReduceByKey(JavaSparkContext sc) {
		JavaRDD<String> rdd1 = sc.textFile("hdfs://192.168.111.120:9000/edudata/product_click.log");
		
		JavaRDD<String> rdd2 = rdd1.flatMap((String line) -> Arrays.asList(line.split("[\\s]+")).iterator());

		JavaRDD<String> rdd3 = rdd2.filter(word -> word.matches("p\\d{3}"));
		//p로 시작하면  ^p
		JavaPairRDD<String,Integer> rdd4= rdd3.mapToPair(word -> new Tuple2<String, Integer>(word, 1));

		JavaPairRDD<String, Integer> rdd5 = rdd4.reduceByKey((a, b) -> a + b);	
		
		JavaPairRDD<Integer, String> rdd6 = rdd5.mapToPair(x -> x.swap());
		JavaPairRDD<Integer, String> rdd7 = rdd6.sortByKey(false);	
		JavaPairRDD<String, Integer> rdd8 = rdd7.mapToPair(x -> x.swap());
		
		rdd8.foreach(item -> System.out.println(item));	
	}

}
```



#### SparkLab3(정규표현식, counting)

```java
package sparkexam;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class SparkLab3 {

	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("SparkLab1");//객체 생성

		doReduceByKey(sc);
		
		sc.stop();
	}

	public static void doReduceByKey(JavaSparkContext sc) {
		JavaRDD<String> rdd1 = sc.textFile("hdfs://192.168.111.120:9000/edudata/tomcat_access_log.txt");
		
		JavaRDD<String> rdd2 = rdd1.flatMap((String line) -> Arrays.asList(line.split("- -")[1]).iterator());

		JavaRDD<String> rdd3 = rdd2.flatMap((String line) -> Arrays.asList(line.substring(14, 16)).iterator());


		
		JavaPairRDD<String,Integer> rdd4 = rdd3.mapToPair(w->new Tuple2<String, Integer>(w,1));
		
		JavaPairRDD<String,Integer> rdd5 = rdd4.reduceByKey((a,b)->a+b);
		
		
		JavaPairRDD<Integer,String> rdd6 = rdd5.mapToPair(f->f.swap());
		JavaPairRDD<Integer,String> rdd7 = rdd6.sortByKey(false);
		JavaPairRDD<String,Integer> rdd8 = rdd7.mapToPair(f->f.swap());
		
		
		System.out.println("가장 많이 요청된 시간은 "+rdd8.first()._1+"시 입니다");


	}

}
```



#### RDDOperate11(zip,union,distinct)

```java
package sparkexam;

import java.util.Arrays;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class RDDOperate11 {

	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("test11");

		doZip(sc);

		sc.stop();
	}

	public static void doZip(JavaSparkContext sc) {
		JavaRDD<String> rdd1 = sc.parallelize(Arrays.asList("a", "b", "c"));
		JavaRDD<Integer> rdd2 = sc.parallelize(Arrays.asList(1, 2, 3);
		JavaPairRDD<String, Integer> result = rdd1.zip(rdd2);//zip 합하기
		System.out.println(result.collect());
		
		JavaRDD<String> rdd3 = sc.parallelize(Arrays.asList("a", "b", "c"));
		JavaRDD<String> rdd4 = sc.parallelize(Arrays.asList("a", "e", "f"));
		JavaRDD<String> result2 = rdd3.union(rdd4);//all
		System.out.println(result2.collect());
		
		JavaRDD<String> result3 = result2.distinct();//중복되는 애 unique 하게
		System.out.println(result3.collect());
	}

}
```



#### RDDOperate12(join )

```java
package sparkexam;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class RDDOperate12 {

	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("test12");

		doJoin(sc);

		sc.stop();
	}
	//같은 key 값을 갖고 있는 데이터끼리 조인함............개수는 상관 없음
	public static void doJoin(JavaSparkContext sc) {
		List<Tuple2<String, Integer>> data1 = Arrays.asList(new Tuple2<>("a", 1), new Tuple2<>("b", 1), new Tuple2<>("c", 1),
				new Tuple2<>("d", 1), new Tuple2<>("e", 1));
		List<Tuple2<String, Integer>> data2 = Arrays.asList(new Tuple2<>("b", 2), new Tuple2<>("c", 2));
		
		JavaPairRDD<String, Integer> rdd1 = sc.parallelizePairs(data1);
		JavaPairRDD<String, Integer> rdd2 = sc.parallelizePairs(data2);

		JavaPairRDD<String, Tuple2<Integer, Integer>> result = rdd1.<Integer>join(rdd2);
		System.out.println(result.collect());

	}

}
```



#### RDDOperate13(groupby)

```java
package sparkexam;

import java.util.Arrays;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class RDDOperate13 {

	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("test13");

		doGroupBy(sc);

		sc.stop();
	}

	public static void doGroupBy(JavaSparkContext sc) {
		JavaRDD<Integer> rdd1 = sc.parallelize(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

		// Java7 //첫번째 매개변수...두번째는 리턴값
		JavaPairRDD<String, Iterable<Integer>> rdd2 = rdd1.groupBy(new Function<Integer, String>() {
			@Override
			public String call(Integer v1) throws Exception {
				return (v1 % 2 == 0) ? "even" : "odd";
			}
		});

		// Java8 Lambda
		JavaPairRDD<String, Iterable<Integer>> rdd3 = rdd1.groupBy((Integer v1) -> (v1 % 2 == 0) ? "even" : "odd");

		System.out.println(rdd2.collect());
		System.out.println(rdd3.collect());
	}
}
```



#### WordCount7

```java
package sparkexam;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public class WordCount7 {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName("UNICO").setMaster("local");
		JavaSparkContext sc = new JavaSparkContext(conf);
	
		JavaRDD<String> rdd1 = sc.textFile("hdfs://192.168.111.120:9000/edudata/fruits.txt");

		JavaRDD<String> rdd2 = rdd1.flatMap(new FlatMapFunction<String, String>() {
			@Override
			public Iterator<String> call(String v) throws Exception {
				return Arrays.asList(v.split("[\\s]+")).iterator();
			}
		});

		JavaPairRDD<String, Long> rdd3 = rdd2.mapToPair(new PairFunction<String, String, Long>() {
			@Override
			public Tuple2<String, Long> call(String s) throws Exception {
				return new Tuple2<String, Long>(s, 1L);
			}
		});

		JavaPairRDD<String, Long> rdd4 = rdd3.reduceByKey(new Function2<Long, Long, Long>() {
			@Override
			public Long call(Long v1, Long v2) throws Exception {
				return v1 + v2;
			}
		});
		
		System.out.println(rdd4.collect());
		rdd4.saveAsTextFile("hdfs://192.168.111.120:9000/result/sparkoutput10");
		sc.close();
		sc.stop();

	}

}

```



#### WordCount8

```java
package sparkexam;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class WordCount8 {

	public static void main(String[] args) throws Exception {
		SparkConf conf = new SparkConf().setAppName("WordCount_version8").setMaster("local");
	
		JavaSparkContext sc = new JavaSparkContext(conf);

		JavaRDD<String> rdd1 = sc.textFile("hdfs://192.168.111.120:9000/edudata/fruits.txt");
	

		JavaRDD<String> rdd2 = rdd1.flatMap(line -> Arrays.asList(line.split("[\\s]+")).iterator());

		JavaPairRDD<String, Long> rdd3 = rdd2.mapToPair(w -> new Tuple2<String, Long>(w, 1L));

		JavaPairRDD<String, Long> rdd4 = rdd3.reduceByKey((x, y) -> x + y);
		
		rdd4.saveAsTextFile("hdfs://192.168.111.120:9000/result/sparkoutput12");
		Thread.sleep(50000);
		sc.close();

	}

}

```





## Scala 명령

```scala
val rdd1 = sc.textFile("hdfs://192.168.111.120:9000/edudata/fruits.txt")
val rdd2 = rdd1.flatMap(line => line.split(" "))//iterator 안써도됨
val rdd3 = rdd2.map(word => (word, 1))
val rdd4 = rdd3.reduceByKey(_ + _)//앞에꺼 + 뒤에꺼
rdd4.collect()
```

- http://localhost:4040/jobs/ 실행
- 실제로 어떻게 작용했는지 시각적으로 볼 수 있다