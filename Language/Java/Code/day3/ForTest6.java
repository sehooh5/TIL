package day3;

public class ForTest6 {

	public static void main(String[] args) {
		
		// 10���� 1������ ���ڿ� ���Ͽ� ���� ���� �ش� ������ �������� 
		// ������� ���
		for(int n=10;n>=1;--n) {
			System.out.println(n + " " + n*n);
			System.out.println("------------");
		}
		//10���� 20������ ���� 
		for(int n=10;n<=20;n+=3) {
			 System.out.println(n + " " + n*n);
			System.out.println("------------");
		}
	}
}
