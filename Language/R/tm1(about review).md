# tm1(about review)

```r
#[ 텍스트마이닝 실습(1) ]

#hotel.txt를 읽고 제일 많이 나온 명사 10개를 명칭과 횟수(내림차순)로 구성되는 데이터프레임을 생성해서 
#hotel_top_word.csv 로 저장한다. 작성된 R 소스는 tm1.R 저장한 후에 tm1.R 과 hotel_top_word.csv 을제출한다.



hotel_word<-readLines("hotel.txt")
hotel_word 



hotel_word2<-sapply(hotel_word,extractNoun,USE.NAMES = F)
hotel_word2

un_hword<-unlist(hotel_word2)
un_hword
un_hword<-gsub("[^가-힣]","",un_hword)

un_hword2 <- Filter(function(x) {nchar(x) >= 2}, un_hword)
tb_hword<-table(un_hword2)
tb_hword


final_hotel <- sort(tb_hword, decreasing = T)
top10<-head(final_hotel,10)
df_top<-as.data.frame(top10)

write.csv(df_top,"hotel_top_word.csv")

```

