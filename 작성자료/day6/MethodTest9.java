package day6;

public class MethodTest9 {

	public static void main(String[] args) {
		int size = MethodLab3.getRandom(3, 8);
		int p[] = new int[size];
		for(int i = 0; i<p.length;i++)
			p[i] = MethodLab3.getRandom(20);
		for(int data : p)
			System.out.print(data+" ");
		System.out.println();
		boolean flag[] = isEven(p);
		for(boolean data : flag)
//			System.out.print(data==true?"짝수 ":"홀수 ");
			System.out.print(data+" ");
		System.out.println();
	}
	//전달된 배열의 요소값들을 각각 체크하여
	//짝수면 true, 홀수면 false를 저장하는 boolean 타입 배열을 리턴한다
	//이때. 전달된 배열의 크기와 동일한 크기의 boolean 타입의 배열을 생성하여 리턴한다.
	static boolean[] isEven(int[] p) {		
		boolean[] result = new boolean[p.length];	//p.length 길이의 boolean 타입배열 생성
		for (int i=0;i<p.length;i++)
			result[i]= p[i]%2 == 0?true : false;
		return result;
	}

}
