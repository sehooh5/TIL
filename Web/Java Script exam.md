# Java Script exam

[TOC]



#### exam1 - 출력

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JavaScript 학습</title>
</head>
<body>
<h1>자바스크립트 맛뵐려?</h1>
<hr>
<script>	<!-- 스크립트 태그 안에 작성해야한다! -->  
	window.alert(1+2+3+4+6);	/* 1. 윈도우 창에 뜸//한행 한행 구분 꼭 해줘야 한다 */
	console.log(1+2+3+4+7);		/* 2. 작업관리자 도구에 콘솔에 띄움 */
	document.write(1+2+3+4+8);	/* 3. 인터넷 창에 뜸 // 자바와 같이 (.)은 멤버 연산자이다 //document 가 갖고있는 write 메서드*/
</script>
</body>
</html>
```



#### exam2-데이터 타입체크

```HTML
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JavaScript의 데이터 타입 체크</title>
</head>
<body>
	<h1>JavaScript의 변수 선언과 활용</h1>
	<hr>
	<script>
		var v1;
		document.writeln(v1 + "<br>");
		v1 = 100;
		document.writeln(v1 + "<br>");
		v1 = '가나다';
		document.writeln(v1 + "<br>");
		var v1 = true;
		document.writeln(v1 + "<br>");
		v1 = 123;
		document.writeln(v1 + 45 + "<br>");
		v1 = '123';
		document.writeln(v1 + 45 + "<br>");
	</script>
	<h1>JavaScript의 변수 선언과 활용</h1>
</html>
```



#### exam3-데이터 타입 체크

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JavaScript의 데이터 타입 체크</title>
</head>
<body>
	<h1>JavaScript의 데이터 타입 체크</h1>
	<hr>
	<script>
		document.writeln("<h2>"+typeof 100 +"</h2>");
		document.writeln("<h2>"+typeof 3.14 +"</h2>");
		document.writeln("<h2>"+typeof '가' +"</h2>");
		document.writeln("<h2>"+typeof "abc" +"</h2>");
		document.writeln("<h2>"+typeof '100' +"</h2>");
		document.writeln("<h2>"+typeof true +"</h2>");
		document.writeln("<h2>"+typeof undefined +"</h2>");
	</script></body>
</html>
```



#### exam4-변수 선언과 활용

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>자바스크립트의 변수 선언과 활용(2)</h1>
<hr>
<script>
	document.write("<ul>");
	var v1;
	document.write("<li>"+ v1 +"</li>");
	document.write("<li>"+ typeof v1 +"</li>");
	document.write("<li>"+ (v1+10) +"</li>");	<!-- NaN = Not a Number-->
	v1 = 100;
	document.write("<li>"+ v1 +"</li>");
	document.write("<li>"+ typeof v1 +"</li>");
	document.write("<li>"+ (v1+10) +"</li>");
	v1 = false;
	document.write("<li>"+ v1 +"</li>");
	document.write("<li>"+ typeof v1 +"</li>");
	document.write("<li>"+ (v1+10) +"</li>");	<!-- 11//true = 1, false = 0 -->
	v1 = "가나다";
	document.write("<li>"+ v1 +"</li>");	
	document.write("<li>"+ typeof v1 +"</li>");
	document.write("<li>"+ (v1+10) +"</li>");
	document.write("<ul>");
</script>
</body>
</html>
```



#### exam5-연산자

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>자바스크립트의 연산자(1)</h1>
<hr>
<pre>
<script>
	document.writeln(10>5);	<!-- true -->
	document.writeln("abc">"ABC");	<!-- TRUE! -->
	var str = "가나다";	
	document.writeln(str == "가나다");	<!-- true -->
	document.writeln(true == 1);	<!-- true -->
	document.writeln("100" == 100);	<!-- TRUE! -->
	document.writeln(true === 1);	<!-- false! ===는 타입도 같아야 같다고 한다!!!!!-->
	document.writeln("100" === 100);	<!-- false! -->
	document.writeln(10/3);	<!-- 3.33333333335! -->
	document.writeln(10%3);	<!-- 1 -->
	var num=10;
	document.writeln(++num);	<!-- 11 -->
	document.writeln(--num);	<!-- 10 -->
</script>
</pre>
</body>
</html>
```



#### exam7_1-연산자

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	span { color : red; }
</style>
</head>
<body>
<h1>자바스크립트의 연산자(2)</h1>
<hr>
<pre> <!-- 프리 포맷 : 띄어쓰기, enter를 가능하게 해줌 -->
<!--3번 반복하는 반복문 만들기 for-->
<script>
	for(var su=0;su<3;su++){
	var num=window.prompt("채크하려는 숫자를 입력해 주세요.");
//	window.alert(num+":"+isNaN(num));	
	if(isNaN(num) || num == '' || num == null ) {
		document.writeln("<span>숫자</span>를 입력해 주세요!!!");
	} else {
		num % 2 == 0 && document.writeln(num+"는 짝수");
		num % 2 == 0 || document.writeln(num+"는 홀수");
		}
	}
</script>
</pre>
</body>
</html>
```



#### exam7_2-연산자

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	span { color : red; }
</style>
</head>
<body>
<h1>자바스크립트의 연산자(2)</h1>
<hr>
<pre>
<!-- 사용자가 원할 때 까지 반복문 만들기  -->
<script>
	while(true){
	var num=window.prompt("채크하려는 숫자를 입력해 주세요.");
//	window.alert(num+":"+isNaN(num));	
	if(isNaN(num) || num == '' || num == null ) {
		document.writeln("<span>숫자</span>를 입력해 주세요!!!");
	} else {
		num % 2 == 0 && document.writeln(num+"는 짝수");
		num % 2 == 0 || document.writeln(num+"는 홀수");
		}
	var result = window.confirm("꼐쏚 쑤헁할까요?");
	if (!result)
		break;
	}
</script>
</pre>
</body>
</html>
```



#### exam8-랜덤값 처리

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>자바스크립트의 랜덤값 처리</h1>
<hr>
<script>
for(var i=0; i <10; i++){
	var rand = Math.random();// 0.0<= rand < 1.0
	console.log(rand);
	console.log(rand*3);
	console.log(Math.floor(rand*3));	//가장 가까운 작은 정수 뽑아내기 (SQL에서 사용)
	console.log("-----------------------");	
	document.write(Math.floor(rand*3) +"<br>");
}
</script>
</body>
</html>
```



#### exercise1-구구단실습(while and for)

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 실습</title>
</head>
<body>
	<pre>
<script>
	while (true) {
		var num = window.prompt("1부터 9사이의 숫자를 입력하세요");
		if (isNaN(num) || num == '' || num == null)
			window.alert("숫자를 입력하세요")
		else if (!isNaN(num) && num > 9 || num < 1)
			window.alert("1에서 9 사이 숫자를 입력하세요")
		else if (num > 0 && num < 10) {
			document.writeln("<h1>" + num + " 단입니다." + "</h1><hr>");
			for (var i = 1; i < 10; i++)
				document.writeln(num + " x " + i + " = " + num * i);

			break;
		}

	}
</script>
</pre>
</body>
</html>
```



#### exercise2-반복문 및 if 실습

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실습</title>
</head>
<body>
	<script>
		while (true) {
			var i = window.prompt("첫 번째 숫자를 입력하세요");
			if (isNaN(i) || i == '' || i == null)
				window.alert("숫자를 입력하세요");
			else
				break;
		}
		while (true) {
			var j = window.prompt("두 번째 숫자를 입력하세요");
			if (isNaN(j) || j == '' || j == null)
				window.alert("숫자를 입력하세요");
			else
				break;
		}

		while (true) {
			var oper = window.prompt("연산자를 입력하세요");
			if (oper == "+")
				document.writeln(i + " 와 " + j + " 의 " + oper + " 연산 결과는 "
						+ (Number(i) + Number(j)) + " 입니다.")
			else if (oper == "-")
				document.writeln(i + " 와 " + j + " 의 " + oper + " 연산 결과는 " + i
						- j + " 입니다.")
			else if (oper == "*")
				document.writeln(i + " 와 " + j + " 의 " + oper + " 연산 결과는 " + i
						* j + " 입니다.")
			else if (oper == "/")
				document.writeln(i + " 와 " + j + " 의 " + oper + " 연산 결과는 " + i
						/ j + " 입니다.")
			else if (oper == "%")
				document.writeln(i + " 와 " + j + " 의 " + oper + " 연산 결과는 " + i
						% j + " 입니다.")
			else
				window.alert("연산자를 입력해주세요");
			break;
		}
	</script>
</body>
</html>
```



#### exercise3-배열(원소 지정)

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배열 실습</title>
</head>
<body>
<pre>
<script>
	var array = [10,5,7,21,4,8,18];	//지정된 배열
	var sum = 0;
	for(var i in array)
		sum+=array[i]
	document.writeln("<h1>모든 원소의 합 : "+sum+"</h1>");
	array.sort(function(a,b){return b-a});
	document.writeln("<ul><li>최대값 : "+array[0]+"</li>");
	document.writeln("<li>최소값 : "+array[array.length-1]+"</li></ul>");
</script>
</pre>
</body>
</html>
```



#### exercise4-배열(크기만 지정)

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배열 실습</title>
</head>
<body>
<script>
	var array = new Array(10);
	for(var i = 0;i<10;i++)
		array[i] = parseInt(Math.random()*50+1);
	document.writeln("정렬 전 : "+array.toString()+"<br>");
	array.sort(function(a,b){return a-b});
	document.writeln("정렬 후 : "+array.toString());
</script>
</body>
</html>
```

