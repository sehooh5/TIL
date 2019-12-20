### File 생성

```
package day14.copy;

import java.io.File;
import java.util.Scanner;
public class FileTest1 {
   public static void main(String[] args){
      Scanner scan = new Scanner(System.in);
	  System.out.println("생성하려는 디렉토리명을 작성하세요 : ");
	  String dirName = scan.nextLine();
	  File f = new File(dirName);
      if(f.exists()) {
    	  System.out.println(dirName + "명의 디렉토리가 존재합니다.");
      } else {
    	  if(f.mkdirs()) {
    		  System.out.println(dirName + "명의 디렉토리가 생성되었습니다.");
    	  } else {
    		  System.out.println(dirName + "명의 디렉토리 생성에 실패했습니다.");
    	  }
      }
      scan.close();	     
    }
}
```

```
package day14.copy;

import java.io.*;
import java.util.Date; 
public class FileTest2 { 
   public static void main(String[] args) throws Exception{       
      File dir = new File(args[0]); 
      if(!dir.exists() || !dir.isDirectory()) {//디렉토리 존재하지 않거나 디렉토리가 아니면
         System.out.println("유효하지 않은 디렉토리입니다.");
         return;
      } 
      long time = new Date().getTime();	//1570년 1월부터의 밀리세컨드 
//    String newFileName = "c:/Temp/temp"+time+".tmp";        
      String newFileName = "temp"+time+".tmp";        
      File file = new File(dir,newFileName);      
      if (file.createNewFile())		//파일 생성 메서드
         System.out.println(newFileName+"명의 파일이 생성되었습니다.");
      else
         System.out.println(newFileName+"명의 파일이 이미 존재합니다.");
   }  
}
```

