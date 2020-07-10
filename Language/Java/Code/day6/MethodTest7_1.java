package day6;

public class MethodTest7_1 {

	public static void main(String[] args) {
		String result = "";						// String result; 는 초기화가 안되어 R-value 로 사용이 불가능하다
		for(int i=0;i<args.length;i++)
			result +=args[i];					//result - result + args[i] 이므로 여기서 R-value 사용 되기 때문에
		System.out.println(result);
	}

}
