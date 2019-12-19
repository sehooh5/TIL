package day7;

public class MethodTest10 {

	public static void main(String[] args) {
		int p1[] = {20,10,14,30};
		System.out.println("p1 ������ �� : "+p1);
		printArray(p1);									//p1 ���Ҹ� �־ �迭�� ����Ʈ
		int[] result = updateArray1(p1);				//updateArray1�� p1�� ���ҵ��� �� ���ο� result �迭 �ۼ�
		printArray(result);
		printArray(p1);
		System.out.println("----------------");	
		updateArray1(p1);								//updateArray1 �� ���� ���͵� ���ڴ� ������ �ʴ´�
		printArray(p1);
		System.out.println("----------------");
		updateArray2(p1);								//updateArray2 �� ���� ������ ���ڰ� ���Ѵ�.
		printArray(p1);
	}
	static void printArray(int[]p2) {					//�迭�� ����Ʈ���ִ� �޼���
		for(int data:p2)
			System.out.printf("%d ",data);
		System.out.printf("\n");
	}
	static int[] updateArray1(int [] p2) {				//�迭�� ���ҿ� 2�� �����ִ� �޼���
		System.out.println("p2 ������ �� : "+p2);
		int [] result = new int[p2.length];
		for(int i=0;i<p2.length;i++)
			result[i] = p2[i]*2;
		return result;
	}
	static void updateArray2(int [] p3) {
		System.out.println("p3 ������ �� : "+p3);
		for(int i=0;i<p3.length;i++)
			p3[i] = p3[i]*2;
	}
}	
