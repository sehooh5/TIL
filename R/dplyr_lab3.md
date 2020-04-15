# dplyr_lab3

```r
mpg
#7-1
mpg2<-mpg %>% mutate(to.y=hwy+cty)
#7-2
mpg2<-mpg2 %>% mutate(mean.y=to.y/2)
#7-3
mpg2 %>% arrange(desc(mean.y)) %>% head(3)
#7-4
mpg %>% mutate(to.y=hwy+cty,mean.y=to.y/2) %>% arrange(desc(mean.y)) %>% head(3)


#8-1
mpg %>% 
  group_by(class) %>% 
  summarise(mean_cty=mean(cty)) 
#8-2
mpg %>% 
  group_by(class) %>% 
  summarise(mean_cty=mean(cty)) %>% 
  arrange(desc(mean_cty))
#8-3
mpg %>% 
  group_by(manufacturer) %>% 
  summarise(mean_hwy=mean(hwy)) %>% 
  arrange(desc(mean_hwy)) %>% 
  head(3)
#8-4
mpg %>% 
  group_by(manufacturer) %>% 
  filter(class=="compact") %>% 
  tally() %>% 
  arrange(desc(n))


#9-1
fuel <- data.frame(fl=c("c","d","e","p","r")
                   ,price_fl=c(2.35,2.38,2.11,2.76,2.22)
                   ,stringsAsFactors = F)
fuel

mpg<-left_join(mpg,fuel,by="fl")
#9-2
mpg %>% select(model,fl,price_fl) %>% head(5)


#10-1
View(midwest)
midwest %>% mutate("전체 인구 대비 미성년 인구 백분율"=popadults/total)
#10-2
midwest %>% 
  mutate(ad.ratio=popadults/total,non.ratio=1-ad.ratio) %>% 
  arrange(ad.ratio) %>% 
  select(county,non.ratio) %>% 
  head(5)
#10-3
midwest %>% mutate(arr=ifelse(non.ratio>=0.4,"large",
                              ifelse(non.ratio<0.4&non.ratio>=0.3,"middle","small"))) %>% 
  select(arr,county) %>% 
  group_by(arr) %>% 
  count(arr)
#10-4
midwest %>% 
  mutate(asian.ratio=asian/total) %>% 
  select(state,county,asian.ratio) %>% 
  arrange(asian.ratio) %>% 
  tail(10)


#11-1
mpg<-as.data.frame(ggplot2::mpg)
mpg[c(65,124,131,153,212),"hwy"]<-NA

table(is.na(mpg$drv))
table(is.na(mpg$hwy))

#11-2
mpg %>% 
  group_by(drv) %>%
  filter(!is.na(hwy)) %>% 
  summarise(mean(hwy))


#12-1
mpg<-as.data.frame(ggplot2::mpg)
mpg[c(10,14,58,93),"drv"]<-"k"
mpg[c(29,43,129,203),"cty"]<-c(3,4,39,42)


table(mpg$drv)
mpg$drv<-ifelse(mpg$drv %in% "k",NA,mpg$drv)

#12-2
boxplot(mpg$cty)$stats
summary(mpg$cty)
#19-14=5///7.5+19=26.5//14-7.5=6.5
mpg$cty<-ifelse(mpg$cty>26.5|mpg$cty<6.5,NA,mpg$cty)

#12-3
mpg %>% 
  group_by(drv) %>% 
  filter(!is.na(cty)&!is.na(drv)) %>% 
  summarise(mean(cty)) 
  

View(mpg)

```

