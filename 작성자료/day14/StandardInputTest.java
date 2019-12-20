package day14.copy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StandardInputTest {

	public static void main (String[] args) throws Exception{
		//1번 : 영어 문자 한개만 읽을 수 있는 System.in.read();
//		System.out.println("입력 : ");
//		int input = System.in.read();	//문자 한 개만 읽어감, 1byte stream, 한글 못읽음
//		System.out.println("출력 : "+(char)input);
		
		//2번 : 한글도 읽을 수 있는 InputStreamReader사용하여 System.in을 객체로 받아서 사용
//		System.out.println("입력 : ");
//		int input1 = new InputStreamReader(System.in).read();	//System.in은 InputStreamReader 객체로 사용
//		System.out.println("출력 : "+(char)input1);
		
		//3번 : 한 행을 읽을 수 있는 프린트
		System.out.println("입력 : ");
		String input1 = new BufferedReader(
				new InputStreamReader(System.in)).readLine();
		System.out.println("출력 : "+input1);
		
		
	}

}
