package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Service
public class getTrandsSearchResult {
	public String[][] getTrands(String query) throws UnsupportedEncodingException {
		String app_key = "818df44d90dfebbf8d7718a985d919b7";
		String reqURL = "https://dapi.kakao.com/v2/search/blog?query=" + URLEncoder.encode(query, "UTF-8");
		String result = "";
		String blogData[][] = new String[10][6];
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "KakaoAK " + app_key);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			//��ǲ ��Ʈ�� ��ü�� ������ �� 2��° �ƱԸ�Ʈ�� UTF-8�� ��� �޾ƿ� �� �ѱ��� �ȱ���.
			String line = "";
			while ((line = br.readLine()) != null) {
				result += line;
			}
			//result = URLEncoder.encode(result,"UTF-8");
			System.out.println("response body : " + result);

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			//System.out.println("elelelelel!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:" + element.getAsJsonArray().size());
			//String result = element.getAsJsonObject("")
			//x = element.getAsString();
			
			String title[] = new String[result.length()];
			String content[] = new String[result.length()];
			String doc_url[] = new String[result.length()];
			String blogname[] = new String[result.length()];
			String thumbnail[] = new String[result.length()];
			String datetime[] = new String[result.length()];
			
			for(int i = 0 ; i<10;i++) {
			title[i] = element.getAsJsonObject().get("documents").getAsJsonArray().get(i).getAsJsonObject().get("title").toString();
			content[i] = element.getAsJsonObject().get("documents").getAsJsonArray().get(i).getAsJsonObject().get("contents").toString();
			doc_url[i] = element.getAsJsonObject().get("documents").getAsJsonArray().get(i).getAsJsonObject().get("url").toString();
			blogname[i] = element.getAsJsonObject().get("documents").getAsJsonArray().get(i).getAsJsonObject().get("blogname").toString();
			thumbnail[i] = element.getAsJsonObject().get("documents").getAsJsonArray().get(i).getAsJsonObject().get("thumbnail").toString();
			datetime[i] = element.getAsJsonObject().get("documents").getAsJsonArray().get(i).getAsJsonObject().get("datetime").toString();
			
			blogData[i][0] = title[i];
			blogData[i][1] = content[i];
			blogData[i][2] = doc_url[i].substring(1);
			blogData[i][3] = blogname[i];
			blogData[i][4] = thumbnail[i].substring(1);
			blogData[i][5] = datetime[i];
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return blogData;
	}
}
