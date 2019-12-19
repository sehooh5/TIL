package day6;

public class MethodLab3 {

	public static void main(String[] args) {
		for(int j=0;j<5;j++) {
			getRandom(10);
			if(j<4)
			System.out.print(getRandom(10)+",");
			else System.out.print(getRandom(10));
			}
			System.out.println();

		for(int j=0;j<5;j++){
			getRandom(10,20);
			if(j<4)
				System.out.print(getRandom(10,20)+",");
				else System.out.print(getRandom(10,20));
			}
	}
	public static int getRandom(int n) {
		int rand1=(int)(Math.random()*n)+1;
		return rand1;
	}
	public static int getRandom(int n1, int n2) {
		int differ =n2-n1;
		int rand2=(int)(Math.random()*(differ+1))+(differ);
		return rand2;
	}
}
