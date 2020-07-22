<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
   content="width=device-width, initial-scale=1.0, user-scaclable=no">
<title>김세정의 골목상권</title>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/style.css" rel="stylesheet">
<link href="resources/css/jquery.fullPage.css" rel="stylesheet">
<link rel="stylesheet" href="resources/css/jquery.bxslider.css">
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic+Coding:700&display=swap" rel="stylesheet">
<script src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>
<style>
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothiccoding.css);

body,h1,h2,h3,h4,h5,table,button{
 font-family: 'Nanum Gothic Coding', monospace;
}

#color-change {
   background-color: white;  /* 위에 배너 컬러 */
   text-align:center;
}

.mainspan {
   color: black;
   font-size: 1.2em;
}

.mainspan:hover {
   color: gray;
}


#container div {
   width: 250px;
   height: 300px;
   background: #fff;
   box-shadow: 0px 1px 1px #aaa;
   padding: 15px;
   padding-bottom: 15px;
   margin: 15px;
}

#container div img {
   width: 100%;
   padding-bottom: 15px;
   margin-bottom: 5px;
}

#container div h2 {
   font-size: 1em;
   color: black;
}

#container div p {
   font-family: "맑은 고딕", 돋움;
   font-size: 0.8em;
   color: #222;
   margin: 0;
   padding: 10px;
}

#wrapper {
   width: 600px;
   margin: 0 auto;
}
li{
display:inline-block;
}
ul{
text-align:center;
}


/* a.button {  
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
    } */
  .b_search{
  display: inline-block;
  padding: 7px 15px;
  font-size: 10px;
  cursor: pointer;
  text-align: center;
  outline: none;
  color: black;
  background-color: white;
  border: 1px solid #333333;
  border-radius: 5px;
  box-shadow: 0 2px #999;
  font-size :1em;
  font-weight: bold;
}
.b_search:hover {
border: 1px solid #4867e1;
}
    .wrapper_icon{
      display:grid;
      grid-template-columns:repeat(4, 1fr);
      grid-column-gap:15%;
      grid-row-gap:5%;
      padding-right: 25%;
      padding-left:25%;
      padding-top:3%;
      padding-bottom: 3%;
    }
    .items{ 
      text-align: center;
      font-size: 12px;      
    }   


</style>
</head>
<body>
  <!-- 0311 jung main page 메뉴막대 부분, 한섹션식 스크롤 되도록 설정 -->
   <nav class="navbar navbar-default navbar-fixed-top">
      <div class="navbar-header">
         <!-- 0311 jung 메뉴 토글 부분 -->
         <button type="button" class="navbar-toggle" data-toggle="collapse"
            data-target=".navbar-collapse">
            <span class="icon-bar"></span> 
            <span class="icon-bar"></span> 
            <span class="icon-bar"></span>
         </button>
         <!-- 0311 jung 로고 클릭시 메인화면으로 다시 불러오기 -->
         
      </div>
      <div id="color-change">
      <div class="navbar-collapse collapse">     
      <a href="http://localhost:8000/backstreet/main" class="navbar-brand"><img src="resources/images/blacklogo.png"  width="30%" ></a>            
   <br>
      <ul class="nav navbar-nav" id="mainlist">      
       <li><a href="http://localhost:8000/backstreet/intro"><span class="mainspan"> 사용가이드</span></a></li>
      <li><a href="http://localhost:8000/backstreet/map"><span class="mainspan"> 골목상권 분석</span></a></li>
      <li><a href="http://localhost:8000/backstreet/consulting"><span class="mainspan"> 내 상권 찾기</span></a></li>
      <li><a href="http://localhost:8000/backstreet/newConsulting"><span class="mainspan"> 창업 컨설팅</span></a></li>
      <li><a href="http://localhost:8000/backstreet/trands"><span class="mainspan"> 트랜드</span></a></li>
      <li><a href="http://localhost:8000/backstreet/boardmain"><span class="mainspan">고객센터</span></a></li>
	<c:if test="${requestScope.code eq null}">
		<li id="in"><a href="http://localhost:8000/backstreet/loginView"><span class="mainspan">로그인</span></a></li>
	</c:if>
	<c:if test="${requestScope.code ne null}">
		<li id="in"><a href="http://localhost:8000/backstreet/logout?accessToken=${requestScope.accessToken}"><span class="mainspan">로그아웃</span></a></li>
	</c:if>
      </ul>
      </div>
      </div>
   </nav> 
         <script src="resources/js/jquery-2.2.4.min.js"></script>
      <script src="resources/js/bootstrap.min.js"></script>
      <script src="resources/css/jquery.fullpage.js"></script>

      <script>
         $(document).ready(function() {
            $("#fullpage").fullpage();
         });
      </script>



      <script src="resources/js/jquery-2.2.4.min.js"></script>
      <script src="resources/js/jquery.vgrid.min.js"></script>
      <script>
         $('#container').vgrid({
            time : 400,
            delay : 30,
            wait : 500
         });
      </script>
   <br><br><br><br><br><br><br>
   
   
<%
   String areaId = request.getParameter("area_id");
   String area = request.getParameter("area_coname");
   //System.out.println(areaId);
   if(areaId == null){
      areaId = "";
   }
%>
      <h4 style="text-align:center;"><%= area %> 상권에서 검색하고 싶은 업종을 선택하세요.</h4>
      
      
<%--       <div style="text-align:center;">
         <button class="b_search" type="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS100001'">#한식음식점</button>
         <button class="b_search" type="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS100008'">#제과점</button>
         <button class="b_search" type="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS100009'">#커피 및 음료</button>
         <button class="b_search" type="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS100010'">#호프</button>
         <button class="b_search" type="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS200001'">#학원</button>
         <button class="b_search" type="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS200009'">#숙박업</button>
         <button class="b_search" type="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS200011'">#pc방</button>
         <button class="b_search" type="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS200016'">#미용실</button>
         <button class="b_search" type="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS300002'">#편의점</button>
         <button class="b_search" type="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS300007'">#의류</button>
         <button class="b_search" type="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS300008'">#패션용품</button>
         <button class="b_search" type="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS300011'">#화장품</button>
</div>
 --%>


 <!--     0317 jung  아이콘 div -->
    <div class="wrapper_icon">
<!--     0317 jung 첫번째 아이콘 -->
     <div class="items"  onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS100001'">
     <img  src="resources/images/store_1.svg"width='80%'><br><br>
     <b>한식음식점</b>
       </div>
 <!--     0317 jung 두번째 아이콘 -->
       <div class="items"onclick= "location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS100008'">
     <img src="resources/images/store_2.svg"width='80%'><br><br>
      <b>제과점</b>
       </div>
 <!--     0317 jung 세번째 아이콘 -->
       <div class="items"onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS100009'">
     <img src="resources/images/store_3.svg"width='80%'><br><br>
      <b>커피 및 음료</b>
       </div>
 <!--     0317 jung 네번째 아이콘 -->
       <div class="items" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS100010'">
     <img src="resources/images/store_4.svg"width='80%'><br><br>
      <b>호프</b>
       </div>
 <!--     0317 jung 다섯번째 아이콘 -->
       <div class="items" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS200001'">
     <img src="resources/images/store_5.svg"width='80%'><br><br>
      <b>학원</b>
       </div>
 <!--     0317 jung 여섯번째 아이콘 -->
       <div class="items"  onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS200009'">
     <img src="resources/images/store_6.svg"width='80%'><br><br>
      <b>숙박업</b>
       </div>       

    <!--     0317 jung 일곱번째 아이콘 -->
       <div class="items" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS200011'">
     <img src="resources/images/store_7.svg"width='80%'><br><br>
      <b>pc방</b>
       </div>       
    <!--     0317 jung 8번째 아이콘 -->
       <div class="items" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS200016'">
     <img src="resources/images/store_8.svg"width='80%'><br><br>
      <b>미용실</b>
       </div>       
    <!--     0317 jung 9번째 아이콘 -->
       <div class="items" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS300002'">
     <img src="resources/images/store_9.svg"width='80%'><br><br>
      <b>편의점</b>
       </div>  
    <!--     0317 jung 10번째 아이콘 -->
       <div class="items" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS300007'">
     <img src="resources/images/store_10.svg"width='80%'><br><br>
      <b>의류</b>
       </div>  
    <!--     0317 jung 11번째 아이콘 -->
       <div class="items" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS300008'">
     <img src="resources/images/store_11.svg"width='80%'><br><br>
      <b>패션용품</b>
       </div>       
    <!--     0317 jung 12번째 아이콘 -->
       <div class="items" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS300011'">
     <img src="resources/images/store_12.svg"width='80%'><br><br>
      <b>화장품</b>
       </div>       
   </div>
</body>
</html>