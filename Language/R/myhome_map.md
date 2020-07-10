# myhome_map

```r
sc<-as.numeric(format(Sys.time(),"%S"))
mk <- geocode(enc2utf8("중산마을 태영아파트"), source = "google")
cen <- c(mk$lon, mk$lat)
if (sc>=0&sc<15){
  map <- get_map(center=cen, maptype="terrain",zoom=15, marker=mk)
}else if(sc>=15&sc<29){
  map <- get_map(center=cen, maptype="satelite",zoom=15, marker=mk)
}else if(sc>=30&sc<44){
  map <- get_map(center=cen, maptype="roadmap",zoom=15, marker=mk)
}else if(sc>=45&sc<59){
  map <- get_map(center=cen, maptype="hybrid",zoom=15, marker=mk)
}
ggmap(map) + labs(title="오세후 동네", x="경도", y="위도")
ggsave("mymap.png")



```

