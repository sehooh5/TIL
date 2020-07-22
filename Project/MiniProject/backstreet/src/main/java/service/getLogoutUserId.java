package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Service
public class getLogoutUserId {
    
    public String getUserId (String accessToken) {
        String id = "";
        String reqURL = "https://kapi.kakao.com/v1/user/logout";
        
        try {
            URL url = new URL(reqURL);
            // URL Ŭ������ URL�� ���� �����͸� �������� ���� ��� �޼��带 ������.
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //HttpURLConnection Ŭ������ ������ ����� �����ϴµ� ���̴� Ŭ���� .
            
            
            //    POST ��û�� ���� �⺻���� false�� setDoOutput�� true��
            conn.setRequestMethod("POST");
          // conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "bearer "+accessToken);
            // ������ ����� �� ��, ���� POST, GET�� ���־��µ�,
            // POST ����� ��� HttpURLConnection.setDoOutput(true)�� �����ϰ� �Ķ���͸� �ٵ� ��Ƽ� �����ϰ� ��.
            // HttpURLConnection.setRequestMethod("GET") (default �� GET) �� �����ѵ�
            // HttpURLCOnnection.setDoOutPut(true)�� ȣ���ϸ� setRequestMethod() �� �ڵ����� POST�� �����. 

			/*
			 * // POST ��û�� �ʿ�� �䱸�ϴ� �Ķ���� ��Ʈ���� ���� ���� BufferedWriter bw = new
			 * BufferedWriter(new OutputStreamWriter(conn.getOutputStream())); StringBuilder
			 * sb = new StringBuilder(); sb.append("Authorization: Bearer "+ accessToken);
			 * bw.write(sb.toString()); bw.flush();
			 */
            
            //    ��� �ڵ尡 200�̶�� ����
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
 
            //    ��û�� ���� ���� JSONŸ���� Response �޼��� �о����
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
            
            //    Gson ���̺귯���� ���Ե� Ŭ������ JSON�Ľ� ��ü ����
            //    pom.xml�� gson ���� �±׸� �߰��ؾ� �����ȳ�
			  JsonParser parser = new JsonParser(); 
			  JsonElement element = parser.parse(result);
			  
			  id = element.getAsJsonObject().get("id").getAsString();
			  
			  System.out.println("id : " + id);
			 
            
			/*
			 * br.close(); bw.close();
			 */
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
        return id;
    }
}
