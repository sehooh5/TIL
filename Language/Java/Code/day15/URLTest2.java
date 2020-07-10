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
