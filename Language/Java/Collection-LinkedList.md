# Collection-LinkedList

LinkedList

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

---



```java
package day13;
import java.util.*;
class LinkedListExample1 {
    public static void main(String args[]) {
        LinkedList<String> list = new LinkedList<String>();
        list.add("포도");          
        list.add("딸기");          
        list.add("복숭아");          
        int num = list.size();
        for (int cnt = 0; cnt < num; cnt++) {
            String str = list.get(cnt);
            System.out.println(str);
        }
        LinkedList<Integer> list2 = new LinkedList<Integer>();
        list2.add(new Integer(100));       
        list2.add(1000);// JDK 1.5 부터 AUTO Boxing 이라는 구문이 지원
//        list2.add("1000");
    }
}
```



```java
package day13;
import java.util.*;
class LinkedListExample2 {
    public static void main(String args[]) {
        LinkedList<String> list = new LinkedList<String>();
        list.add("포도");            //0	포도
        list.add("딸기");            //1	포도딸기
        list.add("복숭아");          //2	포도딸기복숭아
        list.add(2, "키위");        //2복숭아 끊고 키위 넣기  포도딸기키위복숭아  
        list.set(0, "오렌지");      //0포도 없애고 오렌지 넣기 오렌지딸기~  
        list.remove(1);            //1로 바뀐 딸기 삭제 오렌지키위복숭아
        list.remove("키위");        //2에 있는 키위 제거	오렌지복숭아
        int num = list.size();
        for (int cnt = 0; cnt < num; cnt++) {
            String str = list.get(cnt);
            System.out.println(str);
        }
    }
}
```



```java
package day13;

import java.util.Iterator;
import java.util.LinkedList;	//ctrl+shift+O 하면 사용한 메서드 찾아줌


class Person{
		private String name;//자손, 클래스 모두 접근 불가- Person 클래스 내에서만 사용
		Person(String name){
			this.name = name;
		}
		public String getInfo() {
			return name;
		}
}
		class Friend extends Person{
			private String phoneNum ;
			private String email;
			Friend(String name, String num, String add){
				super(name);
				this.phoneNum = num;
				this.email = add;
			}
			public String getInfo() {
				return super.getInfo()+"  "+phoneNum+"  "+email;
		}
	}
public class FriendTestLinkedList {
	public static void main(String[] args) {
		
		LinkedList<Friend> info = new LinkedList<>();

		
		info.add(new Friend ("오세호","12345678","seho@seho.com"));
		info.add(new Friend ("오세호","12345678","seho@seho.com"));
		info.add(new Friend ("오세호","12345678","seho@seho.com"));
		info.add(new Friend ("오세호","12345678","seho@seho.com"));
		info.add(new Friend ("오세호","12345678","seho@seho.com"));
		
		for(int i = 0; i<info.size();i++) {	
			Friend list = info.get(i);
				System.out.println(list.getInfo());
		}
		System.out.println("-------------------------");
		for(Friend value : info) {
			System.out.println(value.getInfo()); 	//for eace 익히기
		}
		System.out.println("-------------------------");
		Iterator<Friend> iter = info.iterator();	//iteratop 다시공부!!
		while (iter.hasNext()) {
			Friend fri = iter.next();
            System.out.println(fri.getInfo());
		}
		
	}

}
```

