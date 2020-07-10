package day2;

public class SwitchLab2 {

	public static void main(String[] args) {

		int lotto = (int)(Math.random()*5+1);
		int a = 300;
		int b = 50;
		int c;
		
		switch(lotto) {
		case 1:
			c = a+b;
		break;
		case 2:
			c = a-b;
		break;
		case 3:
			c = a*b;
		break;
		case 4:
			c = a/b;
		break;
		default :
			c = a%b;
		}
		System.out.print("°á°ú°ª : " + c);
		
		
	}

}
