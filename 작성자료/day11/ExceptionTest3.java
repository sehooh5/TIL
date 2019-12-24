package day11;
import java.util.Random;
class TestException extends Exception {
	TestException(String message){
		super(message);		//에러메시지로 등록
	}
}
public class ExceptionTest3 {
	public static void main(String[] args)  {
		System.out.println("main()수행시작");
		a();
		System.out.println("main()수행종료");
	}
	static void a()  {	//try, catch로 할 경우는 throws 절 줄 필요없다.
		System.out.println("a()수행시작");
		try {
			b();
		} catch (TestException e){	
			System.out.println("오류 발생 : "+e.getMessage());
		}
		System.out.println("a()수행종료");
	}
	static void b() throws TestException {	//b가 호출한 c에 오류가 발생할 수 있지만 호출한 애한테 넘기겠다.
		System.out.println("b()수행시작");
		c();
		System.out.println("b()수행종료");
	}
	static void c() throws TestException { //throws TestException 을 안주면 오류 주고싶은 절에서 에러남
		System.out.println("c()수행시작");
		boolean flag = new Random().nextBoolean();	//변수에 안담고 바로 쓴 Random 구문,,한번만 사용 가능,,값은 true or false
		if(flag){
			throw new TestException("<<:::::테스트로 예외발생시킴:::::>>");
		}else {
			System.out.println("ㅋㅋㅋㅋ");
		}	
		System.out.println("c()수행종료");
	}	
}
