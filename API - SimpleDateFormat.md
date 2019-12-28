### API - SimpleDateFormat, LocalDate

```java
package day15;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatTest {

	public static String timeToStrDate(long time) {
		DateFormat formatter = 
				new SimpleDateFormat("yyyy-MM-dd");//원하는 방식으로 지정가능
		return formatter.format(time);
	}

	public static Date parseStrDate(String strDate) throws ParseException {
		DateFormat formatter = 
				new SimpleDateFormat("yyyy년 MM월 dd일");//읽어들이기도 가능
		return formatter.parse(strDate);//내용을 읽어가면서 파악=parsing
	}

	public static void main(String[] args) throws ParseException {
		System.out.println(timeToStrDate(new Date().getTime()));
    //시간불러옴
		System.out.println(parseStrDate("2019년 12월 25일"));
    //날짜 입력	
	}
}
```



```java
package day15;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AdvancedDateTest {

	public static void main(String[] args) {
		LocalDate currentDate = LocalDate.now();    // 컴퓨터의 현재 날짜 정보를 저장한 LocalDate 객체를 리턴한다.
		LocalDate targetDate = LocalDate.of(2020, 1, 1); //읽기
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd E");//E=요일
		String text1 = currentDate.format(formatter);
		String text2 = targetDate.format(formatter);
		LocalDate parsedDate = LocalDate.parse("2019 12 25 수", formatter);
		String text3 = parsedDate.format(formatter);
		System.out.println(text1);
		System.out.println(text2);
		System.out.println(text3);
	}

}
```

