# CSS

- 구조적으로 짜여진 문서(HTML,XML)에 Style(글자,여백,레이아웃)을 적용하기 위해 사용 하는 언어(Language)이다.



### CSS 의 작성 방법

- 인라인방법 : HTML 엘리먼트에 style 이라는 속성으로 정의하는 방법
  <tag style="property: value">
- 전역적 방법 : <style> 이라는 태그에 웹 페이지의 태그들에 대핚 스타일을 정의하는 방법

```html
<style type="text/css">
selector {property: value;}
</style>
```

- 외부 파일 연결 방법 : 독립된 파일(확장자 .css)을 만들어서 HTML 문서에 연결하는 방법
  <link rel="stylesheet" type="text/css" href="style.css" />



### 전역적인 스타일 설정

- `<head>` 태그 안에 `<style>` 태그를 사용한다

- CSS 정의 방법

  ```html
  CSS 선택자 {
  		CSS 속성명 : 속성값 ;
  		CSS 속성명2 : 속성값2 ;
  		CSS 속성명3 : 속성값3 (;)
  }
  ```

  

### CSS 선택자

1. 전체 선택자 : `* {font : 10px Arial}`

2. 태그 선택자 : `h1 {text-decoratione:underline}` , 

   ​						`h1,h2,h3 {text-decoratione:underline}`

3. class 선택자 : .class이름   ex) .t2{}

4. id 선택자 : #id이름   ex) #t1{}

5. 자식 선택자

6. 자손 선택자

7. 첫번째 동생 선택자

8. 동생들 선택자

9. 속성 선택자

```html
(exam2_2)
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CSS 학습</title>
<!-- 전역적 방법//꼭 head 안에 주어야 한다 -->
<style>	/*;으로 여러 스타일을 지정할 수 있다. */
	a{
		text-decoration:none	
	}
	#t1:hover{	/* #t1는 id 선택자, :hover 마우스가 올라가면 변하게하는 스타일*/
		font-weight : bold;
		color : red;	
	}
	.t2:hover{				/* class 선택자 */
		opacity : 0.3;		/* opacity 는 0.0~0.3 */
	}
	h1, a{
		border : 1px solid blue	/* 선주기 : 1.블럭   2.인라인*/
	}
	span {
		color : #9900ff;
	}
</style>
</head>
<body>
<h1>CSS 선택자 <span>학습</span></h1>
<hr>

<a href = "http://www.w3schools.com/" >W3Schools</a><br> 
<a href = "http://www.html5test.com/" id="t1">HTML5 TEST</a><br>	<!-- id 값을 t1으로 줬다 -->
<a href = "http://www.caniuse.com/">HTML과 CSS의 지원여부 체크</a>ㅋㅋㅋ<br>

<img src="../images/totoro.png" width="200" class="t2">
<img src="/edu/images/totoro.png" width="200" class="t2">
<img src="http://localhost:8000/edu/images/totoro.png" width="200" >

</body>
</html>
```





#### 하나의 태그 혹은 태그의 콘텐트 에서 다른 CSS 주고싶을때

- `<div>` : 
  - 여러 태그들을 묶거나 또는 태그에 대하여 CSS속성을 적용할때(행바꿈 O, **block**)
  - 여러개의 태그들을 하나로 묶어서 같은 스타일을 주고싶을 때 사용
- `<span>` : 
  - 컨텐트의 일부분에 대하여 CSS 속성을 적용할 때(행바꿈 X, **inline**)
  - 하나의 단락에서 일부분을 다른 스타일 혹은 자바스크립트 액션할때만 사용

```html
(exam3_1)
<!DOCTYPE html>
<html> 
<head> 
<meta charset="UTF-8">
<title>CSS 학습</title>
<style>
	div{
		background-color : lime;
		margin : 5px;	/*margin : 밖에 간격을 준다 */
		width : 300px;
		height : 200px;
		font-size : 1em;
		padding : 10px; /*padding : 안에 간격을 주어 들어가게 해준다*/
	}	
	span{
		background-color : pink;
		margin : 5px;
		width : 300px;
		height : 200px;
		font-size : 1em;
		padding : 10px;
	}	
</style>
</head>
<body>
<h1>블럭 스타일 태그와 인라인 스타일 태그</h1>
<hr>
<div>가나다라마바사아</div>	<!-- block 행 바꿈 정렬  -->
<div>0123456789</div>
<div>abcdefghj</div>
<hr>
<span>가나다라마바사</span>	<!-- inline 한 행에 쭉 정렬  --> 
<span>0123456789</span>
<span>abcdefghij</span>
</body>
</html>
```

#### Block & Inline 스타일 태그

- **block style** : 
  - 행 끝까지의 공간, 상하좌우 마진 O, 사이즈 조정 O
  - 예 : div

- **inline style** : 
  - 출력되는 위치부터 컨텐트 공간까지, 상하마진X, 좌우마진만, 사이즈 조정X
  - 인라인은 마진을 줄 수 없기 때문에 블록 스타일에 담아서 주도록 한다
  - 예 : span, img...



```html
(exam3_2)
<!DOCTYPE html>
<html> 
<head> 
<meta charset="UTF-8">
<title>CSS 학습</title>
<style>
	div{
		background-color : pink;
		margin : 5px;	/*margin : 밖에 간격을 준다 */
		width : 50%;	/*상대값 % : 창의 사이즈 변형에 따라 사이즈가 변경된다 */
		height : 200px;
		font-size : 1.5em;
		padding : 10px; /*padding : 안에 간격을 주어 들어가게 해준다*/
	}	
	img{
		border : 1px dotted blue;
		margin-right : 10px;
		border-radius : 5px;
		box-shadow : 2px 2px 5px red;
	}
	.two{
		text-align : center;	/* 가운데 정렬 */
	/*	margin-left : auto;		/* 좌우마진을 오토로 줘서 블럭스타일 태그를 가운데로 정렬 */
	/*	margin-right : auto;*/
		margin : 10px auto;
		background-color : skyblue;
	}
	#four{			/*그림도 text-align, margin 으로 가운데 정렬 가능 */
		text-align : center;
		margin : 10px auto;
	}
	h1{
		text-shadow : 0 2px 5px purple, 0 -4px 5px orange; /*좌우 위아래 굵기 색상 */
	}
	h1:hover{
		transform : rotate(-3deg); /*3deg 3도만큼 회전시켜라*/
		transition : transform 5s;/*transition 움직이는 효과*/
	}
</style>
</head>
<body>
<h1>블럭 스타일 태그와 인라인 스타일 태그</h1>
<hr>
<div>가나다라마바사아</div>	<!-- block 행 바꿈 정렬  -->
<div class = "two">0123456789</div>
<div>abcdefghj</div>
<hr>
<!-- 컨테이너 태그로 사용되는 div -->
<div class = "two">
<img src="../images/totoro.png" width="100">
<img src="/edu/images/totoro.png" width="100">
<img src="http://localhost:8000/edu/images/totoro.png" width="100">
</div>

</body>
</html>
```