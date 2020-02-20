# d3.js

[TOC]



- 새로운 Dynamic Web Project 생성 : d3edu
- character encoding : UTF-8 설정
- Tomcat 서버에 등록



#### geoloaction.html

```javascript
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
</head>
<body>
   <p id="demo">위치정보를 추출하려면 실행 버튼을 클릭하세요:</p>
   <button onclick="getLocation()">실행</button>
   <script>
   	/* 해당 돔객체 가져옴 */
      var x=document.getElementById("demo");
	  function getLocation() {
		 /* geolocation 갖고 있는지 확인해주는 if */
         if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition,showError);
         	//showPosition : 정보 불러오는데 성공하면 출력되는 함수
         	//showError : 정보 불러오는데 실패하면 출력되는 함수
         }
         else{x.innerHTML=" 이 브라우저는 geolocation을 지원하지 않습니다.";}
      }
	  //성공시 함수 ..position 객체 불러옴
      function showPosition(position) {
          x.innerHTML="위도: " + position.coords.latitude + "<br />경도: " + position.coords.longitude;       
      }
	  //실패시 함수 ..error 객체 불러옴
      function showError(error) {
         switch(error.code) {
            case error.PERMISSION_DENIED:
               x.innerHTML="사용자가 위치 기능 사용을 거부했습니다."
            break;
 
            case error.POSITION_UNAVAILABLE:
            x.innerHTML="위치를 구할 수 없습니다.";
            break;
 
            case error.TIMEOUT:
            x.innerHTML="사용자가 위치 기능 사용을 거부했습니다.";
            break;
           
            case error.UNKNOWN_ERROR:
             x.innerHTML="기타 에러";
             break;
         }
      }
</script>
</body>
</html>
```



#### geoloaction_map.html

```javascript
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.5.1/dist/leaflet.css"
   integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
   crossorigin=""/>
<script src="https://unpkg.com/leaflet@1.5.1/dist/leaflet.js"
   integrity="sha512-GffPMF3RvMeYyc1LWMHtK8EbPv0iNZ8/oTtHPx9/cc2ILxQ+u905qIwdpULaqDkyBKgOaB57QTMg7ztg8Jm2Og=="
   crossorigin=""></script>
</head>
<body>
   <p id="demo">위치정보를 추출하려면 실행 버튼을 클릭하세요:</p>
   <button onclick="getLocation()">실행</button>
   <hr>
   <div id="mapid" style="width: 600px; height: 400px;"></div>
   <script>
      var x=document.getElementById("demo");
	  function getLocation() {
         if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition,showError);
         }
         else {
        	 x.innerHTML=" 이 브라우저는 geolocation을 지원하지 않습니다.";        	
       	 }         
      }
      function showPosition(position) {
          x.innerHTML="위도: " + position.coords.latitude + "<br />경도: " + position.coords.longitude;
          var lat = position.coords.latitude;
          var lng = position.coords.longitude;
          //블럭스타일의 태그 영역을 준비해야 한다(여기서는 div id="mapid")
          //(line1)Leaflet 은 보통 L. 으로 호출한다
          //setView 의 첫번째 아규먼트 : setter 위도 경도
          //		  두번째 아규먼트 : zoom level
          //(line2)해당 부분의 tile을 가져와야 한다
          var mymap = L.map('mapid').setView([lat, lng], 15)
			L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
				maxZoom: 18,
				attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
					'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
					'Imagery <a href="https://www.mapbox.com/">Mapbox</a>',
				id: 'mapbox.streets'
			}).addTo(mymap);//mymap객체에 넣어준다
			//해당 위치를 보여주는 marker 
			//popup 창을 bind 하여 = .bindPopup
			//디폴트 값으로 popup 창을 자동으로 보여줘라 = openPopup()
			L.marker([lat, lng]).addTo(mymap)
				.bindPopup("<b>우리가 있는 곳... 쬠 이상하다ㅜ").openPopup();   
      }
      function showError(error) {
         switch(error.code) {
            case error.PERMISSION_DENIED:
               	x.innerHTML="사용자가 위치 기능 사용을 거부했습니다."
            	break;
 
            case error.POSITION_UNAVAILABLE:
            	x.innerHTML="위치를 구할 수 없습니다.";
           	 	break;
 
            case error.TIMEOUT:
           	 	x.innerHTML="사용자가 위치 기능 사용을 거부했습니다.";
            	break;
            case error.UNKNOWN_ERROR:
            	x.innerHTML="기타 에러";            	
         }
      }
</script>
</body>
</html>

```



#### exam1.html

```javascript
<html>
<head>
  <title>A Leaflet map!</title>
 <link rel="stylesheet" href="https://unpkg.com/leaflet@1.5.1/dist/leaflet.css"
   integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
   crossorigin=""/>
<script src="https://unpkg.com/leaflet@1.5.1/dist/leaflet.js"
   integrity="sha512-GffPMF3RvMeYyc1LWMHtK8EbPv0iNZ8/oTtHPx9/cc2ILxQ+u905qIwdpULaqDkyBKgOaB57QTMg7ztg8Jm2Og=="
   crossorigin=""></script>
  <style>
    #mapid{ height: 100% }
  </style>
</head>
<body>

  <div id="mapid"></div>

  <script>

  // initialize the map
  var mymap = L.map('mapid').setView([42.35, -71.08], 3);

  // load a tile layer
  L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
		maxZoom: 18,
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery <a href="https://www.mapbox.com/">Mapbox</a>',
		id: 'mapbox.streets'
	}).addTo(mymap);

    var xhr = new XMLHttpRequest();
	xhr.onload =  function() { 
		if(xhr.status == 200) {
			var data = JSON.parse(xhr.responseText);
  	  		L.geoJson(data).addTo(mymap).bindPopup(function (layer) {
  	  			//layer 변수에는 geojson 문서안에 각각의 layer 가 들어가게 된다
      			return layer.feature.properties.name;
  			});
		}
 	 };
    xhr.open("GET", "countries.geojson", true);
	xhr.send();

  </script>
</body>
</html>
```



#### exam2 (맵 고정기능)

```javascript
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
  
</head>
<body>
<div id="mapid" style="width: 600px; height: 400px;"></div>
<script>
    //서울시청 위도,경도 : 37.5662952,126.97794509999994
	var mymap = L.map('mapid').setView([37.566, 126.978], 18);

	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
		maxZoom: 18,
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery <a href="https://www.mapbox.com/">Mapbox</a>',
		id: 'mapbox.streets'
	}).addTo(mymap);

	L.marker([37.566, 126.978]).addTo(mymap)
		.bindPopup("<b>안녕하세요! 난 팝업이야..</b><br />여기가 서울시청입니다...").openPopup(); 
//	맵 고정 기능
	mymap.dragging.disable();
</script>



</body>
</html>
```



#### exam3(4개의 지도, setView 대신 {}사용 가능)

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div {
	display : inline-block;  
}
</style>
 <link rel="stylesheet" href="https://unpkg.com/leaflet@1.5.1/dist/leaflet.css"
   integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
   crossorigin=""/>
<script src="https://unpkg.com/leaflet@1.5.1/dist/leaflet.js"
   integrity="sha512-GffPMF3RvMeYyc1LWMHtK8EbPv0iNZ8/oTtHPx9/cc2ILxQ+u905qIwdpULaqDkyBKgOaB57QTMg7ztg8Jm2Og=="
   crossorigin=""></script>
  
</head>
<body>
<div id="mapid1" style="width: 600px; height: 400px;"></div>
<div id="mapid2" style="width: 600px; height: 400px;"></div>
<div id="mapid3" style="width: 600px; height: 400px;"></div>
<div id="mapid4" style="width: 600px; height: 400px;"></div>
<script>
	var mymap1 = L.map('mapid1', { center : [37.566, 126.978], zoom : 18});
	var mymap2 = L.map('mapid2').setView([37.566, 126.978], 1);
	var mymap3 = L.map('mapid3').setView([37.566, 126.978], 5);
	var mymap4 = L.map('mapid4').setView([37.566, 126.978], 10);

	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
		maxZoom: 18,
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery <a href="https://www.mapbox.com/">Mapbox</a>',
		id: 'mapbox.streets'
	}).addTo(mymap1);
	
	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
		maxZoom: 18,
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery <a href="https://www.m/* apbox.com/">Mapbox</a>',
		id: 'mapbox.streets'
	}).addTo(mymap2);
	
	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
		maxZoom: 18,
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery <a href="https://www.m/* apbox.com/">Mapbox</a>',
		id: 'mapbox.streets'
	}).addTo(mymap3); 
	
	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
		maxZoom: 18,
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery <a href="https://www.m/* apbox.com/">Mapbox</a>',
		id: 'mapbox.streets'
	}).addTo(mymap4); 

</script>
</body>
</html>
```



#### exam4(클릭이벤트 발생.. 위도경도 알려줌)

```javascript
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
  
</head>
<body>
<div id="mapid" style="width: 600px; height: 400px;"></div>
<script>
	var mymap = L.map('mapid').setView([37.566, 126.978], 14);

	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
		maxZoom: 18,
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery <a href="https://www.mapbox.com/">Mapbox</a>',
		id: 'mapbox.streets'
	}).addTo(mymap);
	
	function onMapClick(e) {
		 L.popup()	  
		    .setLatLng(e.latlng)
	        .setContent("여기를 클릭했슈!!!  " + e.latlng)
	        .openOn(mymap);
	}
	//mymap에 on 이벤트 발생시켜서 함수 호출
	mymap.on('click', onMapClick);

</script>
</body>
</html>
```



#### exam5(지도위 도형, 마킹)

```javascript
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
  
</head>
<body>
<div id="mapid" style="width: 600px; height: 400px;"></div>
<script>
	var mymap = L.map('mapid').setView([37.566, 126.978], 16);

	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
		maxZoom: 18,
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery <a href="https://www.mapbox.com/">Mapbox</a>',
		id: 'mapbox.streets'
	}).addTo(mymap);
	
	L.marker([37.566, 126.978]).addTo(mymap);
 
	L.circle([37.566, 126.978], 50, {
		color: 'red',
		fillColor: '#f03',
		fillOpacity: 0.4
	}).addTo(mymap).bindPopup("나는 원!");

	L.polygon([
		[37.5672, 126.9735],
		[37.5646, 126.9741],
		[37.5647, 126.9767],
		[37.5661, 126.9768]
	], {
		color: 'blue',
		fillColor: 'skyblue',
		fillOpacity: 0.4
	}).addTo(mymap).bindPopup("나는 다각형");

</script>



</body>
</html>
```



#### exam6(이미지 삽입)

```javascript
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
 <style>
 	b {
 		color : red;
 	}
 </style> 
</head>
<body>
<div id="mapid" style="width: 600px; height: 400px;"></div>
<script>
	var mymap = L.map('mapid').setView([37.5017, 127.0409], 16);

	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
		maxZoom: 18,
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery <a href="https://www.mapbox.com/">Mapbox</a>',
		id: 'mapbox.streets'
	}).addTo(mymap);

	var myIcon = L.icon({
	    iconUrl: '/d3edu/images/duke.png',
	    iconSize: [30, 50]
	});
	//icon 추가 내용
	var content = "<b>우리가 있는 곳!!</b> <img src='/d3edu/images/duke.png' width='20'><hr>여기가 멀티캠퍼스입니다...<br>우리는 1504호에서 공부합니다."
	L.marker([37.5022, 127.0409], {icon: myIcon}).addTo(mymap).bindPopup(content);
   
</script>
</body>
</html>
```



#### exam7(툴팁 출력)

- 툴팁은 간단한 설명으로 마우스만 올려놓으면 출력하게함

```javascript
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
  
</head>
<body>
<div id="mapid" style="width: 600px; height: 400px;"></div>
<script>
    //멀티캠퍼스 위도, 경도 : 37.5017, 127.0409
	var mymap = L.map('mapid').setView([37.5017, 127.0409], 16);

	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
		maxZoom: 18,
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery <a href="https://www.mapbox.com/">Mapbox</a>',
		id: 'mapbox.streets'
	}).addTo(mymap);

	var myIcon = L.icon({
	    iconUrl: '/d3edu/images/duke.png',
	    iconSize: [30, 50]
	});
	//bindTooltip 으로 툴팁 출력
	L.marker([37.5022, 127.0409], {icon: myIcon}).addTo(mymap).bindTooltip("우리가 있는 곳!! <br>여기가 멀티캠퍼스입니다...", {direction: 'right', offset :[20,3]})
</script>



</body>
</html>
```



#### exam8(이미지 여러개, title, layergroup)

- 툴팁 쓸꺼면 헷갈리지 않게 사용 안하는게 좋다

```javascript
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
  
</head>
<body>
<div id="mapid" style="width: 600px; height: 400px;"></div>
<script>
    //멀티캠퍼스 위도, 경도 : 37.5017, 127.0409
	var mymap = L.map('mapid').setView([37.5017, 127.0409], 16);

	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
		maxZoom: 18,
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery <a href="https://www.mapbox.com/">Mapbox</a>',
		id: 'mapbox.streets'
	}).addTo(mymap);

	var myIcon = L.icon({
	    iconUrl: '/d3edu/images/duke.png',
	    iconSize: [30, 50]
	});
	//icon 에 title 주어서 간략한 설명
	var m1 = L.marker([37.5022, 127.0409], {icon: myIcon, title:"나 1번이야!!"});
	var m2 = L.marker([37.5032, 127.0409], {icon: myIcon, title:"나 2번이야!!"});
	var m3 = L.marker([37.5022, 127.0439], {icon: myIcon, title:"나 3번이야!!"});
	var m4 = L.marker([37.5032, 127.0429], {icon: myIcon, title:"나 4번이야!!"});
	var m5 = L.marker([37.5012, 127.0429], {icon: myIcon, title:"나 5번이야!!"});
	//layerGroup 생성해줌
	var group = L.layerGroup([m1, m2, m3, m4, m5])

	group.addTo(mymap);
</script>
</body>
</html>
```



#### exam9(위성지도)

```javascript
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
  
</head>
<body>
<div id="mapid" style="width: 600px; height: 400px;"></div>
<script>
    //서울시청 위도,경도 : 37.5662952,126.97794509999994
	var mymap = L.map('mapid').setView([37.566, 126.978], 18);

	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
		maxZoom: 18,
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery <a href="https://www.mapbox.com/">Mapbox</a>',
		id: 'mapbox.streets-satellite'
	}).addTo(mymap);

	L.marker([37.566, 126.978]).addTo(mymap)
		.bindPopup("<b>안녕하세요! 난 팝업이야..</b><br />여기가 서울시청입니다...").openPopup();   

</script>
</body>
</html>
```



#### exam10(행정동 구분)

```javascript
<html>
<head>
  <title>A Leaflet map!</title>
  <link rel="stylesheet" href="https://unpkg.com/leaflet@1.5.1/dist/leaflet.css"
   integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
   crossorigin=""/>
<script src="https://unpkg.com/leaflet@1.5.1/dist/leaflet.js"
   integrity="sha512-GffPMF3RvMeYyc1LWMHtK8EbPv0iNZ8/oTtHPx9/cc2ILxQ+u905qIwdpULaqDkyBKgOaB57QTMg7ztg8Jm2Og=="
   crossorigin=""></script>
  
  <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
  <style>
    #mapid{ height: 100% }
  </style>
</head>
<body>
	<div id="mapid" style="width: 600px; height: 400px;"></div>
  	<script>
  	var mymap = L.map('mapid').setView([37.566, 126.978], 12);

  	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
			maxZoom: 18,
			attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery <a href="https://www.mapbox.com/">Mapbox</a>',
			id: 'mapbox.streets'
	}).addTo(mymap);
  	
    var xhr = new XMLHttpRequest();
	xhr.onload =  function() { 
		if(xhr.status == 200) {
			var data = JSON.parse(xhr.responseText);
  	  		L.geoJson(data).addTo(mymap).bindPopup(function (layer) {
  	  			//adm_nm 을 불러오는데 geojson 에 가보면 주소값이다
      			return layer.feature.properties.adm_nm;
  			});
		}
 	 };
    xhr.open("GET", "20190403.geojson", true);
	xhr.send();  	
  	</script>
</body>
</html>
```



#### exam11(전국 도 구분)

```javascript
<html>
<head>
  <title>A Leaflet map!</title>
  <link rel="stylesheet" href="https://unpkg.com/leaflet@1.5.1/dist/leaflet.css"
   integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
   crossorigin=""/>
<script src="https://unpkg.com/leaflet@1.5.1/dist/leaflet.js"
   integrity="sha512-GffPMF3RvMeYyc1LWMHtK8EbPv0iNZ8/oTtHPx9/cc2ILxQ+u905qIwdpULaqDkyBKgOaB57QTMg7ztg8Jm2Og=="
   crossorigin=""></script>
  
  <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
  <style>
    #mapid{ height: 100% }
  </style>
</head>
<body>
	<div id="mapid" style="width: 600px; height: 600px;"></div>
  	<script>
  	var mymap = L.map('mapid').setView([37.566, 126.978], 7);

  	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
			maxZoom: 18,
			attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery <a href="https://www.mapbox.com/">Mapbox</a>',
			id: 'mapbox.streets'
	}).addTo(mymap);
  	
  	var xhr = new XMLHttpRequest();
	xhr.onload =  function() { 
		if(xhr.status == 200) {
			var data = JSON.parse(xhr.responseText);
  	  		L.geoJson(data).addTo(mymap).bindPopup(function (layer) {
      			return layer.feature.properties.CTP_KOR_NM;
  			});
		}
 	 };
    xhr.open("GET", "sido.geojson", true);
	xhr.send();  	
  	</script>
</body>
</html>
```



#### exam12(시, 군, 구)

```javascript
<html>
<head>
  <title>A Leaflet map!</title>
  <link rel="stylesheet" href="https://unpkg.com/leaflet@1.5.1/dist/leaflet.css"
   integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
   crossorigin=""/>
<script src="https://unpkg.com/leaflet@1.5.1/dist/leaflet.js"
   integrity="sha512-GffPMF3RvMeYyc1LWMHtK8EbPv0iNZ8/oTtHPx9/cc2ILxQ+u905qIwdpULaqDkyBKgOaB57QTMg7ztg8Jm2Og=="
   crossorigin=""></script>
  
  <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
  <style>
    #mapid{ height: 100% }
  </style>
</head>
<body>
	<div id="mapid" style="width: 600px; height: 400px;"></div>
  	<script>
  	var mymap = L.map('mapid').setView([37.566, 126.978], 10);

  	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
			maxZoom: 18,
			attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery <a href="https://www.mapbox.com/">Mapbox</a>',
			id: 'mapbox.streets'
	}).addTo(mymap);

  	var xhr = new XMLHttpRequest();
	xhr.onload =  function() { 
		if(xhr.status == 200) {
			var data = JSON.parse(xhr.responseText);
  	  		L.geoJson(data).addTo(mymap).bindPopup(function (layer) {
      			return layer.feature.properties.SIG_KOR_NM;
  			});
		}
 	 };
    xhr.open("GET", "sigungu.geojson", true);
	xhr.send();  	
  	</script>
</body>
</html>
```



#### geocoding_map(remove 로 리셋)

```javascript
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
</head>
<body>
	<h1>주소와 좌표 변환 프로그램</h1>
	<button onclick="addToCoord();">주소입력</button>
	<hr>
	<div id="mapid" style="width: 600px; height: 400px;"></div>
	<script>
	var mymap;
	function addToCoord() {
		var address = prompt("주소를입력하세요");
		var lat;
		var lng;
		
		if (address) {		
			var xhr = new XMLHttpRequest();
			xhr.onload =  function() { 
				if(xhr.status == 200) {
					var data = JSON.parse(xhr.responseText);
					lat = data.results[0].geometry.location.lat;
					lng = data.results[0].geometry.location.lng;
					alert("좌표로 : " + lat + ":" + lng);
					if(mymap)
						//한번 리셋해서 위치 지워주고 맵객체 만들어서 그려줌
						mymap.remove();
					mymap = L.map('mapid').setView([lat, lng], 16)
					L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
						maxZoom: 18,
						attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
							'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
							'Imagery <a href="https://www.mapbox.com/">Mapbox</a>',
						id: 'mapbox.streets'
					}).addTo(mymap); 

					L.marker([lat, lng]).addTo(mymap).bindPopup("<b>여기...").openPopup();   
				}
			};
			
			xhr.open("GET", "https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyD8k2DWC_7yFHCrH6LDR3RfITsmWMEqC8c&address="+encodeURIComponent(address), true);
			xhr.send();
		}		
	}
	</script>
</body>
</html>
```



#### exam3()

```javascript

```

