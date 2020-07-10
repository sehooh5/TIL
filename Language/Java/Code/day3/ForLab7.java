package day3;

public class ForLab7 {

	public static void main(String[] args) {
		
		final char STAR = '*';										//  final 예약어 ---> 상수(값이 고정된 변수)
		for(int rowNum=1;rowNum<=7;rowNum++) {
			for(int colNum=1;colNum<rowNum; colNum++) 
				System.out.print(" ");
			for(int colNum=7;colNum >=rowNum; colNum--) 
				System.out.print(STAR);
			System.out.println();
		}
	}		
}
