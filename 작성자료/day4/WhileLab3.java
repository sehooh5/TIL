package day4;

public class WhileLab3 {

	public static void main(String[] args) {
		
		int sam;
		int num = 0;
			
		while(true) {
			sam = (int)(Math.random()*31);
		if (sam<=26&&sam>0) {
		System.out.print((char)(sam+64));
		System.out.println("("+sam+")");
		}else break;
		num++;
		}
		System.out.println("수행횟수는 "+ num +" 번입니다");
	}
}