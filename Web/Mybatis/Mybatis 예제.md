# Mybatis 예제

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
			InputStream inputStream = 
					Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = 
					new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();
			//반드시 id 앞에 namespace 값 줘야한다
			String statement = "resource.VisitorMapper.selectVisitor";
			//resultType이 VisitorVO 객체를 만들고 arraylist 만들어서 리턴
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
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
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
			session = sqlSessionFactory.openSession(true);
			String statement = "resource.VisitorMapper.insertVisitor";
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

#### VisitorMybatisDAO

```java

```

#### VisitorMybatisDAO

```java

```

#### VisitorMybatisDAO

```java

```

#### VisitorMybatisDAO

```java

```

#### VisitorMybatisDAO

```java

```

#### VisitorMybatisDAO

```java

```

#### VisitorMybatisDAO

```java

```

#### VisitorMybatisDAO

```java

```

#### VisitorMybatisDAO

```java

```

#### VisitorMybatisDAO

```java

```

#### VisitorMybatisDAO

```java

```

