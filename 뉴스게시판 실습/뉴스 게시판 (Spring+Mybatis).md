# 뉴스 게시판 (Spring+Mybatis)

#### newsView.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.NewsVO, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Cute+Font:400" rel="stylesheet">
<style>
body{
	font-family: "Cute Font", sans-serif;
	font-size : 20px;
}
h1#pagename {
	color : green;
	text-align: center;
	text-shadow : 2px 2px 2px brown;
	font-family: "Cute Font", sans-serif;
	font-size : 40px;
}

table {
	margin: auto;
	box-align: center;
}

.simple_table {
    width: 100%;
    border: none;
    border-collapse: separate;
    border-spacing: 2px;
}
 
.simple_table th {
    padding: 15px;
    border: none;
    border-left: 5px solid #C03;
    border-bottom: 1px solid #DDD;
    background: #FCF0F3;
    font-weight: normal;
    text-align:center;
    text-shadow: 0 1px #FFF;
    font-size : 30px;
    vertical-align: middle;
}
 
.simple_table td {
    padding: 15px;
    border: none;
    border-bottom: 1px solid #DDD;
    text-align: left;
    vertical-align: baseline;
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
				document.getElementById("picked").style.display = 'none';
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
		NewsVO nvo = (NewsVO) request.getAttribute("read");
		if (list != null) {
	%>
	<h1 id="pagename"><a href='http://70.12.115.175:8000/springnews/newsmain'>
		하나가 먹다 둘이 죽는 ★영기네★ 버섯 농장</a></h1>
	<div id="table">
		<table class="simple_table">
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
					<a href='http://70.12.115.175:8000/springnews/listOne?action=read&id=<%=vo.getId()%>'
					style='text-decoration: none; color: black;'><%=vo.getTitle()%></a></td>
				<td class='<%=vo.getId()%>' id="writer">
					<a href='http://70.12.115.175:8000/springnews/writer?writer=<%=vo.getWriter()%>'
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
	
	<form method = "get" action ="/springnews/search">
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
		
			if (nvo != null) {
	%>
	<div id="picked" style="display: block">
		<h2>뉴스 내용</h2>
		<form method="post" action="/springnews/update">
			<input type="hidden" name="action" value="update">
			 <input type="hidden" name="id" value="<%=nvo.getId()%>"> 
			<input id="pWriter" type="text" name="writer" width="400" value='<%=nvo.getWriter()%>'><br> 
			<input id="pTitle" type="text" name="title" width="500" value='<%=nvo.getTitle()%>'><br>
			<textarea id="pContent" rows="5" cols="21" name="content"><%=nvo.getContent()%></textarea>
			<br> <input type="button" value="확인"
				onclick="location.href='/springnews/newsmain'"> 
				<input type="submit" value="수정"> 
				<input type="button" value="삭제"
				onclick="location.href='http://70.12.115.175:8000/springnews/delete?action=delete&id=<%=nvo.getId()%>'">
		</form>
	</div>
	<%
		}
		} //이거 위에 if 끝
	%>


	<div id="write" style="display: none">
		<form method="post" action='/springnews/insert'>
			<input type="hidden" name="action" value="insert"> 
			<input id="wWriter" type="text" name="writer" width="400" placeholder="작성자명을 입력하여 주세요">
			<br> 
			<input id="wTitle" type="text" name="title" width="400" placeholder="제목을 입력하여 주세요">
			<br>
			<textarea id="wContent" rows="5" cols="21" name="content" placeholder="내용을 입력하여 주세요"></textarea>
			<br> 
			<input type="submit" value="등록"> 
			<input type="reset" value="재작성"> 
			<input type="reset" value="취소" onclick='displayDiv(3)'>
		</form>
	</div>


</body>
</html>


```

#### NewsDAO

```java
package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.NewsVO;
@Repository
public class NewsDAO {
	///////////////
	@Autowired
	SqlSession session = null;
	String statement="";

	public List<NewsVO> listAll() {
		System.out.println("마이베티스 listAll");
		List<NewsVO> list = null;
		statement = "resource.NewsMapper.allNews";
		list = session.selectList(statement);
		return list;
	}
	
	public boolean insert(NewsVO vo) {
		System.out.println("마이베티스 insert");
		boolean result = false;
		statement = "resource.NewsMapper.insertNews";
		if(session.insert(statement,vo)==1)
			result = true;
		return result;
	}

	public List<NewsVO> search(String key, String searchtype) {
//		System.out.println("마이베티스 search");
		List<NewsVO> list = null;
//		System.out.println("["+searchtype+"]");
//		System.out.println("["+key+"]");
		if(searchtype.equals("title"))
			statement = "resource.NewsMapper.searchNews1";
		else
			statement = "resource.NewsMapper.searchNews2";
		
//		System.out.println(statement);
		list = session.selectList(statement, key);
//		System.out.println(list);
		return list;
	}
	
	public boolean delete(int id) {
		boolean result = false;
		statement = "resource.NewsMapper.deleteNews";
		if(session.delete(statement,id)==1)
			result=true;
		return result;
	}
	
	public boolean update(NewsVO vo) {
		System.out.println(vo);
		boolean result = false;
		statement = "resource.NewsMapper.updateNews";
		System.out.println(vo.getId());
		if(session.delete(statement,vo)==1)
			result=true;
		return result;
	}	

	public List<NewsVO> listWriter(String writer) {
		List<NewsVO> list = null;
		statement = "resource.NewsMapper.writerNews";
		list = session.selectList(statement,writer);
		return list;
	}

	public NewsVO listOne(int id) {
		NewsVO vo = null;
		statement = "resource.NewsMapper.countNews";
		vo = session.selectOne(statement, id);
		statement = "resource.NewsMapper.readNews";
		vo = session.selectOne(statement, id);
		return vo;
	}
	
///////페이징 처리//////////////

}

```

#### NewsController

```java
package my.spring.springnews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.NewsDAO;
import vo.NewsVO;

@Controller
public class NewsController {
	
	@Autowired
	NewsDAO dao;	
	@RequestMapping(value="/newsmain", method=RequestMethod.GET)
	public ModelAndView newsmain() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list",dao.listAll());
		mav.setViewName("newsView");
		return mav;
	}
	@RequestMapping(value="/listOne", method=RequestMethod.GET)
	public ModelAndView one(String action, String id) {
		ModelAndView mav = new ModelAndView();
		if(action!=null&&action.equals("read"))
			mav.addObject("read", dao.listOne(Integer.parseInt(id)));
		mav.addObject("list",dao.listAll());
		mav.setViewName("newsView");
		return mav;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public ModelAndView del(NewsVO vo,String action,String id) {
		ModelAndView mav = new ModelAndView();
		if(action!=null&&action.equals("delete")) {
			boolean result = dao.delete(Integer.parseInt(id));
			if(result) {
				mav.addObject("msg", "글이 성공적으로 삭제되었습니다.");
			}else {
				mav.addObject("msg", "글이 삭제되지 않았습니다.");
			}
		}
		mav.addObject("list",dao.listAll());
		mav.setViewName("newsView");
		return mav;
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ModelAndView search(String key, String searchtype) {
		ModelAndView mav = new ModelAndView();
		if(searchtype!=null&&key!=null)
			mav.addObject("list",dao.search(key, searchtype));
		mav.setViewName("newsView");
		return mav;
	}
	
	@RequestMapping(value="/writer", method=RequestMethod.GET)
	public ModelAndView writer(String writer) {
		ModelAndView mav = new ModelAndView();
		if(writer!=null)
			mav.addObject("list",dao.listWriter(writer));
		mav.setViewName("newsView");
		return mav;
	}
	
	@RequestMapping(value= {"/insert","/update"}, method=RequestMethod.POST)
	public ModelAndView inu(String action, String title, NewsVO vo) {
		ModelAndView mav = new ModelAndView();
		if(action!=null) {
			if(action.equals("insert")) { 
				dao.insert(vo);
			}else if(action.equals("update")) {
				dao.update(vo);
			}
		}
		mav.addObject("list",dao.listAll());
		mav.setViewName("newsView");
		return mav;
	}
	
}

```

#### NewsVO

```java
package vo;

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
	
	
	 @Override 
	 public String toString() { return "NewsVO [id=" + id +"writer=" + writer +
	  ", title=" + title +", content=" + content +", writedate=" + writeDate+", cnt=" + cnt + "]" ; }
	 
}

```

#### NewsMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="resource.NewsMapper">
 	<select id="allNews"  resultType="vo.NewsVO"> 
 		select id, title, writer, to_char(writedate,'yyyy-mm-dd') writedate, cnt from news
 	</select>
  	<insert id="insertNews"  parameterType="vo.NewsVO">
  		<selectKey resultType="int" keyProperty="id" order="BEFORE">
  			select news_seq.nextval from dual
  		</selectKey>
  		insert into news (id, writer, title, content, writedate, cnt) values (news_seq.currval, #{writer}, #{title},#{content},
  		 sysdate, #{cnt})
    </insert>
	<select id="searchNews1" parameterType="string" resultType= "vo.NewsVO">
		select id, writer, title, to_char(writedate, 'yyyy-mm-dd') writedate, cnt from news where title like '%'||#{key}||'%'
    </select>
	<select id="searchNews2" parameterType="string" resultType= "vo.NewsVO">
		select id, writer, title, to_char(writedate, 'yyyy-mm-dd') writedate, cnt from news where writer like '%'||#{key}||'%'
    </select>
    <delete id="deleteNews" parameterType="_int">
    	delete from news where id = #{id}
    </delete>
    <update id="updateNews" parameterType="vo.NewsVO" >
		update news set
			writer = #{writer}, title = #{title},
			writedate = sysdate, content = #{content} where id = #{id}
	</update>
	<select id="writerNews" parameterType="string" resultType= "vo.NewsVO">
		select id, title, writer, to_char(writedate, 'yyyy-mm-dd') writedate, cnt from news where writer = #{writer}
    </select>
    
    <update id="countNews" parameterType="_int" >
		update news set cnt = cnt+1 where id = #{id}
	</update>
	<select id="readNews" parameterType="_int" resultType= "vo.NewsVO">
		select id, title, writer, content from news where id = #{id}
    </select>
    
</mapper>









```



