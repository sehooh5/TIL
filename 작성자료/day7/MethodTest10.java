package day7;

public class MethodTest10 {

	public static void main(String[] args) {
		int p1[] = {20,10,14,30};
		System.out.println("p1 변수의 값 : "+p1);
		printArray(p1);									//p1 원소를 넣어서 배열을 프린트
		int[] result = updateArray1(p1);				//updateArray1에 p1의 원소들이 들어가 새로운 result 배열 작성
		printArray(result);
		printArray(p1);
		System.out.println("----------------");	
		updateArray1(p1);								//updateArray1 에 들어갔다 나와도 숫자는 변하지 않는다
		printArray(p1);
		System.out.println("----------------");
		updateArray2(p1);								//updateArray2 에 들어갔다 나오면 숫자가 변한다.
		printArray(p1);
	}
	static void printArray(int[]p2) {					//배열을 프린트해주는 메서드
		for(int data:p2)
			System.out.printf("%d ",data);
		System.out.printf("\n");
	}
	static int[] updateArray1(int [] p2) {				//배열의 원소에 2씩 곱해주는 메서드
		System.out.println("p2 변수의 값 : "+p2);
		int [] result = new int[p2.length];
		for(int i=0;i<p2.length;i++)
			result[i] = p2[i]*2;
		return result;
	}
	static void updateArray2(int [] p3) {
		System.out.println("p3 변수의 값 : "+p3);
		for(int i=0;i<p3.length;i++)
			p3[i] = p3[i]*2;
	}
}	
