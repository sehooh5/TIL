package day2;

public class SwitchTest2 {

	public static void main(String[] args) {
			
//		int month = (int)(Math.random()*12)+1;
//		if(month == 12 || month == 1 || month == 2)
//			System.out.println(month + " : �ܿ�");
//		if(month == 3 || month == 4 || month == 5)			
//			System.out.println(month + " : ��");
//		if(month == 6 || month == 7 || month == 8)
//		 	System.out.println(month + " : ����");
//		if(month == 9 || month == 10 || month == 11)
//			System.out.println(month + " : ����");
//		
		int month = (int)(Math.random()*12)+1;
		switch(month) { // �� : ����, �����, ���ϰ��� �ִ� �޼����� ȣ���(only int, String)
		case 12:
		case 1:
		case 2: System.out.println(month + " : �ܿ�");
				break;
		case 3:
		case 4:
		case 5: System.out.println(month + " : ��");
				break;
		case 6:
		case 7:
		case 8: System.out.println(month + " : ����");
				break;
				
		default : System.out.println(month + " : ����");
		}
		
		
	}
}
