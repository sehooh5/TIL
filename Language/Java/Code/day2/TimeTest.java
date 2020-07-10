package day2;

public class TimeTest {

	public static void main(String[] args) {
		
		double time = 32150;
		int hour = (int)time/3600;
		int minute = (int)time%3600/60;
		int sec = (int) time%3600%60 ;
		
		
		System.out.print(hour + "시간 ");
		System.out.print(minute + "분 ");
		System.out.print(sec + "초");
	}

}
