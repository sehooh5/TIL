package day2;

public class SwitchLab1 {

	public static void main(String[] args) {

		int grade=(int)(Math.random()*6+1);
		

		switch (grade) {
		case 1:
		case 2:
		case 3:
			System.out.println(grade + " �г��� ���г��Դϴ�.");
		break;
		default : System.out.println(grade + " �г��� ���г��Դϴ�.");
		
		}
		
		for(int num=5;num > 0; --num)
			System.out.println (num);
	}

}
