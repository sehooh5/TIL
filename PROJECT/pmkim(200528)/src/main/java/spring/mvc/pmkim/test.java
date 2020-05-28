package spring.mvc.pmkim;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class test {

	public static void main(String[] args) {
		Map<String, Object> resultM = new HashMap<String, Object>();

        String end_cursor = commandMap.get("end_cursor").toString();  //해당 파라미터는 밑에서 설명해드릴게요!
 
        try {
            String apiURL = "https://www.instagram.com/explore/tags/gs25/?__a=1&max_id="+end_cursor; // json 결과

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }

            JSONParser jsonParser = new JSONParser();
            org.json.simple.JSONObject jsonObj = (org.json.simple.JSONObject) jsonParser.parse(res.toString());
            jsonObj = (org.json.simple.JSONObject) jsonObj.get("graphql");
            jsonObj = (org.json.simple.JSONObject) jsonObj.get("hashtag");

            resultM.put("OUT_DATA", jsonObj);
            System.out.println("인스타// "+resultM);
        } catch (Exception e) {
            System.out.println(e);
        }

	}

}
