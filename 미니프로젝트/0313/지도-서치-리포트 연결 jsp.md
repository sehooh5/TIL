## 지도-서치-리포트 연결 jsp

#### map.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://unpkg.com/leaflet@1.5.1/dist/leaflet.css"
   integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
   crossorigin=""/>
<script src="https://unpkg.com/leaflet@1.5.1/dist/leaflet.js"
   integrity="sha512-GffPMF3RvMeYyc1LWMHtK8EbPv0iNZ8/oTtHPx9/cc2ILxQ+u905qIwdpULaqDkyBKgOaB57QTMg7ztg8Jm2Og=="
   crossorigin=""></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=11a0af5b8b304b124286ecc7e4c2099e&libraries=clusterer"></script>
  
</head>
<body>
<div id="map" style="width: 800px; height: 600px;"></div>
<script>
	var mymap = L.map('map').setView([37.535, 127.030], 12);

	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
		maxZoom: 18,
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery <a href="https://www.mapbox.com/">Mapbox</a>',
		id: 'mapbox.streets'
	}).addTo(mymap);
	
	//geojson 파싱하는 부분
	var xhr = new XMLHttpRequest();
	xhr.onload =  function() { 
		console.log(xhr.status)
		if(xhr.status == 200) {
			var str = xhr.responseText;
			var data = JSON.parse(str);
			//console.log(data)
			//console.log("1번 상권 코드명 : "+data.features[0].properties.area_id)
			//console.log("1번 상권 이름 : "+data.features[0].properties.area_coname)
			//console.log("1번 설명 : "+data.features[0].properties.description)
			//console.log(data.features.length)
			for(i=0;i<data.features.length;i++){
				var lat = data.features[i].properties.latitude;
				var lng = data.features[i].properties.longitude;
				var areaId = data.features[i].properties.area_id;
				var area = data.features[i].properties.area_coname;
				var desc = data.features[i].properties.description;
				
				L.marker([lat,lng]).addTo(mymap)
				.bindPopup('<h3>'+area+'</h3>'+desc+'<br>'+'<a href="/backstreet/searchreport?area_id='+areaId+'&area_coname='+area+'" target="_self">자세한 내용 확인</a>').openPopup();
			}
			
		}
 	 };
    xhr.open("GET", "../backstreet/resources/area_for_json.geojson", true);
	xhr.send();   
	
</script>



</body>
</html>
```



#### searchReport.jsp

```jsp
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
		<a href="#" class="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS300007'">의류</a>
		<br>
		<a href="#" class="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS300008'">패션용품</a>
		<a href="#" class="button" onclick="location.href='/backstreet/report?area_id=<%=areaId %>&serv_id=CS300011'">화장품</a>

</body>
</html>
```



#### report.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리포트 페이지</title>
</head>
<body>
<h2> 월매출 금액은 ${requestScope.list } 원 입니다.</h2>
<h2> 리포트가 좌르륵</h2>
<h2> 리포트가 좌르륵</h2>
<h2> 리포트가 좌르륵</h2>
<h2> 리포트가 좌르륵</h2>
<h2> 리포트가 좌르륵</h2>
<h2> 리포트가 좌르륵</h2>
<h2> 리포트가 좌르륵</h2>
<h2> 리포트가 좌르륵</h2>
</body>
</html>
```



