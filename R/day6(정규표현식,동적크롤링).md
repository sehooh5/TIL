# day6(정규표현식,동적크롤링)

## 동적 크롤링

- 정적 웹페이지인지 동적 웹페이지인지 먼저 체크

- **selenium**이라는 웹브라우저를 자동화 하는 도구모음을 사용한다

  브라우저를 기동시키고 일을 시키며 관리하는 API 이자 Tool

  


[ Selenium 서버기동과정]
(1) selenium-server-standalone-master.zip, chromedriver.exe 를복사한다.
(2) 적당한디렉토리에selenium-server-standalone-master.zip 파일의압축을
해제한다
(3) bin 디렉토리안에chromedriver.exe 를복사한다.
(4) Selenium 을기동시킨다. (박스속의명령을CMD 창에서실행시켜야한다.)

```
java -jar selenium-server-standalone.jar -port 4445
```

- https://www.selenium.dev/ 참고



- API 자료

![image-20200327160252228](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200327160252228.png)

- 하나의 돔객체에서만 처리 가능

- **sapply**(doms,function(x){x$getElem로컬 파일에서 이미지 선택entText()}) 사용해 *반복을 줄여준다*

  : simple apply -> return vector or list

  : 전달된 dom 객체들가지고 function 의 매개변수로 들어가 실행시켜준다





```r
# 정규표현식 사용
word <- "JAVA javascript Aa 가나다 AAaAaA123 %^&* Aaa"
gsub(" ", "@", word)# 전부 다 변환
sub(" ", "@", word)# 맨 처음 하나만 변환
gsub("A", "", word) 
gsub("a", "", word) 
gsub("Aa", "", word) 
sub("Aa", "", word)
gsub("(Aa)", "", word) 
gsub("(Aa){2}", "", word) #{} 횟수 주기,,연속 2번인 애들만 변환
gsub("Aa{2}", "", word) # 뒤에꺼만 2번 반복
gsub("[Aa]", "", word) # A and a 모두 찾아서 지움 
gsub("[가-힣]", "", word) 
gsub("[^가-힣]", "", word) 
gsub("[&^%*]", "", word) 
gsub("[[:punct:]]", "", word) 
gsub("[[:alnum:]]", "", word) 
gsub("[1234567890]", "", word) 
gsub("[0-9]", "", word) 
gsub("\\d", "", word); gsub("\\D", "", word)  # \\ = escape , d = 숫자, D = non-digit
gsub("[[:digit:]]", "", word) 
gsub("[^[:alnum:]]", "", word) 
gsub("[[:space:]]", "", word) 



#### 동적 스크롤링 #####
#Selenium 설치
install.packages("RSelenium")
library(RSelenium)
remDr<-remoteDriver(remoteServerAddr= "localhost" ,
                    port = 4445, browserName= "chrome")
remDr$open() # 크롬이 기돈된다
remDr$navigate("http://www.google.com/")

webElem<-remDr$findElement(using = "css", "[name = 'q']")
webElem$sendKeysToElement(list("JAVA", key = "enter"))

remDr$navigate("http://www.naver.com/")

webElem<-remDr$findElement(using = "css", "[name = 'query']")
webElem$sendKeysToElement(list("JAVA", key = "enter"))




# [ 네이버 웹툰 댓글 읽기 ]
url<-'http://comic.naver.com/comment/comment.nhn?titleId=570503&no=135'
remDr$navigate(url)

#단수형으로 노드 추출
more<-remDr$findElement(using='css','#cbox_module > div > div.u_cbox_sort > div.u_cbox_sort_option > div > ul > li:nth-child(2) > a')
more$getElementTagName() # 태그이름 가져오기
more$getElementText() # Text 가져오기
more$clickElement() # 클릭이벤트 실행


# 2페이지부터 10페이지까지 링크 클릭하여 페이지 이동하기 
# 2page selector : #cbox_module > div > div.u_cbox_paginate > div > a:nth-child(4)
# 3page selector : #cbox_module > div > div.u_cbox_paginate > div > a:nth-child(5)
# 7page selector : #cbox_module > div > div.u_cbox_paginate > div > a:nth-child(9)
# 10 page selector : #cbox_module > div > div.u_cbox_paginate > div > a:nth-child(12)
# 이렇게 반복되는 공식 찾아서 반복시켜준다
for (i in 4:12) {
  nextCss <- paste0("#cbox_module>div>div.u_cbox_paginate>div> a:nth-child(",i,") > span")
  nextPage<-remDr$findElement(using='css',nextCss)
  nextPage$clickElement()
  Sys.sleep(2)
}

#복수형으로 노드 추출 
url<-'http://comic.naver.com/comment/comment.nhn?titleId=570503&no=135'
remDr$navigate(url)
#베스트 댓글 내용 읽어오기
bestReviewNodes<-remDr$findElements(using ="css selector","ul.u_cbox_list span.u_cbox_contents")
sapply(bestReviewNodes,function(x){x$getElementText()})

#전체 댓글 링크 클릭후에 첫 페이지 내용 읽어오기
#전체댓글 클릭이벤트 selector : cbox_module > div > div.u_cbox_sort > div.u_cbox_sort_option > div > ul > li:nth-child(2) > a
totalReview <- remDr$findElement(using='css','#cbox_module > div > div.u_cbox_sort > div.u_cbox_sort_option > div > ul > li:nth-child(2) > a')
totalReview$clickElement()
totalReviewNodes<-remDr$findElements(using ="css selector","ul.u_cbox_list span.u_cbox_contents")
sapply(totalReviewNodes,function(x){x$getElementText()})


```

