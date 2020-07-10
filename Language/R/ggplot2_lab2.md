# ggplot2_lab2

```r
library(ggplot2)
library(plotly)

#Q1
ggplot(data = mpg, aes(x = cty, y = hwy)) + geom_point(color="blue")
ggsave("result1.png")

#Q2
ggplot(mpg, aes(x=class,fill=drv)) + 
  geom_bar()
ggsave("result2.png")

#Q3
View(midwest)
ggplot(midwest, aes(x=total,y=asian))+
  geom_point()+
  coord_cartesian(xlim=c(0, 500000),ylim =c(0,10000))
ggsave("result3.png")

#Q4
View(mpg) 
mpg %>% filter(class == c("compact","subcompact","suv"))->m2
gp<-ggplot(m2,aes(x=class,y=cty))+
  coord_cartesian(ylim=c(0,35))+
  geom_boxplot()
ggsave("result4.png")

#Q5
click<- read.table("data/product_click.log")
df_click<-data.frame(click)
ggplot(df_click,aes(x=V2,fill=V2))+
  geom_bar()+
  guides(color = guide_legend(nrow = 6))+
  theme_light()
ggsave("result5.png")

#Q6
t


d<-click$V1
d2 %>% substr(start=1,stop=8) %>% 
  as.POSIXct(format="%Y%m%d") %>% 
  weekdays() %>%
#  table() %>% 
  data.frame() ->d3
names(d3)<-"day"
str(d3)
View(d3)

##scale_x_discrete 중요!!!
ggplot(d3,aes(x=day,fill=day))+
  geom_bar()+
  labs(x="요일", y="클릭수")+
  scale_x_discrete(limits=c("월요일","화요일","수요일","목요일","금요일"))
ggsave("result6.png")

```

