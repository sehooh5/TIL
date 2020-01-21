# Servlet - Session

### 상태정보 유지 기술

- 웹 서버에 정보를 요청할 때 이전 접속시의 결과물을 일정시간 유지하는 것
  1. Cookie 이용 : 클라이언트 안에 저장하는 네임밸류쌍의 데이터 
  2. **HttpSession** 객체 이용
  3. URL 문자열 뒤에 추가
  4. `<for>` 태그의 hidden 타입을 사용 : 간단할 때 사용 많이함



1. 클라이언트 저장(구글, 네이버, 다음...)
   - 각 클라이언트에 저장이 된다
2. 서버저장
   - 많은 사람들이 요청하면 서버에 과부하가 걸릴 수 있다



- HttpSession 객체는 클라이언트별 1개만 생성할 수 있다
- HttpSession 마다 특유의 ID를 만들고 Cookie 에 저장한다
- **Inactive Interval** 시간은 '**30분**', 이후 자동 삭제
- 브라우저를 재 기동해야 session객체가 사라진다.



### 작성 방법

1. HttpSession 객체를 생성하거나 추출한다.

   - HttpSession session = request.getSession();
   - request.getSession(**false**) : 있으면 찾아오고 없으면 null 리턴,세션안만듬

2. HttpSession 객체에 상태정보를 보관할 객체를 등록한다(한번만)

   - session.setAttribute("key이름",  new Data());

3. HttpSession 객체에 등록되어 있는 상태정보 객체의 참조값을 얻어 사용한다

   - Data ref = (Data)session.getAttribute("이름"); - 형변환

4. HttpSession 객체에 등록되어 있는 상태정보 객체가 더 이상 필요없으면 삭제

   - session.removeAttribute("이름"); : 이름으로 보관된 객체의 참조 값 삭제
- session.invalidate() : 강제로 삭제
   
   

### 주요 메서드

- public long getCreationTime() : 1970.1.1부터 만들어졌을때까지의 밀리초

- public String getId() : 세션에 지정된 세션 ID 리턴

- public long getLastAccessedTime() : 

  클라이언트 요청이 마지막으로 시도된 시간을 밀리초로 리턴한다

- public int getMaxInactiveInterval() : 

  클라이언트 요구 없을 때 서버가 현재의 세션을 언제까지 유지할지를 초시간 단위로 리턴한다..이때 디폴트 세션 마감시간은 30분으로 지정되어 있다.

- public boolea isNew() : 

  서버측에 새로운 세션을 생성한경우 true, 기존 세션 유지되고 있으면 false

- public void setMaxInactiveInterval(int seconds) : 

  세션 유지 시간을 설정..



#### SessionTestServlet1

```java
package core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessiontest1")
public class SessionTestServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int member_v = 0;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int local_v = 0;
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//팩토리 메서드 만들어야한다!
		//무조건 만드는 것이 아닌 이미 session 객체가 있으면 만들지 않고 없으면 만든다.
		HttpSession session = request.getSession();
		//데이터 저장할 방은 한번만 만든다 ... 그 방은 반드시 객체여야 한다
		//한번만 등록하기 위해 if를 사용하여 key 값이 있는지 체크해준다.
		if(session.getAttribute("cnt")==null)//session 에 cnt 라는 객체가 없으면~~
		//cnt 라는 이름의 배열객체 =저장소 만듬
		//이름, 정보
		session.setAttribute("cnt", new int[1]);
		//getAttribute 값은 Object(부모) 이므로 형변환 해준다
		int[] session_v = (int[])session.getAttribute("cnt");//배열 객체를 담을 변수를 만듬,,
		session_v[0]+=10;
		
		member_v += 10;
		local_v += 10;
		out.print("<ul>");
		out.print("<li>멤버변수 : " + member_v + "</li>");
		out.print("<li>지역변수 : " + local_v + "</li>");
		out.print("<li>세션객체에 저장된 배열 원소 : " + session_v[0] + "</li>");
		out.print("<ul>");
		out.close();
	}

}

```



---



#### SessionTestServlet2

```java
package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/sessiontest2")
public class SessionTestServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();	
		out.print("생성된 세션객체의 ID : "+session.getId());
		//생성자를 통해 아규먼트를 줘서 Date 객체의 String 이 출력되게끔 해줌..시,분,초
		out.print("<br>세션 객체가 생성된 시간 : "
				      + new Date(session.getCreationTime()));
		out.print("<br>현재 시간 : " + new Date());
		out.close();
	}
}









```



#### SessionTestServlet3

```java
package core;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/sessiontest3")
public class SessionTestServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();	
	    String action = request.getParameter("action");
	    HttpSession session = null;
	    String msg = "action 값을 주세호"; 
	    if(action.equals("create")) {
	    	session = request.getSession();// getSession(true)와 동일
	    	if(session.isNew())
	    		msg = "세션 객체가 생성됨";
	    	else 
	    		msg = "세션 객체는 이미 생성되어 있었음";    	
	    } else if (action.equals("destroy")) {
	    	session = request.getSession(false);
	    	if(session != null) {
	    		session.invalidate();
	    		msg = "세션 객체가 삭제됨";
	    	} else 
	    		msg = "삭제할 세션 객체가 없음";    	
	    }
		 
		out.print("<h2>"+msg+"</h2>");
		out.close();
	}
}









```



#### SessionTestServlet

```java

```



