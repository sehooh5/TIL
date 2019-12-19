package day5;

public class TwoArrayTest1 {

	public static void main(String[] args) {
		int[] a1 = new int[10];
		char a2[] = {'가','나','다'};
		double a3[] = new double[5];
		int [][] a4 = new int[5][12];
		
		System.out.println(a4);					//[[I@4e25154f
		System.out.println(a4[0]);				//
		System.out.println(a4[1]);				//
		System.out.println(a4[2]);				//
		System.out.println(a4[3]);				//
		System.out.println(a4[4]);				//
		System.out.println(a4.length);			//5 : 12개의 원소를 갖는 5개의 배열이 만들어지므로 5 (= 행의 길이)
		System.out.println(a4[0].length);		//12 : 각 열에 잇는 원소의 길이
		System.out.println(a4[1].length);
		System.out.println(a4[2].length);
		System.out.println(a4[3].length);
		System.out.println(a4[4].length);
		//		System.out.println(a1.length+" : "+a2.length+" : "+a3.length);
//		System.out.println(a1+ " - " + a1[0]);	//[I@15db9742 - 0		I는 int		@뒤에는 참조값=논리적 주소값
//		System.out.println(a3+ " - " + a3[0]);	//[D@6d06d69c - 0.0		D는 double
//		System.out.println(a2+ " - " + a2[0]);	//[C@7852e922 - 가		C는 char
//		System.out.println(a4+ " - " + a4[0]);	//[[I@4e25154f - [I@70dea4e
	}

}
