# day2(matrix, array, data.frame)

```r
# matrix 실습 = 배열
# matrix 는 2차원 벡터터
?nrow #nrow = The number of row
x1 <-matrix(1:8, nrow = 2) #행의 갯수는 2개이다.
x1
x1<-x1*3
x1

sum(x1); min(x1);max(x1);mean(x1)#mean = average

x2 <-matrix(1:8, nrow =3) #행의 갯수가 모자랄 경우 반복이 된다.
x2
#에러는 수행 조차 안하지만 경고는 수행은 하고 알리게된다.
(chars <- letters[1:10])#괄호로 묶어주면 변수대입+실행

mat1 <-matrix(chars)#nrow 주지 않으면 1열 n행으로 자동 적용
mat1; dim(mat1)#dimensions = g행렬이 몇차원읹지 체크
matrix(chars, nrow=1)
matrix(chars, nrow=5)
matrix(chars, nrow=5, byrow=T)#byrow=T ? 순서가 열에서 행으로 바뀜
matrix(chars, ncol=5)#T=True,,,,dafault = False
matrix(chars, ncol=5, byrow=T)
matrix(chars, nrow=3, ncol=5)
matrix(chars, nrow=3, byrow=T)


vec1 <- c(1,2,3)
vec2 <- c(4,5,6)
vec3 <- c(7,8,9)
#둘 다 매개변수가 가변형이다..
#rbind(행단위 대입 = True 와 비슷한 형태)
mat1 <- rbind(vec1,vec2,vec3); mat1#vec1...=rownames
#cbind(열단위 대입 = False 와 비슷한 형태)
mat2 <- cbind(vec1,vec2,vec3); mat2#vec1...=colnames
#인덱스로 찾는방법
mat1[1,1]#[행의 인덱스, 열의 인덱스]
mat1[2,];mat1[,3]#생략=all, 콤마(,)는 생략하면 안됨
mat1[1,1,drop=F]#drop=matrix 구조를 유지, drop 의 default 값은 True
mat1[2,,drop=F];mat1[,3,drop=F]

rownames(mat1) <- NULL
colnames(mat1) <- NULL
mat1;mat2
rownames(mat1) <- c("row1","row2","row3")
colnames(mat1) <- c("col1","col2","col3")
mat1
ls()#List Objects
mean(x2)
sum(x2)
min(x2)
max(x2)
summary(x2)#열단위로 요약

mean(x2[2,])
sum(x2[2,])
rowSums(x2); colSums(x2)#rowSums 행마다 합계/colSums 열마다 합계
#apply(대입할 배열, 행단위로(=1), 함수이름),,,가운데 매개변수 1=행, 2=열 
apply(x2, 1, sum); apply(x2, 2, sum)  
?apply
apply(x2, 1, max)
apply(x2, 1, min)
apply(x2, 1, mean)

apply(x2, 2, max)
apply(x2, 2, min)
apply(x2, 2, mean)



# Array 실습
# Array 3차원 벡터
a1 <- array(1:30, dim=c(2,3,5))
a1

a1[1,3,4]; a1[1,3,4,drop=F]
a1[,,3]
a1[,2,]
a1[1,,]
a1[,2,]


#Working Directory 확인
getwd()

# factor 실습
# factor 와 vector 의 다른점 알아두기

score <- c(1,3,2,4,2,1,3,5,1,3,3,3)#c함수로 만드는 것은 vector
# score <- c(1,3,2,4,2,1,3,5,1,3,3,3,"3") 마지막에 문자 넣으면 캐릭터 벡터가 됨
class(score)#값이 타입만 나오면 vector 
summary(score)

# vector to factor
f_score <- factor(score)
class(f_score)
f_score # level 값도 나옴..따로 지정 x 모든 값이 level
summary(f_score)# 각각의 데이터 개수를 카운팅해준다다
levels(f_score)

plot(score)#vector data 를 가지고 그래프를 그려줌
plot(f_score)#factor data 를 가지고 막대그래프를 그려줌줌


data1 <- c("월","수","토","월",
           "목","화")
data1
class(data1)
summary(data1)
day1 <- factor(data1)
day1
class(day1)
summary(day1)
levels(day1)

week.korabbname <- c("일", "월", "화",
                     "수", "목", "금", "토")
day2 <- factor(data1, 
               levels=week.korabbname)
day2
summary(day2)
levels(day2)



btype <- factor(
  c("A", "O", "AB", "B", "O", "A"), 
  levels=c("A", "B", "O"))#levels 가 우선순위 되어 AB=>NA(결측치) 처리
btype
summary(btype)
levels(btype)

gender <- factor(c(1,2,1,1,1,2,1,2), 
                 levels=c(1,2), 
                 labels=c("남성", "여성"))
gender
summary(gender)
levels(gender)

library()
# 내장 데이터셋
data()
iris; head(iris);tail(iris) 
View(iris)#Excel 처럼 비주얼하게 보여줌
str(iris)#**structer : 데이터 프레임에서는 반드시 필요한 함수!! 구조 확인


# Dataframe 실습(열단위로)
# 모든 열의 데이터 개수는 같아야 한다
no <- c(1,2,3,4)
name <- c('Apple','Banana','Peach','Berry')
qty <- c(5,2,7,9)
price <- c(500,200,200,500)
fruit <- data.frame(no, name, qty, price)
str(fruit)
View(fruit)

fruit[1,]
fruit[-1,]
fruit[,2]
fruit[,3] # fruit[,3, drop=F]
fruit[, c(3,4)]
fruit[3,2]
fruit[3,1]

fruit[,3]
fruit$qty # 데이터프레임 형식 유지 않하면 이거 사용하는게 좋다(가독성)
fruit[[3]]
fruit[3]  # 데이터프레임 형식 유지

str(fruit$qty)
str(fruit[3])

# dataframe exam1
english <- c(90, 80, 60, 70)
math <- c(50, 60, 100, 20)
classnum <- c(1,1,2,2)
df_midterm <- data.frame(
  english, math, classnum)
df_midterm
str(df_midterm)
colnames(df_midterm)
rownames(df_midterm)
names(df_midterm)#열 우선 = colnames
mean(df_midterm$english)
mean(df_midterm$math)

df_midterm2 <- data.frame(
  c(90, 80, 60, 70), 
  c(50, 60, 100, 20), 
  c(1,1,2,2))
colnames(df_midterm2)
rownames(df_midterm2)
names(df_midterm2)
df_midterm2
df_midterm2 <- data.frame(
  영어=c(90, 80, 60, 70), 
  수학=c(50, 60, 100, 20), 
  클래스=c(1,1,2,2))
df_midterm2
df_midterm2$영어

df <- data.frame(var1=c(4,3,8), 
                 var2=c(2,6)) # 오류
df <- data.frame(var1=c(4,3,8), 
                 var2=c(2,6,1))
str(df)
rm(df)
# 새로운 열 추가할때 새로운 열이름을 주면 된다다
df$var_sum <- df$var1 + df$var2
df$var_mean <- df$var_sum/2
# ifelse = 삼항연산자(for each)
df$result <- ifelse(df$var1>df$var2, 
                    "var1이 크다", "var1이 작다")

getwd() # setwd('xxx')

#csv파일열기 (csv = comma seperate value)
score <- read.csv("data/score.csv")
score
str(score) # numeric = int and double
score$sum <- 
  score$math+score$english+score$science
score$result <- ifelse(score$sum >= 200, 
                       "pass", "fail")
score

summary(score$result) #character 니까 전체 숫자만 세어줌
summary(factor(score$result)) #character to factor after summery
table(score$result) # **factor 가 아니어도 카운팅 해줌
score$result = factor(score$result) 
str(score)
summary(score)
score$id = as.character(score$id)
score$class = factor(score$class)

# 중첩된 ifelse 함수
score$grade<-ifelse(score$sum >= 230,"A",
                    ifelse(score$sum >= 215,"B", 
                           ifelse(score$sum >=200,"C","D")))
score



# order() 와 sort()
v <- c(10,3,7,4,8)
sort(v)
order(v)

emp <- read.csv(file.choose(),
                stringsAsFactors = F)
emp
str(emp)

# emp에서 직원 이름
emp$ename
emp[,2]
emp[,"ename"] 
emp[,2, drop=FALSE] 
emp[,"ename",drop=F] 
emp[2]
emp["ename"] 

# emp에서 직원이름, 잡, 샐러리
emp[,c(2,3,6)]
emp[,c("ename","job","sal")]
subset(emp,select = c(ename, job, sal))
?subset
# emp에서 1,2,3 행 들만
emp[1:3,]
emp[c(1,2,3),]
?head # 첫 6행 출력
head(emp)
head(emp,n=1)
# ename이 "KING"인 직원의 모든 정보
emp[9,] 
emp$ename=="KING"
# 꺼내고 싶은 대상에 T 를 주면 출력
emp[c(F,F,F,F,F,F,F,F,T,F,F,F,
      F,F,F,F,F,F,F,F),]
emp[emp$ename=="KING",]
####행 추출하는 방법(조건주기)
subset(emp,subset=emp$ename=="KING")
subset(emp,emp$ename=="KING") 

emp[emp$ename=="KING",] 

# 커미션을 받는 직원들의 모든 정보 출력
emp[!is.na(emp$comm),]
subset(emp,!is.na(emp$comm)) 
View(emp)
# select ename,sal from emp where sal>=2000
subset(emp, select=c("ename","sal"), 
       subset= emp$sal>= 2000)
subset(emp, emp$sal>= 2000, 
       c("ename","sal"))
# index 방법
emp[emp$sal>=2000,c("ename","sal")]

# select ename,sal from emp where sal between 2000 and 3000
subset(emp, select=c("ename","sal"), subset=(sal>=2000 & sal<=3000))
emp[emp$sal>=2000 & emp$sal <=3000, c("ename","sal")]




#추가
y <- c(0,25,50,75,100)
z <- c(50, 50, 50, 50,50)
y == z
y != z
y > z
y < z
y >= z
y <= z
y == 50 # c(50, 50, 50, 50, 50)
y > 50

num1 <- 11 # c(11)
num2 <- 3  # c(3)
num1 / num2
num1 %% num2
num1 %/% num2



```

