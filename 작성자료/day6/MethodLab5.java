package day6;

public class MethodLab5 {

	public static void main(String[] args) {
//		int p=0;
		int r1[] = powerArray(2);
		int r2[] = powerArray(3);
		int r3[] = powerArray(4);
		for(int i=0;i<r1.length;i++)
			System.out.print(i<(r1.length-1)?r1[i]+",":r1[i]);
//			p = i=+i

		
	}
	public static int[] powerArray(int p) {
		int result[] = new int[10];
			for(int i =0;i<result.length;i++)
				result[i]=i+=i;
				
		return result;
	}
}
