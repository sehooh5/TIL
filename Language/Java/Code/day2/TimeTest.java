package day2;

public class TimeTest {

	public static void main(String[] args) {
		
		double time = 32150;
		int hour = (int)time/3600;
		int minute = (int)time%3600/60;
		int sec = (int) time%3600%60 ;
		
		
		System.out.print(hour + "�ð� ");
		System.out.print(minute + "�� ");
		System.out.print(sec + "��");
	}

}
