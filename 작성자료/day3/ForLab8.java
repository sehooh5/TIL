package day3;

public class ForLab8 {

	public static void main(String[] args) {
		
		char eng = 'A';										//  final ����� ---> ���(���� ������ ����)
		for(int rowNum=1;rowNum<=5;rowNum++) {
			for(int colNum=1;colNum <=rowNum; colNum++)
				System.out.print(eng++);
			System.out.println();
		
			
		}
	}		
}
