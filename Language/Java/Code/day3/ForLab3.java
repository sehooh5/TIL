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
//		System.out.println(su+" 부터 "+su2+" 까지의 짝수의 합 : "+sum);	

		int num1 = (int)(Math.random()*10)+1;
		int num2 = (int)(Math.random()*11)+30;
		int sum = 0;
		for(int i=num1;i<=num2;i++) {
			if(i%2==0) {
				sum += i;
			}
		}
		System.out.printf("%d 부터 %d 까지의 짝수의 합 : %d",num1,num2,sum);	
	}
}
