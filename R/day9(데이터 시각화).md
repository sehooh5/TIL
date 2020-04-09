# day9(데이터 시각화)

### 고수준 함수

- 한개만 적용할 수있다, 여러개 하려면 쪼개야한다

- ```
  plot(), boxplot(), hist(), pie(), barplot()
  ```

- plot() : 산전도, 점이 퍼진 형태, pch 지정으로 점의 모양을 변형시킬 수 있다

### 저수준 함수

- 조연으로 필요한 만큼 사용 가능하며 꾸며주는 역할을 한다

- ```
  title(), lines(), axis(), legend(), points(), text()
  ```

- 

### 칼라팔레트 함수

- 색상 관련된 함수

- ```
  rainbow(), cm.colors(), topo.colors(), terrian.colors(), heat.colors()
  ```

- 



### 산포도

- x, y 축의 값들을 지정해줘서 상관관계를 표현할 수 있다

- 최소값과 최대값으로 그래프의 범위를 자동으로 지정해준다

- 여러 그래프, 값을 그려주려면 한개만 고수준, 나머지는 저수준으로 !

- - 

- ```
  - par(mar=c(1,1,1,1), mfrow(c(4,2)))
    # mar : 상하좌우 마진
    # mfrow : 4행 2열 (총 8개의 plot 을 보여줄 수 있다)
  
  - plot(국어, type="o", col="blue", ylim=c(0,12), axes=F, ann=FALSE) ## ylim : y 축, axis=F : 축, ann : ?
  
  - axis(1, at=1:10, lab=c("01","02","03","04", "05","06","07","08","09","10")) # x축추가
  - axis(2, at=c(0,2,4,6,8,10)) # y축추가
  
  - title(main="성적그래프", col.main="red", font.main=4)
  - title(xlab="학번", col.lab=rgb(0,1,0)) ##x축 이름
  - title(ylab="점수", col.lab=rgb(1,0,0))
  - legend(1, 10, c("국어","수학"), cex=0.8, col=c("blue","red"), pch=c(16,21), lty=c(1,2)) 
  # legend : 범례, cex : 사이즈, 1,2번째 매개변수 : 범례의 위치
  
  - png(filename="성적.png", height=400, width=700, bg="white") # 출력을png파일로설정
  - dev.off() #출력종료
  ```



### 바 그래프 

- barplot()

- ```
  - coldens<-seq(from=10, to=100, by=10) # 막대그래프의색밀도설정을위한벡터
  - xname<-성적$학번
  
  - barplot(성적$국어, main="성적그래프", xlab="학번", ylab="점수", border="red", col="green", density=coldens,names.arg=xname)
  # density : 밀도
  # names.arg : 각 각의 이름
  ```

- ```
  성적1<-성적[3:5] ##(중요!)성적에서 3-5번째만 추출 
  barplot(as.matrix(성적1), main="성적그래프", ylab="점수",
  beside=TRUE, col=rainbow(10))
  ## as.matrix 로 10행 3열(3-5)짜리 매트릭스를 만들어줌
  ## beside=T : 옆으로 그린다
  
  
  xname<-성적$학번; # x축레이블용벡터
  barplot(t(성적1), main="성적그래프", ylab="점수", col=rainbow(3),space=0.1, cex.axis=0.8, names.arg=xname, cex=0.8)
  legend(0,28, names(성적1), cex=0.8, fill=rainbow(3));
  
  
  barplot(t(성적1), main="성적그래프",ylab="학번",col=rainbow(3),
  space=0.1, cex.axis=0.8, names.arg=xname,cex=0.8, horiz=T)
  
  legend(22, 4, names(성적1), cex=0.8,fill=rainbow(3));
  ```



### 히스토그램 

- 분포도만 보고싶을때 사용

- hist()

- ```
  hist(성적$수학, main="성적분포-수학", xlab="점수", col = "lightblue",border = "pink")
  ```

- ```
  hist(성적$국어, main="성적분포-국어", xlab="점수", ylab="도수",
  breaks=6, col=rainbow(12), border = "pink")
  ## breaks : 구간을 나누는 변수,,즉 6개로 나누어 그림
  
  hist(성적$국어, main="성적분포-국어", xlab="점수", ylab="도수",
  breaks=6, col=rainbow(12), border = "pink", prob=T)
  ## brob : 퍼센트 보여줌
  ```



### 파이 그래프 

- pie()

- ```
  pie(성적$국어, labels=paste(성적$성명, "-", 성적$국어), col=rainbow(10))
  pie(성적$국어, labels=paste(성적$성명, "-", 성적$국어), col=rainbow(10), main="국어성적", edges=10) # edges : 사각형
  pie(성적$국어, labels=paste(성적$성명,"\n","(",성적$국어,")"), col=rainbow(10))
  # clockwise=T : 정시계 방향// default 는 F
  ```



###  박스 그래프 

- boxplot()

- 이상치가 있을 때 사용한다

- ```
  summary(성적$국어)
  boxplot(성적$국어, col="yellow", ylim=c(0,10), xlab="국어",
  ylab="성적")
  ```

- ```
  성적2 <-성적[3:5]
  boxplot(성적2, col=rainbow(3), ylim=c(0,10), ylab="성적")
  
  boxplot(data, las= 2)
  ## las=2 : 라벨을 세로로 
  boxplot(data, las= 2, at = c(1,2,3,4, 6,7,8,9, 11,12,13,14))
  chtcols= rep(c("red","sienna","palevioletred1","royalblue2"), times=3); ##색상 반복해주는 역할
  boxplot(data, las= 2, at = c(1,2,3,4, 6,7,8,9, 11,12,13,14), col=chtcols)
  grid(col="gray", lty=2, lwd=1) ## 격자 주기
  ```



```r
# 데이터 시각화
rainbow(10)

국어<- c(4,7,6,8,5,5,9,10,4,10)  
plot(국어)

plot(국어, type="o", col="blue")       
title(main="성적그래프", col.main="red", font.main=4) 

국어 <- c(4,7,6,8,5,5,9,10,4,10)
수학 <- c(7,4,7,3,8,10,4,10,5,7)


plot(국어, type="o", col="blue")
lines(수학, type="o", pch=16, lty=2, col="red")     
title(main="성적그래프", col.main="red", font.main=4)

국어 <- c(4,7,6,8,5,5,9,10,4,10)
par(mar=c(1,1,1,1), mfrow=c(4,2))

plot(국어, type="p", col="blue", main="type = p", xaxt="n", yaxt="n")
plot(국어, type="l", col="blue", main="type = l", xaxt="n", yaxt="n")
plot(국어, type="b", col="blue", main="type = b", xaxt="n", yaxt="n")
plot(국어, type="c", col="blue", main="type = c", xaxt="n", yaxt="n")
plot(국어, type="o", col="blue", main="type = o", xaxt="n", yaxt="n")
plot(국어, type="h", col="blue", main="type = h", xaxt="n", yaxt="n")
plot(국어, type="s", col="blue", main="type = s", xaxt="n", yaxt="n")
plot(국어, type="S", col="blue", main="type = S", xaxt="n", yaxt="n")

국어 <- c(4,7,6,8,5,5,9,10,4,10); 
수학 <- c(7,4,7,3,8,10,4,10,5,7)
par(mar=c(5,5,5,5), mfrow=c(1,1))
plot(국어, type="o", col="blue", ylim=c(0,10), axes=FALSE, ann=FALSE) 

# x, y 축 추가하기
axis(1, at=1:10, lab=c("01","02","03","04", "05","06","07","08","09","10")) # x축 추가
axis(2, at=c(0,2,4,6,8,10))  # y축 추가

# 그래프 추가하고, 그래프에 박스 그리기
lines(수학, type="o", pch=16, lty=2, col="red")    
box()   # 박스 그리기

# 그래프 제목, 축의 제목, 범례 나타내기
title(main="성적그래프", col.main="red", font.main=4) 
title(xlab="학번", col.lab=rgb(0,1,0)) 
title(ylab="점수", col.lab=rgb(1,0,0)) 
legend(8, 3, c("국어","수학"), cex=0.8, col=c("blue","red"), pch=c(21,16), lty=c(1,2))  






(성적 <- read.table("data/성적.txt", header=TRUE))

plot(성적$학번, 성적$국어, main="성적그래프", xlab="학번", ylab="점수",  xlim=c(0, 11), ylim=c(0, 11)) 

ymax <- max(성적[3:5]) #성적 데이터 중에서 최대값을 찾는다(y 축의 크기 제한)
ymax
pcols<- c("red","blue","green")
png(filename="성적.png", height=400, width=700, bg="white") # 출력을 png파일로 설정
plot(성적$국어, type="o", col=pcols[1], ylim=c(0, ymax), axes=FALSE, ann=FALSE)
axis(1, at=1:10, lab=c("01","02","03","04","05","06","07","08","09","10"))
axis(2, at=c(0,2,4,6,8,10), lab=c(0,2,4,6,8,10))
box()
lines(성적$수학, type="o", pch=16, lty=2, col=pcols[2])
lines(성적$영어, type="o", pch=23, lty=3, col=pcols[3] )
title(main="성적그래프", col.main="red", font.main=4)
title(xlab="학번", col.lab=rgb(1,0,0))
title(ylab="점수", col.lab=rgb(0,0,1))
legend(1, ymax, names(성적)[c(3,4,5)], cex=0.8, col=pcols, pch=c(21,16,23), lty=c(1,2,3))
dev.off()

plot(국어, 수학)
plot(수학~국어)

?plot

# 막대그래프 그리기

barplot(국어)

coldens <- seq(from=10, to=100, by=10)   # 막대그래프의 색밀도 설정을 위한 벡터
xname <- 성적$학번                                         # X 축 값 설정위한  벡터
barplot(성적$국어, main="성적그래프", xlab="학번", ylab="점수", border="red", col="green", density=coldens, names.arg=xname)

# 학생의 각 과목에 대한 각각의 점수에 대한 그래프
성적1<- 성적[3:5] 
str(성적1)
par(mar=c(5,5,5,5), mfrow=c(1,1))
barplot(as.matrix(성적1), main="성적그래프", beside=T, ylab="점수", col=rainbow(10))

par(mar=c(5,5,5,5), mfrow=c(1,2))
barplot(as.matrix(성적1), main="성적그래프", ylab="점수", col=rainbow(10))
barplot(t(성적1), main="성적그래프", ylab="점수", col=rainbow(10))

par(mar=c(5,5,5,5), mfrow=c(1,1))
# 학생의 각 과목에 대한 합계 점수에 대한 그래프
xname <- 성적$학번;    #  x축 레이블용 벡터
par(xpd=T, mar=par()$mar+c(0,0,0,4));   # 우측에 범례가 들어갈 여백을 확보
barplot(t(성적1), main="성적그래프", ylab="점수", col=rainbow(3), space=0.1, cex.axis=0.8, names.arg=xname, cex=0.8)
legend(11,30, names(성적1), cex=0.8, fill=rainbow(3));

par(xpd=T, mar=c(5,5,5,5));   # 우측에 범례가 들어갈 여백을 확보
barplot(t(성적1), main="성적그래프", ylab="점수", col=rainbow(3), space=0.1, cex.axis=0.8, names.arg=xname, cex=0.8)
legend(11,30, names(성적1), cex=0.8, fill=rainbow(3));


# 학생의 각 과목에 대한 합계 점수에 대한 그래프(가로막대 그래프)
xname <- 성적$학번;    #  x축 레이블용 벡터
barplot(t(성적1), main="성적그래프", ylab="학번", col=rainbow(3), space=0.1, cex.axis=2.0, names.arg=xname,cex.lab=3.0, horiz=T);
legend(30,11, names(성적1), cex=0.8, fill=rainbow(3))

?barplot


# 파이그래프
(성적 <- read.table("data/성적.txt", header=TRUE));
pie(성적$국어, labels=paste(성적$성명, "-", 성적$국어), col=rainbow(10))
pie(성적$국어, clockwise=T, labels=paste(성적$성명, "-", 성적$국어), col=rainbow(10))
pie(성적$국어, density=10, clockwise=T, labels=paste(성적$성명, "-", 성적$국어), col=rainbow(10))
pie(성적$국어, labels=paste(성적$성명, "-", 성적$국어), col=rainbow(10), main="국어성적", edges=10)
pie(성적$국어, labels=paste(성적$성명,"\n","(",성적$국어,")"), col=rainbow(10))
pie(rep(1, 24), col = rainbow(24), radius = 0.5)


# 히스토그램
hist(성적$국어, main="성적분포-국어", 
       xlab="점수", breaks=5, 
       col = "lightblue", border = "pink")
hist(성적$수학, main="성적분포-수학", 
       xlab="점수", col = "lightblue", 
       breaks=2, border = "pink")
hist(성적$국어, main="성적분포-국어", 
       xlab="점수", ylab="도수", 
       col=rainbow(12), border = "pink")

nums <- sample(1:100, 30)
hist(nums)
hist(nums, breaks=c(0,10,20,30,40,50,60,70,80,90,100))
hist(nums, breaks=c(0,50,100), probability = T)
hist(nums, breaks=c(0,33,66,100))


# 박스플롯
summary(성적$국어)
boxplot(성적$국어, col="yellow",  ylim=c(0,10), xlab="국어", ylab="성적")


성적2 <- 성적[,3:5]
boxplot(성적2, col=rainbow(3), ylim=c(0,10), ylab="성적")

data <- read.table("data/온도.txt", header=TRUE, sep=",")
head(data, n=5); 
boxplot(data)
boxplot(data, las = 2)
boxplot(data, las = 2, at = c(1,2,3,4, 6,7,8,9, 11,12,13,14))
chtcols = rep(c("red","sienna","palevioletred1","royalblue2"), times=3)
chtcols
boxplot(data, las = 2, at = c(1,2,3,4, 6,7,8,9, 11,12,13,14), col=chtcols)
grid(col="gray", lty=2, lwd=1)

rainbow()
heat.colors()
terrain.colors()
topo.colors()
cm.colors()
gray.colors()


```

