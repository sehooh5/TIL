package day4;

public class BreakTest2 {

	public static void main(String[] args) {
			gugu: for(int dan=1;dan<=9;dan++) {
			for(int num=1;num <=9; num++) {
				if(dan*num>30) 
					break gugu;				///break �� �ڿ� ���� �� �� �ִ�.
				System.out.print(dan+"x"+num+"="+dan*num+"\t");
			}
			System.out.println();

		}
		System.out.println();				//�߰������ �ٹٲ�
		System.out.println("������ ��� ����!");
	}		
}
