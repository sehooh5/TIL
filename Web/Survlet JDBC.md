# Survlet JDBC

- 서블릿(웹)에서의 DB연동

### 순서

```
1. JDBC 드라이버 로딩(Class.forName())
2. DB 서버 접속(DriverManager.getConnection("jdbcurl","계정","암호"))
3. SQL문을 수행하기 위한 Statement, PreparedStatement 객체 생성
4. SELECT - executeQuery() : 리턴값 ResultSet(next(),getXXX())
	INSERT, DELETE, UPDATE, CREATE tABLE, DROP TABLE...
	- executeUpdate() : 리턴값 int(SQL명령에 의해 변화된 행의 갯수)
```



### 처음 배운 작업내용 순서

1. visitorMain.html - 방명록 리스트 보기 ---> /visitordb (GET방식)

​		visitorForm.html - 방명록 작성하기  --->  /visitordb (POST방식)

2. VisitorDBServlet (/visitordb)

   ```
   GET : visitor 테이블의 데이터들을 모두 읽어 와서 테이블 형식으로 브라우저에 출력
   
   POST : 전송되는 Query 문자열을 가지고 visitor 테이블에 저장
   ```

   

#### VisitorDBServlet

```
package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VisitorDBServlet
 */
@WebServlet("/visitordb2")
public class VisitorDBServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");//꼭 try catch 써줘야함
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 오류");
		}
		//**ojdbc6.jar 를 /WEB-INF/lib 밑에 넣어놔야한다
		//DB서버 접속, Statement 객체 생성, "select name, writedate, memo from visitor" SQL명령 수행
		//예외처리 필수!!try catch with resource 사용!!간단하게 close 안쓰고 사용 가능
		String sql = "select name, to_char(writedate, 'yyyy\"년\" mm\"월\" dd\"일\"') writedate, memo from visitor"; 
		try (Connection conn = DriverManager.getConnection
				("jdbc:oracle:thin:@localhost:1521:XE", "jdbctest", "jdbctest");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);){
			out.print("<h1>방명록 리스트</h1><hr>");
			out.print("<table border='1'>");
			while(rs.next()) {
				out.print("<tr>");
				out.print("<td>"+rs.getString("name")+"</td>");
				out.print("<td>"+rs.getString("writedate")+"</td>");
				out.print("<td>"+rs.getString("memo")+"</td>");
				out.print("</tr>"); // 서블릿은 html 뽑을라면 일일이 out.print 해야됨.. 이걸 보완해주는애가 jsp
			}
			out.print("</table>");
		}catch(SQLException e) {
			out.print("<h2>오류 발생!!</h2>");
			e.printStackTrace();
		}
		out.print("<hr><a href='/sedu/html/visitorMain.html'>방명록 메인화면으로 가기</a>");
		out.close();
	}
	
	//기본적으로 GET 과 같음
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
/**/	request.setCharacterEncoding("UTF-8");
		
/**/	String name = request.getParameter("name");
/**/	String memo = request.getParameter("memo");
		
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");//꼭 try catch 써줘야함
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 오류");
		}
		//**ojdbc6.jar 를 /WEB-INF/lib 밑에 넣어놔야한다
		//DB서버 접속, Statement 객체 생성, "select name, writedate, memo from visitor" SQL명령 수행
		//예외처리 필수!!try catch with resource 사용!!간단하게 close 안쓰고 사용 가능
/**/	String sql = "insert into visitor values('"+name+"',sysdate,'"+memo+"')"; 
		//**객체 생성하는 애들만 오고 close 필요한 애들만 사용해야한다..따라서executeUpdate는 불가
		try (Connection conn = DriverManager.getConnection
				("jdbc:oracle:thin:@localhost:1521:XE", "jdbctest", "jdbctest");
				Statement stmt = conn.createStatement();
				){
/**/		stmt.executeUpdate(sql);//위에 try 안 객체 생성에서 내림!
			out.print("<h2>방명록글 저장 성공</h2>");
		}catch(SQLException e) {
			out.print("<h2>방명록글 저장 실패</h2>");
			e.printStackTrace();
		}
		out.print("<hr><a href='/sedu/html/visitorMain.html'>방명록 메인화면으로 가기</a>");
		out.close();
	}
}
```





#### visitorMain,Form.html

```
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>방명록</h1>
<hr>
<a href="/sedu/visitordb2">방명록 리스트 보기</a>
<hr>
<a href="/sedu/html/visitorForm.html">방명록 작성하기</a>
</body>
</html>


<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>방문자 의견</title>
</head>

<body>
<h1>글을 남겨주세요</h1>
<hr>

<form action="/sedu/visitordb2" method = "POST">
이름 : <input type = "text" required name="name"><br>
남기고자 하는 의견 : <br>
<textarea rows="8" cols="30" name="memo"></textarea><br>
<input type = "submit" value = "등록">
<input type = "reset" value = "재작성">
</form>

</body>
</html>
```

