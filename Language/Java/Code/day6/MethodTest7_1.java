package day6;

public class MethodTest7_1 {

	public static void main(String[] args) {
		String result = "";						// String result; �� �ʱ�ȭ�� �ȵǾ� R-value �� ����� �Ұ����ϴ�
		for(int i=0;i<args.length;i++)
			result +=args[i];					//result - result + args[i] �̹Ƿ� ���⼭ R-value ��� �Ǳ� ������
		System.out.println(result);
	}

}
