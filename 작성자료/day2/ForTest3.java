package day2;

public class ForTest3 {

	public static void main(String[] args) {

		for(int num = 1 ;num <=50;num+=2)
			System.out.print(num+" ");
		System.out.println();
		for(int num = 20 ;num >=1;num--)
			System.out.print(num+" ");
		System.out.println();
		for(int num = 20 ;num >=1;num-=3)
			System.out.print(num+" ");
		System.out.println();
		for(int num = 10 ;num >=1;num-=3)
			System.out.print(num+" ");
		System.out.println();
		System.out.print("---------");
		
		
		int num;							//for 이전에 초기식 설정해주면 for 이후에도 사용 가능
		System.out.println();
		for(num=10 ;num >=1;num-=3)
			System.out.print(num+" ");
		System.out.println();
		System.out.println(num);
		System.out.println(num);
	}

}
