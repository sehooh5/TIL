package day3;

public class ForLab4 {

	public static void main(String[] args) {
//		int su1 = (int)(Math.random()*8)+3;
//		int su2 = (int)(Math.random()*3)+1;
//		
//		if (su2==1) {
//			for (int n = 1;n<=su1;n++) 
//				System.out.print("*");	
//		}
//		else if (su2==2) {
//			for (int n = 1;n<=su1;n++) 
//			
//				System.out.print("$");	
//		}
//		else if (su2==3) {
//			for (int n = 1;n<=su1;n++) 
//				System.out.print("#");	
//		}
		int num1 = (int)(Math.random()*8)+3;
		int num2 = (int)(Math.random()*3)+1;
//		if(num2 == 1)
//			for(int i=0;i<num1;i++)
//				System.out.print("*");
//		else if(num2 == 2)
//			for(int i=0;i<num1;i++)
//				System.out.print("$");
//		else
//			for(int i=0;i<num1;i++)
//				System.out.print("#");
		switch(num2) {
		case 1 : for(int i=0;i<num1;i++)
			System.out.print("*");
		break;
		case 2 : for(int i=0;i<num1;i++)
			System.out.print("$");
		break;
		default :
			System.out.print("#");
		}
		
	}
}
