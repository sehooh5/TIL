package day12;

import java.util.Date;

class Value {
	int value;
	Value(int value){
		this.value = value;
	}
	public boolean equals(Object obj) {
		boolean result = false;
        if(obj != null && obj instanceof Value)	//instance of 사용 하여 obj 가 Value 객체인지 확인
        	if(value == ((Value)obj).value)		//obj를 Value 값으로 강제 형변환
        		result = true;
		return result;
    }
}
public class EqualsTest {
	public static void main(String[] args) {
		Value v1 = new Value(10);
		Value v2 = new Value(10);
		Value v3 = new Value(20);
		System.out.println(v1.equals(null)); // f - null 이므로 
		System.out.println(v1.equals(v3));   // f - 같은 Value값, 하지만 값이 다름
		System.out.println(v1.equals(v2));   // t
		System.out.println(v1.equals(new Date()));   // f - Value 객체 아님
		if(v1 == v2) 		//	등가 == 쓰면 참조값만 비교한다 . 그러므로 다르다!
			System.out.println("v1과 v2는 같습니다.");
		else 
			System.out.println("v1과 v2는 다릅니다.");
		v2 = v1;
		if(v1 == v2) 
			System.out.println("v1과 v2는 같습니다.");
		else 
			System.out.println("v1과 v2는 다릅니다.");
		String s1 = "가나다";
		String s2 = "가나다";
		String s3 = new String("가나다");
		String s4 = new String("가나다");
		
		System.out.println(s1 == s2);	//t 똑같은 내용의 리터럴을 작성하면 한개만 만들어서 쓴다.
		System.out.println(s3 == s4);	//f
		System.out.println(s1.equals(s2));//t
		System.out.println(s3.equals(s4));//t
		
	}
}
