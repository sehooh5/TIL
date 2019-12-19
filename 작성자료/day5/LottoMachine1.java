package day5;

public class LottoMachine1 {

	public static void main(String[] args) {
		int lotto[] = new int[6];
		int num;									//반복문을 저장하는 변수는 밖에 선언.
		int i;
		System.out.print("오늘의 로또 번호 - ");
		for(i=0;i<lotto.length;i++) {
			num = (int)(Math.random()*45)+1;		//난수를 꺼내는건 반복문 안에 있어야한다. 
			lotto[i] = num;
			for(int n=0;n<i;n++) {
			if(lotto[i]==lotto[n]) { 
				i--; 									//?????
				break;
				}
			}
			if(i!=lotto.length-1)
			System.out.print(lotto[i]+",");
			else System.out.print(lotto[i]);
//			for(i = 0; i<lotto.length;i++){
//			if(i != lotto.length-1) System.out.print(lotto[i]+",");
//			else System.out.print(lotto[i]);
		}
	}

}
/////////////////////프로세스////////////////////////////
// 	1. 난수 추출 - 반복문
//	2. 똑같은 번호가 있는지 체크하기 - 반복문
//	3. 넣기 또는 넣지 않고 다음 반복으로 넘어가기