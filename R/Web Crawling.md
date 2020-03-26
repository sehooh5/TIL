# webcrawling

#### scraping

```r
url1 <- "http://unico2013.dothome.co.kr/crawling/exercise_bs.html"
scrap1 <- read_html(url1)
#h1 태그의 컨텐츠
nodes_1 <- html_nodes(scrap1,"h1")
html_text(nodes_1)

#텍스트 형식으로 내용을 가지고 있는 <a> 태그의 컨텐츠와 href 속성값
a_text <- html_nodes(scrap1,"a")
html_text(a_text)
html_attr(a_text,"href")

#<img> 태그의 src 속성값
img_text <- html_nodes(scrap1,"img")
html_attr(img_text,"src")

#첫 번째 <h2> 태그의 컨텐츠
h2_text <- html_nodes(scrap1,"h2")
html_text(h2_text)[1]

#<ul> 태그의 자식 태그들 중 style 속성의 값이 green으로 끝나는 태그의 컨텐츠
#ul>li:nth-child(n)
ul_text_green <- html_nodes(scrap1,"ul>*[style$=green]")
html_text(ul_text_green)

#두 번째 <h2> 태그의 컨텐츠
html_text(h2_text)[2]

#<ul> 태그의 모든 자식 태그들의 컨텐츠
ul_text <- html_nodes(scrap1,"ul>*")
ol_text <- html_nodes(scrap1,"ol>*")
html_text(ol_text)

#<table> 태그의 모든 자손 태그들의 컨텐츠 
table_text <- html_nodes(scrap1,"table *")
html_text(table_text)
table_text <- html_nodes(scrap1,"table>*>*")

#name이라는 클래스 속성을 갖는 <tr> 태그의 컨텐츠
tr_text_name <- html_nodes(scrap1, "tr.name")
html_text(tr_text_name)

#target이라는 아이디 속성을 갖는 <td> 태그의 컨텐츠
td_text_target <- html_nodes(scrap1, "td#target")
html_text(td_text_target)

```





#### movie1

```r
daum<-NULL
url<-"https://movie.daum.net/moviedb/grade?movieId=131576" 
daum <- read_html(url)
point <- html_nodes(daum,".raking_grade em.emph_grade")
point_content <- html_text(point)

review <- html_nodes(daum,"p.desc_review")
review_content <- html_text(review,trim=T)

if(length(review_content)==10){
  page <- cbind(point_content,review_content)
  write.csv(page,"daummovie1.csv")
}else{
  stop("리뷰 개수가 부족합니다")
}
View(page)

```





#### movie2(반복문해서 페이지 넘기기)

```r
text<- NULL
movie.review <- NULL
url<-"https://movie.daum.net/moviedb/grade?movieId=131576&page=" 

for(p.num in 1:5){
  url_p <- paste(url,p.num,sep="")  
  text <- read_html(url_p)
  nodes <- html_nodes(text,".raking_grade em.emph_grade")
  point <- html_text(nodes)
  nodes <- html_nodes(text,"p.desc_review")
  imsi <- html_text(nodes,trim=T)
  review <- imsi[nchar(imsi) > 0] 
  if(length(review) == 10) {
    page <- cbind(point, review)
    movie.review <- rbind(movie.review, page)
  } else {
    cat(paste(i," 페이지에는 리뷰글이 생략된 데이터가 있어서 수집하지 않습니다.ㅜㅜ\n"))
  }
}
View(page)
write.csv(movie.review, "daummovie2.csv")

```





#### news

```r
url <- "https://media.daum.net/ranking/popular/"
text<-NULL
review<-NULL
text<-read_html(url)
select.start<-"#mArticle > div.rank_news > ul.list_news2 > li:nth-child("
select.end<-") > div.cont_thumb > strong > a"
select.start2<-"*> li:nth-child("
select.end2<-") > div.cont_thumb > strong > span"
for(i in 1:50){
#title
select<-paste(select.start,i,select.end,sep="" )
nodes<-html_nodes(text,select)
newstitle<-html_text(nodes,trim=T)
#name of news
select2<-paste(select.start2,i,select.end2,sep="")
nodes<-html_nodes(text,select2)
newspapername<-html_text(nodes,trim=T)
#bind 
#만약 바인딩할때 for 나가고 싶으면 rbind 로
#기존 파일 덮어씌워서 저장하면서 반복문 돌아가게 하면 된다
page<-cbind(newstitle,newspapername)
review <- rbind(review, page)
}
View(review)
write.csv(review, "daumnews.csv")

```

