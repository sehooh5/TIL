# Spring MVC 예제

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

### @RequestParam, VO 사용



#### QueryStringController1.java

```java
package my.spring.springedu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class QueryStringController1 {
	@RequestMapping("/querystring1")
	public ModelAndView proc(String name) {
		ModelAndView mav = new ModelAndView();
		System.out.println("["+name+"]");
		mav.addObject("spring", name);
		mav.setViewName("queryView1");
		return mav;
	}	
	@RequestMapping("/querystring2")
	public ModelAndView proc(int number) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("spring", number);
		mav.setViewName("queryView1");
		return mav;
	}	
	@RequestMapping("/querystring3")
	public ModelAndView proc(String name, 
			                   @RequestParam("num")int number) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("spring", name+":"+number);
		mav.setViewName("queryView1");
		return mav;
	}
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
<h3>Data passed as spring name(EL) : ${ spring }</h3>
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



### VO 사용

#### .java

```java
package my.spring.springedu;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vo.QueryVO;
@Controller
public class QueryStringController2 {	
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

