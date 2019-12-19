package day4;

public class BreakTest2 {

	public static void main(String[] args) {
			gugu: for(int dan=1;dan<=9;dan++) {
			for(int num=1;num <=9; num++) {
				if(dan*num>30) 
					break gugu;				///break 는 뒤에 라벨을 줄 수 있다.
				System.out.print(dan+"x"+num+"="+dan*num+"\t");
			}
			System.out.println();

		}
		System.out.println();				//추가해줘야 줄바꿈
		System.out.println("구구단 출력 종료!");
	}		
}
