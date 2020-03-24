# lab_04(list)

```r
#1
L1 <- list(
  name= "scott"
  , sal=3000)
result1<-L1$sal*2

#2
L2 <- list(
  "scott"
  ,c(100,200,300)
)

#3
L3 <- list(
  c(3,5,7)
  ,c("A","B","C")
)
L3[[2]][1]<-"Alpha"

#4
L4<-list(
  alpha=0:4
  ,beta=(sqrt(1:5))
  ,gamma=log(1:5)
)
L4$alpha+10

#5
L5<- list(
  math=list(95,90)
  ,writing=list(90,85)
  ,reading=list(85,80)
); L5
new_L5=c(
  mean(unlist(L5$math)),
  mean(unlist(L5$writing)),
  mean(unlist(L5$reading))
)
mean(new_L5)
#방법 2
L5 <- unlist(L5)
mean(L5)

#6
time <-32150
hh <- time%/%3600
mm <- time%%3600%/%60
ss <- time-(hh*3600+mm*60)
hh+" 시간 "+mm+" 분 "+ss+" 초"
print(paste(hh," 시간",mm, " 분", ss," 초",collapse=" " ),quote=F)
cat(hh,"시간",mm,"분",ss,"초","\n")

```

