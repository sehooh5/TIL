package day1;

public class VariableTest {

	public static void main(String[] args) {

		System.out.println(1+2+3+4+5+10);	//25
		System.out.println(1+2+3+4+5-10);	//5
		System.out.println((1+2+3+4+5)*10);	//60
		System.out.println((1+2+3+4+5)/10);	//10.5
		
		
		int result = 1+2+3+4+5;  // result : ����   1+2+3+4+5 : ���ͷ�
		System.out.println(result+10);
		System.out.println(result-10);
		System.out.println(result*10);
		System.out.println(result/10);
	
		char munja = 'A'; //0x41, 65
		
		System.out.println(munja);
		System.out.println(""+munja + munja); // 130
		System.out.println(""+munja + munja); // "" = NULL ���ڿ� , AA

		System.out.println(munja + munja+""); // 130
		
		

	}

}
