# AJAX&XML&JASON

[TOC]

### AJAX : Asynchronous JavaScript and XML

- Traditional 통신 방법은 직접 url 혹은 하이퍼링크 등을 통해서 웹페이지 이동..

   **AJAX** 통신은 처리방법이 다르다..어떤 형식이든 받아올 수 있다. text, image, JASON 등등 전부 받아올 수 있다.

- Traditional 에서 웹페이지일부분 갱신을 위해서는 페이지전체 다시 로드..

  **AJAX**에서는 재로드 하지 않고 웹페이지 일부만 갱신, 빠르게 동적 웹페이지를 생성
  
- 등록하는 것과 응답을 보여주는 것을 처리하면 됨

- Cross Origin Resource Sharing(**CORS**)

   - 다른 서버에 직접 접근하여 가져오는 것은 불가능하다.

   - response.addHeader("Access-Control-Allow-Origin", "*");

      HTTP Header 에 CORS 관련된 항목을 추가해주면 가져오기 가능하다.



##### - 동작 과정

1. 이벤트 발생에 의해 이벤트 헬들러 역할의 JavaScript 함수를 호출

2. 핸들러 함수에서 **XMLHttpRequest** 객체를 생성한다.

   요청이 종료되었을 때 처리할 기능을 **콜백 함수**로 만들어 등록한다

3. XMLHttpRequest 객체를 통해 서버에 요청을 보낸다

4. 요청을 받은 서버는 요청 결과를 적당한 데이터로 구성하여 응답한다.

5. XMLHttpRequest 객체에 의해 등록된 **콜백 함수를 호출**하여 응답 결과를 현재 웹페이지에 반영한다.



##### XMLHttpRequest 객체

- 객체 생성 : new XMLHttpRequest()

- open, send([body])는 꼭 써준다

- onreadystatechange : 통신상태가 변화된 타이밍에 호출되는 이벤트 헨들러

  on 이 이벤트헨들러라고 생각하면 됨

![image-20200115104657328](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200115104657328.png)

- readyState 값

![image-20200115114346420](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200115114346420.png)

​			onload 쓰면 readyState 값이 4와 같고

​			onloadstart는 open과 같은 값인 2 가 된다.



#### exam1-AJAX 작동방법

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비 ajax 통신과 ajax 통신 비교</title>
</head>
<body>
<h1>AJAX 통신 테스트</h1>
<a href="content/sample.txt">하이퍼링크로요청</a>
<br><br>
<a href="content/sample.txt"><img src="../images/totoro.png" width="100"></a>
<br><br>
<!-- 버튼에 이벤트 핸들러로 요청, location 사용-->
<button onclick="location.href='content/sample.txt';">버튼을클릭하여요청</button>
<br><br>
<button onclick="requestAjax();">버튼을클릭하여요청(AJAX사용)</button>
<output id="result"></output>
<script>
    //readyState 값 : open 전 = 0 open = 1 // send= 2 // 응답 시작 = 3 // 응답이 다 오면 = 4 
function requestAjax() {
	var req = new XMLHttpRequest();
	var result = document.getElementById("result");
	//readyState 가 바뀔때마다 function 이 호출된다
	req.onreadystatechange = function() {		
       	//readyState 값 확인하는 과정
		alert("req.readyState : "+req.readyState);
        //정상적인 호출인지 확인하는 것 req.status == 200(성공)
		//응답이 다 왔다는 것 확인 req.readyState == 4
		if(req.status == 200 && req.readyState == 4)
			result.innerHTML += req.responseText; 
	}	
	req.open("GET", "content/sample.txt", true);//default true는 비동기방식	
	req.send();	
}
</script>
</body>
</html>
```



#### exam2, textxml.xml

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>JavaScript로 구현하는 Ajax 프로그램-XML</h1>
<hr>
<script>
window.onload = function() {
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		//응답이 잘 왔냐는 것을 확인하는것 request.readyState == 4
		if(request.readyState == 4) {
			if(request.status == 200) {
				var xml = request.responseXML;//xml을 읽을 때는 responseXML..document 즉 dom 객체가 들어있다.
				alert(xml);
				var rootE = xml.getElementsByTagName("testxml");//배열을 리턴한다.
				var output = "";
				alert(rootE[0].childNodes.length);
				//textxml 의 childNodes 는 7개이다..textxml.xml 을 보면 엔터,들여쓰기도 하나의 돔객체로 본다.
				//그래서 1부터 시작하고 2씩 증가하게 한다.
				for(var i=1;i <rootE[0].childNodes.length; i+=2)
					output += "<h2>"+
					//firstChild 는 외동이니까 first 로 소환 ,,텍스트값 읽을때는 nodeValue
					   rootE[0].childNodes[i].firstChild.nodeValue
					   +"</h2>";
				alert(output);
				//결과 넣기
				document.body.innerHTML += output;	   
			}
		}		
	}
	request.open("GET", "content/testxml.xml", true);
	request.send();
}
</script>
</body>
</html>



<?xml version="1.0" encoding="utf-8" ?>
<testxml>
	<name>자바스크립트</name>
	<age>22</age>
	<kind>웹앱개발 전용 OOP 언어</kind>
</testxml>
```



#### exam3-JASON

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>JavaScript로 구현하는 Ajax 프로그램-JSON</h1>
<hr>
<script>
window.onload = function() {
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		/* alert(request.readyState); */
		if(request.readyState == 4) {			
			if(request.status == 200) {			
				//JASON 을 읽으므로 responseText
				var str = request.responseText;
				alert(str);
				//JSON.parse(str);로 자바스크립트 객체로 바꾸어주는 과정
				var jsObj = JSON.parse(str);
				alert(jsObj);
				var output = "";				
				for(var i in jsObj)
					output += "<h2>"+ jsObj[i] +"</h2>";
				alert(output);
				document.body.innerHTML += output;	   
			}
		}		
	}
	request.open("GET", "content/testjson.txt", true);
	request.send();
}
</script>
</body>
</html>
```



#### exam4-weather.jsp, 실습 비슷

```javascript
<!DOCTYPE html>
<html>
    <head>
     <meta charset='utf-8'>
     <title>New Web Project</title>
     <script>	
     window.onload = function() {
 		var request = new XMLHttpRequest();
 		request.onload = function(event) {
 			if (request.status == 200) {
 				var str = request.responseText;
 				var target = document.getElementById('output');
 				target.innerHTML = str;
 			}
 		};
 		request.open('GET', 'content/rss.jsp', true);
 		request.send();
 	};
		
     </script>
    </head>
    <body>
        <h1 style="text-align : center">실시간 날씨 정보입니다.</h1>
        <div id="output" style="width:350px; margin : 10px auto"></div>
    </body>
</html>



<%@ page contentType="text/json; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<c:catch var="err">
	<c:import var="weather"
		url="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4413325600">
	</c:import>
	<x:parse var="wrss" xml="${weather}"></x:parse>

	<c:set var="wfKor">
		<x:out select="$wrss/rss/channel/item/description/body/data/wfKor" />
	</c:set>
 {
     "time" :  "<x:out select="$wrss/rss/channel/pubDate" />", 
     "temp" : "<x:out
		select="$wrss/rss/channel/item/description/body/data/temp" />",
     "wtext" : "${ wfKor }",
     "img" : <c:choose>
		<c:when test='${ wfKor == "구름 많음"}'>
					"images/cloud.png"
				</c:when>
		<c:when test='${ wfKor == "구름 조금"}'>
					"images/cloud_sun.png"
				</c:when>
		<c:when test='${ wfKor == "맑음"}'>
					"images/sun.png"
				</c:when>
		<c:when test='${ wfKor == "비"}'>
					"images/rain.png"
				</c:when>
		<c:when test='${ wfKor == "눈"}'>
					"images/snow.png"
				</c:when>
		<c:otherwise>
					"images/etc.png"
				</c:otherwise>
	</c:choose>
  }         
</c:catch>
<c:if test="${err!=null}">
   ${err}
</c:if>
```



#### exam5-newsjason.jsp, setInterval 뉴스 헤드라인 바꿔주는 기능, 폴링(실시간정보)

```javascript
클라이언트만 요청할 수 있음
서버만이 응답할 수 있음
그때그때 요청하여 응답내용을 바꿔가면서 보여주는 예제 (폴링)

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	height: 100px;
	width: 600px;
	text-align: center;
}
</style>
<script>
window.onload = function() {
	setInterval(function() {//setInterval 으로 계속 요청
		var request = new XMLHttpRequest();
		request.onload = function(event) {
			if (request.status == 200) {
				var str = request.responseText;
				var obj = JSON.parse(str);
				var target = document.getElementById('news');
				target.innerHTML = obj.news;
			}
		};
		request.open('GET', 'content/newsjson.jsp', true);
		request.send();
	}, 2000);//2초
};	
</script>
</head>
<body>
	<table border="1">
		<tr>
			<th>뉴스 내용</th>
		</tr>
		<tr>
			<td id="news">...........................................</td>
		</tr>
	</table>
</body>
</html>
```



#### exam6

```javascript
<!DOCTYPE html>
<html>
    <head>
     <meta charset='utf-8'>
     <title>New Web Project</title>
     <script>	
     window.onload = function() {
 		var request = new XMLHttpRequest();
 		request.onload = function(event) {
 			if (request.status == 200) {
 				var str = request.responseText;
 				var target = document.getElementById('output');
 				target.innerHTML = str;
 			}
 		};
 		request.open('GET', 'http://70.12.115.160:8000/edu/ajaxexam/content/rss.jsp', true);
 		request.send();
 	};
		
     </script>
    </head>
    <body>
        <h1 style="text-align : center">Ajax 로 다른 사이트의 내용을 끌어옵니다.</h1>
        <div id="output" style="width:350px; margin : 10px auto"></div>
    </body>
</html>
```



#### exam7

```javascript
<!DOCTYPE html>
<html>
    <head>
     <meta charset='utf-8'>
     <title>New Web Project</title>
     <script>	
     window.onload = function() {
 		var request = new XMLHttpRequest();
 		request.onload = function(event) {
 			if (request.status == 200) {
 				var str = request.responseText;
 				var target = document.getElementById('output');
 				target.innerHTML = str;
 			}
 		};
 		request.open('GET', 'http://70.12.115.160:8000/edu/ajaxexam/content/rss2.jsp', true);
 		request.send();
 	};
		
     </script>
    </head>
    <body>
        <h1 style="text-align : center">Ajax 로 다른 사이트의 내용을 끌어옵니다.</h1>
        <div id="output" style="width:350px; margin : 10px auto"></div>
    </body>
</html>
```



#### exam8-로그인, longin.jsp

```javascript
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Ajax테스트</title>
      <script>
      //윈도우에 로드이벤트 
	  window.addEventListener("load", function() {
	     document.getElementById("loginb").onclick = handleButtonPress;
	  });
	  var xhr;
	  var id;
	  function handleButtonPress(e){
	     e.preventDefault();//기본 이벤트 핸들러 해제...하고 AJAX 로 하겟다
	     xhr =  new XMLHttpRequest();
	     
	     //돔객체 불러오는 부분
	     form = document.querySelector("#loginf");
	     id = document.querySelector("#id");
	     var passwd = document.querySelector("#passwd");
	     
	     //쿼리문자열 만드는 부분,,,,encodeUTIComponent 는 영문자만 보내고 
	     var queryString = "id="+encodeURIComponent(id.value)+"&passwd="+passwd.value;
	     
	     alert(queryString);
	     xhr.addEventListener("load", handleResponse);//다 로드되면  handleResponse 함수 실행
	     
	     
	     //POST 방식으로 전달하는 방법
	     xhr.open("POST", form.action, true);//이전에는 "GET"을 사용했었음
	     xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	     xhr.send(queryString);//POST 방식 일때는 send 에 아규먼트를 줘서 서버에 전달한다.
	  }
	  function handleResponse() {
		 var jsonObj = JSON.parse(xhr.responseText);
	     var output = document.querySelector("output");
		 if (jsonObj.result == "ok") {
			 output.textContent="로그인 성공!!";
			 output.style.color = "blue";
		 } else if (jsonObj.result == "fail") {
		  	 output.textContent="로그인 실패!!";
		  	 output.style.color = "red";
			 form.reset();//실패하면 form 에 있는 내용 없애주는 기능
			 id.focus();//아이디에 포커스도 해줌
         }
	  }								
      </script>
   </head>
   <body>
	  <h3>계정과 패스워드를 입력해 주세요.</h3>
	  <form id="loginf" action="/edu/ajaxexam/content/login.jsp">
		  <table>
			  <tr>
				 <td><label for="id">계정</label></td>
				 <td><input type="text" id="id" name="id" required/></td>
			  </tr>
			  <tr>
				 <td><label for="passwd">패스워드</label></td>
				 <td><input type="password" id="passwd" name="passwd" required/></td>
			  </tr>
			  <tr>
				 <td><input type="submit" id="loginb" value="로그인" /></td>
				 <td><output></output></td>
			 </tr>
		  </table>
	  </form>
   </body>
</html>
```



#### exam9-

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax테스트</title>
</head>
<body>
<h2 onclick="getImage();">이 행을 클릭해요. Ajax로 이미지를 요청하고 출력해요....</h2>
<script>
function getImage() {
	var xhr = new XMLHttpRequest();	
	//다른점..responseType 이 바이너리 형이다.img,video,audio 모두 가능!
	xhr.responseType = 'blob';
	xhr.onload = function(e) {
  		if (this.status == 200) {
    		var blob = e.target.response;
    //타입이 지정되어있으면 response 를 사용 가능하다.		
    //xhr.response 도 가능..this도 가능 cuz HMLHttpRequest객체를 의미하기 때문
    
    		//새로운 태그 img 만듬
    		var img = document.createElement('img');
    		img.width=100;
    		img.height=100;
    		img.onload = function(e) {
     	 		URL.revokeObjectURL(img.src); 
     	 			//메모리에 복사되어있는 URL을 해제시켜주는 메서드
    		};  
    		img.src = URL.createObjectURL(blob);
    					//이미지 내용으로 가상 URL만들어주는 메서드
    		document.body.appendChild(img);   
    				//body태그에 img를 계속 마지막 자식을 append 하여 누적한다.
  		}
	};
	xhr.open('GET', '/edu/images/duke_luau.png', true);
	xhr.send();
}
</script>
</body>
</html>
```



#### ajaxlab1.html

```javascript
<!DOCTYPE html>
<html>
    <head>
     <meta charset='utf-8'>
     <title>날씨 정보</title>
     <style>
     img{
     	width : 200px;
     	height : 200px;
     }
     </style>
     <script>	
     window.onload = function() {
 		var request = new XMLHttpRequest();
 		request.onload = function(event) {
 			/* alert("들어옴"); */
 			if (request.status == 200) {
 				/* alert("200 들어옴"); */
 				var str = request.responseText;
 				/* alert(str); */
 				var jsObj = JSON.parse(str);
 				/* alert(jsObj); */
 				var textArea = document.getElementById('output');
 				var imgArea = document.getElementById('image');
 				textArea.innerHTML = "<h2>기준 시간 : "+ jsObj.time +"</h2>"+
 								"<h2>기온 : "+ jsObj.temp +"도</h2>";
 				imgArea.innerHTML = "<img src=\"../"+jsObj.img+"\">";
 			}
 		};
 		/* alert("오픈시작"); */
 		request.open('GET', 'content/weather.jsp', true);
 		/* alert("센드 시작"); */
 		request.send();
 	};
		
     </script>
    </head>
    <body>
        <h1>오늘의  날씨 정보</h1>
        <hr>
        <div id="output"></div>
        <figure id="image" style="width:200px; height:200px;"></figure>
    </body>
</html>
```



#### ajaxlab2.html

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<title>주소와 위도경보</title>
<script>
function startPrompt() {

		var add = prompt("주소를 입력하세요");
		/* alert(add); */
		var add2 = encodeURIComponent(add);
		/* alert(add2); */
		var request = new XMLHttpRequest();
		request.onload = function() {
			/*  alert("들어옴");  */
			if (request.status == 200) {
				/* alert("200 잘 읽어옴");  */
				var str = request.responseText;
				/* alert(str+"객체 잘 읽어냄");  */
				var jsObj = JSON.parse(str);
				/* alert(jsObj+"객체 자바로 잘 바꿔줬음");  */
				var textArea = document.getElementById('output');

				textArea.innerHTML = "변환된 위도와 경도"
						+ jsObj.results[0].geometry.location.lat + " : "
						+ jsObj.results[0].geometry.location.lng;

			}
		};
		/* alert("오픈시작"); */
		request
				.open(
						'GET',
						'https://maps.googleapis.com/maps/api/geocode/json?address='
								+ add2
								+ "&key=AIzaSyD8k2DWC_7yFHCrH6LDR3RfITsmWMEqC8c",
						true);
		/* alert("센드 시작");  */
		request.send();
	};
</script>
</head>
<body>
	<h1>주소와 위도경도 변환 서비스</h1>
	<hr>
	<button onclick="startPrompt();" id="btn">주소입력버튼</button>
	<br>
	<h2 id="output"></h2>

</body>
</html>

```



### XML : Extensible Markup Language

- XML 선언부를 제외하고는 기존 HTML5의 기본 구조와 거의 유사
- XML 선언부
  - 반드시 맨 앞에 명세, XML 문서 유형을 지정
  - XML 문서 구조에 정의한 DTD 또는 XML Schema 선언 스타일을 정의한 CSS연결에 대한 선언도 명세
- 하나의 최상위 엘리먼트의 시작태그로 시작해서 종료태그로 끝남





### JASON(대세)

- jason.org 참고

- 객체 리터럴 만드는 방식으로 문서를 작성한다. 중괄호 사용. JavaScript 와 호환성굿

- **key**는 반드시 문자, 문자열은 반드시 **더블인용부호**를 사용한다

  **value** 는 숫자는 안주고 문자열은 더블

![image-20200115101647471](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200115101647471.png)

