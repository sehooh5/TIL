# myhome_leaflet

```r

install.packages("htmlwidgets")
library(htmlwidgets)
library(leaflet)
library(dplyr)
library(ggmap)
register_google(key='AIzaSyD8k2DWC_7yFHCrH6LDR3RfITsmWMEqC8c')

home<-geocode("chapter old street")
geocode("seoul")

msg <- '<strong><a href="https://www.chapter-living.com/properties/old-street" style="text-decoration:none" >London House</a></strong><hr>영국에서 마지막 살았던 곳'

map <- leaflet() %>% 
  setView(lng = home$lon, lat = home$lat, zoom = 15) %>%
  addTiles()  %>% 
  addCircles(lng = home$lon, lat = home$lat, color='magenta', popup = msg )
map


saveWidget(map, file="mymap.html")


```

