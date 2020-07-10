# Storage API

- storageexam/stmemo/index.html 이 스토리지 API 활용한 기능

- 웹브라우저에 자료를 저장하기 위한 기능으로 로컬스토리지와 세션스토리지로 나뉜다.
- 쿠키와 비슷하지만 차이가 있다.
- 데이터 종류는 제한이 없고 문자열로 저장된다..또한 유일한 이름(키)을 같이 저장한다.



1. **로컬스토리지** : 영구보관
2. **세션스토리지** : 브라우저가 종료될 때까지 보관



#### window.localStorage 와 window.sessionStorage 의 주요 멤버

- length : 스토리지에 저장된 key/value 쌍의 개수를 추출하는 속성이다.

- key(index) : 숫자형 인덱스에 해당하는 key를 리턴한다.

- getItem(key) : 스토리지로 부터 key 에 해당하는 value 를 추출한다.

- setItem(key, value) : 스토리지에 key 에 해당하는 value 를 저장한다.

- removeItem(string key) : 스토리지에 key 에 해당하는 value 를 제거한다.

- clear() : 현재 스토리지의 모든 데이터를 제거한다.

- onstorage : 로컬 스토리지의 내용이 변경될 때릴다 발생되는 이벤트로 로컬 스토리지의 변경 사항을 모니터링 하는 것이 가능하다. StorageEvent 객체가 생성된다.
  [ StorageEvent 객체의 주요 속성 ]
  –key : 추가, 삭제, 변경된 키 이름
  –oldValue : 업데이트되기 전의 값으로 새로 추가된 값이면 null
  –newValue : 새로 업데이트된 값으로 기존 값을 삭제한 경우에는 null

  –url : 변경사항이 발생된 페이지의 URL



- 로컬 스토리지의 데이터 관리
  - 저장
    localStorage.mykey = "myvalue";
    localStorage["mykey"] = "myvalue";
    localStorage.setItem("mykey", "myvalue");
  - 읽기
    var mydata = localStorage.mykey;
    var mydata = localStorage["mykey"];
    var mydata = localStorage.getItem("mykey”);
  - 삭제
    delete localStorage.mykey;
    delete localStorage["mykey"];
    localStorage.removeItem("mykey");
- 세션 스토리지의 데이터 관리
  - 저장
    sessionStorage.mykey = "myvalue";
    sessionStorage["mykey"] = "myvalue";
    sessionStorage.setItem("mykey", "myvalue");
  - 인기
    var mydata = sessionStorage.mykey;
    var mydata = sessionStorage["mykey"];
    var mydata = sessionStorage.getItem("mykey”);
  - 삭제
    delete sessionStorage.mykey;
    delete sessionStorage["mykey"];
    sessionStorage.removeItem("mykey");





---

#### exam1-전부 load 된 후에 실행하는 거(중요!)

```javascript
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<title>간단 메모지</title>

<script>
    var info, msg;
	function saveText() {
		info = document.getElementById("info");
		info.style.display = "block";
		localStorage.setItem("memo", msg.value);
	};

	function pageload() {
		msg = document.getElementById("txtBox"); //textarea 돔객체 가져옴
		msg.value = localStorage.getItem("memo");//localStorage에 있던 메모를 value에 담아 보여줌
	};

	function clr() {
		msg.value = "";//메시지 지워버림
		localStorage.clear();//메시지 지워버림
		info.style.display = "none";
	};
</script>
</head>
<!-- body 에 onload 는 이 페이지 로드 끝나면 pageload()함수 호출하게끔 하는 것 이거 완전중요!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
<body onload="pageload()">
	<h2>간단한 메세지</h2>
	
	<!-- 키보드로 입력하면 생기는 이벤트 발생 onKeyDown, onKeyUp 발생시켜 자동으로 텍스트저장 saveText()-->
	<textarea id="txtBox" onKeyDown="saveText();" onKeyUp="saveText();"
		cols="50" rows="5"></textarea>
	<br />
	<input type="button" value="메모지 비우기" onClick="clr();" />
	
	<!-- 처음에는 display none 속성으로 안뜨게 했다가 saveText() 함수 호출되면 보이게함 -->
	<p id="info" style="display: none;">메모내용이 자동 저장되었습니다.</P>
</body>
</html>
```



#### exam2

```

```



#### exam3

```

```



#### exam4

```

```



#### exam5

```

```



#### exam6

```

```





#### smilenew-저장,삭제, 불러오기

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
var can,smile;

function draw() {
can = document.getElementById("smile");
smile = can.getContext('2d');

smile.beginPath();
smile.arc(200,200,200,45,Math.PI*2,true);
smile.fillStyle = "skyblue";
smile.fill();

smile.beginPath();
smile.arc(120,170,60,0,Math.PI,true);
smile.strokeStyle = "blue";
smile.lineWidth = 3;
smile.lineCap = "round";
smile.stroke();

smile.beginPath();
smile.arc(280,170,60,0,Math.PI*-1,true);
smile.strokeStyle = "blue";
smile.lineWidth = 3;
smile.lineCap = "round";
smile.stroke();

smile.beginPath();
smile.arc(200,250,80,0,Math.PI,false);
smile.strokeStyle = "magenta";
smile.lineWidth = 3;
smile.lineCap = "round";
smile.fillStyle = "pink";
smile.fill();
smile.stroke();
}
window.addEventListener("load", draw, false);

var img;


function sav(){
	can = document.getElementById("smile");
    localStorage.setItem("canvas", can.toDataURL());    
};
function del(){
	can = document.getElementById("smile");
	smile = can.getContext('2d');
	smile.clearRect(0,0,1000,1000);
	/* can.style.display = "none"; */
};
function re(){
	var img = new Image();
	img.src = localStorage.getItem("canvas");
	img.onload = function() {
    	smile.drawImage(img, 0, 0);        
    }
};


</script>

<style>
div{
	text-align : center;
}
</style>
</head>
<body>
<div>
<input type="button" value="저장하기" onClick="sav();" />
<input type="button" value="삭제하기" onClick="del();" />
<input type="button" value="읽어오기" onClick="re();" />
</div>
<br><br>
<div>
<canvas id="smile" width= "500" height = "500"></canvas>
</div>


</body>
</html>
```

