package day11;		
//�������� �θ�Ŭ������ Exception �̶�� Ŭ����
public class ExceptionTest2 {

	public static void main(String[] args) {
		System.out.println("�������");
		try {						//���ܰ� �߻��� ���� �ִ� �ڵ�, �� ���� �߻� �ڵ常 �ִ°� �ƴϴ�.
			int num1 = Integer.parseInt(args[0]);
			int num2 = Integer.parseInt(args[1]);
			int result = num1/num2;		//���� �߻��ϴ� ��ġ
			System.out.println("���� ��� : "+result);
		}catch(ArrayIndexOutOfBoundsException e) {	//���� ���� ĳġ������ ���� ó���ϸ� �ٽ� �ö�
			System.out.println("���α׷� �ƱԸ�Ʈ�� 2�� �����ϼ���!!");
		}catch(ArithmeticException e) {			//0���� ������� �߻�
			e.printStackTrace();				//e ���� ����Ͽ� call stack ���� ���� ���(�񵿱����)
			System.out.println("�ι��� ���α׷� �ƱԸ�Ʈ�� 0�� �ƴ� ���� �����ϼ���!!");
			return;
		}catch (Exception e) {		//�θ� Ŭ�����̱⿡ �ڼ� Ŭ������ ��ü�� ���� �� �ִ�.
									//�θ� Ŭ������ ���� �Ʒ� ��ġ���Ѿ� �Ѵ�
									//���� ����ϸ� ��� ���ܸ� �� ��Ƴ�������
//		}catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("���α׷� �ƱԸ�Ʈ�� ���ڸ� �����ϼ���!!");
		}finally {
			System.out.println("�׻����!!");	
		}
		System.out.println("��������!!");
	}

}
