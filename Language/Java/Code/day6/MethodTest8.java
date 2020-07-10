package day6;

public class MethodTest8 {

	public static void main(String[] args) {
		System.out.println("main() 수행시작");	
		char[] returnValue = getName(1);
		System.out.println(returnValue);		//println은 ---> char 변수만 출력해주고 나머지는 참조값 출력해줌
		System.out.println("main() 수행종료");
	}
	static char[] getName(int type) {									
		char ary[];
		if(type==1)
			ary = new char[] {'J','A','V','A'};
		else
			ary = new char[] {'P','Y','T','H','O','N'};
		return ary;				
	}

}
