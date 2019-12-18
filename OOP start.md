# OOP start



#### 객체지향개념 (OOP)

- 객체는 속성+기능

  속성은 변수로, 기능은 메서드로 정의한다.

- 생성방법

  ```java
  클래스명 참조변수명;						//클래스명 첫글자는 대문자
  참조변수명 = new 클래스명();
  
  ex) Tv t;
  	t = new Tv();
  	t.channel = 7;
  	t.channelDown();
  	System.out.pringln(t.channel);
  ```



#### <클래스 및 객체 생성하는 방법>

```java
package day7;	

class Student{			 //Student 클래스 생성(여기서 클래스 명 시작은 대문자)
	String name;					//1속성	 3개의 속성(변수)
	int age;						//2속성
	String subject;					//3속성	
	void printStudentInfo() {		//1기능	 2개의 기능(메서드)
		System.out.println(name+"학생은 나이가 "+age+" 입니다.");
	}
	void study() {					//2기능	 
		System.out.println(name+"학생은 "+subject+" 과목을 학습합니다.");
	}
}
public class StudentTest {										//public classs는 꼭 한개!

	public static void main(String[] args) {
		Student st1 = new Student();	//객체 생성 식
		System.out.println(st1);		//값:day7.Student@15db9742
		System.out.println(st1.name);	//기본값 Null
		System.out.println(st1.age);	//기본값 0
		System.out.println(st1.subject);//기본값 Null
		st1.printStudentInfo();			//Null 은 나이가 0 입니다.
		st1.study();
		st1.name="듀크";				 //여기서 입력해주면
		st1.age=24;
		st1.subject="HTML5";
		st1.printStudentInfo();			//듀크는 나이가 24 입니다.
		st1.study();
		
		Student st2 = new Student();		
		System.out.println(st2);			
		System.out.println(st2.name);
		System.out.println(st2.age);
		System.out.println(st2.subject);
		st2.printStudentInfo();
		st2.study();
		st2.name="턱시";
		st2.age=30;
		st2.subject="CSS3";
		st2.printStudentInfo();
		st2.study();
		System.out.println("st2=st1 수행");
		st2=st1;
		System.out.println(st1);
		System.out.println(st2);
		st1.printStudentInfo();
		st1.study();
		st2.printStudentInfo();
		st2.study();
		}

}
```



---





#### 생성자 Method : constructor

- class 를 객체 생성할 때

  ```
  new 클래스명()	----> 생성자 매서드
  ```

- **모든 클래스는 1개 이상의 생성자 메서드를 가지고 있어야 한다.**

- 클래스 소스에 생성자 메서드가 정의되어 있지 않으면 컴파일러가 생성자를 만들어준다.

  ===> **default 생성자**

- 생성자 메서드 정의 방법

  1. 메서드명은 클래스명과 동일해야 한다.

  2. 매개변수는 선택적이다. (오버로딩 가능하다)

  3. 리턴값의 이용은 생략한다. 

  4. 객체 생성시 수행해야하는 기능 또는,

     **객체 생성시 데이터를 전달 받아 멤버변수들의 값을 "초기화**" 하는 기능(다수데이터 입력)

  5. 생성자 메서드의 아규먼트 형식으로 데이터를 전달해야한다. 

     ```
     	StudentNew(String p1,int p2, String p3){	}
     	.
     	.
     	.
     	public static void main......
     	StudentNew st1 = new StudentNew("듀크",24,"자바");	
     ```

##### this

`this` : 자신의 객체의 참조값을 의미하는 리터럴

`this.*** ` : 나의 멤버 ***를 의미  ex) `this.title = title`

`this()` : **생성자 메서드 내에서만** 호출 가능

​				 생성자 메서드의 **첫 행에서만** 호출 가능

​				 다른 생성자를 호출하는 결과

#### <생성자 메서드>

```java
package day7;	

class StudentNew{										//StudentNew 라는 class생성
	String name;										//속성 값
	int age;
	String subject;
	StudentNew(String p1,int p2, String p3){			//생성자 메서드 정의 (리턴값 없다)
		name = p1;										//꼭! class 명과 같은 이름
		age = p2;
		subject = p3;
	}
	void printStudentInfo() {							//리턴값 없는 메서드 정의		
		System.out.println(name+"학생은 나이가 "+age+" 입니다.");
	}
	void study() {												
		System.out.println(name+"학생은 "+subject+" 과목을 학습합니다.");
	}
	void study(int hour) {												
		System.out.println(name+"학생은 "+subject+" 과목을 "+hour+" 동안 학습합니다.");
	}
}
public class StudentTest2 {						

	public static void main(String[] args) {
		StudentNew st1 = new StudentNew("듀크",24,"자바");	//객체 생성 및 값 입력
		System.out.println(st1);						  //참조값 출력
		st1.study(2);			 //int 입력값이 있기때문에 두번째 메서드 출력
		StudentNew st2 = new StudentNew("둘리",100,"자바");	
		System.out.println(st2);
		st2.study();			// 입력값이 없기 때문에 첫번째 메서드 출력
		
		}
}
```



---





# <생성자 메서드 오버로딩>

- 생성자 메서드는 오버로딩이 가능하다.

1. ```java
   package day7;	
   
   class StudentNew2{
   	String name;
   	int age;
   	String subject;
   	StudentNew2(){							//매개변수 없는 생성자 생성
   	}
   	StudentNew2(String p1,int p2){			//같은이름의 매개변수 있는(다른)생성자 생성
   		name = p1;							//p1,p2 값은 변하나 subject는 "자바"고정
   		age = p2;
   		subject = "자바";
   	}
   	StudentNew2(String p1,int p2, String p3){		
   		name = p1;							//같은 이름의 매개변수 다른 생성자 생성
   		age = p2;
   		subject = p3;
   	}
   	void printStudentInfo() {									
   		System.out.println(name+"학생은 나이가 "+age+" 입니다.");
   	}
   	void study() {							//매개변수 없으면 출력되는 study 메서드		
   		System.out.println(name+"학생은 "+subject+" 과목을 학습합니다.");
   	}
   	void study(int hour) {					//매개변수가 int 타입이면 출력
   		System.out.println(name+"학생은 "+subject+" 과목을 "+hour+"시간 동안 학습합니다.");
   	}
   }
   public class StudentTest3 {						
   
   	public static void main(String[] args) {
   		StudentNew2 st1 = new StudentNew2("듀크",24);	
   		st1.study(2);
   		StudentNew2 st2 = new StudentNew2("둘리",100,"호잇");	
   		st2.study(2);
   		StudentNew2 st3 = new StudentNew2();	//생성자에 값이 없으므로 기본값
   		st3.study(2);
   		
   		}
   }
   ```



---





##### ex) class 생성, 속성값만 주고 사용하기

```java
package day7;

class Member {
	String name;
	String account;
	String passwd;
	int birthyear;
}

public class MemberTest {

	public static void main(String[] args) {
		Member d1 = new Member();
		d1.name = "울릉도";
		d1.account = "동남쪽1";
		d1.passwd = "secret";
		d1.birthyear = 910208;
		System.out.printf("회원 1 : %s(%s,%s,%d)\n", d1.name, d1.account, d1.passwd, d1.birthyear);
		d1 = new Member();
		d1.name = "뱃길따라";
		d1.account = "이백리1";
		d1.passwd = "topsecret";
		d1.birthyear = 910101;
		System.out.printf("회원 2 : %s(%s,%s,%d)\n", d1.name, d1.account, d1.passwd, d1.birthyear);
		d1 = new Member();
		d1.name = "외로운";
		d1.account = "섬하나1";
		d1.passwd = "toptopsecret";
		d1.birthyear = 010101;
		System.out.printf("회원 3 : %s(%s,%s,%d)\n", d1.name, d1.account, d1.passwd, d1.birthyear);

	}

}
출력값 = 
듀크학생은 자바 과목을 2시간 동안 학습합니다.
둘리학생은 호잇 과목을 2시간 동안 학습합니다.
null학생은 null 과목을 2시간 동안 학습합니다.

```



##### ex) 생성자 오버로딩, String 값으로 리턴하는 메서드 사용

```java
package day7;
class Book{
	String title;
	String author;
	int price;
	Book(){
//		title = "자바의 정석";
//		author = "남궁 성";
//		price = 30000;
		this("자바의 정석","남궁 성",30000);			//this를 사용하여 호출 가능
	}
	Book(String title,String author,int price){
		this.title = title;							//this 는 자기 자신의 객체 멤버의 변수
		this.author = author;
		this.price = price;
	}
	String getBookInfo() {
		String info = title+"  "+author+"  "+price;
		return info;
	}
	void BookTest(){
		System.out.println(getBookInfo());
	}
}
public class BookTest {

	public static void main(String[] args) {
		Book info1 = new Book();							//객채 생성 및 입력값 넣기
		Book info2 = new Book("자바의 정식","남궁 식",40000);
		Book info3 = new Book("자바의 정성","남궁 정성",50000);
		Book info4 = new Book("자바의 정수","남궁 수",60000);
		Book info5 = new Book("자바의 정신","남궁 신",70000);
		info1.BookTest();
		info2.BookTest();
		info3.BookTest();
		info4.BookTest();
		info5.BookTest();

	}

}
출력값 :
자바의 정석  남궁 성  30000
자바의 정식  남궁 식  40000
자바의 정성  남궁 정성  50000
자바의 정수  남궁 수  60000
자바의 정신  남궁 신  70000
```





##### ex)  배열을 사용하여 class 사용

```java
package day7;
class Product{
	String name;
	int balance;
	int price;
	Product(){
		this("듀크인형",5,10000);
//		name = "듀크인형";
//		balance = 5;
//		price = 10000;
	}
	Product(String name,int balance, int price){
		this.name = name;
		this.balance = balance;
		this.price = price;
	}
	String getName() {
//		String info = name;						//구지 변수 사용하지 않고 바로 리턴 가능
//		return info;
		return name;
	}
	int getBalance() {
//		int info = balance;
//		return info;
		return balance;
	}
	int getPrice() {
//		int info = price;
//		return info;
		return price;
	}
}
public class ProductTest {

	public static void main(String[] args) {
		Product[] p = new Product[5];				//Product 타입의 5원소 갖는 배열 생성
		p[0]=new Product();							//각 원소의 값 입력
		p[1]=new Product("듀크영혼",1,20000);		//이 원소들이 생성자로 들어가 구현
		p[2]=new Product("듀크나무",2,30000);
		p[3]=new Product("듀크집안",2,25469);
		p[4]=new Product("듀크전부",0,99999);
		for(int i=0;i<p.length;i++)					//출력을 반복시켜주는 for 구문
		System.out.printf("%s\t%d\t%,d원\n",p[i].getName(),p[i].getBalance(),p[i].getPrice());	
	}												//get 사용으로 입력값 적용

}
출력값 : 
듀크인형	5	10,000원
듀크영혼	1	20,000원
듀크나무	2	30,000원
듀크집안	2	25,469원
듀크전부	0	99,999원
```





##### ex) 보너스, Date Class 불러오기 및 사용

```java
package day7;

import java.util.Date;			    //java.util 패키지에 있는 Date 클래스를 데려와서 사용
import java.util.GregorianCalendar; //GregorianCalendar 클래스를 데려와서 사용
public class DateTest {
	public static void main(String[] args) {
		Date d=new Date();
		System.out.println(d.toString());			//toString() 은 date 출력 메서드
		GregorianCalendar gc = new GregorianCalendar();
		System.out.println(gc.get(GregorianCalendar.DAY_OF_WEEK)); //요일체크 0=일요일
		gc = new GregorianCalendar(1991,1,8);			//입력값 월은 0부터 시작
		System.out.println(gc.get(GregorianCalendar.DAY_OF_WEEK));

	}

}
```

