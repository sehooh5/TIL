package day3;

public class ForLab5 {

	public static void main(String[] args) {
	
		int evenNum=0;
		int oddNum=0;
		
		for(int n=1;n<=100;n+=2) {
			oddNum = oddNum + n;
		}
		
		for(int n2=2;n2<=100;n2+=2) {
			evenNum = evenNum+n2;
			
		}
	
		System.out.println("1���� 100������ ���ڵ� �߿���");
		System.out.println("¦���� ���� "+evenNum+" �̰�");
		System.out.println("Ȧ���� ���� "+oddNum+" �̴�.");
		
	}
}
