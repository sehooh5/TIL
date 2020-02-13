# Spring + Mybatis 실습

[TOC]



## Spring --> Mybatis 사용하여 Controller 변환

### MeetingController.java

```java
package my.spring.springedu;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.MeetingDAO;
import vo.MeetingVO;

@Controller
public class MeetingController {
	//이미 여기서 dao 객체 만들어서 넣어주니까 아래에서 만들면 null 오류 난다
	@Autowired
	MeetingDAO dao;
	@RequestMapping("/meetingForm")
	public String form() {
		return "meetingView2";
	}
//아무것도 안오고...전부다 오는경우,ㅡㅡ 키워드가 있는경우
	
	@RequestMapping(value="/meeting2", method=RequestMethod.GET)
	public ModelAndView handleget(String keyword, String action,MeetingVO vo){
		ModelAndView mav = new ModelAndView();
		if(keyword == null) {
			if(action != null) {
				boolean result = dao.delete(vo.getId());
				if (result) {
					mav.addObject("msg", "글이 성공적으로 삭제되었습니다.");
				} else {
					mav.addObject("msg", "글이 삭제되지 않았습니다.");
				}
			} 
			mav.addObject("list", dao.listAll());
		} else {
			List<MeetingVO> list = dao.search(keyword);
			if (list != null && list.size() == 0) {
				mav.addObject("msg", keyword + "(이)가 포함된 글이 없습니다.");
			} else {
				mav.addObject("list", dao.search(keyword));
			}
		}
		mav.setViewName("meetingView2");
		return mav;
	}
	
	@RequestMapping(value="/meeting2", method=RequestMethod.POST)
	public ModelAndView doPost(String action, String name, String title, String meetingDate) throws ServletException, IOException {
		/* request.setCharacterEncoding("UTF-8"); */
		ModelAndView mav = new ModelAndView();
		MeetingVO vo = new MeetingVO();
		vo.setName(name);
		vo.setTitle(title);
		vo.setMeetingDate(meetingDate);
		if(action.equals("insert")) {
			boolean result = dao.insert(vo);
			if (result) {			
				mav.addObject("msg", name + "님의 글이 성공적으로 입력되었습니다.");			
			} else {
				mav.addObject("msg", name + "님의 글이 입력되지 않았습니다.");
			}
		} else {
			vo.setId(Integer.parseInt(action));
			boolean result = dao.update(vo);
			if (result) {			
				mav.addObject("msg", name + "님의 글이 성공적으로 수정되었습니다.");			
			} else {
				mav.addObject("msg", name + "님의 글이 수정되지 않았습니다.");
			}
		}
		mav.setViewName("meetingView2");
		return mav;
	}
}

```

