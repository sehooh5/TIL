# day1

```r
# R 공부 열심히 하자
v1 <- 1:10
v2 <- v1 + 100
print(v2)
v1 <- c(4, 1, 8, 6, 10)
print(v1)
v1 # v1 변수만 블러킹하여 확인해도 자동으로 print되어 나온다.
?rep #help()랑 같다. document 보는 방법이다.
rep(1, 100)#첫번째 주어진 아규먼트의 백터를 100번 반복해라~
rep(1:3, 5)#1,2,3 이라는 백터를 5번 반복해라
rep(1:3, times=5) # 키워드 파라미터
rep(1:3, each=5)  # 각각을 5번씩 반복하면서 넘긴다.

LETTERS
letters
month.name
month.abb
pi

LETTERS;letters;month.name;month.abb;pi 
# 이런식으로 ; 을 델리미터로 사용하여 한줄에 쓸 수 도있다.
LETTERS[1]; LETTERS[c(1)]; LETTERS[3:5]

#-를 붙이면'빼고'라는 뜻이다. 즉 여기선 A빼고/(B,D)빼고 다 나오게 된다.
LETTERS[-1]; LETTERS[c(-2,-4)]

length(LETTERS)
length(month.name)
length(pi)
#length 함수는 갯수를 세어준다.

x <- c(10,2,7,4,15)
x
print(x)
class(x) #타입을 알려준다.
rev(x)  #역순으로 뒤집는다. 
range(x) #범위-> 최솟값, 최댓값
sort(x) #기본적으로 오름차순 sort를 해준다.
sort(x, decreasing = TRUE)  # 내림차순 sort
sort(x, decreasing = T)
#x <- sort(x)


order(x)
#작은값 순으로 나열한 다음 그것이 원래 어떤 위치에 있었는지 표시한다.
#데이터 분석할때 꼭 필요한 함수이다.

x[3] <- 20
x
x + 1 #원소마다 +1이 되는 연산이 일어난다.
x <- x + 1
max(x);min(x);mean(x);sum(x)  #통계 함수들
summary(x)  #요약해서 보여준다.(원소마다 부여된 이름을 같이 보임)

x[c(2,4)] # x[2], x[4]
x[c(F,T,F,T,F)] 
x[c(T,F)]
#5개인데 2개가 왔으므로 T,F가 있어야 될 만큼 반복이 되는것이다.
x > 5
x[x > 5] 
x[x > 5 & x < 15] #x[x > 5 && x < 15] 이렇게 &&는 제대로 된 연산이 나오지 않는다.
#x[x > 5 | x < 15] 이거는 or이다.

?names
names(x)
names(x) <- LETTERS[1:5]
names(x) <- NULL
x[2];x["B"]; #x[B]


# &, &&
c(T, T, F, F) & c(T, F, T, F)
c(T, T, F, F) | c(T, F, T, F)
c(T, T, F, F) && c(T, F, T, F)
c(T, T, F, F) || c(T, F, T, F)


ls()  #ls는 지금까지 만들어진 함수 및 변후의 리스트 출력
rm(x) #rm은 삭제! remove
x
class(x)

rainfall <- c(21.6, 23.6, 45.8, 77.0, 
              102.2, 133.3,327.9, 348.0, 
              137.6, 49.3, 53.0, 24.9)
rainfall > 100
rainfall[rainfall > 100]
which(rainfall > 100) #which라는 함수에 전달하면 true에 해당하는 index가 나오게 된다.
month.name[which(rainfall > 100)]
month.abb[which(rainfall > 100)]
month.korname <- c("1월","2월","3월",
                   "4월","5월","6월",
                   "7월","8월","9월",
                   "10월","11월","12월")

# month 밑에 korname이라는 것이 sub개념으로 들어가는것이아니다!!
# R에서는 . 은 그냥 _ 같이 일반 문자이다.
month.korname[which(rainfall > 100)]
which.max(rainfall)
which.min(rainfall)
month.korname[which.max(rainfall)]
month.korname[which.min(rainfall)]


sample(1:20, 3) 
#앞에 범위부터 범위까지, 2번쨰 파라미터 만큼 뽑아리,중복된것없이 
sample(1:45, 6)
sample(1:10, 7)
sample(1:10, 7, replace=T)  #replace를 사용하면 중복 허용이다.

count <- sample(1:100,7)
month.korname <- c("일요일", "월요일", "화요일",
                   "수요일", "목요일", 
                   "금요일", "토요일")

paste(month.korname, count, sep = ":")  #1대 1 매칭으로 붙여준다.
#sep에 준 값은 델리미터로 들어간다.
month.korname[which.max(count)]
month.korname[which.min(count)]
month.korname[which(count > 50)]

paste(month.korname, count, sep = " : ")
#R은 paste로 문자열 결합을 한다.

paste("I'm","Duli","!!")
paste("I'm","Duli","!!", sep="")
paste0("I'm","Duli","!!")
#paste0 는 델리미터를 널문자열을 사용한다.

fruit <- c("Apple", "Banana", "Strawberry")
food <- c("Pie","Juice", "Cake")
paste(fruit, food)

paste(fruit, food, sep="")
paste(fruit, food, sep=":::")
paste(fruit, food, sep="", collapse="-")
paste(fruit, food, sep="", collapse="")
paste(fruit, food, collapse=",")


```

