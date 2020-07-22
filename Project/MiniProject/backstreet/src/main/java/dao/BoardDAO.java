package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.BoardVO;

@Repository
public class BoardDAO {
	@Autowired
	SqlSession session = null;
	//0309 jung 전체 리스트 출력
	public List<BoardVO> listAll() {
		List<BoardVO> list = new ArrayList<BoardVO>();
		String statement = "resource.BoardMapper.select";
		list = session.selectList(statement);
		return list;
	}
	//0309 jung 데이터 입력
	public boolean insert(BoardVO vo) {
		System.out.println(" date: " + vo.getWritedate());
		boolean result = false;
		String statement = "resource.BoardMapper.insert";
		if (session.insert(statement, vo) == 1)
			result = true;
		return result;
	}
	// 0309 jung  데이터 검색
	public List<BoardVO> search(String keyword, String searchType) {
		List<BoardVO> list =  new ArrayList<BoardVO>();
		String statement = "resource.BoardMapper.search";
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", keyword);
		map.put("searchType", searchType);
		list = session.selectList(statement, map);
		return list;
	}
	// 0309 jung 작성자로 검색 
	public List<BoardVO> listWriter(String writer) {
		List<BoardVO> list = new ArrayList<BoardVO>();
		String statement = "resource.BoardMapper.selectWriter";
		list = session.selectList(statement, writer);
		return list;
	}
	// 0309 jung 데이터 삭제
	public boolean delete(int id) {
		boolean result = false;
		String statement = "resource.BoardMapper.delete";
		if (session.delete(statement, id) == 1)
			result = true;
		return result;
	}
	// 0309 jung 데이터 업데이트
	public boolean update(BoardVO vo) {
		boolean result = false;
		String statement = "resource.BoardMapper.update";
		System.out.println("dao의id"+vo.getBoard_id());
		if (session.update(statement, vo) == 1)
			result = true;
		System.out.println("DAO result값"+result);
		return result;
	}
	// 0309 jung  한개만 출력
	public BoardVO listOne(int board_id) {
		BoardVO vo = null;
		String statement = "resource.BoardMapper.read";
		vo = session.selectOne(statement, board_id);
		System.out.println(vo);
		
		 String statement2 = "resource.BoardMapper.Cntup";		 
		 session.selectOne(statement2, board_id);
		 

		return vo;
	}

}
