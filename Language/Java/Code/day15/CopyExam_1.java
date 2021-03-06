package day15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CopyExam_1 {

	public static void main(String args[]) {
		FileReader fr = null;
		FileWriter fw = null;
		try {
			String path = "C:/iotest";
	        
			File isDir = new File(path);
	        if (!isDir.exists()) {
	        	isDir.mkdirs();
	        }
			
			fr = new FileReader(path+"/sample.txt");
			BufferedReader br = new BufferedReader(fr);
			LocalDate currentDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			fw = new FileWriter(path+"/"+currentDate.format(formatter)+".txt",true);
			String data = "";
			while (true) {
				data = br.readLine();
				if(data == null)
					break;
				fw.write(data+"\r\n");			
			}
		}catch (MalformedURLException e) {
			System.out.println("URL문자열 오류 : "+e.getMessage());
		} catch (IOException e) {
			System.out.println("IO 오류 : "+e.getMessage());
		}  finally {
			try {
				if (fw != null) 
					fw.close();
				if (fr != null) 
					fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}
}

//package day15;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//
//public class CopyExam {
//
//	public static void main(String[] args)  {
//		 FileWriter wt = null;
//	        String path = "C:/iotest";
//	        File isDir = new File(path);
//	        if (!isDir.exists()) {
//	        	isDir.mkdirs();
//	        }
//	
//		try (FileReader reader = new FileReader("c:/iotest/sample.txt");
//		    	   BufferedReader br = new BufferedReader(reader);)  {        
//		    	   String data;
//		    	   wt = new FileWriter("C:/iotest/sample_yyyy_mm_dd.txt",true); 
//			             //객체 while 안에 x
//		           while (true) {
//		               data = br.readLine();
//		               if (data == null)
//		            	 break;
//		               wt.write(data); 
//		           }
//		       System.out.println("저장 완료되었습니다.");
//		       wt.close();
//				} catch (Exception exc) {
//		           System.out.println("처리하는 동안 오류가 발생했습니다.");
//		       }  
//	}
//}