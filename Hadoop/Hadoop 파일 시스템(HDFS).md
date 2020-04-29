# Hadoop 파일 시스템(HDFS)

- `NameNode` : 작업관리자, 우리가 얘한테 부탁해서 `DataNodes`에게 전달하여 분산시켜서 저장하게끔 해준다
- 만약 파일을 A,B,C 세개로 나누어서 저장을 하게되는데 또 `DataNodes` 3개로 분산하여 저장한다. 이유는 한개의 원본 두개의 사본 사용. 에러나도 안전.
- 각각 한개씩 `NameNode` 에게 요청하여 각각 분산되어 저장된다
- `hear beat` : `DataNodes`에 보관된 자료가 멀쩡한지 `NameNode `에 전달
- 기본개념 참고 URL : 
  - https://www.youtube.com/watch?v=lU9OLSVyIuw : 기본개념
  - https://www.youtube.com/watch?v=xacUzaFYQCI : 파일 읽기,쓰기



### 1. 명령어로 실행 (참고자료 3page 참고)

1. `학습자료-0429`에 있는 3개 파일을 `master`의 `sampledata`디렉토리를 생성후 저장해준다

2. `bzip2 -kd 2008.csv.bz2` 명령을 실행해서 파일 압축을 해제해준다

3. `hdfs dfs` 는 hdfs의 명령어의 기본

4. `hdfs dfs -mkdir /파일명` : 폴더 만들기

5. `hdfs dfs -ls /` : 파일 리스트 출력

6. `hdfs dfs -chmod 777 /edudata`  : 사용자 허가모드를 변경해줘야 시스템에서 데이터를 작성, 변경 할 수 있다. (p.196)

   ```
   [root@master sampledata]# hdfs dfs -mkdir /edudata
   [root@master sampledata]# hdfs dfs -ls /
   Found 1 items
   drwxr-xr-x   - root supergroup          0 2020-04-29 14:16 /edudata
   [root@master sampledata]# hdfs dfs -chmod 777 /edudata
   //777의미는 rwx 전부 허용(밑에 참고)
   [root@master sampledata]# hdfs dfs -ls /
   Found 1 items
   drwxrwxrwx   - root supergroup          0 2020-04-29 14:16 /edudata    ////////사용자 허가모드하면 이렇게 변환된다
   
   ```

   -  **사용자 허가모드**
     - r : `read` / 1=Y, 0=N
     - w : `write` / 1=Y, 0=N 
     - x :  `excution` / 1=Y, 0=N
     - 예 : `111 = rwx = 7`, `101 = r x = 5`, `110 = rw  = 6`

7. 파일 넣기 : 

   ```
   [root@master sampledata]# hdfs dfs -put /root/sampledata/president_moon.txt  /edudata
   [root@master sampledata]# hdfs dfs -put /root/sampledata/product_click.log  /edudata
   [root@master sampledata]# hdfs dfs -put /root/sampledata/2008.csv  /edudata
   ```

   

8. MapReduce 결과 보관하기 위해 `/result` 폴더 만든 후 사용자 허가 777

   ```
   [root@master sampledata]# hdfs dfs -mkdir /result
   [root@master sampledata]# hdfs dfs -chmod 777 /result
   ```





### 2. 프로그램으로 실행(Java로 사용)

#### 처음에는 일반 `Java Project-hadoopexam` 으로 실행

- 파일 세개 복사하는데 API 때문에 오류가 난다 따라서..

- Maven 이 관리하는 자바프로젝트로 변경

  - `우클릭-Configure - Convert to Maven Project`

- `pom.xm`l  에 `<version>` 과 `<build>` 사이에 추가

  ```
  	<dependencies>
  		<!-- https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-common -->
  		<dependency>
  			<groupId>org.apache.hadoop</groupId>
  			<artifactId>hadoop-common</artifactId>
  			<version>2.7.7</version>
  		</dependency>
  		<!-- https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-client -->
  		<dependency>
  			<groupId>org.apache.hadoop</groupId>
  			<artifactId>hadoop-client</artifactId>
  			<version>2.7.7</version>
  		</dependency>
  		<!-- https://mvnrepository.com/artifact/jdk.tools/jdk.tools -->
  		<dependency>
  			<groupId>jdk.tools</groupId>
  			<artifactId>jdk.tools</artifactId>
  			<version>1.8</version>
  			<scope>system</scope>
  			<systemPath>C:/Program Files/Java/jdk1.8.0_231/lib/tools.jar</systemPath>
  		</dependency>
  	</dependencies>
  ```

- 파일 관련 코드 3개

  1. 파일 읽기

     - 1. 

       ```java
       package hdfsexam;
       
       import org.apache.hadoop.conf.Configuration;
       import org.apache.hadoop.fs.FSDataInputStream;
       import org.apache.hadoop.fs.FileSystem;
       import org.apache.hadoop.fs.Path;
       import org.apache.hadoop.io.IOUtils;
       
       public class FileSystemCat {
       	public static void main(String[] args) throws Exception {
       		Configuration conf = new Configuration(); //Configuration 객체 생성한 후
       		conf.set("fs.defaultFS", "hdfs://192.168.111.120:9000");
       		FileSystem hdfs = FileSystem.get(conf); 
               //파일 시스템에 대한 객체를 리턴
       		FSDataInputStream in = null;
       		try {
       			in = hdfs.open(new Path("/edudata/president_moon.txt")); 
       //Path 객체로 만든 후 open,write,append ,,,하지만 update 는 허용하지 않음
       //빅데이터 특성상 자주 update 되는 파일들은 Hadoop에 적합하지 않다 -> Oracle 에!
       			IOUtils.copyBytes(in, System.out, 4096, false);	//
       		} finally {
       			IOUtils.closeStream(in);
       		}
       	}
       }
       
       ```

  2. 파일 작성

     ```java
     package hdfsexam;
     
     import java.io.BufferedWriter;
     import java.io.File;
     import java.io.FileReader;
     import java.io.OutputStreamWriter;
     
     import org.apache.hadoop.conf.Configuration;
     import org.apache.hadoop.fs.FSDataOutputStream;
     import org.apache.hadoop.fs.FileSystem;
     import org.apache.hadoop.fs.Path;
     
     public class FileWrite {
     	public static void main(String[] args) {
     		try {
     			Configuration conf = new Configuration();
     			conf.set("fs.defaultFS", "hdfs://192.168.111.120:9000");
     			FileSystem hdfs = FileSystem.get(conf);
     			String fname = "dessert-menu.csv";
     			File f = new File("c:/seho/" + fname);
     			if (!f.exists()) {
     				System.out.println("파일이 없음!!");
     				return;
     			}
     			Path path = new Path("/edudata/" + fname);
     			if (hdfs.exists(path)) {
     				hdfs.delete(path, true);
     			}
     			long size = f.length();
     			FileReader fr = new FileReader(f);
     			char[] content = new char[(int) size];
     			fr.read(content);
     			FSDataOutputStream outStream = hdfs.create(path);
     			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
     			writer.write(content);
     			writer.close();
     			outStream.close();
     			fr.close();
     			System.out.println(size + " 크기의 데이터 출력 완료!!");
     		} catch (Exception e) {
     			e.printStackTrace();
     		}
     	}
     }
     
     ```

  3. 파일 테스트해보기

     ```java
     package hdfsexam;
     
     import java.io.BufferedReader;
     import java.io.InputStreamReader;
     import java.net.URI;
     
     import org.apache.hadoop.conf.Configuration;
     import org.apache.hadoop.fs.FileStatus;
     import org.apache.hadoop.fs.FileSystem;
     import org.apache.hadoop.fs.LocatedFileStatus;
     import org.apache.hadoop.fs.Path;
     import org.apache.hadoop.fs.RemoteIterator;
     
     public class FileTest {
     	
     	private static final String srcDir = "/edudata/" ;
     
     	public static void main(String[] args) throws Exception {
     		String fileName = "president_moon.txt";
     		Path path = new Path(srcDir + fileName);
     		Configuration conf = new Configuration();
             conf.set("fs.defaultFS", "hdfs://192.168.111.120:9000");
     		FileSystem fs = FileSystem.get(URI.create(srcDir + fileName), conf);
     		
     		if(fs.exists(path)) { 
     			BufferedReader br =new BufferedReader(new InputStreamReader(fs.open(path)));
     			
     			while(br.ready()){//행단위로 읽는데 빅데이터에서는 선호하지 않음..너무 행이 많아서 	  	
     				String line = br.readLine();
     				System.out.println(line);
     	        }
     			
     			FileStatus fStatus = fs.getFileStatus(path);//file 정보 확인
     			if(fStatus.isFile()) {
     				System.out.println("");
     				System.out.println("===========================================");
     				System.out.println("File Block Size : " + fStatus.getBlockSize());
     				System.out.println("Group of File   : " + fStatus.getGroup());
     				System.out.println("Owner of File   : " + fStatus.getOwner());
     				System.out.println("File Length     : " + fStatus.getLen());
     			} else {
     				System.out.println("파일이 아닙니다.");
     			}
     			path = new Path(srcDir);
     			//Iterator 데이터들의 집합을 하나하나 접근하게해줌
     			//Linked List, Hash,, hasNext() 동안 계속 읽어라..
     			RemoteIterator<LocatedFileStatus> list = fs.listFiles(path, false);
     			while(list.hasNext()) {
     				LocatedFileStatus ls = list.next();
     				Path p = ls.getPath();
     				System.out.println(p.getName());
     			}
     		} else {//파일 못찾을때
     			System.out.println("파일을 찾을 수 없습니다.");
     		}
     	}
     
     }
     
     ```

     