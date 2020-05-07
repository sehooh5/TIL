# Spark

### 환경설정

- 클러스터 컴퓨터를 기존 4대에서 2대로 줄여준다

- `hadoop` 의 홈디렉토리에 slaves 를 편집한다(**데이터노드 정보**)

  ```
  cd $HADOOP_HOME/etc/hadoop
  vi slaves
  ```

- `hdfs-site.xml` 의 `replication`개수 수정(**복제** 개수)

  ```
  vi hdfs-site.xml
  
  :set number (번호주기)
  :22 (22 번째 줄로 이동)
  ```

- `HDFS`데몬 기동

  ```
  start-dfs.sh
  ```

- `spark.apache.org` 에서 해당 `spark`를 다운로드 한다

- 파일은 `.tgz` 인데 리눅스 압축 프로그램이므로 `반디집`을 이용하여 압축 해제해준다

- `spark-2.4.5-bin-hadoop2.7` 폴더를 `c://` 바로 밑에 저장

- 환경 변수 추가

  ```
  SPARK_HOME	c:\spark-2.4.5-bin-hadoop2.7
  PATH		%SPARK_HOME%\bin;%SPARK_HOME%\sbin
  ```

- `SPARK_HOME`의 `conf` 디렉토리에 `log4j` 설정파일을 만들고(`log4j.properties.template`파일을 `log4j.properties`로 복사) 로그 레벨을 `INFO` 에서 `WARN`으로 변경한다



### cmd 에서 실행 

- `cmd`에서 `spark-shell`명령으로 실행

- ```
  val textFile = sc.textFile("hdfs://192.168.111.120:9000/edudata/president_moon.txt")
  //var - 일반변수
  //val - final 형
  ```

  텍스트 파일을 읽어와서 `textFile`이라는 변수에 담앗다

  (**RDD 객체!!! 중요**)

- ```
  val counts = textFile.flatMap(line=>line.split(" ")).map(word=>(word,10)).reduceByKey(_+_)
  
  // . 마다 RDD객채가 생김!!!!(중요!!)
  //(word,1) - 괄호 : key Value의 `튜플객체` 내보냄
  //reduceByKey(_+_) - key Value 로 다 합쳐줌
  ```

  `RDD`객체가 `.`**마다** 생성됨

  (word,1) 해주면 key value 쌍의 **튜플객체** 생성해줌(=파이썬)

- ```
  count.collect()
  ```

  Array객체로 만들어서 출력해준다 = **action** = 이때 RDD들 실행

- 

