# MVC

[TOC]

### Request Scope

여러 정보를 전달할 때 VO 클래스를 만든다

#### lottoView.jsp(mvc 폴더)

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.vo.LottoVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//리퀘스트에서 lottoVO 꺼내와야 한다
	//보관을 리퀘스트에 하면 꺼낼때도 리퀘스트에서(세션에서 꺼내는 실수 많이함)
	//"lotto"라는 이름으로 리퀘스트에 보관된 객체 불러오기
	LottoVO vo = (LottoVO)request.getAttribute("lotto");
%>
<h1><%= vo.getMsg() %></h1>
<%
	if(vo.getImgUrl()!=null){
%>
<img src="<%= vo.getImgUrl() %>" width="150"><br><br>
<%
	}
%>
<%
	if(vo.isLinkDisplay() == true){
%>
<a href="<%= request.getHeader("referer") %>">로또 재응모</a>>
<%
	}
%>
</body>
</html>
```



#### LottoVO.java

````java
package model.vo;

public class LottoVO {
	private String msg;
	private String imgUrl;
	private boolean linkDisplay;
    //세개 만들어주고 getter, setter 설정
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public boolean isLinkDisplay() {
		return linkDisplay;
	}
	public void setLinkDisplay(boolean linkDisplay) {
		this.linkDisplay = linkDisplay;
	}
}
````

#### LottoServlet2.java

````java
package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.vo.LottoVO;

@WebServlet("/lotto2")
public class LottoServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String num = request.getParameter("num");
		int sNum = Integer.parseInt(num);
		
		int rNum = (int)(Math.random()*6+1);
		System.out.println("선택한 숫자 : "+ sNum + ", 랜덤 숫자 : " + rNum);
				
		HttpSession session = request.getSession();
		if(session.getAttribute("cnt")==null)
			session.setAttribute("cnt", new int[1]);
		int[] session_r = (int[])session.getAttribute("cnt");
		session_r[0]+=1;
		
		LocalDateTime cDate = LocalDateTime.now();
		DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("HH시 mm분");
		
		LottoVO vo = new LottoVO();
		
		if(session_r[0]>=4) {
			vo.setMsg("더이상 응모할 수 없어요..! \n브라우저를 재기동하고");
		}else if(session_r[0]<=3) {
			if (sNum == rNum) {
				vo.setMsg(cDate.format(fomatter) +"에 로또 당첨!!");
				vo.setImgUrl("http://70.12.115.175:8000/sedu/jspexam/gra.png");
				session_r[0]=4;
			}else {
				vo.setMsg(cDate.format(fomatter)+"에 당첨 실패!!"
						+ "젊은 친구 인생은 다시 도전하는거야~");
				vo.setImgUrl("http://70.12.115.175:8000/sedu/jspexam/back.PNG");
                //true 면 실패--> 이거로 이미지,글자색 변경 조건으로 사용할 수 있다
				vo.setLinkDisplay(true);
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/jspexam/lottoView.jsp");
		rd.forward(request,response);
	}

}
````



---

### EduServlet 첫번째 실습

- 객체공유 아무것도 사용하지 않음
- html -> servlet -> jsp

#### eduForm.html

````html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div{
	width : 550px;
	height : 230px;
	background-image: radial-gradient(red, green, yellow);
	box-shadow : 5px 5px #d5f4e6;
	padding : 10px;
	border-radius: 40px 40px 40px 40px;
}
</style>
</head>
<body>
<div>
<h2>성적을 입력하시오.</h2>
<hr>
<form method="GET" action="/mvc/edu1">
이름 : <input type="text" name="name"><br>
평균 점수 : <input type="text" name="avg"><br><br>
<input type="submit" value="제출">
<input type="reset" value="재작성">
</form>
</div>
</body>
</html>
````



#### EduServlet.java

````java
package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edu1")
public class EduServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String avg = request.getParameter("avg");
		int intAvg = Integer.parseInt(avg);
		String url = null;
		
		if(intAvg>=90)
			url = "/jspexam/gradeA.jsp";
		else if(intAvg>=80&&intAvg<=89)
			url = "/jspexam/gradeB.jsp";
		else if(intAvg>=70&&intAvg<=79)
			url = "/jspexam/gradeC.jsp";
		else if(intAvg<=69)
			url = "/jspexam/gradeD.jsp";
		
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request,response);
	}

}
````



#### gradeABCD.java

````jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>A 등급 페이지</title>
</head>
<body>
<%
	String name = request.getParameter("name");
%>
<h2><%= name %>님은 A 등급입니다. 우수한 성적이네요(^^)</h2>
<a href="<%= request.getHeader("referer") %>">성적 입력 화면으로..</a>
</body>
</html>
````



---

### MVC 실습

- 모델 등장

#### Request Scope 사용

##### memberForm.html

````java
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 입력창</title>
<style>
h1{
	text-shadow : 2px 2px grey;
}
input.c1{
	width : 190px;
	height : 20px;
	border-radius : 5px;
	box-shadow : -2px -2px grey;
	margin : 2px;
}
input.c2{
	border-radius : 5px;
	box-shadow : 1px 1px grey;
	background-color : #d9d9d9;
}
</style>
</head>
<body>
<h1>회원 정보를 입력하십시오.</h1>
<hr>
<form method="post" action="/mvc/member">
<input type="text" name="gname" placeholder="이름을 입력하세요" class="c1"><br>
<input type="text" name="gnum" placeholder="전화번호를 입력하세요" class="c1"><br>
<input type="text" name="gid" placeholder="계정을 입력하세요" class="c1"><br>
<input type="password" name="gpwd" placeholder="패스워드를 입력하세요" class="c1"><br><br>
<input type="submit" value="제출" class="c2">
<input type="reset" value="재작성" class="c2">
</form>
</body>
</html>
````

##### MemberVO.java

````java
package model.vo;

public class MemberVO {
	private String name;
	private String num;
	private String id;
	private String pwd;
	public MemberVO() {}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}

````

##### MemberServlet.java

````java
package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.vo.MemberVO;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("gname");
		String num = request.getParameter("gnum");
		String id = request.getParameter("gid");
		String pwd = request.getParameter("gpwd");
		
		MemberVO mVO = new MemberVO();
		//여기서 입력 안됐는지 찾을때 String 형 null("") 이랑 비교해야한다
		if(name.equals(""))
			mVO.setName("없 음");
		else
			mVO.setName(name);
		
		if(num.equals(""))
			mVO.setNum("없 음");
		else
			mVO.setNum(num);
		
		if(id.equals(""))
			mVO.setId("없 음");
		else
			mVO.setId(id);
		
		if(pwd.equals(""))
			mVO.setPwd("없 음");
		else
			mVO.setPwd(pwd);
		
		request.setAttribute("member", mVO);
		RequestDispatcher rd = request.getRequestDispatcher("/jspexam/memberView.jsp");
		rd.forward(request,response);
	}

}



````

#### memberView.jsp

````java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.vo.MemberVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 확인창</title>
</head>
<body>
<h1>회원 정보</h1>
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
<a href="<%= request.getHeader("referer") %>">회원정보 재입력</a>>
</body>
</html>
````

---



### Session Scope 사용

#### LottoServlet2.java

````java

````

#### LottoServlet2.java

````java

````

#### LottoServlet2.java

````java

````

