# JDBC(실습)

```java
package jdbcsrc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ReadVisitor22 {

	public static void main(String[] args) throws Exception{
		//1. JDBC Driver 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//2. DBMS에 접속 
		Connection conn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
							"jdbctest","jdbctest");
		System.out.println(conn);
		//3.Statement / PreparedStatement 객체 생성
		Statement st = conn.createStatement();
		System.out.println(st);
		Scanner sc = new Scanner(System.in);//Scanner로 입력값 변형준다
		System.out.println("검색할 이름을 입력하세요 : ");
		String srName = sc.nextLine();//입력값 객체 생성
		sc.close();
		//4. 처리하는 기능에 따라 SQL 문 전달
		String sql = "select name, writedate, memo from visitor2 where name = '"+srName+"'"; //where 문에 srName 객체 적용하여 뽑아냄 ''꼭 써줘야함
		ResultSet rs = st.executeQuery(sql);//statement executeQuery의 리턴값은 ResultSet(Select 구문)
		//5.결과처리
		if(rs.next()) {
			System.out.println(rs.getString("name")+"  "+rs.getDate("writedate")+"  "+rs.getString("memo"));
		}else {
			System.out.println(srName + "님이 작성한 글이 없습니다.");
		}
		System.out.println("-----------끝-----------");
		rs.close();
		st.close();
		conn.close();

	}

}
```



