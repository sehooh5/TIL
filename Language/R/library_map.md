# library_map

```r

df <- read.csv("data/지역별장애인도서관정보.csv", stringsAsFactors=F) 
View(df_add)

df_add <- df[,c(2,5,9,10)]
names(df_add) <- c("name","address", "lat", "lon")

gc <- geocode(enc2utf8(df_add$address))
seoul <- geocode("seoul", source = "google")
cen <- c(seoul$lon, seoul$lat)
#mk <- c(df_add$lon,df_add$lat)
map <- get_googlemap(center=cen, maptype="roadmap",zoom=11)

ggmap(map)+
  geom_point(data=df_add, aes(x=lon, y=lat), alpha=0.5, size=2, color="red")+
  geom_text(data=df_add,               
            aes(x=lon,y=lat,color="black"),               
            size=3,                
            label=df_add$name,
            color="black",
            vjust=-1, 
            hjust=0.5) 

ggsave("library.png")


```

