# lab_01(which, past,order...)

```r
#문제1
v1 <- c(1:10); v1
v2 <- v1*2; v2
max_v <- max(v2); max_v
min_v <- min(v2); min_v
avg_v <- mean(v2); avg_v
sum_v <- sum(v2); sum_v
v3 <- v2[-5]; v3
v1;v2;v3;max_v;min_v;avg_v;sum_v

#문제2
seq(1,9,2)
rep(1,5,each=1)
rep(1:3,3)
rep(1:4,each=2)

#문제3
nums <- sample(1:100,10)
sort(nums)
sort(nums,decreasing=T)
nums[which(nums>50)]
which(nums<=50)
which.max(nums)
which.min(nums)

#4
v8 <- seq(1,10,3)
names(v8) <- LETTERS[1:4]

#5
score1<-sample(1:20,5)
myFriend <- c("둘리", "또치", "도우너", "희동", "듀크")
paste(v8,myFriend,sep=" - ")
myFriend[which.max(v8)]
myFriend[which.min(v8)]
myFriend[which(v8>=10)]

```

