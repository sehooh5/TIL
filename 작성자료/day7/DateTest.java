package day7;
/////////////Ŭ���� �̸��� Date/////////
import java.util.Date;					//java.util ��Ű���� �ִ� Date Ŭ������ �����ͼ� ���
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
