# Spring MVC

- springedu 프로젝트 생성 : 

  Spring Legacy - Spring MVC Project - my.spring.springedu 입력

- Tomcat 에 연동시키기

- client html 모두 **src - main - webapp - resource** 에 보관해야한다



### 특징

---

- Controller 가 두개다  : Front controller / Controller

- **Front Controller** : 

  - **Dispatcher Servlet** 클래스, 자동으로 생성해줌
  - 브라우저로부터 받은 요청은 Dispatcher Servlet 클래스가 모두 관리한다

- View Resolver 또한 자동으로 생성해준다

- 우리는 Controller Class 와 JSP 만 만들면 된다

  (Controller 는 게시판인지 알려주는 클래스)

- 모든 요청을 적용할때

  - `<url pattern>/*</url pattern>`

![image-20200207145327734](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200207145327734.png)



### 저장소

---

#### Controller

- Java Resource - src/main/java - my.spring.springedu



#### VO

- Java Resource - src/main/java - vo



#### View

- src - main - web app - WEB-INF - view



#### Resource

- src - main -  web app - resource



---





- POJO : Plain Old Java Object = 순수한 자바객체, 아무거나 상속가능



---



#### 컨트롤러 메서드의 파라미터 타입

![image-20200207161659252](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200207161659252.png)



#### 컨트롤러 메서드의 리턴 타입

![image-20200207161801068](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200207161801068.png)