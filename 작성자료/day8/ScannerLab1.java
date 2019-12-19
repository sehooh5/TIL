package day8;
import java.util.Scanner;
public class ScannerLab1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		int i3=0;
		System.out.println("첫 번째 숫자를 입력하세요 : ");
		int i1 = sc.nextInt();
		System.out.println("두 번째 숫자를 입력하세요 : ");
		int i2 = sc.nextInt();
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
		System.out.println("+,-,*,/ 를 입력하숑");
		sc.close();
		return;							///중요! 없으면 밑에도 출력함! main method 이기때문에사용 가능
		}
		System.out.printf("%d 와 %d의 %s 연산 결과는 %d 입니다.",i1,i2,st,i3);
		sc.close();
		
	}

}
