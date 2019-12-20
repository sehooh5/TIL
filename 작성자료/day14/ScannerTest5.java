package day14.copy;
import java.util.*;
public class ScannerTest5 {
   public static void main(String args[]) {
      String str1 = "I love Java";
      Scanner scan = new Scanner(str1);
      while(scan.hasNext())
          System.out.println(scan.next());
      scan.close();
      String str2 = "I:love:Java";
      scan = new Scanner(str2);
      scan = scan.useDelimiter(":");//useDelimiter 사용으로 토큰들로 나누어 끊어 읽을 수 있다.
      while(scan.hasNext())
          System.out.println(scan.next());
      scan.close();      
      String str3 = "1 fish    2 fish red fish blue fish";
      scan = new Scanner(str3);
      scan = scan.useDelimiter("\\s*fish\\s*");	//정규표현식
      System.out.println(scan.nextInt());	//* = 0개 이상 : ab* b있는거 다 찾음
      System.out.println(scan.nextInt());	// \s = 공백
      System.out.println(scan.next());
      System.out.println(scan.next());      
      scan.close();
   }
}





