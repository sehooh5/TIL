package day6;

public class MethodLab4 {

	public static void main(String[] args) {
		int[] array1 = {10,20,30};
		int[] array2 = {100,500,300,200,400};
		int[] array3 = {1,10,3,4,5,8,7,6,9,2};

//		maxNumArray(array1);
		System.out.printf("가장 큰 값은 %d 입니다.\n",maxNumArray(array1));
//		maxNumArray(array2);
		System.out.printf("가장 큰 값은 %d 입니다.\n",maxNumArray(array2));
//		maxNumArray(array3);
		System.out.printf("가장 큰 값은 %d 입니다.\n",maxNumArray(array3));
		
	}
	public static int maxNumArray(int[] p) {
		int max=0;
		for(int i=0;i<p.length;i++)
			if(p[i]>max)
				max = p[i];
		return max;
	}
}
