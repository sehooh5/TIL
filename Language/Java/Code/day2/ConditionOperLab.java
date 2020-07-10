package day2;

public class ConditionOperLab {

	public static void main(String[] args) {

		int lotto = (int)(Math.random()*5+1);
		int a = 300;
		int b = 50;
		int c;
		
		if (lotto == 1) 
			c = a+b;
		
		else if  (lotto == 2) 
			c = a-b;
		
		else if  (lotto == 3) 
			c = a*b;
		
		else if  (lotto == 4) 
			c = a/b;
		
		else
			c = a%b;
		
		System.out.print("°á°ú°ª : " + c);
		
		
	}

}
