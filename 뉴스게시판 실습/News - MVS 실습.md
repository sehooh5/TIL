# News - MVS 실습

1. MVC
   - VO - DAO - Servlet - JSP
2. search / insert / update / listAll / listOne / counting



#### NewsVO.java

```java
package model.vo;

public class NewsVO {
	private int id;
	private String writer;
	private String title;
	private String content;
	private String writeDate;
	private int cnt;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	//없어도 되지만 JUNIT 테스트 할 때 출력할 때 사용한다
	 @Override 
	 public String toString() { return "NewsVO [id=" + id +"writer=" + writer +
	  ", title=" + title +", content=" + content +", writedate=" + writeDate+", cnt=" + cnt + "]" ; }
	 
}

```



#### NewsDAO.java

```java
package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.MeetingVO;
import model.vo.NewsVO;

public class NewsDAO {
	private Connection connectDB() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbctest", "jdbctest");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

//	private void close(Connection conn, Statement stmt, ResultSet rs) {
//		if ( rs != null ) try{rs.close();}catch(Exception e){e.printStackTrace();}
//		if ( stmt != null ) try{stmt.close();}catch(Exception e){e.printStackTrace();}
//	     if ( conn != null ) try{conn.close();}catch(Exception e){e.printStackTrace();}
//	}

	public boolean insert(NewsVO vo) {
		boolean result = true;
		Connection conn = connectDB();
		try (PreparedStatement pstmt = conn
				.prepareStatement("insert into news values" + "(news_seq.nextval, ?, ?,?,sysdate, ?)");) {
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(1, vo.getWriter());
			pstmt.setInt(4, vo.getCnt());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate();
			/* close(conn,pstmt,pstmt.executeQuery()); */
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	public boolean update(NewsVO vo) {
		boolean result = true;
		Connection conn = connectDB();
		try (PreparedStatement pstmt = conn
				.prepareStatement("update news set " + "title = ?, " + "writer = ?, " + "writedate = sysdate, " +
				/* "cnt = ?, " + */
						"content = ? " + "where id = ?");) {
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			/* pstmt.setInt(3, vo.getCnt()); */
			pstmt.setString(3, vo.getContent());
			pstmt.setInt(4, vo.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	public boolean delete(int id) {
		boolean result = true;
		Connection conn = connectDB();
		try (PreparedStatement pstmt = conn.prepareStatement("delete from news where id = " + id);) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	public List<NewsVO> listAll() {
		List<NewsVO> list = new ArrayList<>();
		Connection conn = connectDB();

		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"select id, title, writer, " + "to_char(writedate, 'yyyy-mm-dd'),cnt from news");) {
			NewsVO vo;
			while (rs.next()) {
				vo = new NewsVO();
				vo.setId(Integer.parseInt(rs.getString(1)));
				vo.setTitle(rs.getString(2));
				vo.setWriter(rs.getString(3));
				vo.setWriteDate(rs.getString(4));
				vo.setCnt(Integer.parseInt(rs.getString(5)));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<NewsVO> listWriter(String writer) {
		List<NewsVO> list = new ArrayList<>();
		Connection conn = connectDB();

		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select id, title, writer, "
						+ "to_char(writedate, 'yyyy-mm-dd'),cnt from news " + "where writer='" + writer + "'");) {
			NewsVO vo;
			while (rs.next()) {
				vo = new NewsVO();
				vo.setId(Integer.parseInt(rs.getString(1)));
				vo.setTitle(rs.getString(2));
				vo.setWriter(rs.getString(3));
				vo.setWriteDate(rs.getString(4));
				vo.setCnt(Integer.parseInt(rs.getString(5)));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<NewsVO> search(String key, String searchType) {
		List<NewsVO> list = new ArrayList<>();
		Connection conn = connectDB();

		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("select id, title, writer, " + "to_char(writedate, 'yyyy-mm-dd'),cnt from news "
								+ "where " + searchType + " like '%" + key + "%'");) {
			NewsVO vo;
			while (rs.next()) {
				vo = new NewsVO();
				vo.setId(Integer.parseInt(rs.getString(1)));
				vo.setTitle(rs.getString(2));
				vo.setWriter(rs.getString(3));
				vo.setWriteDate(rs.getString(4));
				vo.setCnt(Integer.parseInt(rs.getString(5)));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public NewsVO listOne(int id) {
		NewsVO vo = new NewsVO();
		Connection conn = connectDB();

		try (PreparedStatement pstmt = conn.prepareStatement("update news set cnt = cnt+1 where id = ?");) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select id,writer, title, content " + "from news where id =" + id);) {
			while (rs.next()) {
				vo.setId(rs.getInt(1));
				vo.setWriter(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setContent(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

}

```



#### NewsDAOTest.java

```java
package model.dao;

//import static : static 형 멤버들 쓸 때 클래스 이름 안주고 메서드 사용가능
import static org.junit.jupiter.api.Assertions.*;

import static java.lang.System.out;
import java.util.List;
import org.junit.jupiter.api.Test;
import model.vo.NewsVO;

class NewsDAOTest {
	/*@Test
	
	 * void test1() { out.println("<test1 실행 : 삽입기능>"); NewsDAO dao = new NewsDAO();
	 * NewsVO vo = new NewsVO(); vo.setWriter("테스트"); vo.setTitle("테스트 제목");
	 * vo.setContent("테스트 내용입니다"); boolean result = dao.insert(vo);
	 * System.out.println(result); System.out.println(vo.getId());
	 * System.out.println(vo.getWriter()); System.out.println(vo.getTitle());
	 * System.out.println(vo.getContent()); if(result) out.println("삽입 성공"); else
	 * fail("삽입실패"); out.println("==========================="); }
	 */
	
	@Test
	void test2() {
		out.println("<test2 실행 : 전체 자료 검색>");
		NewsDAO dao = new NewsDAO();
		List<NewsVO> list = dao.listAll();
		out.println(list.size());
		for(NewsVO vo : list)
			out.println(vo);
		out.println("===========================");
	}
	
	/*
	 * @Test void test3() { out.println("<test3 실행 : 검색기능>"); NewsDAO dao = new
	 * NewsDAO(); NewsVO vo = dao.listOne(1); out.println(vo);
	 * out.println("==========================="); }
	 * 
	 * @Test void test4() { out.println("<test1 실행 : 수정기능>"); NewsDAO dao = new
	 * NewsDAO(); NewsVO vo = new NewsVO(); vo.setWriter("테스트 수정");
	 * vo.setTitle("테스트 제목 수정"); vo.setContent("테스트 수정한 내용입니다"); vo.setId(1);
	 * boolean result = dao.update(vo); System.out.println(vo.getId());
	 * System.out.println(vo.getWriter()); System.out.println(vo.getTitle());
	 * System.out.println(vo.getContent()); System.out.println(vo.getCnt());
	 * if(result) out.println("수정 성공"); else fail("수정실패");
	 * out.println("==========================="); }
	 */
	
	@Test
	void test5() {
		out.println("<test5 실행 : 검색 기능>");
		NewsDAO dao = new NewsDAO();
		List<NewsVO> list = dao.search("다시","title");
		out.println(list.size());
		for(NewsVO vo : list)
			out.println(vo);
		out.println("===========================");
	}
	
	@Test
	void test6() {
		out.println("<test5 실행 : 작성자 찾기 기능>");
		NewsDAO dao = new NewsDAO();
		List<NewsVO> list = dao.listWriter("오세호");
		out.println(list.size());
		for(NewsVO vo : list)
			out.println(vo);
		out.println("===========================");
	}
	
}

```



#### NewsServlet.java

```java
package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.NewsDAO;
import model.vo.MeetingVO;
import model.vo.NewsVO;

@WebServlet("/news")
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String searchtype = request.getParameter("searchtype");
		String key = request.getParameter("key");
		String id = request.getParameter("newsid");
		String action = request.getParameter("action");
		String writer = request.getParameter("writer");
		String log = "";
		NewsDAO dao = new NewsDAO();

		if(key == null) {
		if (action == null && id == null) {
			if(writer==null) {
			request.setAttribute("list", dao.listAll());
			log = "/jspexam/newsView.jsp";
			}else {
				request.setAttribute("list", dao.listWriter(writer));
				log = "/jspexam/newsView.jsp?writer="+writer;
			}
		} else if (action != null) {
			if (action.equals("read")) {
				NewsVO vo = dao.listOne(Integer.parseInt(id));
				log = "/jspexam/newsView.jsp?action=" + action + "&newsid=" + id;
				request.setAttribute("read", vo);
				request.setAttribute("list", dao.listAll());
			} else if (action.equals("delete")) {
				boolean result = dao.delete(Integer.parseInt(id));
				log = "/jspexam/newsView.jsp?action=" + action + "&newsid=" + id;
				request.setAttribute("list", dao.listAll());
			}
		}
		}else {
			request.setAttribute("list", dao.search(key,searchtype));	
			log = "/jspexam/newsView.jsp?searchtype=" + searchtype + "&key=" + key;
		}
		request.getRequestDispatcher(log).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		NewsDAO dao = new NewsDAO();
		NewsVO vo = new NewsVO();
		vo.setWriter(writer);
		vo.setTitle(title);
		vo.setContent(content);
		if (action.equals("insert")) {
			boolean result = dao.insert(vo);
			if (result) {
				request.setAttribute("msg", writer + "님의 글이 성공적으로 입력되었습니다.");
			} else {
				request.setAttribute("msg", writer + "님의 글이 입력되지 않았습니다.");
			}
		} else if (action.equals("update")) {
			vo.setId(Integer.parseInt(id));

			boolean result = dao.update(vo);
			if (result) {
				request.setAttribute("msg", writer + "님의 글이 성공적으로 수정되었습니다.");
			} else {
				request.setAttribute("msg", writer + "님의 글이 수정되지 않았습니다.");
			}
		}
		request.setAttribute("list", dao.listAll());
		request.getRequestDispatcher("/jspexam/newsView.jsp").forward(request, response);
	}
}

```



#### newsView.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.vo.NewsVO, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<style>
h2 {
	text-align: center;
}

table {
	margin: auto;
	box-align: center;
}

td {
	border-bottom: 2px dotted green;
}

td#title:hover {
	font-weight: bold;
}

td:nth-child(2) {
	width: 250px;
}

form {
	text-align: center;
}

div.button {
	margin: auto;
	text-align: center;
}
tr#first{
	background-color : #b3ccff;
	height : 30px;
}
th#id{
	color : #ff0000;
	width : 60px;
	font-weight : bolder;
}
th#title{
	color : #e65c00;
	width : 60px;
	font-weight : bolder;
}
th#writer{
	color : #ff4d4d;
	font-weight : bolder;
}
th#date{
	color : #e67300;
	font-weight : bolder;
}
th#cnt{
	color : #cc3300;
	width : 60px;
	font-weight : bolder;
}
</style>
<body>
	<script>
		function displayDiv(type) {
			if (type == 1) {
				document.getElementById("write").style.display = 'block';
			} else if (type == 2) {
				document.getElementById("write").style.display = 'none';
				document.getElementById("picked").style.display = 'block';
			} else if (type == 3) {
				document.getElementById("write").style.display = 'none';
			}
		}
	</script>



	<%
		ArrayList<NewsVO> list = (ArrayList<NewsVO>) request.getAttribute("list");
		if (list != null) {
	%>
	<h2>뉴스게시판</h2>
	<hr>
	<div id="table">
		<table>
			<tr id="first">
				<th id="id">번호</th>
				<th id="title">제목</th>
				<th id="writer">작성자</th>
				<th id="date">작성일</th>
				<th id="cnt">조회수</th>
			</tr>
			<%
				for (NewsVO vo : list) {
			%>
			<tr>
				<td class='<%=vo.getId()%>' id="id"><%=vo.getId()%></td>
				<td class='<%=vo.getId()%>' id="title">
					<a href='http://70.12.115.175:8000/mvc/news?action=read&newsid=<%=vo.getId()%>'
					style='text-decoration: none; color: black;'><%=vo.getTitle()%></a></td>
				<td class='<%=vo.getId()%>' id="writer">
					<a href='http://70.12.115.175:8000/mvc/news?writer=<%=vo.getWriter()%>'
					style='text-decoration: none; color: black;'><%=vo.getWriter()%></a></td>
				<td class='<%=vo.getId()%>' id="date"><%=vo.getWriteDate()%></td>
				<td class='<%=vo.getId()%>' id="cnt"><%=vo.getCnt()%></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
	<br>
	
	<form method = "get" action ="/mvc/news">
	<select name="searchtype">
		<option value="title">제목</option>
		<option value="writer">작성자</option>
	</select>
	<input type = "search" name = "key" >
	<input type="submit" value="뉴스검색">
	</form>
	
	<div class="button">
		<button onclick="displayDiv(1);">뉴스 작성</button>
	</div>
	<%
		NewsVO nvo = (NewsVO) request.getAttribute("read");
			if (nvo != null) {
	%>
	<div id="picked" style="display: block">
		<h2>뉴스 내용</h2>
		<form method="post" action="/mvc/news">
			<input type="hidden" name="action" value="update"> <input
				type="hidden" name="id" value="<%=nvo.getId()%>"> <input
				id="pWriter" type="text" name="writer" width="400"
				value='<%=nvo.getWriter()%>'><br> <input id="pTitle"
				type="text" name="title" width="500" value='<%=nvo.getTitle()%>'><br>
			<textarea id="pContent" rows="5" cols="21" name="content"><%=nvo.getContent()%></textarea>
			<br> <input type="button" value="확인"
				onclick="location.href='/mvc/news'"> <input type="submit"
				value="수정"> <input type="button" value="삭제"
				onclick="location.href='/mvc/news?action=delete&newsid=<%=nvo.getId()%>'">
		</form>
	</div>
	<%
		}
		} //이거 위에 if 끝
	%>


	<div id="write" style="display: none">
		<form method="post" action='/mvc/news'>
			<input type="hidden" name="action" value="insert"> <input
				id="wWriter" type="text" name="writer" width="400"
				placeholder="작성자명을 입력하여 주세요"><br> <input id="wTitle"
				type="text" name="title" width="400" placeholder="제목을 입력하여 주세요"><br>
			<textarea id="wContent" rows="5" cols="21" name="content"
				placeholder="내용을 입력하여 주세요"></textarea>
			<br> <input type="submit" value="등록"> <input
				type="reset" value="재작성"> <input type="reset" value="취소"
				onclick='displayDiv(3)'>
		</form>
	</div>


</body>
</html>

```

