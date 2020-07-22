<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ page import = "vo.BoardVO,java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
   content="width=device-width, initial-scale=1.0, user-scaclable=no">
<title>김세정의 골목상권</title>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/style.css" rel="stylesheet">
<link href="resources/css/jquery.fullPage.css" rel="stylesheet">
<link rel="stylesheet" href="resources/css/jquery.bxslider.css">
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic+Coding:700&display=swap" rel="stylesheet">
<script src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>

<style>
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothiccoding.css);

body,h1,h2,h3,h4,h5,table,button{
 font-family: 'Nanum Gothic Coding', monospace;
}

#color-change {
   background-color: white;  /* 위에 배너 컬러 */
   text-align:center;
}

.mainspan {
   color: black;
   font-size: 1.2em;
}

 {
   color: gray;
}


#container div {
   width: 250px;
   height: 300px;
   background: #fff;
   box-shadow: 0px 1px 1px #aaa;
   padding: 15px;
   padding-bottom: 15px;
   margin: 15px;
}

#container div img {
   width: 100%;
   padding-bottom: 15px;
   margin-bottom: 5px;
}

#container div h2 {
   font-size: 1em;
   color: black;
}

#container div p {
   font-family: "맑은 고딕", 돋움;
   font-size: 0.8em;
   color: #222;
   margin: 0;
   padding: 10px;
}

#wrapper {
   width: 600px;
   margin: 0 auto;
}
li{
display:inline-block;
}
ul{
text-align:center;
}

@import url(//fonts.googleapis.com/earlyaccess/hanna.css);

table.type1 {
	border-collapse: separate;
    border-spacing: 1px;
    text-align: center;
    line-height: 1.5;
    margin: 20px 10px;

}

table.type1 th {
    width: 155px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: pink;
    background:#e7708d ;
}
table.type1 td {
    width: 155px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc; /* 테이블 바닥 밑줄 */
    background: white; /* 테이블 배경색 */
}
tr:hover{
font-weight : bold;
}
#d {
	width: 300px;
}

#all {
	width : 60%;
	margin-left : auto;
	margin-right : auto;	
	padding: 50px;
	border-style: dotted dashed solid double;
	border-color: gray;
	font-family: 'Hanna', sans-serif;
		
}
h1{
	font-size: 3em;
	color: white;
  	text-align:center;
  	text-shadow: 2px 2px 4px #000000;
}
textarea {
  width: 100%;
  height: 150px;
  padding: 12px 20px;
  box-sizing: border-box;
  border: 2px solid #ccc;
  border-radius: 4px;
  background-color: #white;
  font-size: 16px;
  resize: none;
}
input[type=text] {
  width: 80%;
  box-sizing: border-box;
  border: 2px solid #ccc;
  border-radius: 4px;
  font-size: 16px;
  background-color: #eee;
  background-image: url('searchicon.png');
  background-position: 10px 10px; 
  background-repeat: no-repeat;
  padding: 12px 20px 12px 40px;
  -webkit-transition: width 0.4s ease-in-out;
  transition: width 0.4s ease-in-out;
}

input[type=text]:focus {
  width: 100%;
}
select {
width: 200px;
padding: .8em .5em;
border: 1px solid #999;
border-radius: 0px;

}

.button {
  display: inline-block;
  padding: 10px 30px;
  font-size: 10px;
  cursor: pointer;
  text-align: center;
  outline: none;
  color: #333333;
  background-color: white;
  border: 1px solid #333333;
  border-radius: 5px;
  box-shadow: 0 2px #999;
  font-size :0.8em;
}

.button:hover {
border: 1px solid #4867e1;
}

.imgtag{ 
	width: 100%; 
	display: block;
	margin-botton: -3px;

}

.b_search{
  display: inline-block;
  padding: 7px 15px;
  font-size: 10px;
  cursor: pointer;
  text-align: center;
  outline: none;
  color: #333333;
  background-color: white;
  border: 1px solid #333333;
  border-radius: 5px;
  box-shadow: 0 2px #999;
  font-size :1em;
  
}
.b_search:hover {
border: 1px solid #4867e1;
}
</style>
</head>
<body>
   <!-- 0311 jung main page 메뉴막대 부분, 한섹션식 스크롤 되도록 설정 -->
   <nav class="navbar-default navbar-fixed-top">
      <div class="navbar-header">
         <!-- 0311 jung 메뉴 토글 부분 -->
         <button type="button" class="navbar-toggle" data-toggle="collapse"
            data-target=".navbar-collapse">
            <span class="icon-bar"></span> 
            <span class="icon-bar"></span> 
            <span class="icon-bar"></span>
         </button>
         <!-- 0311 jung 로고 클릭시 메인화면으로 다시 불러오기 -->
         
      </div>
      <div id="color-change">
      <div class="navbar-collapse collapse">     
      <a href="http://localhost:8000/backstreet/main" class="navbar-brand"><img src="resources/images/blacklogo.png"  width="30%" ></a>            
	<br>
      <ul class="nav navbar-nav" id="mainlist">      
      <li><a href="http://localhost:8000/backstreet/intro"><span class="mainspan"> 사용가이드</span></a></li>
      <li><a href="http://localhost:8000/backstreet/map"><span class="mainspan"> 골목상권 분석</span></a></li>
      <li><a href="http://localhost:8000/backstreet/consulting"><span class="mainspan"> 내 상권 찾기</span></a></li>
      <li><a href="http://localhost:8000/backstreet/newConsulting"><span class="mainspan"> 창업 컨설팅</span></a></li>
      <li><a href="http://localhost:8000/backstreet/trands"><span class="mainspan"> 트랜드</span></a></li>
      <li><a href="http://localhost:8000/backstreet/boardmain"><span class="mainspan">고객센터</span></a></li>
	<c:if test="${requestScope.code eq null}">
		<li id="in"><a href="http://localhost:8000/backstreet/loginView"><span class="mainspan">로그인</span></a></li>
	</c:if>
	<c:if test="${requestScope.code ne null}">
		<li id="in"><a href="http://localhost:8000/backstreet/logout?accessToken=${requestScope.accessToken}"><span class="mainspan">로그아웃</span></a></li>
	</c:if>
      </ul>
      </div>
      </div>
   </nav> 
         <script src="resources/js/jquery-2.2.4.min.js"></script>
      <script src="resources/js/bootstrap.min.js"></script>
      <script src="resources/css/jquery.fullpage.js"></script>

      <script>
         $(document).ready(function() {
            $("#fullpage").fullpage();
         });
      </script>



      <script src="resources/js/jquery-2.2.4.min.js"></script>
      <script src="resources/js/jquery.vgrid.min.js"></script>
      <script>
         $('#container').vgrid({
            time : 400,
            delay : 30,
            wait : 500
         });
      </script>
   <br><br><br><br><br><br><br>
   
      
 <div id="all">
<h3><img src="resources/images/q&a.svg" style="width: 30px">&nbsp;자주하는 질문</h3>
<br>
<button class="b_search" type="button" onclick="location.href='http://localhost:8000/backstreet/search?action=search&searchType=title&keyword=환불'">#환불</button>
<button class="b_search"  type="button" onclick="location.href='http://localhost:8000/backstreet/search?action=search&searchType=title&keyword=유료'">#유료 결제</button>
<button class="b_search"  type="button" onclick="location.href='http://localhost:8000/backstreet/search?action=search&searchType=title&keyword=홈페이지'">#홈페이지 사용</button>
<button class="b_search"  type="button" onclick="location.href='http://localhost:8000/backstreet/search?action=search&searchType=title&keyword=컨설팅'">#골목상권 컨설팅</button>
<button class="b_search"  type="button" onclick="location.href='http://localhost:8000/backstreet/search?action=search&searchType=title&keyword=골목상권'">#골목상권 분석</button>
<br>
<br>
<div id="search">
<form method = "get" action ="/backstreet/search">
<input type="hidden" name="action" value="search">
<select name="searchType">
<option value = "content">내용</option>
<option value = "title">제목</option>
<option value = "writer">작성자</option>
</select>
<input type = "text" placeholder="search.." name = "keyword" >
<input type = "submit" value = "게시판 검색" class="button">
</form>
</div>
<hr>

<%String ac = (String)request.getAttribute("ac");%>

<!-- http://localhost:8000/backstreet/search?action=search&searchType=title&keyword=환불 -->
<%
	ArrayList<BoardVO> list = (ArrayList<BoardVO>)request.getAttribute("list");
	BoardVO listone = (BoardVO)request.getAttribute("listOne");
	System.out.println("listone:"+listone);
	if(list !=null){
%>
	<hr>
	<table class="type1">
	<tr>
	<td>번호</td>
	<td>제목</td>
	<td>작성자</td>
	<td>작성일</td>
	<td>조회수</td>
	</tr>
<%
	for(BoardVO vo : list){
%>
			<tr>
			<td><%= vo.getBoard_id() %></td>
			<td onclick="location.href='/backstreet/listOne?board_id=<%= vo.getBoard_id() %>'"><%= vo.getTitle() %></td>
			<td onclick="location.href='/backstreet/writer?writer=<%= vo.getWriter() %>'"><%= vo.getWriter() %></td>
			<td id="d"><%= vo.getWritedate() %></td>
			<td><%= vo.getCnt() %></td>
			</tr>			
<%
		}
%>
	</table>
	<hr>

	
	
<%
	}
	if (request.getAttribute("msg") != null) {
%>
	<script>
		alert('${ msg }');
	</script>

<%
	}
%>

<div id="writetext">
<button onclick="displayDiv(1);" class="button">게시판 작성</button>
</div>

<%
	
	if (listone != null) {
		System.out.println("JSP 출력 :" + listone);
%>

<div id="reading">
<form method = "post" action = "/backstreet/update">
<input type="hidden" name="action" value="update">
<input type="hidden" name="id" value="<%= listone.getBoard_id() %>">
<br>
<input id="m_writer" type="text"  name="writer" value="<%= listone.getWriter() %>">
<br>
<input id="m_title" type="text" name="title"  value="<%= listone.getTitle() %>">
<br>
<textarea id="m_content"  rows="3" cols="30" name = "content" ><%= listone.getContent() %></textarea>
<br>
<input type= "button" value ="확인" onclick="displayDiv(3)" class="button">
<input type= "submit" value ="수정" class="button" >
<input type= "button" value ="삭제" onclick="location.href ='/backstreet/delete?board_id=<%= listone.getBoard_id()%>'" class="button" >
</form>
</div>
<hr>
<%
	}
%>

<script>
function displayDiv(type) {
	if(type == 1) {
		document.getElementById("write").style.display='block';
	}else if(type == 2) {
		document.getElementById("write").style.display='none';
	}
	else if(type == 3){
		document.getElementById("reading").style.display='none';
	}
}
</script>
<div id="write"  style="display:none">
<form method = "post" action = "/backstreet/insert">
<input type="hidden" name="action" value="insert">
<input type="text"  name="writer" placeholder="작성자명을 입력해주세요">
<br>
<input type="text" name="title" placeholder="제목을 입력해주세요">
<br>
<textarea rows="3" cols="30" name = "content" placeholder="내용을 입력해주세요" ></textarea>
<br>
<input type = "submit" value = "저장" class="button">
<input type = "reset" value = "재작성" class="button">
<button onclick="displayDiv(2);return false;" class="button">취소</button>
</form>
<br>
</div>



</div>


   
   
  

</body>
</html>