package day12;
public class StringTest {
	public static void main(String[] args) {
		System.out.println("1".length());    //1			
		System.out.println("가나다".length()); //3
		System.out.println("abc".charAt(1)); //b, 문자열에서 원하는 위치 꺼내는 것 0=첫번째, 1=두번째		
		System.out.println("abc".toUpperCase());  //ABC 	
		String str1 = "ABCDEFGHIJ";
		String str2 = "abcdefgfhij";
		
		System.out.println(str1.substring(4));		// 4부터 뽑아냄, 값 안주면 0 부터     
		System.out.println(str1.substring(0, 3)); 	// 0부터 3 전까지(n,m) = n~m-1		
		System.out.println(str2.indexOf("f"));    	// 5, f가 있는 위치를 전달해라
		System.out.println(str2.lastIndexOf("f")); 	// 7, 뒤에서부터 찾음
		System.out.println(str2.replace('h', 'H')); // h를 H로 대체,,,많이 바꿀거면 StringBuffer	    
		
		String str3 = "java:html5:css3:javascript";
		String[] ary = str3.split(":");   //워드로 스트링에 담아서 리턴
		
		for(int i=0; i < ary.length; i++){
			System.out.println(ary[i]);
		}		
		char ch[] = str3.toCharArray();	// 한문자 한문자 캐릭터형으로 담아서 리턴
		System.out.println(str3.length() + " ---- " + ch.length);
		System.out.println(str3);
		System.out.println(ch);
		for(int i=0; i < ch.length; i++){
			System.out.print(ch[i] + " ");
		}
	}
}