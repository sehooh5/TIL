### FileWriter-달력 출력예제

```java
package day14.copy;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.GregorianCalendar;

public class FileOutExam {
   public static void main(String[] args) throws IOException{
   
	   
	  GregorianCalendar gc = new GregorianCalendar();

      
	  String day = null;
	  int dayNum = gc.get(GregorianCalendar.DAY_OF_WEEK);
	  
	  switch(dayNum) {
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


      String path = "C:/iotest";
      File isDir = new File(path);
      if (!isDir.exists()) {
      	isDir.mkdirs();
           }	


	  try (FileWriter writer = new FileWriter("c:/iotest/today.txt");){ 
    	  gc = new GregorianCalendar(2019,11,20);
    	  writer.write(String.format("%s %d%s %d%s %d%s\r\n", "오늘은", gc.get(GregorianCalendar.YEAR),
    			  "년",gc.get(GregorianCalendar.MONTH)+1,"월",gc.get(GregorianCalendar.DATE),"일입니다."));

    	  writer.write("오늘은 ");
    	  writer.write(day);
    	  writer.write("요일 입니다.\r\n");

    	  gc = new GregorianCalendar(1991,1,8);
    	  
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
//          }  
      }
    }
}
```



```java
package day14.copy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FileOutExam2 {
	public static void main(String[] args) {
		String path = "C:/iotest/";
		String filename = "today.txt";
		File isDir = new File(path);
        if (!isDir.exists()) {
        	isDir.mkdirs();
        }
        
        try(FileWriter writer = new FileWriter(path + filename);){
        	Calendar c = Calendar.getInstance();
        	       	
        	writer.write(String.format("오늘은 %tY년 %tm월 %td일입니다.\n", c, c, c));
        	writer.write(String.format("오늘은 %ta요일입니다.\n", c));
        	
        	c = new GregorianCalendar(2019, 12, 20);
        	writer.write(String.format("저는 %ta요일에 태어났습니다.\n", c));
        	
        	System.out.println("저장이 완료되었습니다.");
        	
        }catch(IOException e) {
        	System.out.println(e.getMessage());
        	System.out.println("파일에 저장하는 동안 오류가 발생했습니다.");
        }
	        
	}
}

```

