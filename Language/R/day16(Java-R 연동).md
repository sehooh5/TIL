# day16(Java-R 연동)

- 새로운 Spring MVC 프로젝트 생성
- Java 에서 R 코드 생성 가능
- **RServe** 라는 프로그램이 필요하다 (R에서 기동시켜놔야 한다)
  - R binary server, Java 혹은 다른 언어에서 R 코드 연동할 때 서포트
  - R studio 기동 후 `install.packages("Rserve")`
  - RConnection 객체 생성
- **Java  에서 사용하는 방법**
  - `eval()`을 사용하여 R 코드 실행...REXP 객체 반환 후 결과값 확인 가능
  - `assign()` 또한 사용 가능 ..반환값 : REXP, String

- **REXP 타입** 
  - R 과 Java 에서 서로의 자료구조와 데이터 타입을 서로 사용할수 있도록 지원하는 데이터 모델형의 클래스
  - asBytes, asDouble, asDoubleMatrix, asDoubles, asList, asString, asStrings, length
- R 데이터프레임을 Java에서 사용 - **RList** : Java API
  - <u>`at` : Index 또는 변수명에 해당하는 열 데이터를 REXP 객체로 반환</u>
  - <u>`size` : 리스트의 개수를 알 수 있다</u>

- **Rserve 기동**

  (1) RStudio 에서 기동시키기 

  - Rserve(args="--RS-encoding utf8")

   (2) CMD 창에서 단독으로 기동시키기

  - C:\Users\student\Documents\R\win-library\3.6\Rserve\libs\x64(윈도우10)모든 파일을 

  - C:\Program Files\R\R-xxxx\bin\x64 디렉토리에 복사한 후에 

  - cmd 창을 띄우고 cd C:\Program Files\R\R-3.6.3\bin\x64 디렉토리에 가서 다음 명령을 수행시킨다. 

    ​	`Rserve --RS-encoding utf8`





### redu 

- Spring MVC project

- pom.xml 에 추가

  ```
  <dependency>
  			<groupId>com.github.lucarosellini.rJava</groupId>
  			<artifactId>JRIEngine</artifactId>
  			<version>0.9-7</version>
  		</dependency>
  		<dependency>
  			<groupId>net.rforge</groupId>
  			<artifactId>Rserve</artifactId>
  			<version>0.6-8.1</version>
  		</dependency>
  ```

- 우 클릭 후 properties 에서 UTF-8로 재설정

- package, file 복사붙여넣기 후

  - run - Java application 으로 실행

- RStudio 에서 실행

  ```r
  # R in JAVA
  
  install.packages("Rserve")
  library(Rserve)
  Rserve(args="--RS-encoding utf8")
  
  
  library(dplyr)
  pdf <- read.table("data/product_click.log")
  names(pdf) <- c("logdate", "product")
  pdf <- pdf %>% select(product) %>% group_by(product) %>% summarise(clickcount = n()) %>% arrange(desc(clickcount)) %>% head(1)
  pdf <- as.data.frame(pdf)
  pdf
  
  ```

- Java 에서 실행

  ```java
  package rjavaapp;
  
  import org.rosuda.REngine.REXP;
  import org.rosuda.REngine.REXPMismatchException;
  import org.rosuda.REngine.REngineException;
  import org.rosuda.REngine.RList;
  import org.rosuda.REngine.Rserve.RConnection;
  import org.rosuda.REngine.Rserve.RserveException;
  
  public class RServeExample {
  	// throws 절로 처리해줘야 한다
  	public static void getString() throws RserveException, REXPMismatchException {
  		RConnection rc = new RConnection();
  		String s = "가나다";
  		rc.assign("x", s); //Java 의 특정한 값을 R 에 주는 함수
  		rc.eval("y<- '" + s + "'"); //혹은 eval사용으로 R 대입연산자 사용
  		rc.eval("if(x == '가나다') print('XXX')");
  		rc.eval("if(y == '가나다') print('YYY')");
  		rc.eval("Encoding(x)<- 'UTF-8'"); //assign 할때는 encoding 해줘야함
  		rc.eval("y<-iconv(y, 'CP949', 'UTF-8')"); //eval 대입할때는 iconv 를 이용하여 컨버전해줘야함
  		REXP x = rc.eval("paste(R.version.string,x,y)"); //eval 의 결과를 REXP 변수에 담아야 한다(위에선 호출만)
  		System.out.println("R 버전 정보 : " + x.asString()); //문자열 이므로 REXP의 as.String() 으로 출력
  		rc.close();
  	}
  	
  	public static void getInteger() throws RserveException, REXPMismatchException {
  		RConnection rc = new RConnection();
  		REXP x = rc.eval("length(LETTERS)");
  		System.out.println("알파벳 갯수 : " + x.asInteger());
  		rc.close();
  	}
  
  	public static void getDoubles() throws RserveException, REXPMismatchException {
  		RConnection rc = new RConnection();
  		REXP x = rc.eval("rnorm(20)");//rnorm(20) 20개의 난수(double) 랜덤 수 추출
  		double[] d = x.asDoubles();
  		for (int i = 0; i < d.length; i++) {
  			System.out.println(d[i]);
  		}
  		rc.close();
  	}
  
  	public static void getIntegers() throws REngineException, REXPMismatchException {
  		RConnection rc = new RConnection();
  		int[] dataX = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
  		rc.assign("x", dataX);
  		rc.eval("y <- x + 10"); //각 변수마다 10씩 더해줌
  		int[] resultX = rc.eval("y").asIntegers();
  		for (int i = 0; i < resultX.length; i++) {
  			System.out.println(resultX[i]);
  		}
  		rc.close();
  	}
  	
  	// RList 사용
  	public static void getDataFrame1() throws RserveException, REXPMismatchException {
  		RConnection rc = new RConnection();
  		REXP x = rc.eval("d<-data.frame(LETTERS[11:20],c(11:20), stringsAsFactors=F)");
  		RList list = x.asList();
  //		REXP x = rc.eval("names(d) <- c('v1', 'v2')");
  		int v_size = list.size();
  		int d_length = list.at(0).length();
  		System.out.println(list);
  		System.out.println(list.at(0));
  		System.out.println(list.at(1));
  //		int d_length = list.at("v1").length();
  		System.out.println("데이터(관측치)의 갯수 : " + d_length);
  		System.out.println("변수의 갯수 : " + v_size);
  
  		int arrayRows = v_size;
  		int arrayCols = d_length;
  		// 데이터프레임의 변수 갯수로 행의 크기를 정한다.
  		//String 으로 맞춰줘야한다 #########이 아래부분 복습
  		String[][] s = new String[arrayRows][]; 
  
  		for (int i = 0; i < arrayRows; i++) {
  			s[i] = list.at(i).asStrings();
  		}
  
  		for (int i = 0; i < arrayRows; i++) {
  			for (int j = 0; j < arrayCols; j++) {
  				System.out.print(s[i][j] + "\t");
  			}
  			System.out.println();
  		}
  		rc.close();
  	}
  
  	public static void getDataFrame2() throws RserveException, REXPMismatchException {
  		RConnection rc = new RConnection();
  		//R 코드 불러와서 실행하는 방법
  		//imsi$value = data 값 읽어올때 사용
  		REXP x = rc.eval("imsi<-source('rjavatest.R'); imsi$value");
  		RList list = x.asList();
  		
  		String pid = list.at("product").asString();
  		System.out.print("PID : " + pid);
  
  		int clickcount = list.at("clickcount").asInteger();
  //		String clickcount = list.at("clickcount").asString();
  
  		System.out.println("\tCLICKCOUNT : " + clickcount);
  		rc.close();
  	}
  
  	public static void main(String[] args) throws REXPMismatchException, REngineException {
  		System.out.println("------------ R에서 버젼정보 가져오기 --------------");
  		RServeExample.getString();
  		System.out.println("------------ R에서 정수 데이터 가져오기 --------------");
  		RServeExample.getInteger();
  		System.out.println("------------ R에서 더블 데이터들 가져오기 -------------");
  		RServeExample.getDoubles();
  		System.out.println("------------  R에서 데이터 주입 연산후 가져오기 ------");
  		RServeExample.getIntegers();
  		System.out.println("------------  R에서 데이터 생성(데이터 프레임) 연산후 가져오기------");
  		RServeExample.getDataFrame1();
  		System.out.println("------------ R에서 데이터 프레임 가져오기 --------------");
  		RServeExample.getDataFrame2();
  
  	}
  
  }
  
  ```

  