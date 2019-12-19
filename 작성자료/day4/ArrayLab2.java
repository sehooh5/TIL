package day4;

public class ArrayLab2 {

	public static void main(String[] args) {
		int ary[] = new int[10];
		int numR;
		int sum=0;
		System.out.print("모든 원소의 값 : ");
		for(int i=0;i<ary.length;i++) {
			numR = (int)(Math.random()*17)+4;
			ary[i] = numR;
			sum += ary[i];
			if(i<ary.length-1) 
			System.out.print(ary[i]+",");
		else 
			System.out.println(ary[i]);
		}
		System.out.println("모든 원소의 합 : "+sum);
	}

}
