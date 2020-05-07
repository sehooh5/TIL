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



#### MyfunctionalInterface3 - MyfunctionaExam3

```java
package lamdaexam;

@FunctionalInterface
public interface MyFunctionalInterface3 {
	public int method3(int x, int y);
}

/////////////////////////
package lamdaexam;

public class MyFunctionalExam3 { 
	public static void main(String[] args) {
		MyFunctionalInterface3 fi;
		//public int method3(int x, int y);
		//오버라이딩 해야할 인터페이스를보고 매개변수를 확인해야함
		fi = (x, y) -> {
			int result = x + y;
			return result;
		};//수행문장이 두개이기 때문에 중괄호 사용
		System.out.println(fi.method3(2, 5));
		
		fi = (x, y) ->  {return x + y; };
		System.out.println(fi.method3(2, 5));
		
		fi = (x, y) -> x + y;
		System.out.println(fi.method3(2, 5));
		
		fi = (x, y) -> sum(x, y);
		System.out.println(fi.method3(2, 5));
	}
	
	public static int sum(int x, int y) {
		return x + y;
	}
}

```



#### MyfunctionalInterface5 - UsingLocalVariable - UsingLocalVariableExample(메인메서드 x inter)

- 메인메서드가 없는 인터페이스
- `annonymous inner local class` 를 확장한 `람다식`

```java
package lamdaexam;

public interface MyFunctionalInterface5 {
    public void method5();
}
//////////////////////////////////////
package lamdaexam;

public class UsingLocalVariable {
	void test_method(int arg) {  
		int localVar = 40; 	
		
		//arg = 31;  		
		//localVar = 41; 
		
		System.out.println(arg + ":" + localVar);
        
		//람다식..annonymous inner local class 확장함
		MyFunctionalInterface5 fi= () -> {
           //람다식에서 사용되는 매개변수들은 자동으로 final
            //람다식을 사용하는 메서드의 매개변수
			System.out.println("arg: " + arg); 
            //로컬변수 사용
			System.out.println("localVar: " + localVar + "\n");
		};
		fi.method5();
	}
}

////////////////////////////////////
package lamdaexam;

public class UsingLocalVariableExample {
	public static void main(String... args) {
		UsingLocalVariable ulv = new UsingLocalVariable();
		ulv.test_method(20);
	}
}

```



#### MyfunctionalInterface4 - UsingThis - UsingThisExample(메인메서드 x inter)

- `annonymous inner local class` 를 확장한 `람다식`

- `annonymous inner local class` 는 엄연한 클래스

  `람다식` 은 클래스가 아닌 함수..

- 따라서, `람다식`에 사용된 `this` 는 `람다식`을 갖고있는 클래스의 것

```java
package lamdaexam;

public interface MyFunctionalInterface4 {
    public void method4();
}
//////////////////////////////////////////
package lamdaexam;

public class UsingThis {
	public int outterField = 10;

	class Inner {
		int innerField = 20;

		void test_method() {
			//람다식
			MyFunctionalInterface4 fi= () -> {
				System.out.println("outterField: " + outterField);
				System.out.println("outterField: " + UsingThis.this.outterField + "\n");
                //Outter class 에접근하려면 O.this
				
				System.out.println("innerField: " + innerField);
				System.out.println("innerField: " + this.innerField + "\n");//this = Inner class
			};
			fi.method4();
		}
	}
}
///////////////////////////////////////////
package lamdaexam;

public class UsingThisExample {
	public static void main(String... args) {
		UsingThis usingThis = new UsingThis();
		UsingThis.Inner inner = usingThis.new Inner();
		inner.test_method();
	}
}


```



#### LamdaThreadLab

```java
package lamdaexam;

import java.text.SimpleDateFormat;
import java.util.Date;

class LamdaThreadLab {

	public static void main(String[] args) {

		Thread t1 = new Thread(()->{
				for (int i = 0; i < 3; i++) {
					Date today = new Date();
					SimpleDateFormat date = new SimpleDateFormat("오늘은 MM월 dd일입니다.");
					System.out.println(date.format(today));
					try {
						Thread.sleep(10000);
					} catch (Exception e) {
					}
				}
			});
		Thread t2 = new Thread(()->{
				for (int i = 0; i < 5; i++) {
					Date today = new Date();
					SimpleDateFormat date = new SimpleDateFormat("hh시 mm분 ss초");
					System.out.println(date.format(today));
					try {
						Thread.sleep(5000);
					} catch (Exception e) {
					}
				}
			});

		t1.start();
		t2.start();

		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("number of milliseconds since January 1, 1970, 00:00:00 GMT");
		}

	}

}


```
