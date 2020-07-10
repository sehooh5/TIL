# chartExam

```r
#데이터 가져오기
log<-read.table("data/product_click.log",sep="\t")
timeline<-log$V1
pd<-log$V2
View(log)
#문제 1
#제품당 클릭 갯수에 대한 데이터를 가지고 다음 조건으로 그래프를 그린다.
#칼라는 terrain.colors 칼라로 설정한다.
#그래프 메인 제목 : "세로바 그래프 실습"
#clicklog1.png 에 저장한다.
png(filename="clicklog1.png", bg="white")
pd2<-summary(pd)
pd3<-as.vector(pd2)
names(pd3)<-c(paste("p00",1:9,sep=""),"p010")
barplot(pd3,main="세로바 그래프 실습",ylab="클릭수",col=terrain.colors(10),las=2)




#문제2
#상품이 클릭된 시간 정보를 가지고 다음 조건의 그래프를 그린다
#그래프 메인 제목 : "파이그래프 실습"
#clicklog2.png 에 저장한다.
png(filename="clicklog2.png", bg="white")
t2<-substr(timeline,start=9,stop=10)
t3<-table(t2)
t4<-data.frame(t3)
t5<-as.numeric(levels(t4$t2))

warnings()
pie(t3, labels=paste(t5, "~",t5+1),breaks=24, col=rainbow(10), main="파이그래프 실습")
dev.off()
```

