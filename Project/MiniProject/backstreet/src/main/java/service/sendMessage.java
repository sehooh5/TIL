package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Service
public class sendMessage {
	 public String sendMeMessage(String acessToken) throws UnsupportedEncodingException {

	      String acess_token = acessToken;
	      String reqURL = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
	      String result = "";
	      System.out.println("센드미메세지");
	
	      try {
	         URL url = new URL(reqURL);
	         HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	         conn.setRequestMethod("POST");
	         conn.setDoOutput(true);
	         conn.setRequestProperty("Authorization", "Bearer " + acess_token);
	       
	         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
	         StringBuilder sb = new StringBuilder();
	         sb.append("template_object={\"object_type\": \"text\",\r\n" + 
	         		"        \"text\": \"김세정의 골목상권에 오신 것을 환영합니다!\",\r\n" + 
	         		"        \"link\": {\r\n" + 
	         		"            \"web_url\": \"http://70.12.115.176/backstreet/main\",\r\n" + 
	         		"            \"mobile_web_url\": \"http://70.12.115.176/backstreet/main\"\r\n" + 
	         		"        },\r\n" + 
	         		"        \"button_title\": \"모바일로 이용하기\"}");
	         bw.write(sb.toString());
	         bw.flush();

	         int responseCode = conn.getResponseCode();
	         System.out.println("responseCode : " + responseCode);

	         BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         String line = "";

	         while ((line = br.readLine()) != null) {
	            result += line;
	         }
	         System.out.println("response body : " + result);

	         JsonParser parser = new JsonParser();
	         JsonElement element = parser.parse(result);

	       //  result = element.getAsJsonObject().get("result_code").getAsString();

	       //  System.out.println("result(unlinkId) : " + result);

	         br.close();
	         bw.close();
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
			
	      String msg = result;
	      return msg;
	   }
}
