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

- JSP - - - > Servlet 으로 변환된다!



### 사용방법

- JSP 는 **내장객체**를 사용하면 된다. 내장 객체 **9개**! (즉, 변수만 기억하면 된다)

  ```
  request, response, session, out, application, config, exceptionisErrorPage에서만 사용 가능, page, pageContext
  ```

  - application : servlet 갯수마다 context가 생긴다 (우린 edu, sedu 두개)
  - exception : isErrorPage에서만 사용 가능

  ##### HTML 문서 안에 정해진 **JSP 태그**로 사용한다

  ```
  - <%@		%> : 지시자 태그
  - <%!		%> : 선언문 태그 (멤버선언, 메서드 정의)
  - <%=		%> : 표현식 태그 (표현),식만 올 수 있다,인용부호안에사용O
  - <%		%> : 수행문 태그 (자바 코드를 작성하는 태그, 젤 많이 사용)
  - <%--	  --%> : 주석 태그
  
  
  -가운데 세개는 자바 코드 작성에 사용하는 태그들
  ```

  - <%@ page 속성명="속성값".........%> 

    - 인용부호("") 꼭 줘야한다

    - language, contentType, pageEncoding, isErrorPage, session, buffer,

      import, isELIgnored

  - <%@ include file="포함하려는 대상 파일의 로컬 URL" %> : include 지시자

    - 반복되는 파일 사용할때
    - 포함하는 위치 : 이 지시자 태그가 사용된 위치
    - 포함하는 시기 : JSP를 Servlet으로 변환하기 전
    - 포함하는 대상 : html, jsp, jspf(다른 jsp에 포함되는 jsp file 일때 사용)
    - 서블릿은 상대 jsp를 포함해서 변환되기때문에 **한 개만 생성** : 동적포함

- **액션 태그**

  - 기능이 정해져있는 태그

  ```
  <jsp:include /> :같이 응답하고싶으면 사용, ***서블릿 각자 생성,정적포함
  <jsp:forward  /> : 요청, 재지정
  <jsp:parapm	/> : 포워드하는 대상한테 데이터 전달하는 태그
  ```

- **커스텀 태그**

  - JSTL
  - 우리는 XML, core 태그 위주로 공부할 것
  
- **EL (Expression Language)**

  - <u>표현식 태그</u>를 간단하게 구현 하게 해주는 것 = JSP를 훨씬 간단하게 구현가능
  - jsp 파일들을 변환하는 실습
  - ${header.referer} : header 는 요청 header 객체를 불러오는..
  - ${header.user-agent} : ~~오류남~~ ---> ${header["user-agent"]}
  - **EL 에서 연산자**: 변수명.xxx
    - 변수의 참조 대상이 일반 Java 객체이면 getxxx()를 호출한 결과
    - 변수의 참조 대상이 Map 객체(Hash)이면 get("xxx") 메서드를 호출한 결과 : xxx는 get() 메서드의 변수명으로 사용된다.

#### MVC

- 요청은 servlet /  응답은 jsp
- 기본적으로 요청과 응답을 나누어주는 방법
- **모델** : 어플리케이션의 정보(데이터) 담당, **Java 객체(VO,DTO,DAO)**
  - Domain Model : 정보를 보관하여 전달(VO 클래스로 만듬)
  - Service Model 
    - Business Obj 
    - DAO 
  - xxxVO (Value Object) : 값을 보관하는 용도의 객체
  - xxxDAO (Data Access Object)  : DB연동기능(JDBC)을 지원하는 객체
  - xxxService(xxxBiz) -Service Object : 이런 저런 서비스 로직을 지원하는 객체
- **뷰** : 텍스트, 체크박스 항목 등과 같은 사용자 인터페이스 요소 담당, **JSP**
- **컨트롤러** : 데이터와 비즈니스 로직 사이의 상호동작 관리, 어플리케이션 기능담당, **Servlet**

![image-20200123174025928](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200123174025928.png)

#### DAO

- jdbc 드라이버 연동하려면 WEB-INF -> lib 에 ojdbc6.jar 을 넣어줘야 연동 가능



#### 객체공유

- forward 는 객체공유 가능
- redirect 는 가능할 때도 있고 아닐 때도 있다
- Page Scope : Servlet 또는 JSP 가 수행되는 동안만 유효한 객체
- **RequestScope** : **HttpServletRequest** 객체에 보관하여 응답 될 때까지 보관
- **Session Scope** : **HttpSession** 객체에 보관하여 session 있는동안 보관
-  **Application Scope** : 
  - **ServletContext** 서버 기동될때까지 보관(가장 길다)
  - 모든 클라이언트(서블릿)에 의해 공유
  - 웹정보, 서버정보 추출 가능
- **메서드**
  - public void setAttribute(String key, Object value)
    - 세 방법 모두 배열로 사용할 수 있는 컨테이너 객체이다
  - public Object get Attribute(String key)
  - public void removeAttribute(String key)

![image-20200123173706387](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200123173706387.png)

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







#### exam6

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>요청 방식에 따른 처리</title>
<style>
	input {
		margin : 3px;
	}
</style>
</head>
<body>
	<% if (request.getMethod().equals("GET")) { %>
		<h2>원하는 칼라와 날짜를 선택하세요</h2>
		<!-- 자기자신을 요청.....action 태그 생략 가능 -->
		<form method="post" action="/sedu/jspexam/exam7.jsp">
			칼라 : <input type="color"  name="fcolor" ><br>
			날짜 : <input type="date"  name="fdate"><br>
			<input type="submit" value="전송">
		</form>
		<!-- post 방식으로 요청하면 작동 --> 
	<% } else { %>	
			<script>
				document.body.style.backgroundColor =
					         '<%= request.getParameter("fcolor") %>';
			</script>
			<h2>선택 날짜는 <%= request.getParameter("fdate") %> 이네요..</h2>
	<% } %>
</body>
</html>
```







#### exam7

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>요청 방식에 따른 처리</title>
<style>
	input {
		margin : 3px;
	}
</style>
</head>
<body>
	<% if (request.getMethod().equals("GET")) { %>
		<h2>원하는 칼라와 날짜를 선택하세요</h2>
		<!-- 자기자신을 요청.....action 태그 생략 가능 -->
		<form method="post" action="/sedu/jspexam/exam7.jsp">
			칼라 : <input type="color"  name="fcolor" ><br>
			날짜 : <input type="date"  name="fdate"><br>
			<input type="submit" value="전송">
		</form>
		<!-- post 방식으로 요청하면 작동 --> 
	<% } else { %>	
			<script>
				document.body.style.backgroundColor =
					         '<%= request.getParameter("fcolor") %>';
			</script>
			<h2>선택 날짜는 <%= request.getParameter("fdate") %> 이네요..</h2>
	<% } %>
</body>
</html>
```







#### reservation.jsp

```jsp
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>펜션 예약해보자</title>
<style>
	input {
		margin : 3px;
	}
</style>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
%>
		<h1> <%= request.getParameter("name") %>님의 예약 내용</h1>
		<hr>
		<ul>
		<li> 룸 : <%= request.getParameter("rType") %></li>
		<li> 추가 요청 사항 : <% 
		String[] reQ = request.getParameterValues("request");
		if (reQ == null) {
			out.print("추가 요청사항이 없습니다.");	
		}
		else {
			for (int i=0 ; i<reQ.length ; ++i) {
				if (i == reQ.length -1) {
					out.print(reQ[i]);
					break;
				}
				out.print(reQ[i] +",");
			}
		}%>
		</li>
		<li> 예약날짜 : 
		<% 
		String date = request.getParameter("date");
		LocalDate newD = LocalDate.parse(date);
		DateTimeFormatter date1 = DateTimeFormatter.ofPattern(
				"yyyy년 MM월 dd일");
		String date2 = newD.format(date1);
		out.print(date2);
		%></li>
		</ul>
		<br>
		<br>
		<a href="<%= request.getHeader("referer") %>"><input type="image" src="http://70.12.115.175:8000/sedu/jspexam/back.PNG" width="150"></a>
</body>
</html>
```







#### exam8-errorPage 설정

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!-- 실행하다 에러 났을때만 에러페이지 기능이 작동한다 -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="text-align:center">
<h2>오류 발생했어욤ㅠㅠ</h2>
<h3>빠른시일내에 복구하겠습니다...</h3>
<img src="error.jpg">
<%	
/* 내장객체 변수 ..@page 지시자 태그에 isErroPage=true일때만 사용 가능*/
    String msg = "오류 원인 : " + exception;
	System.out.println("----------------------------------------");
	System.out.println(msg);
	System.out.println("----------------------------------------");	
	exception.printStackTrace();
%>
</body>
</html>




<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <!-- 실행하다 에러 났을때만 에러페이지 기능이 작동한다 -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="text-align:center">
<h2>오류 발생했어욤ㅠㅠ</h2>
<h3>빠른시일내에 복구하겠습니다...</h3>
<img src="error.jpg">
<%	
	/* 내장객체 변수 ..@page 지시자 태그에 isErroPage=true일때만 사용 가능*/
    String msg = "오류 원인 : " + exception;
	System.out.println("----------------------------------------");
	System.out.println(msg);
	System.out.println("----------------------------------------");	
	exception.printStackTrace();
%>
</body>
</html>
```





#### exam9 - include jspf사용

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 학습</title>
</head>
<body>
<h2>include 지시자 태그 테스트</h2>
<hr>
<%@  include  file="part1.jspf"  %>
<hr>
<%@  include  file="part2.jspf"  %>
<hr>
<h3>이 페이지에서 직접 출력하는 내용입니다.</h3>
</body>
</html>


<%@  page pageEncoding="UTF-8"  %>
<div>
include 지시자에 의해 포함된 내용입니다.
</div>


<%@  page pageEncoding="UTF-8"  %>
<% int num = 1000; %>
<div>
100 * 2 의 연산 결과 : <%= num * 2 %>
</div>
```





#### exam10 - jsp:forward, param 액션태그

```jsp
<%@ page contentType="text/html; charset=utf-8"   %>
<!DOCTYPE html>
<html>
<HEAD>
<meta charset="UTF-8">
<TITLE>forward 액션 태그를 이용한 분기 예제  </TITLE>
</HEAD>
<BODY>
<% if(request.getParameter("type")==null || 
           request.getParameter("type").equals("admin") ){ %>
  <jsp:forward page="admin_new.jsp">
  	<jsp:param name="message" value="Hi! Administrator" />
  </jsp:forward>
<% } else  {%>
  <jsp:forward page="user_new.jsp">
  	<jsp:param name="message" value="Hi! User" />
  </jsp:forward>
<%} %> 
</BODY>
</HTML>


<%@ page contentType="text/html; charset=utf-8"   %>
<HTML>
<HEAD>
<TITLE>관리자 화면  </TITLE>
</HEAD>
<BODY>
관리자님 환영합니다. <br>
이 화면은 관리자를 위한 화면입니다. <br>
전달된 파라미터 : <span style="color:blue;">
<%=request.getParameter("message")%></span>
</BODY>
</HTML>
```





#### exam11 - jsp:include 액션태그

```jsp
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<HTML>
<HEAD>
<META charset="UTF-8">
<TITLE>INCLUDE 액션태그 예제</TITLE>
</HEAD>
<BODY>
<H3>INCLUDE 액션태그 예제</H3>
<H4>각 나라의 감사 인사말입니다.</H4>
<P>
한국어 : 
<jsp:include page="greeting.jsp">
   <jsp:param name="country" value="korea" />
</jsp:include>
</P>
<P>
영어 :
<jsp:include page="greeting.jsp">
   <jsp:param name="country" value="english" />
</jsp:include>
</P>
<P>
일본어 :
<jsp:include page="greeting.jsp">
   <jsp:param name="country" value="japan" />
</jsp:include>
</P>
</BODY>
</HTML>
    
    
    
    
 <!-- 파일명 : greeting.jsp -->
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<HTML>
<HEAD>
<META charset="UTF-8">
<TITLE>감사인사</TITLE>
</HEAD>
<BODY>
<%
if(request.getParameter("country").equals("korea")) {
%>
   <%= "감사합니다." %>
<%	
}
else if(request.getParameter("country").equals("english")) {
%>
   <%= "Thank You." %>
<%
}
else if(request.getParameter("country").equals("japan")) {
%>
    <%= "ありがとうございます" %>
<%
}
%>
</BODY>
</HTML>   
 
```







#### exam12 - include 정적포함, 동적포함 비교

- <@ include >는 date.jsp 에 ld 가 두번 정의되서 가져오기 때문에 중복오류
- jsp:include 는 결과값을 가져와서 오류 없음

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 예제</title>
</head>
<body>
<h2>include 지시자와 액션태그 비교</h2>
<hr>
<%@ include file="date.jsp" %>
<hr>
<%@ include file="date.jsp" %> 
<%-- 
<hr>
<jsp:include page="date.jsp" />
<hr>
<jsp:include page="date.jsp" />
--%>
</body>
</html>
```







#### go.jsp - forward:, redirect

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고고!!</title>
</head>
<body>
<%	
	request.setCharacterEncoding("UTF-8");
	String sub = request.getParameter("study");
	if(sub == null){%>
<h2>study 라는 이름으로 전달된 쿼리가 존재하지 않습니다.</h2>
<%	}	
	else{
		String site = "";
		if(sub.equals("javascrpit"))
			site = "http://www.w3schools.com/js/default.asp";
		else if(sub.equals("ajax"))
			site = "http://www.w3schools.com/js/default.asp";
		else if(sub.equals("dom"))
			site = "http://www.w3schools.com/xml/ajax_intro.asp";
		else if(sub.equals("ajax"))
			site = "http://www.w3schools.com/js/js_json_intro.asp";
		else {
			if(sub.equals("jsp")){%>
<jsp:forward page="/first.jsp">
	<jsp:param name="gname" value="unico" />
</jsp:forward>
<% 		
		}else if(sub.equals("html")){ 
			%>
<jsp:forward page="/first.html" />
			<% 
		}
		}
		response.sendRedirect(site);
	}
%>
</body>
</html>
```







#### exam13 - JSP 내장객체 점검

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP의 내장 객체들</title>
</head>
<body>
<h2>JSP의 내장 객체들 점검</h2>
[ request ]<br>
getMethod() : <%=  request.getMethod()  %><br>
getRequestURI() : <%=  request.getRequestURI()  %><br><!-- /sedu/jspexam.... -->
getHeader("user-agent") : <%=  request.getHeader("user-agent")  %><br><!-- 요청 헤더정보,크롬;모바일 등등 -->
[ application ]<br> 
getContextPath() : <%=  application.getContextPath()  %><br>
getServerInfo() : <%=  application.getServerInfo()  %><br><!-- 톰캣 이름 -->
getMajorVersion() : <%=  application.getMajorVersion()  %><br><!-- 서블릿 버전 -->
[ session ]<br>
getId() : <%=  session.getId()  %><br>
getCreationTime() : <%=  new Date(session.getCreationTime())  %><br>
[ response ]<br>
getStatus() : <%=  response.getStatus() %><br>
getBufferSize() : <%=  response.getBufferSize() %><br>
getContentType() : <%=  response.getContentType() %>
<H4>Web Application(/sedu) 디렉토리의 파일 리스트 </H4>
<% 
java.util.Set<String> list = application.getResourcePaths("/");
if (list != null) {
   Object obj[] = list.toArray();
   for(int i=0; i < obj.length; i++) {
      out.print(obj[i]+", ");
   }
}
%>
</body>
</html>
```





#### exam14

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>JSP에서의 HttpSession 객체</h2>
<hr>
<%= session.isNew() %><br>
<%= session.getId() %><br>
<%= session.getCreationTime() %><br>
<%= new java.util.Date(session.getCreationTime()) %><br>
</body>
</html>
```





#### exam15 - 로그인

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장객체 예제 </title>
<style>
	input {
		margin : 1px;
	}
</style>
</head>
<body>
<%
if (request.getMethod().equals("POST")) {
   if (session.isNew() || session.getAttribute("member_id") == null ) {
      String name = request.getParameter("membername");
      String passwd = request.getParameter("memberpassword");
      if(name.equals("duke") && passwd.equals("java")) {
    	  session.setAttribute("member_id", name);  
    	  session.setAttribute("member_passwd", passwd);
    	  session.setMaxInactiveInterval(60);
%>
     	 <script>
  			alert("성공적으로 로그인했습니다!!");
  		 </script>
     	 <h3><%=  name %> 회원님.. 좋은하루 되세요...</h3>
		 <a href="/sedu/jspexam/exam16.jsp">로그아웃</a>	
<%
      } else {    	 
%>
			<script>
  				alert("로그인에 실패했습니다 !!");
  			</script>
  			<h2>로그인</h2><hr>
			<form method="post" action="/sedu/jspexam/exam15.jsp">
				<input placeholder="계정을 입력하세요" 
				                         name="membername"><br>
				<input type="password" placeholder="암호를 입력하세요" 
				                         name="memberpassword"><br>
				<input type="submit"  value="로그인">
			</form>
<%
      }
   }		      
} else if (request.getMethod().equals("GET")) {
	String name = (String)session.getAttribute("member_id") ;
	if (name != null ) {
%>
		 <h3><%=  name %> 회원님.. 좋은하루 되세요...</h3>
	 	 <a href="/sedu/jspexam/exam16.jsp">로그아웃</a>	
<%
   	} else {
%>
 		<script>
  			alert("로그인 해주세요!");
  		</script>
		<h2>로그인</h2><hr>
		<form method="post"  action="/sedu/jspexam/exam15.jsp">
			<input placeholder="계정을 입력하세요" name="membername"><br>
			<input type="password" placeholder="암호를 입력하세요" name="memberpassword"><br>
			<input type="submit"  value="로그인">
		</form>
<%	
   	}
}
%>			
</body>
</html>

```





#### exam16 - 로그아웃

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장객체 예제 </title>
<style>
	input {
		margin : 1px;
	}
</style>
</head>
<body>
<%
	 String membername = 
	                     (String)session.getAttribute("member_id");
	 if(membername != null) {
 		session.removeAttribute("member_id");
 		session.removeAttribute("member_passwd");
%>
		<script>
  			alert("성공적으로 로그아웃했습니다 !!");
  		</script>
 <%
	 } else { 		 
 %>
 		<script>
  			alert("로그인 상태가 아닙니다 !!");
  		</script>
 <%	
	} 
%>	
 		<h2>로그인</h2><hr>
		<form method="post"  action="/sedu/jspexam/exam15.jsp">
			<input placeholder="계정을 입력하세요" name="membername"><br>
			<input type="password" placeholder="암호를 입력하세요" name="memberpassword"><br>
			<input type="submit"  value="로그인">
		</form>		
</body>
</html>

```





### 객체공유 exam

- xxxVO : Value Object, 값을 설정하고 보관하는 기능
- xxxDAO : Data Access Object, DB 연동기능 (JDBC)을 지원하는 객체
- xxxService(xxxBiz) : Service Object, 서비스 로직을 지원하는 객체



### Share 예제!! 브라우저간 공유하는지

#### ShareTestServlet1.java / CountVO / share1.jsp

- **Request Scope**, 요청이 끝나면 없어진다!

```java
package controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.CountVO;
@WebServlet("/sharetest1")
public class ShareTestServlet1 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int param = Integer.parseInt(request.getParameter("num"));
		CountVO vo = new CountVO();
		vo.setNumber(param);
		request.setAttribute("objreq", vo);
		request.getRequestDispatcher("/jspexam/share1.jsp").
		              forward(request, response);
	}
}


package vo;

public class CountVO {
	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number += number;
	}	
}


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.CountVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title>Insert title here</title>
</head>
<body>
<h2>서블릿이 전달한 객체(request)</h2>
<hr>
<%
	CountVO vo = (CountVO)request.getAttribute("objreq");
    if(vo != null) {
%>
		추출된 값 : <%= vo.getNumber() %><br>		
<%
    } else {
%>
    	추출된 객체가 없슈!!
<%	
    }
%>
</body>
</html>
```





#### ShareTestServlet2.java / CountVO / share2.jsp

- **Session Scope**, 브라우저 살아있는 동안 보관

```java
package controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vo.CountVO;
@WebServlet("/sharetest2")
public class ShareTestServlet2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int param = Integer.parseInt(request.getParameter("num"));
		HttpSession session = request.getSession();
		if(session.getAttribute("objsession") == null) {
			session.setAttribute("objsession", new CountVO());
		}
		CountVO vo = (CountVO)session.getAttribute("objsession");
		vo.setNumber(param);
		
		request.getRequestDispatcher("/jspexam/share2.jsp").
		              forward(request, response);
	}
}


//VO 달고 있으면 값을 설정하고 보관하는 기능이 라고 생각하면 된다
//Value Object 
package vo;

public class CountVO {
	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number += number;
	}	
}



<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.CountVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title> 
</head>
<body>
<h2>서블릿이 전달한 객체(session)</h2>
<hr>
<%
	CountVO vo = (CountVO)session.getAttribute("objsession");
    if(vo != null) {
%>
		추출된 값 : <%= vo.getNumber() %><br>		
<%
    } else {
%>
    	추출된 객체가 없슈!!
<%	
    }
%>
</body>
</html>
```







#### ShareTestServlet3.java / CountVO / share3.jsp

- **Application Scope**, 서버가 기동되어 있는 동안 유효하다

```java
package controller;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.CountVO;
@WebServlet("/sharetest3")
public class ShareTestServlet3 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int param = Integer.parseInt(request.getParameter("num"));
		System.out.println(param);
		ServletContext context = getServletContext();
		if(context.getAttribute("objapp") == null) {
			context.setAttribute("objapp", new CountVO());
		}
		CountVO vo = (CountVO)context.getAttribute("objapp");
		vo.setNumber(param);
		
		request.getRequestDispatcher("/jspexam/share3.jsp").
		              forward(request, response);
	}
}



package vo;

public class CountVO {
	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number += number;
	}	
}


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.CountVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title> 
</head>
<body>
<h2>서블릿이 전달한 객체(application)</h2>
<hr>
<%
	CountVO vo = (CountVO)application.getAttribute("objapp");
    if(vo != null) {
%>
		추출된 값 : <%= vo.getNumber() %><br>	
<%
    } else {
%>
    	추출된 객체가 없슈!!
<%	
    }
%>
</body>
</html>
```





### EL 사용법

#### memberView_EL.jsp : 액션태그, EL 사용법

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.vo.MemberVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 확인창</title>
</head>
<body>
<h1>스크립트 태그</h1>
<h2>회원 정보</h2>
<hr>
<%
	MemberVO mVO = (MemberVO)request.getAttribute("member");
%>
<ul>
<li>회원 이름 : 
<%= mVO.getName() %>
</li>
<li>회원 계정 : 
<%= mVO.getId()%>
</li>
<li>회원 암호 : 
<%= mVO.getPwd() %>
</li>
<li>회원 전화번호 : 
<%= mVO.getNum()%>
</li>

</ul>
<hr>

<h1>액션 태그</h1>
<!-- 마지막에 닫아줘야하는 XML 구문 -->
<!-- id 는 request 객체에 지정한 이름 -->
<!-- class 는 import 하는거처럼 위치 지정해주기 -->
<!-- request 객체에서 사용하므로 scope = "request" -->
<!-- 객체 없으면 새로 만든다..아규먼트 안받는 생성자를 만들어버린다 -->
<jsp:useBean id="member" class="model.vo.MemberVO" scope="request" />
<h2>회원 정보</h2>
<hr>
<ul>
<li>회원 이름 : 
<!-- name 은 객체에 지정한 이름 -->
<!-- property 에는 getter 의 이름에서 get 빼고 첫글자 소문자로 바꿔서 지정 -->
<jsp:getProperty name="member" property="name" />
</li>
<li>회원 계정 : 
<jsp:getProperty name="member" property="id" />
</li>
<li>회원 암호 : 
<jsp:getProperty name="member" property="pwd" />
</li>
<li>회원 전화번호 : 
<jsp:getProperty name="member" property="num" />
</li>

</ul>
<hr>

<h1>Expression Language 태그</h1>
<h2>회원 정보</h2>
<hr>
<ul>
<li>회원 이름 : 
<!-- scope방법.객체이름.property명 -->
${requestScope.member.name}
</li>
<li>회원 계정 : 
${requestScope.member.id}
</li>
<li>회원 암호 : 
${requestScope.member.pwd}
</li>
<li>회원 전화번호 : 
${requestScope.member.num}
</li>

</ul>
<hr>
<a href="<%= request.getHeader("referer") %>">회원정보 재입력</a>
</body>
</html>
```



#### productView.jsp (위와 동일한 방법으로 변환)

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.vo.ProductVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과일 바구니 확인창</title>
<style>
a{
	background : linear-gradient(to top, #d5f4e6 , #b3c6ff);
}
</style>
</head>
<body>
<h2>선택된 상품 정보는 다음과 같습니다(스크립트 태그)</h2>
<hr>
<%
	ProductVO vo = (ProductVO)session.getAttribute("cnt");
%>
<ul>
<li>선택된 사과의 개수 : <%= vo.getAnum() %></li>
<li>선택된 바나나의 개수 : <%= vo.getBnum()%></li>
<li>선택된 한라봉의 개수 : <%= vo.getHnum() %></li>
</ul>
<h2>선택된 상품 정보는 다음과 같습니다(액션 태그)</h2>
<hr>
<jsp:useBean id="cnt" class="model.vo.ProductVO" scope="session" />
<ul>
<li>선택된 사과의 개수 : <jsp:getProperty name="cnt" property="anum"/></li>
<li>선택된 바나나의 개수 : <jsp:getProperty name="cnt" property="bnum"/></li>
<li>선택된 한라봉의 개수 : <jsp:getProperty name="cnt" property="hnum"/></li>
</ul>
<h2>선택된 상품 정보는 다음과 같습니다(Expression Language)</h2>
<hr>
<ul>
<li>선택된 사과의 개수 : ${sessionScope.cnt.anum}</li>
<li>선택된 바나나의 개수 : ${sessionScope.cnt.bnum}</li>
<li>선택된 한라봉의 개수 : ${sessionScope.cnt.hnum}</li>
</ul>
<hr>
<a href="${header.referer}">상품선택화면</a>
</body>
</html>
```



#### sedu/jspbean,elexam1,2,3,4.jsp

**elexam**1

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 테스트</title>
</head>
<body>
<h2>EL의 연산자들</h2>
<hr>
\${200+100} :  ${200+100} <br> 
\${200-100} :  ${200-100} <br>
\${200/100} :  ${200/100} <br>
\${200>100} :  ${200>100} <br>
\${200==100} :  ${200==100} <br>
\${200!=100} :  ${200!=100} <br>
\${ '10' - 10 } : ${ '10' - 10 }  숫자 계산함<br> 
\${ '10' + 10 } : ${ '10' + 10 }<br> 
\${10 * "10" } : ${10 * "10" }<br>  
\${40 div 5 } : ${40 div 5 }<br>
\${40 mod 5 } : ${40 mod 5 }<br> 
\${ 10 eq 10 } : ${ 10 eq 10 }<br> 
\${ 10 lt 10 } : ${ 10 lt 10 }<br> 
\${ 10 gt 10 } : ${ 10 gt 10 }<br>
\${ 10 le 10 } : ${ 10 le 10 }<br>
\${ 10 ge 10 } : ${ 10 ge 10 }<br>
\${10 > 5?'A':'B'} : ${10 > 5?'A':'B'}<br>
\${100 + 200 + 300 } : ${100 + 200 + 300 }<br>
\${100 += 200 += 300 } : ${100 += 200 += 300 }<br> 
\${"EL" += 12 += 34 += "-문자열 결합연산" } : ${"EL" += 12 += 34 += "-문자열 결합연산" }
</body>
</html>
```



##### elexam2

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 테스트</title>
</head>
<body>
<h2>EL의 Query 문자열 추출</h2>
<hr>
**중요! param 은  HashMap 객체들을 갖고있는 내장 객체이다 name=value<hr>
전달된 메시지의 존재 여부 : ${ !empty param.message }<hr>
전달된 메시지의 내용은 ${param.message} 입니다.<br>
전달된 메시지의 내용은 ${param["message"]} 입니다.<br>
전달된 메시지의 내용은 <%= request.getParameter("message") %> 입니다.<br>
</body>
</html>
```



##### elexam3 - EL 변수의 특징

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 테스트</title>
</head>
<body>
<h2>EL 변수</h2>
<hr>
name 변수의 값 : ${name}<br>
<% String name="듀크"; %>
name 변수의 값(표현식 태그) : <%= name %><br>
<!-- name 에 접근되지 않는다
***특정 스코프에 저장되어 있는 값만 접근 가능하다 -->
name 변수의 값(EL) : ${name}<br>
<!-- 가장 좁은 의미의 스코프인 pageContext -->
<!-- 지금 이 jsp에서만 사용 가능한 -->
<% pageContext.setAttribute("name", "자바");  %>
name 변수의 값 : ${name}<br>
pageScope.name 변수의 값 : ${pageScope.name}<br>
<hr>
<% pageContext.setAttribute("number", 100); %>
number 변수의 값 : ${number}<br>
pageScope.number 변수의 값 : ${pageScope.number}<br>
number 변수의 값에 23을 더한 값 : ${ number + 23 }
</body>
</html>
```



##### elexam4 - LanguageInfoBean, Today, TestBean.java

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<%@ page import="jspbean.*, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title>EL 테스트</title> 
</head>
<body>
<h2>객체의 getter 메서드, 컬렉션 객체의 원소, 클래스의 정적 멤버 사용</h2> 
<hr>
<%
/* today라는 이름으로 일반 Today.java 객체 저장 */
pageContext.setAttribute("today", new Today());
ArrayList<TestBean> mylist = new ArrayList<>();
mylist.add(new TestBean("둘리"));
mylist.add(new TestBean("또치"));
mylist.add(new TestBean("도우너"));
/* list라는 이름으로 ArrayList 형  객체 저장 */
pageContext.setAttribute("list", mylist); 
%>
<h3>객체의 멤버 접근</h3>
<!-- getYear 과 동일 -->
${ today.year }년 ${ today.month }월 ${ today.date }일
<h3>컬렉션의 객체 사용</h3>
<!-- getName 과 동일 -->
${ list[0].name }-${ list[1].name }-${ list[2].name }<br>
<h3>클래스의 정적 멤버 사용</h3>
<!-- 클래스명을 줘서 바로 가져오는것도 가능하다 -->
<!-- get 메서드도 사용 가능 -->
${ LanguageInfoBean.name }<br>
${ LanguageInfoBean.getBirthYear() }<br>
${ LanguageInfoBean.getKindInfo() }<br> 
</body>
</html>
```



```java
package jspbean;

public class LanguageInfoBean {
	//static 멤버에 접근이 가능하다는것을 보여주기 위한 예제
	public static String name = "자바";
	
	public static int getBirthYear() {
		return 1996;
	}
	public static String getKindInfo() {
		return name +"는 OOP 프로그래밍 언어입니다.";
	}
}



package jspbean;
import java.time.LocalDate;

public class Today {
	private int year;
	private int month;
	private int date;
	public Today() {
		year = LocalDate.now().getYear();
		month = LocalDate.now().getMonthValue();
		date = LocalDate.now().getDayOfMonth();
	}
	//setter 메서드가 필요없다..LocalDate로 세팅해주기때문
	public int getYear() {
		return year;
	}
	public int getMonth() {
		return month;
	}
	public int getDate() {
		return date;
	}	
}



package jspbean;

import java.time.LocalTime;

public class TestBean {
	private String name;
	private String time;
	public TestBean() {
		LocalTime lt = LocalTime.now();
		time = lt.getHour()+ "시 " +lt.getMinute() +"분 " +lt.getSecond() +"초";
		name="Guest";
	}
	public TestBean(String name) {
		LocalTime lt = LocalTime.now();
		time = lt.getHour()+ "시 " +lt.getMinute() +"분 " +lt.getSecond() +"초";
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
}

```

