package day11;
import java.util.Random;
class TestException extends Exception {
	TestException(String message){
		super(message);		//�����޽����� ���
	}
}
public class ExceptionTest3 {
	public static void main(String[] args)  {
		System.out.println("main()�������");
		a();
		System.out.println("main()��������");
	}
	static void a()  {	//try, catch�� �� ���� throws �� �� �ʿ����.
		System.out.println("a()�������");
		try {
			b();
		} catch (TestException e){	
			System.out.println("���� �߻� : "+e.getMessage());
		}
		System.out.println("a()��������");
	}
	static void b() throws TestException {	//b�� ȣ���� c�� ������ �߻��� �� ������ ȣ���� ������ �ѱ�ڴ�.
		System.out.println("b()�������");
		c();
		System.out.println("b()��������");
	}
	static void c() throws TestException { //throws TestException �� ���ָ� ���� �ְ���� ������ ������
		System.out.println("c()�������");
		boolean flag = new Random().nextBoolean();	//������ �ȴ�� �ٷ� �� Random ����,,�ѹ��� ��� ����,,���� true or false
		if(flag){
			throw new TestException("<<:::::�׽�Ʈ�� ���ܹ߻���Ŵ:::::>>");
		}else {
			System.out.println("��������");
		}	
		System.out.println("c()��������");
	}	
}
