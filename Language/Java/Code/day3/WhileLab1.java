package day3;

public class WhileLab1 {

	public static void main(String[] args) {
		
		int good = (int)(Math.random()*6)+5;
		int whil =1;
		
		System.out.println("[ for 결과 ]");
		for(int fo = 1;fo<=good;fo++) {
			System.out.println(fo+" -> "+fo*fo);
		}
		System.out.println("[ while 결과 ]");
	
		while(whil<=good) {
			System.out.println(whil+" -> "+whil*whil);
			whil++;
		}
		
	}

}
