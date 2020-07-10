### FileReader-파일 읽기 예제

```java
package day14.copy;

import java.io.*;
public class FileReaderTest {
    public static void main(String args[]) {
       FileReader reader = null;
       try {
           reader = new FileReader("c:/iotest/output1.txt");
           while (true) {
               int data = reader.read();
               if (data == -1)
                   break;
               char ch = (char) data;	//한문자씩 읽어서 출력
               System.out.print(ch);
           }
       } catch (FileNotFoundException fnfe) {
           System.out.println("파일이 존재하지 않습니다.");
       } catch (IOException ioe) {
           System.out.println("파일을 읽을 수 없습니다.");
       } finally {
          try {
        	  if(reader!=null)	//이걸 붙여줘야 Null exception 이 안발생함
               reader.close();
          } catch (Exception e) {
        	  e.printStackTrace();
          }
       }
    }
}

```

```java
package day14.copy;
import java.io.*;
public class FileReaderTest2 {
    public static void main(String args[]) {
       FileReader reader = null;
       BufferedReader br = null;
       try {
           reader = new FileReader("c:/iotest/output.txt");
           br = new BufferedReader(reader);	//한 행씩 읽도록 BufferReader 사용
           while (true) {
               String data = br.readLine();
               if (data == null)
                   break;               
               System.out.println(data);
           }
       } catch (FileNotFoundException fnfe) {
           System.out.println("파일이 존재하지 않습니다.");
       } catch (IOException ioe) {
           System.out.println("파일을 읽을 수 없습니다.");
       } finally {
          try {
        	   br.close();
               reader.close();
          } catch (Exception e) {
        	  e.printStackTrace();
          }
       }
    }
}

```

```java
package day14.copy;

import java.io.*;
public class FileReaderTest3 {
    public static void main(String args[]) {
       try (FileReader reader = new FileReader("c:/iotest/output.txt");){
    	   int data;
    	   System.out.println(reader.getEncoding());
           while (true) {
               data = reader.read();
               if (data == -1)
                   break;               
               System.out.print((char)data);
           }
       } catch (FileNotFoundException fnfe) {
           System.out.println("파일이 존재하지 않습니다.");
       } catch (IOException ioe) {
           System.out.println("파일을 읽을 수 없습니다.");
       } 
    }
}
```

```java
package day14.copy;
import java.io.*;
public class FileReaderTest4 {
    public static void main(String args[]) {
       try (FileReader reader = new FileReader("c:/iotest/output.txt");
    	   BufferedReader br = new BufferedReader(reader);){          
    	   String data;
           while (true) {
               data = br.readLine();
               if (data == null)
                   break;               
               System.out.println(data);
           }
       } catch (FileNotFoundException fnfe) {
           System.out.println("파일이 존재하지 않습니다.");
       } catch (IOException ioe) {
           System.out.println("파일을 읽을 수 없습니다.");
       } 
    }
}
```

