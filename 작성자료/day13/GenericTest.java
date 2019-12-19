package day13;
import java.util.*;
public class GenericTest {
	public static void main(String[] args) {
		LinkedList list = new LinkedList();	//Linked Hash �� ���ʸ� ������ ����Ʈ��,()���� �����ָ� Object ��ü�� �����Ǿ� �� �ڼ� ��ü�� ��� �� �� �ֵ�.
		list.add("java");		//�ڷ� ���� �� ���
		list.add("100");
		list.add("servlet");
		list.add("jdbc");
		
		for(int i=0; i < list.size(); i++)	//�ڷ� ���� ���1. �ӵ��� ������
			System.out.println(list.get(i));
		System.out.println();		
		
		for(Object value : list) {		//�ڷ� ���� ���2. ���� ����
			String s = (String)value;	
			System.out.println(s);
		}
		System.out.println();		
		
		Iterator iter = list.iterator();	//�ڷ� ���� ���3. �԰�ȭ�� ���
		while(iter.hasNext()){				//hasNext() ���ʴ�� �����°�
			Object value = iter.next();		//�ٸ� Ŭ�����鿡���� ��� ����
			String s = (String)value;		//����ȯ �� ������Ѵ� Object-> String
			System.out.println(s);
		}
	}
}
