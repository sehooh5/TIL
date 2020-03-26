# day5(Webcrawling)

## WebCrawling

#### revest, xml, httr 세 개의 패키지 사용



1. 서버한테서 웹페이지 내용을 끌어온다 

   : **read_html**(url,encding="CP949") -> *html_document* 객체를 리턴한다!(list)

   : html_nodes, html_text(x, trim=FALSE), html_attr(x,name,default="")

2. 웹페이지의 URL 구조와 문서 구조를 파악해야한다

   : 페이지 소스보기, 관리자 도구의 elements 등등 으로 파악

   : **CSS 선택자**, **xpath** 사용 -> **"rvest"** package 사용

- **선택자 및 xpath 는 작성 파일 참고할 것!**

```r

```



3. XML 패키지 사용

   - xpath 만 사용 가능

   - **htmlParse(file, encoding="…")**

   - **xpathSApply(doc, path, fun)** 

   - fun : **xmlValue, xmlGetAttr, xmlAttrs**

   - nodes 의 과정은 생략해주지만 두가지 function 사용은 불가,,

   - ```r
     gsub : 첫번째 아규먼트의 조건을 갖는 애들을 두번째로 바꿔라
     gsub('A','a',content) = 'A' to 'a'
     gsub('[0-9]','',content) = 0-9 숫자를 ''로 변경해라
     gsub('[0123456789]','',content)
     ```

   - ```r
     library(XML)
     imsi <-read_html("http://hankookilbo.com")
     t <-htmlParse(imsi) # 도큐먼트 객체 전달됨
     #xmlValue -> vector인 컨텐츠 를 전달
     content<-xpathSApply(t,"//p[@class='title']", xmlValue);
     content
     #[[안에 있는 애들은 "RegExCheatsheet.pdf" 참고]]
     content <-gsub("[[:punct:][:cntrl:]]", "", content)
     content
     #trimws = 앞 뒤에 있는 공백문자 없애주는 기능
     content <-trimws(content)
     content
     ```

4. httr 패키지

   - 유일하게 POST 방식 지원해주는 패키지
   - 좀 더 전문적인 패키지이다

```r
[ R에서GET으로사이트내용가져오기: httr패키지사용]
install.packages("httr")
library(httr)
http.standard<-GET('http://www.w3.org/Protocols/rfc2616/rfc2616.html')
title2 = html_nodes(read_html(http.standard), 'div.toch2')
title2 = html_text(title2)
Title2
[ R에서POST로사이트내용가져오기: httr패키지사용]
game = POST('http://www.gevolution.co.kr/score/gamescore.asp?t=3&m=0&d=week',
encode = 'form', body=list(txtPeriodW= '2020-03-15'))
title2 = html_nodes(read_html(game), 'a.tracktitle')
title2 = html_text(title2)
title2[1:10]
```







### day5 공부자료

```r
# 교재 81페이지
library() # 아래 내용과 비슷함, 전체 내용 보여주기 view 버전
installed.packages()
search() # load 된 패키지들 보여주는 함수
read_excel()
install.packages("readxl")
library(readxl) # require(readxl)
excel_data_ex <- read_excel("book/data_ex.xls")
getwd()
View(excel_data_ex)
search()
str(excel_data_ex)



######## 웹 크롤링과 스크래핑 ########

install.packages("rvest") 
library(rvest)
092-023
url <- "http://unico2013.dothome.co.kr/crawling/tagstyle.html"
text <- read_html(url)
text

nodes <- html_nodes(text, "div")
nodes
title <- html_text(nodes)
title

node1 <- html_nodes(text, "div:nth-of-type(1)")
node1
html_text(node1)
html_attr(node1, "style") # 속성 뽑아내기

node2 <- html_nodes(text, "div:nth-of-type(2)")
node2
html_text(node2)
html_attr(node2, "style")

node3 <- html_nodes(text, "div:nth-of-type(3)")
node3
html_text(node3)


# 단일 페이지(rvest 패키지 사용)
install.packages("rvest"); 
library(rvest)
text<- NULL
url<- "http://movie.naver.com/movie/point/af/list.nhn?page=1"
text <- read_html(url,  encoding="CP949")
text
# 영화제목
nodes <- html_nodes(text, ".movie")
title <- html_text(nodes)
title
# 영화평점
nodes <- html_nodes(text, ".title em")
point <- html_text(nodes)
point
# 영화리뷰 
nodes <- html_nodes(text, xpath="//*[@id='old_content']/table/tbody/tr/td[2]/text()")
imsi <- html_text(nodes, trim=TRUE)
review <- imsi[nchar(imsi) > 0] 
review
if(length(review) == 10) {
  page <- cbind(title, point)
  page <- cbind(page, review)
  write.csv(page, "movie_reviews.csv")
} else {
  cat("리뷰글이 생략된 데이터가 있네요ㅜㅜ\n")
}
## 혹시 여기서 리뷰 생략된 애들 NA 처리하고 출력하는 방법이 있는지?


library(XML)
# 여러 페이지
site<- "http://movie.naver.com/movie/point/af/list.nhn?page="#page 뒤에 숫자 달라짐
text <- NULL
movie.review <- NULL
##무한루프는 어떻게 주는지?
##읽어온 자료가 없으면 끝내는 구문 넣어주면 됨
for(i in 1: 100) {
  url <- paste(site, i, sep="")# paste 로 뒤에 숫자를 붙여줌
  text <- read_html(url,  encoding="CP949")
  nodes <- html_nodes(text, ".movie")
  title <- html_text(nodes)
  nodes <- html_nodes(text, ".title em")
  point <- html_text(nodes)
  nodes <- html_nodes(text, xpath="//*[@id='old_content']/table/tbody/tr/td[2]/text()")
  imsi <- html_text(nodes, trim=TRUE)
  review <- imsi[nchar(imsi) > 0] 
  if(length(review) == 10) {
    page <- cbind(title, point)
    page <- cbind(page, review)
    movie.review <- rbind(movie.review, page)
  } else {
    cat(paste(i," 페이지에는 리뷰글이 생략된 데이터가 있어서 수집하지 않습니다.ㅜㅜ\n"))
  }
}
write.csv(movie.review, "movie_reviews2.csv")


# 한국일보 페이지(XML 패키지 사용)
install.packages("XML")
library(XML)
imsi <- read_html("http://hankookilbo.com")
t <- htmlParse(imsi)
content<- xpathSApply(t,"//p[@class='title']", xmlValue); 
content
content <- gsub("[[:punct:][:cntrl:]]", "", content)
content
content <- trimws(content)
content

# httr 패키지 사용 - GET 방식 요청
install.packages("httr")
library(httr)
http.standard <- GET('http://www.w3.org/Protocols/rfc2616/rfc2616.html')
title2 = html_nodes(read_html(http.standard), 'div.toc h2')
title2 = html_text(title2)
title2

# httr 패키지 사용 - POST 방식 요청
library(httr)
library(rvest)
# POST 함수를 이용해 모바일 게임 랭킹 3월 15일 주  모바일 게임 랭킹을 찾는다
#(http://www.gevolution.co.kr/score/gamescore.asp?t=3&m=0&d=week) 
game = POST('http://www.gevolution.co.kr/score/gamescore.asp?t=3&m=0&d=week',
            encode = 'form', body=list(txtPeriodW = '2020-03-15'))
title2 = html_nodes(read_html(game), 'a.tracktitle')
title2 = html_text(title2)
title2[1:10]


# 뉴스, 게시판 등 글 목록에서 글의 URL만 뽑아내기 
res = GET('https://news.naver.com/main/list.nhn?mode=LSD&mid=sec&sid1=001')
htxt = read_html(res)
link = html_nodes(htxt, 'div.list_body a'); length(link)
article.href = unique(html_attr(link, 'href'))
article.href

# 이미지, 첨부파일 다운 받기 
# pdf
res = GET('http://cran.r-project.org/web/packages/httr/httr.pdf')
writeBin(content(res, 'raw'), 'c:/Temp/httr.pdf')

# jpg
h = read_html('http://unico2013.dothome.co.kr/productlog.html')
imgs = html_nodes(h, 'img')
img.src = html_attr(imgs, 'src')
for(i in 1:length(img.src)){
  res = GET(paste('http://unico2013.dothome.co.kr/',img.src[i], sep=""))
  writeBin(content(res, 'raw'), paste('c:/Temp/', img.src[i], sep=""))
} 

```

