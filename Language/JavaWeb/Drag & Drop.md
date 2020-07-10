# Drag & Drop

- HTML의 엘리먼트들은 draggable 속성 값을 true로 지정해 드래그 가능한 객체 생성

[TOC]



#### 드래그 대상에서 발생하는 이벤트

- dragstart : 드래그 시작할 때 발생
- dragend : 마지막 단계, drop 이벤트 후 발생..드래그가 완료되었다는 것을 뜻함
- drag : 드래그 중에 일어나는 연속적인 이벤트



#### 드롭이 가능한 영역에서 발생하는 이벤트

- dragenter : 드래그 요소가 dropzone 영역에 들어갔을 때 발생하는 이벤트
- dragleave : 드래그 요소가 dropzone 영역에 벗어났을 때 발생하는 이벤트
- 기본 이벤트가 지정되어 있어서 시작할 때 prevent default 로 해제 해줘야 한다
- dragover : 드래그 된 엘리먼트가 dropzone 영역에서 움직일때 발생
- drop : 사용자가 마우스 버튼을 놓을 때 (기본해제,transfer 로 어떤 객체인지 확인)



#### dataTransfer 객체

- 드래그되는 소스객체에서 드롭이 일어나는 타겟 객체로 전달하려는 데이터를 저장하는 객체
- file 속성 : FileList 타입으로 드래그 대상이 파일일 때 사용
- types : StringList 타입으로 전달되는 데이터들의 타입명을 추출할 수 있다.
- clearData(type) : 데이터 삭제
- getData(type) : 데이터 추출 
- setData(type,data) : type 명으로 데이터 저장
- setDragImage(image,x,y) : 드래그 동안 커서를 따라다니는 이미지를 좌표에 출력



#### file API

- file : 정보를 알아내기 위한 기능을 지원함
- fileReader : 파일의 내용을 읽어들이는 기능



#### file 객체

- name : 파일 이름 추출
- type : 파일의 MIME Type정보를 추출하는 기능
- size : 파일의 크기를 추출
- lastModifiedDate : 마지막 수저일 추출



#### fileReader 객체

- readAsText(Blob 또는 File 객체, encoding) : 파읷 내용을 텍스트 문자열로 읽는다.
  두 번째 일자로는 파일의 문자 인코딩을 지정한다.(생략 시는 UTF-8)
- readAsDataURL(Blob 또는 File 객체) : 파일 내용을 data: 으로 시작하는 DataURL 형식의 문자열로 읽는다.
- readAsBinaryString(Blob 또는 File 객체) : 파일의 내용을 바이너리 형식으로 읽는다.
- readAsArrayBuffer(Blob 또는 File 객체) : 파일의 내용을 읽어서 ArrayBuffer 객체에 저장한다.
- abort() : 읽는 작업을 중간에 중지한다.
- result : 읽어들인 파일의 내용 추출하는 용도로 사용되는 속성이다.
- error : 에러 발생 시의 에러 정보를 추출하는 용도로 사용되는 속성이다.



#### FileList

- File 객체들을 담고 있는 배열과 같은 객체이다.
- 파일에 대핚 드래그 앤 드롭에서 dataTransfer 객체를 통해 또는
  `<input type="file">` 태그의 DOM 객체를 통해서 사용되는 files 속성의 타입이다.
- length : 저장된 File 객체들의 크기를 추출할 수 있는 속성이다.
- item(index) : index 위치의 File 객체를 추출하는 기능의 메서드이다.

#### Blob

- 실제 데이터들을 표현하는 객체이다.
- type : 파일의 MIME Type (알 수 없을 때는 null)정보를 추출하는 기능의 속성이다.
- size : 파일의 크기를 추출하는 기능의 속성이다.
- slice(start, length, contentType) : 파일에서 시작위치(start)부터 length만큼 파일의 내용을 Blob 객체로 리턴한다.



#### 수정내용 적용이 안될 때

- 작업자 띄워놓고 새로고침 길게 누르기





---



#### exam1-몬스터 중복이동

```
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Drag and Drop</title>
  <link rel="stylesheet" href="dragdrop.css">
  <script src="dragdrop.js"></script>
</head>
<body>
  <section id="dropbox">
    Drag and drop the image here
  </section>
  <section id="picturesbox">
    <img id="image" src="monster1.gif" >
  </section>
</body>
</html>


//js
var source, drop;
function initiate(){
  source=document.getElementById('image');
  source.addEventListener('dragstart', dragged, false);

  drop=document.getElementById('dropbox');
  //preventDefault() 로 꼭 해제해줘야한다!!!!!!!!!!!!  
  drop.addEventListener('dragover', function(e){console.log("dragover"); e.preventDefault(); }, false);
  drop.addEventListener('drop', dropped, false);
}
function dragged(e){
	console.log("dragstart");
  var code='<img src="'+source.getAttribute('src')+'">';
  e.dataTransfer.setData('Text', code);
}
function dropped(e){
	console.log("drop");
  e.preventDefault();
  drop.innerHTML+=e.dataTransfer.getData('Text');//append ..계속 붙여넣기
}
window.addEventListener('load', initiate);


//css
#dropbox{
  float: left;
  width: 500px;
  height: 300px;
  margin: 10px;
  border: 1px solid #999999;
}
#picturesbox{
  float: left;
  width: 320px;
  margin: 10px;
  border: 1px solid #999999;
}
#picturesbox > img{
  float: left;
  padding: 5px;
}

```





#### exam2- exam1이랑 비교해봐

```
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Drag and Drop</title>
  <link rel="stylesheet" href="dragdrop.css">
  <script>
    var source, drop;
    function initiate(){
	  source=document.getElementById('image');
	  source.addEventListener('dragstart', dragged, false);
	  source.addEventListener('dragend', ending, false);
	  source.addEventListener('drag', drag, false);

	  drop=document.getElementById('dropbox');
	  drop.addEventListener('dragenter', entering, false);
	  drop.addEventListener('dragleave', leaving, false);
	  drop.addEventListener('dragover', function(e){console.log("드레그오버중"); e.preventDefault(); }, false);
	  drop.addEventListener('drop', dropped, false);
	}    
	function entering(e){
		console.log("타겟객체영역에들어감");
	  drop.style.background='rgba(0,150,0,0.2)';
	}
	function leaving(e){
		console.log("타겟객체영역에서나감");
	  drop.style.background='#FFFFFF';
	}
	function ending(e){
	  console.log("드래그종료");
	  elem=e.target;  // this
	  elem.style.visibility='hidden';	//한번 옴기고나면 객체가 안보이게해서 더이상 중복으로 실행 불가  
	  									//display none 으로 해도 된다.
	}
	//img 를 dataTransfer 를 이용해 받는다
	function dragged(e){
	  console.log("드래그시작");
	  var code='<img src="'+source.getAttribute('src')+'">'; 
	  e.dataTransfer.setData('Text', code);
	}
	function drag(e) {
		console.log("드래그중");
	}
	function dropped(e){
		console.log("드롭됨");
	  e.preventDefault();
	  drop.style.background='#FFFFFF';
	  drop.innerHTML+=e.dataTransfer.getData('Text');//중복처리
	}
	window.addEventListener('load', initiate, false);
  </script>
</head>
<body>
  <section id="dropbox">
    Drag and drop the image here
  </section>
  <section id="picturesbox">
    <img id="image" src="monster1.gif">
  </section>
</body>
</html>

```



#### exam3-여러이미지, 황소는 드랍 불가,드롭박스 section

```javascript
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Drag and Drop</title>
  <link rel="stylesheet" href="dragdrop.css">
  <script>
    var drop;
    function initiate(){
	  var images=document.querySelectorAll('#picturesbox > img');
	  //소스 객체마다 dragstart를 적용해줘야하는데 여기서는 for문 사용하여 반복
	  for(var i=0; i<images.length; i++){
	    images[i].addEventListener('dragstart', dragged, false);	  
	  }
	  drop=document.getElementById('dropbox');
	  drop.addEventListener('dragover', function(e){ e.preventDefault(); }, false);
	  drop.addEventListener('drop', dropped, false);
	}
	function dragged(e){
		//elem 선언은? 선언 안하고 바로 사용하는 안좋은 개발 습관
	  elem=e.target; //elem = this 동일 : 이벤트가 발생된 대상 DOM 객체
	  e.dataTransfer.setData('Text', elem.getAttribute('id'));//id 속성을 전달함
	}
	function dropped(e){
	  e.preventDefault();//기본 디폴트 해제 꼭 해줘야함!!!
	  var id=e.dataTransfer.getData('Text');
	  
	  //황소 = image4 ..는 드롭 불가하게 하는 처리
	  if(id!="image4"){
	    var src=document.getElementById(id).src;
	    drop.innerHTML+='<img src="'+src+'">';
		//  drop.innerHTML=id + '가 드롭되었어요...';
	  } else{
	    //drop.innerHTML='not admitted';
	    alert("황소는 드롭 불가");
	  } 
	}
	window.addEventListener('load', initiate, false);
  </script>
</head>
<body>
  <section id="dropbox">
    Drag and drop images here
  </section>
  <!-- 드래그 가능한 소스가 총 4개 -->
  <section id="picturesbox">
    <img id="image1" src="monster1.gif">
    <img id="image2" src="monster2.gif">
    <img id="image3" src="monster3.gif">
    <img id="image4" src="monster4.gif">
  </section>
</body>
</html>
```





#### exam4-수행평가 비슷,dropbox canvas

```javascript
1. 원하는 위치에 그리고 있어서 중복도 가능하고 
2. 드래그 할 때 0,0 위치로 마우스가 지정되면서 이동,정확히 이미지 이동이 가능하게함
(exam3에서는 드래그의 위치가 마우스가 지정한 위치에서 이동 가능)

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Drag and Drop</title>
  <link rel="stylesheet" href="dragdrop.css">
  <script>
    var canvas, drop;
    function initiate(){
	  var images=document.querySelectorAll('#picturesbox > img');
	  for(var i=0; i<images.length; i++){
	    images[i].addEventListener('dragstart', dragged, false);
	    images[i].addEventListener('dragend', ending, false);
	  }

	  drop=document.getElementById('canvas');
	  canvas=drop.getContext('2d');

	  drop.addEventListener('dragover', function(e){ e.preventDefault(); }, false);
	  drop.addEventListener('drop', dropped, false);
	}
	function ending(e){
	  elem=e.target;
	  //---------사라지게하는 방법들-----------
	  //elem.style.visibility='hidden';
	  //elem.style.display='none';
	  //elem.parentNode.removeChild(elem);
	}
	function dragged(e){
	  var elem=e.target;
	  e.dataTransfer.setData('aa', elem.getAttribute('id'));//exam3과 같음
	  e.dataTransfer.setDragImage(e.target, 0, 0);//2. left top 으로 마우스위치가 변경되며 이동
	}
	function dropped(e){	
	  e.preventDefault();
	  var id=e.dataTransfer.getData('aa');//aa 꺼내기
	  var elem=document.getElementById(id);//elem에 담기
		
	  //드롭위치 설정 : 
	  var posx=e.pageX-drop.offsetLeft;//캔버스 박스의  0,0을 맞추기 위해 offset만큼 빼준다.
	  var posy=e.pageY-drop.offsetTop;
	
	  //캔버스에 그림그리듯 drawImage 적용되어 중복 허용
	  canvas.drawImage(elem,posx,posy);//, 70, 70);
	 
	}
	window.addEventListener('load', initiate, false);
  </script>
</head>
<body>
  <section id="dropbox">
    <canvas id="canvas" width="500" height="300"></canvas>
  </section>
  <section id="picturesbox">
    <img id="image1" src="monster1.gif">
    <img id="image2" src="monster2.gif">
    <img id="image3" src="monster3.gif">
    <img id="image4" src="monster4.gif">
  </section>
</body>
</html>
```





#### exam5-ul 문자 드래그, 두부 설정 true 줘야 드래그 가능

```javascript
<!-- 파일명 : drag_drop/simple_dragndrop2.html -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>drag &amp; drop 테스트</title>
<style type="text/css">
.drag{
    float: left;
}
.drag li{
    width: 50px;
    border: 1px dotted blue;
}
.drop{
    float: left;
    margin-left: 50px;
    width: 200px;
    height: 200px;
    border: 1px solid gray;
}
.drop li{
	list-style: none;
}
</style>
<script>
window.onload = function(){	
    setDraggable();
    setDroppable();   
}

function setDraggable(){
    var purchases = document.querySelector(".drag");
    purchases.ondragstart = function(event){
        var id = event.target.id;
        console.log("드래그 시작 : " + id);
        if(event.target.tagName.toLowerCase() == "li") {
	        event.dataTransfer.setData("listItemId", id);
	    }
        
    };
    purchases.ondrag = function(event){
        console.log("드래그 중....");
    };    
}

function setDroppable(){
	var order = document.querySelector(".drop");
	var cart = document.querySelector(".cart");
	
	order.ondragover = function(event) {
		event.preventDefault();
	}
    order.ondrop = function(event){
        console.log("드롭!!!");
        var data = event.dataTransfer.getData("listItemId");
        console.log(data);
        cart.appendChild(document.getElementById(data).cloneNode(true));    
        //cart.appendChild(document.getElementById(data)); 
    };  
}

</script>
</head>
<body>
<div>
    <h1>쇼핑 목록</h1>
    <p><a href="#">마트에서 사야할 목록</a></p>
    <div class="drag">
	    <ul>
	    <!-- draggable true 를 줘야지 드래그가 가능하다,,이미지는 기본이 true -->
	        <li id="item1" >두부</li>
	        <li id="item2" draggable="true">계란</li>
	        <li id="item3" draggable="true">라면</li>
	    </ul>
    </div>
    
    <div class="drop">
        <ul class="cart">
            <li>- 장바구니
        </ul>
    </div>
</div>
</body>
</html>
```



#### exam6-MIME 타입 문서 보여주기, files 속성-fileList 리턴

```javascript
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Drag and Drop</title>
  <link rel="stylesheet" href="dragdrop.css">
  <script>
  var drop;
  function initiate(){
	  drop=document.getElementById('dropbox');
	  drop.addEventListener('dragover', function(e){ e.preventDefault(); }, false);
	  drop.addEventListener('drop', dropped, false);
	}
  
  //file 에 대한 드래그 드롭은 dropped 만 사용하면 된다!!!!!!!!!!!!!!!!!!!!!!!!!!
	function dropped(e){
	  e.preventDefault();//기본 이벤트 핸들러 해제
	  var files=e.dataTransfer.files;
	  var list='';//null 문자열 사용하여 초기화 시켜주고 시작
	  for(var f=0;f<files.length;f++){
	    list+='File: '+files[f].name+' '+files[f].size+' '+files[f].type+'<br>';
	  }
	  drop.innerHTML=list;//drop 위치에 innerHTML 로 넣어주기
	}
	window.addEventListener('load', initiate, false);
  </script>
</head>
<body>
  <section id="dropbox">
    Drag and drop FILES here
  </section>
</body>
</html>
```





#### exam7

```javascript

```



#### exam6

```javascript

```



#### exam6

```javascript

```

