package day12;
public class StringTest {
	public static void main(String[] args) {
		System.out.println("1".length());    //1			
		System.out.println("������".length()); //3
		System.out.println("abc".charAt(1)); //b, ���ڿ����� ���ϴ� ��ġ ������ �� 0=ù��°, 1=�ι�°		
		System.out.println("abc".toUpperCase());  //ABC 	
		String str1 = "ABCDEFGHIJ";
		String str2 = "abcdefgfhij";
		
		System.out.println(str1.substring(4));		// 4���� �̾Ƴ�, �� ���ָ� 0 ����     
		System.out.println(str1.substring(0, 3)); 	// 0���� 3 ������(n,m) = n~m-1		
		System.out.println(str2.indexOf("f"));    	// 5, f�� �ִ� ��ġ�� �����ض�
		System.out.println(str2.lastIndexOf("f")); 	// 7, �ڿ������� ã��
		System.out.println(str2.replace('h', 'H')); // h�� H�� ��ü,,,���� �ٲܰŸ� StringBuffer	    
		
		String str3 = "java:html5:css3:javascript";
		String[] ary = str3.split(":");   //����� ��Ʈ���� ��Ƽ� ����
		
		for(int i=0; i < ary.length; i++){
			System.out.println(ary[i]);
		}		
		char ch[] = str3.toCharArray();	// �ѹ��� �ѹ��� ĳ���������� ��Ƽ� ����
		System.out.println(str3.length() + " ---- " + ch.length);
		System.out.println(str3);
		System.out.println(ch);
		for(int i=0; i < ch.length; i++){
			System.out.print(ch[i] + " ");
		}
	}
}