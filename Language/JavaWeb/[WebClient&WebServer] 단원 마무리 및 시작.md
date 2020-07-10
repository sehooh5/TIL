### Web Client : HTML5, CSS3, JavaScript ---> edu

### Web Server : Servlet, JSP, (JDBC), Spring FW, MyBatis FW, (Junit FW, Log4J) - Java       ---> sedu



web server 시작

new-dynamic web project

context root는 톰켓에 sedu라는 이름으로 등록한다는 뜻!

sedu-properties-utf8로 변경

톰켓 기동되어 있다면 종료하고 새롭게 톰켓 등록해야됨

웹은톰켓을통해야되기때문에



톰켓-오른쪽버튼 -addand remove- add로 오른쪽

서버폴더의 server.xml 맨마지막줄 

context태그에 sedu가 추가된것을 볼 수 있음.



- http://localhost:8000/sedu/first.html // 항상 똑같이 나옴 정적페이지

- http://localhost:8000/sedu/first.jsp?gname=듀크 //쿼리가 뭐가 나왔느냐에 따라 동적으로 변함

- http://localhost:8000/sedu/first.jsp?gname=또치 //동적페이지

- 서버에서 해야한다? JSP 서블릿..

- 동적 처리를 서버에서 한다.

- ### 서블릿은 반드시 sedu-javaresources-src에 넣어야 됨!!

- 패키지명 생략 

- 클래스네임 FirstServlet - next-next-체크박스상에서 constructor from superclass 생략

- 인헤리티드 앱스트렉트 메서드와 두겟만 체크 -finish 

- 얘는 메인 없음, 톰켓이 메인임.

- 두겟 안에 싹 지우고

- first.jsp h1태그 전체 카피

- ```
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		response.setContentType("text/html; charset=utf-8");
   		PrintWriter out = response.getWriter();
   		String s = request.getParameter("gname");
   		out.print("<h1>안녕하세요!!"+s+"회원님~~</h1>");
   	}
  ```


  	입력..

  ```
- ### 요청방법..

  - http://localhost:8000/sedu/FirstServlet
  - http://localhost:8000/sedu/FirstServlet?gname=고길동
  - http://localhost:8000/sedu/FirstServlet?gname=둘리
  - 404 오류뜨면 서버 재기동
  ```