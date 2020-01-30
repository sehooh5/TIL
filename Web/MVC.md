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

#### EduServlet 첫번째 실습

- 객체공유 아무것도 사용하지 않음
- html -> servlet -> jsp

##### eduForm.html

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



##### EduServlet.java

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



##### gradeABCD.java

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

#### Request Scope (모델VO 사용)

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

##### memberView.jsp

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





#### RequestScope (VO 사용 X)

##### calcForm.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연산해봅시다</title>
<style>
h1{
	text-shadow : 2px 2px 2px #d9d9d9;
}
input.c1{
	width : 190px;
	height : 20px;
	border-radius : 5px;
	box-shadow : -2px -2px #d9d9d9;
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
<h1>연산할 두 개의 숫자를 입력하고 연산자를 선택하시오.</h1>
<hr>
<form method="get" action="/mvc/calc">
<input type="text" name="num1" placeholder="첫번째 숫자" class="c1">
<select name='sam'>
	<option value='+'>+</option>
	<option value='-'>-</option>
	<option value='*'>*</option>
	<option value='/'>/</option>
</select>
<input type="text" name="num2" placeholder="두번째 숫자" class="c1">
<input type="submit" value="제출" class="c2">
</form>
</body>
</html>
```



##### CalcServlet.java

```java
package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));
		String sam = request.getParameter("sam");
		String result = "";
		String url = "/jspexam/calcResult.jsp";
		System.out.println(num1);
		System.out.println(num2);
		System.out.println(sam);
		System.out.println(url);
		
		switch(sam) {
		case "+" : result = Integer.toString(num1+num2);
		break;
		case "-" : result = Integer.toString(num1-num2);
		break;
		case "*" : result = Integer.toString(num1*num2);
		break;
		case "/" : 
			if(num2==0) {
				url="/jspexam/errorResult.jsp";
				result = "나눗셈 연산시 두 번째 숫자는 0일 수 없습니다.";
			}else
				result = Integer.toString(num1/num2);
		}
		System.out.println(result);

		request.setAttribute("result", result);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request,response);
	}

}
```



##### calcResult, errorResult.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연산 성공화면</title>
<style>
span{
	color : red;
}
</style>
</head>
<body>
<%
	String result = (String)request.getAttribute("result");
%>
<h1>연산 요청 결과</h1><hr>
<h2>결과 : <span><%= result %></span></h2>

<a href="<%= request.getHeader("referer") %>">입력화면</a>>

</body>
</html>




<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연산 실패화면</title>
<style>
span{
	color : magenta;
}
</style>
</head>
<body>
<%
	String result = (String)request.getAttribute("result");
%>
<h1>요청을 처리하는 동안 오류가 발생했어요</h1><hr>
<h1>오류의 원인 : <span><%= result %></span></h1>

<a href="<%= request.getHeader("referer") %>">입력화면</a>>

</body>
</html>
```



---



#### Session Scope 사용

##### product.html

````java
<!DOCTYPE html>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>형제상회</title>
<style>
img {
	width: 150px;
	height: 150px;
}
</style>
</head>

<body>
	<h2>구매하고자 하는 상품을 선택하세요</h2>
	<hr>
	<a href="/mvc/product?pid=p001"><img
		src="http://70.12.115.175:8000/mvc/images/apple.png"></a>
	<a href="/mvc/product?pid=p002"><img
		src="http://70.12.115.175:8000/mvc/images/banana.png"></a>
	<a href="/mvc/product?pid=p003"><img
		src="http://70.12.115.175:8000/mvc/images/halla.png"></a>
	<br>
	<br>
	<button onclick='aaa("del");'>장바구니 비우기</button>

	<script>
		function aaa(ee) {
			var request = new XMLHttpRequest();//엔진객체 초기화
			request.onload = function() {
				if (request.status == 200) {
					var str = request.responseText;
					console.log(str);
					var jsObj = JSON.parse(str);
					if (jsObj.msg) {//삭제할때 메시지 내보내기
						alert(jsObj.msg);
					}
				}
			};
			request.open('GET', '/mvc/product?pid=' + ee, true);
			request.send();
		};
	</script>

</body>

</html>



````

##### ProductVO.java

````java
package model.vo;

public class ProductVO {
	private int Anum;
	private int Bnum;
	private int Hnum;
	public int getAnum() {
		return Anum;
	}
	public void setAnum(int Anum) {
		this.Anum += Anum;
	}
	public int getBnum() {
		return Bnum;
	}
	public void setBnum(int Bnum) {
		this.Bnum += Bnum;
	}
	public int getHnum() {
		return Hnum;
	}
	public void setHnum(int Hnum) {
		this.Hnum += Hnum;
	}

}

````

##### ProductServlet.java

````java
package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.vo.ProductVO;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		//ProductVO vo 는 가비지 객체가 계속 생성된다
		//ProductVO vo = new ProductVO();
		
		if(session.getAttribute("cnt")==null)
			session.setAttribute("cnt", new ProductVO());
        								//VO 객체 최초 생성 한번만!
		ProductVO session_c = (ProductVO)session.getAttribute("cnt");
		String id = request.getParameter("pid");
		String url = "/jspexam/productView.jsp";
		
		if(id.equals("p001")) {
			session_c.setAnum(1);
		}
		else if(id.equals("p002")) {
			session_c.setBnum(1);
		}
		else if(id.equals("p003")) {
			session_c.setHnum(1);
		}
		else if(id.equals("del")) {
			session = request.getSession(false);
            //false 는 원래 있는 세션 사용하는것
			if(session != null)
				session.invalidate();
			url = "/jspexam/productDel.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request,response);
	}

}
````

##### productView,Del.jsp

````java
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
<h2>선택된 상품 정보는 다음과 같습니다</h2>
<hr>
<%
	ProductVO vo = (ProductVO)session.getAttribute("cnt");
%>
<ul>
<li>선택된 사과의 개수 : 
<%= vo.getAnum() %>
</li>
<li>선택된 바나나의 개수 : 
<%= vo.getBnum()%>
</li>
<li>선택된 한라봉의 개수 : 
<%= vo.getHnum() %>
</li>
</ul>
<hr>
<a href="<%= request.getHeader("referer") %>">상품선택화면</a>>
</body>
</html>
    

    
//////////////그냥 json 형식으로 전달//////////////////    
<%@ page contentType="application/json; charset=utf-8"%>
{ 
   "msg" : "장바구니가 비어졌어요!"
}
````



---



#### visitorForm.html (DAO 예제)

````html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>글을 남겨 주세요</h1>
<hr>
<form method="post" action="/mvc/visitor">
이름 : <input type="text"  name="name" >
<br>
남기고자 하는 의견 : <br>
<textarea rows="10" cols="50" name = "content" ></textarea>
<br>
<input type="submit" value="등록">
<input type="reset" value="재작성">
</form>
<hr>
<form method="get" action="/mvc/visitor">
검색어 : <input type="search" name="keyword">
<input type="submit" value="검색">
<hr>
<a href="/mvc/visitor">방명록 리스트 보기</a>
</form>
</body>
</html>
````



#### VisitorServlet.java (DAO 예제)

````java
package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.VisitorDAO;
import model.vo.VisitorVO;
@WebServlet("/visitor")
public class VisitorServletDB extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		
		VisitorDAO dao = new VisitorDAO();
		if(keyword == null) {
			List<VisitorVO> list = dao.listAll();
			for(VisitorVO vo : list) {
				System.out.println(vo.getMemo());
			}
			request.setAttribute("list", list);
		} else {
			List<VisitorVO> list = dao.search(keyword);
			if(list.size() == 0) {
				request.setAttribute("msg", keyword+"를 담고있는 글이 없어용");
			} else {
				request.setAttribute("list", list);
			}
		}
		request.getRequestDispatcher("/jspexam/visitorView.jsp")
        .forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		
		String name = request.getParameter("name");
		String memo = request.getParameter("content");
		VisitorDAO dao = new VisitorDAO();
		VisitorVO vo = new VisitorVO();
		vo.setName(name);
		vo.setMemo(memo);
		boolean result = dao.insert(vo);
		if(result) {
			request.setAttribute("msg", name+"님의 글이 성공적으로 입력되었어요!!..");
		} else {
			request.setAttribute("msg", name+"님의 글이 입력에 실패했어요!!");
		}
		request.getRequestDispatcher("/jspexam/visitorView.jsp")
		           .forward(request, response);
	}
}
````



#### VisitorDAO.java (DAO 예제)

````java
package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.VisitorVO;

public class VisitorDAO {
	public List<VisitorVO> listAll() {
		List<VisitorVO> list = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		try (Connection conn = DriverManager.getConnection
				("jdbc:oracle:thin:@localhost:1521:xe", "jdbctest", 
						"jdbctest");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery
				("select name, to_char(writedate, "
				+ "'yyyy\"년\" mm\"월\" dd\"일\"'), memo from visitor");) {
			VisitorVO vo;
			while(rs.next()) {
				vo = new VisitorVO();
				//숫자들의 의미? 컬럼값
				vo.setName(rs.getString(1));
				vo.setWriteDate(rs.getString(2));
				vo.setMemo(rs.getString(3));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<VisitorVO> search(String keyword) {
		List<VisitorVO> list = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		try (Connection conn = DriverManager.getConnection
				("jdbc:oracle:thin:@localhost:1521:xe", "jdbctest", "jdbctest");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery
				("select name, to_char(writedate, 'yyyy\"년\" mm\"월\" dd\"일\"'), memo "
						+"from visitor where memo like '%"+keyword+"%'");) {
			VisitorVO vo;
			while(rs.next()) {
				vo = new VisitorVO();
				vo.setName(rs.getString(1));
				vo.setWriteDate(rs.getString(2));
				vo.setMemo(rs.getString(3));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//DAO에 데이터를 insert 할 목적으로 VO 를 사용한다
	public boolean insert(VisitorVO vo) {
		boolean result = true;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		try (Connection conn = DriverManager.getConnection
				("jdbc:oracle:thin:@localhost:1521:xe", "jdbctest", "jdbctest");
				//PreparedStatement 뭐였지? 물음표 써서 사용하는거..
				//동적 파라미터를 사용한다.
				PreparedStatement pstmt = conn.prepareStatement(
						"insert into visitor values(?, sysdate, ?)");) {
			pstmt.setString(1, vo.getName());//1은 첫번째 물음표
			pstmt.setString(2,  vo.getMemo());//2는 두번째 물음표
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}
}

````



#### VisitorVO.html (DAO 예제)

````java
package model.vo;

public class VisitorVO {
	private String name;
	private String writeDate;
	private String memo;
	public String getName() {
		return name;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public String getMemo() {
		return memo;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "VisitorVO [name=" + name + ", writeDate=" + writeDate + ", memo=" + memo + "]";
	}	
}

````



#### visitorView.jsp (DAO 예제)

````jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.vo.VisitorVO, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	td {
		border-bottom : 1px dotted green;
	}
	tr:hover {
		background-color : pink;
		font-weight : bold;
	}
	td:nth-child(3) {
		width : 400px;
	}
</style>
</head>
<body>
<%
	List<VisitorVO> list = (List<VisitorVO>)request.getAttribute("list");
    if (list != null) {
%>
    	<h2>방명록 글 리스트</h2><hr>
    	<table>    
<%	
    	for(VisitorVO vo : list) { 	   
%>
			<tr>
				<td><%= vo.getName() %></td>
				<td><%= vo.getWriteDate() %></td>
				<td><%= vo.getMemo() %></td>		
			</tr>
<%
    	}
%>
    	</table>
<%
    } else {
%>
		<h2>${msg}</h2>
<%
    }
%>
<hr>
<a href="/mvc/htmlexam/visitorForm.html ">방명록 홈 화면으로 가기</a>

</body>
</html>




````



#### visitorForm.html (DAO 예제)

````java

````

