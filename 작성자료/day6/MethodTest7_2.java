package day6;

public class MethodTest7_2 {

	public static void main(String[] args) {
		int result = 0;						
		for(int i=0;i<args.length;i++)
			result +=Integer.parseInt(args[i]);					// String ----> int : Integer.parseInt(String)	
		System.out.println(result);
	}

}
/////////¼ıÀÚ µ¡¼ÀÀ¸·Î ¹Ù²Ù±â//////////////