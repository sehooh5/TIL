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



### 생성자 함수 이용한 객체 생성

#### 1.생성자 함수 이용한 객체 생성

![image-20200109102305001](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200109102305001.png)

#### 2.프로토타입에 넣기

![image-20200109102228726](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200109102228726.png)



### exam21-기본 객체 만들기

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



### exam24-생성자함수 이용한 객체 생성(중요)

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>자바스크립트의 객체 생성과 사용(생성자함수)</h1>
<hr>
<script src="util.js"></script>
<script>
function Student(p1,p2,p3,p4){	/* 생성자 함수는 첫 글자를 대문자로 표기한다 */
	this.name = p1;	/* this 는 자바스크립트에서는 어디서 사용되는냐에 따라 달라진다 */
	this.kor = p2;
	this.eng = p3;
	this.math = p4;
	this.getSum = function(){
		return this.kor + this.eng + this.math;
	}
	this.getAvg = function(){
		return this.getSum()/3;
	}
	this.toString = function(){
		return this.name+" 학생의 총점은"+
			this.getSum()+" 입니다.";
	}
}
/* {} or new 멤버없는 객체 만들기  여기서 this를 사용하면 비어있는 객체를 지칭한다.*/
/* 비어있는 new 객체 만들고 바로 넣어주는 생성자 Student 후에 this는 비어있는 자바스크립트 객체를 참조한다 */
/* 생성자 함수는 꼭 new와 함께 사용해라! */
var student1 = new Student('세호',100,200,300); 	
var student2 = new Student('동규',-100,-200,-300);
var student3 = new Student('박하야로',-1000,-200,-300);
writeColor("학생1 : "+student1,"h3","blue");
writeColor("학생2 : "+student2,"h3","green");
writeColor("학생3 : "+student3,"h3","skyblue");
</script>
</body>
</html>
```



### exam25

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>자바스크립트의 객체 생성과 사용(생성자함수 프로토타입)</h1>
<hr>
<script src="util.js"></script>
<script>
function Student(p1,p2,p3,p4){	/* 생성자 함수는 첫 글자를 대문자로 표기한다 */
	this.name = p1;	/* this 는 자바스크립트에서는 어디서 사용되는냐에 따라 달라진다 */
	this.kor = p2;
	this.eng = p3;
	this.math = p4;
	}
	Student.prototype.getSum = function(){
		return this.kor + this.eng + this.math;
	}
	Student.prototype.getAvg = function(){
		return this.getSum()/3;
	}
	Student.prototype.toString = function(){
		return this.name+" 학생의 총점은"+
			this.getSum()+" 입니다.";
	}

/* {} or new 멤버없는 객체 만들기  여기서 this를 사용하면 비어있는 객체를 지칭한다.*/
/* 비어있는 new 객체 만들고 바로 넣어주는 생성자 Student 후에 this는 비어있는 자바스크립트 객체를 참조한다 */
/* 생성자 함수는 꼭 new와 함께 사용해라! */
var student1 = new Student('세호',100,200,300); 	
var student2 = new Student('동규',-100,-200,-300);
var student3 = new Student('박하야로',-1000,-200,-300);
writeColor("학생1 : "+student1,"h3","blue");
writeColor("학생2 : "+student2,"h3","green");
writeColor("학생3 : "+student3,"h3","skyblue");
</script>
</body>
</html>
```



### exercise8-객체리터럴 생성

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>함수 마지막 실습</title>
</head>
<body>
<script src="util.js"></script>
<script>
function printObject(p){
	if(typeof p != "object")
		return;
	else 
		 return writeColor(p.msg,p.tag,p.color);	//p..this사용 안된다!
}
</script>
<script>
var p = {
		tag : "h1",
		color : "green",
		msg : "가나다라마바사"
}
printObject(p);
var p = {
		tag : "h1",
		color : "blue",
		msg : "아자차카타파하"
}
printObject(p);
var p = {
		tag : "h2",
		color : "purple",
		msg : "수리수리마수리"
}
printObject(p);
var p = {
		tag : "h2",
		color : "magenta",
		msg : "글씨야 작아저라 얍!"
}
printObject(p);
var p = {
		tag : "h5",
		color : "black",
		msg : "글씨가 작아졌지 후후"
}
printObject(p);

</script>
</body>
</html>
```



### exercise9- 생성자 함수 프로토타입

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>함수 마지막 실습</title>
</head>
<body>
<script src="util.js"></script>
<script>
function DayInfo(name,yy,mm,dd){
	this.name = name;
	this.yy = yy;
	this.mm = mm;
	this.dd = dd;
}
DayInfo.prototype.getTotalDays = function(){
	 var day1 = new Date(this.yy,this.mm,this.dd);
	 var day2 = new Date();
	 var result = Math.floor((day2-day1)/86400000);
	 return this.name+" 님은 태어난지 "+result+" 일 되었어요.";
}
DayInfo.prototype.getKorDay = function(){	//프로토 타입 함수 작성 방법 : 객체명.prototype.함수명 --> 괄호없이!
	 var d = new Date(this.yy,this.mm,this.dd);
	 var week = new Array(7);
	 week[0] = "일요일";
	 week[1] = "월요일";
	 week[2] = "화요일";
	 week[3] = "수요일";
	 week[4] = "목요일";
	 week[5] = "금요일";
	 week[6] = "토요일";
	 var day3 = week[d.getDay()];
	 return this.name+" 님은 "+day3+"에 태어났어요.";
}
var person1 = new  DayInfo("세호", 1991,1,8);
var person2 = new  DayInfo("동규", 1992,8,9);
var person3 = new  DayInfo("바보동규", 1992,9,9);

writeColor(person1.getTotalDays(),"h3","orange");	//함수 호출 방법 : 객체명.함수명() --> 괄호 중요!!
writeColor(person1.getKorDay(),"h3","orange");

writeColor(person2.getTotalDays(),"h3","red");
writeColor(person2.getKorDay(),"h3","red");

writeColor(person3.getTotalDays(),"h3","magenta");
writeColor(person3.getKorDay(),"h3","magenta");


</script>
</body>
</html>
```



### exam28

```javascript

```

