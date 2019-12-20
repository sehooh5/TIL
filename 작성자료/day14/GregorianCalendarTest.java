package day14.copy;
import java.util.*;
public class GregorianCalendarTest {
	public static void main(String[] args) {
		GregorianCalendar gc = new GregorianCalendar();
		System.out.println(gc.get(Calendar.DAY_OF_YEAR));
		System.out.println(gc.get(Calendar.YEAR));
		System.out.println(gc.get(Calendar.MONTH)); //+1 해주어야한다
		System.out.println(gc.get(Calendar.DAY_OF_MONTH));
		System.out.println(gc.get(Calendar.DATE));
		System.out.println(gc.get(Calendar.DAY_OF_WEEK_IN_MONTH));	//몇번째주?
		System.out.println(gc.get(Calendar.DAY_OF_WEEK));	//몇번째 일?
		
		System.out.printf("%tY\n", gc);	//연
		System.out.printf("%ty\n", gc);	//10의자리만
		System.out.printf("%tm\n", gc);	//0~12로 표현하는 월
		System.out.printf("%tb\n", gc);	//~월
		System.out.printf("%tH\n", gc);	//시간
		System.out.printf("%tM\n", gc);	//분
		System.out.printf("%tS\n", gc);	//초
		System.out.printf("%tA\n", gc);	//요일
		System.out.printf("%ta\n", gc);	//축약형 요일
	}
}
