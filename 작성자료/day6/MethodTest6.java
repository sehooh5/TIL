package day6;

public class MethodTest6 {

	public static void main(String[] args) {
		System.out.println("main() 수행시작");
		System.out.println(add(10,100));
		System.out.println(add(new int[]{10,10,30}));		//new int[]{ }으로 배열생성해주어야한다.
		System.out.println(add(new int[]{10,10,30,40,50,60}));		//new int[]{ }으로 배열생성해주어야한다.
//		System.out.println(add(10,10,30,40));
		System.out.println(add());
		System.out.println(add(10,20,30,40,50));
		System.out.println(add(11,22,33,44,55,66,77,88,99));
		System.out.println("main() 수행종료");
	}
	static int add(int p1, int p2) {
		return p1+p2;
	}
/*	static int add(int[] p) {
		int sum = 0;
		for(int i=0;i<p.length;i++)
			sum +=p[i];
		return sum;
	}*/
	static int add(int...p) {
		int sum = 0;
		for(int i=0;i<p.length;i++)
			sum += p[i];
		return sum;
	}
}
