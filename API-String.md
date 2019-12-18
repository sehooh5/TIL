# API-String

```java
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
```

- 문자열.charAt(n) = 문자열에서 n 위치의 캐릭터 값 찾기,,시작은 0부터

- 문자열.toUpperCase() = 소문자 대문자로 변환

- 문자열.substring(n) = n에 있는 값부터 마지막까지 뽑아냄

  문자열.substring(n1,n2) = n1 부터 n2 까지 뽑아냄

- 문자열.indexOf(문자열) = 문자열이 있는 위치를 전달

  문자열.lastIndexOf(문자열) = 문자열이 있는 위치를 뒤에서 부터 전달

- 문자열.replace(char1,char2) = char1 을 char2 로 대체

- 객체.toCharArray(); = 한문자 한문자 캐릭터형으로 담아서 리턴