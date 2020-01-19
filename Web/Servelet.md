# Servelet

[TOC]



- CGI(Common Gateway Interface) : 웹의 표준, 구현언어 투명성

  - Fast CGI
  - Servlet

- Server Side Applet (Applet : 웹브라우저에서 실행되는 Java프로그램)

- Web Server : 웹 통신에서 서버역할을 수행하는 프로그램(=Http Server)

- Web Server + Application Server = Web Application Server = **WAS**

  **TOMCAT** = WAS = 코요테(웹서버) + 카탈리나(어플리케이션 서버)



### 구현상 특징

1. HttpServlet 을 상속해야한다.

2. main() 메서드 구현하지 않는다. main() 메서드를 담고있는 메인 클래스는 톰캣내장

3. 수행하는동안 호출되는 메서드가 정해져 있는데
   이 때 호줄되는 메서드는 init(), service(), destroy(), doGet(), doPost() 등이다.
   따라서 이메서드들을 선택적으로 오버라이딩하여 구현한다.

   destroy 는 리로드 혹은 서블릿이 종료될때 

4. Servlet 에서 수행결과를 출력 할 때

   - 방법 1  : 요청해온 브라우저로의 출력(=응답)

     ​				HttpServletResponse의 getWriter()를 호출해 클라이언트로의 

     ​				출력 스트림 객체를 생성해서 출력

   - 방법 2 : 표준출력 = System.out.println() - 톰캣서버의 콘솔창에 출력

5. Servlet이 수행하는 필요한 데이터를 요청 보내오는 클라이언트로 부터 전달받을 수 있다. 이 때 전달받는 데이터를 쿼리 문자열이라 한다.(ex : 회원정보, 로그인정보 등)

   - HttpServletRequest 의 getParameter() 또는 getParameterValues()를 사용
   - getParameter() :  단수, String 형
   - getParameterValues() : 복수, String[] 형 배열 ....ex) checkbox 
   
6. **POST** 때는 한글이 깨지니까 

   **response.setContentType("text/html; charset=utf-8");**  꼭 지정해줘야 한다!



### 실행상 특징

1. 서블릿을 요청할 때는 "/컨텍스트루트명/서블릿의매핑명" 형식의 URI를 사용한다.
   여기서 컨텍스트 루트명은 sedu

2. 서블릿의 요청은

   - 서블릿을 요청하는 URL 문자열을 브라우저의 주소필드에 입력해서 직접 : **GET**
   - 서블릿이 바이너리 일 경우에만 `<img>` 태그로 요청 : **GET**
   - `<a>` 태그로 요청 : **GET**
   - `<form>` 태그를 통해서 요청 : **GET or POST**

3. 서블릿 객체는 한 번 생성되는 서버 종료되거나 컨텍스트가 리로드 될 때까지 객체상태를 유지한다 = 서블릿이 속도가 빠른 이유

4. 여러 클라이언트가 동일한 서블릿을 동시 요청하면 하나의 서블릿 객체를 공유해 수행

5. 최초 요청시의 수행 흐름

   init()  -  service() : doGet()  -  destroy()

   ​							 : doPost()
   
6. 두번째 요청시에는 service() 부터 호출된다!



#### 요청재지정

- **redirect** : HttpServletResponse 의 sendRedirect() 메서드를 사용한다

  - 동일한 요청상에서 다른 자원에 요청을 넘겨서 대신 응답
  - 동일한 서버의 동일 웹 어플리케이션에 존재하는 대상에만 가능
  - 브라우저의 주소필드의 URL 이 바뀌지 않음
  - 두 자원이 HttpServletRequest 객체 공유

- **forward** : RequestDispatcher 의 forward()메서드를 사용한다

  - 다른 자원을 다시 요청하여 응답
  - Web 상의 모든 페이지로 요청재지정 가능
  - 브라우저의 주소필드의 URL이 바뀜
  - 재지정 대상에 대한 요청 자체를 브라우저가 하게됨
  - 두 자원이 HttpServletRequest 객체를 공유하지 않음

  

### 실행한 기초 내용들

- sedu 프로젝트 만들고

- tomcat-add and remove에서 sedu 를 add 해준다

- .jsp 로 저장해준다

- html 과 다른점은 html은 정적 페이지로 고정된 결과를 보여주고

  jsp 는 쿼리문을 줘서 동적 웹페이지로 사용할 수 있다. **동적 처리를 서버에서 한다.**

```javascript
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>안녕하슈! <%=request.getParameter("gname") %>이에유~</h1>
<!-- http://localhost:8000/sedu/first.jsp?gname=듀크  이렇게 웹에 입력하면 동적으로 입력가능-->
</body>
</html>
```



#### first servlet 에서 테스트

```java
<servlet>
<servlet-name> abc </servlet-name>
<servlet-class>FirstServlet</servlet-class>
</servlet>
<servlet-mapping>
<servlet-name>abc</servlet-name>
<url-pattern>/firstthree</url-pattern>
</servlet mapping>
```





#### servelt 은 /sedu/Java Resources 에 넣어줘야 한다

- 패키지명 생략 
- 클래스네임 FirstServlet - next-next-체크박스상에서 constructor from superclass 생략

- 인헤리티드 앱스트렉트 메서드와 두겟만 체크 -finish 

- 얘는 메인 없음, 톰켓이 메인임.

- 두겟 안에 싹 지우고

- first.jsp h1태그 전체 카피
- http://localhost:8000/sedu/FirstServlet?gname=듀크  이렇게 사용

```java

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String s = request.getParameter("gname");
		out.print("<h1>안녕하슈! "+s+"이에유~<h1>");
		out.close();
	}

}
```





### core 패키지

---

#### FlowServlet(flow) : /flow 는 매핑명

- init(), destroy(), service() 는 선택 / constructor 해제

  ```java
  
  
  import java.io.IOException;
  import java.io.PrintWriter;
  
  import javax.servlet.Servlet;
  import javax.servlet.ServletException;
  import javax.servlet.annotation.WebServlet;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  
  /**
   * Servlet implementation class FirstServlet
   */
  @WebServlet({"/firstone", "/firsttwo"})
  public class FirstServlet extends HttpServlet implements Servlet {
  	private static final long serialVersionUID = 1L;
  
  	/**
  	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  	 */
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		response.setContentType("text/html; charset=utf-8");
  		PrintWriter out = response.getWriter();
  		String s = request.getParameter("gname");
  		out.print("<h1>안녕하슈! "+s+"이에유~~~!<h1>");
  		System.out.println("표준출력하시렵니까?");
  		out.close();
  	}
  
  }
  ```



#### MemberLocalServlet(/memberlocal) - GET

- doGet만 설정

```java
package core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberLocalServlet
 */
@WebServlet("/memberlocal")
public class MemberLocalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int member_v = 0;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int local_v = 0;
		
		response.setContentType("text/html; charset=utf-8");
		//text 형식인데 html이고 utf-8버전이라는 것을 알려주는 부분
		PrintWriter out = response.getWriter();
        
		//servlet 은 하나만 생성해서 공유해서 사용하는 부분..메모리 효과적	
		member_v += 10;//객체생성시 메모리 할당
		local_v += 10;//메서드 호출될 때 메모리 개별적 할당되고, 그리고 사라짐
		
		out.print("<ul>");
		out.print("<li>멤버변수 : " + member_v + "</li>");
		out.print("<li>지역변수 : " + local_v + "</li>");
		out.print("<ul>");
		out.close();
	}

}
```





#### QuerryServlet(/querry) - GET

- doGet만 설정

```java
package core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberLocalServlet
 */
@WebServlet("/memberlocal")
public class QuerryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int member_v = 0;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int local_v = 0;
		
		response.setContentType("text/html; charset=utf-8");
		//text 형식인데 html이고 utf-8버전이라는 것을 알려주는 부분
		PrintWriter out = response.getWriter();
		
		member_v += 10;//servlet 은 하나만 생성해서 공유해서 사용하는 부분..메모리 효과적
		local_v += 10;
		
		out.print("<ul>");
		out.print("<li>멤버변수 : " + member_v + "</li>");
		out.print("<li>지역변수 : " + local_v + "</li>");
		out.print("<ul>");
		out.close();
	}



```



#### 실습-날짜뽑기, null 값 주기

```java
package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Month;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberLocalServlet
 */
@WebServlet("/myfirst")
public class MyFirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		if (name==null)
			name = "GUEST";
		
		LocalDate date = LocalDate.now();
		int dNum = date.getDayOfWeek().getValue();
		String day = "";
		switch(dNum) {
		case 1 :  
			day = "월";
			break;
		case 2 :  
			day = "화";
			break;
		case 3 :  
			day = "수";
			break;
		case 4 :  
			day = "목";
			break;
		case 5 :  
			day = "금";
			break;
		case 6 :  
			day = "토";
			break;
		case 7 :  
			day = "일";
			
		}
		

		out.print("<h2>"+name+"동무 반갑습네다!! 오늘은 혁명의 " + day+"요일 입니다!!</h2>");
		out.close();
	}

}
```



---



#### GetPostServlet.java - GET + POST 방식

```java
package core;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getpost")
//url 주면 무조건 GET 방식으로 출력된다
public class GetPostServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<h2>요청 방식 : "+request.getMethod()+"</h2>");
		out.print("<h2>Query 문자열 : "+
		                  request.getParameter("name")+"</h2>");
		out.close();
		System.out.println("GET 방식 수행");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		out.print("<h2>요청 방식 : "+request.getMethod()+"</h2>");
		out.print("<h2>Query 문자열 : "+request.getParameter("name")+"</h2>");
		out.close();
		System.out.println("POST 방식 수행");
	}
}
```

#### getpost.html - 하이퍼링크, GET, POST 버튼

```java
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>서블릿을 요청하는 다양한 방법</h1>
<hr>						<!-- 하이퍼링크 무조건 GET 방식으로 호출-->
<a href="/sedu/getpost?name=듀크">하이퍼링크로 요청</a>
<hr>
    
<!-- form 태그는 선택해서 방식으로 호출-->  
  <!-- GET 방식으로  /sedu/getpost 호출-->
<form method="get" action="/sedu/getpost">
    							
<input type="text" placeholder="이름을입력하세요" name="name" >
<input type="submit"  value="GET방식요청">
</form>
<hr>
    <!-- POST 는 그때그때 할당, querry 문 나타나지 않음-->
<form method="post" action="/sedu/getpost">
<input type="text" placeholder="이름을입력하세요" name="name" >
<input type="submit"  value="POST방식요청">
</form>
</body>
</html>
```



---



#### GetPostServlet2.java - 

```java
package core;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getpost2")
//url 주면 무조건 GET 방식으로 출력된다
public class GetPostServlet2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<h2>요청 방식 : "+request.getMethod()+"</h2>");
		out.print("<h2>Query 문자열 : "+
		                  request.getParameter("name")+"</h2>");
		out.close();
		System.out.println(request.getMethod()+" 방식 수행");
	}

	/* doPost가 호출되면 Encoding 수행하고 doGet을 호출한다 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		doGet(request,response);
	}
}
```



#### GetPostServlet3.java - service

```java
package core;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getpost3")
//url 주면 무조건 GET 방식으로 출력된다
public class GetPostServlet3 extends HttpServlet {
	//service 는 GET POST 구분없이 수행하게 함
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String method = request.getMethod();
		if(method.equals("POST"))
			request.setCharacterEncoding("utf-8");
		out.print("<h2>요청 방식 : "+method+"</h2>");
		out.print("<h2>Query 문자열 : "+
		                  request.getParameter("name")+"</h2>");
		out.close();
		System.out.println(method+" 방식 수행");
	}
}
```





#### QueryTestServlet.java

```java
package core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/querytest")
public class QueryTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("stname");
		String pwd = request.getParameter("pwd");
		int age = Integer.parseInt(request.getParameter("age"));
		
		String gender = request.getParameter("gender");
		
		String[] hobby = request.getParameterValues("hobby");
		String[] food = request.getParameterValues("food");
		
		String color = request.getParameter("color");
		String op = request.getParameter("op");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print("<h2> 전달된 내용 </h2>"); out.print("<hr>");
		
		
		out.print("<ul>");
		out.print("<li> 이름 : " +name+ "</li>");
		out.print("<li> 암호 : " +pwd+ "</li>");
		out.print("<li> 나이 : " +age+ "</li>");
		
		out.print("<li> 성별 : ");
			if (gender == null) {
				out.print("선택 제대로 하세요 -ㅅ-");	
			}
			else {
				out.print(gender);
			}
		out.print("</li>");
		
		out.print("<li> 취미 : ");
			if (hobby == null) {
				out.print("선택 제대로 하세요 -ㅅ-");	
			}
			else {
				for (int i=0 ; i<hobby.length ; ++i) {
					if (i == hobby.length -1) {
						out.print(hobby[i]);
						break;
					}
					out.print(hobby[i] +",");
				}
			}
		out.print("</li>");
		
		out.print("<li> 좋아하는 색 :" +(color == ""?"없음":color) +"</li>");
		out.print("<li> 좋아하는 음식 : ");
		if (food == null) {
			out.print("선택 제대로 하세요 -ㅅ-");	
		}
		else {
			for (int i = 0; i < food.length; ++i) {
				if (i == food.length - 1) {
					out.print(food[i]);
					break;
				}
				out.print(food[i] +",");
			}
		}
		out.print("</li>");
		out.print("<li> 의견 : " + op + "</li>");
		out.print("<li> 비밀1 : " + request.getParameter("h_1") + "</li>");
		out.print("<li> 비밀2 : " + request.getParameter("h_2") + "</li>");
		
		
		out.print("</ul>"); out.print("<hr>");
		out.print("<a href='http://70.12.113.164:8000/sedu/queryForm.html'>"
				+ "입력화면으로</a>");
		
		out.close();
	}

}
```



#### querytest.html

```java

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	    <form method="GET" 
	    action="http://localhost:8000/sedu/querytest">
		이름 : <input name="stname" value="듀크" required><br>
		암호 : <input type="password" name="pwd"><br>
		나이 : <input type="number" name="age"><br>
		성별 : <input type="radio" name="gender" value="남자">남자
		<input type="radio" name="gender" value="여자">여자
		<br>
		
		취미 : 
		피아노 <input type="checkbox" name="hobby" value="피아노">
		수영 <input type="checkbox" name="hobby" value="수영">
		독서 <input type="checkbox" name="hobby" value="독서">
		게임 <input type="checkbox" name="hobby" value="게임">
		<br>
	
		좋아하는 색 : 
		<select name="color">
		    <option value=""></option>
			<option value="빨강색">RED</option>
			<option value="파랑색">BLUE</option>
			<option value="노랑색">YELLOW</option>
		</select>
		<br>
		
		좋아하는 음식 :
		<br> 
		<select name="food" size="4" multiple><!-- mutiple 여러개 선택 가능 -->
			<option value="라면">라면</option>
			<option value="냉면">냉면</option>
			<option value="짜장면">짜장면</option>
			<option value="햄버거">햄버거</option>
			<option value="닭강정">닭강정</option>
			<option value="육회">육회</option>
		</select>
		<br>
		
		의견 : 
		<br>
		<textarea name="op" rows="10" cols="50"></textarea><br>
		
		<input type="hidden" name="h_1" value="-ㅅ-">
		<input type="hidden" name="h_2" value="=ㅅ="> 
		
		<input type="submit" value="보내기">
		<input type="reset" value="다시작성하기">
	</form>
</body>
</html>
            
<<<결과물>>>            
http://localhost:8000/sedu/querytest?
	stname=%EB%93%80%ED%81%AC
	&pwd=qkqhdi
    &age=123456
    &gender=%EB%82%A8%EC%9E%90
    &hobby=%ED%94%BC%EC%95%84%EB%85%B8
    &color=%EB%B9%A8%EA%B0%95%EC%83%89
    &food=%EB%83%89%EB%A9%B4
    &food=%EC%A7%9C%EC%9E%A5%EB%A9%B4
    &food=%ED%96%84%EB%B2%84%EA%B1%B0
    &op=%EC%A7%91%EC%97%90+%EC%96%B8%EC%A0%9C%EA%B0%80%EB%88%84
    &h_1=-%E3%85%85-
    &h_2=%3D%E3%85%85%3D
```



#### 실습-ReservationSevlet, html - date 변환

```java
package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reservation")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String rType = request.getParameter("rType");
		String[] reQ = request.getParameterValues("request");
		
		
		String date = request.getParameter("date");
		LocalDate newD = LocalDate.parse(date);
		DateTimeFormatter date1 = DateTimeFormatter.ofPattern(
				"yyyy년 MM월 dd일");
		String date2 = newD.format(date1);


		 
		 
/*		if(pwd == "7777") {*/
		out.print("<h1>"+name+" 님의 예약내용</h1><hr>");
		
			
		out.print("<ul>");
		out.print("<li> 룸 : " +rType+ "</li>");
		out.print("<li> 추가 요청 사항 : ");
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
		}
		out.print("</li>");
			
		
		  out.print("<li> 예약날짜 : " +date2+ " </li>");
		 
		
		out.print("</ul>"); out.print("<hr>");
		out.print("<a href='http://70.12.115.175:8000/sedu/html/reservation.html'>"
				+ "입력화면으로</a>");
		/*
		 * }else { out.print("비밀번호를 다시 입력하세요"); }
		 */
		out.close();
	}

}



<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>울랄라 팬션 예약</title>
<style>
div#form { 
	width : 420px;
	height : 250px;
	background : linear-gradient(to top, #d5f4e6 , #b3c6ff);
	/* border : 1px solid; */
	box-shadow : 5px 5px #d5f4e6;
	padding : 10px;
	border-radius: 40px 40px 60px 80px;
}
div#end{
	text-align : center;
}
</style>
</head>

<body>


<div id="form">
<form action="/sedu/reservation" method = "POST">
<h2 style="color: #2952a3; text-shadow : 1px 1px #1f3d7a;">펜션 예약 서비스</h2>
<hr>
예약자명 : <input type = "text" required name="name"><br>
예약암호 : <input type = "password" required name="pwd"><br>
룸 선택 : 
<input type="radio" name="rType" value="춘" >춘
<input type="radio" name="rType" value="하">하
<input type="radio" name="rType" value="추">추
<input type="radio" name="rType" value="덩">덩
<br>
추가요청 : 
<input type="checkbox" name="request" value="패러글라이딩">패러글라이딩
<input type="checkbox" name="request" value="스카이다이빙">스카이다이빙
<input type="checkbox" name="request" value="산악행군">산악행군
<br>
예약 날짜 : 
<input type="date" name="date" ><hr>
<div id="end">
<input type = "submit" value = "보내기">
<input type = "reset" value = "다시작성하기">
</div>
</form>
</div>
</body>
</html>
```



#### 실습-BasketService,productlog

```java
package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/basket")
public class BasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("pid");
		
		out.print("<h2 style='color:blue'>선택한 상품 : " +id+"<br>"
				+"<img src = 'http://70.12.115.175:8000/edu/images/"+id+".png'");
		out.print("<hr>");
		out.print("<a href='http://70.12.115.175:8000/sedu/html/productlog.html'>"
				+ "다른 칵테일 고르기</a>");

		out.close();
	}

}



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>하나가 먹다 둘이 죽는 칵테일 리스트</title>
<style>
img{
	 border : 3px dotted #5d85d5; 
	box-shadow : 10px 10px #5d85d5;
	width : 150px;
	height : 150px;
	margin : 10px;
	border-radius: 10px 10px 10px 10px;
}
</style>
</head>

<body>
<h2>하나가 먹다 둘이 죽는 칵테일 리스트</h2>
<a href="/sedu/basket?pid=p001"><img src = "http://70.12.115.175:8000/edu/images/p001.png" name="pid" id="p001"></a>
<a href="/sedu/basket?pid=p002"><img src = "http://70.12.115.175:8000/edu/images/p002.png" name="pid" id="p002"></a>
<a href="/sedu/basket?pid=p003"><img src = "http://70.12.115.175:8000/edu/images/p003.png" name="pid" id="p003"></a>
<a href="/sedu/basket?pid=p004"><img src = "http://70.12.115.175:8000/edu/images/p004.png" name="pid" id="p004"></a>
<a href="/sedu/basket?pid=p005"><img src = "http://70.12.115.175:8000/edu/images/p005.png" name="pid" id="p005"></a><br>
<a href="/sedu/basket?pid=p006"><img src = "http://70.12.115.175:8000/edu/images/p006.png" name="pid" id="p006"></a>
<a href="/sedu/basket?pid=p007"><img src = "http://70.12.115.175:8000/edu/images/p007.png" name="pid" id="p007"></a>
<a href="/sedu/basket?pid=p008"><img src = "http://70.12.115.175:8000/edu/images/p008.png" name="pid" id="p008"></a>
<a href="/sedu/basket?pid=p009"><img src = "http://70.12.115.175:8000/edu/images/p009.png" name="pid" id="p009"></a>
<a href="/sedu/basket?pid=p010"><img src = "http://70.12.115.175:8000/edu/images/p010.png" name="pid" id="p010"></a>





</body>

</html>
```



