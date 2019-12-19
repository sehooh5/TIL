package day6;

public class MethodLab1 {

	public static void main(String[] args) {
		printCI('@',3);
		System.out.println();
		printCI('%',4);
		System.out.println();
		printCI('A',5);
		System.out.println();
		printCI('°¡',3);
	}
	static void printCI(char mun, int num) {								
		for(int i=0;i<num;i++)
			System.out.print(mun);
	}

}
