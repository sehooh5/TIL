# API-Collection

### 데이터들을 저장하여 사용하는 방의 역할을 하는 API

---

#### 1. List	2.Set	3.Map 

---

#### 특징

- 저장할 수 있는 데이터 타입에 제한이 없다.(Object 타입의 배열같음, 하지만 제한 가능)
- 저장할 수 있는 데이터의 개수의 제한이 없다.
- **Collections 클래스** : Collection API들을 도와주는 도우미 클래스



#### 1. List 

- 저장되는 데이터의 순서를 유지한다.
- 저장되는 데이터의 중복을 허용한다.
- ArrayList, LinkedList, Vector



#### 2. Set

- 저장되는 데이터의 순서를 유지하지 않는다.
- 저장되는 데이터의 중복을 허용하지 않는다. 로또 같이 중복 찾는 프로그램 찾는데 용이
- equals, Hashcode(객체 고유 번호=객체의 참조값, 주소값) 메서드 사용
- HashSet, LinkedSet, TreeSet



#### 3. Map

- 데이터 저장 시, 데이터 이름(key)과 데이터 값(value)을 동시에 저장한다.
- 저장되는 데이터의 순서를 유지하지 않는다.
- 저장되는 데이터의 이름(key)의 중복을 허용하지 않는다.
- 저장되는 데이터의 값(value)의 중복은 허용한다.
- HashMap, TreeMap, Hashtable



**stack** last in first out (나중에 넣은 객체 먼저 꺼내기)

**queue** first in first out ( 처음에 넣은 객체 먼저 꺼내기)



제너릭스((((((((((이클립스보고 정리))))))))))



#### 1. Listlist 

1. ArrayList 

   - ArrayList<파라미터> list 	= new ArrayList<(생략가능)>();

     값 안주면 10개짜리 배열

   - add 메소드 호출 순서대로 데이터가 저장

   - 타입 파라미터와 맞는 데이터 값을 넘겨 주어야 한다.

   - list.add(2, "키위" ); = 3번째 공간에 데이터를 밀고 키위를 넣어라 

   - list.set(2, "키위" ); = 3번째 공간 데이터 지우고 키위를 넣어라

   - list.remove(1); = 2번째 공간의 데이터 지워라

     list.remove("키위"); = 키위 데이터 지워라

   - list.indexOf("사과"); = 처음부터 사과의 위치 찾아서 리턴

     list.lastIndexOf("사과"); = 마지막 부터 사과위치 찾아서 리턴

     *만약 리턴값 없으면 -1 리턴

2. LinkedList

   - 사용하는 방법은 ArrayList 와 동일하다

   - 비어잇는 객체에서 시작되어서 add 객체 생성하면 노드객체 생성하면서 입력

   - list.get(2); = 처음을 인덱스 0 으로 차례대로 2번에 해당하는 위치 리턴...시간 오래걸림

     for each 나 iterator 사용하면 빠르다

   - list.add(2, "키위" ); = 1과 2 사이 링크 끊고 사이에 넣어서 링크 연결

   - (((((((32-33 페이지 보고 정리하기 iterator.hasNext)))))))

   - push = addLast = 마지막에 넣어라

     pop = getLast =  마지막꺼 꺼내라

   - queue.offer(); = 입력

   - queue.poll(); = 제일 먼저 입력된 값 없애면서 리턴

   - queue.peek(); = 데이터는 그대로 두고 값 제일 먼저 입력된 값 리턴

3.  HashSet

   - iterator 는 거의 필수다
   - 중복처리를 알아서 해준다 로또 만들기 쉬움
   - list.size(); 길이
   - list.add(); 더해주기

4. HashTable, HashMap,HashSet

   - 버켓을 만들어서 key값을 이용하여 데이터 넣을 통 번호를 계산하는 구조
   - 버켓 개수가 많으면 세세하게 정리할 수 있다.
   - 파라미터 두개를 줘야함 key,data 두개
   - HashMap<keyType,dataType> hashtable = new HashMap<keyType,dataType>();
   - 데이터 넣기 : hashtable.put("해리",new Integer(95));
   - 데이터 뽑기 : key 값으로 찾음// Integer num = hashtable.get("해리");
   - 지우기 : hashtable.remove("해리");
- 중복된 키가 들어갈 경우 기존 데이터를 무조건 대체함
   - 기본적으로 16개의 버켓을 만든다..물론 늘려줄 수 있다
   - hash.containsKey(key값 ) ==> key 값이 중복되면 true를 리턴
   - hash.keySet() ==> key의 값만 불러오기
   
   - **HashCode 로 오버라이딩**
   
     - 버켓을 판단할 때 쓰는 것이 바로 해쉬 값, HashCode
   
     - 사용하려는 클래스에 오버라이딩 하지 않으면 Object로 상속받은 hashCode메소드를 그대로 사용하여 출력을 할 수 없다.
   
     - **equals** 또한 오버라이딩 해줘야 한다.
   
     - ```java
       package day14;
       
       import java.util.HashSet;
       
       class Member3 {
       	private int id;
       	private String name;
       	private String password;
       	Member3(int id, String name, String password) {
       		this.id = id;
       		this.name = name;
       		this.password = password;
       	}
       	public boolean equals(Object o) {	//equals 오버라이딩
       		if (o != null && o instanceof Member3) {
       			Member3 m = (Member3) o;			
       			if (id ==  m.id && 
       					name.equals(m.name) && 
       							password.equals(m.password))
       				return true;
       		}
       		return false;
       	}	
       	public int hashCode() {//hashCode 오버라이딩 하여 key로 사용
       		return id+name.hashCode()+password.hashCode();	
       	}
       	
       	public String toString() {
       		return "("+id+":"+name+":"+password+")";
       	}
       }
       public class ObjectTest3 {
       	public static void main(String args[]) {
       		Member3 obj1 = new Member3(10, "자바", "duke");
       		Member3 obj2 = new Member3(10, "자바", "duke");
       		Member3 obj3 = new Member3(20, "자바", "duke");
       		System.out.println(obj1.hashCode());
       		System.out.println(obj2.hashCode());		
       		System.out.println(obj3.hashCode());	
       		HashSet<Member3> set = new HashSet<>();
       		set.add(obj1);
       		set.add(obj2);
       		set.add(obj3);
       		System.out.println("저장된 데이터의 갯수 : " + set.size());
       		System.out.println(set);
       	}
       
       ```
   
       

---

### 가장 기본이 되는 자료

```java
package day13;
import java.util.Date;
public class CreateGenericTest { 
  //***Generic = 객체 생성되는 타입이 객채 생성시 정하는 일반화 구문
	public static void main(String[] args) {
  //만들때도 입력때도 자유롭게 형변환에 대해서 자유롭게 할 수 있다.
		Value1 o1 = new Value1();
		o1.put("abc");
		String s1 = o1.get(); 
		System.out.println(s1);		//전부 String 무난히 통과
		
		Value2 o2 = new Value2();
		o2.put("abc");
		String s2 = (String)o2.get(); //Object 사용으로 사용할때는
		System.out.println(s2);				//다시 형변환 해줘야 한다
		
		Value3<String> o3 = new Value3<>();		
		o3.put("abc");
		String s3 = (String)o3.get(); 
    //형변환 안해줘도 된다. 타입 파라미터가 String 이기 때문에,,저거생략가능
		System.out.println(s3);	
		
		Value3<Date> o4 = new Value3<Date>();		
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

class Value3<TT> {		//< > 안에 내용은 상관이 없다. 사용할때 오버라이딩
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

### Generic 출력하는 방법!!

```java
package day13;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.*;
public class GenericTest {
	public static void main(String[] args) {
		LinkedList list = new LinkedList();	//Linked Hash 등 제너릭 적용한 리스트들,()안에 설정 안해주면 Object 객체로 설정되어 그 자손 객체가 모두 들어갈 수 있따.대신 한가지 종류의 객체들을 입력 가능.
		list.add("java");		//자료 넣을 때 name.add 사용
		list.add("100");
		list.add("servlet");
		list.add("jdbc");
		
    //자료 추출 방법1.기본 for 구문, 속도가 느리다
		for(int i=0; i < list.size(); i++)	
			System.out.println(list.get(i));
		System.out.println();		
		
    //자료 추출 방법2.for each 구문, 제일 빠름
		for(Object value : list) {		
			String s = (String)value;	
			System.out.println(s);
		}
		System.out.println();		
		
    //자료 추출 방법3.Iterator 인터페이스, 규격화된 방법
		Iterator iter = list.iterator();	
		while(iter.hasNext()){				//hasNext() 차례대로 꺼내는것
			Object value = iter.next();		//다른 클래스들에서도 사용 가능
			String s = (String)value;		//형변환 꼭 해줘야한다 Object-> String
			System.out.println(s);
		}
	}
}
```



### 프린트를 파라미터 사용하여 더 간편하게 하기

```java
package day13;
import java.util.*;
public class GenericTestNew {
	public static void main(String[] args) {
		// 제네릭스 라는 구문이 적용되어 만들어진 클래스의 객체 생성시
		// 타입 파라미터라는 것을 사용한다. 
		LinkedList<String> list = new LinkedList<String>();  
    // 타입 파라미터 String 사용
		list.add("java");
		list.add("100");
		list.add("servlet");
		list.add("jdbc");
		
		for(int i=0; i < list.size(); i++)
			System.out.println(list.get(i));
		System.out.println();		
		
		for(String value : list) {			
			System.out.println(value);//선언해줄 필요가 없음
		}
		System.out.println();
		
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()){
			String s = iter.next();			//장점2. 형변환을 안해줘도 괜찮다
			System.out.println(s);
		}
	}
}
```



