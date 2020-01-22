# JSP 

### Java Server Page

[TOC]

---

- 서블릿 개발자 " 출력하는 부분이 너무 불편하고 실행 전 오타를 체크할 수 없다"

  ​						"자바를 모르면 작성하기가 어렵다"

- 이런 불편함을 해소해주기 위해 만들어진 것이 **JSP** 

- **JSP** = HTML + JSP태그 + Java // 코드실행되면 Servlet으로 변경된다! 

- MVC 개발패턴 : Servlet(요청) + JSP(응답)

- 기본적으로 Service 기능으로 GET과 POST 를 구분하지 않고 사용 가능



### 사용방법

- JSP 는 **내장객체**를 사용하면 된다. 내장 객체 **9개**! (즉, 변수만 기억하면 된다)

  ```
  request, response, session, out, application, config, exception, page, pageContext
  ```

  

  ##### HTML 문서 안에 정해진 **JSP 태그**로 사용한다

  ```
  - <%@		%> : 지시자 태그
  - <%!		%> : 선언문 태그 (멤버선언, 메서드 정의)
  - <%=		%> : 표현식 태그 (표현),식만 올 수 있다,인용부호안에사용O
  - <%		%> : 수행문 태그 (자바 코드를 작성하는 태그, 젤 많이 사용)
  - <%--	  --%> : 주석 태그
  
  
  -가운데 세개는 자바 코드 작성에 사용하는 태그들
  ```

- **액션 태그**

  ```
  <jsp:include />,<jsp:forward  />, <jsp:parapm	/>
  ```

- **커스텀 태그**

  - JSTL
  - 우리는 XML, core 태그 위주로 공부할 것



---

#### exam1 - 기본 작성 방법 localdatetime

```jsp
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>JSP 맛봐볼려?</h1>
<hr>
<h3>현재 시간 정보 : <%= java.time.LocalDateTime.now() %></h3>
<h2>혹은</h2>
<h3>현재 시간 정보 : <%= LocalDateTime.now() %></h3>
</body>
</html>
```



#### exam2 - 멤버 로컬변수 지정, 기본적 html 과 작성 방법

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%! int member_v = 0; %>
 <!-- 지역변수 선언때는 느낌표 안씀 -->
<% 
	int local_v = 0; 
	member_v += 100;
	local_v += 100;
%>
<!-- 표현식 태그에는 식만 사용 가능!! ; 불가, Rvalue만-->
<ul>
<li>멤버 변수 : <%= member_v %><li>
<li>로컬 변수 : <%= local_v %><li>
</ul>
</body>
</html>
```





#### exam3 - 표현식 태그 연습

```jsp
<!-- 표현식 태그 연습 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>표현식 태그 테스트해볼깝숑?</h1>
<hr>
<% int num = (int)(Math.random()*10+1); %>
<ul>
	<li><%= 100 %></li>
	<li><%= num %></li>
	<li><%= num++ %></li>
	<li><%= ++num %></li>
	<li><%= num*999 %></li>
	<li><%= "가나다라바마사".length() %></li>
	<li><%= num%2 == 0 %></li>
	<!-- request가 내장객체여서 바로 사용 가능 -->
	<li><%= request.getParameter("stname") %></li>
	<li><%= new java.util.Date(session.getCreationTime()) %></li>
	<li><%= application.getServerInfo() %></li>
	<li><%= application.getContextPath() %></li>
</ul>
</body>
</html>
```





#### exam4 / exam4_jsp.html

```jsp
<!-- 표현식 태그 연습 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 특수문자 사용방법 + amp; -->
<h1>Query 문자열 추출(Get&amp;POST)</h1>
<hr>
<%
	if(request.getMethod().equals("POST")){
		request.setCharacterEncoding("utf-8");
	}
%>
<h2>오마에 나마에와 <%= request.getParameter("name") %> 데쓰까!?</h2>
<!-- 요청한 페이지 HTML의 URL 을 출력된다, jsp 표현식은 인용부호 안에서도 사용 가능-->
<a href="<%= request.getHeader("referer") %>">입력 화면으로</a>
</body>
</html>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>exam4.jsp 요청하는 페이지 이구만만구리구리</h1>
<hr>
<a href="/sedu/jspexam/exam4.jsp?name=옹셍홍">JSP를 GET방식 요청</a>
<hr>
<form method="GET" action="/sedu/jspexam/exam4.jsp">
	이름<input type="text" name="name">
	<input type="image" src="http://70.12.115.175:8000/edu/images/gra.png" width="150">
</form>
	<hr>
<form method="POST" action="/sedu/jspexam/exam4.jsp">
	이름<input type="text" name="name">
	<input type="image" src="http://70.12.115.175:8000/edu/images/gra.png" width="150">
</form>
</body>
</html>
```





#### exam5 - 요청한 페이지로 돌아가는 법, 띄어쓰기 주의

```jsp
<!-- 표현식 태그 연습 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 특수문자 사용방법 + amp; -->
<h1>선언문 태그</h1>
<hr>
<%!
	int multiply(int n1, int n2){
	return n1*n2;
}
%>
<%
	int result = 0;
	if(request.getMethod().equals("GET")){
%>
	<h3>숫자 2개를 입력하세요</h3>
	<form method="post" action="/sedu/jspexam/exam5.jsp">
	남바 완  <input type="number" name="no1"><br>
	남바 투  <input type="number" name="no2"><br>
	<input type="submit">
	</form>
<%
	}else{
		int no1 = Integer.parseInt(request.getParameter("no1"));
		int no2 = Integer.parseInt(request.getParameter("no2"));
		result = multiply(no1,no2);
	
%>
	<h2>결과는 바로오오~~~~~ : <%= result %></h2>
	<!-- 요청한 페이지 HTML의 URL 을 출력된다, jsp 표현식은 인용부호 안에서도 사용 가능-->
	<a href="<%= request.getHeader("referer") %>">입력 화면으로</a>
<%  } %>	

</body>
</html>
```





#### 번호 맞추기 실습 lotto

```jsp
///////////////////////////////////////////LottoServlet1.java
package core;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lotto1")
public class LottoServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		String num = request.getParameter("num");
		int sNum = Integer.parseInt(num);
		
		int rNum = (int)(Math.random()*6+1);
		System.out.println("전달된 값 : "+ sNum + ", 추출된 값 : " + rNum);
		
		String url;
		if (sNum == rNum) {
			url="/jspexam/success.jsp";
		}else {
			url="/jspexam/fail.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request,response);
	}

}


///////////////////////////////////////////lottoForm.html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div{
	width : 550px;
	height : 180px;
	background : linear-gradient(to top, #d5f4e6 , #b3c6ff);
	box-shadow : 5px 5px #d5f4e6;
	padding : 10px;
	border-radius: 40px 40px 60px 80px;
	text-align : center;
}
</style>
</head>
<body>
<div>
<h1>오이오이 로또 번호 맞춰보라구우~</h1>
<hr>
<form method="GET" action="/sedu/lotto1">
1부터 6까지의 숫자를 입력 하시게 : <input type="number" min="1" max="6" name="num">
<input type="image" 
src="http://70.12.115.175:8000/sedu/jspexam/clover.png" width="30">
</form>
</div>
</body>
</html>


///////////////////////////////////////////success.jsp
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div{
	width : 350px;
	height : 100px;
	background : linear-gradient(to top, #d5f4e6 , #b3c6ff);
	box-shadow : 5px 5px #d5f4e6;
	padding : 10px;
	border-radius: 40px 40px 60px 80px;
	text-align : center;
}
span{
	color : yellow;
	text-shadow : 2px 2px black;
}
</style>
</head>
<body>

<% 
	LocalDateTime cDate = LocalDateTime.now();
	DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("HH시 mm분");
%>
<div><h2>
<%= cDate.format(fomatter) %>에 로또 당첨!!<br>
<span>FLEX</span> 해보렸지모야~</h2>
</div><br>
<img src="http://70.12.115.175:8000/edu/images/gra.png" width="150">

</body>
</html>



///////////////////////////////////////////fail.jsp
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div{
	width : 430px;
	height : 130px;
	background : linear-gradient(to top, #d5f4e6 , #b3c6ff);
	box-shadow : 5px 5px #d5f4e6;
	padding : 10px;
	border-radius: 40px 40px 60px 80px;
	text-align : center;
	text-color : white;
	text-shadow : 2px 2px white;
}
span{
	color : yellow;
	text-shadow : 2px 2px black;
}
</style>
</head>
<body>

<% 
	LocalDateTime cDate = LocalDateTime.now();
	DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("HH시 mm분");
%>
<div><h2>
<%= cDate.format(fomatter) %>에 당첨 실패!!<br>
젊은 친구 인생은 다시 도전하는거야~<br><span>빠끄</span>~눌러</h2>
</div><br>
<a href="<%= request.getHeader("referer") %>"><input type="image" src="http://70.12.115.175:8000/sedu/jspexam/back.PNG" width="150"></a>
<!-- <form>
<input type="image" src="http://70.12.115.175:8000/sedu/jspexam/back.PNG" width="150">
</form> -->

</body>
</html>
```

