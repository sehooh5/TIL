# tm2(about buying)

```r
제공된 “공구.txt” 파일의 내용을 읽고 명사만 뽑아내서 전처리한 다음 많이 등장한 단어 순으로 다음과 같이 워드클라우딩 하는 R 코드를 작성하여 tm2.R 로 그리고 워드 클라우딩 결과는 wc.png 로 저장하여 제출하시오. 이미지이므로 
기본 그래프를 저장하는 방법과 동일하다. 가장 많이 등장한 단어의 크기가 가장 크게 처리하고 점점 작아지게 하면 되며 칼라나 폰트의 종류 그리고 크기는 다르게 하더라도 출력되는 단어의 구성은 최대한 맞춰본다. 한 글자는 제외한다. 
그리고 전처리시 숫자, 특수문자 그리고 영어 등은 모두 삭제한다. 

gg_text<-readLines("공구.txt",encoding="UTF-8")
gg_text 


gg_text2<-sapply(gg_text,extractNoun,USE.NAMES = F)
gg_text2

un_gg<-unlist(gg_text2)
un_gg
un_gg<-gsub("^공구*.","",un_gg)
un_gg<-gsub("^주세","",un_gg)
un_gg<-gsub("[^가-힣]","",un_gg)
un_gg<-gsub("원해","",un_gg)
un_gg<-gsub("필요해요","",un_gg)
un_gg<-gsub("프라우반","",un_gg)
un_gg<-gsub("해요","",un_gg)
un_gg<-gsub("언제","",un_gg)
un_gg<-gsub("재공구","",un_gg)
un_gg<-gsub("구매","",un_gg)

un_gg<-Filter(function(x) {nchar(x) >= 2}, un_gg)  
tb_gg<-table(un_gg)
arr_gg<-sort(tb_gg, decreasing = T)
df_gg<-as.data.frame(arr_gg)
head(df_gg,10)
png("wc.png",width = 400, height = 400)
wordcloud(df_gg$un_gg, df_gg$Freq, 
          min.freq = 2, 
          random.order = F,
          rot.per = 0.3, scale = c(6, 1), 
          random.color = T,
          colors = rainbow(7)) 

windowsFonts(lett=windowsFont("휴먼옛체"))

dev.off()

warning()

```

