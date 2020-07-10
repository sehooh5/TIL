package day10;
import java.util.Scanner;
public class ScannerLab2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int i=0;
		int i1 ;
		int i2 ;
		int i3=0;
		boolean flag = false;
		//숫자와 연산자를 입력받아 처리하는 기능을 3번 반복하도록 코드 작성
		for(i=0;i<3;i++) {
		System.out.println("첫 번째 숫자를 입력하세요 : ");
		i1 = sc.nextInt();
		System.out.println("두 번째 숫자를 입력하세요 : ");
		i2 = sc.nextInt();
		System.out.println("연산자(+,-,*,/)를 입력하세요 : ");
		String st = sc.next();
		switch(st) {
		case "+" : i3 = i1+i2;
		break;
		case "-" : i3 = i1-i2;
		break;
		case "/" : i3 = i1/i2;
		break;
		case "*" : i3 = i1*i2;
		break;
		default : 
			flag =true;
		}
		if(flag == true) {
		System.out.println("+,-,*,/ 를 입력하숑");
		}else
		System.out.printf("%d 와 %d의 %s 연산 결과는 %d 입니다.",i1,i2,st,i3);
		
	}sc.close();
	
}
}
