# day13(공공데이터-08일, ggplot2)

### ggplot2

- `ggplot()` 함수 무조건 호출 후
- `+` 연산자를 사용하여 추가적으로 내용들을 추가해준다



```r
# 테이블을 읽어오자
# 정적페이지
library(XML)
install.packages("Rcpp")
library(rvest)
library(Rcpp)
url = "https://en.wikipedia.org/wiki/List_of_countries_and_dependencies_by_population"
read1 <- read_html(url)
read2 <- htmlParse(read1)
first_table <- getNodeSet(read2,"//table")[[1]]
xt <- readHTMLTable(first_table)
xt


# http://www.airkorea.or.kr/ : 한국환경공단 실시간 자료 조회
# 동적페이지
rm(list=ls())
library(RSelenium)
remDr <- remoteDriver(remoteServerAddr = "localhost" , port = 4445, browserName = "chrome")
remDr$open()
url <- "http://www.airkorea.or.kr/web/pmRelay?itemCode=11008&pMENU_NO=109"
remDr$navigate(url)

webElem <- remDr$findElement(using = "css", "#dateDiv_1 > img")
webElem$clickElement()
Sys.sleep(1)
webElem <- remDr$findElement(using = "css", "#ui-datepicker-div > table > tbody > tr:nth-child(2) > td:nth-child(7) > a")
webElem$clickElement()
Sys.sleep(1)
webElem <- remDr$findElement(using = "css", "#cont_body > form > div > div > a:nth-child(1)")
webElem$clickElement()
Sys.sleep(3)

library(XML)
elem <- remDr$findElement(using="css", value=".st_1")
elemtxt <- elem$getElementAttribute("outerHTML")
elem_html <- htmlTreeParse(elemtxt, asText = TRUE, useInternalNodes = T, encoding="UTF-8")
## [한글 깨지면 해줘야하는 추가 작업]] ##
Sys.setlocale("LC_ALL", "English") # location 변경
# readHTMLTable 하면 한글이 깨짐(이전까진 괜찮)
games_table <- readHTMLTable(elem_html, header = T, stringsAsFactors = FALSE, )[[1]]
Sys.setlocale() #  location 다시 한국으로
Encoding(names(games_table)) <- "UTF-8"
## 여기까지 ##
head(games_table)
str(games_table)
View(games_table)
tail(games_table)

# ggplot2 패키지를 활용한 고급시각화

install.packages("ggplot2") # ggplot2 패키지 설치
install.packages("glue") # ggplot2 로드시 이 패키지 오류나면 
library(ggplot2)
library(dplyr)

mpg <- as.data.frame(ggplot2::mpg)
str(mpg)
# 첫번째 매개변수 = data.frame
# 두번째 매개변수 = aes()
ggplot(mpg) + geom_point(aes(displ, hwy))
ggplot(mpg, aes(displ, hwy)) + geom_point(size=2)
# x축 displ, y축 hwy로 지정해 배경 생성
ggplot(data = mpg, aes(x = displ, y = hwy))
# 배경에 산점도 추가
ggplot(data = mpg, aes(x = displ, y = hwy)) + geom_point()
# 출력 범위 설정 coord_cartesian
ggplot(data = mpg, aes(x = displ, y = hwy)) + geom_point() + coord_cartesian(xlim=c(3, 6))
ggplot(data = mpg, aes(x = displ, y = hwy)) + geom_point() + coord_cartesian(xlim=c(3, 6), ylim=c(10, 30))
ggplot(data = mpg, aes(x = displ, y = hwy, col = drv)) + geom_point()
ggplot(data = mpg, aes(x = displ, y = hwy)) + geom_point(aes(color=drv))

data(airquality)
str(airquality)
View(airquality)
ggplot(airquality, aes(x=Day, y=Temp))
ggplot(airquality, aes(x=Day, y=Temp)) + geom_point()
ggplot(airquality, aes(x=Day, y=Temp)) + geom_point(size=3, color="red")
ggplot(airquality, aes(x=Day, y=Temp)) + geom_line()
ggplot(airquality, aes(x=Day, y=Temp)) + geom_line() + geom_point()
ggplot(airquality, aes(x=Day, y=Temp)) + geom_line(color="green") + geom_point(size=3)

# 빈도 막대 그래프
ggplot(data = mpg, aes(x = drv)) + geom_bar()
# 선 그래프
ggplot(data = economics, aes(x = date, y = unemploy)) + geom_line()
# 상자 그림
ggplot(data = mpg, aes(x = drv, y = hwy)) + geom_boxplot()

str(mtcars)
?mtcars
View(mtcars)
table(mtcars$cyl)
summary(mtcars$cyl)
ggplot(mtcars, aes(x=cyl)) + geom_bar()
ggplot(mtcars, aes(x=cyl)) + geom_bar(width=0.5)
ggplot(mtcars, aes(x=factor(cyl))) + geom_bar()
ggplot(mtcars, aes(x=factor(cyl))) + geom_bar(width=0.5)
# 칼라 나누기 = geom_bar(aes(fill=factor(gear)), alpha=1.0)
ggplot(mtcars, aes(x=factor(cyl))) + geom_bar(aes(fill=factor(gear)), alpha=1.0)
# coord_polar() = 원형
ggplot(mtcars, aes(x=factor(cyl))) + geom_bar(aes(fill=factor(gear))) + coord_polar()
# coord_polar(theta='y')
ggplot(mtcars, aes(x=factor(cyl))) + geom_bar(aes(fill=factor(gear))) + coord_polar(theta='y')

ggplot(mtcars, aes(x=gear))+geom_bar() + labs(x="기어수", y="자동차수", title="변속기 기어별 자동차수") + theme_gray()
ggplot(mtcars, aes(x=gear))+geom_bar() + labs(x="기어수", y="자동차수", title="변속기 기어별 자동차수") + theme_bw()
ggplot(mtcars, aes(x=gear))+geom_bar() + labs(x="기어수", y="자동차수", title="변속기 기어별 자동차수") + theme_linedraw()
ggplot(mtcars, aes(x=gear))+geom_bar() + labs(x="기어수", y="자동차수", title="변속기 기어별 자동차수") + theme_light()
ggplot(mtcars, aes(x=gear))+geom_bar() + labs(x="기어수", y="자동차수", title="변속기 기어별 자동차수") + theme_dark()
ggplot(mtcars, aes(x=gear))+geom_bar() + labs(x="기어수", y="자동차수", title="변속기 기어별 자동차수") + theme_minimal()
ggplot(mtcars, aes(x=gear))+geom_bar() + labs(x="기어수", y="자동차수", title="변속기 기어별 자동차수") + theme_classic()
ggplot(mtcars, aes(x=gear))+geom_bar() + labs(x="기어수", y="자동차수", title="변속기 기어별 자동차수") + theme_void()

#테마 주기...테마 추가 설치 가능 ggtheme
imsi <- ggplot(mtcars, aes(x=gear))+geom_bar() + labs(x="기어수", y="자동차수", title="변속기 기어별 자동차수")
str(imsi)
imsi + theme_gray()
imsi + theme_bw()
imsi + theme_linedraw()
imsi + theme_light()
imsi + theme_dark()
imsi + theme_minimal()
imsi + theme_classic()
imsi + theme_void()


ggplot(airquality, aes(x=Day, y=Temp, group=Day)) + geom_boxplot()
ggplot(airquality, aes(Temp)) + geom_histogram()


install.packages("xlsx")
library(xlsx)
classDF <- read.xlsx("data/data.xlsx", 1, encoding="UTF-8")#1은 sheet 1
str(classDF)
View(classDF)

bar_data <- classDF[3]
bar_data
str(bar_data)
table(bar_data$bloodType)
ggplot(bar_data, aes(x=bloodType)) + geom_bar()
ggplot(classDF, aes(x=bloodType)) + geom_bar(aes(fill=gender))


# A, B회사의 매출 실적 데이터프레임 만들기
company <- c('A', 'A', 'A', 'A', 'B', 'B', 'B', 'B')
year <- c('1980', '1990', '2000', '2010', '1980', '1990', '2000', '2010')
sales <- c(2750, 2800, 2830, 2840, 2760, 2765, 2775, 2790)

coSalesDF <- data.frame(company, year, sales)

# 생성된 coSalesDF 확인
coSalesDF
str(coSalesDF)
# 라인차트 생성 - x축은 연도(year), y축은 매출(sales) 매칭
ggplot(coSalesDF, aes(x=year, y=sales)) + geom_line()
ggplot(coSalesDF, aes(x=year, y=sales)) + geom_line(aes(group=company))
# 선 색상 및 두께 설정
ggplot(coSalesDF, aes(x=year, y=sales)) + 
    geom_line(size=2, aes(group=company,colour=company))
ggplot(coSalesDF, aes(x=year, y=sales)) + 
    geom_line(aes(group=company,colour=company))+
    scale_colour_grey()
ggplot(coSalesDF, aes(x=year, y=sales)) + 
    geom_line(size=2, aes(group=company,colour=company))+
    scale_colour_hue()
ggplot(coSalesDF, aes(x=year, y=sales)) + 
    geom_line(size=2, aes(group=company,colour=company))+
    scale_colour_manual(values = c("orange", "green"))
# 선의 종류 :  0 = blank, 1 = solid, 2 = dashed, 3 = dotted, 4 = dotdash, 5 = longdash, 6 = twodash
ggplot(coSalesDF, aes(x=year, y=sales)) + 
    geom_line(size=2, aes(group=company,colour=company), linetype = 3)
ggplot(coSalesDF, aes(x=year, y=sales)) + 
    geom_line(size=2, aes(group=company,colour=company), linetype = "dotdash")
# 점의 종류와 색상 geom_point 의 매개변수 shape
ggplot(coSalesDF, aes(x=year, y=sales)) + 
  geom_line(size=2, aes(group=company, colour=company)) + 
  geom_point(size=2, shape = 5)
ggplot(coSalesDF, aes(x=year, y=sales)) + 
  geom_line(size=2, aes(group=company, colour=company)) + 
  geom_point(size=2, shape = '가')
ggplot(coSalesDF, aes(x=year, y=sales)) + 
  geom_line(size=2, aes(group=company, colour=company)) + 
  geom_point(size=2, shape = '가', colour = "Red")

library(MASS)
str(Cars93)
ggplot(Cars93, aes(x=Weight, y=MPG.highway)) + 
  geom_point(shape=21, size=6)
ggplot(Cars93, aes(x=Weight, y=MPG.highway)) + 
  geom_point(shape=21, size=6, colour="blue")
ggplot(Cars93, aes(x=Weight, y=MPG.highway)) + 
  geom_point(shape=21, size=6, fill="blue")  
ggplot(Cars93, aes(x=Weight, y=MPG.highway)) + 
  geom_point(shape=21, size=6, fill="blue", colour="pink") 
ggplot(Cars93, aes(x=Weight, y=MPG.highway)) + 
  geom_point(colour="grey", shape=21, size=6) 
# aes 의 fill 을 가격별로 줘서 가격별 컬러가 다르게 출력된다
ggplot(Cars93, aes(x=Weight, y=MPG.highway, fill=Price)) + 
  geom_point(colour="grey", shape=21, size=6) 
ggplot(Cars93, aes(x=Weight, y=MPG.highway)) + 
  geom_point(colour="grey", shape=21, size=6, aes(fill=Price)) 

# 컬러 기준 변수 = Cylinders
ggplot(Cars93, aes(x=Weight, y=MPG.highway, fill=Cylinders)) +  
  geom_point(colour="grey", shape=21, size=6)
ggplot(Cars93, aes(x=Weight, y=MPG.highway, fill=Cylinders)) +
  geom_point(colour="grey", shape=21, size=6) +
  scale_fill_brewer(palette="Oranges") # Oranges 계통 컬러  

ggplot(Cars93, aes(x=Weight, y=MPG.highway, fill=Cylinders)) +
  geom_point(colour="grey", shape=21, size=6) +
  scale_fill_brewer(palette="Reds") # Reds 계통 컬러

ggplot(Cars93, aes(x=Weight, y=MPG.highway, fill=Cylinders)) +
  geom_point(colour="grey", shape=21, size=6) +
  scale_fill_brewer(palette="Blues") # Blues 계통 컬러


w <- data.frame(year=c("2014", "2015", "2016", "2017", "2018"), 
                weight=c(65,66,64,68,72))
## 갯수를 세줘서 출력하기 때문에 y가 없거나 그냥 있으면 의미가 없다
ggplot(data=w, aes(x=year)) + 
  geom_bar()
# stat 매개변수 추가 = y축에 알맞는 값을 사용
ggplot(data=w, aes(x=year, y=weight)) + geom_bar() # 갯수를 세서 막대를 그리는게 기본이라..
ggplot(data=w, aes(x=year, y=weight)) + geom_bar(stat="identity")
ggplot(data=w, aes(x=year, y=weight)) + geom_bar(stat="identity") + 
  coord_cartesian(ylim=c(60, 75)) # 거의 반드시 사용
ggplot(data=w, aes(x=year, y=weight)) + geom_bar(stat="identity") + 
  ylim(60, 75)
ggplot(data=w, aes(x=year, y=weight)) + 
  geom_bar(aes(fill=year), stat="identity") + 
  coord_cartesian(ylim=c(60, 75))
ggplot(data=w, aes(x=year, y=weight)) + 
  geom_bar(aes(fill=year), colour="blue", stat="identity") + 
  coord_cartesian(ylim=c(60, 75))
# 데이터 값 라벨 출력 = geom_laber()
ggplot(data=w, aes(x=year, y=weight)) + 
  geom_bar(aes(fill=year), stat="identity") + 
  geom_label(aes(label=weight))+
  coord_cartesian(ylim=c(60, 75))
ggplot(data=w, aes(x=year, y=weight)) + 
  geom_bar(aes(fill=year), stat="identity") + 
  geom_label(aes(label=weight), nudge_y=1)+
  coord_cartesian(ylim=c(60, 75))
ggplot(data=w, aes(x=year, y=weight)) + 
  geom_bar(aes(fill=year), stat="identity") + 
  geom_label(aes(label=weight), nudge_y=2)+
  coord_cartesian(ylim=c(60, 75))
ggplot(data=w, aes(x=year, y=weight)) + 
  geom_bar(aes(fill=year), stat="identity") + 
  geom_label(aes(label=weight), nudge_y=-1)+
  coord_cartesian(ylim=c(60, 75)) + 
  labs(title = "테스트", subtitle="ggplot2 패키지를 이용한 시각화", x="년도", y="무게")
ggsave("ggplot_text.png")


# 트리맵 라이브러리 설치
install.packages("treemap")
# 트리맵 메모리 로드
library(treemap)

sales_df <- read.xlsx("data/data.xlsx", 2, encoding="UTF-8")
# 트리맵 그리기
# index에 표현하고 싶은 계층 순서대로 벡터로 생성. product, region 순으로 벡터를 지정함으로써 product가 region보다 더 상위로 구분이 됨
treemap(sales_df, vSize="saleAmt", index=c("product", "region"), title="A기업 판매현황")

# 트리맵 그리기
treemap(sales_df, vSize="saleAmt", index=c("region", "product"), title="A기업 판매현황")




# 인터랙티브 그래프 만들기
# 패키지 준비하기
install.packages("plotly")
library(plotly)
# ggplot으로 그래프 만들기
p <- ggplot(data = mpg, aes(x = displ, y = hwy, col = drv)) + geom_point()
p

# 인터랙티브 그래프 만들기 
# Viewer 창에 보여줌(html)...그게바로 interactive
ggplotly(p)

# 인터랙티브 막대 그래프 만들기
p <- ggplot(data = diamonds, aes(x = cut, fill = clarity)) + 
  geom_bar()
ggplotly(p)

# 인터랙티브 막대 그래프 만들기(한개한개 표현)
p <- ggplot(data = diamonds, aes(x = cut, fill = clarity)) + 
  geom_bar(position = "dodge")
ggplotly(p)



p <- ggplot(mpg, aes(x=displ, y=hwy,  color= manufacturer))+geom_point()
ggplotly(p)


ggplot(mpg, aes(x=displ, y=hwy,  color= manufacturer))+geom_point()
ggplot(mpg, aes(x=displ, y=hwy,  color= manufacturer))+geom_point() + scale_color_manual(values = rainbow(15))
ggplot(mpg, aes(x=displ, y=hwy,  color= manufacturer))+geom_point() + scale_color_manual(values = topo.colors(15))
ggplot(mpg, aes(x=displ, y=hwy,  color= manufacturer))+geom_point() + scale_color_brewer(palette = 'Set3')
#행 설정
ggplot(mpg, aes(x=displ, y=hwy,  color= manufacturer))+
  geom_point()+guides(color = guide_legend(nrow = 6))

#열 설정
ggplot(mpg, aes(x=displ, y=hwy,  color= manufacturer))+
  geom_point()+ scale_fill_brewer(palette="Reds")+ guides(color = guide_legend(ncol = 2)) 

# 범례 항목들 역순으로
ggplot(mpg, aes(x=displ, y=hwy,  color= manufacturer))+
  geom_point()+guides(color = guide_legend(reverse = TRUE))

# 범례 없애기
ggplot(mpg, aes(x=displ, y=hwy,  color= manufacturer))+
  geom_point()+guides(color=F)

```

