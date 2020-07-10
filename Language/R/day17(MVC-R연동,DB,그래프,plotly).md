# day17(MVC-R연동,DB,그래프,plotly)

- 각 폴더 및 파일 추가

- WEB-INF -> spring -> appServlet -> servlet-context.xml 에 추가

  ```xml
  <context:component-scan base-package="rtest"/>
  ```

- Spring 

  - servlet-context.xml 에 추가 1. "Namespaces" 탭에서 task 체크해주기

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans:beans xmlns="http://www.springframework.org/schema/mvc"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:beans="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:task="http://www.springframework.org/schema/task"
	xsi:schemaLocation="http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd
		http://www.springframework.org/schema/task http://www.springframework.org/schema/task/spring-task-3.1.xsd
		http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

	<!-- DispatcherServlet Context: defines this servlet's request-processing infrastructure -->
	
	<!-- Enables the Spring MVC @Controller programming model -->
	<annotation-driven />
	<task:annotation-driven />
	<!-- Handles HTTP GET requests for /resources/** by efficiently serving up static resources in the ${webappRoot}/resources directory -->
	<resources mapping="/resources/**" location="/resources/" />

	<!-- Resolves views selected for rendering by @Controllers to .jsp resources in the /WEB-INF/views directory -->
	<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<beans:property name="prefix" value="/WEB-INF/views/" />
		<beans:property name="suffix" value=".jsp" />
	</beans:bean>
	
	<context:component-scan base-package="edu.spring.redu" />
	
	<context:component-scan base-package="rtest"/>
	
	<context:component-scan base-package="service"/>

</beans:beans>

```



- 에러 구문
  - **127 에러가 뜨면 cmd 창에서 뭐가 잘못 되었는지 확인해야한다**
  - syntax 에러 : eval 등에서 구문에 오류가 있는 것이다





### RDBController.java 

### & ROracleDAO1.java 

### & rOracleView.jsp

```java
package edu.spring.redu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import rtest.ROracleDAO1;
import rtest.ROracleDAO2;
@Controller
public class RDBController {
	@Autowired
	ROracleDAO1 rdb1;
	@Autowired
	ROracleDAO2 rdb2;
	@RequestMapping("/visitorAll")
	public ModelAndView get1() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", rdb1.returnDBData(1));
		mav.setViewName("rOracleView");
		return mav;
	}	
	@RequestMapping("/visitorAllOrderByName")
	public ModelAndView get2() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", rdb1.returnDBData(2));
		mav.setViewName("rOracleView");
		return mav;
	}	
	@RequestMapping("/visitorByName")
	public ModelAndView get3(String name) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", rdb1.returnDBData("\\'"+name+"\\'"));
		mav.setViewName("rOracleView");
		return mav;
	}	
	@RequestMapping("/insertVisitor")
	public ModelAndView proc(String name, String content) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", rdb1.insertDBData("\\'"+name+"\\'", "\\'"+content+"\\'"));
		mav.setViewName("rOracleView");
		return mav;
	}	
	@RequestMapping("/createTable")
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", rdb2.createTable());
		mav.setViewName("rOracleView");
		return mav;
	}	
	@RequestMapping("/dropTable")
	public ModelAndView drop() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", rdb2.dropTable());
		mav.setViewName("rOracleView");
		return mav;
	}	
	@RequestMapping("/listTable")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", rdb2.listTable());
		mav.setViewName("rOracleView");
		return mav;
	}	
}

```



```java
package rtest;

import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Repository;

@Repository
public class ROracleDAO1 {
	public String returnDBData(int type) {
		String retStr = "";
		RConnection r = null;
		try {
			r = new RConnection();			
			r.eval("library(DBI)");
			r.eval("library(RJDBC)");
			r.eval("drv <- JDBC('oracle.jdbc.driver.OracleDriver','c:/unico/ojdbc6.jar')");
			r.eval("conn <- dbConnect(drv, 'jdbc:oracle:thin:@localhost:1521:xe','jdbctest','jdbctest')");
			if( type == 1)
				r.eval("query = 'SELECT * FROM VISITOR'");
			else if (type == 2)
				r.eval("query = 'SELECT * FROM VISITOR order by name'");
			RList list = r.eval("dbGetQuery(conn,query)").asList();
			int cols = list.size();
			int rows = list.at(0).length();
			String[][]s = new String[cols][];
			for(int i=0; i<cols; i++) {
				s[i] = list.at(i).asStrings();				
			}
			for(int j=0; j<rows; j++) {
				for(int i=0; i<cols; i++) {
					retStr += (s[i][j])+"";
				}
				retStr += "</br>";
			}			
		} catch(Exception e) {
			System.out.println(e);
			retStr = "오류 발생!!";
		} finally {
			r.close();
		}
		return retStr;
	}
	public String returnDBData(String name) {
		String retStr = "";
		RConnection r = null;
		System.out.println("---->"+name);
		try {
			r = new RConnection();			
			r.eval("library(DBI)");
			r.eval("library(RJDBC)");
			r.eval("drv <- JDBC('oracle.jdbc.driver.OracleDriver','c:/unico/ojdbc6.jar')");
			r.eval("conn <- dbConnect(drv, 'jdbc:oracle:thin:@localhost:1521:xe','jdbctest','jdbctest')");
			r.eval("query <- 'SELECT * FROM VISITOR WHERE NAME = "+name+"'"); 
			RList list = r.eval("dbGetQuery(conn,query)").asList();
			int cols = list.size();
			int rows = list.at(0).length();
			String[][]s = new String[cols][];
			for(int i=0; i<cols; i++) {
				s[i] = list.at(i).asStrings();				
			}
			for(int j=0; j<rows; j++) {
				for(int i=0; i<cols; i++) {
					retStr += (s[i][j])+"";
				}
				retStr += "</br>";
			}					
		} catch(Exception e) {
			System.out.println(e);
			retStr = "오류 발생!!";
		} finally {
			r.close();
		}
		return retStr;
	}
	public String insertDBData(String name, String content) {
		String retStr = "";
		RConnection r = null;
		System.out.println("---->"+name);
		System.out.println("---->"+content);
		try {
			r = new RConnection();			
			r.eval("library(DBI)");
			r.eval("library(RJDBC)");
			r.eval("drv <- JDBC('oracle.jdbc.driver.OracleDriver','c:/unico/ojdbc6.jar')");
			r.eval("conn <- dbConnect(drv, 'jdbc:oracle:thin:@localhost:1521:xe','jdbctest','jdbctest')");
			r.eval("insertSQL <- 'INSERT INTO visitor VALUES ("+name+",sysdate,"+content+")'");
			r.eval("dbSendUpdate (conn, insertSQL)");
			retStr = r.eval("'정상적으로 저장되었어요..'").asString();			
		} catch(Exception e) {
			System.out.println(e);
			retStr = "오류 발생!!";
		} finally {
			r.close();
		}
		return retStr;
	}
}

```



### ROracleDAO2.java

```java
package rtest;

import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Repository;

@Repository
public class ROracleDAO2 {
	public String createTable() {
		String retStr = "";
		RConnection r = null;
		try {
			r = new RConnection();
			r.eval("library(DBI)");
			r.eval("library(RJDBC)");
			r.eval("setwd('c:/seho/Rstudy')");//wd 지정해준다
			r.eval("drv <- JDBC('oracle.jdbc.driver.OracleDriver','C:/seho/ojdbc6.jar')");
			r.eval("conn <- dbConnect(drv, 'jdbc:oracle:thin:@localhost:1521:xe','jdbctest','jdbctest')");
			r.eval("student<-read.csv('data/example_studentlist.csv')");
			r.eval("dbWriteTable(conn, 'student', student)");
			retStr = r.eval("'테이블 생성과 데이터 저장 성공.......'").asString();	
		} catch (Exception e) {
			e.printStackTrace();
			try {
				retStr = r.eval("'테이블 생성과 데이터 저장 실패.......'").asString();
			} catch(Exception e1) {
				e.printStackTrace();
			}
		} finally {
			r.close();
		}
		return retStr;
	}

	public String dropTable() {//삭제 테이블
		String retStr = "";
		RConnection r = null;
		try {
			r = new RConnection();
			r.eval("library(DBI)");
			r.eval("library(RJDBC)");
			r.eval("drv <- JDBC('oracle.jdbc.driver.OracleDriver','C:/seho/ojdbc6.jar')");
			r.eval("conn <- dbConnect(drv, 'jdbc:oracle:thin:@localhost:1521:xe','jdbctest','jdbctest')");
			r.eval("dbRemoveTable(conn, 'STUDENT')");
			retStr = r.eval("'삭제 성공.......'").asString();	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			try {
				retStr = r.eval("'삭제 실패.......'").asString();
			} catch(Exception e1) {
				e.printStackTrace();
			}
		} finally {
			r.close();
		}
		return retStr;
	}

	public Object listTable() {
		String retStr = "";
		RConnection r = null;
		try {
			r = new RConnection();
			r.eval("library(DBI)");
			r.eval("library(RJDBC)");
			r.eval("drv <- JDBC('oracle.jdbc.driver.OracleDriver','C:/seho/ojdbc6.jar')");
			r.eval("conn <- dbConnect(drv, 'jdbc:oracle:thin:@localhost:1521:xe','jdbctest','jdbctest')");
			r.eval("query <- 'SELECT * FROM STUDENT'");
			RList list = r.eval("dbGetQuery(conn,query)").asList();
			int cols = list.size();
			int rows = list.at(0).length();
			String[][] s = new String[cols][];
			for (int i = 0; i < cols; i++) {
				s[i] = list.at(i).asStrings();
			}
			for (int j = 0; j < rows; j++) {
				for (int i = 0; i < cols; i++) {
					retStr += (s[i][j]) + "";
				}
				retStr += "</br>";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			try {
				retStr = r.eval("'테이블 내용 출력 실패.......'").asString();
			} catch(Exception e1) {
				e.printStackTrace();
			}
		} finally {
			r.close();
		}
		return retStr;
	}

}

```



---



### RGraphController1.java

```java
package edu.spring.redu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import rtest.RGraph;
@Controller
public class RGraphController1 {
	@Autowired
	RGraph rg;
		
	@RequestMapping("/graph1")
	public ModelAndView get1() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("img_content", rg.returnRImg1());
		mav.setViewName("rgraph");
		return mav;
	}	
	@RequestMapping("/graph2")
	public ModelAndView get2() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("img_content", rg.returnRImg2());
		mav.setViewName("rgraph");
		return mav;
	}		
	@RequestMapping("/wordcloud1")
	public ModelAndView get3() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("img_content", rg.returnRImg3());
		mav.setViewName("rgraph");
		return mav;
	}
}


```



### RGraphController2.java

```java
package edu.spring.redu;
import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import rtest.RGraph2;
import rtest.RLeaflet;
@Controller
public class RGraphController2 {
	@Autowired
	RGraph2 rg2;	
	@Autowired
	RLeaflet rl;

	@RequestMapping("/ggplot2")
	public ModelAndView get4() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("img_content", rg2.returnRImg3());
		mav.setViewName("rgraph2");
		return mav;
	}	

	@RequestMapping("/plotly")
	public ModelAndView get5() {
		ModelAndView mav = new ModelAndView();
		String result = rg2.returnPlotly();
		System.out.println(result);
		mav.addObject("dynamic_content", result);
		mav.setViewName("plotlyview");
		return mav;
	}
	
	@RequestMapping("/wordcloud2_1")
	public ModelAndView get6() {
		ModelAndView mav = new ModelAndView();
		String result = rg2.returnWC2_1();
		System.out.println(result);
		mav.addObject("dynamic_content", result);
		mav.setViewName("wc2view");
		return mav;
	}
	
	@RequestMapping("/wordcloud2_2")
	public ModelAndView get7(String name) {
		ModelAndView mav = new ModelAndView();
		String result = rg2.returnWC2_2(name);
		System.out.println(result);
		mav.addObject("dynamic_content", result);
		mav.setViewName("wc2view");
		return mav;
	}
	@RequestMapping("/leaflet1")
	public ModelAndView get8(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		String real_path = req.getSession().getServletContext().getRealPath("/");
		System.out.println(real_path);
	    real_path = real_path.replace("\\", "/");
	    System.out.println(real_path);		
		File f = new File(real_path+"/resources/leafletchart1");
		if(!f.exists()) f.mkdir();
		String type = req.getParameter("type");
		if(type == null)
			type = "종합";
		String result = rl.returnLeaflet1(real_path+"/resources/leafletchart1", type);
		System.out.println(real_path+"/resources/leafletchart1");
		System.out.println("result lf1="+result);
		mav.addObject("leafletChartName", "http://localhost:8000/redu/resources/leafletchart1/"+result);
		mav.setViewName("leafletview");
		return mav;
	}	
	@RequestMapping("/leaflet2")
	public ModelAndView get9(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		String real_path = req.getSession().getServletContext().getRealPath("/");//실제 패스 불러오는 메서드
		//C:/Users/student/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/redu/
		System.out.println(real_path);
	    real_path = real_path.replace("\\", "/");
	    System.out.println(real_path);		
		File f = new File(real_path+"/resources/leafletchart2");//
		if(!f.exists()) f.mkdir();//없으면 디렉토리 만들기
		String result = rl.returnLeaflet2(real_path+"/resources/leafletchart2");//result=index.html을 RLeaflet 으로부터 받아옴
		mav.addObject("leafletChartName", "http://localhost:8000/redu/resources/leafletchart2/"+result);
		//아무리 찾아봐도 없지만..톰캣이 관리하는 (위 리얼패스) 폴더에 들어가있다
		mav.setViewName("leafletview");
		return mav;
	}	
	@RequestMapping("/leaflet3")
	public ModelAndView get10(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		String real_path = req.getSession().getServletContext().getRealPath("/");
		System.out.println(real_path);
	    real_path = real_path.replace("\\", "/");
	    System.out.println(real_path);		
	   	File f = new File(real_path+"/resources/leafletchart3");
		if(!f.exists()) f.mkdir();
		String result = rl.returnLeaflet3(real_path+"/resources/leafletchart3");
		mav.addObject("leafletChartName", "http://localhost:8000/redu/resources/leafletchart3/"+result);
		mav.setViewName("leafletview");
		return mav;
	}

}


```



### RGraph.java

```java
package rtest;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Service;
@Service
public class RGraph {
	public byte[] returnRImg1() {
		RConnection r = null;
		byte retImg [] = null;
		try {
			r = new RConnection();		
			r.eval("setwd('c:/seho/Rstudy')");
			r.eval("png('test1.png')");
			r.eval("x <- 1:10");
			r.eval("barplot(x, names='SALES', col=rainbow(15))");
			r.eval("dev.off()");
			r.eval("r<-readBin('test1.png','raw',1024*1024)");
			r.eval("unlink('test1.png')"); //지우는 기능..남아있어도 상관은 없지만 없애기
			retImg = r.eval("r").asBytes();//return 값
		} catch(Exception e) {
			System.out.println(e);	
			e.printStackTrace();
		} finally {
			r.close();
		}
		return retImg; 
	}
	public byte[] returnRImg2() {
		RConnection r = null;
		byte retImg [] = null;
		try {
			r = new RConnection();		
			r.eval("library(DBI)");
			r.eval("setwd('c:/seho/Rstudy')");
			r.eval("result <- read.table('data/product_click.log')");
			r.eval("names(result) <- c('CLICK_TIME', 'PID')");
			r.eval("data_p <- table(result$PID)");
			r.eval("data_t <- table(substr(result$CLICK_TIME,9,10)) ");
			r.eval("png('test2.png')");
			r.eval("par(mfrow=c(1,2))");
			r.eval("pie(data_p, radius = 1, col=rainbow(10))");
			r.eval("barplot(data_t, col=terrain.colors(10))");
			r.eval("dev.off()");
			r.eval("r=readBin('test2.png','raw',1020*1020)");
			r.eval("unlink('test2.png')");
			retImg = r.eval("r").asBytes();
		} catch(Exception e) {
			System.out.println(e);	
			e.printStackTrace();
		} finally {
			r.close();
		}
		return retImg; 
	}
	
	public byte[] returnRImg3() {
		RConnection r = null;
		byte retImg [] = null;
		try {
			r = new RConnection();	
			r.eval("setwd('c:/seho/Rstudy')");
			r.eval("png('test3.png')");
			r.eval("library(KoNLP)");
			r.eval("library(XML)");
			r.eval("library(rvest)");	
			r.eval("library(wordcloud)");	
			r.eval("imsi <- read_html('http://hankookilbo.com')");
			r.eval("t <- htmlParse(imsi)");
			r.eval("content<- xpathSApply(t,\"//p[@class='title']\", xmlValue)");
			r.eval("content <- gsub('[[:punct:][:cntrl:]]', '', content)");
			r.eval("content <- trimws(content)");			
			r.eval("word <- sapply(content,extractNoun,USE.NAMES=F)");
			r.eval("cdata <- unlist(word)");
			r.eval("cdata <- Filter(function(x) {nchar(x) >= 2} ,cdata)");
			r.eval("wordcount <- table(cdata)");
			r.eval("wordcount <- head(sort(wordcount, decreasing=T),20)");
			r.eval("wordcloud(names(wordcount),freq=wordcount,scale=c(5,1),"+
					  "rot.per=0.35,min.freq=2,random.order=F,random.color=T,colors=rainbow(20))");
			r.eval("dev.off()");
			r.eval("r=readBin('test3.png','raw',1020*1020)");
			r.eval("unlink('test3.png')");
			retImg = r.eval("r").asBytes();			
		} catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			r.close();			
		}
		return retImg;
	}	
}

```



### RGraph2.java

```java
package rtest;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Service;

@Service
public class RGraph2 {
	
	public byte[] returnRImg3() {
		RConnection r = null;
		byte retImg[] = null;
		try {
			r = new RConnection();
			r.eval("setwd('c:/seho/Rstudy')");
			r.eval("library(ggplot2)");
			r.eval("product_click <-read.table('data/product_click.log')");
			r.eval("colnames(product_click)<- c('date','id')");
			r.eval("color_cnt <- unique(product_click$id)");

			r.eval("chart<-ggplot(data=product_click,aes(x=id))+geom_bar(fill=rainbow(length(color_cnt)))");
			r.eval("ggsave('clicklog.png', chart)");
			r.eval("r=readBin('clicklog.png','raw',1024*1024)");
			r.eval("unlink('clicklog.png')");
			retImg = r.eval("r").asBytes();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			r.close();
		}
		return retImg;
	}
	public String returnPlotly() {
		RConnection r = null;
		String retStr = "";
		try {		
			r = new RConnection();		
			r.eval("library(plotly)");		
			r.eval("library(ggplot2)");
			r.eval("library(htmltools)");		
			r.eval("p <- ggplot(data = mpg, aes(x = displ, y = hwy, col = drv)) + geom_point()");
			r.eval("my_plotly <- ggplotly(p)");
			r.eval("my_path <- renderTags(my_plotly)");
			retStr = r.eval("my_path$html").asString();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			r.close();
		}
		System.out.println(retStr);
		return retStr;
	}

	public String returnWC2_1() {
		RConnection r = null;
		String retStr = "";
		try {
			r = new RConnection();
			r.eval("library(wordcloud2)");		
			r.eval("library(htmltools)");			
			r.eval("my_cloud <- wordcloud2(demoFreq, size = 0.5, color = 'random-light', )");
			r.eval("my_path <- renderTags(my_cloud)");
			retStr = r.eval("my_path$html").asString();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			r.close();
		}
		System.out.println(retStr);
		return retStr;
	}
	public String returnWC2_2(String name) {
		RConnection r = null;
		String retStr = "";
		try {
			r = new RConnection();
			r.assign("filename", name);
			//name 으로 받아온 값을 R의 filename 에 전달
			//null 이 전달됨 -> nullpointexeption
			r.eval("source('c:/seho/Rstudy/wordcloud2.R')");		
			retStr = r.eval("my_path$html").asString();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			r.close();
		}
		System.out.println(retStr);
		return retStr;
	}
}

```



### RLeaflet.java

```java
package rtest;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Service;
@Service
public class RLeaflet {	
	public String returnLeaflet1(String path, String type)  {
		RConnection r = null;
		String retStr = "";
		try {
			r = new RConnection(); 
			r.eval("setwd('c:/seho/Rstudy')");
			r.eval("library(dplyr)");
			r.eval("library(leaflet)");
			r.eval("library(Kormaps)");
			r.eval("library(htmlwidgets)");
			r.eval("crime <- read.csv('data/2017crime.csv')");

			r.eval("Encoding(names(korpopmap2))<-'UTF-8'");
			r.eval("Encoding(korpopmap2@data$name)<-'UTF-8'");
			r.eval("Encoding(korpopmap2@data$행정구역별_읍면동)<-'UTF-8'");
			r.eval("korpopmap2@data <- korpopmap2@data[-26:-251,]");
			r.eval("korpopmap2@polygons<-korpopmap2@polygons[-26:-251]");
			r.eval("mymap <- korpopmap2@data");
			r.eval("palette1<-colorNumeric(palette = 'Oranges', domain = crime$살인_발생)");
			r.eval("popup1 <- paste0(mymap$name,'<br> 살인 : ',crime$살인_발생, '건')");
			r.eval("palette2<-colorNumeric(palette = 'Blues', domain = crime$폭력_발생)");
			r.eval("popup2 <- paste0(mymap$name,'<br> 폭력 : ',crime$폭력_발생, '건')");
			r.eval("palette3<-colorNumeric(palette = 'Reds', domain = crime$범죄_발생_총합)");
			r.eval("popup3 <- paste0(mymap$name,'<br> 총 범죄 건수 : ',crime$범죄_발생_총합, '건')");
			String baseMap = "map1<-leaflet(korpopmap2)%>%" + "addTiles()%>%"
					+ "setView(lat=37.559957 ,lng=126.975302 , zoom=11)%>%";
			if(type.equals("살인"))
				r.eval(baseMap
					+ "addPolygons(stroke=FALSE,smoothFactor=0.2,fillOpacity=.5, popup=popup1, color=~palette1(crime$살인_발생), group='살인')");
			else if(type.equals("폭력"))
				r.eval(baseMap
					+ "addPolygons(stroke=FALSE,smoothFactor=0.2,fillOpacity=.5, popup=popup2,color=~palette2(crime$폭력_발생),group='폭력')");
			else
				r.eval(baseMap
					+ "addPolygons(stroke=FALSE,smoothFactor=0.2,fillOpacity=.5, popup=popup3,color=~palette3(crime$범죄_발생_총합),group='총 범죄')");

			String fileName = path + "/index.html";
			System.out.println("path="+path);
			System.out.println("fileName="+fileName);
			
			r.eval("saveWidget(map1,'"+fileName+"',  selfcontained = F)");	        
			retStr = r.eval("'index.html'").asString();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			r.close(); 
		}
		return retStr;
	}	
	public String returnLeaflet2(String path)  {
		RConnection r = null;
		String retStr = "";
		try {
			r = new RConnection();
			r.eval("setwd('c:/seho/Rstudy')");
			r.eval("library(leaflet)");
			r.eval("library(dplyr)");
			r.eval("library(htmlwidgets)");
			r.eval("library(ggmap)");
			r.eval("register_google(key='AIzaSyD8k2DWC_7yFHCrH6LDR3RfITsmWMEqC8c')");
			r.eval("mk <- geocode(enc2utf8('서울특별시 강남구 역삼동 테헤란로 212'), source = 'google')");
			r.eval("lan <- mk$lon");
			r.eval("lat <- mk$lat");
			r.eval("msg <- '<strong><a href=http://www.multicampus.co.kr style=text-decoration:none >멀티캠퍼스</a></strong><hr>우리가 공부하는 곳'");
			r.eval("map2 <- leaflet() %>% setView(lng = mk$lon, lat = mk$lat, zoom = 14) %>% addTiles() %>% addCircles(lng = lan, lat = lat, color='green', popup = msg )");
			String fileName = path + "/index.html";
			r.eval("saveWidget(map2,'"+fileName+"',  selfcontained = F)");	        
			retStr = r.eval("'index.html'").asString();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			r.close();
		}
		return retStr;
	}
	public String returnLeaflet3(String path)  {
		RConnection r = null;
		String retStr = "";
		try {
			r = new RConnection();
			r.eval("setwd('c:/seho/Rstudy')");
			r.eval("library(leaflet)");
			r.eval("library(dplyr)");
			r.eval("library(htmlwidgets)");
			r.eval("content <- paste(sep = '<br/>',\"<b><a href='https://www.seoul.go.kr/main/index.jsp' target='_blank'>서울시청</a></b>\",'아름다운 서울','박원순 시장님 화이팅')");
			//<a target='_blank'> 속성은 새로운 탭에 띄우는 결과
			r.eval("map3<-leaflet() %>% addTiles() %>%  addPopups(126.97797,  37.56654, content, options = popupOptions(closeButton = FALSE) )");
		    String fileName = path+"/index.html";
	       	r.eval("saveWidget(map3,'"+fileName+"',  selfcontained = F)");	
	       	//r.eval("saveWidget(map3,'"+fileName+"')");
			String s = r.eval("'index.html'").asString();
			return s;
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





## View

### 

```
##wcview.jsp
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
   	<title>Rserve</title>
    <script src="/redu/resources/wordcloud2/htmlwidgets-0.8/htmlwidgets.js?ver=2"></script>
	<link href="/redu/resources/wordcloud2/wordcloud2-0.0.1/wordcloud.css" rel="stylesheet" />
	<script src="/redu/resources/wordcloud2/wordcloud2-0.0.1/wordcloud2-all.js"></script>
	<script src="/redu/resources/wordcloud2/wordcloud2-0.0.1/hover.js"></script>
	<script src="/redu/resources/wordcloud2/wordcloud2-binding-0.2.0/wordcloud2.js?ver=2"></script>	
</head>
<body>
<h1>WordCloud2 시각화 결과 표현하기</h1> 
<hr>
${ dynamic_content }
</body>
</html> 



##rgraph.jsp
<%@page import="java.util.Base64"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>R 시각화 결과 표현하기</h1>
<hr>
<%
if(request.getAttribute("img_content") != null) {		
	byte content[] = (byte[])request.getAttribute("img_content");//바이너리 값을 받아옴
%>
	<img src="
	   data:image/png;base64, <%= Base64.getEncoder().encodeToString(content) %>"
	   width="500" height="400">
	   <!-- 이미지 url 줘야한다!! 바이너리를 스트링으로 바꿔줘야한다 --Base64.getEncoder()encodeToString(content)-->
<%
} 
%>	
</body>
</html>


##leafletview.jsp
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Rserve</title>
</head>
<body>
<h1>R-Leaflet 지도 시각화 결과 표현하기</h1> 
<hr>
<iframe src="${leafletChartName }" width="100%" height=500></iframe> 
</body>
</html> 

```





#### plotly 사용 방법

plotly 에 사용하는 각종 js, css 등 모드 redu 에 있어야하고

확인은 ggplotly 사용한 결과값의 페이지 소스보기로 어떤게 필요한지 확인 한 후 

폴더를 찾아가서 자료들을 /redu/resources/plotly 폴더에 전부 저장해서 사용했다)

추후에 plotlyview.jsp 에서 호출해서 사용했다(script 부분)

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
   	<title>Rserve</title>
    <script src="/redu/resources/plotly/htmlwidgets.js"></script>
	<script src="/redu/resources/plotly/plotly.js"></script>
	<script src="/redu/resources/plotly/typedarray.min.js"></script>
	<script src="/redu/resources/plotly/jquery.min.js"></script>
	<link href="/redu/resources/plotly/crosstalk.css" rel="stylesheet" />
	<script src="/redu/resources/plotly/crosstalk.min.js"></script>
	<link href="/redu/resources/plotly/plotly-htmlwidgets.css" rel="stylesheet" />
	<script src="/redu/resources/plotly/plotly-latest.min.js"></script>	
</head>
<body>
<h1>ggplotly 시각화 결과 표현하기</h1> 
<hr>
${ dynamic_content }
<!-- body 태그 안에 내용만 어떻게 가져왔는지? -->
</body>
</html> 

```

