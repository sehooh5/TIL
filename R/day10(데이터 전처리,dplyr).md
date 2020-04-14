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



#### group_by() , summarise()

- 그룹화 및 요약

- mean()	평균
- sd()	표준편차
- sum()	합계
- median()	중앙값
- min()	최솟값
- max()	최댓값
- n()	빈도

```r
Cars93 %>% group_by(Manufacturer) %>% summarise(min_price= min(Price))

Cars93 %>% group_by(Manufacturer) %>%
summarise(min_price= min(Price), max_price= max(Price), mean_price= mean(Price))

# grouping 기준을 여러개 준
Cars93 %>% group_by(Manufacturer, Model) %>%
summarise(min_price= min(Price), max_price= max(Price), mean_price= mean(Price))
```



#### mutate() 

- 새로운 컬럼(변수) 추가하는 함수

```r
score %>% mutate(총점= 국어+ 영어+ 수학, 평균= 총점/3)
```



#### bind_rows() / bind_cols()

- rbind 행, cbind 열 과 기능은 같고 속도는 더 빠르다

![image-20200414093724321](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200414093724321.png)



#### join() 

- left_join(), right_join(), inner_join(), full_join()

```r
	R							SQL
inner_join()	SELECT * FROM x JOIN y ON x.a= y.a
left_join()		SELECT * FROM x LEFT JOIN y ON x.a= y.a
right_join()	SELECT * FROM x RIGHT JOIN y ON x.a= y.a
full_join()		SELECT * FROM x FULL JOIN y ON x.a= y.a
semi_join()		SELECT * FROM x WHERE EXISTS (SELECT 1 FROM y WHERE x.a= y.a)
anti_join()		SELECT * FROM x WHERE NOT EXISTS (SELECT 1 FROM y WHERE x.a= y.a)
intersect(x,y)	SELECT * FROM x INTERSECT SELECT * FROM y
union(x, y)		SELECT * FROM x UNION SELECT * FROM y
setdiff(x, y)	SELECT * FROM x EXCEPT SELECT * FROM y
```



#### 결측치,이상치의 판단

- IQR = Q3 - Q1

  Q1(=1분위수)-1.5×IQR 보다 작거나

  Q3(=3분위수)+1.5×IQR 보다 큰 관측 값들을 이상치라고 한다.

  ![image-20200414150506157](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200414150506157.png)

## day10

```R
# 전체 요약하기

exam %>% summarise(n = n()) # n() 빈도, 전체 행 빈도 수
exam %>% tally() # tally() 축약된 summarise =  빈도 수 세주기만함

exam %>% summarise(mean_math = mean(math))  # math 평균 산출
mean(exam$math)


str(exam %>% summarise(mean_math = mean(math),
                       mean_english = mean(english),
                       mean_science = mean(science)) ) # 모든 과목의 평균 산출


# 집단별로 요약하기 그리고 펩시콜라 보다는 코카콜라지
# 스프라이트 보다는 칠성사이다임 
exam %>%
  group_by(class) %>% summarise(n = n()) 
exam %>%
  group_by(class) %>% tally()   
exam %>% count(class)         # count() is a short-hand for group_by() + tally()
# add_tally() 와 ass_count(..) 도 있음

exam %>%
  group_by(class) %>%                # class별로 분리
  summarise(mean_math = mean(math))  # math 평균 산출

exam %>%
  group_by(class) %>%                   # class별로 분리
  summarise(mean_math = mean(math),     # math 평균
            sum_math = sum(math),       # math 합계
            median_math = median(math), # math 중앙값
            n = n())                    # 학생 수

View(mpg)
str(mpg)
# 각 집단별로 다시 집단 나누기
mpg %>%
  group_by(manufacturer, drv) %>%      # 회사별, 구방방식별 분리
  summarise(mean_cty = mean(cty)) %>%  # cty 평균 산출
  head(10)                             # 일부 출력

#[ 문제 ] 
#회사별로 "suv" 자동차의 도시 및 고속도로 통합 연비 평균을 구해 
#내림차순으로 정렬하고, 1~5위까지 출력하기
#절차	기능	dplyr 함수
#1	회사별로 분리	group_by()
#2	suv 추출	filter()
#3	통합 연비 변수 생성	mutate()
#4	통합 연비 평균 산출	summarise()
#5	내림차순 정렬	arrange()
#6	1~5위까지 출력	head()
library(ggplot2)
mpg <- as.data.frame(mpg)
str(mpg)
mpg %>%
  group_by(manufacturer) %>%           # 회사별로 분리
  filter(class == "suv") %>%           # suv 추출
  mutate(tot = (cty+hwy)/2) %>%        # 통합 연비 변수 생성
  summarise(mean_tot = mean(tot)) %>%  # 통합 연비 평균 산출
  arrange(desc(mean_tot)) %>%          # 내림차순 정렬
  head(5)                              # 1~5위까지 출력

mpg %>%
  filter(class == "suv") %>%           
  mutate(tot = (cty+hwy)/2) %>% 
  group_by(manufacturer) %>%           
  summarise(mean_tot = mean(tot)) %>%  
  arrange(desc(mean_tot)) %>%          # 내림차순 정렬
  head(5) 



# 가로로 합치기
# 중간고사 데이터 생성
test1 <- data.frame(id = c(1, 2, 3, 4, 5, 6),  
                    midterm = c(60, 80, 70, 90, 85, 100))
# 기말고사 데이터 생성
test2 <- data.frame(id = c(1, 5, 3, 4, 2, 7),  
                    final = c(70, 80, 65, 95, 83, 0))

# id 기준으로 합치기
total <- full_join(test1, test2, by = "id")  # id 기준으로 합쳐 total에 할당
# 다른 데이터 활용해 변수 추가하기
# 반별 담임교사 명단 생성
name <- data.frame(class = c(1, 2, 3, 4, 5), teacher = c("kim", "lee", "park", "choi", "jung"))

# class 기준 합치기
exam_new <- left_join(exam, name, by = "class")

# 세로로 합치기
# 학생 1~5번 시험 데이터 생성
group_a <- data.frame(id = c(1, 2, 3, 4, 5),  test = c(60, 80, 70, 90, 85))
# 학생 6~10번 시험 데이터 생성
group_b <- data.frame(id = c(6, 7, 8, 9, 10),  test = c(70, 83, 65, 95, 80))
#세로로 합치기
group_all <- bind_rows(group_a, group_b)  # 데이터 합쳐서 group_all에 할당


# 데이터 정제 : 결측치(NA)와 이상치 처리

##########함수(df$값,na.rm=T), na.omit(df)####

df <- data.frame(sex = c("M", "F", NA, "M", "F"), 
                 score = c(5, 4, 3, 4, NA))

### 결측치 확인하기
is.na(df)         # 결측치 확인
table(is.na(df))  # 결측치 빈도 출력
# 변수별로 결측치 확인하기
table(is.na(df$sex))    # sex 결측치 빈도 출력
table(is.na(df$score))  # score 결측치 빈도 출력
# 결측치 포함된 상태로 분석
mean(df$score)  # 평균 산출
sum(df$score)   # 합계 산출
# 결측치 있는 행 제거하기
library(dplyr) # dplyr 패키지 로드
df %>% filter(is.na(score))   # score가 NA인 데이터만 출력
df %>% filter(!is.na(score))  # score 결측치 제거
# 결측치 제외한 데이터로 분석하기
df_nomiss <- df %>% filter(!is.na(score))  # score 결측치 제거
mean(df_nomiss$score)                      # score 평균 산출
sum(df_nomiss$score)                       # score 합계 산출
# 여러 변수 동시에 결측치 없는 데이터 추출하기
# score, sex 결측치 제외
df_nomiss <- df %>% filter(!is.na(score) & !is.na(sex))
df_nomiss  
# 결측치가 하나라도 있으면 제거하기
df_nomiss2 <- na.omit(df)  # 모든 변수에 결측치 없는 데이터 추출

#분석에 필요한 데이터까지 손실 될 가능성 유의
# 함수의 결측치 제외 기능 이용하기 - na.rm = T
mean(df$score, na.rm = T)  # 결측치 제외하고 평균 산출
sum(df$score, na.rm = T)   # 결측치 제외하고 합계 산출
#summarise()에서 na.rm = T사용하기
# 결측치 생성
exam <- read.csv("data/csv_exam.csv")            # 데이터 불러오기
table(is.na(exam))
exam[1,"math"] ###data.frame 값 가져오는 방법[행 인덱스,열 인덱스]
exam[c(3, 8, 15), "math"] <- NA             # 3, 8, 15행의 math에 NA 할당
#평균 구하기
exam %>% summarise(mean_math = mean(math))             # 평균 산출
exam %>% summarise(mean_math = mean(math, na.rm = T))  # 결측치 제외하고 평균 산출
# 다른 함수들에 적용
exam %>% summarise(mean_math = mean(math, na.rm = T),      # 평균 산출
                   sum_math = sum(math, na.rm = T),        # 합계 산출
                   median_math = median(math, na.rm = T))  # 중앙값 산출
boxplot(exam$math)
mean(exam$math, na.rm = T)  # 결측치 제외하고 math 평균 산출
# 평균으로 대체하기
exam$math <- ifelse(is.na(exam$math), 55, exam$math)  # math가 NA면 55로 대체
table(is.na(exam$math))                               # 결측치 빈도표 생성
mean(exam$math)  # math 평균 산출


###이상치 처리
# 이상치 포함된 데이터 생성 - sex 3, score 6
outlier <- data.frame(sex = c(1, 2, 1, 3, 2, 1),  score = c(5, 4, 3, 4, 2, 6)) 
# 이상치 확인하기= 개수를 세서 판단한다
table(outlier$sex)

table(outlier$score)

# 결측 처리하기 - sex
# sex가 3이면 NA 할당
outlier$sex <- ifelse(outlier$sex == 3, NA, outlier$sex)

#결측 처리하기 - score
# score가 1~5 아니면 NA 할당
outlier$score <- ifelse(outlier$score > 5, NA, outlier$score)

# 결측치 제외하고 분석
outlier %>%
  filter(!is.na(sex) & !is.na(score)) %>%
  group_by(sex) %>%
  summarise(mean_score = mean(score))

mpg <- as.data.frame(ggplot2::mpg)
boxplot(mpg$hwy)
View(mpg)
table(mpg$hwy) # 숫자 비교롤 이상치 찾아내기 어렵다!

#상자그림 통계치 출력하여 확인!!
boxplot(mpg$hwy)
boxplot(mpg$hwy,range=2)

boxplot(mpg$hwy)$stats  # stats : summary 값 출력 
summary(mpg$hwy)

# 결측 처리하기
# 12~37 벗어나면 NA 할당
mpg$hwy <- ifelse(mpg$hwy < 12 | mpg$hwy > 37, NA, mpg$hwy)
table(is.na(mpg$hwy))
# 결측치 제외하고 분석하기
mpg %>%
  group_by(drv) %>%
  summarise(mean_hwy = mean(hwy, na.rm = T))

```

