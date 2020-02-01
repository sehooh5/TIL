# Meeting JDBC 실습(DAO,VO)

[TOC]

#### meetingForm.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method = "post" action = "/mvc/meeting">
미팅 대상 이름 : <input type="text"  name="name">
<br>
미팅 목적 : <br>
<textarea rows="3" cols="30" name = "title" ></textarea>
<br>
날짜와 시간 :  <input type="datetime-local" name="meetingDate" value="2020-10-20T15:20">
<br>
<input type = "submit" value = "등록">
<input type = "reset" value = "재등록">
</form>
<hr>
<form method = "get" action ="/mvc/meeting">
검색어 : <input type = "search" name = "keyword" >
<input type = "submit" value = "검색">
<hr>
<button type="button" onclick="location.href='/mvc/meeting' ">미팅 스케쥴 보기</button>
</form>
</body>
</html>
```



#### MeetingVO

```java
package model.vo;

public class MeetingVO {
	private int id;
	private String name;
	private String meetingDate;
	private String title;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getMeetingDate() {
		return meetingDate;
	}
	public String getTitle() {
		return title;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	 @Override 
	 public String toString() { return "MeetingVO [name=" + name +
	  ", title=" + title +", meetingDate=" + meetingDate + "]" ; }
	 
}

```



#### MeetingDAO,JDBCDAO (interface 사용)

```java
package model.dao;
import java.util.List;
import model.vo.MeetingVO;
public interface MeetingDAO {
	public List<MeetingVO> listAll();
	public boolean insert(MeetingVO vo);
	public List<MeetingVO> search(String keyword);
	public boolean delete(int eNo);	
}


/////////////////////////////////////////////////////    
    
    
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

public class MeetingJDBCDAO implements MeetingDAO {
	@Override
	public List<MeetingVO> listAll(){
		List<MeetingVO> list = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbctest", "jdbctest");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select id, name, title, "
						+ "to_char(meetingdate, 'yyyy\"년\" mm\"월\" dd\"일\" hh24\"시\" mi\"분\"') from meeting");){
			MeetingVO vo;
			while(rs.next()) {
				vo = new MeetingVO();
				vo.setId(Integer.parseInt(rs.getString(1)));
				vo.setName(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setMeetingDate(rs.getString(4));
				list.add(vo);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public boolean insert(MeetingVO vo){
		boolean result = true;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbctest", "jdbctest");
				PreparedStatement pstmt = conn.prepareStatement(
						"insert into meeting values"
						+ "(meeting_seq.nextval, ?, ?, to_date(?, 'yyyy-mm-dd\"T\"hh24:mi'))");){
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getMeetingDate());
			pstmt.executeUpdate();
			}catch(SQLException e){
				result = false;
				e.printStackTrace();
			}
			return result;
		}
	@Override	
	public List<MeetingVO> search(String keyword){
		List<MeetingVO> list = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbctest", "jdbctest");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select id, name, title, "
						+ "to_char(meetingdate, 'yyyy\"년\" mm\"월\" dd\"일\" hh24\"시\" mi\"분\"') from meeting where title like '%"+keyword+"%'");){
			MeetingVO vo;
			while(rs.next()) {
				vo = new MeetingVO();
				vo.setId(Integer.parseInt(rs.getString(1)));
				vo.setName(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setMeetingDate(rs.getString(4));
				list.add(vo);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean delete(int eNo) {
		boolean result = true;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbctest", "jdbctest");
				PreparedStatement pstmt = conn.prepareStatement(
						"delete from meeting where id = "+eNo);){
			pstmt.executeUpdate();
			}catch(SQLException e){
				result = false;
				e.printStackTrace();
			}
			return result;
		}


}

```



#### MeetingServlet.java

```java
package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MeetingDAO;
import model.dao.MeetingJDBCDAO;
import model.vo.MeetingVO;


@WebServlet("/meeting")
public class MeetingServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String keyword = request.getParameter("keyword");
		String del = request.getParameter("del");
		MeetingDAO dao = new MeetingJDBCDAO();
		
		if(del == null) {
		if(keyword == null) {
			List<MeetingVO> list = dao.listAll();
			request.setAttribute("list", list);
		} else {
			List<MeetingVO> list = dao.search(keyword);
			if(list.size() == 0) {
				request.setAttribute("msg", keyword+"를 담고있는 글이 없어용");
			} else {
				request.setAttribute("list", list);
			}
		}
		}else if(del.equals("yes")) {
			String id = request.getParameter("id");
			dao.delete(Integer.parseInt(id)); 
			List<MeetingVO> list = dao.listAll();
			request.setAttribute("list", list);
			/*
			 * if(keyword != null) { List<MeetingVO> list = dao.search(keyword);
			 * request.setAttribute("list", list); }else { List<MeetingVO> list =
			 * dao.listAll(); request.setAttribute("list", list); }
			 */

		}
		request.getRequestDispatcher("/jspexam/meetingView.jsp")
        .forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String date = request.getParameter("meetingDate");
		MeetingDAO dao = new MeetingJDBCDAO();
		MeetingVO vo = new MeetingVO();
		vo.setName(name);
		vo.setTitle(title);
		vo.setMeetingDate(date);
		boolean result = dao.insert(vo);
		if(result) {
			request.setAttribute("msg", name+"님의 글이 성공적으로 입력되었어요!!..");
		} else {
			request.setAttribute("msg", name+"님의 글이 입력에 실패했어요!!");
		}
		request.getRequestDispatcher("/jspexam/meetingView.jsp")
		           .forward(request, response);
	}
}

```



#### meetingView.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.vo.MeetingVO, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일정 목록</title>
<style>
	td {
		border-bottom : 1px dotted green;
	}
	tr:hover {
		background-color : pink;
		font-weight : bold;
	}
	td:nth-child(3) {
		width : 400px;
	}
	img{
		width : 18px;
		height : 18px;
	}
</style>
</head>
<body>
<%
	List<MeetingVO> list = (List<MeetingVO>)request.getAttribute("list");
    if (list != null) {
%>
    	<h2>방명록 글 리스트</h2><hr>
    	<table>    
<%	
    	for(MeetingVO vo : list) { 	   
%>
			<tr>
				<td><%= vo.getName() %></td>
				<td><%= vo.getTitle() %></td>
				<td><%= vo.getMeetingDate() %></td>
				<td><a href="/mvc/meeting?del=yes&id=<%= vo.getId() %>" ><img src="http://70.12.115.175:8000/mvc/images/hu.jpg"></a></td>		
			</tr>
<%
    	}
%>
    	</table>
<%
    } else {
%>
		<h2>${msg}</h2>
<%
    }
%>
<hr>
<a href="/mvc/htmlexam/meetingForm.html ">미팅 홈 화면으로 가기</a>

</body>
</html>




```



