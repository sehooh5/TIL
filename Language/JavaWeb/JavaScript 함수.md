# JavaScript 함수

[TOC]

### exam12-선언적 함수정의

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>함수 정의와 활용(1)-선언적 함수 정의</h1>
<hr>
<script>
function f1(){
	document.write('f1() 호출<br>');	/* 리턴값이 없음 */
}
function f2(p1,p2){
	document.write('f2() 호출-'+(p1+p2)+'<br>');/* 리턴값이 없음 */
}
f1();
f2(10,20);
document.write('<hr>');
var result1 = f1();
var result2 = f2(100,200);
document.write('result1 : '+result1+'result2 : '+result2);/* 리턴값이 없으므로 undefined 출력 */
if(result1==undefined){//if(!result1){	undefined는 false 이므로 이런 형식으로 구현 가능
	document.write('<br>리턴값이 없군요!!');
}
document.write('<hr>');
f1(100);/*	매개변수 없는 함수인데도 아규먼트 전달한다  */
f2(100);/*	매개변수 다른 함수인데도 아규먼트 전달한다  */
</script>
</body>
</html>
```



### exam12-표현적 함수정의

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>함수 정의와 활용(2)-표현적 함수 정의</h1>
<hr>
<script>
var f1 = function(){
	document.write('f1() 호출<br>');	/* 리턴값이 없음 */
}
var f2 = function(p1,p2){
	document.write('f2() 호출-'+(p1+p2)+'<br>');/* 리턴값이 없음 */
}
f1();
f2(10,20);
document.write('<hr>');
var result1 = f1();
var result2 = f2(100,200);
document.write('result1 : '+result1+'result2 : '+result2);
if(result1==undefined){//if(!result1){	
	document.write('<br>리턴값이 없군요!!');
}
document.write('<hr>');
f1(100);
f2(100);
document.write(typeof f1 + '<br>');
document.write(typeof f2 + '<br>');
</script>
</body>
</html>
```



### exam14 - 가변아규먼트 처리함수

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>가변아규먼트 처리 함수 만들기</h1> <!-- 자바에서는 ... 사용했었다 -->
<hr>
<script>
function out(){
	/* arguments라는 자동으로 만들어진 배열 변수를 사용한다..가변형 */
	document.write("아규먼트 갯수 : "+arguments.length+"<br>");	
	for(var i=0; i<arguments.length;i++)
		console.log(arguments[i]);
	console.log('------------------');
}

out();
out(10); out(10,20); out('a','b','c'); out(1,2,3,4,5,6,7,8);
</script>
</body>
</html>
```



### exam15-함수에서 데이터 체크,이벤트 발생

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>함수에서 데이터의 타입채크</h2>
<hr>
<button onclick="clickProcess(100);">숫자</button> <!-- form 태그 안에서만 button 이 submit -->
<button onclick="clickProcess('100');">문자열</button> <!-- onclick 으로 이벤트 발생 -->
<button onclick="clickProcess(true);">논리값</button>
<button onclick="clickProcess(function(){ });">함수</button>
<button onclick="clickProcess([ ]);">배열</button> <!-- 원소가 없는 배열  -->
<button onclick="clickProcess({ });">객체</button> <!-- {}의 의미는 객체 -->
<button onclick="clickProcess();">????</button>
<script>
function clickProcess(p) {	/* 이벤트 핸들러 : 이벤트가 발생했을 때 호출되는 함수 */
	if (typeof p == "number") {
		alert("숫자 전달!!");
	} else if (typeof p == "string") {
		alert("문자열 전달!!");
	} else if (typeof p == "boolean") {
		alert("논리값 전달!!");
	} else if (typeof p == "function") {
		alert("함수 전달!!");
	} else if (typeof p == "object") {
		if (Array.isArray(p))	//어렵다
			alert("배열객체 전달!!");
		else 
			alert("객체 전달!!");
	} else if (typeof p == "undefined") {  // p == undefined
		alert("전달된 아규먼트 없음!!");
	}	
}
</script>
</body>
</html>
```



### exam16

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>함수의 아규먼트 처리</h1>
<hr>
<script>
function pr(p) {
	document.write(typeof p + " : " + p + "<br>");	/* p의 타입과 p의 값을 출력하는 함수 */
}
pr(100);	/* number : 100 */
pr("100");	/* string : 100 */
pr(true);	/* boolean : true */
pr(3.5);	/* number : 3.5 */
pr(new Array(1,2,3));	/* object : 1,2,3 */	
/* prNum(10);
document.write("ㅋㅋㅋㅋㅋㅋ"); */
</script>
<hr>
<script>
function prNum(p) {
	if(typeof p == "number")
		document.write("숫자 전달 : " + p + "<br>");
	else if (p == undefined)
		document.write("아규먼트를 전달하시오 : " + p + "<br>");
	else
		document.write(typeof p + " : " + p + "<br>");	
}
prNum(100);
prNum("100");
prNum(true);
prNum(3.5);
prNum(new Array(1,2,3));
prNum();
pr(100000);	/* 위 스크립트 태그에 있던 함수도 사용 가능 */
</script>
<hr>
<script>
function prAll() {
	for(var i=0; i < arguments.length; i++)
		document.writeln(arguments[i]);
	document.write("<br>"); /* 이건 그냥 블랭크 */
	return arguments.length;
}
var r1 = prAll(100);
var r2 = prAll(1,2,3,4,5);
var r3 = prAll('a', 'b', '가', true);
var r4 = prAll();
document.write(r1 + "<br>");
document.write(r2 + "<br>");
document.write(r3 + "<br>");
document.write(r4 + "<br>");
</script>
</body>
</html>
```



### exam17-함수에 함수

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>함수의 아규먼트 처리</h1>
<hr>
<script>
function output(p){
	if(typeof p == 'function'){
		p("ㅋㅋㅋ");
	}else{
		document.write("<h2> ㅋㅋㅋ :" +p+"</h2>");
	}
}
output("둘리");
output(function(param){console.log(param);})
//function myAlert(param){
	var myAlert = function(param){
		window.alert(param);
}
output(myAlert);
</script>
</body>
</html>
```



### exam18-고차함수 활용..5초마다

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>고차함수 활용 예</h2>
<hr>
<p>5초 후에 이 화면은 바뀝니다..</p>
<script>
	var displayDate = function(){
		var d = new Date();
		document.write(d.toLocaleString()+"<br>");
	};
	var time = 5000;
	window.setInterval(displayDate, time);	//setInterval 몇초마다 함수를 실행해라!
</script>
</body>
</html>
```



### exercise5-

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>함수 실습</title>
</head>
<body>
<script>
function sum(n){
	if(n==''||n==null)
		return;
	else 
		for(var i=1;i<=n;i++){
			return n+=i;
		}
}
/* document.write(sum()); */
var n = parseInt(Math.random()*6);
if(n==0)
	sum();
else
	sum(n);
	if (typeof sum(n) == 'undefined')
		document.write("결과값이 없어요!");
	else
		document.write("호출 결과값 : "+sum(n));
</script>
</body>
</html>

<!-- <pre>
<script>
	var array = new Array(10);
	for(var i = 0; i<array.length; i++)
		array[i] = parseInt(Math.random()*50+1)
		
	document.writeln("정렬 전 : "+array.toString());
	
	array.sort(function(a, b) {return a - b});
	document.writeln("정렬 후 : "+array.toString());
		
</script>
</pre> -->
```



### exercise6

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가변형 실습</title>
</head>
<body>
	<script src="util.js"></script>
	<script>
		function calc() {
			if (arguments.length == 0) {
				return 0;
			} else {
				var sum = 0;
				for (var i = 0; i < arguments.length; i++) {
					sum += parseInt(arguments[i])
					if (isNaN(arguments[i]))
						return "숫자만 전달하세요";
				}
				return sum;
			}
		}
		var r1 = calc();
		var r2 = calc(10, 20, '30');
		var r3 = calc(10, '가나다', 20);
		var r4 = calc(1, 2, 3, 4, 5);
/* 		document.write("<h3>" + r1 + "</h3>");
		document.write("<h3>" + r2 + "</h3>");
		document.write("<h3>" + r3 + "</h3>");
		document.write("<h3>" + r4 + "</h3>"); */
		write(r1,"h3");	//util.js 함수 받아와서 사용하기!!!!!개꿀@!
		write(r2,"h3");
		write(r3,"h3");
		write(r4,"h3");
	</script>
</body>
</html>

```



### exam19

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>자바스크립트 변수의 스코프(2)</h2>	<!-- 스코프 : 언제부터 언제까지 사용 가능한가? -->
<hr>
<script>
var i = 100;	// 함수 밖에 선언된 변수 : 전역변수
var sum = 0;
document.write("i : " + i + "<br>");
for(var i=0; i < 10; i++) {
	sum += i;
}
document.write("i : " + i + "<br>");
document.write("sum : " + sum + "<br>");
</script>
</body>
</html>
```



### exam20

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>자바스크립트 변수의 스코프(2)</h2>
<hr>
<script src="util.js"></script>
<script>
var g_v = 100;	//전역변수
function scopeTest() {
	var l_v = 1000;	//지역변수 : 함수 안에서만 사용된다!
	writeNewLine("scopeTest() l_v : " + l_v);
	writeNewLine("scopeTest() g_v : " + g_v );
}
scopeTest();
try {
	writeNewLine("l_v : " + l_v  );
} catch(e) {	//catch 에 변수명만 주면 됨..에러 생기면 객체를 받고 e에 담아준다
	writeNewLine(e);	//에러 객체 e를 프린트 해준다.
}
writeNewLine("g_v : " + g_v );
</script>
</body>
</html>
```



### exercise7-함수안에 함수

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>함수 마지막 실습</title>
</head>
<body>
<script>
	var array = new Array();
	while(true){
		var fill = window.prompt();
		if(fill)
			array.push(fill);
		else
			break;
	}	
	
	function printH1(content){
		document.write("<h1>"+content+"</h1>")
	}
	function printH4(content){
		document.write("<h4>"+content+"</h4>")
	}
	
	function apply(arr,func){	//arr = 배열, func = 함수
		if(arguments.length == 0|| !Array.isArray(arr) || typeof func != "function"){
			console.log("아규먼트 오류")
			return false;
		}else {
			for(var i=0;i<arr.length;i++)
				func(arr[i]);
			return true;
		}
	}


	var week = new Array('일','월','화','수','목','금','토');
	var today = new Date().getDay();
	var yo = week[today];
	var result = true;
	if(yo=='월'||yo=='수'||yo=='금'){
		result = apply(array,printH1);
	}else if(yo=='화'||yo=='목'){
		result = apply(array,printH4);
	}else if(yo=='일'){
		result = apply("ㅋㅋ",printH1);
	}else if(yo=='토'){
		result = apply(array);
	}
	if (result==true){
		window.alert("성공");
	}else if (result == false)
		window.alert("실패");
	
</script>
</body>
</html>
```

