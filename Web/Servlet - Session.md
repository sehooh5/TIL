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
- inactive interval 시간은 '30분'
- 브라우저를 재 기동해야 session객체가 사라진다.



### 작성 방법

1. HttpSession 객체를 생성하거나 추출한다.

   - HttpSession session = request.getSession();
   - request.getSession(false) : 있으면 찾아오고 없으면 null 리턴

2. HttpSession 객체에 상태정보를 보관할 객체를 등록한다(한번만)

   - session.setAttribute("이름",  new Data());

3. HttpSession 객체에 등록되어 있는 상태정보 객체의 참조값을 얻어 사용한다

   - Data ref = (Data)session.getAttribute("이름"); - 형변환

4. HttpSession 객체에 등록되어 있는 상태정보 객체가 더 이상 필요없으면 삭제

   - session.removeAttribute("이름");

   

#### SessionTestServlet

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
		//무조건 만드는 것이 아닌 이미 sesshion 객체가 있으면 만들지 않고 없으면 만든다.
		HttpSession session = request.getSession();
		//데이터 저장할 방은 한번만 만든다 ... 그 방은 반드시 객체여야 한다
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



#### SessionTestServlet

```java

```



#### SessionTestServlet

```java

```



#### SessionTestServlet

```java

```



