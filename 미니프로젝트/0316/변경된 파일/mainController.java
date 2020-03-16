package my.spring.backstreet;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.areaViewDAO;
import dao.consultingViewDAO;
import service.getConsultingSearchResult;
import service.getLoginAccessToken;
import service.getLogoutUserId;
import service.getTrandsSearchResult;
import vo.areaVO;
import vo.salesVO;
import vo.serviceVO;


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
	
	@RequestMapping(value="/main")
	public String mainView() {
		return "main";
	}
	@RequestMapping(value="/loginView")
	public String loginView() {
		return "login";
	}
	@RequestMapping(value="/login")
	public ModelAndView login(@RequestParam("code") String code) {
		System.out.println("Controller - login 함수");
		System.out.println("code : " + code ); // 사용자 인증을 통한 code 획득 -> Access Token, Refresh Token 획득 가능 -> API 사용
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
		System.out.println("Controller - logout 함수"); // 사용자 인증을 통한 code 획득 -> Access Token, Refresh Token 획득 가능 -> API 사용

		// 로그아웃 서비스 클래스 실행
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
	
	//consultingView Controller......
	@RequestMapping(value="/changeConsulting1")
	public ModelAndView changeConsulting1() {
		String changeCodeName = consultingViewDAO.changeConsulting1(1001495);	
		String areaCodeName = consultingViewDAO.areaConsulting1(1001495); // 	
		int change_id = consultingViewDAO.areaConsulting2(1001495);
		String serviceCodeName = consultingViewDAO.serviceConsulting1("CS300005");
		int oper_month = consultingViewDAO.changeConsulting2(1001495);
		int close_month = consultingViewDAO.changeConsulting3(1001495);
		float oper_month_avg = consultingViewDAO.changeViewConsulting1();	
		float close_month_avg = consultingViewDAO.changeViewConsulting2();	
		int all_job_num = consultingViewDAO.jobConsulting1(1001495);	
		int all_job_num_avg = consultingViewDAO.jobViewConsulting1();
		
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
		mav.setViewName("consultingView");
		return mav;
	}
	
	
	/////세호쓰
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
	
	@RequestMapping(value="/report")
	public ModelAndView report(salesVO vo, areaVO avo, serviceVO svo) {
		//System.out.println(vo);
		salesVO vo1 = areaViewDAO.sales_1Area1(vo);
		areaVO vo2 = areaViewDAO.area_Area1(avo);
		serviceVO vo3 = areaViewDAO.service_Area1(svo);
		ModelAndView mav = new ModelAndView();
		//System.out.println("vo1 머니머니: "+vo1.getMonth_sal_money());
		//System.out.println("vo2 : "+vo2);
		//System.out.println("vo3 : "+vo3);
		
		//area table
		mav.addObject("area_coname", vo2.getArea_coname());
		//service table 
		mav.addObject("serv_coname", vo3.getServ_coname());
		if(vo1!=null) {
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
