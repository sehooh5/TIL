package day11;		
//에러들의 부모클래스는 Exception 이라는 클래스
public class ExceptionTest2 {

	public static void main(String[] args) {
		System.out.println("수행시작");
		try {						//예외가 발생할 수도 있는 코드, 꼭 예외 발생 코드만 있는건 아니다.
			int num1 = Integer.parseInt(args[0]);
			int num2 = Integer.parseInt(args[1]);
			int result = num1/num2;		//예외 발생하는 위치
			System.out.println("연산 결과 : "+result);
		}catch(ArrayIndexOutOfBoundsException e) {	//만약 여기 캐치블럭에서 예외 처리하면 다시 올라감
			System.out.println("프로그램 아규먼트를 2개 전달하세요!!");
		}catch(ArithmeticException e) {			//0으로 나눌경우 발생
			e.printStackTrace();				//e 값을 사용하여 call stack 에러 정보 출력(비동기수행)
			System.out.println("두번쨰 프로그램 아규먼트는 0이 아닌 값을 전달하세요!!");
			return;
		}catch (Exception e) {		//부모 클래스이기에 자손 클래스의 객체를 받을 수 있다.
									//부모 클래스일 수록 아래 위치시켜야 한다
									//위에 사용하면 모든 예외를 다 잡아내버린다
//		}catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("프로그램 아규먼트로 숫자를 전달하세요!!");
		}finally {
			System.out.println("항상수행!!");	
		}
		System.out.println("수행종료!!");
	}

}
