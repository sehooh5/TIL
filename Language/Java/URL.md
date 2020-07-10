# URL

```java
package day15;
//웹크롤링 : 웹상 존재하는 콘텐츠를 수집하는 작업
//웹스크래핑 : 필요한 자료를 parse 하여 뽑아내는 작업
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class URLTest1 {
	public static void main(String[] args) throws Exception {
		URL url = new URL("https://movie.naver.com/"); //생성자를 통해 생성
		InputStream is = url.openStream();//openStream으로 읽어올 수 있다
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		//한줄씩 읽으며 출력,,,여기서 UTF-8로 바꿔줘야 안꺠진다.
		String line = null;
		while (true) {
			line = br.readLine();
			if (line == null)
				break;
			System.out.println(line);
		}
	}
}
```



```java
package day15;

import java.net.*;
import java.io.*;
public class URLTest2 {
	public static void main(String[] args) {
		try {//트라이 캐치 구문 사용함
			URL req = new URL("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1168064000");
			InputStream is = req.openStream();
			BufferedReader reader = new BufferedReader(
					                       new InputStreamReader(is, "UTF-8"));
			String lineStr = "";
			while(true) {
				lineStr = reader.readLine();
				if(lineStr == null)
					break;
				System.out.println(lineStr);				
			}			
		} catch (MalformedURLException e) {
			System.out.println("URL문자열 오류 : "+e.getMessage());//:빼먹으면 발생
		} catch (IOException e) {
			System.out.println("IO 오류 : "+e.getMessage());//연결 안될때 오류
		}
	}
}
```



```java
package day15;

import java.net.*;
import java.io.*;
public class URLTest3 {
	public static void main(String[] args) {
		try {//트라이캐치
			URL req = new URL("http://img.etnews.com/news_ebuzz/afieldfile/2012/01/04/c_bk010101_87984_3.jpg");
			InputStream is = req.openStream();//바이트 스트림 
			FileOutputStream fos = new FileOutputStream("c:/iotest/duke.jpg");//파일로 저장
			int input=0;
			while(true) {
				input = is.read();
				if(input == -1)
					break;
				fos.write(input);				
			}
			fos.close();
			System.out.println("duke.jpg가 성공적으로 생성되었습니다.");
		} catch (MalformedURLException e) {
			System.out.println("URL문자열 오류 : "+e.getMessage());
		} catch (IOException e) {
			System.out.println("IO 오류 : "+e.getMessage());
		} 
	}
}
```



```java
package day15;

import java.net.*;
import java.io.*;
public class URLTest4 {
	public static void main(String[] args) {
		InputStream is = null;
		BufferedReader reader = null;
		BufferedWriter fw = null;
		try {
			URL req = new URL("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1168064000");
			is = req.openStream();
			reader = new BufferedReader(
					                       new InputStreamReader(is, "utf-8"));
			fw = new BufferedWriter(new OutputStreamWriter(
					     new FileOutputStream("c:/iotest/weather.xml"), "utf-8"));//UTF-8 설정하기 위해 Writer 사용
			String lineStr = "";
			while(true) {
				lineStr = reader.readLine();
				if(lineStr == null)
					break;
				fw.write(lineStr+"\r\n");				
			}	
			System.out.println("weather.xml이 성공적으로 생성되었습니다.");
		} catch (MalformedURLException e) {
			System.out.println("URL문자열 오류 : "+e.getMessage());
		} catch (IOException e) {
			System.out.println("IO 오류 : "+e.getMessage());
		}  finally {
			try {
				if (fw != null) 
					fw.close();
				if (reader != null) 
					reader.close();
				if (is != null) 
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}
}
```

