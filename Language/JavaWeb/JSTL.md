# JSTL

[TOC]



#### JSP ----> Servlet

- Custom Tag 
  - 필요한 기능의 **태그를 직접 만들 수 있는 기능**
  - Apache Open Group : JSTL (만들어진 태그들의 표준)
  - core library, xml, sql, fmt, fn library
- Action Tag : JSP 가 제공하는 태그로 기능, 구현 방법이 정해져 있는 태그들

- ASP,PHP -> CGI

- JSP ---> 웹페이지 ----> HTML + JSP 태그 + JAVA

​			규격화된 문서 --> XML + JSP태그 + JAVA

​											JSON + JSP태그 + JAVA

```
ex)
<h1> 
<form> 
<%= %>  : out.print 기능이 자동으로 
<% %>   : 그대로 들어감
<jsp:forward   /> <jsp:useBean   /> : 여기서 jsp 를 prefix라 한다
```



### core Library

- 변수 선언, 조건문, 반복문 등 간단한 프로그램 로직 구현을 대신할 수 있는 기능의 커스텀 태그들이 지원되는 라이브러리이다
- core 라이브러리를 사용하려면 다음과 같이 taglib 지시자 태그를 정의해야한다

```
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```



#### <c:set>

- **[]대괄호 안에 있으면 생략 가능하다..default = pageScope**

```jsp
<c:set var="varName" value="varValue" [scope="영역"]></c:set> <c:set var="varName" [scope="영역"]>varValue</c:set>
[]대괄호 안에 있으면 생략 가능하다..default = pageScope

<c:set target="대상" property="프로퍼티이름" value="값"/> <c:set target="대상" property="프로퍼티이름">값</c:set>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 테스트</title>
</head>
<body>
<h2>&lt;c:set&gt;, &lt;c:remove&gt; 그리고 &lt;c:out&gt; 태그</h2>
<hr>
<c:set var="num1" value="20" /> 
<c:set var="num2">
10.5
</c:set>
<c:set var="today" value="<%= new java.util.Date() %>" />
변수 num1 = ${ num1 } <br>
변수 num1 = <c:out value="${num1}"  /> <br>
변수 num2 = <c:out value="${num2}"  /> <br>
변수 num3 = <c:out value="${num3}"  default="기본 값"/> <br>
num1 + num2 = ${num1 + num2} <br>
오늘은 ${today} 입니다.
<hr>
<c:remove var="num1" />
삭제한 후의 num1 = ${num1} <br>
삭제한 후의 num1 + num2 = ${num1 + num2}<br>
삭제한 후의 num1 = <c:out value="${num1}"  default="기본 값"/> 
<hr>
<c:set var="map" value="<%= new java.util.HashMap<String,String>() %>" />
<c:set target="${map}"  property="st1" value="듀크" />
<c:set target="${map}"  property="st2" value="둘리" />
변수 map에 저장된 st1 값: ${map.st1}<br>
변수 map에 저장된 st2 값: ${map.st2}<br>
<hr>
EL 사용 : <c:out value="${param.buyer}"/><br>
스크립트 태그 사용 : <c:out value='<%= request.getParameter("buyer")%>' />
</body>
</html>


///////////스코프 활용//////////////////////
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 테스트</title>
</head>
<body>
<h2>&lt;c:set&gt; 태그 예제</h2>
<hr>
<c:set var="color"  value="red-page"  scope="page"/>
<c:set var="color"  value="blue-request" scope="request"/>
<c:set var="color" value="green-session"  scope="session"/>
<c:set var="color"  value="yellow-application"  scope="application"/>

color = ${color} <br>

pageScope.color = ${pageScope.color}<br>
requestScope.color = ${requestScope.color}<br>
sessionScope.color = ${sessionScope.color}<br>
applicationScope.color = ${applicationScope.color}<br>

</body>
</html>
```



#### <c:remove>

```jsp
<c:remove var="varName" [scope="영역"]/>

<c:set var="name" value="홍길동" scope="request"/> <c:remove var="name"/>
```



#### <c:out>

```jsp
<%--방법 1 --%> 
<c:out value="value" [escapeXml="(true | false)"] [default="defaltValue"]/>

<%--방법 2 --%>
<c:out value="value" [escapeXml="(true | false)"] > defaltValue </c:out>
```



---



#### <c:if>

- else 는 없고 if 태그를 두번 주면 된다
- test 안에 조건에 해당하는 구문을 주면 된다
- content 가 반드시 있어야 한다 = double side tag 

```jsp
<c:if test="조건"> ... </c:if>

<c:if test="<%= some condition %>" var="testResult"> ... </c:if>
```



#### <c:choose,when,otherwise>

- when = if 
- otherwise = else

```jsp
<c:choose> 
   	<c:when test="${member.level == 'trial'}"> ... </c:when> 		<c:when test="${member.level == 'regular'}"> ... </c:when> 		<c:otherwise> ... </c:otherwise> 
</c:choose>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 테스트</title>
</head>
<body>
<h2>&lt;c:if&gt; 와 &lt;c:choose&gt; 태그 예제</h2>
<hr>
<c:if test="true">
무조건 수행<br>
</c:if>

<c:if test="${ empty param.name and empty param.age }" >
	<h3>name=xxx&amp;age=xxx 의 형식으로 Query 문자열을 전달하세요..</h3>
</c:if>

<c:if test="${ !empty param.name and !empty param.age }" >
	<c:if test="${param.name == 'duke'}">
		name 파라미터의 값이 ${param.name} 입니다.<br> 
	</c:if>

	<c:if  test="${ param.age >= 30 }"> 
		당신의 나이는 30세 이상입니다.
	</c:if>
	<c:if  test="${ param.age < 30 }"> 
		당신의 나이는 30세 미만입니다.
	</c:if>
	<hr>
	<ul>
	<c:choose> 
  		<c:when test="${param.name == 'duke'}" > 
    		<li> 당신의 이름은 ${param.name} 입니다.
  		</c:when> 
  		<c:when test="${param.age >= 30}" > 
    		<li> 당신은 30세 이상입니다.
  		</c:when> 
  		<c:otherwise> 
    		<li> 당신은 'duke'도 아니고 30세 이상도 아닙니다.
  		</c:otherwise> 
	</c:choose> 
	</ul>
</c:if>
</body>
</html>
```



---



#### <c:forEach>

- for(변수선언 : 컬렉션 객체) 랑 같은 기능
- 여기서는 var를 사용해 변수를 선언한다
- varStatus 는 변수를 담는 객체
- 아래 프로퍼티로 필요한 자료들을 추출할 수 있다
  - ex) index 를 받아오면 몇번째 값인지 알 수 있다

```jsp
<c:forEach var="i" begin="1" end="10" step="2"> ${ i } 사용 </c:forEach>

<c:forEach var="item" items="<%= someItemList %>" varStatus="status"> ${status.index + 1 } 번째 항목 ${item.name } </c:forEach>

```

![image-20200201102725331](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200201102725331.png)



#### <c:forTokens>

- items : 문자열 주고
- delims 는 문자열을 나누는 기준
- 나누어서 var 값에 각각 차례대로 대입한다

```jsp
<c:forTokens var="token" items="문자열" delims="구분자"> ${ token }의 사용 </c:forTokens>

<c:forTokens var="color" items="red, green, blue" delims=","> ${ color }
</c:forTokens>

<c:forTokens var="token" items="빨강색,주황색.노란색.초록색,파랑색,남색.보라색" delims=",."> ${ token } </c:forTokens>
```





```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 테스트</title>
</head>
<body>
<h2>&lt;c:forEach&gt;와 &lt;c:forTokens&gt;태그 예제</h2>
<hr>
<h3>[ 맵 ]</h3>
<c:set var="map" value="<%= new java.util.HashMap<String,String>() %>" />
<c:set target="${map}"  property="st1" value="듀크" />
<c:set target="${map}"  property="st2" value="둘리" />
<c:set target="${map}"  property="st3" value="또치" />
<c:forEach var="data"  items="${map}">
   [ ${data.key} = ${data.value} ]
</c:forEach>

<h3>[ 배열 ]</h3>
<c:set var="intArray" value="<%= new int[] {1,2,3,4,5} %>" />
<c:forEach var="i" items="${intArray}" begin="2" end="4">
    [${i}]
</c:forEach>

<h3>[ 값을 변화시키면서 반복처리 ]</h3>
<c:set var="sum" value="0" />
<c:forEach var="i" begin="1" end="100" step="2">
	<c:set var="sum" value="${sum + i}" />
</c:forEach>
<h4>결과 = ${sum}</h4>

<h4>구구단: 4단</h4>
<c:forEach var="i"  begin="1"  end="9">
   4 * ${i} = ${4 * i}&nbsp;&nbsp;
</c:forEach>

<hr>
콤마와 점을 구분자로 사용:<br>
<c:forTokens var="token" 
             items="빨강색,주황색.노란색.초록색,파랑색,남색.보라색"
             delims=",.">
<button>${token} </button>
</c:forTokens>
</body>
</html>
```



---



#### <c:import>

- url 만 필수 나머지는 선택적
- 

```jsp
<c:import url="URL" [var="변수명"] [scope="영역"] [charEncoding="문자셋"]> <c:param name="이름" value="값" /> </c:import>

<c:import var="weather" url="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1168064000"> </c:import>


<c:param>이라는 서브 태그를 사용하는 예
<c:import var="weather" url="http://www.kma.go.kr/wid/queryDFSRSS.jsp"> <c:param name="zone" value="1168064000"/> </c:import>
```



#### <c:url>

```jsp
<c:url value="URL" [var="varName"] [scope="영역"]>
<c:param name="이름" value="값" /> </c:url>
```



#### <c:redirect>

```jsp
<c:redirect url="URL" [context="콘텍스트 경로"]> <c:param name="이름" value="값" /> </c:redirect>
```



#### <c:catch>

- content 부분이 try 구문
- catch 에 걸리면 if 수행

```jsp
<c:catch var="exName"> 예외가 발생할 수 있는 코드 </c:catch>


<c:catch var="ex"> name 파라미터의 값 = <%= request.getParameter("name") %><br> <% if(request.getParameter("name").equals("test")){ %> ${param.name }은 test 이다. <% } %> </c:catch> <c:if test="${ ! empty ex } "> 예외가 발생하였습니다 : <br> ${ ex } </c:if>
```



---

### XML Library

- 태그명은 core library 랑 비슷하지만 역할이 다르다
- xml에 특화되어 있다

- 웹 크롤링 할때 사용할듯



#### <x:out>

```jsp
<x:out select=”XPath표현식” escapeXml=”true/false” >
    
ex)   /wikimedia/projects/project/editions/edition[@language="English"]/text()

    
= //edition[@language="English"]/text()
= /wikimedia//edition[@language="English"]/text()

    
**만약! 이렇게 컨텐트가 구성되어있으면 
    CS 선택자로 "ㅠㅠㅠ" 나 "ㅋㅋㅋ" 를 못불러온다(태그위주여서 안됨)
<abc>
    ㅋㅋㅋ
    <def>MMM</def>
    ㅠㅠㅠ
</abc>

****이럴때 XPath 를 사용하면 노드들을 이용해서 찾을 수 있다
```

- @  붙이면 속성 선택
- XPath 예제 : student.xml

![image-20200201113719566](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200201113719566.png)



#### <x:set>

```jsp
<x:set var=”변수명” select=” XPath표현식” scope=”범위” >
```



#### <x:if>

```jsp
<x:if var=”변수명” select=”XPath 표현식”/>
```



#### <x:choose,when,otherwise>

```jsp
<x:choose> 
    <x:when select=”XPath 표현식” >참일 때 수행 코드</x:when> 			<x:otherwise>거짓일 때 수행 코드</x:otherwise>
</x:choose>
```



#### <x:forEach>

```jsp
<x:forEach var=”변수명” select=”XPath 표현식” begin=”시작 인텍스” end=”끝 인덱스” step=”증감식” >
```



#### <x:parse>

```jsp
<x:parse var=”변수명” varDom=”변수명” scope=”범위” scopeDom=”범위” >
```

- var : XML 문서를 저장하고 있는 EL 변수를 설정
- varDom : 파싱하여 생성된 DOM 객체를 저장할 변수명을 설정
- scope : var 속성에 지정된 EL 변수의 스코프를 설정
- scopeDom : varDom 속성에 지정된 EL 변수의 스코프를 설정



#### 예제 - jstlexam8,9,10.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 테스트</title>
</head>
<body>
<h2>xml 라이브러리  태그 예제</h2>
<hr>
<c:import var="xmldata" url="http://localhost:8000/sedu/jspexam/student.xml" charEncoding="UTF-8"/>
<x:parse xml="${xmldata}" varDom="xdata"/> 

<c:catch var="ex">
/////앞에 $ 를 주어서 xdata 파싱하여 생성된 DOM객체의 변수명 알림
<x:out select="$xdata//학생[1]/이름" /> <x:out select="$xdata//학생[1]/@번호" /><br> 
<x:out select="$xdata//학생[2]/이름" /> <x:out select="$xdata//학생[2]/@번호" /><br>
<x:out select="$xdata//학생[3]/이름" /> <x:out select="$xdata//학생[3]/@번호" /><br>
<x:out select="$xdata//학생[4]/이름" /> <x:out select="$xdata//학생[4]/@번호" /><br>
<hr>
<x:forEach select="$xdata//학생">
    /////학생을 기준으로 해서 이름 & 번호라는 속성값을 추출한다
  <x:out select="이름" />
  <x:out select="@번호" /><br>
</x:forEach>
<hr>
    ///성별 '남' 속성을 갖는 이름 태그를 result 에 담아라
    ///text()는 뭐임?
<x:forEach select="$xdata//이름[@성별='남']" var="result">
남학생 : <x:out select="text()" /> <br>
</x:forEach>
<hr>

<x:if select="$xdata//학생[@번호='st0004']" >
번호가 st0004 번인 학생 이름 : <x:out select="$xdata//학생[@번호='st0004']/이름" /><br>
</x:if>
<x:choose>
<x:when select="$xdata//학생[@번호='st0005']" >
번호가 st0005 번인 학생 이름 : <x:out select="$xdata//학생[@번호='st0005]/이름" /><br>
</x:when>
<x:otherwise>
<h3>번호가 st0005인 학생 데이타가 존재하지 않습니다!!</h3>
</x:otherwise>
</x:choose> 
</c:catch>
<c:if test="${ex != null}">
<h3>${ex}</h3>
</c:if>
</body>
</html>
```



```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 테스트</title>
<style>
table, td, th {
	border : 1px solid black;
}
</style>
</head>
<body>
<h2>기상청 날씨정보(XML)를 읽어오는 예제!</h2>
<hr>
<c:catch var="err">   
  <c:import var="weather" 
 url="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1168064000">
  </c:import>
  <%-- <c:import var="weather" 
 url="http://www.kma.go.kr/wid/queryDFSRSS.jsp">
    <c:param name="zone" value="1168064000"/>
  </c:import>  --%>
  <!-- xml 파싱하기 -->
  <x:parse varDom="wrss" xml="${weather}"></x:parse>
  <x:out select="$wrss/rss/channel/title"></x:out><br/>   
  <table>
     <tr>
         <th colspan="2" >현재날씨</th>
     </tr>   
     <tr>
         <td>기준시간</td>
         <td>
               <x:out select="$wrss/rss/channel/pubDate"/> 
         </td>
     </tr>   
     <tr>
         <td>기온 </td>
         <td>
               <x:out select="$wrss/rss/channel/item/description/body/data/temp"/> 
        </td>
     </tr>   
     <tr>
         <td>습도 </td>
         <td> 
              <x:out select="$wrss/rss/channel/item/description/body/data/reh"/>% 
         </td>
     </tr>   
     <tr>
         <td>강수확률 </td>
         <td> 
              <x:out select="$wrss/rss/channel/item/description/body/data/pop"/>% 
         </td>
     </tr>
  </table>
</c:catch>
<c:if test="${!empty err}">
   <h2>오류 발생!!</h2>
   ${err}
</c:if> 
</body>
</html>
```





```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:choose>
    ////유일하게 jsp, EL 둘 모두의 내장객체이다 (method = getMethod)
	<c:when test="${ pageContext.request.method == 'GET' }">
		<h2>점검하려는 버스의 번호를 입력하세요</h2>
		<hr>
		<form method="POST" action="/sedu/jspexam/jstlexam10.jsp">
			버스번호 : <input type="number" name="num" min="0" required>
			<input type="submit" value="알아보기">
		</form>	
	</c:when>
	<c:otherwise>
	     <h2>${param.num}번 버스의 정보</h2>
	     <hr>
	     <c:catch var="ex">
	     	<c:import var="xmldata" url="http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList?serviceKey=%2BjzsSyNtwmcqxUsGnflvs3rW2oceFvhHR8AFkM3ao%2Fw50hwHXgGyPVutXw04uAXvrkoWgkoScvvhlH7jgD4%2FRQ%3D%3D&strSrch=${param.num }" />
	        <x:parse xml="${xmldata }" varDom="xdata" />
	        <x:if select="$xdata//headerCd = 0">
	        	<c:set var="busnum" value="${ param.num }"/>
	        	<x:forEach select="$xdata//itemList">
	        		<x:if select="busRouteNm = $busnum">	        		  
	        			버스번호 : <x:out select="busRouteNm"/><br>
     					기점 : <x:out select="stStationNm"/><br>
     					종점 : <x:out select="edStationNm"/><br>
     					라우트아이디 : <x:out select="busRouteId"/><br>
     					회사정보 : <x:out select="corpNm"/><br>
     					배차간격 : <x:out select="term"/><br>
     					<c:set var="outFlag" value="true"/>
	        		</x:if>	        		
	        	</x:forEach>
	        	<c:if test="${ empty outFlag }">
	        		<h3>${ param.num }번 버스의 정보는 존재하지 않아요....</h3>
	        	</c:if>	        
	        </x:if>
	        <x:if select="$xdata//headerCd != 0">
	        	<h3>${ param.num }번 버스의 정보는 존재하지 않아요...</h3>	        
	        </x:if>	        
	     </c:catch>	
	     <c:if test="${ !empty ex }">
	     	<h3>오류 발생 : ${ex}</h3>	     
	     </c:if>
	     <hr>
		 <a href="/sedu/jspexam/jstlexam10.jsp">입력 화면으로</a>
	</c:otherwise>
</c:choose>
</body>
</html>




```







---

### latlng 실습

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!--fmt는 Post 방식일때 한글로 인코딩하기위해 import-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소 위도경도(JSTL)</title>

</head>
<body>
<!-- POST 방식으로 실행하니까 한글로 인코딩 해줘야함 -->
<fmt:requestEncoding value="UTF-8"/>

<c:catch>
    <!-- import 할때 c:param으로 쿼리문 나눠주기 -->
	<c:import var="log" url="https://maps.googleapis.com/maps/api/geocode/xml">
        <!-- 우리가 입력하는 값 가져오기위한 ${param.address } -->
	<c:param name="address" value="Query${param.address }"/>
	<c:param name="language" value="ko"/>
	<c:param name="key" value="AIzaSyD8k2DWC_7yFHCrH6LDR3RfITsmWMEqC8c"/>
	</c:import>
	<x:parse xml="${log}" varDom="xdata"/>
	<x:choose>
	<x:when select="$xdata/GeocodeResponse/status='OK'" >
	<h2>선택한 주소의 위도 경도</h2><hr>
	<ul>
	<li>주소 : <x:out select="$xdata//formatted_address"/></li>
	<li>경도 : <x:out select="$xdata//geometry/location/lat"/></li>
	<li>위도 : <x:out select="$xdata//geometry/location/lng"/></li>
	</ul>
	</x:when>
	<x:otherwise>
	<x:if select="$xdata//status='REQUEST_DENIED'">
	<h2>서비스 사용허가를 받지 못했습니다.</h2><hr>
	서버로 부터 전달된 오류 메시지 : 
	<span style="color : red">
	<x:out select="$xdata//error_message"/>
	</span>
	</x:if>
	</x:otherwise>
	</x:choose>
</c:catch>
<c:if test="${!empty err}">
   <h2>오류 발생!!</h2>
   ${err}
</c:if> 
</body>
</html>

```



```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.net.URLEncoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소 위도경도(JSTL)</title>

</head>
<body>
<!-- POST 방식으로 실행하니까 한글로 인코딩 해줘야함 -->
<fmt:requestEncoding value="UTF-8"/>

<c:catch>
	<c:import var="log" url="https://maps.googleapis.com/maps/api/geocode/xml?address=${URLEncoder.encode(param.address, 'UTF-8')}&language=ko&key=AIzaSyD8k2DWC_7yFHCrH6LDR3RfITsmWMEqC8c">
	</c:import>
	<x:parse xml="${log}" varDom="xdata"/>
	<x:choose>
	<x:when select="$xdata/GeocodeResponse/status='OK'" >
	<h2>선택한 주소의 위도 경도</h2><hr>
	<ul>
	<li>주소 : <x:out select="$xdata//formatted_address"/></li>
	<li>경도 : <x:out select="$xdata//geometry/location/lat"/></li>
	<li>위도 : <x:out select="$xdata//geometry/location/lng"/></li>
	</ul>
	</x:when>
	<x:otherwise>
	<x:if select="$xdata//status='REQUEST_DENIED'">
	<h2>서비스 사용허가를 받지 못했습니다.</h2><hr>
	서버로 부터 전달된 오류 메시지 : 
	<span style="color : red">
	<x:out select="$xdata//error_message"/>
	</span>
	</x:if>
	</x:otherwise>
	</x:choose>
</c:catch>
<c:if test="${!empty err}">
   <h2>오류 발생!!</h2>
   ${err}
</c:if> 
</body>
</html>




```

