package day3;

public class ForTest5 {

	public static void main(String[] args) {
		
		// A-Z ���� ���
		char munja = 'A';
		for(int n=1;n<=26;++n) 
		 System.out.print(munja++);
		 System.out.println("\n------------");
		
		for(munja='A';munja<='Z';++munja) 
			 System.out.print(munja);
			 System.out.println("\n------------");
		
		munja = 'A';
		for(int n=1;n<=26;++n) {
			 System.out.print(munja);
			 munja+=1;
		 }
			 System.out.println("\n------------");

		munja = 'A';
		for(int n=1;n<=26;++n) {
			System.out.print(munja);
			 munja= (char)(munja + 1);          // munja + 1 �� int �� char���� ũ�⿡ ����ȯ ������ �ʿ�
		 }
			 System.out.println("\n------------");		
		
			 
	}
}
