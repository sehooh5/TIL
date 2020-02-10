# Spring MVC 예제 실습

[TOC]

### Calc 예제 : 기존 실습 변환

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

### VO 자동입력 실습

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

