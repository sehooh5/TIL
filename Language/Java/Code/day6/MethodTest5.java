package day6;

public class MethodTest5 {

	public static void main(String[] args) {
		System.out.println("main() �������");
		int result = operate(10,20);							//���� ������ �����Ͽ� ����� �� �ִ�.
		System.out.println("ȣ�� ���1 : "+result);
		System.out.println("ȣ�� ���2 : "+operate(100,200));		//�޼��带 �ٷ� ����� �� �ִ�.
		int result2 = operate(11,22)%2;
		if(result2 ==0)
		System.out.println("ȣ�� ���3 : ¦��");
		else
		System.out.println("ȣ�� ���3 : Ȧ��");
		System.out.println("ȣ�� ���4 : "+operate(100,200,300));
		System.out.println("�� �ڸ��� ������ �·�? "+getName());
		System.out.println("*"+getName()+"*");
		System.out.println("main() ��������");
	}
	static int operate(int num1, int num2) {
		return num1+num2;
	}
	static int operate(int num1, int num2,int num3) {			//Method overloading #########�߿�!!
		return num1+num2+num3;									//�̸��� ������ �Ű����� ���� �ٸ� �� �ִ�
	}
	static String getName() {
		return "SEHO OH";
	}
}
////////////////���ϰ� ����////////////