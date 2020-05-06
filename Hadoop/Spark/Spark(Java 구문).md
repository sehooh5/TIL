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
			SimpleDateFormat date = new SimpleDateFormat("오늘은 MM월 dd일입니다.");
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

