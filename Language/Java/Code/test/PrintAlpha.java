package test;

public class PrintAlpha {
	public static void main(String[] args) {
		char alpah = 'A';

		for (int rowNum = 1; rowNum <= 5; rowNum++) {
			for (int colNum = 1; colNum <= rowNum; colNum++)
				System.out.print(alpah++);
			System.out.println();
		}

	}
}
