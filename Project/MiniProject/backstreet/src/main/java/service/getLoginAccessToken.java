package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Service
public class getLoginAccessToken {
    
    public String getAccessToken (String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";
        
        try {
            URL url = new URL(reqURL);
            // URL Ŭ������ URL�� ���� �����͸� �������� ���� ��� �޼��带 ������.
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //HttpURLConnection Ŭ������ ������ ����� �����ϴµ� ���̴� Ŭ���� .
            
            
            //    POST ��û�� ���� �⺻���� false�� setDoOutput�� true��
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            // ������ ����� �� ��, ���� POST, GET�� ���־��µ�,
            // POST ����� ��� HttpURLConnection.setDoOutput(true)�� �����ϰ� �Ķ���͸� �ٵ� ��Ƽ� �����ϰ� ��.
            // HttpURLConnection.setRequestMethod("GET") (default �� GET) �� �����ѵ�
            // HttpURLCOnnection.setDoOutPut(true)�� ȣ���ϸ� setRequestMethod() �� �ڵ����� POST�� �����. 

            //    POST ��û�� �ʿ�� �䱸�ϴ� �Ķ���� ��Ʈ���� ���� ����
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=818df44d90dfebbf8d7718a985d919b7");
            sb.append("&redirect_uri=http://70.12.115.176:8000/backstreet/login");
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();
            
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
			  
			  access_Token = element.getAsJsonObject().get("access_token").getAsString();
			  refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
			  
			  System.out.println("access_token : " + access_Token);
			  System.out.println("refresh_token : " + refresh_Token);
			 
            
            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
        return access_Token;
    }
}
