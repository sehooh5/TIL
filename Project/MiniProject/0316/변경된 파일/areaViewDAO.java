package dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.areaVO;
import vo.salesVO;
import vo.serviceVO;

@Repository
public class areaViewDAO {
	
	@Autowired
	SqlSession session = null;
		
	public salesVO sales_1Area1(salesVO vo){
		salesVO vo2;
		//System.out.println(vo);
		String statement = "resource.BackstreetMapper.sales_1Area1";
		vo2 = session.selectOne(statement,vo);
		//System.out.println("DAO 에서 추출된 값"+vo2);
		return vo2;
	}

	public salesVO sales_2Area1(salesVO vo){
		salesVO vo2;
		String statement = "resource.BackstreetMapper.sales_2Area1";
		vo2 = session.selectOne(statement,vo);
		return vo2;
	}

	public salesVO sales_3Area1(salesVO vo){
		salesVO vo2;
		String statement = "resource.BackstreetMapper.sales_3Area1";
		vo2 = session.selectOne(statement,vo);
		return vo2;
	}
	
	public salesVO sales_4Area1(salesVO vo){
		salesVO vo2;
		String statement = "resource.BackstreetMapper.sales_4Area1";
		vo2 = session.selectOne(statement,vo);
		return vo2;
	}
	
	public areaVO area_Area1(areaVO vo){
		areaVO vo2;
		String statement = "resource.BackstreetMapper.area_Area1";
		vo2 = session.selectOne(statement,vo);
		return vo2;
	}
	
	public serviceVO service_Area1(serviceVO vo){
		serviceVO vo2;
		String statement = "resource.BackstreetMapper.service_Area1";
		vo2 = session.selectOne(statement,vo);
		return vo2;
	}
	
}
