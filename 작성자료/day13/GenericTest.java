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
		
		for(Object value : list) {		//자료 추출 방법2. 제일 빠름
			String s = (String)value;	
			System.out.println(s);
		}
		System.out.println();		
		
		Iterator iter = list.iterator();	//자료 추출 방법3. 규격화된 방법
		while(iter.hasNext()){				//hasNext() 차례대로 꺼내는것
			Object value = iter.next();		//다른 클래스들에서도 사용 가능
			String s = (String)value;		//형변환 꼭 해줘야한다 Object-> String
			System.out.println(s);
		}
	}
}
