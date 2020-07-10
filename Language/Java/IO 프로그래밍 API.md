###  I/O 프로그래밍 API

#### java.io, javax.nio

---

- ##### 문자셋(charset)

  - ASCII - 문자셋 이름.  0x00~0x7f(1byte)  =>  0x41~영문 대문자, 0x61~영문 소문자

  - 한글코드

    ~1986년 : 표준화된 한글 코드가 없었다.

    ​				  컴퓨터마다 한글 코드가 달랐다.

    ~1987년 : 표준화된 한글 코드 : 완성형 한글 코드 - KSC5601 (EUC-KR, MS949(CP949))

    ​													  영문(1byte:ASCII), 한글(0xBA1~가,2byte)

      1990년 : 전세계의 나라언어 코드를 통일하자 

    ​				  unicode 발표(2byte) : utf-16, utf16  ==> A : 0x0041, 가 : 0xAC00

    ​									 (1~4byte) : utf-8, utf8 (웹 표준)  ==> A : 0x41 , 가 : 0xEAB080

    OS - MS949

    Java - utf16		==> 자바는 MS949 와 변환을 자동으로 해준다

    Web - utf8

---

- 예외처리는 필수이다 Exception 또한 close 또한 필수이다.

- **File** : 시스템에 존재하는 파일에 대한 처리, 정보 추출 해주는 메서드를 정의하는 클래스

- 입력용 API와 출력용 API가 나눠져있다.

- 입력 단위 : 바이트 단위(v1.0), 문자 단위(v1.1)

- 바이트 단위를 문자단위로 바꿔줘야하는 경우가 있다. ex) InputStreamReader 사용

- 스트림이라는 논리적인 구조 이용한다.

  이 또한, 입력 스트림과 출력 스트림으로 구분한다.

  - xxxInputStream, xxxOutputStream : 바이트용 스트림

  - xxxReader, xxxWriter : 문자용 스트림

1. ##### 문자스트림

   - FileReader, FileWriter - 파일 오픈 기능
   - BufferedReader, BufferedWriter - 한꺼번에 실행가능하게 함

2. ##### 바이트스트림

   - FileInputStream, FileOutputStream - 파일 오픈 기능

   - DataInputStream, DataOutputStream - 

     ObjectInputStream, ObjectOutputSteam 

3.  InputStreamReader, OutputStreamWriter - 바이트를 문자로 읽어줄때 사용

