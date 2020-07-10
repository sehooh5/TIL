# 실습-AJAX,JASON,SERVLET,DATE



### SERVLET,DATE

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

@WebServlet("/basket1")
public class BasketServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("pid");//pid의 값을 불러옴
		
        //날짜 정의해주는 부분
		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter formatter = 			
            		DateTimeFormatter.ofPattern("yyyyMMddHHmm");

		//파일 생성해주는 부분
        FileWriter writer = null;
		String path = "C:/logtest";
		File isDir = new File(path);

		if (!isDir.exists()) {
			isDir.mkdirs();
		}
		try {
			writer = new FileWriter("c:/logtest/mylog.txt", true);
            //true 값 주어야 append 시켜준다.
			writer.write(currentDate.format(formatter) + "\t" + id + "\r\n");
		} catch (IOException ioe) {
			System.out.println("파일로 출력할 수 없습니다.");
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (Exception e) {
				System.out.println("파일을 닫는동안 오류 발생!!");
			}
		}
        //중간에 : 줘야 JSON 형식
		out.print("{\"pid\": \"" + id + "\"}");
		out.close();
	}

}

```



### AJAX, JSON

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>하나가 먹다 둘이 죽는 칵테일 리스트</title>
<style>
img {
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
</style>
</head>

<body>
	<h2>하나가 먹다 둘이 죽는 칵테일 리스트</h2>
	<img src="http://70.12.115.175:8000/edu/images/p001.png"
		data-pid="p001" onclick='act("p001");'>
	<img src="http://70.12.115.175:8000/edu/images/p002.png"
		data-pid="p002" onclick='act("p002");'>
	<img src="http://70.12.115.175:8000/edu/images/p003.png"
		data-pid="p003" onclick='act("p003");'>
	<img src="http://70.12.115.175:8000/edu/images/p004.png"
		data-pid="p004" onclick='act("p004");'>
	<img src="http://70.12.115.175:8000/edu/images/p005.png"
		data-pid="p005" onclick='act("p005");'>
	<br>
	<img src="http://70.12.115.175:8000/edu/images/p006.png"
		data-pid="p006" onclick='act("p006");'>
	<img src="http://70.12.115.175:8000/edu/images/p007.png"
		data-pid="p007" onclick='act("p007");'>
	<img src="http://70.12.115.175:8000/edu/images/p008.png"
		data-pid="p008" onclick='act("p008");'>
	<img src="http://70.12.115.175:8000/edu/images/p009.png"
		data-pid="p009" onclick='act("p009");'>
	<img src="http://70.12.115.175:8000/edu/images/p010.png"
		data-pid="p010" onclick='act("p010");'>
	<div id="output"></div>

	<script>
        //AJAX , JSON 구현 부분
		function act(p) {
			var request = new XMLHttpRequest();
			request.onload = function() {
				if (request.status == 200) {
					var str = request.responseText;
					var jsObj = JSON.parse(str);//텍스트들 JSON으로 변경
					var textArea = document.getElementById('output');
                    //jsObj 제이슨 객체의 pid 값을 가져온다!
					textArea.innerHTML = jsObj.pid;
				}
			};
			console.log(p);
			request.open('GET', "/sedu/basket1?pid=" + p, true);
			request.send();
		}
	</script>

</body>

</html>
```

