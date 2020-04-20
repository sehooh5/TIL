# day15(Kormaps, RJDBC)

### oneMap

```r
library(Kormaps)
library(dplyr)
library(leaflet)

Encoding(names(korpopmap1)) <- 'UTF-8'
Encoding(names(korpopmap2)) <- 'UTF-8'
Encoding(names(korpopmap3)) <- 'UTF-8'

Encoding(korpopmap2@data$name)<-'UTF-8'
Encoding(korpopmap2@data$행정구역별_읍면동)<-'UTF-8'
Encoding(names(korpopmap3))<-'UTF-8'

Encoding(korpopmap3@data$name)<-'UTF-8'
Encoding(korpopmap3@data$행정구역별_읍면동)<-'UTF-8'

#데이터 가져오기
k2<-korpopmap2
k3 <- korpopmap3



#gucode<-NULL
guname <- '강남구'
gucode <- k2@data[k2@data$name == guname, "code.data"]
pattern <- paste0('^', gucode)

# 강남구 내용만 뽑아서 업데이트
# grep 으로 강남구의 내용만 추출
# polygons를 업데이트할때 문제가 생기므로
# grep 을 변수에 담아서 실행
grep<-grep(pattern, k3@data$code.data)
k3@data<-k3@data[grep,]
k3@polygons<-k3@polygons[grep] # 폴리건 지정할 애들 제한시키기

# 1인 가구 (강남구) 만 추출
# 동별 1인가구수
DONG<-read.csv('data/one.csv')
k3@data$name<-gsub('·','',k3@data$name) 
colnames(DONG)<-c('구별','name','일인가구')
dong <- DONG %>%filter(구별=='강남구')


# 1인가구 col 합쳐주기..dong 이름이 같은 자료들만
k3@data<-merge(k3@data,dong,by.x='name',sort=FALSE)
mymap <- k3@data

mypalette <- colorNumeric(palette ='Set3' , domain = k3@data$'일인가구')
mypopup <- paste0(mymap$name,'<br> 1인가구: ',k3@data$'일인가구')

map_gangnam <- NULL
map_gangnam<-leaflet(k3) %>% 
  addTiles() %>% 
  setView(lat=37.52711, lng=126.987517, zoom=12) %>%
  addPolygons(stroke =FALSE,
              fillOpacity = .7,
              popup = mypopup,
              color = ~mypalette(k3@data$일인가구)) %>% 
  addLegend( values = ~k3@data$일인가구,
             pal = mypalette ,
             title = '인구수',
             opacity = 1)

map_gangnam	

saveWidget(map_gangnam, file="oneMap.html")

install.packages("rgeos")
library(rgeos)

```

