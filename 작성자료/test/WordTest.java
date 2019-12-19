package test;

public class WordTest {
	public static void main(String[] args) {
		WordCount a = new WordCount();
		System.out.println("결과 1  : "+a.counter("java jdbc sql html5 css javascript", true));
		System.out.println("결과 2  : "+a.counter("java jdbc sql html5 css javascript", false));
	}
}
//- word = true 인 경우 => 단어의 개수를 리턴한다.
//- word = false 인 경우 => 문자열의 개수를 리턴한다.(공백도 포함)
class WordCount {	
	int counter(String text,boolean word) {
		int a;
		if(word == true) {
			String[] ary = text.split(" "); 
			a = ary.length;
		}
		else 
			a = text.length();
				
		return a;
	}
		
}
