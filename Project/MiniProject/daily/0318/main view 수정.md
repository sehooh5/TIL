# main view 수정

```java
package my.spring.backstreet;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;

import dao.areaViewDAO;
import dao.consultingViewDAO;
import service.getConsultingSearchResult;
import service.getLoginAccessToken;
import service.getLogoutUserId;
import service.getTrandsSearchResult;
import vo.areaVO;
import vo.salesVO;
import vo.serviceVO;
import vo.storeVO;


@Controller
public class mainController {
	
	@Autowired
	private getLoginAccessToken kakaoLogin;
	
	@Autowired
	private getLogoutUserId kakaoLogout;
	
	@Autowired
	private getConsultingSearchResult kakaoConsultingSearch;
	
	@Autowired
	private getTrandsSearchResult kakaoTrandsSearch;
	
	@Autowired
	private consultingViewDAO consultingViewDAO;
	
	@Autowired
	private areaViewDAO areaViewDAO;
	
	
	@RequestMapping(value="/test")
	public String testz() {
		return "test";
	}
	

	@RequestMapping(value="/loginView")
	public String loginView() {
		return "login";
	}
	@RequestMapping(value="/login")
	public ModelAndView login(@RequestParam("code") String code) {
		System.out.println("Controller - login �Լ�");
		System.out.println("code : " + code ); // ����� ������ ���� code ȹ�� -> Access Token, Refresh Token ȹ�� ���� -> API ���
		String accessToken = kakaoLogin.getAccessToken(code);
		System.out.println("AccessToke : " + accessToken);
		ModelAndView mav = new ModelAndView();
		mav.addObject("code",code);
		mav.addObject("accessToken", accessToken);
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping(value="/logout")
	public String logout(@RequestParam("accessToken") String accessToken) {
		System.out.println("Controller - logout �Լ�"); // ����� ������ ���� code ȹ�� -> Access Token, Refresh Token ȹ�� ���� -> API ���

		// �α׾ƿ� ���� Ŭ���� ����
		String UserId = kakaoLogout.getUserId(accessToken);
		System.out.println("UserID : " + UserId);
		return "main";
	}
	
	
	
	@RequestMapping(value="/trands")
	public String trands() {
		
		return "trands";
	}
	
	@RequestMapping(value="/trandsSearch")
	public ModelAndView trandsSearch(@RequestParam("query") String query) throws Exception {
		ModelAndView mav = new ModelAndView();
		String[] result = kakaoTrandsSearch.getTrands(query);
		mav.addObject("json",result);
		mav.setViewName("trands");
		return mav;
	}
	
	
	@RequestMapping(value="/consulting")
	public String consulting() {
		return "consulting";
	}

	//boardController................
	@RequestMapping(value="/consultingSearch")
	public ModelAndView search(@RequestParam("query") String query) throws Exception {
		float[] result = kakaoConsultingSearch.getResult(query);
		ModelAndView mav = new ModelAndView();
		mav.addObject("json", result);
		mav.setViewName("consulting");
		return mav;
	}
	
/////////////////////////////////////////////0318 수정 mainView
	@RequestMapping(value="/main", produces = "application/json; charset=utf-8")
	public ModelAndView mainView() {
		ModelAndView mav = new ModelAndView();
		
		JsonArray jsonArray = consultingViewDAO.chartMain();
		System.out.println("컨트롤러 jsonArray 출력"+jsonArray);
		mav.addObject("chart", jsonArray);
		    
		return mav;
	}
//////////////////////////////////////////////////////////////////
	
	//consultingView Controller......
	@RequestMapping(value="/changeConsulting1")
	public ModelAndView changeConsulting1(salesVO rvo, areaVO avo, serviceVO svo) {//int area_id �� ���߿� �Ű������� ���߿� ��ȣ���� �����ذ� ������ ��..
		String changeCodeName = consultingViewDAO.changeConsulting1(1001495);
//		System.out.println("��Ʈ�ѷ� changeCodeName :" + changeCodeName);
		//�ӽ÷�  ��������.. ���߿� �Ű������� �޾ƿ��� ���� 
		//��� ������ �޾ƿ��� DAO��.. ex) ���̳��� ���
		//���� ���
		
		String areaCodeName = consultingViewDAO.areaConsulting1(1001495); // 
//		System.out.println("��Ʈ�ѷ� areaCodeName :" + areaCodeName);
		// �ӽ÷�(area���̺��� area_id) ��������
		// ����ڵ��(area���̺��� area_coname)�� �޾ƿ��� DAO ex)���̿��������
		
		int change_id = consultingViewDAO.areaConsulting2(1001495);
	//	System.out.println("��Ʈ�ѷ� change_id :" + change_id);
		//change_id ������ ��ü�Ǿ� �ִ»��~ Ȱ���� ��� ����
		
		String serviceCodeName = consultingViewDAO.serviceConsulting1("CS300005");
	//	System.out.println("��Ʈ�ѷ� serviceCodeName :" + serviceCodeName);
		
		int oper_month = consultingViewDAO.changeConsulting2(1001495);
		//System.out.println("��Ʈ�ѷ� oper_month :" + oper_month);
		//change_id ������ ��ü�Ǿ� �ִ»��~ Ȱ���� ��� ����
		//���� ����
		
		int close_month = consultingViewDAO.changeConsulting3(1001495);
	//	System.out.println("��Ʈ�ѷ� close_month :" + close_month);
		//change_id ������ ��ü�Ǿ� �ִ»��~ Ȱ���� ��� ����
		//��� ����
		
		float oper_month_avg = consultingViewDAO.changeViewConsulting1();	
	//	System.out.println("��Ʈ�ѷ�oper_month_avg :" + oper_month_avg);
		// ��� ���� ����
		float close_month_avg = consultingViewDAO.changeViewConsulting2();
	//	System.out.println("��Ʈ�ѷ� close_month_avg :" + close_month_avg);
		// ��� ��� ����
		
		int all_job_num = consultingViewDAO.jobConsulting1(1001495);
//		System.out.println("��Ʈ�ѷ� all_job_num :" + all_job_num);
		//�� ���� �α���
		
		int all_job_num_avg = consultingViewDAO.jobViewConsulting1();
//		System.out.println("��Ʈ�ѷ� all_job_num_avg :" + all_job_num_avg);
		//�� ���� �α��� ���
		
	    
	      int all_living_num = consultingViewDAO.livingPopulationConsulting1(1001495);
//	      System.out.println("컨트롤러 all_living_num :" + all_living_num);
	      
	      int all_living_num_avg = consultingViewDAO.livingPopulationViewConsulting1();
//	      System.out.println("컨트롤러 all_living_num_avg :" +  all_living_num_avg);
	      
	      int apart_num = consultingViewDAO.apartmentConsulting1(1001495);
	      System.out.println("컨트롤러 apart_num :" +  apart_num);
	      
	      int apart_num_avg = consultingViewDAO.apartmentViewConsulting1();
	      System.out.println("컨트롤러 apart_num_avg :" +  apart_num_avg);
	      

	      
	      storeVO vo = new storeVO();
	      
	      int store_num = consultingViewDAO.storeConsulting1(vo);
	      System.out.println("컨트롤러 store_num :" +  store_num);
	      
	      int sim_store_num = consultingViewDAO.storeConsulting2(vo);
	      System.out.println("sim_store_num :" +  sim_store_num);
	      
	      int start_rate = consultingViewDAO.storeConsulting3(vo);
	      System.out.println("컨트롤러 start_rate :" +  start_rate);
	      
	      int start_store_num = consultingViewDAO.storeConsulting4(vo);
	      System.out.println("컨트롤러 start_store_num :" +  start_store_num);
	      
	      int close_rate = consultingViewDAO.storeConsulting5(vo);
	      System.out.println("컨트롤러 close_rate :" +  close_rate);
	      
	      int close_store_num = consultingViewDAO.storeConsulting6(vo);
	      System.out.println("컨트롤러 close_store_num :" +  close_store_num);
	      
	      
	      int start_rate_avg = consultingViewDAO.storeViewConsulting1();
	      System.out.println("컨트롤러 start_rate_avg :" +  start_rate_avg);
	      
	      float start_store_num_avg = consultingViewDAO.storeViewConsulting2();
	      System.out.println("컨트롤러 start_store_num_avg :" +  start_store_num_avg);
	      
	      int close_rate_avg = consultingViewDAO.storeViewConsulting3();
	      System.out.println("컨트롤러 close_rate_avg :" +  close_rate_avg);
	      
	      float close_store_num_avg = consultingViewDAO.storeViewConsulting4();
	      System.out.println("컨트롤러 close_store_num_avg :" +  close_store_num_avg);
	      
	      ModelAndView mav = new ModelAndView();
	      mav.addObject("changeCodeName", changeCodeName);
	      mav.addObject("areaCodeName", areaCodeName);
	      mav.addObject("change_id", change_id);
	      mav.addObject("serviceCodeName", serviceCodeName);
	      mav.addObject("oper_month", oper_month);
	      mav.addObject("close_month", close_month);
	      mav.addObject("oper_month_avg", oper_month_avg);
	      mav.addObject("close_month_avg", close_month_avg);
	      mav.addObject("all_job_num",all_job_num);
	      mav.addObject("all_job_num_avg",all_job_num_avg);
	      mav.addObject("all_living_num",all_living_num);
	      mav.addObject("all_living_num_avg",all_living_num_avg);
	      mav.addObject("apart_num",apart_num);
	      mav.addObject("apart_num_avg",apart_num_avg);
	      
	      mav.addObject("store_num", store_num);
	      mav.addObject("sim_store_num", sim_store_num);
	      mav.addObject("start_rate", start_rate);
	      mav.addObject("start_store_num", start_store_num);
	      mav.addObject("close_rate", close_rate);
	      mav.addObject("close_store_num", close_store_num);
	      
	      
	      mav.addObject("start_rate_avg", start_rate_avg);
	      mav.addObject("start_store_num_avg", start_store_num_avg);
	      mav.addObject("close_rate_avg", close_rate_avg);
	      mav.addObject("close_store_num_avg", close_store_num_avg);
	      
	      
	      
	      
////////////////////////////////////////////////////////////////////
			salesVO vo1 = areaViewDAO.sales_1Area1(rvo);
			areaVO vo2 = areaViewDAO.area_Area1(avo);
			serviceVO vo3 = areaViewDAO.service_Area1(svo);
		
			//area table
			mav.addObject("area_coname", vo2.getArea_coname());
			//service table 
			mav.addObject("serv_coname", vo3.getServ_coname());
			if(vo1!=null) {
			JsonArray jsonArray = areaViewDAO.chart(rvo);
			//10-60대 매출 그래프
			mav.addObject("chart", jsonArray);
			//sales_1 table
			mav.addObject("month_money", formatter.format(vo1.getMonth_sal_money()));
			mav.addObject("month_num", formatter.format(vo1.getMonth_sal_num()));
			mav.addObject("wday_money", formatter.format(vo1.getWday_sal_money()));
			mav.addObject("wkend_money", formatter.format(vo1.getWkend_sal_money()));
			mav.addObject("m_money", formatter.format(vo1.getM_sal_money()));
			mav.addObject("w_money", formatter.format(vo1.getW_sal_money()));
			//sales_2 table
			vo1 = areaViewDAO.sales_2Area1(rvo);
			mav.addObject("money_10", formatter.format(vo1.getSal_money_10()));
			mav.addObject("money_20", formatter.format(vo1.getSal_money_20()));
			mav.addObject("money_30", formatter.format(vo1.getSal_money_30()));
			mav.addObject("money_40", formatter.format(vo1.getSal_money_40()));
			mav.addObject("money_50", formatter.format(vo1.getSal_money_50()));
			mav.addObject("money_60", formatter.format(vo1.getSal_money_60()));
			//sales_3 table
			vo1 = areaViewDAO.sales_3Area1(rvo);
			mav.addObject("wday_num", formatter.format(vo1.getWday_sal_num()));
			mav.addObject("wkend_num", formatter.format(vo1.getWkend_sal_num()));
			mav.addObject("m_num", formatter.format(vo1.getM_sal_num()));
			mav.addObject("w_num", formatter.format(vo1.getW_sal_num()));
			mav.setViewName("report");
			//sales_4 table
			vo1 = areaViewDAO.sales_4Area1(rvo);
			mav.addObject("num_10", formatter.format(vo1.getSal_num_10()));
			mav.addObject("num_20", formatter.format(vo1.getSal_num_20()));
			mav.addObject("num_30", formatter.format(vo1.getSal_num_30()));
			mav.addObject("num_40", formatter.format(vo1.getSal_num_40()));
			mav.addObject("num_50", formatter.format(vo1.getSal_num_50()));
			mav.addObject("num_60", formatter.format(vo1.getSal_num_60()));
			}	      
	      
////////////////////////////////////////////////////////////////////
	      
			mav.setViewName("consultingView");
	      
	      
	      return mav;}
	
	

/////////////////////////////////////////////세호
	@RequestMapping(value="/map")
	public String mapStreet() {
		return "map";
	}
	
	
	@RequestMapping(value="/searchreport") 
	public String searchStreet() { 
		return "searchReport"; 
	}
	
	
	//1000단위 표시해주는 포맷 선언
	DecimalFormat formatter = new DecimalFormat("###,###");
	
	@RequestMapping(value="/report", 
		      produces = "application/json; charset=utf-8")
	public ModelAndView report(salesVO vo, areaVO avo, serviceVO svo) {
		//System.out.println(vo);
		salesVO vo1 = areaViewDAO.sales_1Area1(vo);
		areaVO vo2 = areaViewDAO.area_Area1(avo);
		serviceVO vo3 = areaViewDAO.service_Area1(svo);
		ModelAndView mav = new ModelAndView();
		//System.out.println("vo1 머니머니: "+so1.getMonth_sal_money());
		//System.out.println("vo2 : "+vo2);
		//System.out.println("vo3 : "+vo3);
	
		//area table
		mav.addObject("area_coname", vo2.getArea_coname());
		//service table 
		mav.addObject("serv_coname", vo3.getServ_coname());
		if(vo1!=null) {
		JsonArray jsonArray = areaViewDAO.chart(vo);
		//10-60대 매출 그래프
		mav.addObject("chart", jsonArray);
		//sales_1 table
		mav.addObject("month_money", formatter.format(vo1.getMonth_sal_money()));
		mav.addObject("month_num", formatter.format(vo1.getMonth_sal_num()));
		mav.addObject("wday_money", formatter.format(vo1.getWday_sal_money()));
		mav.addObject("wkend_money", formatter.format(vo1.getWkend_sal_money()));
		mav.addObject("m_money", formatter.format(vo1.getM_sal_money()));
		mav.addObject("w_money", formatter.format(vo1.getW_sal_money()));
		//sales_2 table
		vo1 = areaViewDAO.sales_2Area1(vo);
		mav.addObject("money_10", formatter.format(vo1.getSal_money_10()));
		mav.addObject("money_20", formatter.format(vo1.getSal_money_20()));
		mav.addObject("money_30", formatter.format(vo1.getSal_money_30()));
		mav.addObject("money_40", formatter.format(vo1.getSal_money_40()));
		mav.addObject("money_50", formatter.format(vo1.getSal_money_50()));
		mav.addObject("money_60", formatter.format(vo1.getSal_money_60()));
		//sales_3 table
		vo1 = areaViewDAO.sales_3Area1(vo);
		mav.addObject("wday_num", formatter.format(vo1.getWday_sal_num()));
		mav.addObject("wkend_num", formatter.format(vo1.getWkend_sal_num()));
		mav.addObject("m_num", formatter.format(vo1.getM_sal_num()));
		mav.addObject("w_num", formatter.format(vo1.getW_sal_num()));
		mav.setViewName("report");
		//sales_4 table
		vo1 = areaViewDAO.sales_4Area1(vo);
		mav.addObject("num_10", formatter.format(vo1.getSal_num_10()));
		mav.addObject("num_20", formatter.format(vo1.getSal_num_20()));
		mav.addObject("num_30", formatter.format(vo1.getSal_num_30()));
		mav.addObject("num_40", formatter.format(vo1.getSal_num_40()));
		mav.addObject("num_50", formatter.format(vo1.getSal_num_50()));
		mav.addObject("num_60", formatter.format(vo1.getSal_num_60()));
		}
		return mav;
	}
	
	
}

```

