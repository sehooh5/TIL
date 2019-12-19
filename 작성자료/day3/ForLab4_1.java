package day3;

public class ForLab4_1 {

	public static void main(String[] args) {
		
		
		int su1 = (int)(Math.random()*8)+3;
		int su2 = (int)(Math.random()*3)+1;
		
		
		switch(su2){
			case(1): 
			for (int n = 1;n<=su1;n++) 
			System.out.print("*");	
		break;
			case(2):
			for (int n = 1;n<=su1;n++) 
			System.out.print("$");	
		break;
			case(3):
			for (int n = 1;n<=su1;n++) 
			System.out.print("#");	
		}
		System.out.println();
		System.out.println(su1 + " " + su2);
	}
}
