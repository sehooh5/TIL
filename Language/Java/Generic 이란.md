###  Generic 이란

- 클래스에서 사용할 타입을 외부에서 설정하는 것

- 설정 방법 : ClassName<사용할 타입=파라미터>

- 파라미터에 사용 가능한 타입 : Wrapper Class

  ex) String, Integer, Boolean, Shorts, Double...대부분 대문자사용한 

- 장점 : 

  1. 만들 때 자유롭게 객체 생성 타입을 정할 수 있다.
  2. 객체의 형변환에 대해서 자유롭게 할 수 있다.

```java
package day13;
import java.util.Date;
public class CreateGenericTest { 
    	//***Generic = 객체 생성되는 타입이 객채 생성시 정하는 일반화 구문
	public static void main(String[] args) {
        //만들때도 자유롭게 형변환에 대해서 자유롭게 할 수 있다.
		Value1 o1 = new Value1();
		o1.put("abc");
		String s1 = o1.get(); 
		System.out.println(s1);		
		
		Value2 o2 = new Value2();
		o2.put("abc");
		String s2 = (String)o2.get();	//부모 클래스에서 자식으로 형변환
		System.out.println(s2);			//Object to String
		
		Value3<String> o3 = new Value3<>();	//String 파라미터 사용	
		o3.put("abc");
		String s3 = o3.get(); //형변환 안해줘도 된다. 타입 파라미터가 String 이기 때문에
		System.out.println(s3);	
		
		Value3<Date> o4 = new Value3<Date>();	//Date 타입 파라미터 사용
		o4.put(new Date());
		Date s4 = o4.get(); 
		System.out.println(s4);	
	}
}


class Value1 {
	String obj;
	void put(String obj){
		this.obj = obj;
	}
	String get() {
		return obj;
	}
}
class Value2 {
	Object obj;	//모든 객체 저장 가능-Object 이기 때문
	void put(Object obj){ //입력할때는 상관없음
		this.obj = obj;
	}
	Object get() {		//꺼낼때는 형변환 해줘야 한다.
		return obj;
	}
}
// Value3<Card> v = new Value3<card>();	//Card 를 먼저 주면 아래 TT가 Card로 바뀐다
// Value3<String> v = new Value3<String>();	//객체가 생성되는 타입을 일반화 시킨다. 객체 생성 시점에서 정하는 고급 구문

class Value3<TT> {		//< > 안에 내용은 상관이 없다. 사용할때 변경 가능
	TT obj;
	void put(TT obj){
		this.obj = obj;
	}
	TT get() {
		return obj;
	}
}
```

---

```java
package day13;
import java.util.*;
public class GenericTest {
	public static void main(String[] args) {
		LinkedList list = new LinkedList();	//Linked Hash 등 제너릭 적용한 리스트들,()설정 안해주면 Object 객체로 설정되어 그 자손 객체가 모두 들어갈 수 있따.
		list.add("java");		//자료 넣을 때 사용
		list.add("100");
		list.add("servlet");
		list.add("jdbc");
		
		for(int i=0; i < list.size(); i++)	//자료 추출 방법1. 속도가 느리다
			System.out.println(list.get(i));
		System.out.println();		
		
		for(Object value : list) {			//자료 추출 방법2. 제일 빠름
			String s = (String)value;	
			System.out.println(s);
		}
		System.out.println();		
		
		Iterator iter = list.iterator();	//자료 추출 방법3. 규격화된 방법
		while(iter.hasNext()){				//hasNext() 차례대로 꺼내는것
			Object value = iter.next();		//다른 클래스들에서도 사용 가능
			String s = (String)value;		//형변환 꼭 해줘야한다 Object-> String
		}
	}
}
```

- List, Hash 등등 모두 제너릭을 사용하는데 파라미터 지정 안해주면 자동으로 Object 형..

  따라서, 모든 자료들을 넣을 수 있다.

- 이 자료에서 파라미터에 `<String>` 을 넣어주면 형변환에 자유로울 수 있다.

---

