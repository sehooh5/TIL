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

	/*
	 * public List<NewsVO> listAll(int pageNum) { List<NewsVO> list = new
	 * ArrayList<>(); Connection conn = connectDB();
	 * 
	 * try (Statement stmt = conn.createStatement(); ResultSet rs =
	 * stmt.executeQuery( "select id, title, writer, " +
	 * "to_char(writedate, 'yyyy-mm-dd'),cnt from news");) { NewsVO vo; for(int
	 * count=20*(pageNum-1)+1;count<20*pageNum ;count++){ vo = new NewsVO();
	 * vo.setId(Integer.parseInt(rs.getString(1))); vo.setTitle(rs.getString(2));
	 * vo.setWriter(rs.getString(3)); vo.setWriteDate(rs.getString(4));
	 * vo.setCnt(Integer.parseInt(rs.getString(5))); list.add(vo); } } catch
	 * (SQLException e) { e.printStackTrace(); } return list; }
	 */

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

