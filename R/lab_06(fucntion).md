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

```

