package day2;

public class OperTest1 {

	public static void main(String[] args) {

		// ���������� : ����������(++), ���ҿ�����(--)
		
		int su1, su2, su3, su4;
		su1 = 10;
		su2 = 10;
		su3 = 10;
		su4 = 10;
		
		System.out.println(su1);
		System.out.println(++su1);  // ���� ��Ű�� su1�� ����
		System.out.println(su1++);
		System.out.println(su1++);
		System.out.println(++su1);
		System.out.println(--su1);
		
		System.out.println(su2);
		System.out.println(su2++);  // su2 �� �����ϰ� ���߿� ����
		System.out.println(su2++);  // su2 �� �����ϰ� ���߿� ����
		System.out.println(su2++);  // su2 �� �����ϰ� ���߿� ����
		System.out.println(su2--);  // su2 �� �����ϰ� ���߿� ����
		System.out.println(su2);	// 12
		
		System.out.println(su3);	// 10
		System.out.println(su3++);  // 10
		System.out.println(++su3);  // 12
		System.out.println(su3++);  // 12
		System.out.println(su3--);  // 13
		System.out.println(--su3);	// 11
		
		System.out.println(su4);	// 10
		++su4;
		System.out.println(su4);  // 11
		su4++;
		System.out.println(su4);  // 12
		// ++10;  can't use
		
		
	}

}
