package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;

import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

@Service
public class getConsultingSearchResult {
	  public float[] getResult (String query) throws UnsupportedEncodingException {
	        String app_key = "818df44d90dfebbf8d7718a985d919b7";
	        String reqURL = "https://dapi.kakao.com/v2/local/search/address.json?query="+URLEncoder.encode(query,"UTF-8");
            String result = "";
            float[] coordinate = new float[2];
	        try {
	            URL url = new URL(reqURL);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("GET");
	            conn.setRequestProperty("Authorization", "KakaoAK "+app_key);
	            
	            int responseCode = conn.getResponseCode();
	            System.out.println("responseCode : " + responseCode);
	            //    ��û�� ���� ���� JSONŸ���� Response �޼��� �о����
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
	            String line = "";
	            while ((line = br.readLine()) != null){
	                result += line;
	            }
	            System.out.println("response body : " + result);
	            //    Gson ���̺귯���� ���Ե� Ŭ������ JSON�Ľ� ��ü ����
	            //    pom.xml�� gson ���� �±׸� �߰��ؾ� �����ȳ�
				  JsonParser parser = new JsonParser(); 
				  JsonElement element = parser.parse(result);
				
				// Ư�� Ű �迭 �� - Ư�� index �� ����������
				// element.getAsJsonObject().get("oo").getAsJsonArray().get(0).toString(); ���·� ���
			
				// result= element.getAsJsonObject().get("documents").getAsJsonObject().get("address").getAsJsonObject().get("address_name").getAsString();
				  String x = element.getAsJsonObject().get("documents").getAsJsonArray().get(0).getAsJsonObject().get("x").getAsString();
				  String y = element.getAsJsonObject().get("documents").getAsJsonArray().get(0).getAsJsonObject().get("y").getAsString();
				//  String y = element.getAsJsonObject().get("documents").getAsJsonArray().get(5).toString();
				  System.out.println(x);
				  System.out.println(y);
				  
			        coordinate[0] = Float.parseFloat(x);
					 coordinate[1] = Float.parseFloat(y);
				//  System.out.println(y);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return coordinate;
	    }
}
