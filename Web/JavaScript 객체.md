# JavaScript 객체

[TOC]

### 객체 정의 방법

- 변수용

  : 객체명.변수명;  or  객체명["변수명""];    ==>l&r value 둘다 가능

- 함수용

  : 객체명.함수명(){};

- 삭제때는 **delete**사용

- 객체 리터럴 사용방법

```
{
	속성명 : 속성값,
	속성명 : 속성값,
	속성명 : 속성값,...
}
```



#### exam21-기본 객체 만들기

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>자바스크립트의 객체 생성과 사용(객체리터럴)</h1>
<hr>
<script src="util.js"></script>
<script>
	var obj = {	//두개의 객체를 obj 변수에 담는다. 문자열 객체 name 와 함수를 갖는 eat
			name : "듀크",
			eat : function(food){
				writeColor(this.name + "가 "+food+"를 먹어요!!","h3","green");
							//**같은 객체안에서 멤버 접근할때 꼭 this 붙여야한다!
			}
	}
	obj.eat("말고기");
	obj.eat("고래고기");
	hr();
	writeColor(typeof obj,'h2',"red");
	//객체 생성 후에도 변환 할 수 있다. project라는 객체를, study 라는 함수를 더 넣어준다.
	obj.project = "자바스크립트";
	obj.study = function(){
		writeColor(this.name + "가 "+this.project+"를 씹어먹어요!!","h3","magenta");
	}
	obj.study();
	hr();
	for(var key in obj)
		write(key+" : " + obj[key], "h4");	//obj.key 는 사용할 수 없다=undefined
	hr();
	write(obj.project,"h2");
	write(obj["project"],"h2");
	delete obj.study;	//obj 가 갖고잇는 study 를 삭제한다
	hr();
	for(var key in obj)
		write(key+" : " + obj[key], "h4");
</script>
</body>
</html>
    
```



### exam22 

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>자바스크립트의 객체 생성과 사용(객체리터럴)</h1>
<hr>
<script src="util.js"></script>
<script>
	var student = {
			name : "세호",
			kor : 100,
			eng : 100,
			math : 110,
			getSum : function(){
				return this.kor+this.eng+this.math;
			},
			getAvg : function(){
				return this.getSum()/3;
			},
			toString : function(){
				return this.name + "학생의 총점은"+this.getSum()+"입니다.";
			}
	}
	write("총점 : "+student.getSum(),"h3");
	write("총점 : "+student.getAvg(),"h3");
	writeColor(student.toString(),"h3","skyblue");
	writeColor(student,"h3","skyblue");
</script>
</body>
</html>
```

