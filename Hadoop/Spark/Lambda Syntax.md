# Lambda Syntax

## Interface in Java 8

- 추상 메서드 외에도 **디폴트 메서드**와 **스태틱 메서드** 정의 가능

  ```java
  interface 인터페이스명{
  	//상수
      타입 상수명 = 값; 
      //추상 메서드
      타입 메서드명(매개변수,...);
      //디폴트 메서드
      default 타입 메서드명(매개변수,..){
          ..................
      }
      //정적
      static 타입 메서드명(매개변수,..){
          ..................
      }  
  }
  ```

#### 디폴트 메서드 사용

- 인터페이스로 직접 사용 불가
- 모든 구현 객체가 가지고 이는 기본 메서드로 사용
  - 필요에 따라 구현 클래스가 디폴트 메서드를 재정의함

#### 정적 메서드 사용

- 인터페이스로 바로 호출 가능



## Lambda

- 람다식 : **식별자 없이 실행가능한 함수**
- 메서드를 함수(식)으로 표현 : 코드가 매우 간결해진다
- 유지보수성이 좋아진다
- **오버라이딩 할 메서드가 한 개 인 interface때만 사용가능**
  - `functional interface` : 오버라이딩 할 **추상메서드** 한개
- <u>마지막으로 실행된 결과값</u>이 **return 값**



### 기본 식

- No arguments : () -> 식

- One argument : s -> 식(s)

- Two arguments : (x,y) -> x+y

- with explicit argument types : (Integer x, Integer ) -> x+y

- Multiple statements : (x,y) -> {

  ​											System.print.out(x);

  ​											System.print.out(y);

  ​											return (x+y);

  ​											}



### 특징

- 매개변수의 타입은 실행시에 대입 값 따라 자동 결정 생략 가능
- 하나의 매개변수만 있을 경우에는 괄호( ) 생략 가능
- 하나의 실행문만 있다면 중괄호 { } 생략 가능
- 매개변수 없다면 괄호 ( ) 생략 불가
- 리턴값이 있는 경우, return 문 사용
- 중괄호 { }에 return 문만 있을 경우, 중괄호 생략 가능



### 타켓 타입 과 함수적 인터페이스

#### 타겟 타입

- 람다식이 대입되는 인터페이스
- 익명 구현 객체를 만들 때 사용할 인터페이스
- 인터페이스 변수 = 람다식;

#### 함수적 인터페이스

- 하나의 추상 메서드만 선언된 인터페이스
- **@FunctionalInterface** 어노테이션 정의
- 하나의 추상메서드만을 가지는 컴파일러가 체크하여 두 개 이상의 추상메서드가 선언되어 있으면 컴파일 오류 발생



## lamdaexam

#### MyFunctionalInterface1 - MyfunctionaExam1

```java
package lamdaexam;

@FunctionalInterface
public interface MyFunctionalInterface1 {
	public static void pr() {
		System.out.println("ㅋㅋ");
	}
    public void method1();
    //public void method2();  abstract 메서드 두개는 불가하다
}

///////////////////////////////////////////

package lamdaexam;

public class MyFunctionalExam1 { 
	public static void main(String[] args) {
		MyFunctionalInterface1 fi;
		
		MyFunctionalInterface1.pr();
		//두가지 방법 사용
		//람다식
		fi= () -> { 
			String str = "method call1";
			System.out.println(str);
		};
		fi.method1();
		//annonymous inner class 사용
		fi = new MyFunctionalInterface1 () {
			public void method1() {//부모의 abstract 메서드 모두 오버라이딩 해야한다
				System.out.println("method call2"); 
			}
		};		
		fi.method1();		
	}
}


```

#### MyFunctionalInterface2 - MyfunctionaExam2

```java
package lamdaexam;

@FunctionalInterface
public interface MyFunctionalInterface2 {
    public void method2(int x);
}

///////////////////////////////////////

package lamdaexam;

public class MyFunctionalExam2 { 
	public static void main(String[] args) {
		MyFunctionalInterface2 fi;
		
		fi= (x) -> {
			int result = x * 5;
			System.out.println(result);
		};
		fi.method2(2);
		
		fi = x -> { System.out.println(x*5); };
		fi.method2(2);
		
		fi = x -> System.out.println(x*5);
		fi.method2(2);
	}
}

```



#### MyfunctionaExam1

```java

```



#### MyfunctionaExam1

```java

```



#### MyfunctionaExam1

```java

```



#### MyfunctionaExam1

```java

```



#### MyfunctionaExam1

```java

```



#### MyfunctionaExam1

```java

```



#### MyfunctionaExam1

```java

```



#### MyfunctionaExam1

```java

```



#### MyfunctionaExam1

```java

```



#### MyfunctionaExam1

```java

```



