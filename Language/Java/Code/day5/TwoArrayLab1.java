package day5;

public class TwoArrayLab1 {

	public static void main(String[] args) {
		int[][] nums = new int[4][4];
		int first = 10;
		
		for(int i =0; i<nums.length;i++) {
			for(int j = 0; j<nums[i].length ; j++) {
				nums[i][j]=first;						///�߿�
				first = first + 2;
			}
		}
		System.out.println("1�� 1���� ������ : "+nums[0][0]);
		System.out.println("3�� 4���� ������ : "+nums[2][3]);
		System.out.println("���� ���� : "+nums.length);
		System.out.println("���� ���� : "+nums[0].length);
		System.out.print("3���� �����͵� : ");
		for(int i =0; i<nums.length;i++) {
		System.out.print(nums[2][i]+" ");
		}
		System.out.println();
		System.out.print("2���� �����͵� : ");
		for(int i =0; i<nums.length;i++) {
			System.out.print(nums[i][1]+" ");
		}
		System.out.println();
		System.out.print("���� �밢�� �����͵� : ");
		for(int i=0;i<nums.length;i++) {
			for(int j=0;j<nums[i].length;j++) {
				if(i==j)
				System.out.print(nums[i][j]+" ");
			}
		}
		System.out.println();
//		System.out.print("������ �밢�� �����͵� : ");
//		for(int i=nums.length-1;i>=0;i--) {
//			for(int j=0;j<nums[i].length;j++) {
//				if(i+j==(nums.length-1))
//				System.out.print(nums[i][j]+" ");
		System.out.print("������ �밢�� �����͵� : ");
		for(int j=0;j<nums.length;j++) {
			for(int i=nums[j].length-1;i>=0;i--) {
		if(i+j==(nums.length-1))
	System.out.print(nums[j][i]+" ");
			}
		}
	}

}
