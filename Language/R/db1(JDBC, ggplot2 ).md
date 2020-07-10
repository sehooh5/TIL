# db1(JDBC, ggplot2 )

```r
install.packages("DBI");
install.packages("RJDBC");
library(RJDBC)
library(DBI)
library(ggplot2)

# JDBC driver 의 위치에 넣어놓고 PATH 지정해주기(ojdbc6.jar)
drv <- JDBC("oracle.jdbc.driver.OracleDriver","C:/Users/student/Desktop/SEHO/ojdbc6.jar")
conn <- dbConnect(drv,"jdbc:oracle:thin:@localhost:1521:xe","jdbctest","jdbctest")
conn  


View(iris)
# 들어갈때 "."은 사용 못하므로 바꿔주는 과정!
names(iris) <-c("SLENGTH", "SWIDTH", "PLENGTH", "PWIDTH", "SPECIES")
dbWriteTable(conn,"IRIS",iris)
dbGetQuery(conn, "SELECT * FROM IRIS")

rs <- dbSendQuery(conn,"SELECT * FROM IRIS")
ret <- dbFetch(rs)

ggplot(data = ret, aes(x = SLENGTH, y = SWIDTH, col = SPECIES)) + geom_point()
ggsave("db1.jpg")

ggplot(data = ret, aes(x = PLENGTH, y = PWIDTH, col = SPECIES)) + geom_point()
ggsave("db2.jpg")

```

