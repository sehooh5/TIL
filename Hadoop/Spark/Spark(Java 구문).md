# Spark(Java 구문)

- Java의 정석 12장 ppt 참고
- 병렬처리를 잘 설계한 프로그램



## Thread

- 프로세스 : 프로그램 실행, 최소 1개의 쓰레드(공장)

- **쓰레드** : 프로그램 실행의 흐름(일꾼)

- 명령어 : 

  ```
  ps 
  ps -ef
  ps -ef|more	// `|` 뒤에는 또다른 명령
  ps -ef>process.txt	// `>` 뒤에는 파일명
  ps -ef|grep init	// init 이 들어간 행만 찾아라
  grep unico *.java	// 찾고자하는 패턴 찾아줌
  ```

- 멀티 프로세스 vs 멀티 쓰레드 : 쓰레드가 비용이 더 적다





### Thread 구현과 실행

1. Thread 클래스 상속

   ```java
   class MyThread extends Thread{
   	public void run(){
           ~~~~~~~~~~~~~~~~~~
       }
   }
   ```

   - `start()` 바로 호출 가능

2. Runnable 인터페이스 구현

   ```java
   class MyThread implements Runnable{
   	public void run(){
           ~~~~~~~~~~~~~~~~~~
       }
   }
   ```

   - `start()` 바로 호출 불가능

3. 싱글 vs 멀티 쓰레드

   ![image-20200506102738552](C:\Users\student\Documents\GitHub\TIL\Hadoop\Spark\image-20200506102738552.png)

4. round robin & priority

   :  CPU 사용 권한을 부여하는 스케쥴링 알고리즘

   :  여러 쓰레드 동시실행이 불가하므로 시간을 나눠 각각 실행



### threadexam

#### ThreadEx06 (싱글 쓰레드)

```java
package threadexam;
import javax.swing.JOptionPane;

class ThreadEx06 {
	public static void main(String[] args) throws Exception	{
		String input = 
			JOptionPane.showInputDialog("아무 값이나 입력하세요."); 
		System.out.println("입력하신 값은 " + input + "입니다.");

		for(int i=10; i > 0; i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch(Exception e ) { }
		}
	}
}

```



#### ThreadEx07 (멀티 쓰레드)

- 두 쓰레드가 동시 실행됨

```java
package threadexam;
import javax.swing.JOptionPane;

class ThreadEx07 {//부모쓰레드
	public static void main(String[] args) throws Exception 	{
		ThreadEx7_1 th1 = new ThreadEx7_1();
		th1.start();
		String input = JOptionPane.showInputDialog("아무 값이나 입력하세요."); 
		System.out.println("입력하신 값은 " + input + "입니다.");
	}
}
class ThreadEx7_1 extends Thread {//자식쓰레드
	public void run() {
		for(int i=10; i > 0; i--) {
			System.out.println(i);
			try {
				sleep(1000);
			} catch(Exception e ) {}
		}
	}
}

```



#### ThreadEx09(Runnable,Daemon)

- 자동 종료
- `Daemon`화 하지 않으면 메인은 멈추지만 자식은 계속된다
- `Thread` 의 매개변수는 `Runnable` 객체

```java
package threadexam;
 class ThreadEx09 implements Runnable  {
	static boolean autoSave = false;

	public static void main(String[] args) {
		Thread t = new Thread(new ThreadEx09());
		t.setDaemon(true);// 이 부분이 없으면 종료되지 않는다. Daemon 으로 설정해서 서포트해주는역할
		t.start();

        //부모쓰레드
		for(int i=1; i <= 20; i++) {			
			try{
				Thread.sleep(1500);
			} catch(InterruptedException e) {}
			System.out.println(i);
			
			if(i==5)
				autoSave = true;
		}

		System.out.println("프로그램을 종료합니다.");
	}

	public void run() {
		while(true) {
			try { 
				Thread.sleep(3 * 1000);	// 3초마다
			} catch(InterruptedException e) {}	

			// autoSave의 값이 true이면 autoSave()를 호출한다.
			if(autoSave) {
				autoSave();
			}
		}
	}

	public void autoSave() {
		System.out.println("작업파일이 자동저장되었습니다.");
	}
}

```





#### ThreadLab1(Thread 상속)

```java
package threadexam;

import java.text.SimpleDateFormat;
import java.util.Date;

class ThreadLab1 {
	public static void main(String[] args) throws Exception {
		ThreadLab1_1 th1 = new ThreadLab1_1();
		ThreadLab1_2 th2 = new ThreadLab1_2();
		th1.start();
		th2.start();
		for (int i = 0; i < 10; i++) {
			Thread.sleep(3000);
			System.out.println("number of milliseconds since January 1, 1970, 00:00:00 GMT");
		}

	}
}

class ThreadLab1_1 extends Thread {

	public void run() {
		for (int i = 0; i < 3; i++) {
			Date today = new Date();
			SimpleDateFormat date = new 		SimpleDateFormat("오늘은 MM월 dd일입니다.");
			System.out.println(date.format(today));
			try {
				sleep(10000);
			} catch (Exception e) {
			}
		}
	}
}

class ThreadLab1_2 extends Thread {

	public void run() {
		for (int i = 0; i < 5; i++) {
			Date today = new Date();
			SimpleDateFormat date = new SimpleDateFormat("hh시 mm분 ss초");
			System.out.println(date.format(today));
			try {
				sleep(5000);
			} catch (Exception e) {
			}
		}
	}
}

```





#### ThreadLab2(Runnable 인터페이스)

```java
package threadexam;

import java.text.SimpleDateFormat;
import java.util.Date;

class ThreadLab2 {
	public static void main(String[] args) {

		Thread t1 = new Thread(new ThreadLab2_1());// 매개변수에 Runnable 객체가 필요
		Thread t2 = new Thread(new ThreadLab2_2());// 그래서 new 해줘야함

		t1.setDaemon(true);
		t1.start();
		t2.setDaemon(true);
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

class ThreadLab2_1 implements Runnable {

	public void run() {
		for (int i = 0; i < 3; i++) {
			Date today = new Date();
			SimpleDateFormat date = new SimpleDateFormat("오늘은 MM월 dd일입니다.");
			System.out.println(date.format(today));
			try {
				Thread.sleep(10000);
			} catch (Exception e) {
			}
		}
	}
}

class ThreadLab2_2 implements Runnable {

	public void run() {
		for (int i = 0; i < 5; i++) {
			Date today = new Date();
			SimpleDateFormat date = new SimpleDateFormat("hh시 mm분 ss초");
			System.out.println(date.format(today));
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
			}
		}
	}
}

```



---



## Inner Class

- 좀 더 심플하게 코드를 짤 수있다

- 멤버 변수와 지역 변수의 개념과 비슷하다. 

  즉, **멤버 클래스**와 **지역 클래스**로 생각하면 된다

- `static inner member` or `non-static inner member`

- `annonymous local class`한번만 사용할 때 사용(가장많이씀)

  `local class` 여러번 사용할 때 사용

- `Annonymous local class` : 

  ```java
  //AC가 abstract일 때
  AC obj; 
  //obj = new AC(); ---> 오류
  obj = new AC(){
  	멤버들..............	
  }; //얘네가 Annonymous Class
  
  
  //원래는 상속받아서 사용했다
  AC obj;
  obj = new My();
  class My extends AC{
      멤버들....
  }
  
  ```

### Inner Class 의 종류

#### Member Class

##### 1. Instance Class

- `OuterName$InnerName.class` 명의 `.class` 파일 만들어짐
- `OuterClass`의 인스턴스 멤버처럼 다루어짐
- `Instance Class`에서는 static 멤버를 정의할 수 없다

##### 2. Static Class

- `OuterName$InnerName.class` 명의 `.class` 파일 만들어짐

- `OuterClass`의 static 멤버처럼 다루어짐
- `OuterClass`의 클래스 메서드내에서 사용될 목적으로 정의

##### 3. 접근방법 (동일, 외부 클래스 밖에서 접근 가능)

```java
class A{
	class B{
        멤버..
    }
    static class C{
        멤버
    }
}
```

- `Instance Class` : 변수 정의 후 접근 가능
  - A a = new A();
  - A.B b =a.new B();
  - b.멤버
- `Static Class` : 바로 접근 가능
  - A.C.멤버



#### Local Class

- **메서드 내**에 정의되는 클래스

- 활용 범위 : 정의되어 있는 메서드 블럭 내부로 제한

- Interface 는 로컬로 정의될 수 없다

- 포함하는 **클래스**의 **멤버 변수**와

  포함하는 **메서드**의 **final 로컬변수**, **final 매개변수** 사용 가능

##### 1. 이름있는 Local Class

- `X$1$Y.class` 명의 클래스 파일이 만들어진다

- Y 클래스 내에서는 X 클래스의 멤버 변수 num,sam() 메서드의 final 지역변수 s를 사용할 수 있다

- Y 클래스는 sam() 메서드 내에서만 사용 가능하다

- ```java
  class X{
      int num;
      void sam(final int i){
          int total = 20;
          final String s = "text";
          class Y{
              멤버들
          }
          Y y = new Y();
          y.멤버들...;
      }
  }
  ```



##### 2. Annonymous Local Class

- `N$1.class` 명의 클래스 파일이 만들어 진다

- 정의된 위치에서 한번만 객체 생성이 가능하다

- **클래스 정의와 객체생성을 동시**에 하는 1회용

- <u>new 키워드 뒤의 생성자 메서드의 명칭</u>이 ***기존 클래스 명***인 경우에는 자동적으로 ***이 클래스의 자손클래스***가 되고,,,,***인터페이스 명***인 경우 이 인터페이스를 구현하여 추가 ***상속하는 자손클래스***로서 부모 클래스는 Object 가 된다

- ```java
  class N{
      void pr(Test t){
          ...ㅊ
      }
      void sam(){
          pr(new Test(){
              멤버들...
          });
          ...
      }
  }
  ```

  

### Innerexam

#### InnerTest1

- 총 네개의 클래스 생성
  - Outer, Outer$Inner, Outer$Static_Inner, InnerTest

```java
package innerexam;
class Outer  {
   void pr() {
      System.out.println("Outer's pr() Method !!");     
   }
   class Inner {
      void pr() {
         System.out.println("Inner's pr() Method !!");
      }
   }
   static class Static_Inner {
	   static void pr() {
         System.out.println("Static_Inner's pr() Method !!");
      }
   }
}
public class InnerTest1 {
   public static void main(String args[]) {
      Outer.Static_Inner.pr();
      Outer o = new Outer();
      o.pr();
      Outer.Inner i = o.new Inner();
      i.pr();
   }
}

```



#### InnerTest2

```java
package innerexam;
class LocalTest {
   String name = "Java";//멤버에게는 final 제약이 없다
   void pr(String s) {//메서드 안에 변수에 대해서 final 적용
      int su = 100; //inner local class에서 사용되므로 자동으로 final su 로 변환됨
      System.out.println(s + " : " + su);
      class Local {
         void pr(String ls) {
            System.out.println("s : " + s);   	// Main Call
            System.out.println("ls : " + ls);   	// Local Call      
            System.out.println(name);        	// Java
            System.out.println(su);				// 100
            //su++; 오류 : su final 이므로 변화 불가능
         }
      }          
      //su++; 오류 : su final 이므로 증가는 가능하지만 Local 안에서 사용 불가능
      Local lt = new Local();
      lt.pr("Local Call");
   }
}   
public class InnerTest2 {
   public static void main(String args[]) {
      LocalTest l = new LocalTest();
      l.pr("Main Call");
   }
}

```



#### InnerTest3

```java
package innerexam;

//abstract클래스
abstract class Test {
	// 생성자 두개
	Test() {
		System.out.println("No Argument sample");
	}

	Test(String msg) {
		System.out.println("Argument sample : " + msg);
	}
	//abstract메서드
	abstract void output(String s);
}

public class InnerTest3 {
	void pr(Test o) {
		o.output("Test");
	}

	public static void main(String args[]) {
		System.out.println("Main start !!");
		InnerTest3 n = new InnerTest3();//객체생성후 pr 호출이 가능하다
		n.pr(new Test("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ루ㅋㅋ") {//매개변수 있든 없든 Test의 자식이 된다,,이름없는 클래스,,Test가 부모 클래스,, 한번만 사용가능
			int su = 100;

			public void output(String s) {
				System.out.println("Anonymous Class : " + s);
				System.out.println("Anonymous Class : " + su);
			}
		});
	}
}

```



#### InnerTest4

```java

```

