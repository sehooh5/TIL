# starbucks

```r
remDr <- remoteDriver(remoteServerAddr = "localhost", port = 4445, browserName = "chrome")
remDr$open()
url <- "https://www.istarbucks.co.kr/store/store_map.do?disp=locale"
remDr$navigate(url)

# 서울 클릭
click <- remDr$findElement(using="css","#container > div > form > fieldset > div > section > article.find_store_cont > article > article:nth-child(4) > div.loca_step1 > div.loca_step1_cont > ul > li:nth-child(1) > a")
click$clickElement()
# 전체 클릭
click <- remDr$findElement(using="css","#mCSB_2_container > ul > li:nth-child(1) > a")
click$clickElement()

sizeCss <- "#container > div > form > fieldset > div > section > article.find_store_cont > article > article:nth-child(4) > div.loca_step3 > div.result_num_wrap > span"
size <- remDr$findElement(using="css",sizeCss)
limit <- size$getElementText()


#스크롤바 3개씩 내리는 기능


#매장명(shopname), 위도(lat), 경도(lng), 주소(addr) 그리고 전화번호(telephone)

# 매장명 뽑기
for(index in 1:as.numeric(limit)){
  #명,주소,번호 뽑기
  informCss <- paste("#mCSB_3_container > ul > li:nth-child(",index,")")
  inform <- remDr$findElement(using="css",informCss)
  informText <- inform$getElementText()  
  #개행 문자로 문자열 나누는 방법
  info <- strsplit(unlist(informText),split="\n")
  
  #위도, 경도 뽑기
  pointCss<-paste("#mCSB_3_container > ul > li:nth-child(",index,")")
  pointElem<-remDr$findElement(using="css",pointCss)
  lat<-pointElem$getElementAttribute("data-lat")
  lng<-pointElem$getElementAttribute("data-long")
  
  #파일 묶기
  csv <- data.frame(shopname = unlist(info)[1]
                    ,address = unlist(info)[2]
                    ,lat = unlist(lat)
                    ,lng = unlist(lng)
                    ,telephone = unlist(info)[3])
  file<- rbind(file,csv)
  
  #세개 읽은 후에 스크롤 내리기
  if(index %%3 == 0 && index != limit)
    remDr$executeScript(
      "var dom = document.querySelectorAll('#mCSB_3_container >ul >li')[arguments[0]];
    dom.scrollIntoView();",list(index)
    )
  }
  
  #test
  library(rvest)
  csv2<-NULL
  test <- remDr$findElement(using="css","#mCSB_3_container > ul > li:nth-child(1)")
   test$getElementAttribute("data-lat")

  first<- remDr$getElementAttribute
  i<-strsplit(unlist(t),split="\n")
  csv <- data.frame(shopname = unlist(i)[1]
                    ,address = unlist(i)[2]
                    ,telephone = unlist(i)[3]
                    ,lat = unlist(lat)
                    ,lng = unlist(lng))
  csv2 <- rbind(csv2, csv)
  View(csv2)
  write.csv(csv,file="text.csv")  
  
```

