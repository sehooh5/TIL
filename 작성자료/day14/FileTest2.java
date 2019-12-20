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


