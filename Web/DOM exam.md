# DOM exam

[TOC]

### exam1

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>컨텐트1</h1>
<h1>컨텐트2</h1>
<h1>컨텐트3</h1>
<h1>컨텐트4</h1>
<h2>컨텐트5</h2>
<script src="../util.js"></script>
<script>
var h1dom = document.getElementsByTagName("h1"); //추출되는 값이 배열객체라고 생각하면 된다
write(h1dom.length,"h3");
for(var i = 0; i<h1dom.length;i++)	//모든 h1dom 의 배열 원소들을 뽑아서 출력하겠닭!
	writeColor(h1dom[i].textContent,"h4","red");
var h2dom = document.getElementsByTagName("h2");
writeColor(h2dom[0].textContent,"h2","green");
writeColor(h1dom,"h2","blue"); //[object HTMLCollection]
writeColor(h2dom[0],"h2","blue"); //[object HTMLHeadingElement]
var h5dom = document.getElementsByTagName("h5");
writeColor(h5dom,"h2","magenta"); //[object HTMLCollection]
writeColor(h5dom.length,"h2","magenta"); //1

</script>
</body>
</html>
```





### exam2

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="../util.js"></script>
<script>
//원래 찾으려는 태그들보다 위에 작성되어있으면 사용이 불가하다. 따라서..window.onload 사용
//문서가 다 읽혔을 때(렌더링 후) 수행시키게 된다
//이벤트 핸들러 함수 안에서 document.write 는 이전 출력 했던 것들은 다 없애버린다
window.onload = function(){	
var dom1 = document.getElementById("t1");
console.log(dom1);
console.log(dom1.textContent);
var dom2 = document.getElementById("t3");
console.log(dom2);
var dom3 = document.getElementById("t4");
console.log(dom3);
var dom4 = document.getElementById("t5");
console.log(dom4);
console.log(dom4.getAttribute("src")); //입력학 url 출력 ../../images/totoro.png
console.log(dom4.src); //절대url 추출http://localhost:8000/edu/images/totoro.png
var dom5 = document.getElementById("p");
//태그를 태그로 인식하게하려면 innerHTML사용해 빈 dom객체에 받아서 입력하여 출력한다!
dom5.innerHTML = "<h2>"+new Date().toLocaleDateString()+"</h2>"; 
}
</script>
<h1 id="t1">컨텐트1</h1>
<h2 id="t2">컨텐트5</h2>
<p id="t3">컨텐트2</p>
<div id="t4">컨텐트2</div>
<img id="t5" src="../../images/totoro.png" width="100">
<hr>
<!-- output 컨텐츠 없지만 자바스크립트로 추가할예정 // 자바스크립츠를 표현하는 용도-->
<output id="p"></output> 
<hr>


</body>
</html>
```





### exam3

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../util.js"></script>
</head>
<body>
<h1 id="test">DOM객체를 찾자</h1>
<h1>ㅋㅋㅋㅋㅋㅋㅋ</h1>
<hr>
<script>
var dom = document.getElementsByTagName("h1");
write(dom, "h2");
write(dom[1], "h2");
write(dom[1].textContent, "h2"); // innerText
write(dom[1].innerHTML, "h2");
hr();
dom = document.getElementById("test");
write(dom, "h2");
write(dom.textContent, "h2");
window.setTimeout(function() {	//setTimeout(함수,밀리세컨) 은 한번 작용
	dom.innerHTML = "오늘은 불금";
	dom.style.color ="red";
	dom.style.backgroundColor ="lime";
}, 5000);
</script>
</body>
</html>
```





### exam4-시간정보출력,button

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>시간정보 출력하기</h1>
<hr>
<button onclick="startTime()">시간을 알려주라옹...</button>
<button onclick="stopTime()">시간 출력을 종료하라옹...</button>
<output></output>	<!-- 비워놓고 자바스크립트 실행 시 채워 넣겠다는 의미 -->
<script>
/* 이벤트 핸들러 안에서 document.write 쓰면 전부 지워버림 */
var target = document.getElementsByTagName("output")[0]; 
function startTime() { 
	var d = new Date();
	target.innerHTML = "<h2>"+d.toLocaleString()+"</h2>";
}
function stopTime() {
	target.innerHTML = "";
}
</script>
</body>
</html>
```



### exam4_1-setTimeout의 인터벌 화

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>시간정보 출력하기</h1>
<hr>
<button onclick="startTime()">시간을 알려주라옹...</button>
<button onclick="stopTime()">시간 출력을 종료하라옹...</button>
<output></output>	<!-- 비워놓고 자바스크립트 실행 시 채워 넣겠다는 의미 -->
<script>
/* 이벤트 핸들러 안에서 document.write 쓰면 전부 지워버림 */
var target = document.getElementsByTagName("output")[0]; 
function startTime() { 
	var d = new Date();
	target.innerHTML = "<h2>"+d.toLocaleString()+"</h2>";
}
function stopTime() {
	target.innerHTML = "";
}
</script>
</body>
</html>
```



### exam4_2 - setInterval

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>시간정보 출력하기</h1>
<hr>
<button onclick="startTime()">시간을 알려주라옹...</button>
<button onclick="stopTime()">시간 출력을 종료하라옹...</button>
<output></output>
<script>
// setInterval()을 사용하여 동일한 기능 구현
var target = document.getElementsByTagName("output")[0];
var setId;
function startTime() {
	setId = window.setInterval(function(){
		var d = new Date();
		target.innerHTML = "<h2>"+d.toLocaleString()+"</h2>";
	}, 1000);
}
function stopTime() {
	//target.innerHTML = "";
	window.clearInterval(setId);
}
</script>
</body>
</html>
```



### exam5 - 인라인, 고전, 표준 이벤트 모델

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>세 가지 이벤트 모델</h1>
<hr>
<h1 onclick="f1();">인라인 이벤트 모델</h1>
<hr>
<h1 id="t1">고전 이벤트 모델</h1>
<hr>
<h1 id="t2">표준 이벤트 모델</h1>
<hr>
<script>
	function f1() {
		//querySelectorAll 오든 h1  찾아라
		//[1] 는 두번째, 첫번째 h1 잇기 때문
		alert(document.querySelectorAll('h1')[1].textContent);	
	}
	//querySelector 는 한개만 찾음
	var dom2 = document.querySelector('#t1');	
	//고전 이벤트 모델,,id속성을 많이 사용함
    var dom3 = document.querySelector('#t2');	
	//표준 이벤트 모델,,,안드로이드에서 사용
	function f2() {	
		alert(dom2.textContent);
	}
	function f3() {
		alert(dom3.textContent);
	}    
    dom2.onclick = f2; //이벤트 핸들러로 함수를 발생시킴,,괄호를 주면 안됨
    dom3.addEventListener("click", f3); 
</script>
</body>
</html>
```



### exam5_1 - 함수가 한개로 바뀌고 this 사용

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>세 가지 이벤트 모델</h1>
<hr>
<h1 onclick="f1();">인라인 이벤트 모델</h1>
<hr>
<h1 id="t1">고전 이벤트 모델</h1>
<hr>
<h1 id="t2">표준 이벤트 모델</h1>
<hr>
<script>
    console.log(this);
	function f1() {
		alert(document.querySelectorAll('h1')[1].textContent);
	}
	var dom2 = document.querySelector('#t1');
    var dom3 = document.querySelector('#t2');
    // 이벤트핸들러 안에서 타겟 지정하려면 this 혹은 e.target 사용하면 됨
	function f2(e) {	//함수 요소에 e 지정!
		alert(e.target.textContent); //이렇게하면 두번 경고 발생
		alert(this.textContent);
		console.log(this);	//이벤트 발생하는 멤버를 보여줌
	} 
    dom2.onclick = f2;	
    dom3.addEventListener("click", f2);	
</script>
</body>
</html>
```



### exercise11_1 - 인라인 이벤트 모델

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2></h2>
	<button onclick = "timeR()">빨강색</button>
	<button onclick = "timeB()">파랑색</button>
	<button onclick = "timeY()">노랑색</button>
	
	<script>
	var target = document.getElementsByTagName("h2")[0];	//[0]주는게 중요하다!!!
	var d = new Date();
	target.innerHTML = "오늘은 "
		+d.getFullYear()+"년 "+d.getMonth()+1+"월 "+d.getDate() + "일 입니다.";
	
	function timeR(){
		target.style.color = "red";
	}
	function timeB(){
		target.style.color = "blue";
	}
	function timeY(){
		target.style.color = "yellow";
	}
	
	</script>
</body>
</html>

//total이란 함수로 묶고 인라인 이벤트 핸들러(button onclick)에 this 사용하여 출력//
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2></h2>
	<!-- 여기서 this는 객체,,,window 아님!!그래서 변할수 잇음 -->
	<button onclick = "total(this,'red')">빨강색</button> 
	<button onclick = "total(this,'blue')">파랑색</button>
	<button onclick = "total(this,'yellow')">노랑색</button>
	
	<script>
	var target = document.getElementsByTagName("h2")[0];	//[0]주는게 중요하다!!!
	var d = new Date();
	target.innerHTML = "오늘은 "
		+d.getFullYear()+"년 "+d.getMonth()+1+"월 "+d.getDate() + "일 입니다.";
	
	var old;
	var total = function (me,color){
		if(old)
			old.style.backgroundColor = "white";
		me.style.backgroundColor = color;	//배경색 바꿔줌
		target.style.color = color;
		old = me;
	}
	</script>
</body>
</html>
```



### exercise11_2 - 전역적 이벤트 모델

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2></h2>
	<button id = "t1" style="color:red;">빨강색</button>
	<button id = "t2" style="color:blue;">파랑색</button>
	<button id = "t3" style="color:yellow;">노랑색</button>
	
	<script>
	var target = document.getElementsByTagName("h2")[0];
	var d = new Date();
	target.innerHTML = "오늘은 "
		+d.getFullYear()+"년 "+d.getMonth()+1+"월 "+d.getDate() + "일 입니다.";
	
	var dom1 = document.querySelector('#t1');
	var dom2 = document.querySelector('#t2');
	var dom3 = document.querySelector('#t3');
	function colorR(){
		target.style.color = "red";
	}
	function colorB(){
		target.style.color = "blue";
	}
	function colorY(){
		target.style.color = "yellow";
	}
	dom1.onclick = colorR;
	dom2.onclick = colorB;
	dom3.onclick = colorY;
	
	</script>
</body>
</html>


/////////////
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2></h2>
	<button id="red">빨강색</button>
	<button id="blue">파랑색</button>
	<button id="yellow">노랑색</button>
	
	<script>
		var h2dom = document.getElementsByTagName("h2")[0];
		var dateInfo = new Date();
		h2dom.textContent = "오늘은 "+dateInfo.getFullYear() + "년 "
							+(dateInfo.getMonth()+1)+"월 " 
							+dateInfo.getDate()+ "일입니다.";
		
		var btns = document.getElementsByTagName("button");

		var total = function(){
			h2dom.style.color = this.id;
		};		
		
		for(var i in btns) 	
			btns[i].onclick = total;
	</script>
</body>
</html>
```



### exercise11_3 - 표준 이벤트 모델

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2></h2>
	<button id = "t1">빨강색</button>
	<button id = "t2">파랑색</button>
	<button id = "t3">노랑색</button>
	
	<script>
	var target = document.getElementsByTagName("h2")[0];
	var d = new Date();
	target.innerHTML = "오늘은 "
		+d.getFullYear()+"년 "+d.getMonth()+1+"월 "+d.getDate() + "일 입니다.";
	
	var dom1 = document.querySelector('#t1');
	var dom2 = document.querySelector('#t2');
	var dom3 = document.querySelector('#t3');
	function colorR(){
		target.style.color = "red";
	}
	function colorB(){
		target.style.color = "blue";
	}
	function colorY(){
		target.style.color = "yellow";
	}
	dom1.addEventListener("click",colorR);
	dom2.addEventListener("click",colorB);
	dom3.addEventListener("click",colorY);
	
	</script>
</body>
</html>

/////////////////
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2></h2>
	<button id="red">빨강색</button>
	<button id="blue">파랑색</button>
	<button id="yellow">노랑색</button>
	
	<script>
		var h2dom = document.getElementsByTagName("h2")[0];
		var dateInfo = new Date();
		h2dom.textContent = "오늘은 "+dateInfo.getFullYear() + "년 "
							+(dateInfo.getMonth()+1)+"월 " 
							+dateInfo.getDate()+ "일입니다.";
		
		var btns = document.getElementsByTagName("button");

		var total = function(colorname){
			h2dom.style.color = this.id;
		};

		for (var i = 0 ; i < btns.length ; i++){
			btns[i].addEventListener("click", total);
		}
	</script>
</body>
</html>
```



### exam6 - 고전과 표준의 차이

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>고전 이벤트 모델과 표준 이벤트 모델의 차이</h1>
<hr>
<button>고전 이벤트 모델</button>
<button>표준 이벤트 모델</button>
<script>
    //전부다 꺼내는거 querySelectorAll~!!!!
	var doms = document.querySelectorAll("button");
	var dom1 = doms[0];
	var dom2 = doms[1];
	//고전은 속성에 값을 넣는 것이므로 ,, 두개 등록 안됨!! 마지막꺼만 남음
	dom1.onclick = function () {
								 alert("첫 번째 핸들러 수행(고전)");
							  };
	dom1.onclick = function () {					
								 alert("두 번째 핸들러 수행(고전)");
							  };
						
	//표준은 여러개 수행 가능하다!!
	dom2.addEventListener("click", 
			                 function () { alert("첫 번째 핸들러 수행(표준)");});
	dom2.addEventListener("click", 
            function () { alert("두 번째 핸들러 수행(표준)");});

</script>
</body>
</html>
```



### exam7

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>자바스크립트의 이벤트 모델</h1>
<hr>
<button onmouseover="test1();">인라인이벤트모델</button>
<button id="b1">고전이벤트모델</button>
<button id="b2">표준이벤트모델</button>
<script>
//인라인은 매번 실행, 고전,표준은 한번 실행하고 종료
function test1() {
	alert("인라인이벤트모델 버튼 클릭");
}
function test2() {
	alert("고전이벤트모델 버튼 클릭");
	btn1.onmouseover = null; //고전,, 핸들러 종료
}
function test3() {
	alert("표준이벤트모델 버튼 클릭");	//표준,, 핸들러 종료
	btn2.removeEventListener("mouseover", test3); 
}
var btn1 = document.querySelector("#b1");
var btn2 = document.getElementById("b2");
btn1.onmouseover = test2;  //마우스가 버튼위에 올라가기만 하면 실행
btn2.addEventListener("mouseover", test3);
</script>
</body>
</html>
```



### exercise12_1 - 인라인, CSS, 초기화, this 이용해 반복

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
h2{
	text-align : center;
}
div{
   text-align : center;/* button 을 div로 묶어서 가운데 정렬 */
}
article {
	width: 30%;
	border: 1px solid skyblue;
	border-radius: 15px;
	margin : 15px auto;
	padding: 20px;
	box-align : center;
	text-align : center;
}
</style>
</head>
<body>
	<h2>구구단</h2>
<!-- 	<div class="bt">
		<input type="button" value="1단" onclick = "dan(this,'1')" >
		<input type="button" value="2단" onclick = "dan(this,'2')" >
		<input type="button" value="3단" onclick = "dan(this,'3')" >
		<input type="button" value="4단" onclick = "dan(this,'4')" >
		<input type="button" value="5단" onclick = "dan(this,'5')" >
		<input type="button" value="6단" onclick = "dan(this,'6')" >
		<input type="button" value="7단" onclick = "dan(this,'7')" >
		<input type="button" value="8단" onclick = "dan(this,'8')" >
		<input type="button" value="9단" onclick = "dan(this,'9')" >
	</div> -->
	<div>
	<button onclick = "dan(this,'1')" >1단</button>
	<button onclick = "dan(this,'2')" >2단</button>
	<button onclick = "dan(this,'3')" >3단</button>
	<button onclick = "dan(this,'4')" >4단</button>
	<button onclick = "dan(this,'5')" >5단</button>
	<button onclick = "dan(this,'6')" >6단</button>
	<button onclick = "dan(this,'7')" >7단</button>
	<button onclick = "dan(this,'8')" >8단</button>
	<button onclick = "dan(this,'9')" >9단</button>
	</div>
	<article>
	</article>
	<script>
 	var target = document.getElementsByTagName("article")[0];

	function dan(i,num){
		target.innerHTML="";
			for(var i=1;i<10;i++)
				//+= 는 append로 결과를 계속 얹는다.
				target.innerHTML += num+"x"+i+"="+num*i+"<br>";	//why??
	}
	</script>
</body>
</html>
```



### exercise12_2 - 고전, CSS, 초기화, this 이용해 반복

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
h2{
	text-align : center;
}
div{
   text-align : center;/* button 을 div로 묶어서 가운데 정렬 */
}
article {
	width: 30%;
	border: 1px solid skyblue;
	border-radius: 15px;
	margin : 15px auto;
	padding: 20px;
	box-align : center;
	text-align : center;
}
</style>
</head>
<body>
	<h2>구구단</h2>
	<div>
	<button id="1">1단</button>
	<button id="2">2단</button>
	<button id="3">3단</button>
	<button id="4">4단</button>
	<button id="5">5단</button>
	<button id="6">6단</button>
	<button id="7">7단</button>
	<button id="8">8단</button>
	<button id="9">9단</button>
	</div>
	<article>
	</article>
	<script>
 	var target = document.getElementsByTagName("article")[0];
 	
 	var bt = document.getElementsByTagName("button");
 	
	function dan(){
		target.innerHTML=null;
			for(var i=1;i<10;i++)
				target.innerHTML += this.id+"x"+i+"="+this.id*i+"<br>";	
	}
	
	for(var j in bt)
		bt[j].onclick = dan;
	</script>
</body>
</html>
```



### exercise12_3 - 표준, CSS, 초기화, this 이용해 반복

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
h2{
	text-align : center;
}
div{
   text-align : center;/* button 을 div로 묶어서 가운데 정렬 */
}
article {
	width: 30%;
	border: 1px solid skyblue;
	border-radius: 15px;
	margin : 15px auto;
	padding: 20px;
	box-align : center;
	text-align : center;
}
</style>
</head>
<body>
	<h2>구구단</h2>
	<div>
	<button id="1">1단</button>
	<button id="2">2단</button>
	<button id="3">3단</button>
	<button id="4">4단</button>
	<button id="5">5단</button>
	<button id="6">6단</button>
	<button id="7">7단</button>
	<button id="8">8단</button>
	<button id="9">9단</button>
	</div>
	<article>
	</article>
	<script>
 	var target = document.getElementsByTagName("article")[0];
 	
 	var bts = document.querySelectorAll("button");
 	
	function dan(){
		target.innerHTML="";
			for(var i=1;i<10;i++)
				target.innerHTML += this.id+"x"+i+"="+this.id*i+"<br>";	
	}
	
	for(var j=0;j<bts.length;j++)
		bts[j].addEventListener("click", dan);
	</script>
</body>
</html>
```



### exam8

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>디폴트 이벤트 핸들러</h1>
<hr>
<a href="http://www.w3schools.com/" onclick="test1(); ">HTML5 학습하기(인라인)</a><hr>
<!-- <a href="http://www.w3schools.com/" onclick="test1(); return false;">HTML5 학습하기(인라인)</a><hr> -->
<a id="t1" href="http://www.w3schools.com/">HTML5 학습하기(고전)</a><hr>
<a id="t2" href="http://www.w3schools.com/">HTML5 학습하기(표준)</a>
<script>
function test1() {
	alert("인라인이벤트모델 버튼 클릭");
}
function test2() {
	alert("고전이벤트모델 버튼 클릭");	
/* 	return false; */	//인라인과 고전은 return false 로 실행을 안시킬 수 있다.
}
function test3(e) {
/* 	e.preventDefault();	//preventDefault 로 실행 안시킬 수 있다. */
	alert("표준이벤트모델 버튼 클릭");	
}
var link1 = document.querySelector("#t1");
var link2 = document.getElementById("t2");
link1.onclick = test2;  // 고전
link2.addEventListener("click", test3);



</script>
</body>
</html>
```



### exam9 - 이벤트 버블링

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	section {
		border : 1px solid lime;	
		padding :10%;
		margin : 0px auto;	
	}
	div {
		border : 1px solid blue;	
		width : 80%;
		padding :10%;
		margin : 0px auto;	
	}
	h1 {
		border : 1px solid red;	
		width : 80%;
		padding :10%;
		margin : 0px auto;		
	}
</style>

</head>
<body> <!-- section의 조상 -->
  <section> <!-- div의 조상 -->	
	<div>	<!-- h1의 조상 -->
		<h1>테스트</h1>
	</div>
  </section>
<script>
function clickHandler() {
	var dom1 = document.getElementsByTagName("h1")[0];
	var dom2 = document.getElementsByTagName("div")[0];
	var dom3 = document.getElementsByTagName("section")[0];
	var dom4 = document.getElementsByTagName("body")[0];
	dom1.addEventListener("click", displayAlert);	
	dom2.addEventListener("click", displayAlert);	
	dom3.addEventListener("click", displayAlert);	
	dom4.addEventListener("click", displayAlert);	
}
function displayAlert(e) {
	//여전히 타겟은 자식. this = currentTarget (부모)
	window.alert(e.target+"\n"+e.currentTarget+"\n"+this);
	//이벤트 버블링 멈추는 기능! 혹은 return false; 로 중단할수도 잇음
	/* e.stopPropagation(); */	
}
window.addEventListener("load", clickHandler);
</script>
</body>
</html>
```



### exam9_1

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	section {
		border : 1px solid lime;	
		padding :10%;
		margin : 0px auto;	
	}
	div {
		border : 1px solid blue;	
		width : 80%;
		padding :10%;
		margin : 0px auto;	
	}
	h1 {
		border : 1px solid red;	
		width : 80%;
		padding :10%;
		margin : 0px auto;		
	}
</style>

</head>
<body onclick="alert('body태그');">
  <section onclick="alert('section태그');">
	<div onclick="alert('div태그');">
		<h1>테스트</h1>
	</div>
  </section>
<script>
function clickHandler() {
	var list = document.getElementsByTagName("h1");
	list[0].addEventListener("click", displayAlert);	
	list = document.getElementsByTagName("section");
	list[0].addEventListener("click", displayAlert);	 
}
function displayAlert(e) {
	window.alert("h1 태그가 클릭됨!!"+this+e.target+e.currentTarget);
	//e.stopPropagation();  	
}
window.addEventListener("load", clickHandler);
</script>
</body>
</html>
```





### exam10

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div {
		border : 1px solid blue;	
		width : 80%;
		padding :10%;
		margin : 0px auto;	
	}
	h1 {
		border : 1px solid green;	
		background : #b3ffb3;
		width : 80%;
		padding :10%;
		margin : 0px auto;		
	}
</style>
</head>
<body>
<div>
<h1>테스트</h1>
</div>
<script>
function clickHandler() {
	var h1Tag = document.getElementsByTagName("h1")[0];
	h1Tag.addEventListener("click", displayAlert);
}
function displayAlert(e) {	
	window.alert("클릭 : " + e.pageX + ", " + e.pageY);	//도큐먼트 페이지 영역 기준
	window.alert("클릭 : " + e.screenX + ", " + e.screenY);//스크린 영역 기준
}
window.addEventListener("load", clickHandler);
</script>
</body>
</html>
```





### exam11

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p id="demo" onclick="myFunction()">Click me to change my text color.</p>

<script>
function myFunction() {
    var pdom = document.getElementById("demo");
    alert(pdom.getAttribute("id"));
    alert(pdom.id);
    pdom.style.color = "red";
    // 백그라운드 칼라를 "lime" 으로 설정한다.
     pdom.style.backgroundColor = "lime";
    // 텍스트 굵기는 굵게 한다.
     pdom.style.fontWeight = "bold";
}
</script>
</body>
</html>
```



