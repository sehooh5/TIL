# FileOut_Exam

```java
package day14.copy;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.GregorianCalendar;

public class FileOutExam {
   public static void main(String[] args) throws IOException{
   
	   
	  GregorianCalendar gc = new GregorianCalendar();//생성자 호출

      
	  String day = null;
	  int dayNum = gc.get(GregorianCalendar.DAY_OF_WEEK);
	  
	  switch(dayNum) {//숫자로 출력되므로 숫자를 요일로 뽑아주는 과정
	  case 1:
		  day = "일";
		  break;
	  case 2:
		  day = "월";
		  break;
	  case 3:
		  day = "화";
		  break;
	  case 4:
		  day = "수";
		  break;
	  case 5:
		  day = "목";
		  break;
	  case 6:
		  day = "금";
		  break;
	  case 7:
		  day = "토";
		  break;
	  }


      String path = "C:/iotest";//path 경로 설정
      File isDir = new File(path);//파일 생성
      if (!isDir.exists()) {//만약 파일이 없으면!
      	isDir.mkdirs();			//파일을 생성하겠다 mkdirs
    
      }	

					//여기 writer 에 true가 쓰이면 이어서 작성, 없으면 덮어쓰기
	  try (FileWriter writer = new FileWriter("c:/iotest/today.txt");){ 
    	  gc = new GregorianCalendar(2019,11,20);//날짜 초기화
    	  writer.write(String.format("%s %d%s %d%s %d%s\r\n", "오늘은", gc.get(GregorianCalendar.YEAR),
    			  "년",gc.get(GregorianCalendar.MONTH)+1,"월",gc.get(GregorianCalendar.DATE),"일입니다."));

    	  writer.write("오늘은 ");
    	  writer.write(day);
    	  writer.write("요일 입니다.\r\n");

    	  gc = new GregorianCalendar(1991,1,8);//날짜 다시한번 초기화
    	  
    	  gc.get(GregorianCalendar.DAY_OF_WEEK);
    	  writer.write("옹셍홍는 ");
    	  writer.write(day);
    	  writer.write("요일에 태어났습니다.");
    	  System.out.println("저장이 완료되었습니다.");
      } catch (IOException ioe) {
          System.out.println("저장하는 동안 오류가 발생했습니다.");
//      } finally {
//          try {
//          	if (writer != null)
//          		writer.close();
//          } catch (Exception e) {
//          	System.out.println("파일을 닫는동안 오류 발생!!");
//          }  //Try 괄호 안에 설정하여 try catch 안써줘도 된다.
      }
    }
}
```

