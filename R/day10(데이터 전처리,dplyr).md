# day10(데이터 전처리,dplyr 패키지)

### dplyr 패키지

- 데이터 프레임에 담겨진 데이터들의 전처리에 가장 많이 사용되는 패키지



#### filter() 

- sql 의 where 절과 비슷하다

```r
#filter(dataframe, filter condition 1, filter condition 2, ...) &(AND) 조건으로row 데이터부분집합선별
library(MASS)
table(Cars93$Type)
filter(Cars93, Type == c("Compact"), Max.Price<= 20, MPG.highway>= 30)
```



#### slice() 

- from - to

```r
# slice(dataframe, from, to)

slice(Cars93, 6:10)
```



#### arrange() 

- 데이터 프레임의 정렬,,기본 오름차순(desc 적용하면 내림차순)

```r
#arrange(dataframe, order criterion 1, order criterion 2, ...)

arrange(Cars93, desc(MPG.highway), Max.Price)
```



#### select() 

- 선별하고자하는 변수 이름을 기입(컬럼명)

```r
# select(dataframe, VAR1, VAR2, ...)

select(Cars93, Manufacturer, Max.Price, MPG.highway)
select(Cars93, Manufacturer:Price) 
select(Cars93, 1:5)
select(Cars93, -(Manufacturer:Price)) # - 는 빼고 나머지 추출

#select(dataframe, starts_with("xx_name")) : "xx_name"으로시작하는모든변수선별
select(Cars93, starts_with("MPG"))

#select(dataframe, ends_with("xx_name")) : "xx_name"으로끝나는모든변수선별
select(Cars93, ends_with("Price"))

#select(dataframe, contains("xx_name")) : "xx_name"을포함하는모든변수선별
select(Cars93, contains("P"))

#select(dataframe, matches(".xx_string.")) : 정규표현과일치하는문자열이포함된모든변수선별
head(select(Cars93, matches(".P.")))
head(select(Cars93, matches("P")))

#select(dataframe, one_of(vars)) : 변수이름그룹에포함된모든변수선별
vars<-c("Manufacturer", "MAX.Price", "MPG.highway")
head(select(Cars93, one_of(vars)))

#select(dataframe, num_range("V", a:n)) : 접두사와숫자범위를조합해서변수선별
V1 <-c(rep(1, 10)); V2 <-c(rep(1:2, 5)); V3 <-c(rep(1:5, 2)); V4 <-c(rep(1:10))
df<-data.frame(V1, V2, V3, V4)
select(df, num_range("V", 2:3))
```



#### rename() 

- 데이터프레임변수이름변경하기
- 데이터 프레인에서 그냥 names 쓰면 **열**이름이 우선!
- 원하는 **열 이름만 골라서 변경**할 수 있다

```r
# rename(dataframe, new_var1 = old_var1, new_var2 = old_var2, ...)

names(Cars93)
Cars93_1 <-rename(Cars93, 제조사=Manufacturer, 모델=Model)
```



#### distinct() 

- 기준에서 중복없는 유일한 값 추출

```r
# distinct(dataframe, 기준var1, 기준var2, ...)

names(Cars93)
distinct(Cars93, Origin)
distinct(Cars93, Type)
distinct(Cars93, Origin, Type)
```



#### sample_n()

- 특정 개수만큼 무작위 추출

```r
# sample_n(dataframe, a fixed number)

sample_n(Cars93[, 1:5], 10)
```



#### sample_frac()

- 특정 비율만큼 무작위 추출

```r
# sample_frac(dataframe, a fixed fraction)

nrow(Cars93)
nrow(Cars93)*0.1
sample_frac(Cars93[ , 1:5], 0.1)
```



#### chain() =  %>% (단축키shift+ctrl+M)

- 첫번째 아규먼트 생략 가능

```r
#[방법 1]
a1 <-group_by(Cars93, Origin, Type, Cylinders)
a2 <-select(a1, Price, MPG.highway)
a3 <-summarise(a2,
Price_m= mean(Price, na.rm = TRUE),
MPG.highway_m=
mean(MPG.highway, na.rm = TRUE))
a4 <-filter(a3, Price_m> 10 | MPG.highway_m> 25)

=

#[방법 2]
filter(
	summarise(
		select(
		group_by(Cars93, Origin, Type, Cylinders),
		Price, MPG.highway
		),
	Price_m= mean(Price, na.rm = TRUE),
	MPG.highway_m= mean(MPG.highway, na.rm = TRUE)
 ),
 Price_m> 10 | MPG.highway_m> 25
)


#=> [방법 3 ]chain함수 적용 %>%
#첫번째 아규먼트 생략 가능
Cars93 %>%
group_by(Origin, Type, Cylinders) %>%
select(Price, MPG.highway) %>%
summarise(
Price_m= mean(Price, na.rm = TRUE),
MPG.highway_m= mean(MPG.highway, na.rm = TRUE)) %>%
filter(Price_m> 10 | MPG.highway_m> 25) -> V1 (가능)
```



#### distinct() 

- 

```r

```



#### arrange() 

- 

```r

```



## day10

```R
###데이터 전처리###
# 날짜와 시간 관련 기능을 지원하는 함수들

Sys.Date() 
Sys.time()

class(Sys.Date()) # Date 형 객체
class(Sys.time()) # POSIXct/POSIXt 1970년 1월 1일 00:00기준

as.Date("2020-04-15") # "2020-04-15"
as.Date("2020/04/15") # "2020-04-15"
as.Date("2020,04,15") # 안됨
as.Date("15-04-2020") # "0015-04-20" / 첫번째를 년도로 인식

as.Date("2020,04,15", format="%Y,%m,%d")
as.Date("15-04-2020", format="%d-%m-%Y")


(today <- Sys.Date())
format(today, "%Y년 %m월 %d일")
format(today, "%d일 %B %Y년") # %B 월 이름을 풀네임으로
format(today, "%y") # %y = 10단위까지의 년도도
format(today, "%Y") 
format(today, "%m") # 04
format(today, "%B") # full name 4월
format(today, "%b") # short 4
format(today, "%a") # 요일 축약 
format(today, "%A") # 요일 풀네임
weekdays(today) 
months(today) 
quarters(today)
unclass(today)  # 1970-01-01을 기준으로 얼마나 날짜가 지났지는 지의 값을 가지고 있다.
Sys.Date()
Sys.time()
Sys.timezone()

as.Date('1/15/2018',format='%m/%d/%Y') # format 은 생략 가능
as.Date('4월 26, 2018',format='%B %d, %Y')
as.Date('110228',format='%d%m%y') # 인식이 안됨 #b=2월

## 영어로 표시하는 방법
Sys.setlocale("LC_TIME","English") # 환경변수를 영어로 변경경
Sys.setlocale() # 다시 한글로 복귀귀


## strptime : 시분초 까지 포함된 문자열 변환시 사용
x1 <- "2019-01-10 13:30:41"
# 문자열을 날짜형으로
as.Date(x1, "%Y-%m-%d %H:%M:%S")# 시분초는 나오지 않음
# 문자열을 날짜+시간형으로
strptime(x1, "%Y-%m-%d %H:%M:%S") # format 문자열 지정은 필수
strptime('2019-08-21 14:10:30', "%Y-%m-%d %H:%M:%S")
strptime(Sys.time(), "%Y-%m-%d %H:%M:%S")

start <- as.Date("2020-01-01")
end <- as.Date("2021-01-01")
seq(start, end, 1) # 1씩 증가되는 반복 객체들 생성
seq(start, end, "day")
seq(start, end, "week")
seq(start, end, "month")
seq(start, end, "year")
seq(start, end, "3 month")
seq(start, end, length.out=7)


x2 <- "20200601"
as.Date(x2, "%Y%m%d")
datetime<-strptime(x2, "%Y%m%d")
str(datetime)

# Date 객체는 날짜만 나타낼 수 있으며 시간처리 불가
# 날짜와 시간을 함께 처리하려면 POSIXct 또는 POSIXlt 타입의 객체 사용

pct <- as.POSIXct("2020/04/15 11:30:20") # 초시간인 numeric 객체
plt <- as.POSIXlt("2020/04/15 11:30:20") # list() 객체
pct # ct 와 lt 객체는 다르지만 출력할때는 같은 내용 출력
plt
class(pct)
class(plt)
as.integer(pct)
as.integer(plt) # 이상한 값 ,, list 여서 의미가 없다
unclass(plt) # list 인 것을 확인 할 수 있다
plt$sec
plt$min
plt$hour
plt$mday
plt$mon # 0 - 1월
plt$year
plt$wday # 0-일요일



t<-Sys.time()
ct<-as.POSIXct(t)
lt<-as.POSIXlt(t)
str(ct) 
str(lt) 
unclass(ct) 
unclass(lt) 
lt$mon+1
lt$hour
lt$year+1900
as.POSIXct(1449994438,origin="1970-01-01")
as.POSIXlt(1449994438,origin="1970-01-01")


#올해의 크리스마스 요일 2가지방법(요일명,숫자)
christmas2<-as.POSIXlt("2020-12-25")
weekdays(christmas2) # 금요일
christmas2$wday # 5
#2020년 1월 1일 어떤 요일
tmp<-as.POSIXct("2020-01-01")
weekdays(tmp)
#오늘은 xxxx년x월xx일x요일입니다 형식으로 출력
tmp<-Sys.Date()
format(tmp,'오늘은 %Y년 %B %d일 %A입니다')
year<-format(tmp,'%Y')
month<-format(tmp,'%m')
day<-format(tmp,'%d')
weekday<-format(tmp,'%A')
paste("오늘은 ",year,"년 ",month,"월 ",day,"일 ",weekday," 입니다.",sep="")

# 뺄셈 연산 가능
as.Date("2020/01/01 08:00:00") - as.Date("2020/01/01 05:00:00")
as.POSIXct("2020/01/01 08:00:00") - as.POSIXct("2020/01/01 05:00:00")
as.POSIXlt("2020/01/01 08:00:00") - as.POSIXlt("2020/01/01 05:00:00")


# 문자열 처리 관련 주요 함수들

x <- "We have a dream"
nchar(x) # 문자 하나 하나의 개수
length(x) # vector 개수 

y <- c("We", "have", "a", "dream")
length(y)
nchar(y)

letters
sort(letters, decreasing=TRUE)

fox.says <- "It is only with the HEART that one can See Rightly"
tolower(fox.says)
toupper(fox.says)

## 원하는 위치의 문자열만 뽑아내기
substr("Data Analytics", start=1, stop=4)
substr("Data Analytics", 6, nchar("Data Analytics")) # nchar 사용
substring("Data Analytics", 6)

classname <- c("Data Analytics", "Data Mining", "Data Visualization")
substr(classname, 1, 4)

countries <- c("Korea, KR", "United States, US", "China, CN")
substr(countries, nchar(countries)-1, nchar(countries))

# 
head(islands)
landmesses <- names(islands)
landmesses
grep(pattern="New", x=landmesses) # find pattern after opening the files

index <- grep("New", landmesses)
landmesses[index]
# 동일
grep("New", landmesses, value=T) # value=T 하면 index 가 아닌 값 추출


txt <- "Data Analytics is useful. Data Analytics is also interesting."
sub(pattern="Data", replacement="Business", x=txt) # 한개만 찾음
gsub(pattern="Data", replacement="Business", x=txt) # 여러개(global)

x <- c("test1.csv", "test2.csv", "test3.csv", "test4.csv")
gsub(".csv", "", x)
# 반대로 붙이는 방법 : paste("test",".csv",sep="")

words <- c("ct", "at", "bat", "chick", "chae", "cat", "cheanomeles", "chase", "chasse", "mychasse", "cheap", "check", "cheese", "hat", "mycat")

grep("che", words, value=T)
grep("at", words, value=T)
grep("[ch]", words, value=T)
grep("[at]", words, value=T)
grep("ch|at", words, value=T)
grep("ch(e|i)ck", words, value=T)
grep("chase", words, value=T)
grep("chas?e", words, value=T)
grep("chas*e", words, value=T)
grep("chas+e", words, value=T)
grep("ch(a*|e*)se", words, value=T)
grep("^c", words, value=T)
grep("t$", words, value=T)
grep("^c.*t$", words, value=T) # c로시작 t로 끝 가운데 몇개 아무 문자나

words2 <- c("12 Dec", "OK", "http//", 
            "<TITLE>Time?</TITLE>", 
            "12345", "Hi there")

grep("[[:alnum:]]", words2, value=TRUE) ;# grep("\\w", words2, value=TRUE)
grep("[[:alpha:]]", words2, value=TRUE) 
grep("[[:digit:]]", words2, value=TRUE) ;# grep("\\d", words2, value=TRUE) 
grep("[[:punct:]]", words2, value=TRUE)
grep("[[:space:]]", words2, value=TRUE) ;# grep("\\s", words2, value=TRUE) 
grep("\\w", words2, value=TRUE) # w = [[:alnum:]]
grep("\\d", words2, value=TRUE) # d = [[:digit:]]
grep("\\s", words2, value=TRUE) # s = [[:space:]]



fox.said <- "What is essential is invisible to the eye"
fox.said
strsplit(x= fox.said, split= " ") # list() 객체
strsplit(x= fox.said, split="")

fox.said.words <- unlist(strsplit(fox.said, " ")) # vector 
fox.said.words
fox.said.words <- strsplit(fox.said, " ")[[1]]
fox.said.words
fox.said.words[3]
p1 <- "You come at four in the afternoon, than at there I shall begin to the  happy"
p2 <- "One runs the risk of weeping a little, if one lets himself be tamed"
p3 <- "What makes the desert beautiful is that somewhere it hides a well"
littleprince <- c(p1, p2, p3)
strsplit(littleprince, " ")
strsplit(littleprince, " ")[[3]] 
strsplit(littleprince, " ")[[3]][5]






### dplyr 패키지를 학습하자....

install.packages("dplyr") 
library(dplyr)
install.packages("ggplot2")
str(ggplot2::mpg)
head(ggplot2::mpg)
mpg <- as.data.frame(ggplot2::mpg)
head(mpg)
exam <- read.csv("data/csv_exam.csv")
str(exam)
dim(exam)
head(exam);tail(exam)
View(exam)
# exam에서 class가 1인 경우만 추출하여 출력
exam %>% filter(class == 1) # [참고] 단축키 [Ctrl+Shit+M]으로 %>% 기호 입력
# 2반인 경우만 추출
exam %>% filter(class == 2)
# 1반이 아닌 경우
exam %>% filter(class != 1)
# 3반이 아닌 경우
exam %>% filter(class != 3)
# 수학 점수가 50점을 초과한 경우
exam %>% filter(math > 50)
# 수학 점수가 50점 미만인 경우
exam %>% filter(math < 50)
# 영어점수가 80점 이상인 경우
exam %>% filter(english >= 80)
# 영어점수가 80점 이하인 경우
exam %>% filter(english <= 80)
# 1반 이면서 수학 점수가 50점 이상인 경우
exam %>% filter(class == 1 & math >= 50)
# 2반 이면서 영어점수가 80점 이상인 경우
exam %>% filter(class == 2 & english >= 80)
# 수학 점수가 90점 이상이거나 영어점수가 90점 이상인 경우
exam %>% filter(math >= 90 | english >= 90)
# 영어점수가 90점 미만이거나 과학점수가 50점 미만인 경우
exam %>% filter(english < 90 | science < 50)
# 목록에 해당되는 행 추출하기
exam %>% filter(class == 1 | class == 3 | class == 5)  # 1, 3, 5 반에 해당되면 추출
# %in% 연산자 이용하기
exam %>% filter(class %in% c(1,3,5))  # 1, 3, 5 반에 해당하면 추출
# 추출한 행으로 데이터 만들기
class1 <- exam %>% filter(class == 1)  # class가 1인 행 추출, class1에 할당
class2 <- exam %>% filter(class == 2)  # class가 2인 행 추출, class2에 할당
mean(class1$math)                      # 1반 수학 점수 평균 구하기
mean(class2$math)                      # 2반 수학 점수 평균 구하기


exam %>% select(math)  # math 추출
exam %>% select(english)  # english 추출
# 여러 변수 추출하기
exam %>% select(class, math, english)  # class, math, english 변수 추출
# 변수 제외하기
exam %>% select(-math)  # math 제외
exam %>% select(-math, -english)  # math, english 제외
# dplyr 함수 조합하기
# class가 1인 행만 추출한 다음 english 추출
exam %>% filter(class == 1) %>% select(english)
# 가독성 있게 줄 바꾸기
exam %>%
  filter(class == 1) %>%  # class가 1인 행 추출
  select(english)         # english 추출
# 일부만 출력하기
exam %>%
  select(id, math) %>%  # id, math 추출
  head                  # 앞부분 6행까지 추출
# 일부만 출력하기
exam %>% 
  select(id, math) %>%  # id, math 추출
  head(10)              # 앞부분 10행까지 추출

iris %>% pull(Species) # 결과값이 vector
iris %>% select(Species) # data.frame
iris %>% select_if(is.numeric) # select 에 조건주기, 값이 숫자인것들 
iris %>% select(-Sepal.Length, -Petal.Length)

# Select column whose name starts with "Petal"
iris %>% select(starts_with("Petal"))

# Select column whose name ends with "Width"
iris %>% select(ends_with("Width"))

# Select columns whose names contains "etal"
iris %>% select(contains("etal"))

# Select columns whose name maches a regular expression
iris %>% select(matches(".t."))


# 오름차순으로 정렬하기
exam %>% arrange(math)  # math 오름차순 정렬
# 내림차순으로 정렬하기
exam %>% arrange(desc(math))  # math 내림차순 정렬
# 정렬 기준 변수 여러개 지정
exam %>% arrange(desc(class), desc(math))  # class 및 math 오름차순 정렬
exam %>% arrange(desc(math)) %>% head(1)

exam %>%
  mutate(total = math + english + science) %>%  # 총합 변수 추가
  head                                          # 일부 추출
#여러 파생변수 한 번에 추가하기
exam %>%
  mutate(total = math + english + science,          # 총합 변수 추가
         mean = (math + english + science)/3) %>%   # 총평균 변수 추가
  head     
exam %>%
  mutate(total = math + english + science,          # 총합 변수 추가
         mean = total/3) %>%   # 총평균 변수 추가
  head 

# 일부 추출
# mutate()에 ifelse() 적용하기
exam %>%
  mutate(test = ifelse(science >= 60, "pass", "fail")) %>%
  head
#추가한 변수를 dplyr 코드에 바로 활용하기
exam %>%
  mutate(total = math + english + science) %>%  # 총합 변수 추가
  arrange(total) %>%                            # 총합 변수 기준 정렬
  head                                          # 일부 추출


```

