package dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.salesVO;

@Repository
public class consultingViewDAO {
	
	@Autowired
	SqlSession session = null;
	
	public String listAll(){
		String vo = null;
		String statement = "resource.BackstreetMapper.changeConsulting1";
		vo = session.selectOne(statement,1000501);
		return vo;
	}
	
	public long sales_1Area1(salesVO vo){
		long money;
		//System.out.println(vo);
		String statement = "resource.BackstreetMapper.sales_1Area1";
		money = session.selectOne(statement,vo);
		System.out.println("DAO 에서 추출된 값"+money);
		return money;
	}
	
	//한꺼번에 처리하려다가 실패한 내용 ㄱ ㄱ 
/*	public salesVO sales_1Area1(salesVO vo){
	long money;
	salesVO vo2;
	//System.out.println(vo);
	String statement = "resource.BackstreetMapper.sales_1Area1";
	//money = session.selectOne(statement,vo);
	vo2 = session.selectOne(statement,vo);
	System.out.println("DAO 에서 추출된 값"+vo2);
	return vo2;
}*/

}