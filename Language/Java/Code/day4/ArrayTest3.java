package day4;

public class ArrayTest3 {

	public static void main(String[] args) {
		int a1[] = {3,10,2,9,5,11,12,1};
		int max;	
		// a1 배열변수에 할당된 배열의 요소중 최댓값
		max = a1[0];
		for(int i=1;i<a1.length;i++)            //첫번째 원소는 이미 담겼으니 i=1부터 시작
			if(a1[i]>max)						// a1[i]=10>3
				max = a1[i];					// max = 10
			System.out.println("최댓값 : "+max);
		
		//  a1 배열변수에 할당된 배열의 요소중 최댓값
		int min;
		min = a1[0];
		for(int i=1;i<a1.length;i++)
			if(a1[i]<min)
				min = a1[i];
		System.out.println("최소값 : "+min);
	}

}
