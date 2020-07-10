# day8 (Open API 활용하여 수집)

### Rest API

- **url** 문자를 이용하여 서버에 요청하고 **xml** 혹은 **json**, **csv** 로 받아온다

  ```
  입력 : url
  리턴 : xml, json, csv
  ```

- Representational State Transfer API 의 약자로 클라이언트의 요청대로 리턴



```r
# SNS의 Open API 활용
library(rvest)
library(XML)
library(httr) # POST 사용 가능한 패키지
rm(list=ls())
searchUrl<- "https://openapi.naver.com/v1/search/blog.xml"
Client_ID <- "izGsqP2exeThwwEUVU3x"
Client_Secret <- "WrwbQ1l6ZI"

query <- URLencode(iconv("코로나","euc-kr","UTF-8"))
# add_headers 때문에 GET 방식으로 요청했다.
# hearder 에 뭔가 추가하거나 POST 방식일대 httr 패키지 사용
url <- paste0(searchUrl,"?query=",query,"&display=100")
doc<- GET(url
          , add_headers('Content-Type' = "application/xml", 'X-Naver-Client-Id' = Client_ID, 'X-Naver-Client-Secret' = Client_Secret))

# 블로그 내용에 대한 리스트 만들기		
doc2 <- htmlParse(doc, encoding="UTF-8")
text<- xpathSApply(doc2, "//item/description", xmlValue)
text
## 정규표현식 </?b> 의 의미 : ?는 바로 앞 문자가 0번 또는 1번
text <- gsub("</?b>", "", text)
## 정규 표현식 . 과 + : . 은 임의의 문자, + 는 1번 이상
## 즉 .+ 는 어떤 문자든 1번 이상 오면 찾아준다
text <- gsub("&.+t;", "", text)
text

# 네이버 뉴스 연동  
searchUrl<- "https://openapi.naver.com/v1/search/news.xml"
Client_ID <- "izGsqP2exeThwwEUVU3x"
Client_Secret <- "WrwbQ1l6ZI"
query <- URLencode(iconv("코로나","euc-kr","UTF-8"))
url <- paste0(searchUrl, "?query=", query, "&display=100")
doc <- GET(url, add_headers("Content_Type" = "application/xml",
                            "X-Naver-client-Id" = Client_ID, "X-naver-Client-Secret" = Client_Secret))

# 네이버 뉴스 내용에 대한 리스트 만들기		
doc2 <- htmlParse(doc, encoding="UTF-8")
text<- xpathSApply(doc2, "//item/description", xmlValue); 
text
text <- gsub("</?b>", "", text)
text <- gsub("&.+t;", "", text)
text


# 트위터 글 읽어오기
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

key <- "코로나"
key <- enc2utf8(key)
result <- search_tweets(key, n=500, token = twitter_token)
str(result)
result$retweet_text
content <- result$retweet_text
content <- gsub("[[:lower:][:upper:][:digit:][:punct:][:cntrl:]]", "", content)   
content


library(XML)
API_key  <- "%2BjzsSyNtwmcqxUsGnflvs3rW2oceFvhHR8AFkM3ao%2Fw50hwHXgGyPVutXw04uAXvrkoWgkoScvvhlH7jgD4%2FRQ%3D%3D"
bus_No <- "402"
url <- paste("http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList?ServiceKey=", API_key, "&strSrch=", bus_No, sep="")
doc <- xmlParse(url, encoding="UTF-8")
top <- xmlRoot(doc)
top
# getNodeSet 함수 -> //itemList 조상이 누구든간에 itemList 를 찾아서
# xmlToDataFrame -> 데이터 프레임으로 바꿔라
df <- xmlToDataFrame(getNodeSet(doc, "//itemList"))
df
str(df)

busRouteId <- df$busRouteId
busRouteId




url <- paste("http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid?ServiceKey=", API_key, "&busRouteId=", busRouteId, sep="")
doc <- xmlParse(url, encoding="UTF-8")
top <- xmlRoot(doc)
top
df <- xmlToDataFrame(getNodeSet(doc, "//itemList"))
df

# 서울시 빅데이터- XML 응답 처리
# http://openapi.seoul.go.kr:8088/796143536a756e69313134667752417a/xml/LampScpgmtb/1/100/

library(XML)
key = '796143536a756e69313134667752417a'
contentType = 'xml'
startIndex = '1'
endIndex = '200'
url = paste0('http://openapi.seoul.go.kr:8088/',key,'/',contentType,'/LampScpgmtb/',startIndex,'/',endIndex,'/')

con <- url(url, "rb") 
imsi <- read_html(con)
t <- htmlParse(imsi, encoding="UTF-8")
upNm<- xpathSApply(t,"//row/up_nm", xmlValue) 
pgmNm<- xpathSApply(t,"//row/pgm_nm", xmlValue)
targetNm<- xpathSApply(t,"//row/target_nm", xmlValue)
price<- xpathSApply(t,"//row/u_price", xmlValue)

df <- data.frame(upNm, pgmNm, targetNm, price)
View(df)
write.csv(df, "edu.csv")

# 한국은행 결제 통계시스템 Open API - JSON 응답 처리
library(jsonlite)
key = '/4WQ7X833TXC370SUTDX4/'
contentType = 'json/'
startIndex = '1'
endIndex = '/100/'

url <- paste0('http://ecos.bok.or.kr/api/KeyStatisticList',key,contentType,'kr/',startIndex,endIndex)
response <- GET(url)
json_data <- content(response, type = 'text', encoding = "UTF-8")
json_obj <- fromJSON(json_data)
df <- data.frame(json_obj)
df <- df[-1]
names(df) <- c("className", "unitName", "cycle", "keystatName", "dataValue")
View(df)


### apply 계열의 함수를 알아보자
weight <- c(65.4, 55, 380, 72.2, 51, NA)
height <- c(170, 155, NA, 173, 161, 166)
gender <- c("M", "F","M","M","F","F")

df <- data.frame(w=weight, h=height)
df
View(df)

apply(df, 1, sum, na.rm=TRUE) ## 함수 뒤에오는 것들은 함수에 영향을 준다
apply(df, 2, sum, na.rm=TRUE)

# lapply : MARGIN 주지 않는다...열단위로 실행
lapply(df, sum, na.rm=TRUE) #=MARGIN=2

# sapply : vector 로 할수있으면 vector,,안되면 list, matrix
#           MARGIN 주지 않는다...열단위로 실행
sapply(df, sum, na.rm=TRUE)

# tapply : 그룹화하여 실행
tapply(1:6, gender, sum, na.rm=TRUE)
tapply(df$w, gender, mean, na.rm=TRUE)

# mapply : 함수가 가장 첫번째 매개변수에 온다...가변형이기 때문
mapply(paste, 1:5, LETTERS[1:5], month.abb[1:5])


v<-c("abc", "DEF", "TwT")
sapply(v, function(d) paste("-",d,"-", sep="")) # 간단하게 named 로 리턴

l<-list("abc", "DEF", "TwT")
sapply(l, function(d) paste("-",d,"-", sep=""))
lapply(l, function(d) paste("-",d,"-", sep=""))

flower <- c("rose", "iris", "sunflower", "anemone", "tulip")
length(flower)
nchar(flower)
sapply(flower, function(d) if(nchar(d) > 5) return(d))## NULL 값이 리턴되는데 NULL 은 데이터셋 영역이므로 list 로 리턴된다
sapply(flower, function(d) if(nchar(d) > 5) d)
sapply(flower, function(d) if(nchar(d) > 5) return(d) else return(NA))
sapply(flower, function(d) paste("-",d,"-", sep=""))
sapply(flower, function(d, n) if(nchar(d) > n) return(d), 4) #function 의 첫번째 아규먼트만 앞에 주어진 데이터셋

count <- 1
myf <- function(x, wt=T){
  print(paste(x,"(",count,")"))
  Sys.sleep(0.5)
  if(wt) 
    r <- paste("*", x, "*")
  else
    r <- paste("#", x, "#")
  count <<- count + 1;
  return(r)
}
result <- sapply(df$w, myf)
length(result)
result
sapply(df$w, myf, F)
sapply(df$w, myf, wt=F)
rr1 <- sapply(df$w, myf, wt=F)
str(rr1)

count <- 1
sapply(df, myf)
rr2 <- sapply(df, myf)
str(rr2)
rr2[1,1]
rr2[1,"w"]

#1부터 26사이의 값들 중에서 10개를 추출하여 v라는 변수에 저장한후에
#추출된 숫자에 해당하는 알파벳 대문자를 원소값으로 벡터를 생성하는 코드를 작성하시오.


v<- sample(1:26,10)
v2<-sapply(v,function(x) return(LETTERS[x]))
        
        
```

