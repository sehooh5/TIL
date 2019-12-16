# Standard Infut - Scanner

##### 표준 입력 이란?

프로그램이 수행하는 동안 필요로 하는 데이터를

시스템의 <u>표준 입력 장치(키보드)</u>로부터 받아오는 것



##### Java 에서 표준입력 사용법?

- `System.in` 사용하여 입력한다
- java.util.Scanner 클래스 제공하여 좀 더 편하게 데이터 입력받게 API 추가됨

```java
System.in.read()					   //한글이 깨져서 나온다

import java.util.Scanner;
Sanner scan = new Scanner(System.in)   //system.in 즉 키보드에서 받아옴
    scan.next()
    scan.nextLine()
    scan.nextInt()
    scan.nextDouble()
    .
    .
    .
```

---

```java
package day8;
import java.util.Scanner;						// Scanner 불러오기
public class ScannerLab1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);	// 입력값에 표준입력(키보드)
	
		int i3=0;								// 숫자 계산 값 담을 i3 초기화
		System.out.println("첫 번째 숫자를 입력하세요 : ");
		int i1 = sc.nextInt();					// Scanner에서 숫자 입력하는 메서드 호출
		System.out.println("두 번째 숫자를 입력하세요 : ");
		int i2 = sc.nextInt();
		System.out.println("연산자(+,-,*,/)를 입력하세요 : ");
		String st = sc.next();					// Scanner에서 문자열 입력하는 메서드 호출
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
		return;				///중요! 없으면 밑에도 출력함! main method 이기때문에사용 가능
		}
		System.out.printf("%d 와 %d의 %s 연산 결과는 %d 입니다.",i1,i2,st,i3);
		sc.close();
		
	}

}
```



---



==

숫자나 문자는 등가 연산자로 값이 동일한지 비교 가능하지만

문자열은 등가 연산자로 비교할 수 있는 경우도 있지만일반적으로 API를 사용해야 한다.



equals() 메서드를 사용해야 한다.

String 클래스가 제공



자바의 문자열 리터럴은 String 객체로 취급 된다.

ex) 'y' 와 "y" 는 완전히 다르다

​		- 'y' : char 타입, 기본형

​		-"y" : String 타입, 객체형(참조형)

​			ex) "y".equals("...")



```java
package day10;

import java.util.Scanner;

public class ScannerLab3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int i = 0;
		int i1;
		int i2;
		int i3 = 0;
		boolean flag = false;
		// 숫자와 연산자를 입력받아 처리하는 기능을 3번 반복하도록 코드 작성
		while (i < 3) {
			System.out.println("첫 번째 숫자를 입력하세요 : ");
			i1 = sc.nextInt();
			System.out.println("두 번째 숫자를 입력하세요 : ");
			i2 = sc.nextInt();
			System.out.println("연산자(+,-,*,/)를 입력하세요 : ");
			String st = sc.next();
			switch (st) {
			case "+":
				i3 = i1 + i2;
				break;
			case "-":
				i3 = i1 - i2;
				break;
			case "/":
				i3 = i1 / i2;
				break;
			case "*":
				i3 = i1 * i2;
				break;
			default:
				flag =true;						//d이거 다시 생각해보기 사용법
			}
			if (flag == true) {
				System.out.println("+,-,*,/ 를 입력하숑");
			} else
				System.out.printf("%d 와 %d의 %s 연산 결과는 %d 입니다.", i1, i2, st, i3);
			System.out.println();
			//System.out.println("계속 수행하려면 1, 종료하려면 다를 숫자를 눌러주세요 : ");
			System.out.println("계속 수행하려면 y, 종료하려면 다를 숫자를 눌러주세요 : ");

			//go = sc.nextInt();
			if(!sc.next().equals("y"))		// !은 ("y")와 같지 않을 경우로 반전시키는 방법
			//if(sc.nextInt() != 1)
				break;
		}
		sc.close();

	}
}
```



