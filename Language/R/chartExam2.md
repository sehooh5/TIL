# chartExam2

```r
library(ggplot2)
library(dplyr)
# 문제 1
도시연비<-mpg$cty
고속도로연비<-mpg$hwy
plot(도시연비, 고속도로연비, xlab="도시연비", ylab="고속도로연비",  xlim=c(0, 35), ylim=c(0, 45), pch=3) 



# 문제 2
mpg$drv
barplot(table(mpg$drv),col=c("red","green","blue"))


#문제 3
boxplot(mpg$hwy~mpg$manufacture,data=mpg,las=2,ylab="고속도로연비",xlab="",col=heat.colors(15))
title(main="*제조사별 고속도로 연비*", col.main="magenta")
```

