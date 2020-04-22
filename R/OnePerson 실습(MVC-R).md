# OnePerson 실습

#### OnePersonController.java

```java
package edu.spring.redu;
import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import rtest.OnePersonService;
@Controller
public class OnePersonController {

	@Autowired
	OnePersonService one;

	@RequestMapping("/map7")
	public ModelAndView one(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		String real_path = req.getSession().getServletContext().getRealPath("/");
		System.out.println(real_path);
	    real_path = real_path.replace("\\", "/");
	    System.out.println(real_path);		
		File f = new File(real_path+"/resources/oneperson");
		if(!f.exists()) f.mkdir();
		String gu = req.getParameter("gu");
//		System.out.println(gu);
		String result = one.returnLeaflet(real_path+"/resources/oneperson", gu);
		System.out.println(real_path+"/resources/oneperson");
		System.out.println("result one="+result);
		mav.addObject("leafletChartName", "http://localhost:8000/redu/resources/oneperson/"+result);
		mav.setViewName("oneView");
		return mav;
	}	
	

}

```





#### OnePersonService.java

```java
package rtest;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Service;
@Service
public class OnePersonService {	
	public String returnLeaflet(String path, String gu)  {

		RConnection r = null;
		String retStr = "";
		try {
			r = new RConnection(); 
			r.eval("setwd('c:/seho/Rstudy')");
			r.eval("library(dplyr)");
			r.eval("library(leaflet)");
			r.eval("library(Kormaps)");
			r.eval("library(htmlwidgets)");
			
			r.eval("Encoding(names(korpopmap2))<-'UTF-8'");
			r.eval("Encoding(korpopmap2@data$name)<-'UTF-8'");
			r.eval("Encoding(korpopmap2@data$행정구역별_읍면동)<-'UTF-8'");
			r.eval("Encoding(names(korpopmap3))");
			r.eval("Encoding(korpopmap3@data$name)");
			r.eval("Encoding(korpopmap3@data$행정구역별_읍면동)");
			
			r.eval("k2 <- korpopmap2");
			r.eval("k3 <- korpopmap3");
			r.assign("gu", gu);
			r.eval("gu <- iconv(gu, from='CP949',to='UTF-8')");
			r.eval("gucode <- k2@data[k2@data$name == gu, \"code.data\"]");
			r.eval("pattern <- paste0('^', gucode)");
			r.eval("grep<-grep(pattern, k3@data$code.data)");
			r.eval("k3@data<-k3@data[grep,]");
			r.eval("k3@polygons<-k3@polygons[grep]");
			
			r.eval("DONG<-read.csv('data/one.csv')");
			r.eval("k3@data$name<-gsub('·','',k3@data$name) ");
			r.eval("colnames(DONG)<-c('구별','name','일인가구')");
			
			r.eval("dong <- DONG %>%filter(구별=='"+gu+"')");
			
			r.eval("k3@data<-merge(k3@data,dong,by.x='name',sort=FALSE)");
			r.eval("mymap <- k3@data");
			r.eval("mypalette <- colorNumeric(palette ='Set3' , domain = k3@data$'일인가구')");
			r.eval("mypopup <- paste0(mymap$name,'<br> 1인가구: ',k3@data$'일인가구')");
			
			r.eval("map7 <- NULL");
			//여기서 오류
			r.eval("map7<-leaflet(k3) %>% addTiles() %>% setView(lat=37.52711, lng=126.987517, zoom=12) %>% addPolygons(stroke =FALSE,fillOpacity = .7, popup = mypopup,color = ~mypalette(k3@data$일인가구)) %>%" + 
					" addLegend( values = ~k3@data$일인가구, pal = mypalette ,title = '인구수',opacity = 1)");
			r.eval("map7");

			String fileName = path + "/oneMap.html";
//			System.out.println("fileName="+fileName);
			r.eval("saveWidget(map7,'"+ fileName+"', selfcontained=F)");
			retStr = r.eval("'oneMap.html'").asString();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			r.close(); 
		}
		return retStr;
	}	
	
}

```





#### oneView.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>1인가구 현황</title>
</head>
<body>
<h1>1인가구가 얼마나 있는지 한 눈에 봐볼까요?</h1> 
<hr>
<iframe src="${leafletChartName }" width="100%" height=500></iframe> 
</body>
</html> 

```

