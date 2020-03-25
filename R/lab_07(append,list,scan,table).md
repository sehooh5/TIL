# lab_07(append,list,scan,table)

```r
#1
countEvenOdd<-function(...){
  x<-c(...)
  e<-0
  o<-0
  if(is.numeric(x)){
    for(i in x){
      if(i%%2==0){#짝수
        e<-e+1
      }else{
        o<-o+1
      }
    }
      return(list(odd=o,even=e))
  }else
    return()
}
countEvenOdd(1,2,3,4,5)


#2
rm(sum)
vmSum<-function(...){
  x<-c(...)
  rs2<-0
  for(i in x){
    if(!is.vector(i))
      return("벡터만 전달하숑!")
    else if(is.vector(i)&!is.numeric(i)){
      print("숫자벡터를 전달하숑!")
      return(0)
    }else{
        rs2<-rs2+i
    }
  }
  return(rs2)
}
vmSum("하이")
vmSum(1,2,3)


#3
vmSum2<-function(...){
  x<-c(...)
  rs3<-0
  for(i in x){
    if(!is.vector(i))
      stop("벡터만 전달하숑!")
    else if(is.vector(i)&!is.numeric(i)){
      warning("숫자벡터를 전달하숑!")
      return(0)
    }else{
      rs3<-rs3+i
    }
  }
  return(rs3)
}
vmSum2("하이")
vmSum(3,6,9)


#4
mySum<-function(...){
  x<-c(...)
  #print(min(x,na.rm = T))
  cnt<-0
  even<-0
  odd<-0
  for(i in x){
    if(!is.vector(i))
      stop("벡터만 처리 가능!")
    else if(is.null(i))
      return()
    else{
      if(is.na(i)){
        warning("NA를 최저값으로 변경하여 처리함")
        i<-min(x,na.rm = T)
      }
      cnt<-cnt+1
      if (cnt%%2!=0){
        odd<-odd+i
      }else{
        even<-even+i
      }
    }
    
  }
  return(list(oddSum=odd,evenSum=even))
}
mySum("하이")
mySum(1,2,1,2,1,2)
mySum(1,5,3,2,NA)
mySum()


#5
myExpr<-function(x){
  if(!is.function(x)){
    stop("수행 안할꺼임!!")
  }else{
    num<-sample(1:45,6)
    return(x(num))
  }
}
myExpr(sum)


#6
createVector1<-function(...){
  x<-c(...)
  if(is.null(x))
    return()
  else if(any(is.na(x)))
    return(NA)
  else
    return(x)
}
createVector1(1,"가",NA)


#7
createVector2<-function(...){
  x<-list(...)
 # print(is.numeric(x[[1]]))
  num<-NULL
  cha<-NULL
  log<-NULL
  if(is.null(x))
    return()
  else if(any(is.na(x)))
    return(NA)
  else{
    for(data in x){
      if(is.numeric(data)){
        num<-append(num,data)
      }else if(is.character(data))
        cha<-append(cha,data)
      else if(is.logical(data))
        log<-append(log,data)
    }
  }
  x<-list(numeric=num,character=cha,logical=log)
    return(x)
}
createVector2(1,2,3,"하이",T,F)
createVector2()
test<-c(2)




```





## iotest

```r
#8
numb <- scan("data/iotest1.txt")
numb1<- sort(numb)
numb2<- sort(numb,decreasing = T)
numb3<-sum(numb)
numb4<-mean(numb)
cat("오름차순 : ",numb1
    ,"\n내림차순 : ",numb2
    ,"\n합 : ",numb3
    ,"\n평균 : ",numb4
)


#[ 문제9 ] 


data <- scan("data/iotest2.txt", what="",encoding="UTF-8")
data<-table(data)
which.max(data)
lang<-names(data)[which.max(data)]
cat("가장 많이 등장한 단어는",lang,"입니다.")


```

