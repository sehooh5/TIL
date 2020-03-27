# daumnews

```r
url <- "https://media.daum.net/ranking/popular/"
text<-NULL
review<-NULL
text<-read_html(url)
select.start<-"*> li:nth-child("
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

