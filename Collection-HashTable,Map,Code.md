# Collection-HaseTable,Map,Code

#### HashTable, HashMap,HashCode

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

    

---

### HashTable

```java
package day13;
import java.util.*;
public class HashtableExample1 {
    public static void main(String args[]) {
        Hashtable<String, Integer> hashtable = 
        		new Hashtable<String, Integer>();//테이블 만들기
        hashtable.put("해리", new Integer(95));//자료들 입력
        hashtable.put("헤르미온느", new Integer(100));
        hashtable.put("론", new Integer(85));
        hashtable.put("드레이코", new Integer(93));
        hashtable.put("네빌", new Integer(70));
        hashtable.put("헤르미온느", 55); //데이터 중복? 그러면 대체함
      														// JDK 1.5 Auto Boxing 구문
        System.out.println(hashtable);
        Integer num = hashtable.get("헤르미온느");
        System.out.println("헤르미온느의 성적은? " + num);
    }

```

---

### HashpMap

```java
package day14.copy;
import java.util.*;
class HashMapExample2 {
    public static void main(String args[]) {
        HashMap<Name, Integer> hm = 
                                new HashMap<Name, Integer>();
        hm.put(new Name("해리", "포터"), new Integer(95));
        hm.put(new Name("헤르미온느", "그레인져"), new Integer(100));	
        hm.put(new Name("론", "위즐리"), new Integer(85));
        hm.put(new Name("드레이코", "말포이"), new Integer(93));
        hm.put(new Name("네빌", "롱버텀"), new Integer(70));
        hm.put(new Name("헤르미온느", "그레인져"), new Integer(55));
        //두개를 동일객체로 보지 않는다. Object의 참조값을 보기때문에 각각 다른 값이다.
        //따라서 equals 와 hasCode를 오버라이딩 해줘야 한다.
        System.out.println(hm);
    }
}
```



```java
package day14.copy;

class Name {//Name 이란 클래스 생성
	String firstName;//String 변수 성과 이름 설정
	String lastName;

	Name(String firstName, String lastName) {//Name 생성자 생성
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public boolean equals(Object o) {//equals 메서드 오버라이딩
		boolean result = false ; 
		if(o!=null&&o instanceof Name) {//o가 null 이 아니고 Name 일때
			Name obj = (Name)o;				//o가 Object 여서 Name으로 형변환
			if(firstName.equals(obj.firstName)	//firstName과 obj. 가 받는 firstName 같으면
					&&lastName.equals(obj.lastName)) {
				result = true;					//true 리턴
			}
		}
		return result;
	}
	public int hashCode() {
		return firstName.hashCode()+lastName.hashCode();//키설정
	}

	public String toString() {//프린트 형식화 해주는 메서드
		return "[" + firstName + lastName + "]";
	}
}
```



---

### HashMap 실습

```java
package day13;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class HashMapLab1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HashMap<String, Integer> hash = new HashMap<String, Integer>(5);//5개짜리 HashMap 만들어줌
		String str = null;//초기화
		int in = 0;//초기화
		while(hash.size() !=5){//5개 데이터를 갖을때까지 반복

			System.out.println("나라 이름을 입력하세요");
			str = sc.next();//스캐너 사용하여 str 데이터 입력
			if (hash.containsKey(str) == false) {//만약 중복되지 않으면
        //여기서 containsKey()메서드는 중복되면 true를 출력
        //그런데 false 로 설정하면 중복되지 않는다는 뜻
				System.out.println("인구 수를 입력하세요");
				in = sc.nextInt();
				System.out.println("저장되었습니다.");
				hash.put(str, in);//중복되지 않게 입력된 값들 저장

			} else
				System.out.printf("나라명 %s는 이미 저장되어 있습니다.", str);
				//true리턴으로 중복된 값을 갖으면 저장되어있다는 입력문 출력
		}//5개 모두 완성하면 끝

		System.out.println("5개가 모두 입력되었습니다.");
		System.out.print("입력된 데이터 : ");

		Iterator<String> iter = hash.keySet().iterator();
    												//hash의 키셋으로 출력할것임
		while (iter.hasNext()) {//다음이 없을떄가지 반복
			String st = iter.next();//다음 자료 가져오기
			System.out.print(iter.hasNext() ? st + "(" + hash.get(st) + ")," : "("+st + ")");

		}
		sc.close();//꼭 닫아줘야함!

	}

}
```