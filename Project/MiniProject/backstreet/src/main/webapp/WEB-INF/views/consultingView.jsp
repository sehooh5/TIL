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

#s_color{
color : blue;
}
span{
	color : blue;
}
svg { width: 320px; height: 240px;  }
.bar {
    fill: skyblue;
    fill-opacity: 0.3;
    stroke: skyblue;
}
.bar:hover {
    fill-opacity: 1;
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
	String area_id = request.getParameter("area_id");
	String serv_id = request.getParameter("serv_id");
	//System.out.println(area_id);
	//System.out.println(serv_id);
	if(area_id == null&&serv_id==null){
		area_id = "";
		serv_id = "";
	}
%>

<c:if test="${requestScope.month_money eq null}">
<span>${requestScope.area_coname}</span> 상권의 <span>${requestScope.serv_coname}</span> 업종 관련 자료가 없습니다.

<a href="#" class="button" onclick="location.href='/backstreet/map'">상권 다시 선택하기</a>
<a href="#" class="button" onclick="history.back()">업종 다시 선택하기</a>
</c:if>

<c:if test="${requestScope.month_money != null}">
<pre>
	고객님께서 선택하신   <span>${requestScope.area_coname}</span> 상권의 <span>${requestScope.serv_coname}</span> 업종 분석 정보에 대해 안내 드리겠습니다.
	
	
	## sales_1 테이블
	
	<span>${requestScope.serv_coname}</span> 업종의 월매출 금액은 <span>${requestScope.month_money}</span> 원 이고 월 매출 수는 <span>${requestScope.month_num}</span> 건 입니다.
	
	<span>${requestScope.serv_coname}</span> 업종의 주중(월-금) 매출 금액은 <span>${requestScope.wday_money}</span> 원 이고. 주말(토,일) 매출 금액은 <span>${requestScope.wkend_money}</span> 원 입니다.
	
	<span>${requestScope.serv_coname}</span> 업종의 남성 매출 금액은 <span>${requestScope.m_money}</span> 원 이고, 여성 매출 금액은 <span>${requestScope.w_money}</span> 원 입니다.
	

	
	## sales_2 테이블
	
	<span>${requestScope.serv_coname}</span> 업종의 연령별 매출 금액에 대한 정보 입니다.
	
	10대 매출 금액 : <span>${requestScope.money_10}</span> 원
	
	20대 매출 금액 : <span>${requestScope.money_20}</span> 원
	
	30대 매출 금액: <span>${requestScope.money_30}</span> 원
	
	40대 매출 금액: <span>${requestScope.money_40}</span> 원
	
	50대 매출 금액: <span>${requestScope.money_50}</span> 원
	
	60대 매출 금액: <span>${requestScope.money_60}</span> 원
	
	<!-- 그래프 구현 부분 --><!-- ${requestScope.chart} -->
	<svg id="myGraph"></svg>
	<script>
	var data = ${requestScope.chart};

	var dataSet = [
		data[0].sal_money_10,
		data[0].sal_money_20,
		data[0].sal_money_30,
		data[0].sal_money_40,
		data[0].sal_money_50,
		data[0].sal_money_60
		];
	
	//가장 큰 금액 추출
	var max = d3.max(dataSet);
	console.log(max);
	
	//scale 설정
	var scale = d3.scale.linear()  // 선형 스케일 설정
	.domain([0, max]) // 원래 데이터 범위
	.range([0, 320]); // 실제 출력 크기	
	
	//그래프 부분
		d3.select("#myGraph")
		.selectAll("rect")
		.data(dataSet)
		.enter()
		.append("rect")
		.attr("class", "bar")
		.attr("x",0)
		.attr("y",function(d,i){
			return i*25;
		})
		.on("click", function(d){
					d3.select(this).style("fill", "green")	
		})
		.attr("height", "20px")	// 막대그래프의 높이를 20px로 지정
		.attr("width", "0px")		// 처음에는 막대그래프의 넓이를 0px로 지정
		.transition()				// 그래프에 애니메이션 적용
		.delay(function(d, i){
			return i * 500;		// 0.5초마다 그리도록 대기 시간을 설정
		})
		.duration(2500)			// 2.5초 동안 애니메이션이 진행되도록 함
		.attr("width", function(d, i){	// 넓이를 배열의 내용에 따라 계산
			return scale(d) +"px";		// 데이터의 값을 scale적용하여 보여줌
		})
	var svg = d3.select("svg");
	var dataSet2 = ["10대 매출","20대 매출","30대 매출","40대 매출","50대 매출","60대 매출"];
		//텍스트 구현부문
		svg.selectAll("text")
	    .data(dataSet2)
	    .enter().append("text")
	    .text(function(d) {return d})
	        .attr("class", "text")
	        .attr("x", 10)
	        .attr("y", function(d, i) {return 25*(i-1) + 40});
	</script><!-- 여기까쥐 그래프 -->	
	## sales_3 테이블
	
	<span>${requestScope.serv_coname}</span> 업종의 주중(월-금) 매출 수는 <span>${requestScope.wday_num}</span> 건 이고, 주말(토-일) 매출 수는 <span>${requestScope.wkend_num}</span> 건 입니다.
	
	<span>${requestScope.serv_coname}</span> 업종의 남성 매출 수는 <span>${requestScope.m_num}</span> 건 이고, 여성 매출 수는 <span>${requestScope.w_num}</span> 건 입니다.
	
	
	
	## sales_4 테이블
	
	10대 매출 수 : <span>${requestScope.num_10}</span> 건
	
	20대 매출 수 : <span>${requestScope.num_20}</span> 건
	
	30대 매출 수: <span>${requestScope.num_30}</span> 건
	
	40대 매출 수: <span>${requestScope.num_40}</span> 건
	
	50대 매출 수: <span>${requestScope.num_50}</span> 건
	
	60대 매출 수: <span>${requestScope.num_60}</span> 건
	
	
	<a href="#" class="button" onclick="location.href='/backstreet/map'">상권 다시 선택하기</a>
	<a href="#" class="button" onclick="location.href='/backstreet/searchreport?area_id=<%=area_id %>&serv_id=<%=serv_id%>'">업종 다시 선택하기</a>
</pre>
	<hr>
	<h2>세일즈 1~4 출력 영역</h2>
	<hr>
	
	
	<h5>우선 고객님께서 선택하신 <span id="s_color">${requestScope.areaCodeName.getArea_coname()}</span> 상권에 대한 정보만 안내 드리겠습니다.</h5> 
	<h5>고객님이 선택하신 상권은 <span id="s_color">${requestScope.changeCodeName.getChange_coname() }</span> 입니다. </h5>
	
	<c:if test="${requestScope.change_id.getChange_id() eq 1}">
	<h4><span id="s_color">${requestScope.areaCodeName.getArea_coname()}</span> 상권은 현재 가장 정체되고 있는 상권으로 다른 상권을 선택하시는 것을 권유합니다. </h4>
	</c:if>
	<c:if test="${requestScope.change_id.getChange_id() eq 2}">
	<h4><span id="s_color">${requestScope.areaCodeName.getArea_coname()}</span> 상권은 현재 축소되고 있는 상권으로 다른 상권을 선택하는 것을 고려해보셔야 할 것 같습니다.  </h4>
	</c:if>
	<c:if test="${requestScope.change_id.getChange_id() eq 3}">
	<h4><span id="s_color">${requestScope.areaCodeName.getArea_coname()}</span> 상권은 현재 확장되고 있는 상권으로 앞으로 성공할 가능성이 높습니다. </h4>
	</c:if>
	<c:if test="${requestScope.change_id.getChange_id() eq 4}">
	<h4><span id="s_color">${requestScope.areaCodeName.getArea_coname()}</span> 상권은 현재 다이나믹하게 확장되고 있는 상권으로 고객님의 안목이 아주 높으십니다. </h4>
	</c:if>
	<hr>
	<h4>아래에서 고객님께서 선택하신 <span id="s_color">${requestScope.areaCodeName.getArea_coname()}</span> 상권/ <span id="s_color">${requestScope.serviceCodeName.getServ_coname()}</span> 업종에 대해 더 자세한 컨설팅 정보를 보여드리겠습니다.</h4>
	<ul>
	<li><span id="s_color">${requestScope.serviceCodeName.getServ_coname()}</span> 업종이 운영되는 영업 개월 평균은 <span id="s_color">${requestScope.oper_month.getOper_month() }</span> 입니다.</li>
	<li> 폐업 개월 평균은 <span id="s_color">${requestScope.close_month.getClose_month() }</span> 입니다.</li>
	<c:if test="${requestScope.oper_month.getOper_month() gt requestScope.oper_month_avg}">
	<li>골목상권의 평균 영업 개월 수인 <span id="s_color">${requestScope.oper_month_avg}</span> 보다 큽니다.</li> 
	</c:if>
	<c:if test="${requestScope.oper_month.getOper_month() le requestScope.oper_month_avg}">
	<li>골목상권의 평균 영업 개월 수인 <span id="s_color">${requestScope.oper_month_avg}</span> 보다 작습니다.</li> 
	</c:if>
	<c:if test="${requestScope.close_month.getClose_month() gt requestScope.close_month_avg}">
	<li>또한, 골목상권의 평균 폐업 개월 수인 <span id="s_color">${requestScope.close_month_avg}</span> 보다 큽니다.</li>
	</c:if>
	<c:if test="${requestScope.close_month.getClose_month() le requestScope.close_month_avg}">
	<li>또한, 골목상권의 평균 폐업 개월 수인 <span id="s_color">${requestScope.close_month_avg}</span> 보다 작습니다.</li>
	</c:if>
	</ul>
	<br>
	<hr>
	<h5># job table</h5>
	<span id="s_color">${requestScope.areaCodeName.getArea_coname()}</span> 상권의 총 직장인구 수는 <span id="s_color">${requestScope.all_job_num.getAll_job_num()}</span> 입니다.
	<ul>
	<c:if test="${requestScope.all_job_num.getAll_job_num() gt requestScope.all_job_num_avg}">
	<li>이것은 골목상권의 평균 총 직장인구 수인 ${requestScope.all_job_num_avg} 보다 큽니다.</li>
	</c:if>
	<c:if test="${requestScope.all_job_num.getAll_job_num() le requestScope.all_job_num_avg}">
	<li>이것은 골목상권의 평균 총 직장인구 수인 ${requestScope.all_job_num_avg} 보다 작습니다.</li>
	</c:if>
	</ul>
	<hr>
	
	<h5># living_population</h5>
	<span id="s_color">${requestScope.areaCodeName.getArea_coname()}</span> 상권의 총 상주인구 수는 <span id="s_color">${requestScope.all_living_num.getAll_living_num()}</span> 입니다.
	<ul>
	<c:if test="${requestScope.all_living_num.getAll_living_num() gt requestScope.all_living_num_avg}">
	<li>이것은 골목상권의 평균 총 상주인구 수인 <span id="s_color">${requestScope.all_living_num_avg}</span> 보다 큽니다.</li>
	</c:if>
	<c:if test="${requestScope.all_living_num.getAll_living_num() le requestScope.all_living_num_avg}">
	<li>이것은 골목상권의 평균 총 상주인구 수인 <span id="s_color">${requestScope.all_living_num_avg}</span> 보다 작습니다.</li>
	</c:if>
	</ul>
	<hr>
	
	<h5># apartment</h5>
	<span id="s_color">${requestScope.areaCodeName.getArea_coname()}</span> 상권의 아파트 단지수는 <span id="s_color">${requestScope.apart_num.getApart_num()}</span>입니다.
	<ul>
	<c:if test="${requestScope.apart_num.getApart_num() gt requestScope.apart_num_avg}">
	<li>이것은 골목상권의 평균 총 아파트 단지 수인 <span id="s_color">${requestScope.apart_num_avg}</span> 보다 큽니다.</li>
	</c:if>
	<c:if test="${requestScope.apart_num.getApart_num() le requestScope.apart_num_avg}">
	<li>이것은 골목상권의 평균 총 아파트 단지 수인 <span id="s_color">${requestScope.apart_num_avg}</span> 보다 작습니다.</li>
	</c:if>
	</ul>
	<hr>
	<h5># store</h5>
	<span id="s_color">${requestScope.areaCodeName.getArea_coname()}</span> 상권의 점포수는 <span id="s_color">${requestScope.store_num.getStore_num()}</span> 이고,
	고객님이 선택하신 업종과 유사한 업종의 점포수는 <span id="s_color">${requestScope.sim_store_num.getSim_store_num()}</span> 입니다.
	<span id="s_color">${requestScope.areaCodeName.getArea_coname()}</span> 상권의 개업률은 <span id="s_color">${requestScope.start_rate.getStart_rate()}</span> 이고,
	 개업 점포 수는 <span id="s_color">${requestScope.start_store_num.getStart_store_num()}</span> 입니다. 
	또한, <span id="s_color">${requestScope.areaCodeName.getArea_coname()}</span> 상권의 폐업률은 <span id="s_color">${requestScope.close_rate.getClose_rate()}</span> 이고,
	 폐업 점포 수는 <span id="s_color">${requestScope.close_store_num.getClose_store_num()}</span> 입니다.
	
	
	<c:if test="${requestScope.start_rate.getStart_rate() gt requestScope.start_rate_avg}">
	<span id="s_color">${requestScope.areaCodeName.getArea_coname()}</span> 상권의 개업률은 평균인 <span id="s_color">${requestScope.start_rate_avg}%</span>와 비교했을 때 높고,
	   <c:if test="${requestScope.store_num.getStore_num() gt requestScope.start_store_num_avg}">
	   개업 점포 수는 평균인 <span id="s_color">${requestScope.start_store_num_avg}</span>과 비교했을 때 높습니다.
	   </c:if>
	   <c:if test="${requestScope.store_num.getStore_num() le requestScope.start_store_num_avg}">
	   개업 점포 수는 평균인 <span id="s_color">${requestScope.start_store_num_avg}</span>과 비교했을 때 낮습니다.
	   </c:if>
	</c:if>
	
	<c:if test="${requestScope.start_rate.getStart_rate() le requestScope.start_rate_avg}">
	<span id="s_color">${requestScope.areaCodeName.getArea_coname()}</span> 상권의 개업률은 평균인 <span id="s_color">${requestScope.start_rate_avg}%</span>와 비교했을 때 낮고,
	   <c:if test="${requestScope.store_num.getStore_num() gt requestScope.start_store_num_avg}">
	   개업 점포 수는 평균인 <span id="s_color">${requestScope.start_store_num_avg}</span>개 점포와 비교했을 때 높습니다.
	   </c:if>
	   <c:if test="${requestScope.store_num.getStore_num() le requestScope.start_store_num_avg}">
	   개업 점포 수는 평균인 <span id="s_color">${requestScope.start_store_num_avg}</span>개 점포와 비교했을 때 낮습니다.
	   </c:if>
	</c:if>
	
	<c:if test="${requestScope.close_rate.getClose_rate() gt requestScope.close_rate_avg}">
	<span id="s_color">${requestScope.areaCodeName.getArea_coname()}</span> 상권의 폐업률은 평균인 <span id="s_color">${requestScope.close_rate_avg}%</span>와 비교했을 때 높고,
	   <c:if test="${requestScope.close_rate.getClose_rate() gt requestScope.close_rate_avg}">
	   폐업 점포 수는 평균인 <span id="s_color">${requestScope.close_rate_avg}</span>개 점포와 비교했을 때 높습니다.
	   </c:if>
	   <c:if test="${requestScope.close_rate.getClose_rate() le requestScope.close_rate_avg}">
	   폐업 점포 수는 평균인 <span id="s_color">${requestScope.close_rate_avg}</span>개 점포와 비교했을 때 낮습니다.
	   </c:if>
	</c:if>
	
	<c:if test="${requestScope.close_rate.getClose_rate() lt requestScope.close_rate_avg}">
	<span id="s_color">${requestScope.areaCodeName.getArea_coname()}</span> 상권의 폐업률은 평균인 <span id="s_color">${requestScope.close_rate_avg}%</span>와 비교했을 때 낮고,
	   <c:if test="${requestScope.close_rate.getClose_rate() gt requestScope.close_rate_avg}">
	   폐업 점포 수는 평균인 <span id="s_color">${requestScope.close_rate_avg}</span>개 점포와 비교했을 때 높습니다.
	   </c:if>
	   <c:if test="${requestScope.close_rate.getClose_rate() le requestScope.close_rate_avg}">
	   폐업 점포 수는 평균인 <span id="s_color">${requestScope.close_rate_avg}</span>개 점포와 비교했을 때 낮습니다.
	   </c:if>
	</c:if>
</c:if>

<hr>
</body>
</html>