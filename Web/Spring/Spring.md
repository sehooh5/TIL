# Spring

[TOC]

### Spring  Framework

- Framework : 반 제품 형태의 Soft Ware = 개발 생산성과 유지보수성이 높다
- **XML** 과 **에노테이션** 활용으로 무엇을 작용하는지 알린다..지금은 에노테이션



### Spring IoC

---

#### DL (Dependency Lookup)



#### DI (Dependency Injection) - 빈도수 높고 기본이다

- Setter Injection : 그때그때 받을때 
- Constructor Injection : 객체 생성 시 받을 때



#### 사용법

##### Spring DI 컨테이너 초기화

```java
ApplicationContext context 
    		= new ClassPathXmlApplicationContext("빈 설정 파일");
```

#####  DL의 예

```java
타입명 bean =(타입명)context.getBean("빈이름"); ---> **id 값이 빈 이름!!
```

#####  DI의 예

```
1. Construction Injection : 생성자를 통해서 객체 바인딩(의존관계 연결)
2. Setter Injection : setter 메서드를 이용해서 객체 바인딩(의존관계 연결)
```



#### 설치 순서

- Eclipse EE = Exlipse for Java Developer + WTP(플러그인 : plugin) //기본

  ​						+**STS**를 추가로 설치(Spring 실행기 위해 서포트해주는 plugin)

  - Help - EclipseMarketPlace - sts검색

  - New - Other - SpringLegacy - name : springioc - Templet : simplejava

  - springioc 우클릭 - Configure - Convert Maven Project

  - description 과 build 태그 사이에 이 구문 넣어주기

    ```xml
    <dependencies>
      <!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.0.2.RELEASE</version>
    </dependency>
    </dependencies>
    ```

    

- 브라우저가 렌더링 할 수 있는 문서를 확장해주는게 "플러그인" ex) pdf on web



#### 공부 순서

1. Spring IoC
   - Java Application 사용
   - Project Title : SpringIoC
2. Spring MVC 
   - Web 기반 
3. Mybatis
   - Web 기반





### autowire : 조건에 따라 자동으로 객체와 결합해줌

---

- 나중에 **에노테이션**으로 사용할 수 있다

#### byName : setter

1. 프로퍼티명과 동일한 명칭의 빈을 찾아서 해당 객체 주입
2. 없으면 null 주입 = 객체 주입 포기



#### byType : setter

1. 타입으로 찾아서 1개면 해당객체 주입
2. 타입으로 찾아서 2개 이상이면 NoUniqueBeanDefinitionException 발생
3. 없으면 null 주입 = 객체 주입 포기



#### constructor : constructor

1. 타입으로 찾아서 1개이면 해당 객체 주입
2. 타입으로 찾아서 2개 이상이면 매개변수명과 동일한 id 값을 갖는 객체 주입
3. 없으면 null 주입 = 객체 주입 포기



---



### @component : class 위에 사용

- 해당 패키지를 다 읽어서 빈객체를 만들어주는 기능

- autowired 된 애들은 빈객체 만들어서 사용

- ```xml
  <context:component-scan base-package="패키지명"/>
  ```

