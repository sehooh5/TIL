# 191211(Wed)-Array Mathod, OOP start

```
package day7;

public class MethodTest10 {

	public static void main(String[] args) {
		int p1[] = {20,10,14,30};
		System.out.println("p1 변수의 값 : "+p1);
		printArray(p1);						//p1 원소를 넣어서 배열을 프린트
		int[] result = updateArray1(p1);	//updateArray1에 p1의 원소들이 들어가 새로운 result 배열 작성
		printArray(result);
		printArray(p1);
		System.out.println("----------------");	
		updateArray1(p1);					//updateArray1 에 들어갔다 나와도 숫자는 변하지 않는다
		printArray(p1);
		System.out.println("----------------");
		updateArray2(p1);					//updateArray2 에 들어갔다 나오면 숫자가 변한다.
		printArray(p1);
	}
	static void printArray(int[]p2) {		//배열을 프린트해주는 메서드
		for(int data:p2)
			System.out.printf("%d ",data);
		System.out.printf("\n");
	}
	static int[] updateArray1(int [] p2) {		//배열의 원소에 2씩 곱해주는 메서드
		System.out.println("p2 변수의 값 : "+p2);
		int [] result = new int[p2.length];
		for(int i=0;i<p2.length;i++)
			result[i] = p2[i]*2;
		return result;
	}
	static void updateArray2(int [] p3) {
		System.out.println("p3 변수의 값 : "+p3);
		for(int i=0;i<p3.length;i++)
			p3[i] = p3[i]*2;
	}
}	
```

여기서 중요한 점은

update Array1 과 update Array2 의 차이점이다.

1 에 변수배열이 들어갔을 때의 값은 변하지 않는다.

2 에 변수 배열이 들어가면 값이 변해서 나온다.

그리고

배열을 프린트하는 메서드

printArray)를 작성해놓으면

메인 메서드에서 쉽게 사용할 수 있다.



#### 객체지향개념 (OOP)

- 객체는 속성+기능

  속성은 변수로, 기능은 메서드로 정의한다.

- 생성방법

  ```
  클래스명 참조변수명;				//클래스명 첫글짜는 대문자
  참조변수명 = new 클래스명();
  
  ex) Tv t;
  	t = new Tv();
  	t.channel = 7;
  	t.channelDown();
  	System.out.pringln(t.channel);
  ```



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