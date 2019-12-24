package day15;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CopyExam {

	public static String timeToStrDate(long time) {
		DateFormat formatter = 
				new SimpleDateFormat("yyyy_MM_dd");
		return formatter.format(time);
	}
	
	public static void main(String[] args) {
	       try (FileReader reader = 
	    		   new FileReader("C:\\Users\\student\\Desktop\\SEHO\\실습예제\\sample.txt");
	        	   BufferedReader br = new BufferedReader(reader);){          
	        	   String data;
	               while (true) {
	                   data = br.readLine();
	                   if (data == null)
	                       break;   
	                   
	                   try (FileWriter  writer 
	                		   = new FileWriter("C:\\Users\\student\\Desktop\\SEHO\\실습예제\\sample_"+timeToStrDate(new Date().getTime()))+""){  
	                       writer.write(data);            
	                       
	                   } catch (IOException ioe) {
	                       System.out.println("파일로 출력할 수 없습니다.");
	                   } 
	                   
	               }
	               
	           } catch (FileNotFoundException fnfe) {
	               System.out.println("파일이 존재하지 않습니다.");
	           } catch (IOException ioe) {
	               System.out.println("파일을 읽을 수 없습니다.");
	           } 
	       
		

	}

}
