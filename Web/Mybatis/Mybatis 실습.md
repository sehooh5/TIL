# Mybatis 실습

### Meeting : select, insert, update, delete, search

#### MeetingMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="resource.MeetingMapper">
 	<select id="selectMeeting"  resultType="model.vo.MeetingVO">
 		select id, name, title, to_char(meetingdate,'yyyy"년" mm"월" dd"일" HH24"시"mi"분"') meetingDate from meeting
 	</select>
  	<insert id="insertMeeting"  parameterType="model.vo.MeetingVO">
  		<selectKey resultType="int" keyProperty="id" order="BEFORE">
  			select meeting_seq.nextval from dual
  		</selectKey>
  		insert into meeting (id, name, title, meetingdate) values (meeting_seq.nextval, #{name}, #{title}, to_date(#{meetingDate}, 'yyyy-mm-dd"T"hh24:mi'))
    </insert>
	<select id="searchMeeting" parameterType="string" resultType= "model.vo.MeetingVO">
		select id, name, title, to_char(meetingdate, 'yyyy"년" mm"월" dd"일" HH24"시" mi"분"') meetingDate 
						from meeting where title like '%'||#{keyword}||'%'
    </select>
    <delete id="deleteMeeting" parameterType="_int">
    	delete from meeting where id = #{id}
    </delete>
    <update id="updateMeeting" parameterType="model.vo.MeetingVO" >
		update meeting set
			name = #{name}, title = #{title},
			meetingdate = to_date(#{meetingDate}, 'yyyy-mm-dd"T"HH24:mi' )
		where id = #{id}
	</update>
</mapper>
```

#### MeetingMybatisDAO.java

```java
package model.dao;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.vo.MeetingVO;

public class MeetingMybatisDAO implements MeetingDAO {
	final String resource = "resource/mybatis-config.xml";
	SqlSessionFactory sqlSessionFactory;
	public MeetingMybatisDAO() {
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<MeetingVO> listAll(){
		System.out.println("MyBatis 로 DB 연동 : listAll()");
		List<MeetingVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		String statement = "resource.MeetingMapper.selectMeeting";
		list = session.selectList(statement);
		session.close();
		return list;
	}

	public boolean insert(MeetingVO vo){
		System.out.println("MyBatis 로 DB 연동 : insert()");
		boolean result = false;
		SqlSession session = sqlSessionFactory.openSession(true);
		String statement = "resource.MeetingMapper.insertMeeting";
		session.insert(statement, vo);
		if(session.insert(statement, vo)==1)
			result = true;
		if(session != null)
			session.close();
			return result;
		}

	public List<MeetingVO> search(String keyword){
		System.out.println("MyBatis 로 DB 연동 : search()");
		List<MeetingVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		String statement = "resource.MeetingMapper.searchMeeting";
		list = session.selectList(statement,keyword);
		session.close();
		return list;
	}


	public boolean delete(int id) {
		boolean result = false;
		System.out.println("MyBatis 로 DB 연동 : delete()");
		SqlSession session = sqlSessionFactory.openSession(true);
		String statement = "resource.MeetingMapper.deleteMeeting";
		if(session.delete(statement,id)==1)
			result = true;
		if(session != null)
			session.close();
			return result;
		}
	

	public boolean update(MeetingVO vo) {
		System.out.println("MyBatis 로 DB 연동 : update()");
		boolean result = false;
		SqlSession session = sqlSessionFactory.openSession(true);
		String statement = "resource.MeetingMapper.updateMeeting";
		if(session.update(statement,vo)==1)
			result = true;
		if(session != null)
			session.close();
		return result;
	}


}
```

#### MeetingServlet2

```java
package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MeetingMybatisDAO;
import model.vo.MeetingVO;

@WebServlet("/meeting2")
public class MeetingServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		String id = request.getParameter("id");
		String action = request.getParameter("action");

		MeetingMybatisDAO dao = new MeetingMybatisDAO();
		if(keyword == null) {
			if(action != null) {
				boolean result = dao.delete(Integer.parseInt(id));
				if (result) {
					request.setAttribute("msg", "글이 성공적으로 삭제되었습니다.");
				} else {
					request.setAttribute("msg", "글이 삭제되지 않았습니다.");
				}
			} 
			request.setAttribute("list", dao.listAll());
		} else {
			List<MeetingVO> list = dao.search(keyword);
			if (list != null && list.size() == 0) {
				request.setAttribute("msg", keyword + "(이)가 포함된 글이 없습니다.");
			} else {
				request.setAttribute("list", dao.search(keyword));
			}
		}
		request.getRequestDispatcher("/jspexam/meetingView2.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String meetingDate = request.getParameter("meetingDate");
		MeetingMybatisDAO dao = new MeetingMybatisDAO();
		MeetingVO vo = new MeetingVO();
		vo.setName(name);
		vo.setTitle(title);
		vo.setMeetingDate(meetingDate);
		if(action.equals("insert")) {
			boolean result = dao.insert(vo);
			if (result) {			
				request.setAttribute("msg", name + "님의 글이 성공적으로 입력되었습니다.");			
			} else {
				request.setAttribute("msg", name + "님의 글이 입력되지 않았습니다.");
			}
		} else {
			vo.setId(Integer.parseInt(action));
			boolean result = dao.update(vo);
			if (result) {			
				request.setAttribute("msg", name + "님의 글이 성공적으로 수정되었습니다.");			
			} else {
				request.setAttribute("msg", name + "님의 글이 수정되지 않았습니다.");
			}
		}
		request.setAttribute("list", dao.listAll());
		request.getRequestDispatcher("/jspexam/meetingView2.jsp").forward(request, response);
	}
}

```

