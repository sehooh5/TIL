package day2;

public class SwitchTest2 {

	public static void main(String[] args) {
			
//		int month = (int)(Math.random()*12)+1;
//		if(month == 12 || month == 1 || month == 2)
//			System.out.println(month + " : 겨울");
//		if(month == 3 || month == 4 || month == 5)			
//			System.out.println(month + " : 봄");
//		if(month == 6 || month == 7 || month == 8)
//		 	System.out.println(month + " : 여름");
//		if(month == 9 || month == 10 || month == 11)
//			System.out.println(month + " : 가을");
//		
		int month = (int)(Math.random()*12)+1;
		switch(month) { // 식 : 변수, 연산식, 리턴값이 있는 메서드의 호출식(only int, String)
		case 12:
		case 1:
		case 2: System.out.println(month + " : 겨울");
				break;
		case 3:
		case 4:
		case 5: System.out.println(month + " : 봄");
				break;
		case 6:
		case 7:
		case 8: System.out.println(month + " : 여름");
				break;
				
		default : System.out.println(month + " : 가을");
		}
		
		
	}
}
