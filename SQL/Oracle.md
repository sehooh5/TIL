Java 구문 - SQL - JDBC(API 자바 데이터베이스 커넥티비티) - HTML5 - CSS3(웹 퍼블리셔) - JavaScript(jquery) - Ajax - (Servlet - JSP 자바의 웹서버 기술) - Spring(Servlet과 JSP로 개발할때 생산성을 확 띄워주는 API) - Mybatis.(JDBC의 코딩을 줄여주는 것) - 관공서 개발에 mybatis와 spring 필수    -D3.js(웹 기반 시각화)  --> 1day 게시판 프로젝트 - 미니 프로젝트

-------------------------------------------------------------웹서버 프로그래밍 기술-------------------------------------------------------------

빅데이터 

리눅스(Centos7) - Hadoop 2.x.x(설치, 설정, HDFS(빅데이터 파일 저장시스템), MapReduce(빅데이터 처리 시스템)) - Hive(맵리듀스 프로그램을 쉽게 해주는것.) - Spark(요즘 가장 각광받음. 속도가 제일 빠름.) 

-R(데이터 마이닝 언어.. 구문 웹크롤링을 이용한 데이터 수집, 공공DB, SNS Open API를 이용하여 데이터 수집 방법 배움, 통계분석, 데이터마이닝, 텍스트마이닝(댓글 분석을 통한 트랜드 분석, 감정 분석)) - Java와 R 연동

팀 프로젝트 : 빅데이터를 가지고 분석하여 웹으로 서비스하는 모델을  



### CRUD ex)게시판, SQL

Create : 입력

Read : 읽기

Update : 수정

Delete : 삭제



#### 서버와 클라이언트

DQL ----> DML -----> DDL

1. cmd 창에 들어가서 sqlplus라는 명령을 수행시킨다.
2. sqldeveloper 라는 추가 프로그램을 설치하여 사용한다 - UI 사용할 수 있음

**학습용 일반계정 : scott, hr(락해제)**

3. scott 계정 생성

   @ C:\oraclexe\app\oracle\product\11.2.0\server\rdbms\admin\scott.sql

4. 계정 비번 설정

   alter user scott identified by tiger : tiger 비번

5.  scott 계정의 테이블 리스트 확인

   select user from dual;  // scott 이라고 출력됨

   select * from tab;   	// scott 계정이 보유하고 있는 테이블 리스트

6. manager 로 복귀

   connect system/manager

7. hr 계정 락 풀기

   alter user hr account unlock;

   alter user hr identified by hr;

   connect hr/hr

8. jdbctest 계정 만들기 : JDBC 수업시간에 사용

   create user jdbctest identified by jdbctest;

   grant connect, resource to jdbctest;



#### 명령어

- desc(describe)  + 테이블: table 의 유형을 보여주는 명령어
- select * from + 테이블; 모든 테이블 정보들 보여줌



#### 년도계산

- rr/mm/dd : 0~49 현재 세기, 50~99 이전 세기

- yy/mm/dd : 현재 세기를 붙임 (ex 20xx)