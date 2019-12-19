package day5;

public class TwoArrayLab3 {

	public static void main(String[] args) {
		int[][] exam = new int[4][];
		exam[0] = new int[5];
		exam[1] = new int[3];
		exam[2] = new int[4];
		exam[3] = new int[9];
		int num1=10;
		int num2=5;
		int num3=11;
		int num4=9;
		int sum1=0, sum2=0, sum3=0, sum4=0;

			for(int j = 0;j<exam[0].length;j++) {
				exam[0][j] = num1;
				num1=num1+10;
				sum1+=exam[0][j];
		}
		System.out.println("1행의 합은 "+sum1+" 입니다.");
			for(int j = 0;j<exam[1].length;j++) {
				exam[1][j] = num2;
				num2=num2+5;
				sum2+=exam[1][j];
		}
		System.out.println("2행의 합은 "+sum2+" 입니다.");
		for(int j = 0;j<exam[2].length;j++) {
			exam[2][j] = num3;
			num3=num3+num3*j;
			sum3+=exam[2][j];
		}
		System.out.println("3행의 합은 "+sum3+" 입니다.");
		for(int j = 0;j<exam[3].length;j++) {
			exam[3][j] = num4;
			num4=num4-1;
			sum4+=exam[3][j];
		}
		System.out.println("3행의 합은 "+sum4+" 입니다.");
////////////////전부 넣어서 하는 방법////////////		
		System.out.println();
		int[][] array = new int[4][];
		array[0] = new int[] {10,20,30,40,50};
		array[1] = new int[] {5,10,15};
		array[2] = new int[] {11,22,33,44};
		array[3] = new int[] {9,8,7,6,5,4,3,2,1};
		int sum[] = new int[4];
		for(int i=0;i<array.length;i++) {
			for(int j=0;j<array[i].length;j++)
				sum[i] +=array[i][j];
		System.out.println(i+1 + "행의 합은 "+sum[i]+" 입니다.");
	}
	}
}
