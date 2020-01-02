# JDBC-DDL(200102)

#### <JDBC 는 Auto commit!!>

### 테이블 생성

```java
package jdbcsrc;

import java.sql.*;
public class CreateTable {
	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "jdbctest", "jdbctest");
		Statement stmt = conn.createStatement();
		if (args.length == 1 && args[0].equals("create")) {
			//executeUpdate 는 int 값을 리턴하는데 생성, 삭제때는 리턴 안함
			stmt.executeUpdate("CREATE TABLE product "
					+ "(id char(5), classid char(2), name varchar(50),balance int, price float)");
			System.out.println("테이블이 생성되었습니다.");
		} else {
			stmt.executeUpdate("DROP TABLE product");
			System.out.println("테이블이 삭제되었습니다.");
		}
		stmt.close();
		conn.close();
	}
}
```

- 아규먼트를 꼭 줘야한다.

---



### Insert 입력

```java
package jdbcsrc;

import java.sql.*;

public class InsertTable {
	public static void main(String args[]) throws Exception {
		try {
			// JDBC 드라이버를 로드한다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 데이터베이스에 접속한다. 적절한 JDBC URL 설정한다.
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "jdbctest", "jdbctest");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT into product values" + 
						"('00001', '10', '자바 프로그래밍',50, 16500)");
			stmt.executeUpdate("INSERT into product values" + 
						"('00002', '10', 'J2EE 구현 패턴',40, 12000)");
			stmt.executeUpdate("INSERT into product values"+
						"('00003', '10', 'JSP 2.0', 60, 18000)");
			System.out.println("데이터들을 추가하였습니다.");
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("오류 발생 : " + e);
		}
	}
}
```



```java
package jdbcsrc;

import java.sql.*;

public class InsertTable2 {
	public static void main(String args[]) throws Exception {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "jdbctest", "jdbctest");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT into product values"+
						"('"+args[0]+"', '"+args[1]+"', '"+args[2]+"',"+
					          args[3]+","+ args[4]+")");
			System.out.println("데이터들을 추가하였습니다.");
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("오류 발생 : " + e);
		}
	}
}
```



#### preparedStatement

```java
package jdbcsrc;

import java.sql.*;

public class InsertTable3 {
	public static void main(String args[]) throws Exception {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "jdbctest", "jdbctest");
			PreparedStatement pstmt = conn.prepareStatement(
				"INSERT into product values (?,?,?,?,?)");//동적파라미터
			pstmt.setString(1, args[0]);
			pstmt.setString(2, args[1]);
			pstmt.setString(3, args[2]);
			pstmt.setInt(4, Integer.parseInt(args[3]));
			pstmt.setFloat(5, Float.parseFloat(args[4]));
			pstmt.executeUpdate();
			System.out.println("데이터들을 추가하였습니다.");
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("오류 발생 : " + e);
		}
	}
}
```

