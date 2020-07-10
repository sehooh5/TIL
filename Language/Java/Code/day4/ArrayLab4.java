package day4;

public class ArrayLab4 {

	public static void main(String[] args) {
		int num[] = new int[10];
		char word[] = new char[10];
	
		for(int i=0;i<=num.length-1;i++) {
			num[i] = (int)(Math.random()*26)+1;
			if(i<num.length-1)
				System.out.print(num[i]+",");
			else 
				System.out.println(num[i]);	
		}
		for(int i=0;i<=num.length-1;i++) {
			word[i] = (char)(num[i]+64);
			if(i<word.length-1)
				System.out.print(word[i]+",");
			else 
				System.out.println(word[i]);	
		}
		}
//		word[i] = (char)(num[i]+64);
//		for(int f=num.length;f<num.length;f++)
//			System.out.print(num[i]+",");
	}	


