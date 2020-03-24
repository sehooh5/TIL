# lab_05(if, switch,cat)

```r
#1
grade <- sample(1:6,1)
if(grade<=3)
  cat(grade,"학년은 저학년 입니다")
else
  cat(grade,"학년은 고학년 입니다")

#2
choice <- sample(1:5,1)
result<- switch(EXPR=choice
       ,300+50
       ,300-50
       ,300*50
       ,300/50
       ,300%%50)

#3
cat("결과값",result)

count <- sample(3:10,1)
deco <- sample(1:3,1)
deco_s <- as.character(deco)
switch(EXPR=deco
       ,"1"=for(i in 1:count)
         cat("*")
       ,"2"=for(i in 1:count)
         cat("$")
       ,"3"=for(i in 1:count)
         cat("#"))


#4
score <- sample(0:100,1)
if(score<=100&score>=90)
  grade<-"A"
if(score<=89&score>=80)
  grade<-"B"
if(score<=79&score>=70)
  grade<-"C"
if(score<=69&score>=60)
  grade<-"D"
if(score<=59&score>=50)
  grade<-"F"

switch(EXPR=grade
       , "A"=level<-"A 등급"
       , "B"=level<-"B 등급"
       , "C"=level<-"C 등급"
       , "D"=level<-"D 등급"
       , "F"=level<-"F 등급"
      )
cat(score,"점은",level,"입니다")

#5
print(paste(LETTERS,letters,sep=""))


```

