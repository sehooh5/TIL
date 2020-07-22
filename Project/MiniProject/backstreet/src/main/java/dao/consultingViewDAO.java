package dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import vo.apartmentVO;
import vo.areaVO;
import vo.changeVO;
import vo.jobVO;
import vo.livingPopulationVO;
import vo.serviceVO;
import vo.storeVO;

@Repository
public class consultingViewDAO {
   
   @Autowired
   SqlSession session = null;
   
// change ���̺�
   public changeVO changeConsulting1(int area_id){
      String statement = "resource.BackstreetMapper.changeConsulting1";
      changeVO vo;
      vo = session.selectOne(statement,area_id); // area_id �� ���� ��.
      return vo;
   } // ���̳���..
   
   public changeVO changeConsulting2(int area_id){
      String statement = "resource.BackstreetMapper.changeConsulting2";
      changeVO vo;
      vo = session.selectOne(statement,area_id); // area_id �� ���� ��.
      return vo;
   } // ���� ���� 
   
   public changeVO changeConsulting3(int area_id){
	      String statement = "resource.BackstreetMapper.changeConsulting2";
	      changeVO vo;
	      vo = session.selectOne(statement,area_id); // area_id �� ���� ��.
	      return vo;
   } // ��� ���� 
//change ���̺� ��
   
//area ���̺�
   public areaVO areaConsulting1(int area_id){
      String areaCodeName = null;
      String statement = "resource.BackstreetMapper.areaConsulting1";
      areaVO vo;
      vo = session.selectOne(statement,area_id); // area_id �� ���� ��.
      return vo;
   } // ������
   
   public changeVO areaConsulting2(int area_id){
      int change_id = 0;
      String statement = "resource.BackstreetMapper.areaConsulting2";
      changeVO vo;
      vo = session.selectOne(statement,area_id); // area_id �� ���� ��.
      return vo;
   } // ��ü, ������, ���Ȯ��, ���̳��� ���� �� �̱� ���� change_id
//area ���̺� ��ㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊㅊ
   
//service ���̺�
   public serviceVO serviceConsulting1(String serv_id){
      String serviceCodeName = null;
      String statement = "resource.BackstreetMapper.serviceConsulting1";
      serviceVO vo;
      vo = session.selectOne(statement,serv_id); // area_id �� ���� ��.
      return vo;
   } // ���� ���
//service ���̺� ��
   
//job ���̺�
   public jobVO jobConsulting1(int area_id) {
      int all_job_num = 0;
      String statement = "resource.BackstreetMapper.jobConsulting1";
      jobVO vo;
      vo = session.selectOne(statement,area_id);
      return vo;
   } //�� ���� �α��� ���
//job ���̺� ��

//living_population
   public livingPopulationVO livingPopulationConsulting1(int area_id) {
      int all_living_num = 0;
      String statement = "resource.BackstreetMapper.livingPopulationConsulting1";
      livingPopulationVO vo;
      vo = session.selectOne(statement,area_id);
      return vo;
   }
//living_population 끝
   
//apartment
   public apartmentVO apartmentConsulting1(int area_id) {
      int apart_num= 0;
      String statement = "resource.BackstreetMapper.apartmentConsulting1";
      apartmentVO vo;
      vo = session.selectOne(statement,area_id);
      return vo;
   }
//apartment 끝
   
//store
   public storeVO storeConsulting1(storeVO vo) {
      int store_num;
//      vo.setArea_id(1001495);
//      vo.setServ_id("CS300011");
      String statement = "resource.BackstreetMapper.storeConsulting1";
      storeVO vo1;
      vo1 = session.selectOne(statement,vo);
      return vo1;
   }
   
   public storeVO storeConsulting2(storeVO vo) {
      int sim_store_num;
//      vo.setArea_id(1001495);
//      vo.setServ_id("CS300011");
      String statement = "resource.BackstreetMapper.storeConsulting2";
      storeVO vo1;
      vo1 = session.selectOne(statement,vo);
      return vo1;
   }
   
   public storeVO storeConsulting3(storeVO vo) {
      int  start_rate;
//      vo.setArea_id(1001495);
//      vo.setServ_id("CS300011");
      String statement = "resource.BackstreetMapper.storeConsulting3";
      storeVO vo1;
      vo1 = session.selectOne(statement,vo);
      return vo1;
   }
   
   public storeVO storeConsulting4(storeVO vo) {
      int start_store_num;
//      vo.setArea_id(1001495);
//      vo.setServ_id("CS300011");
      String statement = "resource.BackstreetMapper.storeConsulting4";
      storeVO vo1;
      vo1 = session.selectOne(statement,vo);
      return vo1;
   }
   
   public storeVO storeConsulting5(storeVO vo) {
      int close_rate;
//      vo.setArea_id(1001495);
//      vo.setServ_id("CS300011");
      String statement = "resource.BackstreetMapper.storeConsulting5";
      storeVO vo1;
      vo1 = session.selectOne(statement,vo);
      return vo1;
   }
   
   public storeVO storeConsulting6(storeVO vo) {
      int close_store_num;
//      vo.setArea_id(1001495);
//      vo.setServ_id("CS300011");
      String statement = "resource.BackstreetMapper.storeConsulting6";
      storeVO vo1;
      vo1 = session.selectOne(statement,vo);
      return vo1;
   }
//store 끝
   
//view
   public float changeViewConsulting1(){
	      float oper_month_avg = 0;
	      String statement = "resource.BackstreetMapper.changeViewConsulting1";
	      oper_month_avg = session.selectOne(statement);
	      return oper_month_avg;
	   } // ���� ���� ���(View)
	   
	   public float changeViewConsulting2(){
	      float close_month_avg = 0;
	      String statement = "resource.BackstreetMapper.changeViewConsulting2";
	      close_month_avg = session.selectOne(statement);
	      return close_month_avg;
	   } // ��� ���� ���(View)
	   
	   public int jobViewConsulting1() {
	      int all_job_num_avg = 0;
	      String statement = "resource.BackstreetMapper.jobViewConsulting1";
	      all_job_num_avg = session.selectOne(statement);
	      return all_job_num_avg;
	   }
	   
	   public int livingPopulationViewConsulting1() {
	      int all_job_num_avg = 0;
	      String statement = "resource.BackstreetMapper.livingPopulationViewConsulting1";
	      all_job_num_avg = session.selectOne(statement);
	      return all_job_num_avg;
	   }
	   
	   public int apartmentViewConsulting1() {
	      int apart_num_avg = 0;
	      String statement = "resource.BackstreetMapper.apartmentViewConsulting1";
	      apart_num_avg = session.selectOne(statement);
	      return apart_num_avg;
	   }
	/////////////////////////////////////////////////////////////////// 0318
	   public int storeViewConsulting1() {
	      int start_rate_avg = 0;
	      String statement = "resource.BackstreetMapper.storeViewConsulting1";
	      start_rate_avg = session.selectOne(statement);
	      return start_rate_avg;
	   }
	   
	   public float storeViewConsulting2() {
		   float start_store_num_avg = 0;
	      String statement = "resource.BackstreetMapper.storeViewConsulting2";
	      start_store_num_avg = session.selectOne(statement);
	      return  start_store_num_avg;
	   }
	   
	   public int storeViewConsulting3() {
	      int close_rate_avg = 0;
	      String statement = "resource.BackstreetMapper.storeViewConsulting3";
	      close_rate_avg = session.selectOne(statement);
	      return close_rate_avg;
	   }
	   
	   public float storeViewConsulting4() {
	      float close_store_num_avg = 0;
	      String statement = "resource.BackstreetMapper.storeViewConsulting4";
	      close_store_num_avg = session.selectOne(statement);
	      return close_store_num_avg;
	   }
//view ��
	   
		public JsonArray chartMain() {
			JsonArray jsonArray = new JsonArray();
			JsonObject jsonObject = new JsonObject();
			String statement = "";
			float result1 = 0;
			int result2 = 0;
			//평균 운영 개월수
		    statement = "resource.BackstreetMapper.changeViewConsulting1";
		    result1 = session.selectOne(statement);
		    jsonObject.addProperty("open_month", result1);
		    //평균 폐업 개월수
		    statement = "resource.BackstreetMapper.changeViewConsulting2";
		    result1 = session.selectOne(statement);
		    jsonObject.addProperty("close_month", result1);
		    //총 직장인 수
		    statement = "resource.BackstreetMapper.jobViewConsulting1";
		    result2 = session.selectOne(statement);
		    jsonObject.addProperty("all_job", result2);
		    //총 상주 인구 수
		    statement = "resource.BackstreetMapper.livingPopulationViewConsulting1";
		    result2 = session.selectOne(statement);
		    jsonObject.addProperty("all_population", result2);
		    //평균 아파트 단지 수
		    statement = "resource.BackstreetMapper.apartmentViewConsulting1";
		    result2 = session.selectOne(statement);
		    jsonObject.addProperty("all_apart", result2);
		    //평균 개업률
		    statement = "resource.BackstreetMapper.storeViewConsulting1";
		    result2 = session.selectOne(statement);
		    jsonObject.addProperty("open_rate", result2);
		    //평균 개업 점포수
			statement = "resource.BackstreetMapper.storeViewConsulting2";
			result1 = session.selectOne(statement);
			jsonObject.addProperty("open_num", result1);
			//평균 폐업률
		    statement = "resource.BackstreetMapper.storeViewConsulting3";
		    result2 = session.selectOne(statement);
		    jsonObject.addProperty("close_rate", result2);
		    //평균 폐업 점포 수
		    statement = "resource.BackstreetMapper.storeViewConsulting4";
		    result1 = session.selectOne(statement);	
		    jsonObject.addProperty("close_num", result1);

			jsonArray.add(jsonObject);

			return jsonArray;
		}
   
}