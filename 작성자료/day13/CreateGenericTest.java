package day13;
import java.util.Date;
public class CreateGenericTest { //***Generic = 객체 생성되는 타입이 객채 생성시 정하는 일반화 구문
	public static void main(String[] args) {//만들때도 자유롭게 형변환에 대해서 자유롭게 할 수 있다.
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
		String s3 = (String)o3.get(); //형변환 안해줘도 된다. 타입 파라미터가 String 이기 때문에
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

class Value3<TT> {		//< > 안에 내용은 상관이 없다.
	TT obj;
	void put(TT obj){
		this.obj = obj;
	}
	TT get() {
		return obj;
	}
}













