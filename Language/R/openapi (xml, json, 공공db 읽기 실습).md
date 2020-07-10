# openapi (xml, json, 공공db 읽기 실습)

```r
library(rvest)
library(XML)
library(httr) # POST 사용 가능한 패키지
rm(list=ls())

## 네이버 블로그 "맛집" 검색
searchUrl<- "https://openapi.naver.com/v1/search/blog.xml"
Client_ID <- "izGsqP2exeThwwEUVU3x"
Client_Secret <- "WrwbQ1l6ZI"

query <- URLencode(iconv("맛집","euc-kr","UTF-8"))
url <- paste0(searchUrl,"?query=",query,"&display=100")
doc <- GET(url
           , add_headers('Content-Type' = "application/xml", 'X-Naver-Client-Id' = Client_ID, 'X-Naver-Client-Secret' = Client_Secret))
doc2 <- htmlParse(doc,encoding="UTF-8")
text <-xpathSApply(doc2,"//item/description",xmlValue)
text
text<-gsub("</?b>","",text)
text<-gsub("&.+t;","",text)
text
write(text,"naverblog.txt")


## 트위터 "취업" 검색
install.packages("rtweet")
library(rtweet) 
appname <- "edu_data_collection"
api_key <- "RvnZeIl8ra88reu8fm23m0bST"
api_secret <- "wTRylK94GK2KmhZUnqXonDaIszwAsS6VPvpSsIo6EX5GQLtzQo"
access_token <- "959614462004117506-dkWyZaO8Bz3ZXh73rspWfc1sQz0EnDU"
access_token_secret <- "rxDWfg7uz1yXMTDwijz0x90yWhDAnmOM15R6IgC8kmtTe"
twitter_token <- create_token(
  app = appname,
  consumer_key = api_key,
  consumer_secret = api_secret,
  access_token = access_token,
  access_secret = access_token_secret)

key <- "취업"
key <- enc2utf8(key)
result <- search_tweets(key, n=100, token = twitter_token)
str(result)
result$retweet_text
content <- result$retweet_text
content <- gsub("[단독]", "", content)   
content <- gsub("[[:punct:]]", "", content)   
content <- gsub("[[:cntrl:]]", "", content)   
content <- gsub("[A-z]", "", content)   
content<-na.omit(content)
View(content)
write.table(content,"twitter.txt")


## 공공 DB 버스 정보 가져오기
library(XML)
API_key  <- "%2BjzsSyNtwmcqxUsGnflvs3rW2oceFvhHR8AFkM3ao%2Fw50hwHXgGyPVutXw04uAXvrkoWgkoScvvhlH7jgD4%2FRQ%3D%3D"
bus_No <- "360"
url <- paste("http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList?ServiceKey=", API_key, "&strSrch=", bus_No, sep="")
doc <- xmlParse(url, encoding="UTF-8")
top <- xmlRoot(doc)
str(top)
df<-xmlToDataFrame(getNodeSet(doc,"//itemList[1]"))
View(df)
ul<-unlist(df)
View(df$busRouteId)
bnum <- paste("[",df$busRouteNm,"번 버스정보]",sep="")
bID <- paste("노선 ID : ",df$busRouteId,sep="")
blength <- paste("노선 길이 : ",df$length,"km",sep="")
bst <- paste("기점 : ",df$stStationNm,sep="")
bed <- paste("종점 : ",df$edStationNm,sep="")
bterm <- paste("배차간격 : ",df$term,"분",sep="")
cat(bnum,bID,blength,bst,bed,bterm,sep="\n")



## 네이버 뉴스 "빅데이터" 검색
searchUrl<- "https://openapi.naver.com/v1/search/news.json"
Client_ID <- "izGsqP2exeThwwEUVU3x"
Client_Secret <- "WrwbQ1l6ZI"
query <- URLencode(iconv("빅데이터","euc-kr","UTF-8"))
url <- paste0(searchUrl, "?query=", query, "&display=100")
doc <- GET(url, add_headers("Content_Type" = "application/json",
                            "X-Naver-client-Id" = Client_ID, "X-naver-Client-Secret" = Client_Secret))

# 네이버 뉴스 내용에 대한 리스트 만들기		
json_data<-content(doc,type='text',encoding="UTF-8")
json_obj<-fromJSON(json_data)
df2<-data.frame(json_obj)
View(df2)
title<-df2$items.title
title<-gsub("</?b>","",title)
title<-gsub("&.+t;","",title)
write(title,"navernews.txt")
write.csv(title,"navernews.csv")


```

