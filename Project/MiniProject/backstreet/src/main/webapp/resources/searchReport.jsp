<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
a.button {  
      text-decoration: none;
      font-size:2rem;
      color:white;
      padding:10px 20px 10px 20px;
      margin:20px;
      display:inline-block;
      border-radius: 10px;
      transition:all 0.1s;
      text-shadow: 0px -2px rgba(0, 0, 0, 0.44);
      transform: translateY(3px);
      background-color: #1f75d9;
      border-bottom:5px solid #165195;
	  border-bottom:2px solid #165195;
    }
  
</style>
<meta charset="UTF-8">
<title>리포트 페이지</title>
</head>
<body>
<%
	String areaId = request.getParameter("area_id");
	String area = request.getParameter("area_coname");
	//System.out.println(areaId);
	if(areaId == null){
		areaId = "";
	}
%>
		<h2><%= area %> 상권에서 검색하고 싶은 업종을 선택하세요</h2>
		<a href="#" class="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS100001'">한식음식점</a>
		<a href="#" class="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS100008'">제과점</a>
		<br>
		<a href="#" class="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS100009'">커피 및 음료</a>
		<a href="#" class="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS100010'">호프</a>
		<br>
		<a href="#" class="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS200001'">학원</a>
		<a href="#" class="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS200009'">숙박업</a>
		<br>
		<a href="#" class="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS200011'">PC방</a>
		<a href="#" class="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS200016'">미용실</a>
		<br>
		<a href="#" class="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS300002'">편의점</a>
		<a href="#" class="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS300007'">의류점</a>
		<br>
		<a href="#" class="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS300008'">패션용품</a>
		<a href="#" class="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS300011'">화장품</a>

</body>
</html>