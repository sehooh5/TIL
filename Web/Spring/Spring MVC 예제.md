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







#### .java

```java

```



#### .java

```java

```



#### .jsp

```jsp

```

