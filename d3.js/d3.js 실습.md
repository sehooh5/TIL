# d3.js 실습

#### myleaflet1.html

```javascript
<!DOCTYPE html>
<html>
<head>
<style>
.button{
	text-align : center;
	margin : auto;
}
h1{
	text-align : center;
}
div#mapid{
	text-align : center;
	margin : auto;
}
</style>
<meta charset="UTF-8">
<title>우리집 그리고 여긴 어디?</title>
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.5.1/dist/leaflet.css"
   integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
   crossorigin=""/>
<script src="https://unpkg.com/leaflet@1.5.1/dist/leaflet.js"
   integrity="sha512-GffPMF3RvMeYyc1LWMHtK8EbPv0iNZ8/oTtHPx9/cc2ILxQ+u905qIwdpULaqDkyBKgOaB57QTMg7ztg8Jm2Og=="
   crossorigin=""></script>
</head>
<body>
	<h1>우리집 혹은 영기버섯 직판매장  <img src='http://70.12.115.175:8000/d3edu/images/mush.png' width='40'></h1>
	<div class="button">
	<button onclick="showMap('탄중로 344');">우리집</button>
	<button onclick="showMap('금수산 궁전');">영기버섯 직판매장</button></div>
	<hr>
	<div id="mapid" style="width: 600px; height: 400px;"></div>
	<script>
	var mymap;
	function showMap(address) {
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
					mymap = L.map('mapid').setView([lat, lng], 15)
					L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
						maxZoom: 18,
						attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
							'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
							'Imagery <a href="https://www.mapbox.com/">Mapbox</a>',
						id: 'mapbox.streets'
					}).addTo(mymap); 
					if(address=="탄중로 344")
						L.marker([lat, lng]).addTo(mymap).bindPopup("<b>요기는 우리 집이에유!!</b><img src='http://70.12.115.175:8000/d3edu/images/baby.png' width='30'><hr>어때요 좋쥬?");
					else if(address=="금수산 궁전")
						L.marker([lat, lng]).addTo(mymap).bindPopup("<b>영기버섯 판매장소!!</b><img src='http://70.12.115.175:8000/d3edu/images/mush.png' width='30'><hr>매진임박!!");
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



#### myleaflet2.html

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
	<div id="mapid" style="width: 600px; height: 400px;"></div>
  	<script>
  	var mymap = L.map('mapid').setView([33.378, 126.521], 10);

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
            //L.geoJson(data) : L 객체 생성
            //addTo(mymap) : mymap 에 추가한다
            //bindPopup(~~~) : layer 객체 생성
  	  		L.geoJson(data).addTo(mymap).bindPopup(function (layer) {		//여기서 feature 는 json 꺼 아님
				var title = layer.feature.properties.description;
  	  			var text = "";
  	  			for(var i=0;i<8;i++)
  	  				text+="<li>"+layer.feature.properties.items[i]+"</li>";
  	  				//배열 출력할때는 입력하고 정의해준 이름을 사용해준다
      			return "<b style='color : red;'>"+title+"</b><hr><ul>"+text+"</ul>";
  			});
		}
 	 };
    xhr.open("GET", "jejumap1.geojson", true);
	xhr.send();  	
  	</script>
</body>
</html>
```

