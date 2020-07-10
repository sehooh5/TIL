# lab_06(fucntion)

```r
#1
exam1 <-function(){
  return(paste(LETTERS,letters,sep=""))
}
print(exam1())

#2
exam2<-function(x){
  data<-c(1:x)
  sum<-0
  for(i in data)
    sum <- sum+i
  return(sum)
}
print(exam2(5))

#3
exam3<-function(x,y){
  if(x>y){
    result<-(x-y)
  }else if(x<y){
    result<-(y-x)
  }else if(x==y){
    result<-0
  }
  return(result)
}
print(exam3(10,07))

#4
exam4<-function(x,y,z){
  if(y=="+"|y=="-"|y=="*"){
    result<-switch(EXPR=y
              ,"+"=x+z
              ,"-"=x-z
              ,"*"=x*z)
  }else if(y=="%%"|y=="%/%"){
    if(x==0){
      result<-"오류1"
    }else if(z==0){
      result<-"오류2"
    }else{
      result<-switch(EXPR=y
                     ,"%%"=x%%z
                     ,"%?%"=x%/%z)
    }
  }else
    result<-"규격의 연산자만 전달하세요"
  return(result)
}
exam4(3,"-",2)

#5
exam5<-function(x,y="#"){
  if(x>=0){
    for(item in 1:x)
      cat(y)
  }
}   
exam5(x=3)

#6
rm(score)
exam6<-function(score){
  for(item in score){
    if(item>=85&item<=100){
      grade<-"상"
    }else if(item>=70&item<=84){
      grade<-"중"
    }else if(item<70){
      grade<-"하"
    }else if(is.na(item))
      print("NA는 처리불가")
    print(paste(item,"점은 ",grade,"등급 입니다",sep=""))
  }
}

exam6(c(50,70,80,90,NA))
```

