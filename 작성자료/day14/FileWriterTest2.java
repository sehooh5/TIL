package day14.copy;
import java.io.*;
public class FileWriterTest2 {
    public static void main(String args[]) { //try catch with resource구문
    	//괄호 안에 넣어주면 try가 끝나는 시점에서 자동으로 close 가 된다.
    	//FileWriter 구문을 try와 catch 사이에 넣어주면 사용 가능하다.
        try (FileWriter  writer = new FileWriter("c:/iotest/output2.txt");){  
        
            char arr[] = { '객', '체', '지', '향', '언', '어', 'J', 'a', 'v', 'a' };          
            for (int cnt = 0; cnt < arr.length; cnt++)
                writer.write(arr[cnt]);
            writer.write('\n');	//FileWriterTest와 다른점..옛 버전은 개행처리 안된다
            writer.write(arr);
            writer.write('\n'); 
            writer.write("OCJP 시험 대비!!");
            writer.write('\n');  
            System.out.println("파일에 출력 완료!!");
        } catch (IOException ioe) {
            System.out.println("파일로 출력할 수 없습니다.");
        } 
    }
}
