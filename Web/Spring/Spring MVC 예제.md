# Spring MVC 예제

[TOC]



#### HelloController.java

```java
package my.spring.springedu;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


//Controller 역할의 bean 객체임을 알리고 객체 생성
@Controller
public class HelloController {	
	//서버 기동시 HellowController 객체 미리 만듬
	public HelloController() {
		System.out.println("HelloController Create object");
	}
	//@WebServlet 과 비슷하지만 매핑명을 메서드에 준다
    //컨트롤러 메서드 = 핸들러 메서드
    //클라이언트 요청을 실행할 수 있는 메서드
	@RequestMapping("/hello")
	public ModelAndView xxx(HttpServletRequest req){		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("helloView");
		mav.addObject("msg", getMessage());
		return mav;
	}
	private String getMessage(){
		int hour = Calendar.getInstance()
				.get(Calendar.HOUR_OF_DAY);		
		if(hour >= 6 && hour <= 10){
			return "Good Morning!!";
		}else if(hour >= 12 && hour <= 15){
			return "Good Afternoon";
		}else if(hour >= 18 && hour <= 22){
			return "Good Evening!!";
		}else{ 
			return "Hello!!";
		}
	}
}
```



#### helloView.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<h1>Result of processing the view</h1>
<hr>
<%
String result = (String)request.getAttribute("msg");
%>
expression tag : <%= result %>
<hr>
EL : ${ msg }
</body>
</html>
```



---

### 요청한 uri 쓰고싶을때 사용하는 기능 있음



#### MultiController.java

```java
package my.spring.springedu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class MultiController {	
	@RequestMapping(value="/select")
	public String select() {
		System.out.println("select ............");
		return  "viewTest";
	}
	@RequestMapping(value="/search")
	public String search() {
		System.out.println("search ............");
		return "viewTest";
	}
	@RequestMapping(value="/insert")
	public String insert(int pageno) {
		System.out.println("insert ............"+pageno);
		return  "viewTest";
	}
}



```



#### viewTest.jsp : uri 찾는법

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
    <!-- uri 보관하니까 필요할떄 사용해라! -->
<h2><%= request.getAttribute(
		"javax.servlet.forward.request_uri") %> Request successful!!!</h2>
<hr>
<h2>Request Method : ${ pageContext.request.method }</h2>
<hr>
<h2>Query Value : ${ param.pageno }</h2>
<hr>
<a href='${ header.referer }'>To Form Page....</a>
</body>
</html>
```



#### multi.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	a, form, button {
		margin : 10px;
	}

</style>
</head>
<body>
<h1>MultiController Request</h1>
<hr>
<a href="/springedu/select?pageno=100" style="text-decoration:none">SELECT Request</a><br>
<hr>
<button onclick="location.href='/springedu/search?pageno=1000'">SEARCH Request</button>
<hr>
<form method="get" action="/springedu/insert">
<input type="hidden" name="pageno" value="10">
<input type="submit" value="GET Request">
</form>
<hr>
<form method="post" action="/springedu/insert">
<input type="hidden" name="pageno" value="10">
<input type="submit" value="POST Request">
</form>
</body>
</html>
```



---



### 컨트롤러에 method 지정하는 방법



#### **RequestMethodController.java

```java
package my.spring.springedu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RequestMethodController {

	public RequestMethodController() {
		System.out.println("RequestMethodController 객체생성");
	}

	@RequestMapping(value = "/requestmethod", method = RequestMethod.GET)
	public String myGet1() {
		System.out.println("GET ............");
		return "getResult";
	}

	@RequestMapping(value = "/requestmethod", method = RequestMethod.POST)
	public String myPost() {
		System.out.println("POST ............");
		return "postResult";
	}
}
```



#### requestmethod.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>requestmethod.html</title>
</head>
<body>
<form method="get" action="/springedu/requestmethod">
<input type="submit" value="GET-request">
</form>
<br><a href="/springedu/requestmethod">GET request</a><br><br>
<form method="post" action="/springedu/requestmethod">
<input type="submit" value="POST-request">
</form>
</body>
</html>
```



#### postResult.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   h1 {
   	color : pink;
   }

</style>
</head>
<body>
<h1>response VIEW : <%= request.getRequestURI() %></h1>
<h2><%= request.getMethod() %></h2>
</body>
</html>
```



#### getResult.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   h1 {
   	color : lime;
   }

</style>
</head>
<body>
<h1>response VIEW : <%= request.getRequestURI() %></h1>
<h2><%= request.getMethod() %></h2>
</body>
</html>

```



---



### 매핑명 동적 세팅,  모델객체를 매개변수로 받음



####  PathController.java

```java
package my.spring.springedu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class PathController {
    //매핑명을 동적으로 적용하는 방법
    //모델객체를 매개변수로 받았다
    //장점 :  재사용이 가능하다
	@RequestMapping
	     (value="/character/detail/{name}/{number}")
    //request 객체에 보관된다 number, name, model
	 public String getAllBoards(@PathVariable("number") int num, 
	                             @PathVariable String name, Model model){
		 System.out.println(model.getClass().getName());
	     if(name.equals("kakao")) {
	    	 if (num == 1) 
	    		 model.addAttribute("imgname", "ryan");
	    	 else if (num == 2) 
	    		 model.addAttribute("imgname", "muzicon");
	    	 else if (num == 3) 
	    		 model.addAttribute("imgname", "apeach");
	    	 else if (num == 4) 
	    		 model.addAttribute("imgname", "jayg");
	    	 else if (num == 5) 
	    		 model.addAttribute("imgname", "frodoneo");
	    	 else if (num == 6) 
	    		 model.addAttribute("imgname", "tube");
	     } else if (name.equals("line")) {
	    	 if (num == 1) 
	    		 model.addAttribute("imgname", "brown");
	    	 else if (num ==2) 
	    		 model.addAttribute("imgname", "james");
	    	 else if (num == 3) 
	    		 model.addAttribute("imgname", "cony");
	    	 else if (num == 4) 
	    		 model.addAttribute("imgname", "edward");
	    	 else if (num == 5) 
	    		 model.addAttribute("imgname", "leonard");
	    	 else if (num == 6) 
	    		 model.addAttribute("imgname", "moon");
	    	 else if (num == 7) 
	    		 model.addAttribute("imgname", "sally");
	    	 else if (num == 8) 
	    		 model.addAttribute("imgname", "jessica");
	     }
	     return "detailView";
	 }
}

```



#### detailView.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="text-align : center">
<H1>${ name } - Introducing characters.</H1>
<hr>
<img src=' /springedu/resources/images/${ imgname }.jpg '>
</body>
</html>

```



---

### @RequestParam 사용



#### QueryStringController1.java

```java
package my.spring.springedu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class QueryStringController1 {
	//매개변수랑 같은 이름의 쿼리를 줘야한다
	@RequestMapping("/querystring1")
	public ModelAndView proc(String name) {
		ModelAndView mav = new ModelAndView();
		System.out.println("["+name+"]");
		mav.addObject("spring", name);//자동으로 request 에 보관(기본)
		mav.setViewName("queryView1");//=queryView1.jsp
		return mav;
	}	
	//query값 안주면 500 error : null 값이 가는데 null 은 int 로 변환 불가
	@RequestMapping("/querystring2")
	public ModelAndView proc(int number) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("spring", number);
		mav.setViewName("queryView1");
		return mav;
	}	
	//annotation @RequestParam 추가된 예제
	//매개변수를 정의하는 annotation 사용
	//@RequestParam 사용하면 더 세세한 세팅 가능
	@RequestMapping("/querystring3")
	public ModelAndView proc(String name, 
			                   @RequestParam("num")int number) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("spring", name+":"+number);
		mav.setViewName("queryView1");
		return mav;
	}
	//required=false 는 필수는 아니라는 말 null 받기 가능! 디폴트는 true
	//defualtValue 는 쿼리값 안왔을때 기본값 설정
	@RequestMapping("/querystring4")
	public ModelAndView proc(
	@RequestParam("myname1") String name1,
	@RequestParam(value="myname2", required=false) String name2,
	@RequestParam(defaultValue="10") int number1, 
	@RequestParam(value="NUM2", defaultValue="100")int number2){
		ModelAndView mav = new ModelAndView();
		mav.addObject("spring", name1+":"+(number1 + number2) 
				+":"+name2);
		mav.setViewName("queryView1");
		return mav;
	}
}
```



#### queryView1,2,3.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP for response</title>
</head>
<body>
<h2>From QueryStringController1
                Forward to queryView1.jsp</h2>
<hr>
<!-- spring 으로 보관된 객체 찾아오기 -->
<!-- EL 일때는 null 값을 안보여주고 아무것도 출력 안한 -->
<h3>Data passed as spring name(EL) : ${ spring }</h3>
<!-- Expression Tag 는 null 을 보여줌 -->
<h3>Data passed as spring name(Expression Tag) : 
                        <%= request.getAttribute("spring") %></h3>
</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.QueryVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>응답용JSP</title>
</head>
<body>
<h2>Forward from QueryStringController2 to queryView2.jsp</h2>
<hr>
<%
	QueryVO vo = (QueryVO) request.getAttribute("queryVO");
    if(vo != null) {
%>
		<ul>
			<li>${ queryVO.testName }</li>
			<li>${ queryVO.testAge }</li>
			<li>${ queryVO.testAddr }</li>
		</ul>
<%
    }
%>
<h3>Total Query String : ${ spring }</h3>
</body>
</html>



<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>응답용JSP</title>
</head>
<body>
<h2>Forward from QueryStringController2 to queryView3.jsp</h2>
<hr>
	<c:if test="${!empty queryVO}" >
		<ul>
			<li>${ queryVO.testName }</li>
			<li>${ queryVO.testAge }</li>
			<li>${ queryVO.testAddr }</li>
		</ul>
    </c:if>
<h3>Total Query String : ${ spring }</h3>
</body>
</html>
```





### VO 사용

#### QueryStringController2.java

```java
package my.spring.springedu;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vo.QueryVO;

//**매개변수에 맞춰서 Controller 메서드가 달라는 값을 다 전달해줌
//여기서는 VO, HttpRequest, 기본형, 없는거, Locale 확인가능
@Controller
public class QueryStringController2 {	
	//없으면 세팅 안하고 0
	//VO사용하면 알아서 VO 객체 만들어서 사용할 수 있게끔 세팅해줌
	//VO객체를 request 객체에 보관도 해준다..
	//이름 = queryVO라고 보관한다
	@RequestMapping("/querystring5")
	public ModelAndView proc(QueryVO vo) {
		ModelAndView mav = new ModelAndView();
		String name = vo.getTestName();
		int age = vo.getTestAge();
		String addr = vo.getTestAddr();
		mav.addObject("spring", name + "-" + age + "-" + addr);
		mav.setViewName("queryView3");
		return mav;
	}	
	//요청방식이 Post 일때만 사용....지정안하면 get,post 둘다 사용가능
	@RequestMapping(value = "/querystring6", 
			                      method = RequestMethod.POST)
	public ModelAndView procPost(QueryVO vo) {
		ModelAndView mav = new ModelAndView();
		String name = vo.getTestName();
		int age = vo.getTestAge();
		String addr = vo.getTestAddr();
		mav.addObject("spring", name + "@" + age + "@" + addr);
		mav.setViewName("queryView3");
		return mav;
	}
	//HttpServletRequest request 선언
	//request.getParameter 로 직접 추출한다!
	//queryVO 로 저장된게 아니어서 jsp의 조건문에 걸린다
	//따라서 spring 으로 보관된 객체만 추출한 결과가 나오게 된다
	@RequestMapping("/querystring7")
	public ModelAndView proc(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String name =request.getParameter("testName");
		int age = Integer.parseInt(request.getParameter("testAge"));
		String addr = request.getParameter("testAddr");
		mav.addObject("spring", name + "#" + age + "#" + addr);
		mav.setViewName("queryView3");
		return mav;
	}
	//위치 저장해주는 기능
	@RequestMapping(value="/locale.do")
	public ModelAndView proc(Locale l) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("spring", "Processing locale ............"+
		       l.getDisplayCountry()+"_"+l.getDisplayLanguage());
		mav.setViewName("queryView3");
		return mav;
	}
}

```



#### queryHtml.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Get the Query String as a Model object(VO)</h1>
<hr>
<a href="/springedu/querystring5?testName=aaa&testAge=10&testAddr=bbb">querystring5-Request(Include query)</a>
<hr>
<a href="/springedu/querystring5">querystring5-Request(Exclude query)</a>
<hr>
<form method="get"  action="/springedu/querystring5">
<input type="text" placeholder="Please enter your name" name="testName" required><br>
<input type="number" placeholder="Please enter your age" name="testAge" required><br>
<input type="text" placeholder="Please enter your address" name="testAddr" required><br>
<input type="submit" value="querystring5-Request">
</form>
<hr>
<form method="post"  action="/springedu/querystring6">
<input type="text" placeholder="Please enter your name" name="testName" required><br>
<input type="number" placeholder="Please enter your age" name="testAge" required><br>
<input type="text" placeholder="Please enter your address" name="testAddr" required><br>
<input type="submit" value="querystring6-Request">
</form>
<hr>
<form method="post"  action="/springedu/querystring7">
<input type="text" placeholder="Please enter your name" name="testName" required><br>
<input type="number" placeholder="Please enter your age" name="testAge" required><br>
<input type="text" placeholder="Please enter your address" name="testAddr" required><br>
<input type="submit" value="querystring7-Request">
</form>
</body>
</html>
```



---

### VO 객체 보관이름 변경&재요청 redirect



#### StepController.java

```java
package my.spring.springedu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vo.StepVO;
// POJO
@Controller
public class StepController {		
	@RequestMapping(value="/step",
			 method=RequestMethod.POST)
	//(@ModelAttribute 는 VO 를 자동 보관할 때 이름 지정할 수 있음
	public String memberHandle(@ModelAttribute("kkk") StepVO vo) {
		//기본 요청재지정은 forward
		//다시 입력하고 싶게 재요청 하고싶을 때 redirect : context path 제외한 나머지값 주기
		if(vo.getAge() < 18)
			return "redirect:/resources/stepForm.html";
		System.out.println("[ Information for the passed Command object ]");
		System.out.println(vo.getName());
		System.out.println(vo.getPhoneNumber());
		System.out.println(vo.getAge());
		return  "stepOutput";
	}
}
```



---

### TestModel

- ModelAttribute 연습



#### TestModelController1.java

```java
package my.spring.springedu;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import vo.MyVO;
@Controller
public class TestModelController1 {
	//[[[[[[[커맨드 메서드]]]]]]]]]]
	//@ModelAttribute 어노테이션이 붙어있는 메서드들이 자동으로 호출된다
	//실행시 마다 호출되고 Controller 메서드 호출 전에 호출된다
	//여기서는 Controller 메서드는 public String handle()
	//커맨드객체에 무엇을 보관할지 설정하기 위하여 ModelAttribute 를 사용
	//커맨드 객체에 여러 객체들을 보관하고 사용하고싶을때 자동화 할때 사용한다.
	//어노테이션 안의 이름으로 return 값을 저장해준다 
	@ModelAttribute("v1")
	public String createString() {
		System.out.println("Creating an object automatically 1");
		return "TEST!!";
	}
	@ModelAttribute("v2")
	public int[] createArray() {
		System.out.println("Creating an object automatically 2");
		return new int[]{10, 20, 30, 40, 50};
	}
	@ModelAttribute("v3")
	public MyVO createVO() {
		System.out.println("Creating an object automatically 3");
		//client 의 값을 받아오는 것이 아닌
		//여기서 입력 해주거나
		//DB 연동해서 입력해주고 사용하고 싶을 때 사용
		MyVO vo = new MyVO();
		vo.setMyColor("yellow");
		vo.setMyNumber(23);
		return vo;
	}	
	@ModelAttribute("v4")
	public Date createDate() {
		System.out.println("Creating an object automatically 4");		
		return new Date();
	}	
	@ModelAttribute("v5")
	public ArrayList<String> createList() {
		System.out.println("Creating an object automatically 5");
		ArrayList<String> list = new ArrayList<String>();
		list.add("ABC");
		list.add("XYZ");
		list.add("123");
		return list;
	}	
	@RequestMapping("/testmodel1")
	public String handle() {
		System.out.println("handle() Method Call");		
		return "modelResult";
	}
}
```



#### MyVO.java

```java
package vo;

public class MyVO {
	private int myNumber;
	private String myColor;
	public int getMyNumber() {
		return myNumber;
	}
	public void setMyNumber(int myNumber) {
		this.myNumber = myNumber;
	}
	public String getMyColor() {
		return myColor;
	}
	public void setMyColor(String myColor) {
		this.myColor = myColor;
	}
	@Override
	public String toString() {
		return "MyVO [myNumber=" + myNumber + ", myColor=" + myColor + "]";
	}	
}

```



#### modelResult.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 테스트 예제</title>
</head>
<body>
<h2>Receiving data from the controller</h2>
<hr>
<ul>
<li>${ v1 }</li>
<li>Array</li>
	<ul>
	<li>${ v2[0] }</li>
	<li>${ v2[1] }</li>
	<li>${ v2[2] }</li>
	<li>${ v2[3] }</li>
	</ul>
<li>${ v3 }</li>
<li>${ v4 }</li>
<li>List</li>
	<ul>
	<li>${ v5[0] }</li>
	<li>${ v5[1] }</li>
	<li>${ v5[2] }</li>	
	</ul>
</ul>
</body>
</html>



```



### 저장된 커멘드 메서드 값을 사용하는 예제

- Controller  메서드가 여러개 있을 때 사용하면 좋다

#### TestModelController2.java

```java
package my.spring.springedu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class TestModelController2 {
	@ModelAttribute("data1")
	public int createModel1() {	
		System.out.println("createModel() call - data1");
		return 100;
	}
	@ModelAttribute("data2")
	public int createModel2() {	
		System.out.println("createModel() call - data2");
		return 200;
	}
    
    //커멘드 메서드를 지정해주고 @ModelAttribute 를 설정해주면
	//data1 이름으로 저장된 값을 vo1 에 넣어준다
	//data2 이름으로 저장된 값을 vo2 에 넣어준다
	//query 를 받는것이 아니라 특정 커멘드 객체를 받는 개념
	//**Controller 메서드가 여러개 있을 때 사용하면 좋다
	@RequestMapping(value="/testmodel2")
	public String handle(@ModelAttribute("data1") int vo1, 
			      @ModelAttribute("data2") int vo2) {
		System.out.println("handle() : "+vo1 + " - " + vo2);
		System.out.println("=============================");
		return "modelResult2";
	}	
}



```



### Session 을 사용한 커맨드 객체 보관

#### TestModelController3.java

```java
package my.spring.springedu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
@Controller
//data1 이름으로 보관되는 커맨드 객체는 Session 에 보관한다
//data1 = sessionScope ////data2 = requestScope
//[[[Session 객체에 보관하는 방법 ]]]ㄴ
//1. 매개변수 를 HttpSession 오로 하는 방법
//2. HttpServletRequest 에서 Session 에 보관 하는 방법
//3. @SessionAttributes - @ModelAttribute 설정으로 보관하는 방법
@SessionAttributes("data1")
public class TestModelController3 {
	@ModelAttribute("data1")
	public StringBuffer createModel1() {	
		System.out.println("createModel1() 호출 - data1(session)");
		return new StringBuffer();
	}
	@ModelAttribute("data2")
	public StringBuffer createModel2() {	
		System.out.println("createModel2() 호출 - data2(request)");
		return new StringBuffer();
	}
	
	//마지막 매개변수 str 은 client 에서 전달을 꼭 해줘야 한다
	@RequestMapping(value="/testmodel3")
	public String handle(@ModelAttribute("data1") StringBuffer vo1, 
			@ModelAttribute("data2") StringBuffer vo2, String str) {
		vo1.append(str+":");
		vo2.append(str+"@");
		System.out.println("handle 에서 출력 : "+vo1 + " - " + vo2);
		System.out.println("=============================");
		return "modelResult2";
	}	
}
```



### Count



#### CountController.java

```java
package my.spring.springedu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import vo.CountVO;
@Controller 
@SessionAttributes({"count1", "count2"})
public class CountController {
	//얘네 둘은 Session 객체에 보관,그리고 한번만 호출된다
	@ModelAttribute("count1")
	public CountVO countMethod1() {	
		System.out.println("countMethod1 호출 - count1");
		return new CountVO();
	}
	@ModelAttribute("count2")
	public CountVO countMethod2() {
		System.out.println("countMethod2 호출 - count2");		
		return new CountVO();
	}	
	@RequestMapping(value="/count")
	public void handle(@ModelAttribute("count1")CountVO vo1, 
			      @ModelAttribute("count2")CountVO vo2, int num1, int num2) {
		vo1.setCountNum(num1);
		vo2.setCountNum(num2);
		System.out.println("handle() : "+ 
			      vo1.getCountNum() + " : " + vo2.getCountNum());
		System.out.println("=============================");
	}
	//sessionScope 에서 재공하는 
	@RequestMapping(value="/countdel")
	public void handle(SessionStatus s) {
		//SessionStatus 를 객체로 사용하여 setComplete() 메서드 사용
		//SessionScope 에 보관되어있는 객체 모두 삭제
        //따로따로 못지움...따로 지우려면 session 객체 따로 만들어주고 삭제
		s.setComplete();
		System.out.println("Both count1 and count2 deleted!");	
		System.out.println("=============================");
	}
}
```

![image-20200210171757589](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200210171757589.png)

- 이렇게 객체를 한번만 생성한다!

#### CountVO.java

```java
package vo;
public class CountVO {
	private int countNum;
	public CountVO() {
		System.out.println("CountVO 객체 생성!!");
	}
	public int getCountNum() {
		return countNum;
	}
	public void setCountNum(int countNum) {
		this.countNum += countNum;
	}	
}

```



#### count.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP test example</title>
</head>
<body>
<h1>Counting Result</h1>
<hr>
<h2>count1 : ${ sessionScope.count1.countNum }</h2>
<h2>count2 : ${ sessionScope.count2.countNum }</h2>
</body>
</html>




```



#### countdel.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.CountVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP test example</title>
</head>
<body>
<h2>Complete Result</h2>
<hr>
<h3>Objects stored in count1 names :
              <%= session.getAttribute("count1") %></h3>
<h3>Objects stored in count2 names :
              <%= session.getAttribute("count2") %></h3>
</body>
</html>
```



---



### Lotto : Service 사용

- Webapp -WEB-IFG - spring - appServlet - servlet-context 설정 해줘야함
- `<context:component-scan base-package="service" />` 이거 넣어줘

#### LottoController.java

```java
//Random 숫자를 컨트롤러 메서드 안에 사용해도(기존방법) 가능하다
//하지만 DAO 를 사용하기 위해 연습하는 예제

package my.spring.springedu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import service.LottoService;
import vo.LottoVO;
@Controller
public class LottoController1 {
	//자동 연결하여 lottoService 가 생성하게 한다
	//lottoService 에 의존적이다
	//@Service @DAO 를 서칭하라고 설정해놔서 자동으로 서칭한다
	//service 패키지에 있는 LottoService 클래스 서칭 (LottoService.java로 감)
	//여기서 Autowired 해주면 밑에 메서드에서 lottoService 선언해줄 필요 없다(제일 많이하는 실수)
	@Autowired
	private LottoService lottoService;	
	@RequestMapping("/lotto1")
	public String lottoProcess(LottoVO vo) {	
		//LottoVO 객체는 RequestScope 에 "lottoVO" 라는 이름으로 저장된다
		//Session설정을 안해줘서 그런다-->jsp 에서 request객체로 출력할 수 있다
		//여기서 LottoVO의 변수인 result의 값을 setResult 로 세팅해준다
		if (lottoService.getLottoProcess(vo.getLottoNum())) {
			vo.setResult("추카추카!!");
		} else {
			vo.setResult("아쉽네요 .. 다음 기회를!!");
		}
		return "lottoView1";
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

#### LottoDAO.java : Tomcat서버 재기동 될때 생성된다

```java
package dao;
import java.util.Random;
import org.springframework.stereotype.Repository;
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



#### lottoView.jsp

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
<%
	vo.LottoVO vo = (vo.LottoVO)request.getAttribute("lottoVO");
%>
<h2><%= vo.getResult() %></h2>
<hr>
<a href="/springedu/resources/lottoForm1.html">재시도.....</a>
</body>
</html>
```





---



## 잭슨 바인드........(어렵)

### ResponseBody : View 안거치고 Controller 응답



#### ResponseBodyController.jsp

```java
package my.spring.springedu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vo.MyGroupModel;
import vo.MyMainModel;
import vo.MyModel;
import vo.XmlVO;

@Controller
public class ResponseBodyController {
	//동적패스 변수명 : value = "/body/text/{id} 의 id는 동적 변수명
	@RequestMapping(value = "/body/text/{id}", produces = "text/plain; charset=utf-8")
	//View 를 안거치고 Controller 가 바로 응답한다
	@ResponseBody	//@PathVariable 은 동적...없으면 쿼리에서 가져옴
	public String getByIdInTEXT(@PathVariable String id) {
		return "<h1>It returns the string directly from the controller : " + id + "</h1>";
	}

	@RequestMapping(value = "/body/htmltext/{id}", produces = "text/html; charset=utf-8")
	@ResponseBody
	public String getByIdInHTMLTEXT(@PathVariable String id) {
		return "<h1>It returns the HTML directly from the controller : " + id + "</h1>";
	}
	
	@RequestMapping(value = "/body/json/{id}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getByIdInJSON(@PathVariable String id) {
		String s = "{ \"name\" : \"ROSE\", \"num\":5, \"id\" : \""+id+"\"}";
		return s;
	}
	
	@RequestMapping(value = "/body/json1/{id}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public MyModel getByIdInJSON1(@PathVariable String id) {
		MyModel my = new MyModel();
		my.setFlowerName("ROSE");
		my.setNum(5);
		my.setId(id);		
		return my;
	}	
	
	@RequestMapping(value = "/body/json2/{id}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<MyModel> getByIdInJSON2(@PathVariable String id) {
		List<MyModel> list = new ArrayList<MyModel>();
		MyModel my = new MyModel();
		my.setFlowerName("ROSE");
		my.setNum(5);
		my.setId(id);
		list.add(my);
		my = new MyModel();
		my.setFlowerName("LILY");
		my.setNum(5);
		my.setId(id);
		list.add(my);
		return list;
	}

	//JSON 은 Value 값을 ArrayList 즉 List 로 줘야한다
    //자바 내 더블인용 사용할때 \사용해야하는데 그걸 줄여주는게 아래 방법들
	@RequestMapping(value = "/body/json3/{id}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<MyMainModel> getByIdInJSON3(@PathVariable String id) {
		ArrayList<MyMainModel> list = new ArrayList<>();
		ArrayList<MyGroupModel> list2 = new ArrayList<>();
		ArrayList<MyGroupModel> list2_1 = new ArrayList<>();
		ArrayList<MyModel> list3 = new ArrayList<>();
		ArrayList<MyModel> list3_1 = new ArrayList<>();
		MyModel my1 = new MyModel();
		my1.setFlowerName("AAA");
		my1.setNum(5);
		my1.setId(id);
		MyModel my2 = new MyModel();
		my2.setFlowerName("BBB");
		my2.setNum(5);
		my2.setId(id);
		MyModel my3 = new MyModel();
		my3.setFlowerName("BBB");
		my3.setNum(5);
		my3.setId(id);
		MyModel my4 = new MyModel();
		my4.setFlowerName("CCC");
		my4.setNum(5);
		my4.setId(id);
		MyModel my5 = new MyModel();
		my5.setFlowerName("DDD");
		my5.setNum(5);
		my5.setId(id);
		list3.add(my1);
		list3.add(my2);
		list3_1.add(my3);
		list3_1.add(my4);
		list3_1.add(my5);
		MyGroupModel mgm1 = new MyGroupModel();
		mgm1.setCategory("요가");
		mgm1.setMyModellist(list3);
		MyGroupModel mgm2 = new MyGroupModel();
		mgm2.setCategory("수영");
		mgm2.setMyModellist(list3_1);
		list2.add(mgm1);
		list2_1.add(mgm2);
		MyMainModel mmm1 = new MyMainModel();
		mmm1.setYear("2019");
		mmm1.setGrouplist(list2);
		MyMainModel mmm2 = new MyMainModel();
		mmm2.setYear("2018");
		mmm2.setGrouplist(list2_1);
		list.add(mmm1);
		list.add(mmm2);
		System.out.println(list);
		return list;
	}
	//HashMap 도 사용 가능하다
	@RequestMapping(value = "/body/json4/{id}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<HashMap<String, String>> getByIdInJSON4(@PathVariable String id) {
		List<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map1 = new HashMap<>();
		map1.put("aa", "10");
		map1.put("bb", "20");
		HashMap<String, String> map2 = new HashMap<>();
		map2.put("cc", "30");
		map2.put("dd", "40");
		list.add(map1);
		list.add(map2);
		return list;
	}

	@RequestMapping(value = "/body/xml1/{id}", produces = "application/xml; charset=utf-8")
	@ResponseBody
	public MyModel getByIdInXML1(@PathVariable String id) {
		MyModel my = new MyModel();
		my.setFlowerName("ROSE");
		my.setNum(5);
		my.setId(id);
		return my;
	}

	
	@RequestMapping(value = "/body/xml2/{id}", produces = "application/xml; charset=utf-8")
	@ResponseBody
	public XmlVO getByIdInXML4(@PathVariable String id) {
		XmlVO vo = new XmlVO();
		ArrayList<MyModel> list = new ArrayList<MyModel>();
		MyModel my = new MyModel();
		my.setFlowerName("ROSE");
		my.setNum(5);
		my.setId(id);
		list.add(my);
		my = new MyModel();
		my.setFlowerName("LILY");
		my.setNum(5);
		my.setId(id);
		list.add(my);
		vo.setList(list);
		return vo;
	}	
    
    //밑에 애들은 ajaxtext1,2.html 가 클라이언트이다
	@RequestMapping(value = "/getJSON1", 
			      produces = "application/json; charset=utf-8")
	@ResponseBody
	public String test1(String id) {		
		String s = "{ \"result\" : \"ㅋㅋ1"+id+"\"}";
		return s;
	}
	@RequestMapping(value = "/getJSON2", 
		      produces = "application/json; charset=utf-8")
	@ResponseBody
	public HashMap<String, String> test2(String id) {		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", "ㅋㅋ2"+id);
		return map;
	}

}

```

#### MyModel.java

```java
package vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MyModel {
	private String flowerName;
	private int num;
	private String id;
	
	public String getFlowerName() {
		return flowerName;
	}
	public void setFlowerName(String flowerName) {
		this.flowerName = flowerName;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "MyModel [flowerName=" + flowerName + ", num=" + num + ", id=" + id + "]";
	}	
	
}

```

#### XmlVO.java

- RootElement 이름 설정 방법

```java
package vo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
//RootElement 의 기본값은 클래스명에서 첫글자 소문자
//name 속성값을 주면 이름으로 바꿀 수 있다
//XML 문서에서 가장 상위 값이 <xmlVO> ==> <unico> 로 변환
@XmlRootElement(name="unico")
public class XmlVO {
	private List<MyModel> list;
	public List<MyModel> getList() {
		return list;
	}
	public void setList(List<MyModel> list) {
		this.list = list;
	}	
}

```

#### ajaxtest1.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<title>New Web Project</title>
</head>
<body>
	<h1>JavaScript 로 구현하는 AJAX 프로그램 - Spring 요청</h1>
	<button onclick="get()">GET요청</button>
	<button onclick="post()">POST요청</button>
	<hr>
	<output id="result"></output>
<script>
	function get() {
		var xhr = new XMLHttpRequest();
		xhr.onload = function(event) {
			if (xhr.status == 200) {
				var result = document.getElementById("result");
				result.textContent += xhr.responseText;				
			}
		};
		xhr.open('GET', '/springedu/body/json1/GETUNICO', true);
		xhr.send();
	}
	function post() {
		var xhr = new XMLHttpRequest();
		xhr.onload = function(event) {
			if (xhr.status == 200) {
				var result = document.getElementById("result");
				result.textContent += xhr.responseText;	
			}
		};
		xhr.open('POST', '/springedu/body/xml1/POSTUNICO', true);
		xhr.setRequestHeader("content-type", 
				"application/x-www-form-urlencoded");
		xhr.send("");
	}
</script>
</body>
</html>
```

#### ajaxtext2.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<title>New Web Project</title>
<script>
	function go(target) {
		var request = new XMLHttpRequest();
		request.onload = function(event) {
			if (request.status == 200) {
				var result = document.getElementById("result");
				result.textContent += request.responseText;				
			}
		};
		request.open('GET', '/springedu/'+target+'?id=DUKE', true);
		request.send();
	}
</script>
</head>
<body>
	<h1>JavaScript 로 구현하는 AJAX 프로그램 - JSON</h1>
	<button onclick="go('getJSON1')">getJSON1호출</button>
	<button onclick="go('getJSON2')">getJSON2호출</button>
	<hr>
	<output id="result"></output>
</body>
</html>
```



---

### 로그인 기능 : html 응답, ajax, VO두개, service

#### LoginController.java

```java
package my.spring.springedu;
	
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.LoginService;
import vo.LoginVO;
import vo.ResultVO;

@Controller
public class LoginController {
	@Autowired
	LoginService ms;
	@RequestMapping(value = "/login", produces="application/json; charset=utf-8", method=RequestMethod.POST)
	@ResponseBody
	public ResultVO login(LoginVO p) { 
		ResultVO vo = new ResultVO();
		boolean result = ms.login(p.getId(), p.getPasswd());
		if (result == true) {
			vo.setResult("ok");
		}
		else {
			vo.setResult("fail");
		}
		return vo;
	}

}
```

#### LoginService.java

```java
package service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	public boolean login(String id, String passwd) {
		if (passwd.equals("@1234") && id.equals("spring")) {
			return true;
		}
		else {
		return false;
		}
	}

}
```



#### exam7_1.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script>
$(document).ready(function() {
	$("#loginb").click(	function() {
		$.post("/springedu/login", $("#loginf").serialize(), function(data) {
			alert(data.result);
			$.each(data, function(key, value) {
				if (value == "ok") {
					$("#result").text("로그인 성공!!").css("color", "Blue");
				} else if (value == "fail") {
					$("#result").text("로그인 실패!!").css("color", "Red");
					$("#loginf").each(function() {
						this.reset();
						$("#id").focus();
					});
				}
			});
		});
	});
});
</script>
</head>
<body>
	<h3>계정과 패스워드를 입력해 주세요.</h3>
	<form id="loginf">
		<table>
			<tr>
				<td><label for="id">계정</label></td>
				<td><input type="text" id="id" name="id" required/></td>
			</tr>
			<tr>
				<td><label for="passwd">패스워드</label></td>
				<td><input type="password" id="passwd" name="passwd" required/></td>
			</tr>
			<tr>
				<td><input type="button" id="loginb" name="loginb" value="로그인" /></td>
				<td><output id="result"></output></td>
			</tr>
		</table>
	</form>
</body>
</html>
```



#### .java

```java

```



#### .jsp

```jsp

```



#### .java

```java

```



#### .jsp

```jsp

```



#### .java

```java

```



#### .jsp

```jsp

```



#### .java

```java

```



#### .jsp

```jsp

```



#### .java

```java

```



#### .jsp

```jsp

```



#### .java

```java

```



#### .jsp

```jsp

```



#### .java

```java

```



#### .jsp

```jsp

```

