# Thread - Inner - Lambda 실습 비교

### Thread 상속

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



### Thread 인터페이스

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



### AnnonymousInerLocalClass 사용

```java
package innerexam;

import java.text.SimpleDateFormat;
import java.util.Date;

class AnonyThreadLab {

	public static void main(String[] args) {

		Thread t1 = new Thread(new Runnable() {

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
		});
		Thread t2 = new Thread(new Runnable() {

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



### Lambda 사용

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



### Annony - Lambda 비교

- `Runnable AnnonyClass` 호출 필요 없고 매개변수도 없다=`()`
- `Runnable` 의 메서드 `run` 매개변수가 없어 생략한다

```java
new Thread(new Runnable() {

    public void run() {

        System.out.println("전통적인 방식의 일회용 스레드 생성");

    }

}).start();

 

new Thread(()->{

    System.out.println("람다 표현식을 사용한 일회용 스레드 생성");

}).start();
```

