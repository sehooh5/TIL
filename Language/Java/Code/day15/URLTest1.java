package day15;
//웹크롤링 : 웹상 존재하는 콘텐츠를 수집하는 작업
//웹스크래핑 : 필요한 자료를 parse 하여 뽑아내는 작업
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class URLTest1 {
	public static void main(String[] args) throws Exception {
		URL url = new URL("https://movie.naver.com/"); //생성자를 통해 생성
		InputStream is = url.openStream();//openStream으로 읽어올 수 있다
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		//한줄씩 읽으며 출력,,,여기서 UTF-8로 바꿔줘야 안꺠진다.
		String line = null;
		while (true) {
			line = br.readLine();
			if (line == null)
				break;
			System.out.println(line);
		}
	}
}
