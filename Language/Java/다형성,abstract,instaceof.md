# 다형성

#### 1. 다형성 2. abstract 클래스 3. 인터페이스 4. 예외처리

---

#### 다형성

- 참조형 변수(클래스 타입)는 타입에 지정된 클래스 객체 뿐만 아니라

  타입에 지정된 클래스의 자손의 객체도 참조할 수 있다.

- 여러번 메서드를 만들어야 하는 번거로움을 해결해준다.

- 자손에서 부모로는 형변환 연산자 필요 없지만

  부모에서 자손으로는 형변환 연산자가 필요하다.

  ```java
  if(o instanceof String) {		
   System.out.println("문자열 객체 전달됨 : "+o.getClass().getName()
  +" - " + ((String)o).length());//o객체를 String 으로 형변환하여 사용
  ```

  

ex)

```java
A obj;

obj = new A();

obj = new B(); 

obj = new C();
```

*전부 입력이 가능하다 (ABC Test에서 가져올 수 있음)*

---



```java
package day10;

public class PolyTest {
	public void main (String[] args) {			
        //String 또한 java.lang 생략 가능
        ////모든 객체들은 Object 의 객체이기에 사용 가능하다!
		printObjectInfo(new Object());			
		printObjectInfo(new String("가나다"));
		printObjectInfo("ABC");
		printObjectInfo(new java.util.Date());
		printObjectInfo(new java.io.File("c:/Temp"));	
        //윈도우의 디렉토리 구분자는 \\ 두번 써줘야 다른기능이 작용 안한다 
        //(혹은 / 한번)
		printObjectInfo(new int[10]);
		printObjectInfo(new double[5]);
		printObjectInfo(new day7.Member());		
		printObjectInfo(new Integer(100));
	}
	static void printObjectInfo(Object o) {		
        //Object 는 java.lang 에 있는데 생략 가능하다
        //이 자리에는 Object 객체 뿐 아니라 자식들의 객체도 올 수 있다.
		System.out.println("전달된 객체의 클래스 명 : "+o.getClass().getName());
	}
}
```

Object 가 printObjectInfo의 객체이기 때문에

메인의 메서드 사용 시 모든 객체들을 사용할 수 있다.



Object obj =  new Date();		-가능		-하지만 Date의 자식 접근 불가

Date d = new Date();				-가능		-본인의 자식 접근 가능

Member m = new Date();		-불가능(부모 자식관계 아님)



#### 추상 클래스, 메서드(abstract)

- 추상메서드를 포함하고 있는 클래스

- 선언부만 있고 구현부가 없는 메서드

  ```java
  abstract class Player
      int currenPos;	//현재 play 되고 있는 위치를 저장하기 위한 변수
  
  	Player(){		//추상 클래스도 생성자가 잇어야한다.
          surrentPos = 0;
      }
  abstract 리턴타입 메서드이름();
  abstract void play(int pos);		//추상메서드
  abstract void stop ();				//추상메서드
  void stop{
      play(currentPos);				//추상메서드를 사용할 수 있다.
  }
  ```

- abstract 안에는 꼭 abstract 메서드가 있어야하는것은 아니다.

  하지만 abstract 메서드를 사용할 때는 **꼭 자손에서 오버라이딩** 하여 사용!

- 객체 생성 불가능



#### 리팩토링

- 중간에 부모를 하나 더 추가하는 작업

- 만약 부모의 자손중에 전부가 아닌 일부의 자손 객체만 사용 하고 싶을 때 

  리팩토링을 하여 일부 자손 객체들을 묶을 수 있다.

- 이 때, **abstract ** 클래스, 메서드를 사용한다.





#### Instance of

- 객체 + instanceof + 비교형식(string, int...)

- 객체가 비교형식과 같으면 참 아니면 거짓

- ```java
  if(o instanceof String) {				//전달된 객체 o가 String 이면 참 아니면 거짓
  			System.out.println("문자열 객체 전달됨 : "+o.getClass().getName());
  		}else {
  		System.out.println("전달된 객체의 클래스 명 : "+o.getClass().getName());
  		}
  ```

  

#### 인터페이스 

- 클래스, final 클래스, abstract 클래스

- **인터페이스**는 **모든 메서드가 abstract 메서드**인 클래스를 의미한다.

- 자바는 **단일 상속**을 지원하는 OOP언어이다.

  모든 메서드가 abstract인 클래스를 상속한 경우 다른 클래스는 상속 불가

  --------> 하지만, **인터페이스**는 **추가 상속**이 가능한 특별한 형태의 클래스

- 인터페이스는 abstract 클래스와 비슷한 자바 프로그램의 구조로서

  **객체 생성은 불가**하고 상속으로만 사용이 가능하다.

- 인터페이스 생성방법 :

  ```java
  interface 인터페이스이름 {
  		상수					//일반변수는 올수 없다
          abstract 메서드	   //다른 메서드는 올 수 없다
  }
  ```

- 인터페이스 사용 방법 : **상속**

  ```java
  interface 인터페이스 이름 extends 부모인터페이스이름 {
  		:
  }
  class 클래스명 extends 부모클래스명 implements 부모인터페이스이름{
  
  }
  ```

  **implements**를 사용하여 인터페이스 상속

- 인터페이스에도 **다형성**이 적용 되기때문에 인터페이스 클래스에서는 객체 생성이 불가하기에 인터페이스를 **상속받는 자식의 객체**를 사용해야 한다.