package day2;

public class IfTest5 {

	public static void main(String[] args) {
			
		int score = (int)(Math.random()*101);
		if(score >= 90)
			System.out.println(score + " : A grade");
		else if(score >= 80)		
			System.out.println(score + " : B grade");
		else if(score >= 70)
			System.out.println(score + " : C grade");
		else if(score >= 60)
			System.out.println(score + " : D grade");
		else
			System.out.println(score + " : F grade");	
		System.out.println("수행종료!!");
		
//		int score = (int)(Math.random()*101);
//		if(score >= 90)
//			System.out.println(score + " : A grade");
//		if(score >= 80 && score < 90)		
//			System.out.println(score + " : B grade");                          // if만 썻을때
//		if(score >= 70&& score < 80)
//			System.out.println(score + " : C grade");
//		if(score >= 60&& score < 70)
//			System.out.println(score + " : D grade");
//		else
//			System.out.println(score + " : F grade");	
//		System.out.println("수행종료!!");
		
		
	}
}
