package day3;

public class ForLab6 {

	public static void main(String[] args) {
		
		int ran = (int)(Math.random()*6)+5;
		final char ALPHA = '&';										//  final 예약어 ---> 상수(값이 고정된 변수)
		
		for(int rowNum=1;rowNum<=ran;rowNum++) {
			for(int colNum=1;colNum <=rowNum; colNum++)
				System.out.print(ALPHA);
			System.out.println();
		
		}
	}		
}
