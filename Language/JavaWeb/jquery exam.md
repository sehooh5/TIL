# jquery exam

[TOC]

## effect

### exam1.html(click 이벤트)

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script>
/* jquery 에서 사용하는 click 이벤트 핸들러 click()*/
$(document).ready(function(){
   $( "#clickme" ).click(function(e) {	
	  $( "#book" ).animate({
		   width: [ "toggle", "linear" ]		
	  }, {
		  duration : 3000,
		  complete : function() {
			  alert("끝");
		  }
	  });
   });
});
</script>
</head>
<body>
<div id="clickme">
  Click here
</div><br>
<img id="book" src="java-duke.jpg" alt="" width="200" height="223"
  style="position: relative; left: 10px;">
</body>
</html>
```



### exam2.html(toggle)

```html
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script>
/* toggle 보이는건 안보이게 안보이는건 보이게*/
$(document).ready(function(){
  $("button").click(function(){
    $("p").toggle(1000);
  });
});
</script>
</head>
<body>

<button>Toggle</button>
<p>This is a paragraph with little content.</p>
<p>This is another small paragraph.</p>
</body>
</html>
```

### exam3.html(faedIn, fadeOut)

```html
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script>
//faedIn, fadeOut
$(document).ready(function(){
  $(".btn1").click(function(){
    $("p").fadeOut(3000)
  });
  $(".btn2").click(function(){
    $("p").fadeIn(1000);
  });
});
</script>
</head>
<body>

<p style="background-Color:green;">
This is a paragraph.<br/>
This is a paragraph.
</p>
<button class="btn1">Fade out</button>
<button class="btn2">Fade in</button>

</body>
</html>


```

### exam4.html(slideUp, slideDown)

```html
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script>
//slideUp, slideDown
$(document).ready(function(){
	  $(".btn1").click(function(){
	    $("p").slideUp(1000);
	  });
	  $(".btn2").click(function(){
	    $("p").slideDown(1000);
	  });
	});
</script>
</head>
<body>

<p style="background-Color:green;">
This is a paragraph.<br/>
This is a paragraph.
</p>

<button class="btn1">Slide up</button>
<button class="btn2">Slide down</button>

</body>
</html>
```

### exam5,6,7,8,9.html(animate)

```html
<!DOCTYPE html>
<html>
<head>
<style>
        * { margin:0px; padding:0px }
        div{
            background:#98bf21;
            height:100px;
            width:100px;
            position:absolute;"
        }
    </style>
<script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script> 
//animate
$(document).ready(function(){
  var toggle = true;
  $("button").click(function(){
	 if(toggle) {
    	$("div").animate({
      		left:'+=250px',//왼쪽에서 250 이동
      		opacity:'0.5',
      		height:'150px',
      		width:'150px'
    	}, 3000)//3초동안
    	toggle = false;
     } else {
        $("div").animate({
        	left:'-=250px',
            opacity:'1.0',
            height:'100px',
            width:'100px'
         });
        toggle = true;  
     } 
  });
});
</script> 
</head>
 
<body>
<button>Start Animation</button>
<br/><br/>
<div></div>

</body>
</html>

<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script> 
$(document).ready(function(){
  $("button").click(function(){
    var div=$("div");  
    div.animate({left:'100px'},"slow");
    div.animate({fontSize:'3em'},"slow"); 
    /* div.animate({left:'100px', fontSize:'3em'},"slow"); */
  });
});
</script> 
</head>
<body>

<button>Start Animation</button>
<br/><br/>
<div style="background:#98bf21;height:100px;width:200px;position:absolute;">HELLO</div>
</body>
</html>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script>
$(document).ready(function(){
   $( "#clickme" ).click(function(e) {	
	  $( "#book" ).animate({
	    opacity: "toggle"
	  }, 5000);
   });
});
</script>
</head>
<body>
<div id="clickme">
  Click here
</div><br>
<img id="book" src="java-duke.jpg" alt="" width="200" height="223"
  style="position: relative; left: 10px;">
</body>
</html>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script>
$(document).ready(function(){
   $( "#clickme" ).click(function(e) {	
	  $( "#book" ).animate({
		   width: [ "toggle", "swing" ],
		   height: [ "toggle", "swing" ]     //swing 효과는 점점 빨라짐  
	  }, 5000);
	});
});
</script>
</head>
<body>
<div id="clickme">
  Click here
</div><br>
<img id="book" src="java-duke.jpg" alt="" width="200" height="223"
  style="position: relative; left: 10px;">
</body>
</html>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script>
/* jquery 에서 사용하는 click 이벤트 핸들러 click()*/
$(document).ready(function(){
   $( "#clickme" ).click(function(e) {	
	  $( "#book" ).animate({
		   width: [ "toggle", "linear" ]		
	  }, {
		  duration : 3000,
		  complete : function() {
			  alert("끝");
		  }
	  });
   });
});
</script>
</head>
<body>
<div id="clickme">
  Click here
</div><br>
<img id="book" src="java-duke.jpg" alt="" width="200" height="223"
  style="position: relative; left: 10px;">
</body>
</html>
```

### exam10.html(animate )

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script>
function callback() {
    setTimeout(function() {
      $( "#effect" ).show();
    }, 1000 );
  };  
$(document).ready(function(){
   $( "#button" ).click(function(e) {	
	   e.preventDefault();	
	   var selectedEffect = $( "#effectTypes" ).val();
	   $( "#effect" ).effect( selectedEffect, 3000, callback );			  
   });
});
</script>
</head>
<body>
    <div style="width: 430px; margin : 0  auto;">
	<img id="effect" src="sundie.png" alt="" width="400" height="423"> 
	</div>
	<br>
	<br>

	<select name="effects" id="effectTypes">
		<option value="blind">Blind</option>
		<option value="bounce">Bounce</option>
		<option value="clip">Clip</option>
		<option value="drop">Drop</option>
		<option value="explode">Explode</option>
		<option value="fade">Fade</option>
		<option value="puff">Puff</option>
		<option value="pulsate">Pulsate</option>
		<option value="shake">Shake</option>
	</select>

	<a href="#" id="button">Run Effect</a>
</body>
</html>
```



## domedit

#### attr() 사용법

- attr('html 속성명') : getter
- attr('html 속성명','html 속성값')
- attr('html 속성명', 함수)
- attr({html 속성명 : html 속성값', html 속성명 : html 속성값'}) : setter



#### css() 사용법

- css('css 속성명') : getter
-    css('css속성명', 'css속성값')
-    css('css속성명', 함수)
-    css({css속성명:'css속성값',css속성명:'css속성값',..}) : setter
                           

#### html() 사용법

- html()  : innerHTML, getter
- html('태그문자열') : setter
- text : textContent ,,, 하나의 문자열로 출력

### exam4~14.html()

```html
<!DOCTYPE html>
<html>
<head>
    <script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
    //exam4
    <script>
        $(document).ready(function () {
            $('img').attr('width', 200);//attr () 속성 추가
        });
    </script>
    
    //exam5 : index 별로 width 설정
    <script>
        $(document).ready(function () {
            $('img').attr('width', function (index) {
                return (index + 1) * 100;
            });
        });
    </script>
    
    //exam6 : width, height 동시 설정
    <script>
           $(document).ready(function () {
                $('img').attr({
                    width: function (index) {
                        return (index + 1) * 100;
                    },
                    height: 100
                });
            });       
    </script>
    
    //exam7
    <script>
        $(document).ready(function () {
            $('h1').removeAttr('data-index');
        });
    </script>
    
    //exam8
    <style>
        .first { color:Red; }
        .second { color:Pink; }
        .third { color:Orange; }
    </style>
    <script src="http://code.jquery.com/jquery-2.1.3.min.js">	 </script>
    <script>
        //getter 는 무조건 첫번째만 처리해줌
        //그래서 각각 색을 넣게되는 기능
        $(document).ready(function () {
            $('h1').each(function(index,data) {
        		var color = $(data).css('color');
        		 alert(color); 
        	}); // 첫번째 아규먼트는 index 0,1,2,..
            	// 두번째 아규먼트는 받아온 DOM 객체
         	var color = $('h1').css('color');
        	alert(color);    
        });
    </script>
    
    //exam9
    <script>
        $(document).ready(function () {
            // 변수를 선언합니다.
            var color = ['Red', 'White', 'Purple'];

            // 문서 객체의 스타일을 변경합니다.
            $('h1').css('color', function (index) {
                return color[index];
            });
        });
    </script>
    
    //exam10
    <script>
        $(document).ready(function () {
            // 변수를 선언합니다.
            var color = ['Red', 'White', 'Purple'];

            // 문서 객체의 스타일을 변경합니다.
            $('h1').css({
                color: function (index) {
                    return color[index];
                },
                backgroundColor: 'Black'
            });
        });
    </script>
    
    //exam11 (중요)
        <script>
        $(document).ready(function () {           
         /*    var html = $('h1').html();          
            alert(html); */  
            $('h1').each(function(index, data){
            	var html = $(data).html();          
                alert(html);
            });  
        });
    </script>
    
    //exam12
    <script>
        $(document).ready(function () {
            // 변수를 선언합니다.
            var text = $('h1').text();
            // 출력합니다.
            alert(text);
        });
    </script>
    
    //exam13
    <script>
        $(document).ready(function () {
            $('.g1').html('<h1>$().html() Method</h1>');
            $('.g2').text('<h1>$().html() Method</h1>');
        });
    </script>
    
    //exam14
    <script>
        $(document).ready(function () {
            $('div').html(function (index) {
                return '<h1>Header-' + index + '</h1>';
            });
        });
    </script>
</head>
<body>
    <img src="Chrysanthemum.jpg"/>
    <img src="Desert.jpg"/>
    <img src="Hydrangeas.jpg"/>
</body>
</html>
```





### ajax 사용

- $.ajax()
- $.get()
- $.post()
- $.getJSON()
- $.(.....).load()



### exam1,2,3,5,7,7_1,2.html()

```html
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<title>New Web Project</title>
<script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
//exam1
<script>        
$(document).ready( function() {
   $.ajax('content/sample.xml', {//얘를 로드
      success :  function(data) {           //응답 성공시 호출하는 함수 success : function         
        $(data).find('testxml').each(function() { //find('찾을 태그명')
          $('body').append("<h1>"+$(this).find('name').text() + '</h1>');
          $('body').append("<h1>"+$(this).find('age').text() + '</h1>');
          $('body').append("<h1>"+$(this).find('kind').text() + '</h1>');
        });
      }
    });
});
</script>
    
//exam2
<script>		
$(document).on('ready',  function() {
	$.ajax('content/samplejson.txt', {				
		success :  function(data) {
			alert(data);
		  	var result = JSON.parse(data);
		    $.each(result, function(key, value) { //$
               $('body').append("<h1>" +value + 
            		   '</h1>');
            });
		}
	});
});
</script>
    

</head>
<body>
	<h1>jQuery 로 구현하는 AJAX 프로그램  - XML</h1>
</body>
</html>


//exam3
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<title>New Web Project</title>
<script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script>
$(document).ready(function () {
   	$.getJSON('content/samplejson.txt',  function(data) {
		  $.each(data, function(key, value) { 
		        $('body').append("<h1>" +value + '</h1>');
		  });
	});
});
</script>
</head>
<body>
	<h1>jQuery 로 구현하는 AJAX 프로그램  2 - JSON</h1>
</body>
</html>

//exam5
<!DOCTYPE html>
<html>
    <head>
     <meta charset='utf-8'>
     <title>New Web Project</title>
     <style>
     table {
        border-collapse: collapse;
        width: 100%;
        border: 1px solid black;
     } 
     td {
        border: 1px solid black;
        padding-left : 5px;
     }
     </style>
     <script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
     <script>		
		$(document).ready( function() {
			$('#output').load("content/rss.jsp");				
		});
     </script>
    </head>
    <body>
        <h1 style="text-align : center">실시간 날씨 정보입니다.</h1>
        <div id="output" style="width:350px; margin : 10px auto"></div>
    </body>
</html>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
//exam7 get방식   
<script>
$(document).ready(function() {
	$("#loginb").click(	function() {
		alert($("#loginf").serialize());
		$.getJSON("content/login.jsp", $("#loginf").serialize(), function(data) {
			$.each(data, function(key, value) {
				if (value == "ok") {
					$("#result").text("로그인 성공!!").css("color", "Blue");
				} else if (value == "fail") {
					$("#result").text("로그인 실패!!").css("color", "Red");
					$("#loginf").each(function() {
						this.reset();
						$("#id").focus();
					});
				}
			});
		});
	});
});
</script>
    
    
//exam7_1 post 방식    
<script>
$(document).ready(function() {
	$("#loginb").click(	function() {
		alert($("#loginf").serialize());
        //serialize() 사용하면 querry 문자열을 생성해서 전달해준다
		$.post("content/login.jsp", $("#loginf").serialize(), function(data) {
			$.each(data, function(key, value) {
				if (value == "ok") {
					$("#result").text("로그인 성공!!").css("color", "Blue");
				} else if (value == "fail") {
					$("#result").text("로그인 실패!!").css("color", "Red");
					$("#loginf").each(function() {
						this.reset();
						$("#id").focus();
					});
				}
			});
		});
	});
});
</script>
    
//exam7_2 post 방식
<script>
$(document).ready(function() {
	$("#loginb").click(	function() {
		$.ajax("content/login.jsp", {		
			type : "POST", 	
			dataType : "json",
			data : $("#loginf").serialize(),
			success : function(data) {
				$.each(data, function(key, value) {
					if (value == "ok") {
						$("#result").text("로그인 성공!!").css("color", "Blue");
					} else if (value == "fail") {
						$("#result").text("로그인 실패!!").css("color", "Red");
						$("#loginf").each(function() {
							this.reset();
							$("#id").focus();
						});
					}
				});
			}
		});
	});
});
</script>

</head>
<body>
	<h3>계정과 패스워드를 입력해 주세요.</h3>
	<form id="loginf">
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
				<td><input type="button" id="loginb" name="loginb" value="로그인" /></td>
				<td><output id="result"></output></td>
			</tr>
		</table>
	</form>
</body>
</html>

```

### exam.html()

```html

```

