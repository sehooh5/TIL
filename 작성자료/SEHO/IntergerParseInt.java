
package SEHO;

import java.util.Scanner;

public class IntergerParseInt {

	public static void main(String[] args) {
		int num = 0;
		
		System.out.print("*�� ����� ������ ���� �Է��ϼ���.>");
		
		Scanner sc = new Scanner(System.in);

		String txt = sc.nextLine();
		num = Integer.parseInt(txt);
		
		for(int i = 0;i<num;i++) {
			for(int j = 0;j<=i;j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		sc.close();
	}

}
