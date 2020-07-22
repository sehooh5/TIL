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
 <link rel="stylesheet" href="https://unpkg.com/leaflet@1.5.1/dist/leaflet.css"
   integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
   crossorigin=""/>
<script src="https://unpkg.com/leaflet@1.5.1/dist/leaflet.js"
   integrity="sha512-GffPMF3RvMeYyc1LWMHtK8EbPv0iNZ8/oTtHPx9/cc2ILxQ+u905qIwdpULaqDkyBKgOaB57QTMg7ztg8Jm2Og=="
   crossorigin=""></script>
<link rel="stylesheet" href="http://localhost:8000/backstreet/resources/css/nav.css">
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

#mapid{
padding: 10%;
}
</style>
</head>
<body>
 <!-- 0311 jung main page 메뉴막대 부분, 한섹션식 스크롤 되도록 설정 -->
   <nav class="navbar-default navbar-fixed-top">
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
<form action="http://localhost:8000/backstreet/consultingSearch"
	method="GET">				
	<div class="d_submit">
	<input type="text" placeholder="주소를 입력하세요." name="query"> 
	&nbsp;&nbsp;
	<input type="submit" value="검색" class="t_submit">
</div>
</form>

<br>

<c:if test="${requestScope.json ne null}">


<div id="mapid" style="width: 100%; height: 600px; text-align:center">  </div>
<script>
//37.6931632:126.7650706

var mymap;
   if(mymap)
      mymap.remove(); // 지운다음 맵객체 생성.
   mymap = L.map('mapid').setView([${requestScope.json[1]},${requestScope.json[0]}],16)
   L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
      maxZoom: 18,
      attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
         '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
         'Imagery <a href="https://www.mapbox.com/">Mapbox</a>',
      id: 'mapbox.streets'
   }).addTo(mymap);
   
var myIcon = L.icon({
    iconUrl: 'resources/images/storeimg.png',
    iconSize: [60, 60]
});
var content = "&nbsp;&nbsp;<img src='resources/images/storeimg.png' width='20'><b>&nbsp;&nbsp;"+' 김세정의 골목상권 '+"&nbsp;&nbsp;</b><img src='resources/images/storeimg.png' width='20'>&nbsp;&nbsp;<hr>"+"고객님께서 창업하실 상권입니다. <br>성공을 기원합니다.";
   //L.marker([37.693116, ${requestScope.json[1]}], {icon: myIcon}).addTo(mymap).bindPopup(content);
   L.marker([${requestScope.json[1]}, ${requestScope.json[0]}], {icon: myIcon}).addTo(mymap).bindPopup(content);
   console.log(${requestScope.json[0]});
   console.log(${requestScope.json[1]}); 
 
</script>
</c:if>
</body>
</html>