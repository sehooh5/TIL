package day5;

public class LottoMachine1 {

	public static void main(String[] args) {
		int lotto[] = new int[6];
		int num;									//�ݺ����� �����ϴ� ������ �ۿ� ����.
		int i;
		System.out.print("������ �ζ� ��ȣ - ");
		for(i=0;i<lotto.length;i++) {
			num = (int)(Math.random()*45)+1;		//������ �����°� �ݺ��� �ȿ� �־���Ѵ�. 
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
/////////////////////���μ���////////////////////////////
// 	1. ���� ���� - �ݺ���
//	2. �Ȱ��� ��ȣ�� �ִ��� üũ�ϱ� - �ݺ���
//	3. �ֱ� �Ǵ� ���� �ʰ� ���� �ݺ����� �Ѿ��