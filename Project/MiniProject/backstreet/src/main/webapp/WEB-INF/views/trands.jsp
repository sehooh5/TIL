 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
   content="width=device-width, initial-scale=1.0, user-scaclable=no">
<title>김세정의 골목상권</title>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/style.css" rel="stylesheet">
<link href="resources/css/jquery.fullPage.css" rel="stylesheet">
<link rel="stylesheet" href="resources/css/jquery.bxslider.css">
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic+Coding:700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>
<style>


body, h1, h2, h3, h4, h5, table, button,p,b {
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
.w3-row-padding{
padding:0% 5%;
text-align:center;
}

.trend-contents{
font-size:10px;
}

input[type=text] {
  width: 40%;
  box-sizing: border-box;
  border: 2px solid #ccc;
  border-radius: 4px;
  font-size: 16px;
  background-color: #eee;
  background-image: url('searchicon.png');
  background-position: 10px 10px; 
  background-repeat: no-repeat;
  padding: 12px 20px 12px 40px;
  -webkit-transition: width 0.4s ease-in-out;
  transition: width 0.4s ease-in-out;
text-align: center;
  border-radius: 45px;
}

input[type=text]:focus {
  width: 60%;
}


html, body {
  height: 100%;
}

.d_submit {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: grey;
}
/* 0317 검색버튼 효과 */
.t_submit{
  width: 100px;
  height: 45px;
  font-family: 'Roboto', sans-serif;
  font-size: 15px;
  text-transform: uppercase;
  letter-spacing: 2.5px;
  font-weight: 500;
  color: #000;
  background-color: #fff;
  border: none;
  border-radius: 45px;
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease 0s;
  cursor: pointer;
  outline: none;
  }
/* 0317 jung 검색버튼 호버 효과 */
.t_submit:hover {
  background-color: #4867e1;
  box-shadow: 0px 15px 20px rgba(46, 229, 157, 0.4);
  color: #fff;
  transform: translateY(-7px);
}

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
<br><br><br><br><br><br><br><br>
<form action="http://localhost:8000/backstreet/trandsSearch"
	method="GET">
	
	<div class="d_submit">
	<input type="text" placeholder="상권 트랜드 입력" name="query"> 
	&nbsp;&nbsp;
	<input type="submit" value="검색" class="t_submit">
</div>
</form>

<br>
<br>



<c:if test="${requestScope.json ne null}">

<!-- 0317 jung 출력부분 -->
<%String[][] blogContents = (String[][])request.getAttribute("json");%>
<hr>
<!-- 배열 인덱스
i는 게시물 갯수
[i][0]: title(블로그 글 제목)
[i][1]: contents(블로그 글 요약)
[i][2]: url(블로그 글 url)
[i][3]: blogname(블로그의 이름)
[i][4]: thumbnail(썸네일: 이미지)
[i][5]: datetime(블로그 글 작성시간)
 -->
  <!-- 0317 jung 두번째 Photo Grid-->
  <div class="w3-row-padding">
  <a href="<%= blogContents[0][2]%>">
    <div class="w3-third w3-container w3-margin-bottom">
      <img src="<%= blogContents[0][4]%>" style="width:60%; height:10% " class="w3-hover-opacity">
      <br><br>
      <div class="w3-container w3-white">
        <p><b><%= blogContents[0][0]%></b></p>
        <p class=trend-contents><%= blogContents[0][1]%></p>
      </div>
    </div>
    </a>
    <a href="<%= blogContents[1][2]%>">
    <div class="w3-third w3-container w3-margin-bottom">
      <img src="<%= blogContents[1][4]%>" style="width:60%; height:10% " class="w3-hover-opacity">
      <br><br>
      <div class="w3-container w3-white">
        <p><b><%= blogContents[1][0]%></b></p>
        <p class=trend-contents><%= blogContents[1][1]%></p>
      </div>
    </div>
    </a>
        <a href="<%= blogContents[2][2]%>">
    <div class="w3-third w3-container w3-margin-bottom">
      <img src="<%= blogContents[2][4]%>" style="width:60%; height:10% " class="w3-hover-opacity">
      <br><br>
      <div class="w3-container w3-white">
        <p><b><%= blogContents[2][0]%></b></p>
        <p class=trend-contents><%= blogContents[1][1]%></p>
      </div>
    </div>
    </a>        
  </div>


  <!--0317 jung 두번째 Photo Grid-->
  <div class="w3-row-padding">
  <a href="<%= blogContents[3][2]%>">
    <div class="w3-third w3-container w3-margin-bottom">
      <img src="<%= blogContents[3][4]%>" style="width:60%; height:10% " class="w3-hover-opacity">
      <br><br>
      <div class="w3-container w3-white">
        <p><b><%= blogContents[3][0]%></b></p>
        <p class=trend-contents><%= blogContents[3][1]%></p>
      </div>
    </div>
    </a>
    <a href="<%= blogContents[4][2]%>">
    <div class="w3-third w3-container w3-margin-bottom">
      <img src="<%= blogContents[4][4]%>" style="width:60%; height:10% " class="w3-hover-opacity">
      <br><br>
      <div class="w3-container w3-white">
        <p><b><%= blogContents[4][0]%></b></p>
        <p class=trend-contents><%= blogContents[4][1]%></p>
      </div>
    </div>
    </a>
        <a href="<%= blogContents[5][2]%>">
    <div class="w3-third w3-container w3-margin-bottom">
      <img src="<%= blogContents[5][4]%>" style="width:60%; height:10% " class="w3-hover-opacity">
      <br><br>
      <div class="w3-container w3-white">
        <p><b><%= blogContents[5][0]%></b></p>
        <p class=trend-contents><%= blogContents[1][1]%></p>
      </div>
    </div>
    </a>        
  </div>

  <!--0317 jung 세번째 Photo Grid-->
  <div class="w3-row-padding">
  <a href="<%= blogContents[6][2]%>">
    <div class="w3-third w3-container w3-margin-bottom">
      <img src="<%= blogContents[6][4]%>" style="width:60%; height:10% " class="w3-hover-opacity">
      <br><br>
      <div class="w3-container w3-white">
        <p><b><%= blogContents[6][0]%></b></p>
        <p class=trend-contents><%= blogContents[6][1]%></p>
      </div>
    </div>
    </a>
    <a href="<%= blogContents[7][2]%>">
    <div class="w3-third w3-container w3-margin-bottom">
      <img src="<%= blogContents[7][4]%>" style="width:60%; height:10% " class="w3-hover-opacity">
      <br><br>
      <div class="w3-container w3-white">
        <p><b><%= blogContents[7][0]%></b></p>
        <p class=trend-contents><%= blogContents[7][1]%></p>
      </div>
    </div>
    </a>
        <a href="<%= blogContents[8][2]%>">
    <div class="w3-third w3-container w3-margin-bottom">
      <img src="<%= blogContents[8][4]%>" style="width:60%; height:10% " class="w3-hover-opacity">
      <br><br>
      <div class="w3-container w3-white">
        <p><b><%= blogContents[8][0]%></b></p>
        <p class=trend-contents><%= blogContents[8][1]%></p>
      </div>
    </div>
    </a>        
  </div>
</c:if>
<c:if test="${requestScope.json eq null}">
<br>
<div style="text-align:center;">
<button class="b_search" type="button" onclick="location.href='http://localhost:8000/backstreet/trandsSearch?query=창업 방법'">#창업 방법</button>
<button class="b_search" type="button" onclick="location.href='http://localhost:8000/backstreet/trandsSearch?query=상권분석'">#상권분석</button>
<button class="b_search" type="button" onclick="location.href='http://localhost:8000/backstreet/trandsSearch?query=유니콘기업'">#유니콘기업</button>
<button class="b_search" type="button" onclick="location.href='http://localhost:8000/backstreet/trandsSearch?query=서울 맛집'">#서울 맛집</button>
<button class="b_search" type="button" onclick="location.href='http://localhost:8000/backstreet/trandsSearch?query=2020 트랜드'">#2020 트랜드</button>
</div>
<br>



</c:if>

</body>
</html> 