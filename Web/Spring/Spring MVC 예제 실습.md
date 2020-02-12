# Spring MVC 예제 실습

[TOC]

## Calc 예제 : 기존 실습 변환

#### CalcController.java

```java
package my.spring.springedu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalcController  {
	@RequestMapping("/calc")
	public ModelAndView control(String sam, int num1, int num2) {
		ModelAndView mav = new ModelAndView();
		String result="나눗셈 연산시 두 번째 숫자는 0일 수 없습니다.";
		if(sam.equals("+"))
				result = Integer.toString(num1+num2);
		else if(sam.equals("-"))
				result = Integer.toString(num1-num2);
		else if(sam.equals("*"))
			result = Integer.toString(num1*num2);
		else if(sam.equals("/")) {
			if(num2==0) {
				mav.addObject("result", result);
				mav.setViewName("errorResult");
			}else
				result = Integer.toString(num1/num2);
		}
		mav.addObject("result", result);
		mav.setViewName("calcResult");
		return mav;
	}	

}
```



#### calcForm.html

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
<form method="get" action="/springedu/calc">
<input type="number" name="num1" placeholder="첫번째 숫자" class="c1" required>
<select name='sam'>
	<option value='+'>+</option>
	<option value='-'>-</option>
	<option value='*'>*</option>
	<option value='/'>/</option>
</select>
<input type="number" name="num2" placeholder="두번째 숫자" class="c1" required>
<input type="submit" value="제출" class="c2">
</form>
</body>
</html>
```



#### calcResult.jsp

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

<a href="<%= request.getHeader("referer") %>">입력화면</a>
<hr>
<h1>연산 요청 결과(Expression Language)</h1><hr>
<h2>결과 : <span>${requestScope.result}</span></h2>

<a href="${header.referer}">입력화면</a>

</body>
</html>
```





#### errorResult.jsp

```jsp
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
<h1>요청을 처리하는 동안 오류가 발생했어요</h1>
<h1>오류의 원인 : <span><%= result %></span></h1>
<hr>
<h1>요청을 처리하는 동안 오류가 발생했어요</h1>
<h1>오류의 원인 : <span>${requestScope.result}</span></h1>

<a href="${header.referer}">입력화면</a>

</body>
</html>
```





---

## VO 자동입력 실습

- VO 객체를 파라미터로 주면 는 자동으로 값을 세팅해준다

#### MemberController.java

```java
package my.spring.springedu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vo.MemberVO;

@Controller
public class MemberContoller  {
	@RequestMapping(value="/member", method=RequestMethod.POST)
	public ModelAndView control(MemberVO vo) {
		ModelAndView mav = new ModelAndView();
		String id = vo.getId();
		String name = vo.getName();
		String num = vo.getNum();
		String pwd = vo.getPwd();
        //null과 null 문자열 둘다 비교해주어야한다!
		if(vo.getId()==null||id.equals(""))
			vo.setId("없음");
		if(vo.getName()==null||name.equals(""))
			vo.setName("없음");
		if(vo.getNum()==null||num.equals(""))
			vo.setNum("없음");
		if(vo.getPwd()==null||pwd.equals(""))
			vo.setPwd("없음");
		mav.setViewName("memberView_EL");
		return mav;
	}

}
//이렇게 ModelAndView 안사용하고 String 으로 리턴해줘도 된다
//return 값은 jsp이름
	@RequestMapping(value="/member", method=RequestMethod.POST)
	public String control(MemberVO vo) {
		String id = vo.getId();
		String name = vo.getName();
		String num = vo.getNum();
		String pwd = vo.getPwd();
		if(vo.getId()==null||id.equals(""))
			vo.setId("없음");
		if(vo.getName()==null||name.equals(""))
			vo.setName("없음");
		if(vo.getNum()==null||num.equals(""))
			vo.setNum("없음");
		if(vo.getPwd()==null||pwd.equals(""))
			vo.setPwd("없음");
		return "memberView_EL";
	}
```



#### memberView_EL.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.MemberVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 확인창</title>
</head>
<body>
<h2>회원 정보</h2>
<hr>
<%
	MemberVO vo = (MemberVO)request.getAttribute("memberVO");
	if(vo!=null){
%>
<ul>
<li>회원 이름 : 
${memberVO.name}
</li>
<li>회원 계정 : 
${memberVO.id}
</li>
<li>회원 암호 : 
${memberVO.pwd}
</li>
<li>회원 전화번호 : 
${memberVO.num}
</li>

</ul>
<%
    }
%>
<%-- 
방법 2
<c:if test="${!empty memberVO}" >
		<ul>
			<li>${ memberVO.name }</li>
			<li>${ memberVO.id}</li>
			<li>${ memberVO.pwd }</li>
			<li>${ memberVO.num }</li>
		</ul>
    </c:if>
 --%>
</body>
</html>


```



#### memberForm.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 입력창</title>
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
<h1>회원 정보를 입력하십시오.</h1>
<hr>
<form method="post" action="/springedu/member">
<input type="text" name="name" placeholder="이름을 입력하세요" class="c1"><br>
<input type="text" name="num" placeholder="전화번호를 입력하세요" class="c1"><br>
<input type="text" name="id" placeholder="계정을 입력하세요" class="c1"><br>
<input type="password" name="pwd" placeholder="패스워드를 입력하세요" class="c1"><br><br>
<input type="submit" value="제출" class="c2">
<input type="reset" value="재작성" class="c2">
</form>
</body>
</html>
```



#### MemberVO.java

```java
package vo;

public class MemberVO {
	private String name;
	private String num;
	private String id;
	private String pwd;
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

```





---





## Product : counting, VO, ModelAndView, Session

- 과일 카운팅 하는 프로그램
- VO의 프로퍼티명과 쿼리명을 동일하게 줘서 자동으로 VO 카운팅
- ModelAndView 로 View (jsp)지정
- SessionScope로 카운팅 유지



#### ProductController.java (Controller=응답)

```java
package my.spring.springedu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import vo.ProductVO;
//Controller 라고 알려주는 annotation
@Controller 
//"count"라는 이름의 커멘드메서드를 Session에 보관해주는 annotation
//(SessionScope)
@SessionAttributes("count") 
public class ProductController  {
    //"count"라는 이름으로 커멘드메서드 정의(RequestScope)
	@ModelAttribute("count")
	public ProductVO count() {
		return new ProductVO();
	}
	//Controller 메서드
    //mapping명 /product 로 받으면 여기로 들어온다
	@RequestMapping(value="/product")
    //ModelAndView 객체로 응답하는 handle이라는 이름의 메서드
    //"count"라는 이름으로 저장된 ProductVO 객체를 vo라는 이름의 변수에 
    //담아서 사용하고 있다.
	public ModelAndView handle(@ModelAttribute("count")ProductVO vo) {		
			ModelAndView mav = new ModelAndView();
        	//ModelAndView 객체의 View를 세팅해줌 : productView.jsjp
			mav.setViewName("productView");
			return mav;
	}
    
    	/*이런 방식도 사용 가능 ..return 값을 String으로 view 값주기
	 * @RequestMapping(value="/product") public String
	 * handle(@ModelAttribute("count")ProductVO vo) {
	 * 
	 * return "productView"; }
	 */
	
	@RequestMapping(value="/productdel")
	public void handle(SessionStatus s) {
		s.setComplete();
	}

}
```



#### productView.jsp (View=응답)

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.ProductVO" %>
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

<h2>선택된 상품 정보는 다음과 같습니다(Expression Language)</h2>
<hr>
<ul>
<li>선택된 사과의 개수 : ${sessionScope.count.apple}</li>
<li>선택된 바나나의 개수 : ${sessionScope.count.banana}</li>
<li>선택된 한라봉의 개수 : ${sessionScope.count.halla}</li>
<!-- 
1. 현재 sessionScope 에는 "count" 라는 이름으로 ProductVo 객체가 저장되어있음
2. session에 저장되어있는 "count" ProductVO 객체 불러오기
	(sesssionScope 생략 가능 pageScope부터 차례대로 찾음)
3. VO 객체의 각 get 메서드 불러오기..EL에서는 get 빼고 첫글자 소문자
-->
</ul>
<hr><hr>
<h2>선택된 상품 정보는 다음과 같습니다(스크립트 태그)</h2>
<hr>
<%
	ProductVO vo = (ProductVO)session.getAttribute("count");
%>
<ul>
<li>선택된 사과의 개수 : <%= vo.getApple() %></li>
<li>선택된 바나나의 개수 : <%= vo.getBanana()%></li>
<li>선택된 한라봉의 개수 : <%= vo.getHalla() %></li>
</ul>
<!-- 
1. ProductVO 객체를 불러오는데 session 에서 "count"라는 이름으로 불러와
2. vo객체 부르고 각 get 메서드 불러오기
-->
<hr><hr>
<h2>선택된 상품 정보는 다음과 같습니다(액션 태그)</h2>
<hr>
<jsp:useBean id="count" class="vo.ProductVO" scope="session" />
<ul>
<li>선택된 사과의 개수 : <jsp:getProperty name="count" property="apple"/></li>
<li>선택된 바나나의 개수 : <jsp:getProperty name="count" property="banana"/></li>
<li>선택된 한라봉의 개수 : <jsp:getProperty name="count" property="halla"/></li>
</ul>
<!-- 
1. id : bean 이름을 설정 = count // class 설정 = vo.ProductVO // 
   scope 설정 = session
2. name : 자바빈 이름이 "count"인 객체를 불러와서 // property : 그 객체의 프로퍼티명대로 불러오기
-->
<a href="${header.referer}">상품선택화면</a>
</body>
</html>
```



#### ProductVO.java(Model=데이터,저장)

```java
package vo;

public class ProductVO {
	private int apple;
	private int banana;
	private int halla;
	public int getApple() {
		return apple;
	}
	public void setApple(int apple) {
		this.apple += apple;
	}
	public int getBanana() {
		return banana;
	}
	public void setBanana(int banana) {
		this.banana += banana;
	}
	public int getHalla() {
		return halla;
	}
	public void setHalla(int halla) {
		this.halla += halla;
	}

}

```



#### product.html(클라이언트=요청)

```html
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
    <!-- (**중요) query 명을 VO property 명과 동일하게 해야함!!  -->
	<a href="/springedu/product?apple=1"><img
		src="http://70.12.115.175:8000/mvc/images/apple.png"></a>
	<a href="/springedu/product?banana=1"><img
		src="http://70.12.115.175:8000/mvc/images/banana.png"></a>
	<a href="/springedu/product?halla=1"><img
		src="http://70.12.115.175:8000/mvc/images/halla.png"></a>
	<br>
	<br>
	<button onclick='aaa();'>장바구니 비우기</button>

	<script>
		function aaa() {
			var request = new XMLHttpRequest();
			request.onload = function() {
				if (request.status == 200) {
					var str = request.responseText;
					console.log(str);
					var jsObj = JSON.parse(str);
					if (jsObj.msg) {
						alert(jsObj.msg);
					}
				}
			};
			request.open('GET', '/springedu/productdel', true);
			request.send();
		};
	</script>

</body>

</html>



```





---



## Lotto2 : session 사용

- session 사용하여 카운트에 제한두는 Lotto



#### LottoController3.java

```java

package my.spring.springedu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import service.LottoService;
import vo.LottoVO;

@Controller

@SessionAttributes("count")
public class LottoController3 {

	@Autowired
	private LottoService lottoService;
	
    //카운팅 할때 int배열 쓰는거 생각하자!!
	@ModelAttribute("count")
	public int[] cnt() {
		return new int[1];
	}

	@RequestMapping("/lotto3")
	public String lottoProcess(LottoVO vo, @ModelAttribute("count") int[] cnt) {
		++cnt[0];
		if (cnt[0] < 4) {
			if (lottoService.getLottoProcess(vo.getLottoNum())) {
				vo.setResult("추카추카!!");
				cnt[0] = 5;
			} else {
				vo.setResult("아쉽네요 .. 다음 기회를!!");
			}
		} else if (cnt[0] >= 4) {
			vo.setResult("브라우저를 재기동해주세요");
		}
		return "lottoView3";
	}
}

```

#### LottoService.java

```java
package service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dao.LottoDAO;

//Service 인지 선언해주는 어노테이션
@Service
public class LottoService {
	public LottoService()  {
		System.out.println("LottoService 객체생성");
	}
	//lottoDAO에 의존적이다 ..따라서 lottoDAO 를 찾아서 생성해줘야 한다(LottoDAO.java로 감)
	//@Autowired 를 사용해서 자동으로 LottoDAO 객체 생성해서 사용하게끔
	@Autowired
	LottoDAO lottoDAO = null;
	//여기서 int lottoNum 은 VO 에서 가져온 숫자로 client 로부터 전달받은 숫자
	public boolean getLottoProcess(int lottoNum) {
		boolean result = false;		
		int randomNumber = lottoDAO.getLottoNumber();
		System.out.println("--- 난수: " + randomNumber);
		System.out.println("--- 입력한 수: " + lottoNum);
		if(randomNumber == lottoNum) 
			result = true;
		return result;
	}
} 
```

#### LottoDAO.java

```java
package dao;
import java.util.Random;
import org.springframework.stereotype.Repository;

//@Repository 는 DAO 에 특화된 어노테이션이다
//@Component 어노테이션을 써도 상관없지만
//@Component 어노테션이 가진 특성과 함께, =이 클래스를 어플리케이션 컨텍스트에 빈으로 등록
//DAO의 메소드에서 발생할 수 있는 unchecked exception들을 
//스프링의 DataAccessException으로 처리할 수 있다
@Repository
public class LottoDAO {
	public LottoDAO()  {
		System.out.println("LottoDAO 객체생성");
	}
	public int getLottoNumber() {
		Random rand = new Random();
		return rand.nextInt(6)+1;
	}
}

```

#### LottoVO.java

```java
package vo;
public class LottoVO {
	//client에서 전달된 쿼리명이 lottoNum이면 자동으로 가져와서 전달됨
	private int lottoNum;
	
	//반드시 client로 올 필요 없이 result 를 설정해준 객체..
	//그냥 View 와 Controller 가 공유하는 객체 result
	private String result;
	
	//------객체 선언 끝----------//
	
	public LottoVO()  {
		System.out.println("LottoVO Create object");
	}
	public int getLottoNum() {
		return lottoNum;
	}
	public void setLottoNum(int lottoNum) {
		this.lottoNum = lottoNum;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}	
}
```

#### lottoView3.jsp

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
<h1>로또 결과</h1>
<hr>
<h2>${ lottoVO.result }</h2>
<hr>
<a href="/springedu/resources/lottoForm3.html">재시도.....</a>
</body>
</html>
```

#### lottoForm3.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div{
display: inline-block;
width:120px;
height: 120px;
}
img{
width:100px;
height: 100px;
}
.shadow img {
transition: .5s ease;
}

.shadow img:hover{
box-shadow:
1px 1px #d9d9d9,
2px 2px #d9d9d9,
3px 3px #d9d9d9,
4px 4px #d9d9d9,
5px 5px #d9d9d9,
6px 6px #d9d9d9;
-webkit-transform: translateX(-3px);
transform: translateX(-3px);
transition: .5s ease;
}
</style>
</head>
<body>
<h1>Lotto Game</h1>
<div><a href="/springedu/lotto3?lottoNum=1" class ="shadow"><img src="images/1.png" alt="1"></a></div>
<div><a href="/springedu/lotto3?lottoNum=2" class ="shadow"><img src="images/2.png" alt="2"></a></div>
<div><a href="/springedu/lotto3?lottoNum=3" class ="shadow"><img src="images/3.png" alt="3"></a></div>
<div><a href="/springedu/lotto3?lottoNum=4" class ="shadow"><img src="images/4.png" alt="4"></a></div>
<div><a href="/springedu/lotto3?lottoNum=5" class ="shadow"><img src="images/5.png" alt="5"></a></div>
<div><a href="/springedu/lotto3?lottoNum=6" class ="shadow"><img src="images/6.png" alt="6"></a></div>
</body>
</html>
```



---

## TeamMaker : @RespnseBody, @RequestMapping value, VO 2개, @XmlRootElement

- 기본적으로 json과 xml 의 내용은 같음

#### MyteamController.java

```java
package my.spring.springedu;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vo.TeamMemberVO;
import vo.TeamVO;

@Controller
public class MyteamController {

	@RequestMapping(value = "/myteam/json", produces = "application/json; charset=utf-8")
	@ResponseBody
	public TeamVO teamMaker1() {
		TeamVO vo = new TeamVO();
		ArrayList<TeamMemberVO> list = new ArrayList<TeamMemberVO>();
		TeamMemberVO member = new TeamMemberVO();
		member.setName("최희정");
		member.setNicName("회장님");
		list.add(member);
		member = new TeamMemberVO();
		member.setName("김동규");
		member.setNicName("사장님");
		list.add(member);
		member = new TeamMemberVO();
		member.setName("오세호");
		member.setNicName("(제일 열심히하는)인턴");
		list.add(member);
		vo.setTeamName("꽃길 by 김세정");
		vo.setTeamMember(list);
		return vo;
	}
	

	@RequestMapping(value = "/myteam/xml", produces = "application/xml; charset=utf-8")
	@ResponseBody
	public TeamVO teamMaker2() {
		TeamVO vo = new TeamVO();
		ArrayList<TeamMemberVO> list = new ArrayList<TeamMemberVO>();
		TeamMemberVO member = new TeamMemberVO();
		member.setName("최희정");
		member.setNicName("회장님");
		list.add(member);
		member = new TeamMemberVO();
		member.setName("김동규");
		member.setNicName("사장님");
		list.add(member);
		member = new TeamMemberVO();
		member.setName("오세호");
		member.setNicName("(제일 열심히하는)인턴");
		list.add(member);
		vo.setTeamName("꽃길 by 김세정");
		vo.setTeamMember(list);
		return vo;
	}	
	
}
```

#### TeamMemberVO.java

```java
package vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TeamMemberVO {
	private String name;
	private String nicName;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNicName() {
		return nicName;
	}
	public void setNicName(String nicName) {
		this.nicName = nicName;
	}
	
}

```

#### TeamVO.java

```java
package vo;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class TeamVO {
	private String teamName;
	private ArrayList<TeamMemberVO> teamMember;
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public ArrayList<TeamMemberVO> getTeamMember() {
		return teamMember;
	}
	public void setTeamMember(ArrayList<TeamMemberVO> teamMember) {
		this.teamMember = teamMember;
	}
	
	
}

```



#### myteam.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<title>New Web Project</title>
<link href="https://fonts.googleapis.com/css?family=Kirang+Haerang:400" rel="stylesheet">
<style>
h1{
	font-size : 60px;
	font-family : "Kirang Haerang";
	color : #f8585b;
	text-shadow : 5px 5px gold;
}
h2{
	font-family : "Kirang Haerang";
	color : #f8585b;
	text-shadow : 2px 2px purple;
}
span{
	font-size : 95px;
}
button {
	font-family : "Kirang Haerang";
	font-color : gold;
    width:150px;
    background-color: #f8585b;
    border: none;
    color:#fff;
    padding: 5px 0;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 25px;
    margin: 4px;
    cursor: pointer;
	border-radius:10px 0 10px 0;
	box-shadow : 5px 5px gold;
}
body{
	padding-top : 10px;
	background-image : url('https://cdn.crowdpic.net/detail-thumb/thumb_d_383E3626EA44DD23DE20DBD56B672D0D.jpg');
	background-repeat : no-repeat;
	background-size : cover;
}
</style>
<script>
//팀이름 출력
	function team(target) {
		var request = new XMLHttpRequest();
		request.onload = function(event) {
			if (request.status == 200) {
				var str = request.responseText;
				var jsobj = JSON.parse(str);
				var result = document.getElementById("result");
				result.innerHTML = "<br><h2><span>"+jsobj.teamName+"</span></h2>";				
			}
		};
		request.open('GET', '/springedu/'+target, true);
		request.send();
	}
//팀 멤버 출력
	function member(target) {
		var request = new XMLHttpRequest();
		request.onload = function(event) {
			if (request.status == 200) {
				var str = request.responseText;
				var jsobj = JSON.parse(str);
				var result = document.getElementById("result");
				result.innerHTML="";
				for(var i=0;i<3;i++)
					result.innerHTML += "<h2>"+
					"<span>"+jsobj.teamMember[i].name+"</span>"+" call Me :"+jsobj.teamMember[i].nicName+"</h2>";			
			}
		};
		request.open('GET', '/springedu/'+target, true);
		request.send();
	}
</script>
</head>
<body>
	<h1>우리팀 소개</h1>
	<button onclick="team('myteam/json')">우리 팀명</button>
	<button onclick="member('myteam/json')">우리 팀원</button>
	<output id="result"></output>
</body>
</html>

```

