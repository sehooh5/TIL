package day10;

public class PolyTest {
	public static void main (String[] args) {			//String ���� java.lang ���� ����
		printObjectInfo(new Object());			////��� ��ü���� Object �� ��ü�̱⿡ ��� �����ϴ�!
		printObjectInfo(new String("������"));
		printObjectInfo("ABC");
		printObjectInfo(new java.util.Date());
		printObjectInfo(new java.io.File("c:/Temp"));	//�������� ���丮 �����ڴ� \\ �ι� ����� �ٸ������ �ۿ� ���Ѵ� (Ȥ�� / �ѹ�)
		printObjectInfo(new int[10]);
		printObjectInfo(new double[5]);
		printObjectInfo(new day7.Member());		//��� �Ұ� = public class �� �ƴϱ� ����
		printObjectInfo(new Integer(100));
		printObjectInfo(100);	//�˾Ƽ� ��ü�� �ٲ��ش� ����
		printObjectInfo('��');	//�˾Ƽ� ��ü�� �ٲ��ش� ����
		printObjectInfo(new Character('��'));
	}
	static void printObjectInfo(Object o) {		//Object �� java.lang �� �ִµ� ���� �����ϴ�
		if(o instanceof String) {				//���޵� ��ü o�� String �̸� �� �ƴϸ� ����
			System.out.println("���ڿ� ��ü ���޵� : "+o.getClass().getName()
					+" - " + ((String)o).length());
		}else {
		System.out.println("���޵� ��ü�� Ŭ���� �� : "+o.getClass().getName());
		}
	}
}
