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
   
9. `시작할 때 start-dfs.sh` 명령어로 `demon`띄우기



#### HADOOP 주요 API

1. 

2. `cat $HADOOP_HOME/etc/hadoop/hdfs-site.xml` 실행

   ```
   <configuration>
    <property>
         <name>dfs.replication</name>
         <value>3</value>	##(3개 복제해준다)
      </property>
      <property>
         <name>dfs.name.dir</name>
         <value>/root/hadoop-2.7.7/hdfs/name</value>
      </property>
      <property>
         <name>dfs.data.dir</name>
         <value>/root/hadoop-2.7.7/hdfs/data</value>
      </property>
      <property>
         <name>dfs.support.append</name>
         <value>true</value>	##(append 해준다 - true)
      </property>
      <property>
         <name>dfs.namenode.secondary.http-address</name>
         <value>slave1:50090</value>	##(Secondary 노드 정보)
      </property>
      <property>
         <name>dfs.namenode.secondary.https-address</name>
         <value>slave1:50091</value>
      </property> 
   </configuration>
   
   ```

3. `cd /root/hadoop-2.7.7/hdfs/data/current/BP-1276865796-192.168.111.120-1588127251595/current/finalized/subdir0/subdir0` 에 들어가면 우리가 저장한 자료들을 보여줌

   ```
   -rw-r--r--. 1 root root      7402  4월 29 14:27 blk_1073741825
   -rw-r--r--. 1 root root        67  4월 29 14:27 blk_1073741825_1001.meta
   -rw-r--r--. 1 root root     14174  4월 29 14:28 blk_1073741826
   -rw-r--r--. 1 root root       119  4월 29 14:28 blk_1073741826_1002.meta
   -rw-r--r--. 1 root root 134217728  4월 29 14:31 blk_1073741833
   -rw-r--r--. 1 root root   1048583  4월 29 14:31 blk_1073741833_1009.meta
   -rw-r--r--. 1 root root 134217728  4월 29 14:31 blk_1073741834
   -rw-r--r--. 1 root root   1048583  4월 29 14:31 blk_1073741834_1010.meta
   -rw-r--r--. 1 root root 134217728  4월 29 14:31 blk_1073741835
   -rw-r--r--. 1 root root   1048583  4월 29 14:31 blk_1073741835_1011.meta
   -rw-r--r--. 1 root root 134217728  4월 29 14:31 blk_1073741836
   -rw-r--r--. 1 root root   1048583  4월 29 14:31 blk_1073741836_1012.meta
   -rw-r--r--. 1 root root 134217728  4월 29 14:31 blk_1073741837
   -rw-r--r--. 1 root root   1048583  4월 29 14:31 blk_1073741837_1013.meta
   -rw-r--r--. 1 root root  18324704  4월 29 14:31 blk_1073741838
   -rw-r--r--. 1 root root    143171  4월 29 14:31 blk_1073741838_1014.meta
   -rw-r--r--. 1 root root       940  4월 29 17:10 blk_1073741839
   -rw-r--r--. 1 root root        15  4월 29 17:10 blk_1073741839_1015.meta
   
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

  4. 앞에 기능 전부 있음

     ```java
     package hdfsexam;
     
     import java.io.BufferedInputStream;
     import java.io.BufferedOutputStream;
     import java.io.File;
     import java.io.FileInputStream;
     import java.io.FileOutputStream;
     import java.io.IOException;
     import java.io.InputStream;
     import java.io.OutputStream;
     
     import org.apache.hadoop.conf.Configuration;
     import org.apache.hadoop.fs.FSDataInputStream;
     import org.apache.hadoop.fs.FSDataOutputStream;
     import org.apache.hadoop.fs.FileSystem;
     import org.apache.hadoop.fs.Path;
     
     public class FileSystemOperations {
       public void addFile(String source, String dest, Configuration conf) throws IOException {
         FileSystem fileSystem = FileSystem.get(conf);
     
         String filename = source.substring(source.lastIndexOf('/') + 1,source.length());
     
         if (dest.charAt(dest.length() - 1) != '/') {
           dest = dest + "/" + filename;
         } else {
           dest = dest + filename;
         }
     
         Path path = new Path(dest);
         if (fileSystem.exists(path)) {
           System.out.println("File " + dest + " already exists");
           return;
         }
     
         FSDataOutputStream out = fileSystem.create(path);
         InputStream in = new BufferedInputStream(new FileInputStream(new File(
             source)));
     
         byte[] b = new byte[1024];
         int numBytes = 0;
         while ((numBytes = in.read(b)) > 0) {
           out.write(b, 0, numBytes);
         }
         in.close();
         out.close();
         fileSystem.close();
       }
     
       public void readFile(String file, Configuration conf) throws IOException {
         FileSystem fileSystem = FileSystem.get(conf);
     
         Path path = new Path(file);
         if (!fileSystem.exists(path)) {
           System.out.println("File " + file + " does not exists");
           return;
         }
     
         FSDataInputStream in = fileSystem.open(path);
     
         String filename = file.substring(file.lastIndexOf('/') + 1,
             file.length());
     
         OutputStream out = new BufferedOutputStream(new FileOutputStream(
             new File(filename)));
     
         byte[] b = new byte[1024];
         int numBytes = 0;
         while ((numBytes = in.read(b)) > 0) {
           out.write(b, 0, numBytes);
         }
     
         in.close();
         out.close();
         fileSystem.close();
       }
     
       public void deleteFile(String file, Configuration conf) throws IOException {
         FileSystem fileSystem = FileSystem.get(conf);
     
         Path path = new Path(file);
         if (!fileSystem.exists(path)) {
           System.out.println("File " + file + " does not exists");
           return;
         }
     
         fileSystem.delete(new Path(file), true);
     
         fileSystem.close();
       }
     
       public void mkdir(String dir, Configuration conf) throws IOException {
         FileSystem fileSystem = FileSystem.get(conf);
     
         Path path = new Path(dir);
         if (fileSystem.exists(path)) {
           System.out.println("Dir " + dir + " already not exists");
           return;
         }
     
         fileSystem.mkdirs(path);
     
         fileSystem.close();
       }
     
       public static void main(String[] args) throws IOException {
     
         if (args.length < 1) {
           System.out.println("Usage: FileSystemOperations add/read/delete/mkdir"
               + " [<local_path> <hdfs_path>]");
           System.exit(1);
         }
     
         FileSystemOperations client = new FileSystemOperations();
     
         Configuration conf = new Configuration();
     	conf.set("fs.defaultFS", "hdfs://192.168.111.120:9000");
     
         if (args[0].equals("add")) {
           if (args.length < 3) {
             System.out.println("Usage: hdfsclient add <local_path> "
                 + "<hdfs_path>");
             System.exit(1);
           }
     
           client.addFile(args[1], args[2], conf);
     
         } else if (args[0].equals("read")) {
           if (args.length < 2) {
             System.out.println("Usage: hdfsclient read <hdfs_path>");
             System.exit(1);
           }
     
           client.readFile(args[1], conf);
     
         } else if (args[0].equals("delete")) {
           if (args.length < 2) {
             System.out.println("Usage: hdfsclient delete <hdfs_path>");
             System.exit(1);
           }
     
           client.deleteFile(args[1], conf);
     
         } else if (args[0].equals("mkdir")) {
           if (args.length < 2) {
             System.out.println("Usage: hdfsclient mkdir <hdfs_path>");
             System.exit(1);
           }
     
           client.mkdir(args[1], conf);
     
         } else {
           System.out.println("Usage: hdfsclient add/read/delete/mkdir"
               + " [<local_path> <hdfs_path>]");
           System.exit(1);
         }
     
         System.out.println("Done!");
       }
     }
     
     ```

     - 실행 방법 : 
       - read 실행 하려면 `RunConfiguration` 에서 `read /edudata/dessert-menu.csv` 해줘야 한다...여기서 최상위 폴더인 /edudata 를 패스의 첫번째로 준다
       - add arguments : `add c:/Temp/hadoopexam/dessert-order.csv /edudata` 



### 3. HADOOP 주요 API 

![image-20200501104105737](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200501104105737.png)





### 4. HDFS 시스템 이용하여 파일 읽기 및 쓰기

#### 폴더 내 여러 텍스트 파일 읽어서 합치고 작성하여 HDFS 에 저장

```java
package hdfsexam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class TomcatLogHDFS_Save {

  public static void main(String[] args) throws IOException {
	  //1. 읽어올 패스(=path) 및 작성할 패스(=writePath) 지정
	  String path= "C:\\Users\\student\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\logs\\";
	  String writePath = "c:/Temp/";
	  
      //2. File 의 패스들을 뽑아내는 과정
	  File dir = new File(path);
	  //System.out.println(dir);//패쓰 나옴 /logs
	  File[] fileList = dir.listFiles();
	  //System.out.println(fileList[1]);//[Ljava.io.File;@15db9742
      //[1]주면 첫번째 파일의 전체 패스가 나온다
	  
      //3. fileList 가 있는 만큼 반복문인데 file 에 하나씩 뽑는다
	  for(File file : fileList) {
		  if(file.isFile()) {
              //fileName 만 받아와서
			  String fileName = file.getName();
			  //System.out.println(fileName);
              
              ///각각 패스와 파일이름으로 파일을 읽어준다
			  try(FileReader reader = new FileReader(path+fileName);
                  //BufferedReader 는 한줄씩 읽을수 있게 해주는 기능
					  BufferedReader br = new BufferedReader(reader);
                  //FileWriter 까지 한꺼번에 try구문 안에 넣어준다, true = append
					  FileWriter writer = new FileWriter(writePath+"tomcat_access_log.txt", true);
                  BufferedWriter bw = new BufferedWriter(writer);
					  ){
                  //글 내용 넣어줄 String 객체 data 초기화
				  String data = null;
                  //data 에 한줄씩 읽어주는데 null 값이 아니면
				  while((data=br.readLine())!=null) {
                      //FileWriter 객체로 읽어준다
					  bw.write(data);
					  bw.newLine();
					  
				  
				
				}
                  //작성한 파일을 내보내주는 기능
				    bw.flush();
				  
			  }catch(IOException e) {
				  System.out.println(e);
			  }
		  }
	  }
	  System.out.println("Temp 폴더에 파일 작성 완료");
	  try {
			Configuration conf = new Configuration();
			conf.set("fs.defaultFS", "hdfs://192.168.111.120:9000");
			FileSystem hdfs = FileSystem.get(conf);
			String fname = "tomcat_access_log.txt";
			File f = new File("c:/Temp/" + fname);
			if (!f.exists()) {
				System.out.println("파일이 없음!!");
				return;
			}
			Path path2 = new Path("/edudata/" + fname);
			if (hdfs.exists(path2)) {
				hdfs.delete(path2, true);
			}
			long size = f.length();
			FileReader fr = new FileReader(f);
			char[] content = new char[(int) size];
			fr.read(content);
			FSDataOutputStream outStream = hdfs.create(path2);
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





### 5. Hadoop 과 Spring 연동

#### 1. 시스템 환경변수 설정

Spring MVC에서 Hadoop 연동하여 HDFS 나 MapReduce 기능을 사용하려면
hadoop 폴더를 c:\ 에 저장한 후에 

1. HADOOP_HOME  ---> c:\hadoop
2. PATH  ---> %HADOOP_HOME%\bin
   을 추가 설정한다.

확인 : cmd 창을 열고 echo %HADOOP_HOME%, echo %PATH%

#### 2. Eclipse 재기동

#### 3. Pom.xml 에 dependency 추가

#### 5. 자바 소스 각 위치에 넣기

#### 4. servlet-context.xml 에 추가

- 새로운 프리픽스이므로 `Namespaces`탭에 가서 해당 프리픽스를 설정해줘야한다

