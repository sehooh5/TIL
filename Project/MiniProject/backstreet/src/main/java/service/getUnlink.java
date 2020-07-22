package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Service
public class getUnlink {
   public String getKakaoUnlink(String acessToken, String targetId) throws UnsupportedEncodingException {
      String Admin_key = "0295666ead98109c91f892aa904a22dd";
      String acess_token = acessToken;
      String reqURL = "https://kapi.kakao.com/v1/user/unlink";
      String result = "";
      //로그아웃하기위해 getLoginAccessToken, getLogoutUserId 필요..
      try {
         URL url = new URL(reqURL);
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();

         conn.setRequestMethod("POST");
         conn.setDoOutput(true);
         conn.setRequestProperty("Authorization", "Bearer " + acess_token);
         conn.setRequestProperty("Authorization", "KakaoAK " + Admin_key);

         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
         StringBuilder sb = new StringBuilder();
         sb.append("target_id_type=user_id");
         sb.append("&target_id=" + targetId);
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

         result = element.getAsJsonObject().get("id").getAsString();

         System.out.println("result(unlinkId) : " + result);

         br.close();
         bw.close();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
		
      String unlinkId = result;
      return unlinkId;
   }
}