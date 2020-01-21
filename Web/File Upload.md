# File Upload

- Querry 문자열에 서버에 올리고자 하는 파일의 내용을 포함시켜 웹서버 프로그램을 요청

- 사용자가 업로드할 파일을 선택할 수 있게 화면을 제시해줘야한다

  1. `<form>` 태그의 자식 태그 중 : `<input type="file">` 사용

     - HTML5 이전에는 파일을 한 개만 선택 가능
     - `<input type="file" multiple>` 을 사용해 다수 선택 가능

     - 업로드되는 파일도 Query 문자열로 서버에 전송되어야 하지만 Query 문자열의 인코딩 규칙으로 인코딩되면 안된다. **그대로 올린다!**

     ```
     //무조건 POST 타입, enctype을 다르게 주어야한다!
     <form method="POST" action=""enctype="multipart/form-data">
     
     *multipart : 여러개의 파트로 나누어서 보내는 방법(p.13)
     ```

     

     기본적인 Query 문자열의 인코딩 규칙 : 

     1. name=value&name=value&name=value.....
     2. 영문, 숫자, 일부 특수문자를 제외하고 %기호와 함께 16진수 코드값 사용

     

     

     
     

  2. 파일에 대한 **Drag&Drop** 기능을 이용해 업로드할 파일을 윈도우즈 탐색기에서 선택하여 서버에 올릴 수 있다. **AJAX** 기술과 연계하여..



##### 기본정보

---

Collection<Part> parts = request.getParts();		파트 나누어서 시작

@MultipartConfig (location = "c:/uploadtest")		이거 꼭 줘야대 위치지정가능

var data = new FormData();		드래그앤드랍에 FormData로 파츠 나눠

for (var f = 0; f < files.length; f++) {		드래그앤드랍 어펜드해
				data.append('myuploadfile'+f, files[f]);
		}

part.write(fileName......)		write 로 알아서 파일 뭔지 알아차리고 저장

---



#### PartTestServlet - 멀티파트 

```java
package core;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@WebServlet("/part")
@MultipartConfig 
//위 부분을 꼭 줘야지 Multipart 를 실행할 수 있다.
public class PartTestServlet extends HttpServlet {   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
        Collection<Part> parts = request.getParts();//collection 객체를 리턴해 준다.
        System.out.println("========== 요청 받음 =========");
        for(Part part : parts) {        	
            System.out.print("name : ");
            System.out.println(part.getName());//이름 가져오기-html에 다 저장되어있음            
            System.out.println("[ 헤더 정보 ] ");
            for(String headerName : part.getHeaderNames()) {//이 의미가 뭐임?
                System.out.print(headerName + " : ");
                System.out.println(part.getHeader(headerName));
            }
            System.out.println("size : "+ part.getSize());
            String filename = part.getSubmittedFileName();
            if (filename != null)
            	System.out.println("file name : "+filename);
            System.out.println("------------------------------------");
        }        
    }
}
```



#### multipart.html - 멀티파트 

```java
<!--  파일명 : uploadexam/multipart.html -->
<!doctype html>
<html>
<head>
<meta charset="UTF-8" />
<title>File Upload Sample</title>
<style>
	input {
		margin : 5px;
	}
</style>
</head>
<body>
							<!-- multipart 타입 준다 -->
	<form action="/sedu/part"  enctype="multipart/form-data" 
	                           method="post"> 
		<label>작성자 이름 :  <input type="text" name="myname" /> </label><br>
		<label>작성자  폰번호 : <input type="text" name="myphone" /> </label><br>
		<label>첨부 파일 :  <input type="file" name="myfile" multiple/></label><br>
		<input type="submit"	value="전송" />
	</form>
</body>
</html>
```

이렇게 파트별 나누어서 전달

![image-20200121164318563](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200121164318563.png)



---



#### UploadServlet 

```java
package core;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@WebServlet("/upload")
//미리 만들어진 저장 위치를 이쪽에 지정해주면 된다.
@MultipartConfig (location = "c:/uploadtest")
public class UploadServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out= response.getWriter();
		request.setCharacterEncoding("utf-8");
		String path = "c:/uploadtest";
		File isDir = new File(path);
		if (!isDir.isDirectory()) {
			isDir.mkdirs();
		}
        //파트로 나누어서 꼭 처리해줘야한다.
		Collection<Part> parts = request.getParts();
		for (Part part : parts) {
			//**해당 컨텐트만의 파트가 있는지 확인하는 방법
			//실제 업로드하는 파트를 확일할 수있음
			//예를 들어 이름이나 전화번호 같은 경우는 출력만 하고 업로드하지 않음
			if (part.getContentType() != null) {
				String fileName = part.getSubmittedFileName();
				if (fileName != null) {
					//fileName 이 실제로 있는지 확인한 후에........part.write로 파일명만 지정해주면 된다
					part.write(fileName.substring(0, fileName.lastIndexOf(".")) + 
							"_"	+ System.currentTimeMillis() 
							+ fileName.substring(fileName.lastIndexOf(".")));
					out.print("<br>업로드한 파일 이름: " + fileName);
					out.print("<br>크기: " + part.getSize());				
				}
			} else {
				String partName = part.getName();
				String fieldValue = request.getParameter(partName);
				out.print("<br>" + partName + ": " + fieldValue);			
			}
		}
		out.close();		
	}
}
```



####  formtagupload

```java
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FileUpload 실습</title>
<style>
	input {
		margin : 5px;
	}
</style>
</head>
<body>
<h1>file 타입의 input 태그 테스트</h1>
<form  method="post"  action="/sedu/upload" 
               enctype="multipart/form-data" >
  작성자<input type="text" name="theAuthor"><br>
  나이<input type="text" name="theAge"><br>
  파일<input type="file" name="theFile" ><br>
  <input type="submit" value="업로드">
</form>
<hr>
<h1>file 타입의 input 태그 테스트(multiple)</h1>
<form  method="post" enctype="multipart/form-data" 
                action="/sedu/upload">
  작성자<input type="text" name="theAuthor"><br>
  나이<input type="text" name="theAge"><br>
  파일<input type="file" name="theFile"  multiple><br>
  <input type="submit" value="업로드">
 </form> 
</body>
</html>
```



#### dndupload - Formdata 사용하여 append 하여 서버에 전송

```java
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Drag and Drop File Upload</title>
<link rel="stylesheet" href="ajax.css">
 	<!-- 제주고딕 가져와서 사용하는 CSS 정의 -->
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css">
<style>
    * {
        font-family: 'Jeju Gothic', serif;
        font-size: 17px;
    }
    h1 {       
        font-size: 25px;
    }
</style>
<script>
	var databox;
	function initiate() {
		databox = document.getElementById('databox');
		databox.addEventListener('dragover', function(e) {
			e.preventDefault();
		}, false);
		//타겟인 databox 에 drop 이벤트 발생하면 작동
		databox.addEventListener('drop', dropped, false);
	}
	function dropped(e) {
		e.preventDefault();
		var files = e.dataTransfer.files;
		var xhr = new XMLHttpRequest();
		var result = "";
		//***FormData(); = 멀티파트로 구성해서 사용하기 위함
		var data = new FormData();
		for (var f = 0; f < files.length; f++) {
				//append 하여 저장하게됨
				data.append('myuploadfile'+f, files[f]);
		}
		//false = 비동기 아님! 이벤트 핸들러 등록 안함
		//send 해놓고 대기하고있음! 서버로부터 응답 올때까지 대기
		xhr.open("POST", "/sedu/upload", false); 
		xhr.send(data);			
		if(xhr.status == 200){
			 result = "업로드 성공!!";				
		} else {
			result = " 업로드 실패!!";			
		}	
		databox.innerHTML += result;				
	}
	window.addEventListener('load', initiate, true);
</script>
</head>
<body>
	<section id="databox">
		<p> 업로드하려는 파일을 드래그하여 여기 드롭하세요....</p>
	</section>
</body>
</html>
```



#### formtagajaxupload  

```java
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FileUpload 실습</title>
<style>
	input {
		margin : 5px;
	}
</style>
</head>
<body>
<h1>file 타입의 input 태그 테스트(Ajax)</h1>
<form id="f">
   파일<input type="file" name="theFile"  multiple><br>
   <!-- return false; 주는 이유는 form 태그가  내장하는 submit 처리를 수행하지 않기 위함
   Ajax 함수가 대신함 -->
  <input type="submit" value="업로드" 
                     onclick="uploadAjax();return false;">
</form>
<script>
function uploadAjax() {
	var xhr = new XMLHttpRequest();
	var data = new FormData(document.getElementById("f"));
	xhr.onload=function() { alert(xhr.responseText); };
	xhr.onerror=function(e) { alert(e); };
	xhr.open("POST", "/sedu/upload", true);
	xhr.send(data); 
}
</script>
</body>
</html>


```



#### memo_canvas_upload 

```java
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8"/>
<title>Draw memo on canvas</title>
</head>
<body>
    <button onclick="save();">Save</button>
    <button onclick="restore();">Restore</button>
    <button onclick="initialize();">Clear</button>
    <button onclick="upload();">Upload</button>  
    <button onclick="downimage();">DownImage</button><br><br> 
    <canvas id="myCanvas" width="580" height="450"></canvas>

    <script src="memo_canvas_upload.js"></script>
</body>
</html>
```



#### memo_canvas_upload.jsp 

```java
var device;
var drawing = false;
var canvas;
var context;
var rect;

function initialize() {
    context.clearRect(0,0,580,450);
    context.beginPath();
    context.rect(0,0,580,450);
    context.strokeStyle = "silver";
    context.fillStyle = "LightGoldenrodYellow";
    context.fill();

    context.lineWidth = 0.5;
    for(i=1;i<=8;i++) {
        context.moveTo(5,i*50);
        context.lineTo(575, i*50);
    }
    context.stroke();
}

function startDrawing() {
    if (device == "moblieDevice") event.preventDefault();
    event.preventDefault();
    drawing = true;
    context.beginPath();
    context.strokeStyle = "dimgray";
    context.lineWidth = 1;
    context.arc(event.clientX - rect.left, event.clientY - rect.top, 3, 0, 2*Math.PI)
    context.stroke();
    context.fillStyle = "dimgray";
    context.fill();
    context.closePath();

    context.beginPath();
    context.moveTo(event.clientX - rect.left, event.clientY - rect.top);
    context.lineCap = "round";
    context.lineWidth = 6;
}

function keepDrawing() {
    if (drawing) {
        var x,y;
        if (device == "mobileDevice") {
            x = event.targetTouches[0].pageX;
            y = event.targetTouches[0].pageY;
        }
        else {
            x = event.clientX;
            y = event.clientY;
        }
        context.lineTo(x - rect.left, y - rect.top);
        context.stroke();
    }
}
function stopDrawing() {
    if (drawing) {
        context.stroke();
        drawing = false;
    }
}
function save() {
	var localStorage = window.localStorage;
   	alert(canvas.toDataURL());
    localStorage.canvas = canvas.toDataURL(); 
}
function upload() {
	var data = new FormData();
	var myblob = new Blob([canvas.toDataURL()], {type : 'text/plain'});
	data.append('dataurlfile', myblob, "test.txt");	
	var xhr = new XMLHttpRequest();
	xhr.onload=function() { alert("업로드 완료");};
	xhr.onerror=function() { alert("업로드 오류발생");};
	xhr.open("POST", "/sedu/upload", true);
	xhr.send(data);	
}
//다운받아 이미지 다시 그려주는 기능
function downimage() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "downcanvasimage.jsp", true);
	xhr.send();  
	xhr.onload=function(e) { 
        var img = new Image();
        img.src = this.responseText;
        context.drawImage(img, 0, 0);
	}   
}



function restore() {
	var localStorage = window.localStorage;
    if (!localStorage) {
        // local storage is not supported by this browser.
        // do nothing
    }
    else {
        var img = new Image();
        img.src = localStorage.canvas;
        img.onload = function() {
            context.drawImage(img, 0, 0);
        }
    }
}



function getDeviceType() {
    var str = navigator.userAgent;
    if (str.match(/(ipad)|(iphone)|(ipod)|(android)|(webos)/i))
        device = "mobileDevice";
    else
        device = "desktopPC";
}

function startMemo() {
    canvas = document.getElementById("myCanvas");
    context = canvas.getContext("2d"); 
    rect = canvas.getBoundingClientRect();
    initialize();
}



getDeviceType();
document.body.onload = startMemo;

dom = document.getElementById("myCanvas");

// for desktop PC
dom.ontouchstart = startDrawing;
dom.ontouchmove = keepDrawing;
dom.ontouchend = stopDrawing;

// for mobile devices
dom.onmousedown = startDrawing;
dom.onmousemove = keepDrawing;
dom.onmouseup = stopDrawing;





```



#### downcanvasimage 

```java
<%@ page language="java" contentType="text/plain; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%
	String path = "C:/uploadtest/";
	File isDir = new File(path);
	if (!isDir.isDirectory()) {
		isDir.mkdirs();
	}
    FileReader reader = new FileReader(path+"test.txt");
    char[] buffer = new char[(int)(new File(path+"test.txt").length())];
    reader.read(buffer);
    out.println(buffer);
	reader.close();
%>
```



#### PartTestServlet - 파트 

```java

```



