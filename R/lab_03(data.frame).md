# lab_03(data.frame)

```r
#1
str(airquality)

#2
x <- c(1:5)
y <- seq(2,10,2)
df1 <- data.frame(x,y)

#3
col1 <- c(1:5)
co12 <- letters[c(1:5)]
col3 <- c(6:10)
df2 <- data.frame(col1,col2,col3)

#4
제품명 <- c("사과","딸기","수박")
가격 <- c(1800,1500,3000)
판매량 <- c(24,38,13)
df3 <- data.frame(제품명, 가격, 판매량, stringsAsFactors=F)
df3
str(df3)

#5
df3$"가격 평균" <- mean(df3$가격)
df3$"판매량 평균" <- mean(df3$판매량)
                     
#6
name <- c("Potter", "Elsa", "Gates", "Wendy", "Ben")
gender <- factor(c("M","F","M","F","M"))
math <- c(85, 76, 99, 88, 40)
df4 <- data.frame(name,gender,math,stringsAsFactors=F)
str(df4)
df4$stat <- c(76,73,95,82,35)
df4$score <- df4$math+df4$stat
df4$grade <- ifelse(df4$score>=150, "A", 
                    ifelse(df4$score<150 & df4$score>=100, "B",
                           ifelse(df4$score<100 & df4$score>=70,"C","D")))

#7
str(emp)

#8
emp[3:5,]

#9
subset(emp,select=c("ename"))

#10
subset(emp,select=c("ename","sal"))

#11
subset(emp,select=c("ename","sal","job"),subset= emp$job=="SALESMAN")
emp[emp$job=="SALESMAN",c("ename","sal","job")]
       
#12
emp[emp$sal>=1000 & emp$sal<=3000,c("ename","sal","empno")]

#13
emp[emp$job!="ANALYST",c("ename","job","sal")]

#14
emp[emp$job=="SALESMAN"|emp$job=="ANALYST",c("ename","job")]

#15
emp[is.na(emp$comm),c("ename","sal")]

#16
emp[order(emp$sal),]
##sort(emp$sal)
order(emp$sal)

```

