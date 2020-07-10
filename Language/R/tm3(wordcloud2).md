# tm3(wordcloud2)

```r
#[ 텍스트마이닝 실습(3) ]


library("htmlwidgets") # 없으면 설치
saveWidget(result,"wc.html",title="WORDCLOUD2 실습", selfcontained = F)

yes<-readLines("yes24.txt")
yes %>% 
  extractNoun() %>% 
  unlist() %>% 
  gsub("[^가-힣]","",.) %>% 
  Filter(function(x) {nchar(x)>=2&nchar(x)<=4},.) %>%
  table() %>% 
  sort(decreasing=T)%>% 
#  head() %>% 
  as.data.frame()->yes2


result<-wordcloud2(data = yes2, shape = 'pentagon',fontFamily="휴먼옛체",backgroundColor="black")
saveWidget(result,"wc.html",title="WORDCLOUD2 실습", selfcontained = F)

  

```

![image-20200416163521843](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200416163521843.png)