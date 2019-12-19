package day2;

public class RandomTest {

	public static void main(String[] args) {

//		// int number = (int)(Math.random()*10);
//		
		double rand1 = Math.random();
//		System.out.println(rand1);
//		
//		double rand2 = rand1*10;
//		System.out.println(rand2);
//		
//		int rand3 = (int)rand2;
//		System.out.println(rand3);
//		
//		// rand1 을 가지고 1부터 6 사이의 난수를 만드는 식을 구현하여
//		// sixNum 변수에 담아 출력하기
//		
//		int rand4 = rand3+1;
//		System.out.println(rand4);
//		
//		if (0<rand4<7) {
//			System.out.println(rand4);
		
		int sixNum = (int)(rand1*6)+1;
		System.out.println(sixNum);
		
		int lottoNum = (int)(rand1*45)+1;
		System.out.println(lottoNum);
		
		
		
		
	}

}
