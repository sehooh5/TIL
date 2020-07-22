package dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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
		//System.out.println("DAO ���� ����� ��"+vo2);
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
	
	public JsonArray chart(salesVO vo) {
		JsonArray jsonArray = new JsonArray();
		salesVO vo2;
		String statement = "resource.BackstreetMapper.sales_2Area1";
		vo2 = session.selectOne(statement,vo);
		JsonObject jsonObject = new JsonObject();
		//System.out.println("dao에서 가져온 10대 매출"+vo2.getSal_money_10());
		jsonObject.addProperty("sal_money_10", vo2.getSal_money_10());
		jsonObject.addProperty("sal_money_20", vo2.getSal_money_20());
		jsonObject.addProperty("sal_money_30", vo2.getSal_money_30());
		jsonObject.addProperty("sal_money_40", vo2.getSal_money_40());
		jsonObject.addProperty("sal_money_50", vo2.getSal_money_50());
		jsonObject.addProperty("sal_money_60", vo2.getSal_money_60());
		jsonArray.add(jsonObject);
		//System.out.println("jsonArray 값 출력"+jsonArray);
		return jsonArray;
	}
	

	
}
