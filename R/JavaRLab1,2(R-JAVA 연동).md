# JavaRLab1,2(R-JAVA 연동)

### lab.R

```r
#[ Java와 R 연동 실습  ]

library(KoNLP)
ht <- readLines("hotel.txt")
ht2 <- sapply(ht, extractNoun, USE.NAMES = F)
unht <- unlist(ht2)
unht2<-gsub("[A-z]","",unht)
unht3 <- Filter(function(x) {nchar(x) >= 2}, unht2)
ht_table <- table(unht3)

final <- sort(ht_table, decreasing = T)
hotel<-head(final,10)
hotel_df<-as.data.frame(hotel)
names(hotel_df)<-c("내용","빈도수")
hotel_df

```





### JavaRLab1

```java
package rjavaapp;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class JavaRLab1 {



	public static void main(String[] args) throws REXPMismatchException, REngineException {
		RConnection rc = new RConnection();
		
		rc.eval("library(KoNLP)"); 
		rc.eval("ht <- readLines(\"hotel.txt\")"); 
		rc.eval("ht2 <- sapply(ht, extractNoun, USE.NAMES = F)"); 
		rc.eval("unht <- unlist(ht2)"); 
		rc.eval("unht2<-gsub(\"[A-z]\",\"\",unht)"); 
		rc.eval("unht3 <- Filter(function(x) {nchar(x) >= 2}, unht2)"); 
		rc.eval("ht_table <- table(unht3)"); 
		rc.eval("final <- sort(ht_table, decreasing = T)"); 
		rc.eval("hotel<-head(final,10)"); 
		rc.eval("best10<-names(hotel)"); 
		
		REXP x = rc.eval("best10"); 
		String[] s =x.asStrings();
		System.out.print("R 이 보내온 최빈 명사들 : ");
		for(int i=0;i<x.length();i++) {
			if(i!=x.length())
				System.out.print(s[i]+", ");
			else
				System.out.println(s[i]);
		}
//		System.out.print(x.length());//10
//		System.out.print(s.length);//10

		rc.close();
	}
}

```

### JavaRLab2

```java
package rjavaapp;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class JavaRLab2 {


	public static void main(String[] args) throws REXPMismatchException, REngineException {
		RConnection rc = new RConnection();

		REXP x = rc.eval("data<-source('lab.R',encoding='UTF-8'); data$value");
		RList list = x.asList();

		String name[] = list.at("내용").asStrings();
		int count[] = list.at("빈도수").asIntegers();
		System.out.println("R 이 보내온 최빈 명사들 :");
		for(int i=0;i<name.length;i++)
			System.out.println(name[i]+"\t "+count[i]);

		rc.close();
	}
}

```

