# Mybatis 예제

[TOC]



#### VisitorMybatisDAO

```java
package model.dao;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.vo.VisitorVO;

public class VisitorMybatisDAO {	
	final String resource = "resource/mybatis-config.xml"; // src 폴더 기준
	
	//select 하는 기능 ----list로 리턴
	public List<VisitorVO> listAll() {
		System.out.println("Mybatis 를 사용 DB 연동-listAll");
		List<VisitorVO> list = null;		
		SqlSession session = null;
		try {
			//session 생성하는 부분
			//session 객체가 완성되면 기존 방법의
			//connection 과 prepared statement 가 완료된 것과 같다
			InputStream inputStream = 
					Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = 
					new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();
			
			//반드시 id 앞에 namespace 값 줘야한다
			String statement = "resource.VisitorMapper.selectVisitor";
			
			//resultType이 VisitorVO 객체를 만들고 arraylist 만들어서 리턴
			//selectList 하면 알아서 list 객체 만들고 담아서 리턴해준다
			list = session.selectList(statement);
			System.out.println(session.getClass().getName());		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	//search 하는 기능
	public List<VisitorVO> search(String keyword) {
		System.out.println("Mybatis 를 사용 DB 연동-search");
		List<VisitorVO> list = null;
		SqlSession session = null; 
		try {			
			InputStream inputStream = 
					Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = 
					new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();
			String statement = "resource.VisitorMapper.searchVisitor";
			list = session.selectList(statement, keyword);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;		
	}

	public boolean insert(VisitorVO vo) {
		System.out.println("Mybatis 를 사용 DB 연동-insert");
		boolean result = false;
		SqlSession session = null;
		try {
			InputStream inputStream = 
					Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = 
					new SqlSessionFactoryBuilder().build(inputStream);
//select, search 랑 다른점 ..매개변수에 true 준다
//true의 의미 : Mybatis 는 오토커밋이 아니어서 true 를 줘야 commit 해준다
			session = sqlSessionFactory.openSession(true);
			String statement = "resource.VisitorMapper.insertVisitor";
//insert 의 리턴값은 int 형인데 잘 들어가면 1줄 들어가기 때문에 1이 리턴되면 true 
			if(session.insert(statement, vo) == 1)			
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null)
				session.close();
		}
		return result;
	}	
}

```

#### VisitorMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="resource.VisitorMapper">
 	<select id="selectVisitor"  resultType="model.vo.VisitorVO">
    	select name, to_char(writedate,'yyyy"년"mm"월"dd"일"') writedate, memo from visitor
 	</select>
 	 <select id="selectVisitor1"  resultType="model.vo.VisitorVO">
    	select name, to_char(writedate,'yyyy"년"mm"월"dd"일"') writedate, memo
    	from visitor where id <![CDATA[<]]> 5 
 	</select>
  	<insert id="insertVisitor"  parameterType="model.vo.VisitorVO">
  		insert into visitor (name, writedate, memo) values (#{name},sysdate, #{memo})
    	</insert>
	<select id="searchVisitor" parameterType="string" resultType= "model.vo.VisitorVO">
    	select name, to_char(writedate,'yyyy"년"mm"월"dd"일"') writedate, memo 
    	from visitor where memo like '%'||#{key}||'%'
    </select>
</mapper>
```

#### **VisitorMybatisDAO2.java : 더 간단하게 만드는 방법

```java
package model.dao;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.vo.VisitorVO;
//DAO 더 간단하게 만드는 방법
public class VisitorMybatisDAO2 {	
	final String resource = "resource/mybatis-config.xml"; 
	//sqlSessionFactory 미리 생성해서 공유 
	SqlSessionFactory sqlSessionFactory;
	//DAO 의 생성자 메서드 만들어 사용
	public VisitorMybatisDAO2() {
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<VisitorVO> listAll() {
		System.out.println("Mybatis 를 사용 DB 연동-listAll-DAO2222");
		List<VisitorVO> list = null;		
		SqlSession session = sqlSessionFactory.openSession();
		String statement = "resource.VisitorMapper.selectVisitor";
		list = session.selectList(statement);
		System.out.println(session.getClass().getName());		
		session.close();
		return list;
	}

	public List<VisitorVO> search(String keyword) {
		System.out.println("Mybatis 를 사용 DB 연동-search-DAO2222");
		List<VisitorVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		String statement = "resource.VisitorMapper.searchVisitor";
		list = session.selectList(statement, keyword);
		session.close();
		return list;		
	}

	public boolean insert(VisitorVO vo) {
		System.out.println("Mybatis 를 사용 DB 연동-insert-DAO2222");
		boolean result = false;
		SqlSession session = sqlSessionFactory.openSession(true);
		String statement = "resource.VisitorMapper.insertVisitor";
		if(session.insert(statement, vo) == 1)			
			result = true;
		if(session != null)
			session.close();
		return result;
	}	
}

```



---


