package day5;

public class TwoArrayLab1_1 {

	public static void main(String[] args) {
		int[][] nums = {{10,12,14,16},
						{18,20,22,24},
						{26,28,30,32},
						{34,36,38,40}
						};
		System.out.printf("1�� 1���� ������ : %d\n",nums[0][0]);
		System.out.printf("3�� 4���� ������ : %d\n",nums[2][3]);
		System.out.printf("���� ���� : %d\n",nums.length);
		System.out.printf("���� ���� : %d\n",nums[0].length);		
		System.out.printf("3���� �����͵� : ");
			for(int i=0;i<nums.length;i++) 
		System.out.print(nums[2][i]+" ");
			System.out.println();
		System.out.print("2���� �����͵� : ");
			for(int i=0;i<nums.length;i++) 
		System.out.print(nums[i][1]+" ");
		System.out.println();
		System.out.print("������ �밢�� �����͵� : ");
			for(int j=0;j<nums[j].length;j++) {
				for(int i=nums.length-1;i>=0;i--) {
			if(i+j==(nums.length-1))
		System.out.print(nums[j][i]+" ");
		}
		}
		
	
	}

}
