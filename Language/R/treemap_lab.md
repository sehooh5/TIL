# treemap_lab

```r
data(GNI2014)
str(GNI2014)
View(GNI2014)

png(filename="treemap.png", bg="white")
windowsFonts(let=windowsFont("휴먼옛체"))
treemap(GNI2014,fontfamily.title = "let",fontfamily.labels = "let", vSize="population", index=c("continent", "country"), title="대륙별 인구현황")

dev.off()

?treemap

```

