package day7;
/////////////클래스 이름이 Date/////////
import java.util.Date;					//java.util 패키지에 있는 Date 클래스를 데려와서 사용
import java.util.GregorianCalendar;
public class DateTest {
	public static void main(String[] args) {
		Date d=new Date();
		System.out.println(d.toString());
		GregorianCalendar gc = new GregorianCalendar();
		System.out.println(gc.get(GregorianCalendar.DAY_OF_WEEK));
		gc = new GregorianCalendar(1991,1,8);
		System.out.println(gc.get(GregorianCalendar.DAY_OF_WEEK));

	}

}
