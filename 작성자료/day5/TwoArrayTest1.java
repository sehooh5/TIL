package day5;

public class TwoArrayTest1 {

	public static void main(String[] args) {
		int[] a1 = new int[10];
		char a2[] = {'��','��','��'};
		double a3[] = new double[5];
		int [][] a4 = new int[5][12];
		
		System.out.println(a4);					//[[I@4e25154f
		System.out.println(a4[0]);				//
		System.out.println(a4[1]);				//
		System.out.println(a4[2]);				//
		System.out.println(a4[3]);				//
		System.out.println(a4[4]);				//
		System.out.println(a4.length);			//5 : 12���� ���Ҹ� ���� 5���� �迭�� ��������Ƿ� 5 (= ���� ����)
		System.out.println(a4[0].length);		//12 : �� ���� �մ� ������ ����
		System.out.println(a4[1].length);
		System.out.println(a4[2].length);
		System.out.println(a4[3].length);
		System.out.println(a4[4].length);
		//		System.out.println(a1.length+" : "+a2.length+" : "+a3.length);
//		System.out.println(a1+ " - " + a1[0]);	//[I@15db9742 - 0		I�� int		@�ڿ��� ������=���� �ּҰ�
//		System.out.println(a3+ " - " + a3[0]);	//[D@6d06d69c - 0.0		D�� double
//		System.out.println(a2+ " - " + a2[0]);	//[C@7852e922 - ��		C�� char
//		System.out.println(a4+ " - " + a4[0]);	//[[I@4e25154f - [I@70dea4e
	}

}
