package day5;

public class TwoArrayLab1_1 {

	public static void main(String[] args) {
		int[][] nums = {{10,12,14,16},
						{18,20,22,24},
						{26,28,30,32},
						{34,36,38,40}
						};
		System.out.printf("1행 1열의 데이터 : %d\n",nums[0][0]);
		System.out.printf("3행 4열의 데이터 : %d\n",nums[2][3]);
		System.out.printf("행의 갯수 : %d\n",nums.length);
		System.out.printf("열의 갯수 : %d\n",nums[0].length);		
		System.out.printf("3행의 데이터들 : ");
			for(int i=0;i<nums.length;i++) 
		System.out.print(nums[2][i]+" ");
			System.out.println();
		System.out.print("2열의 데이터들 : ");
			for(int i=0;i<nums.length;i++) 
		System.out.print(nums[i][1]+" ");
		System.out.println();
		System.out.print("오른쪽 대각선 데이터들 : ");
			for(int j=0;j<nums[j].length;j++) {
				for(int i=nums.length-1;i>=0;i--) {
			if(i+j==(nums.length-1))
		System.out.print(nums[j][i]+" ");
		}
		}
		
	
	}

}
