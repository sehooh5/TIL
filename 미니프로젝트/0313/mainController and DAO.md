# mainController and DAO

### mainController.java

```java
package my.spring.backstreet;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.consultingViewDAO;
import service.getConsultingSearchResult;
import service.getLoginAccessToken;
import service.getLogoutUserId;
import service.getTrandsSearchResult;
import vo.salesVO;


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
	@RequestMapping(value="/list")
	public ModelAndView list() {
		String vo = consultingViewDAO.listAll();
		ModelAndView mav = new ModelAndView();
		System.out.println(vo);
		mav.addObject("list", vo);
		mav.setViewName("consultingView");
		return mav;
	}
	
	
	//내가 수정한 부분 - 아직 작동 안됨
	@RequestMapping(value="/map")
	public String mapStreet() {
		return "map";
	}
	
	
	@RequestMapping(value="/searchreport") 
	public String searchStreet() { 
		return "searchReport"; 
	}
	
	
	@RequestMapping(value="/report")
	public ModelAndView report(salesVO vo) {
		//System.out.println(vo);
		long money = consultingViewDAO.sales_1Area1(vo);	
		ModelAndView mav = new ModelAndView();
		DecimalFormat formatter = new DecimalFormat("###,###");
		//System.out.println(formatter.format(money));
		mav.addObject("list", formatter.format(money));
		mav.setViewName("report");
		return mav;
	}

}

```





### DAO

```java
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
		//System.out.println("DAO 에서 추출된 값"+money);
		return money;
	}
	
	

}

```

