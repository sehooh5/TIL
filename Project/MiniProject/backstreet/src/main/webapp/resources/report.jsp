<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
span{
	color : blue;
}
</style>
<meta charset="UTF-8">
<title>리포트 페이지</title>
</head>
<body>
<%
	String area_id = request.getParameter("area_id");
	String serv_id = request.getParameter("serv_id");
	//System.out.println(area_id);
	//System.out.println(serv_id);
	if(area_id == null&&serv_id==null){
		area_id = "";
		serv_id = "";
	}
%>
<pre>
<c:if test="${requestScope.month_money eq null}">
<span>${requestScope.area_coname}</span> 상권의 <span>${requestScope.serv_coname}</span> 업종 관련 자료가 없습니다.

<a href="#" class="button" onclick="location.href='/backstreet/map'">상권 다시 선택하기</a>

<a href="#" class="button" onclick="history.back()">업종 다시 선택하기</a>
</c:if>
<c:if test="${requestScope.month_money != null}">

고객님께서 선택하신   <span>${requestScope.area_coname}</span> 상권의 <span>${requestScope.serv_coname}</span> 업종 분석 정보에 대해 안내 드리겠습니다.


## sales_1 테이블

a 업종의 월매출 금액은 <span>${requestScope.month_money}</span> 이고 월 매출 수는 <span>${requestScope.month_num}</span> 건 입니다.

a 업종의 주중(월-금) 매출 금액은 <span>${requestScope.wday_money}</span> 이고. 주말(토,일) 매출 금액은 <span>${requestScope.wkend_money}</span> 입니다.

a 업종의 남성 매출 금액은 <span>${requestScope.m_money}</span> 이고, 여성 매출 금액은 <span>${requestScope.w_money}</span> 입니다.



## sales_2 테이블

a 업종의 연령별 매출 금액에 대한 정보 입니다.

10대 매출 금액 : <span>${requestScope.money_10}</span> 원

20대 매출 금액 : <span>${requestScope.money_20}</span> 원

30대 매출 금액: <span>${requestScope.money_30}</span> 원

40대 매출 금액: <span>${requestScope.money_40}</span> 원

50대 매출 금액: <span>${requestScope.money_50}</span> 원

60대 매출 금액: <span>${requestScope.money_60}</span> 원



## sales_3 테이블

a 업종의 주중(월-금) 매출 수는 <span>${requestScope.wday_num}</span> 건 이고, 주말(토-일) 매출 수는 <span>${requestScope.wkend_num}</span> 건 입니다.

a 업종의 남성 매출 수는 <span>${requestScope.m_num}</span> 건 이고, 여성 매출 수는 <span>${requestScope.w_num}</span> 건 입니다.



## sales_4 테이블

10대 매출 수 : <span>${requestScope.num_10}</span> 건

20대 매출 수 : <span>${requestScope.num_20}</span> 건

30대 매출 수: <span>${requestScope.num_30}</span> 건

40대 매출 수: <span>${requestScope.num_40}</span> 건

50대 매출 수: <span>${requestScope.num_50}</span> 건

60대 매출 수: <span>${requestScope.num_60}</span> 건




<a href="#" class="button" onclick="location.href='/backstreet/changeConsulting1?area_id=<%= area_id %>&serv_id=<%= serv_id %>'">더 자세한 컨설팅이 필요하시면 눌러주세요</a>
</c:if>

</pre>
</body>
</html>