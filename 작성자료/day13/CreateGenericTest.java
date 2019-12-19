package day13;
import java.util.Date;
public class CreateGenericTest { //***Generic = ��ü �����Ǵ� Ÿ���� ��ä ������ ���ϴ� �Ϲ�ȭ ����
	public static void main(String[] args) {//���鶧�� �����Ӱ� ����ȯ�� ���ؼ� �����Ӱ� �� �� �ִ�.
		Value1 o1 = new Value1();
		o1.put("abc");
		String s1 = o1.get(); 
		System.out.println(s1);		
		
		Value2 o2 = new Value2();
		o2.put("abc");
		String s2 = (String)o2.get(); 
		System.out.println(s2);
		
		Value3<String> o3 = new Value3<>();		
		o3.put("abc");
		String s3 = (String)o3.get(); //����ȯ �����൵ �ȴ�. Ÿ�� �Ķ���Ͱ� String �̱� ������
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
	Object obj;	//��� ��ü ���� ����-Object �̱� ����
	void put(Object obj){ //�Է��Ҷ��� �������
		this.obj = obj;
	}
	Object get() {		//�������� ����ȯ ����� �Ѵ�.
		return obj;
	}
}
// Value3<Card> v = new Value3<card>();	//Card �� ���� �ָ� �Ʒ� TT�� Card�� �ٲ��
// Value3<String> v = new Value3<String>();	//��ü�� �����Ǵ� Ÿ���� �Ϲ�ȭ ��Ų��. ��ü ���� �������� ���ϴ� ��� ����

class Value3<TT> {		//< > �ȿ� ������ ����� ����.
	TT obj;
	void put(TT obj){
		this.obj = obj;
	}
	TT get() {
		return obj;
	}
}













