# main

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link
	href="https://fonts.googleapis.com/css?family=Nanum+Gothic+Coding:700&display=swap"
	rel="stylesheet">
<script src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>
<style>
@import
	url(http://fonts.googleapis.com/earlyaccess/nanumgothiccoding.css);

body, h1, h2, h3, h4, h5, table, button {
	font-family: 'Nanum Gothic Coding', monospace;
}

body{
background-color:#f1f1f1;
}

#color-change {
	background: linear-gradient(to right, #3d64c0 0%, #63b8ad 100%);
	/* 위에 배너 컬러 */
	text-align: center;
}

.mainspan {
	color: White;
	font-size: 1.2em;
}

.mainspan:hover {
	color: gray;
}

#con {
	background: linear-gradient(to right, #3d64c0 0%, #63b8ad 100%);
	/* 아래 배너 컬러 */
	height: 300px;
	text-align: center;
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
	font-size: 0.8em;
	color: #222;
	margin: 0;
	padding: 10px;
}

#wrapper {
	width: 600px;
	margin: 0 auto;
}

li {
	display: inline-block;
}

ul {
	text-align: center;
}

#map_image {
	text-align: center;
}

@import url('https://fonts.googleapis.com/css?family=Gamja+Flower:400');

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
     .wrapper_icon{
      display:grid;
      grid-template-columns:repeat(3, 1fr);
      grid-column-gap:20%;
      grid-row-gap:5%;
      padding-right: 30%;
      padding-left:30%;
      padding-top:3%;
      padding-bottom: 3%;
    }
    .items{ 
      text-align: center;
      font-size: 12px;      
    }   
svg#first { width: 200px; height: 250px;  }
svg#second { width: 200px; height: 250px;  }
svg#third { width: 200px; height: 250px;  }
svg#fourth { width: 200px; height: 250px;  }

.bar {
    fill: #2e5cb8;
    fill-opacity: 1;
    stroke: #001433;
}
.bar:hover {
    fill-opacity: 0.3;
}
.text{
	
	font-family: 'Roboto', sans-serif;
	color : #a3a375;
	font-size: 10px;
}
.navi{
	font-weight: bold;
	text-align : center;
	font-family: 'Roboto', sans-serif;
	color : #a3a375;
	font-size: 14px;
}
img#signal{
	width : 200 px;
}
span.signaltext{
	text-align : center;
	font-family: 'Roboto', sans-serif;
	font-size: 9px;
}
</style>
</head>
<body >

	<!-- 0311 jung main page 메뉴막대 부분, 한섹션식 스크롤 되도록 설정 -->
	<nav class="navbar-default navbar-fixed-top">
		<div class="navbar-header">
			<!-- 0311 jung 메뉴 토글 부분 -->
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!-- 0311 jung 로고 클릭시 메인화면으로 다시 불러오기 -->

		</div>
		<div id="color-change">
			<div class="navbar-collapse collapse">
				<a href="http://localhost:8000/backstreet/main" class="navbar-brand"><img
					src="resources/images/logo.png" width="30%"></a> <br>
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
	<!-- 0311 jung 한세션씩 스크롤하기 위해 class 속성에 section을 추가해준다. -->
	<div id="fullpage">
			<div id="con" class="book-container section">	
			<br><br><br><br><br><br>
			<form action="http://localhost:8000/backstreet/trandsSearch"
				method="GET">				
				<div class="d_submit">
				<input type="text" placeholder="상권 트랜드 입력" name="query"> 
				&nbsp;&nbsp;
				<input type="submit" value="검색" class="t_submit">
			</div>
			</form>	</div>


 <!--     0317 jung  아이콘 div -->
    <div class="wrapper_icon">
<!--     0317 jung 첫번째 아이콘 -->
<!--      <a href="http://localhost:8000/backstreet/resources/howmuch.html" target="_blank"> -->
     <div class="items"
      onclick="window.open('http://localhost:8000/backstreet/resources/howmuch.html','howmuch','width=800,height=500,location=no,status=no,scrollbars=yes');">
     <img  src="resources/images/payment.svg"width='80%'><br><br>
     <b>컨설팅 비용문의</b>
   	 </div>
<!--    	 </a> -->
 <!--     0317 jung 두번째 아이콘 -->
   	 <div class="items">
     <img src="resources/images/q_2.svg"width='80%'><br><br>
      <b>자주하는 질문</b>
   	 </div>
 <!--     0317 jung 세번째 아이콘 -->
   	 <div class="items">
     <img src="resources/images/q_3.svg"width='80%'><br><br>
      <b>사용가이드</b>
   	 </div>
 <!--     0317 jung 네번째 아이콘 -->
   	 <div class="items">
     <img src="resources/images/storeimg.png"width='80%'><br><br>
      <b>내 상권 찾기</b>
   	 </div>
 <!--     0317 jung 다섯번째 아이콘 -->
   	 <div class="items">
     <img src="resources/images/q_4.svg"width='80%'><br><br>
      <b>골목상권 예시</b>
   	 </div>
 <!--     0317 jung 여섯번째 아이콘 -->
   	 <div class="items">
     <img src="resources/images/q_5.svg"width='80%'><br><br>
      <b>컨설팅 예시</b>
   	 </div>   	 
	</div>

		<div id="wrapper">
			<div class="bxslider">
				<div>
					<img src="resources/images/1.jpg" title="1">
				</div>
				<div>
					<img src=resources/images/2.jpg " title="2">
				</div>
				<div>
					<img src="resources/images/3.jpg" title="3">
				</div>
				<div>
					<img src="resources/images/4.jpg" title="4">
				</div>
			</div>
		</div>
		<script src="resources/js/jquery-2.2.4.min.js"></script>
		<script src="resources/js/jquery.bxslider.js"></script>
		<script>
			var h1dom = document.getElementsByTagName("h1")[0];

			$('.bxslider').bxSlider({
				slideWidth : 600,
				captions : true,
				auto : true,
				autoControls : true,
				stopAutoOnclick : true,
			});
		</script>
		<div id="book1" class="book-container section">
			<div class="container">
				<h1>자사의 컨설팅을 받은 기업들</h1>
				<div class="book-desc">
					<p class="accent">
						김세정의 골목상권의 <br>컨설팅 서비스를 받은 기업들!!
					</p>
					<p>&lt;야! 너두&amp;할수있어&gt;당신도 김세정의 골목상권의 컨설팅 서비스를 받는다면 성공한 기업으로
						바뀔 수 있습니다. 당신도 할수있습니다 !!!</p>
				</div>
			</div>
		</div>

		<div class="book-container section">
			<div id="container">
				<div>
				<h2 class="navi">평균 운영 개월 및 폐업 개월 수</h2>
				<svg id="first"></svg>
				</div>
				<div>
				<h2 class="navi">평균 인구 자료</h2>
				<svg id="second"></svg>
				</div>
				<div>
				<h2 class="navi">개업 및 폐업률</h2>
				<svg id="third"></svg>
				</div>
				<div>
				<h2 class="navi">창업 위험도</h2><br>
				<img src="../backstreet/resources/images/signal2.png" id="signal">
				<span class="signaltext">
				최근 3년 시계열을 관측, 폐업 유사패턴 공간에서
				</span>
				<span class="signaltext">
				개별기업의 생존확률을 추정해 만든 지수입니다.
				</span>
				</div>
			</div>
		</div>
<!-- 그래프 구현 부분 --><!-- ${requestScope.chartMain} -->
<script>
var data = ${requestScope.chartMain};

var dataSet = [
	data[0].open_month,
	data[0].close_month
	];
	
var dataSet2 = [
	data[0].all_job,
	data[0].all_population,
	data[0].all_apart*10
	];
	
var dataSet3 = [
	data[0].open_rate,
	data[0].close_rate
	];
	
//가장 큰 수 추출
var max = d3.max(dataSet);
var max2 = d3.max(dataSet2);
var max3 = d3.max(dataSet3);

//scale 설정
var scale = d3.scale.linear()  // 선형 스케일 설정
.domain([0, max]) // 원래 데이터 범위
.range([0, 200]); // 실제 출력 크기	

var scale2 = d3.scale.linear()  // 선형 스케일 설정
.domain([0, max2]) // 원래 데이터 범위
.range([0, 200]); // 실제 출력 크기	

var scale3 = d3.scale.linear()  // 선형 스케일 설정
.domain([0, max3]) // 원래 데이터 범위
.range([0, 60]); // 실제 출력 크기	

//first 그래프 부분
	d3.select("#first")
	.selectAll("rect")
	.data(dataSet)
	.enter()
	.append("rect")
	.attr("class", "bar")
	.attr("y",function(d, i){	
		return 200-scale(d) +"px";		
	})
	.attr("x",function(d,i){
		return i*70+60;
	})
	.on("click", function(d){
				d3.select(this).style("fill", "green")	
	})
	.attr("width", "40px")	// 막대그래프의 넓이를 20px로 지정
	.attr("height", function(d, i){	// 넓이를 배열의 내용에 따라 계산
		return scale(d) +"px";		// 데이터의 값을 scale적용하여 보여줌
	})
	
//second 그래프 부분
	d3.select("#second")
	.selectAll("rect")
	.data(dataSet2)
	.enter()
	.append("rect")
	.attr("class", "bar")
	.attr("y",function(d, i){	
		return 200-scale2(d) +"px";		
	})
	.attr("x",function(d,i){
		return i*65+20;
	})
	.on("click", function(d){
				d3.select(this).style("fill", "green")	
	})
	.attr("width", "40px")	// 막대그래프의 넓이를 20px로 지정
	.attr("height", function(d, i){	// 넓이를 배열의 내용에 따라 계산
		return scale2(d) +"px";		// 데이터의 값을 scale적용하여 보여줌
	})
	
//third 그래프 부분
	d3.select("#third")
	.selectAll("rect")
	.data(dataSet3)
	.enter()
	.append("rect")
	.attr("class", "bar")
	.attr("y",function(d, i){	
		return 200-scale3(d) +"px";		
	})
	.attr("x",function(d,i){
		return i*70+60;
	})
	.on("click", function(d){
				d3.select(this).style("fill", "green")	
	})
	.attr("width", "40px")	// 막대그래프의 넓이를 20px로 지정
	.attr("height", function(d, i){	// 넓이를 배열의 내용에 따라 계산
		return scale3(d) +"px";		// 데이터의 값을 scale적용하여 보여줌
	})
	
//first text
var svg = d3.select("#first");
var textSet = ["영업 개월수","폐업 개월수"];
	svg.selectAll("text")
    .data(textSet)
    .enter().append("text")
    .text(function(d) {return d})
        .attr("class", "text")
        .attr("x", function(d, i) {return 70*(i-1) + 125})
        .attr("y", 220);
	
	//second text
	var svg = d3.select("#second");
	var textSet = ["직장인 수","상주 인구수","아파트 단지"];
		svg.selectAll("text")
	    .data(textSet)
	    .enter().append("text")
	    .text(function(d) {return d})
	        .attr("class", "text")
	        .attr("x", function(d, i) {return 64*(i-1) + 82})
	        .attr("y", 220);
		
//third text
var svg = d3.select("#third");
var textSet = ["개업률","폐업률"];
	svg.selectAll("text")
		    .data(textSet)
		    .enter().append("text")
		    .text(function(d) {return d})
		        .attr("class", "text")
		        .attr("x", function(d, i) {return 70*(i-1) + 135})
		        .attr("y", 220);

</script><!-- 여기까쥐 그래프 -->
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

		<script>
			var result;
			// 0313 jung, kim 시간에 따라 메인 + 메뉴화면 색상 변경되게 설정
			setInterval(
					function() {
						console.log("3");
						var color;
						result = Math.floor(Math.random() * 5) + 1;
						if (result == 1) {
							color = 'linear-gradient(to right, #63b8ad 0%, #63b8ad 100%)';
						} else if (result == 2) {
							color = 'linear-gradient(to right, ##3d64c0 0%, ##3d64c0 100%)';
						} else if (result == 3) {
							color = 'linear-gradient(to right, #3d84a8 0%, #3d84a8 100%)';
						} else if (result == 4) {
							color = 'linear-gradient(to right, #46cdcf 0%, #46cdcf 100%)';
						} else {
							color = 'linear-gradient(to bottom, #abedd8 0%, #abedd8 100%)';
						}

						d3.selectAll("#color-change, #con").transition()
								.duration(2000).style("background", color);
					}, 3300);
		</script>

		<div id=map_image>
			<img src="resources/images/s_1.jpg" style="width: 50%">
		</div>
</body>
</html>
```

