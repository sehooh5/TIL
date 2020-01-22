# Session 실습

### BasketServlet2

```java
package core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/basket2")
public class BasketServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("pid");

		char last = id.charAt(id.length() - 1);
		/* System.out.println("last값 : "+last); */
		int lastNum = last - 48;// char의 값을 숫자로 바꿀때 47빼줘야..?

		HttpSession session = request.getSession();
		
		if(id.equals("del")) {
			session = request.getSession(false);
				if(session != null) 
					session.invalidate();
				out.print("{\"msg\":\"상품이 모두 삭제되었습니다\"}");
		}else {
		
		
		if (session.getAttribute("cock") == null)
			session.setAttribute("cock", new int[10]);
		int[] list = (int[]) session.getAttribute("cock");
		/* System.out.println("lastNum값 : "+lastNum); */
		if (lastNum != 0)
			list[lastNum - 1] += 1;
		else
			list[lastNum + 9] += 1;

		LocalDateTime log = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

		FileWriter writer = null;
		String path = "c:/logtest";
		File isDir = new File(path);
		if (!isDir.exists()) {
			isDir.mkdirs();
		}
		try {
			writer = new FileWriter("c:/logtest/mylog.txt", true);
			writer.write(log.format(formatter) + "\t" + id + "\r\n");
		} catch (IOException e) {
			System.out.println("파일로 출력할 수 없습니다.");
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (Exception ee) {
				System.out.println("파일 닫는동안 오류 발생!");
			}
		}
		

		out.print("{");
		for(int i = 0; i < list.length-1; i++) 
			out.print("\"p00"+Integer.toString(i+1)+"\":" + list[i] + ",");
		out.print("\"p010\":" + list[9]);
		out.print("}");
		}

		out.close();
	}

}
```



### productlogsession

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>하나가 먹다 둘이 죽는 칵테일 리스트</title>
<style>
.c {
	border: 3px dotted #5d85d5;
	box-shadow: 10px 10px #5d85d5;
	width: 150px;
	height: 150px;
	margin: 10px;
	border-radius: 10px 10px 10px 10px;
}

div {
	text-align: center;
}

img {
	width: 150px;
	height: 150px;
}
</style>
</head>

<body>
	<h2>하나가 먹다 둘이 죽는 칵테일 리스트</h2>
	<img src="http://70.12.115.175:8000/edu/images/p001.png" class="c"
		data-pid="p001" onclick='aaa("p001");'>
	<img src="http://70.12.115.175:8000/edu/images/p002.png" class="c"
		data-pid="p002" onclick='aaa("p002");'>
	<img src="http://70.12.115.175:8000/edu/images/p003.png" class="c"
		data-pid="p003" onclick='aaa("p003");'>
	<img src="http://70.12.115.175:8000/edu/images/p004.png" class="c"
		data-pid="p004" onclick='aaa("p004");'>
	<img src="http://70.12.115.175:8000/edu/images/p005.png" class="c"
		data-pid="p005" onclick='aaa("p005");'>
	<br>
	<img src="http://70.12.115.175:8000/edu/images/p006.png" class="c"
		data-pid="p006" onclick='aaa("p006");'>
	<img src="http://70.12.115.175:8000/edu/images/p007.png" class="c"
		data-pid="p007" onclick='aaa("p007");'>
	<img src="http://70.12.115.175:8000/edu/images/p008.png" class="c"
		data-pid="p008" onclick='aaa("p008");'>
	<img src="http://70.12.115.175:8000/edu/images/p009.png" class="c"
		data-pid="p009" onclick='aaa("p009");'>
	<img src="http://70.12.115.175:8000/edu/images/p010.png" class="c"
		data-pid="p010" onclick='aaa("p010");'>
	<ul id="output"></ul>
	<img src="http://70.12.115.175:8000/edu/images/gra.png" data-pid="del"
		onclick='aaa("del");'>
	<script>
		
		function aaa(ee) {
			var request = new XMLHttpRequest();
			request.onload = function() {
				if (request.status == 200) {
					var str = request.responseText;
					var jsObj = JSON.parse(str);
					var textArea = document.getElementById('output');
					if (jsObj.msg) {//삭제할때 메시지 내보내기
						textArea.innerHTML = jsObj.msg;
					
					} else {
						textArea.innerHTML = '';
						for ( var i in jsObj)
							if(jsObj[i]!=0){
							textArea.innerHTML += "<li>" + i + " 상품 "
									+ jsObj[i] + " 개</li>";
					}
					}
				}//***jsObj.키이름 or jsObj["키이름"]
			};
			request.open('GET', '/sedu/basket2?pid=' + ee, true);
			request.send();
		};
		
	</script>
</body>

</html>
```

