package day5;

public class TwoArrayTest3 {

	public static void main(String[] args) {

		int [][] nums = {
						{10,20,30,40},
						{11,21,31,41},
						{12,22,32,42}
						};
		int sum = 0;							//2���� �迭 �� ���Ҷ���-----------��ø for ���� ���
		for(int i=0;i<nums.length;i++) {
			for(int j=0;j<nums[i].length;j++) {
				sum += nums[i][j];
			}
		}
		System.out.println("��ü �������� �� : "+sum);

		int sumRow=0,sumCol=0;
		// ������ ���� �����͸� �ջ��Ͽ� sumRow�� ���� ( ��ø ��� x )
		for(int k=0;k<nums[nums.length-1].length;k++)
			sumRow += nums[nums.length-1][k];
		// 	�ι�° ���� �����͸� �ջ��Ͽ� sumCol�� ���� ( ��ø ���x )
		for(int l=0;l<nums.length;l++)
			sumCol += nums[l][1];
		System.out.println("������ �� �������� �� : "+sumRow);
		System.out.println("�ι�° �� �������� �� : "+sumCol);
	}

}
