### API-Integer

```java
package day12;
public class IntegerTest {
	public static void main(String[] args) {
		if(args.length != 2) {
			System.out.println("프로그램 아규먼트 2개를 숫자로 입력하세요");
		} else {
			System.out.println(Integer.parseInt(args[0])
					 +Integer.parseInt(args[1]));
		}
		String s1 = Integer.toBinaryString(12);
		String s2 = Integer.toBinaryString(88);
	    
		System.out.printf("%s\n", s1);
		System.out.printf("%s\n", s2);
		
		int num1 = Integer.parseInt(s1 ,2);
		int num2 = Integer.parseInt(s2, 2);
		
		System.out.printf("%d\n", num1);
		System.out.printf("%d\n", num2);
	}
}
```

##### * Integer.parseInt("문자열") ==> Integer 형태로 변환 시켜줌

##### * 만약 Integer.parseInt("2진수 문자열", 2) ==> 2진수 문자열을 10진수 숫자로 바꿔줌

##### * 이때 args[]에 입력값을 주기위해 Run configuration 에 들어가서 입력값 넣어줌

##### * Integer.toBinaryString(숫자) : 숫자를 2진수로 바꿔주는 기능

