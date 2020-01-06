# Java Script (200106)

- html 을 보완하기 위해 만든 브라우저 내에서 사용하는 언어
- html 문서 안에 작성한다. 또한 확장자 .js 로 만들고 가지고 들어갈 수도 있다



### Java Script  코드 작성 방법

반드시 수행 문장 단위로 작성한다! (더 단순하지)



- #### 데이터 타입과 변수선언

  - 숫자, 문자열타입, 논리타입, 객체타입(배열타입), undefined 타입

    ```javascript
    var 변수명;
    var 변수명 = 초기값;
    ex) var v1; --> undefined 타입
    	v1 = 10; --> 숫자 타입
    	v1 = '10' or "10";  --> 문자열타입
    	v1 = false; --> 논리타입
    	-----------------------------------> typeof 연산자가 필요
    ```



- #### 연산자 (Java 와 거의 비슷)

  - ==,  ===,  !=,  !==,  &&,  ||,  delete,  typeof ... 



- #### 제어문

  - for, foreach, while, do while, break, continue 



- #### 함수의 정의와 활용***(R의 함수와 비슷!)



- #### 객체의 생성과 활용 (생성자 함수, 객체 리터럴)



- #### 예외처리

  - 가급적이면 html에서 예외없이 작성하는 것이 좋다!



- #### API

  - 표준 API : 함수, 생성자 함수

  - BOM (Browser Object Model) API : 

    - 브라우저가 자바스크립트에게 제공하는 API

    - 내장 객체 형식 ( 우리가 객체생성 할 필요 없고 바로 사용하면 된다 )

  - DOM (Document Object Model) API 

    - 객체 지향 모델로써 구조화된 문서를 표현하는 방식

  - HTML5 API : 

    - Canvas : 웹페이지의 시각화를 지원해주는 API 

    - WebStorage : 웹브라우저에 자료를 저장하기위한 기능

