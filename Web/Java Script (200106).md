# Java Script (200106)

[TOC]

- html 을 보완하기 위해 만든 브라우저 내에서 사용하는 언어
- html 문서 안에 작성한다. 또한 확장자 .js 로 만들고 가지고 들어갈 수도 있다



### Java Script  코드 작성 방법

반드시 수행 문장 단위로 작성한다! (더 단순하지)

1. <script> 안에 작성하면 된다 </script>

2. </body> 바로 위에 작성하도록 한다



### 데이터 타입과 변수선언

- 숫자, 문자열타입, 논리타입, 객체타입(배열타입), undefined 타입

  ```javascript
  var 변수명;
  var 변수명 = 초기값;
  ex) var v1; --> undefined 타입
  	v1 = 10; --> 숫자 타입
  	v1 = '10' or "10";  --> 문자열타입
  	v1 = false; --> 논리타입
  	-----------------------------------> typeof 연산자가 필요
  ```



### 연산자 (Java 와 거의 비슷)

- ==,  ===,  !=,  !==,  **&&,  ||,  delete,  typeof** ... 

- **&&** 와 **||**를 이용해서 if 문을 대신하여 구현가능하다

  ```javascript
  [JAVA]
  if(a>b)
  	System.out.println(a);
  	
  [JavaScript]	
  if(a>b)
  	window.alert(a); or
  	document.writeln(a);
  	console.log(a);
  or
  **이렇게도 구현 가능하다**
      
  a>b && document.writeln(a); 혹은	(&&는 참)
  
  a<=b || window.alert(a); (||는 거짓)
  
  <!------------------------------------------------------------>
  ex)
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  </head>
  <body>
  <h1>자바스크립트의 연산자(2)</h1>
  <hr>
  <pre>
  <script>
  	var num=5;
  	// num이 짝수이면 "xx는 짝수"
  	// num이 홀수이면 "xx는 홀수"
  	num % 2 == 0 && document.writeln(num+"는 짝수");
  	num % 2 == 0 || document.writeln(num+"는 홀수");
  </script>
  </pre>
  </body>
  </html>
  ```

  

### 제어문

- for, foreach, while, do while, break, continue 

  - for each 조금 다르다

    : for (변수 in 배열이나 객체내 요소멤버)



### 객체의 생성과 활용 (생성자 함수, 객체 리터럴)



### 예외처리

- 가급적이면 html에서 예외없이 작성하는 것이 좋다!



### API

- 표준 API : 함수, 생성자 함수

- BOM (Browser Object Model) API : 

  - 브라우저가 자바스크립트에게 제공하는 API

  - 내장 객체 형식 ( 우리가 객체생성 할 필요 없고 바로 사용하면 된다 )

- DOM (Document Object Model) API 

  - 객체 지향 모델로써 구조화된 문서를 표현하는 방식

- HTML5 API : 

  - **Canvas** : 웹페이지의 시각화를 지원해주는 API 
- **WebStorage** : 웹브라우저에 자료를 저장하기위한 기능
  - **drag&drop** : 파일을 드래그해서 첨부할 수 있게 하는 기능
  - geolotacation : 브라우저의 위치정보 반환



### 출력 방법

```
1. 윈도우 창에 뜸//한행 한행 구분 꼭 해줘야 한다
	window.alert(1+2+3+4+6); //모달창(서브창)이 처리 전에 실행 불가능하다!
	window.prompt(6);	// 사용자로부터 데이터를 입력받는 서브창을 디스플레이
	window.confirm(6);	// 확인받는 목적으로 사용되는 API, yes or no형식

2. 작업관리자 도구에 콘솔에 띄움
	console.log(1+2+3+4+7);	
	
3. 브라우저에 출력	
	document.write(1+2+3+4+8);
```



### 배열

- 다양한 타입의 데이터를 하나의 배열에 구성 가능

- 배열 생성 후에도 크기 변경 가능

- 배열 사용 방법

  ```javascript
  [생성]
  var a1 = [];
  var a2 = [10,20,30];  a2[10]=100;  --> 11번째 배열은 100 나머지 undefined
  var a3 = [true, '가나다', 100]; --> 복합적인 데이터의 배열 가능
  [사용]
  a1.length = 0
  a2.length = 3
  a3.length = 3
  a3[1] = true;
  
  
  [표준 API(Array 라는 생성자 함수를 이용)] -->가변형이라 배열크기 제한 없음
  var a4 = new Array(); --> a1과 같이 아무것도 없는 배열 생성
  var a5 = new Array(10); --> 숫자 1개 오면 배열의 크기를 지정하는 배열을 생성
  var a6 = new Array('abc');
  var a7 = new Array(10,20);
  var a8 = new Array(true,10,'aa');
  
  
  var a = [10,3,7,6,123,4];
  a.sort() = 10,123,3,4,6,7 -->문자열로 
  ```

  

### 함수의 정의와 활용***(R의 함수와 비슷!)

- **함수** : 수행 문장들을 담고 있는 호출 가능한 모듈, 단독으로 호출 가능

- **메서드** : 수행 문장들을 담고 있는 호출 가능한 모듈, 객체를 통해서만 호출 가능

  ​			객체의 멤버로 정의되는 함수

- 전역코드 :  <script>수행문장들........</script>

- 생성 방법

  ```
  [명시적(선언적) 함수 정의]
  
  	function 함수명(매개변수 선언){
  			수행코드.......
  	};
  
  
  [리터럴(표현식) 방식 함수 정의]
  	[방법1]
  	funtion (매개변수 선언){
  			수행코드.......
  	}(아규먼트);
  	
  	[방법2]
  	함수( function (매개변수 선언) {
  			수행코드.......
  	});					==> ex ) sort의 사용
  	
  	[방법3]
  	var 함수명 = function(매개변수 선언) {
  			수행코드.......
  	};
  	함수명([아규먼트]);
  	var 함수명2 = 함수명;
  	함수명2([아규먼트]);
  ```

  

  