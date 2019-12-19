package day10;

public class PolyTest {
	public static void main (String[] args) {			//String 또한 java.lang 생략 가능
		printObjectInfo(new Object());			////모든 객체들은 Object 의 객체이기에 사용 가능하다!
		printObjectInfo(new String("가나다"));
		printObjectInfo("ABC");
		printObjectInfo(new java.util.Date());
		printObjectInfo(new java.io.File("c:/Temp"));	//윈도우의 디렉토리 구분자는 \\ 두번 써줘야 다른기능이 작용 안한다 (혹은 / 한번)
		printObjectInfo(new int[10]);
		printObjectInfo(new double[5]);
		printObjectInfo(new day7.Member());		//사용 불가 = public class 가 아니기 때문
		printObjectInfo(new Integer(100));
		printObjectInfo(100);	//알아서 객체로 바꿔준다 이제
		printObjectInfo('가');	//알아서 객체로 바꿔준다 이제
		printObjectInfo(new Character('가'));
	}
	static void printObjectInfo(Object o) {		//Object 는 java.lang 에 있는데 생략 가능하다
		if(o instanceof String) {				//전달된 객체 o가 String 이면 참 아니면 거짓
			System.out.println("문자열 객체 전달됨 : "+o.getClass().getName()
					+" - " + ((String)o).length());
		}else {
		System.out.println("전달된 객체의 클래스 명 : "+o.getClass().getName());
		}
	}
}
