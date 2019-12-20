### Collection-ArrayList

---

#### List 

- 저장되는 데이터의 **순서를 유지**한다.
- 저장되는 데이터의 **중복을 허용**한다.
- ArrayList, LinkedList, Vector

---

**ArrayList** 

- ArrayList<파라미터> list 	= new ArrayList<(생략가능)>(값);

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

---

```java
package day13;
import java.util.*;
class ArrayListExample1 {
    public static void main(String args[]) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("포도");          
        list.add("딸기");          
        list.add("복숭아");          
        int num = list.size();
        for (int cnt = 0; cnt < num; cnt++) {
            String str = list.get(cnt);
            System.out.println(str);
        }
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        list2.add(new Integer(100));        
        list2.add(1000);// JDK 1.5 부터 AUTO Boxing 이라는 구문이 지원
        //list2.add("1000");
    }
}
```

---

```java
package day13;

import java.util.ArrayList;	//뽑아와서 사용
import java.util.Iterator;

class CreateList {
	public ArrayList<Integer> convertList(int array[]){	
        //Integer 형 리스트, int형 배열을 참조값으로 함
		ArrayList<Integer> ary = new ArrayList<>();	//ary 라는 리스트 생성
		for(int i=10;i>=0;i--)	//ary 의 크기는 10부터 0, 곧 11개의 배열
			ary.add(array[i]);	//array[i]값을 ary 리스트에 넣어준다
		return ary;
	}
}
public class ListTest {

	public static void main(String[] args) {
		
		CreateList array = new CreateList();//본문에서도 array라는 배열 생성
		int[] arr = {3,4,2,5,2,3,6,7,5,7,9};//arr라는 int 배열 생성
        ArrayList<Integer> i = array.convertList(arr);
		//array.convertList(arr);
        //covertList메서드를 사용하고 arr배열값 을 이용해 array배열에 변환
        Iterator<Integer> iter = i.iterator();
		//Iterator<Integer> iter = array.convertList(arr).iterator();
		while ( iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

}
```

