# dply_lab2

```r
1. ggplot2 패키지에서 제공되는 mpg 라는 데이터 셋의 구조를 확인한다.
이 mpg 를 데이터프레임으로 변환하여 mpg 에 저장한다.(as.data.frame())
install.packages("ggplot2")
str(ggplot2::mpg)
mpg <- as.data.frame(ggplot2::mpg)
1-1 mpg의 구조를 확인한다.
1-2 mpg 의 행의 개수와 열의 개수를 출력한다.
1-3 mpg의 데이터를 앞에서 10개 출력한다.
1-4 mpg의 데이터를 뒤에서 10개 출력한다.
1-5. mpg의 데이터를 GUI 환경으로 출력한다.
1-6 mpg를 열 단위로 요약한다.
1-7 mpg 데이터셋에서 제조사별 차량의 수를 출력한다.
1-8 mpg 데이터셋에서 제조사별 그리고 모델별 차량의 수를 출력한다.
2. 다음에 제시된 문제를 각각 2-1, 2-2 으로 넘버링 하여 해결 코드를 R로 작성한다.
#1-1
str(mpg)
#1-2
mpg %>% summarise(rnum=n(),cnum=ncol(mpg))
#1-3
mpg %>% head(10)
#1-4
mpg %>% tail(10)
#1-5
View(mpg)
#1-6
summary(mpg)
mpg %>% group_by(manufacturer) %>% tally()
mpg %>% group_by(model) %>% tally()
mpg %>% group_by(displ) %>% tally()
mpg %>% group_by(year) %>% tally()
mpg %>% group_by(cyl) %>% tally()
mpg %>% group_by(trans)%>% tally()
mpg %>% group_by(drv) %>% tally()
mpg %>% group_by(cty) %>% tally()
mpg %>% group_by(hwy) %>% tally()
mpg %>% group_by(fl) %>% tally()
mpg %>% group_by(class) %>% tally()
#1-7
mpg %>% group_by(manufacturer) %>% tally()
#1-8
mpg %>% group_by(manufacturer,model) %>% tally()


#2-1
mpg %>% rename(city=cty,highway=hwy)
#2-2
mpg

midwest
#3-1
midwest<-as.data.frame(midwest)
str(midwest)
View(midwest)
#3-2
midwest %>% rename(total=poptotal,asian=popasian) ->midwest
#3-3
midwest %>% mutate('전체 인구 대비 아시아 인구 백분율'=asian/total*100) ->midwest
#3-4
avg_asian <-mean(midwest$per_asian)
midwest %>% mutate(compare=if_else(per_asian>mean(per_asian),"large","small"))


#4-1
mpg %>% group_by(displ) %>% summarise(mean_hyw=mean(hwy)) %>% filter(displ>=5 | displ<=4)
mpg %>% filter(displ>=5) %>% group_by(displ) %>% summarise(mean_hyw=mean(hwy)) -> test1
mpg %>% filter(displ<=4) %>% group_by(displ) %>% summarise(mean_hyw=mean(hwy)) -> test2
test1 %>% summarise(over5=mean(mean_hyw))
test2 %>% summarise(lower4=mean(mean_hyw))

#4-2
mpg %>% group_by(manufacturer) %>% summarise(mean_cty=mean(cty)) %>% filter(manufacturer=="audi"|manufacturer=="toyota")

#4-3
mpg %>% group_by(manufacturer) %>% summarise(mean_hwy=mean(hwy)) %>% filter(manufacturer=="chevrolet"|manufacturer=="toyota"|manufacturer=="honda")


#5-1
mpg %>% select(class,cty) ->mpg2
#5-2
mpg2 %>% group_by(class) %>% summarise(mean_cty=mean(cty)) %>% filter(class=="suv"|class=="compact")


#6-1
mpg %>% filter(manufacturer=="audi") %>% select(model, hwy) %>% arrange(desc(hwy)) %>% head(5)

```

