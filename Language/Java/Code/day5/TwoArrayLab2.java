package day5;

public class TwoArrayLab2 {

	public static void main(String[] args) {
		char[][] alpha= {{'B','C','A','A'},
						 {'C','C','B','B'},
						 {'D','A','A','D'}
						 };
		int[] num = new int [4];
			for(int i=0;i<alpha.length;i++) {
				for(int j=0;j<alpha[i].length;j++) {
					switch(alpha[i][j]) {
					case 'A' :num[0]++;
					break;
					case 'B' :num[1]++;
					break;
					case 'C' :num[2]++;
					break;
					default :num[3]++;
					}
				}
			}
			System.out.printf("A �� %d�� �Դϴ�.\n",num[0]);
			System.out.printf("B �� %d�� �Դϴ�.\n",num[1]);
			System.out.printf("C �� %d�� �Դϴ�.\n",num[2]);
			System.out.printf("D �� %d�� �Դϴ�.\n",num[3]);
	}

}
