package day3;

public class ForLab3 {

	public static void main(String[] args) {
	
//		int su = (int)(Math.random()*10)+1;
//		int su2 = (int)(Math.random()*11)+30;
//		
//		int sum = 0;
//		for(int n=su;n<=su2;n++) {
//			if(n%2==0) {
//			sum=sum+n;
//			}
//		}
//		System.out.println(su+" ���� "+su2+" ������ ¦���� �� : "+sum);	

		int num1 = (int)(Math.random()*10)+1;
		int num2 = (int)(Math.random()*11)+30;
		int sum = 0;
		for(int i=num1;i<=num2;i++) {
			if(i%2==0) {
				sum += i;
			}
		}
		System.out.printf("%d ���� %d ������ ¦���� �� : %d",num1,num2,sum);	
	}
}
