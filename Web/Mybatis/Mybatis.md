# Mybatis

- JDBC 를 도와주는 Framework (즉, DB 연동 프레임 워크)

- SQL문이 어플리케이션 소스코드로부터 완전 분리.... XML으로 분류시켜준다

  (소스코드에 SQL 명령문이 있으면 보안적으로 취약한데 그런 부분을 분류시켜 보안성도 높아진다)

- 따로 설치해줘야한다 : 

  1. maven에게 명령   2. mybatis library 를 lib 디렉토리에 넣어주면 된다

  ![image-20200212161431935](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200212161431935.png)

![image-20200212162533807](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200212162533807.png)

- 위 configuration 을 입력해주어야 한다

  - POOLED 방법으로 Connection Pool 을 미리 만들어놓고 하나씩 사용한다..따라서 실질적 DB 연결 수는 줄어든다
  - JDBC 접속도 알아서 해준다
  - mapper 태그 : src 폴더 기준으로 하고 프로그램마다 나누어도되고 같이 사용해도 된다.

- ```java
  <Spring 사용 안하면 SQLSession  객체 만드는 방법>
      
  String resource = "resource/mybatis-config.xml";
  InputStream inputStream = Resources.getResourceAsStream(resource);
  SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
  SqlSession session = sqlSessionFactory.openSession();
  ```

  ![image-20200212163303853](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200212163303853.png)

- 사용방법

  ![image-20200212163629235](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200212163629235.png)

- 주요 메서드

  (1) select

  ![image-20200212163831430](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200212163831430.png)

  	* 한개, List, Map 형식으로 나눠서 select 할 수 있다.

  

  (2) insert, update, delete

  ![image-20200212163856444](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200212163856444.png)



- 예시

![image-20200212164735520](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200212164735520.png)

1. `<mapper>` 태그로 반드시 묶어주고 `namespace`설정
2. id 값은 반드시 유니크할 것!
3. id 값 줄때는 반드시 `namespace+id` !! (= resource.VisitorMapper.selectVisitor)
4. insert 는 result type 을 꼭 줘야한다
   - preparedStatement 처럼 동적 파라미터 사용할 때 지정한 parameterType 객체의 getName 을 전달하는 : **#{name}**
5. < ![**CDATA**[**<**]] >5 : 중간 **[<]** 연산자 쓰기 위하여 CDATA session 사용
6. **like 연산자** : '%'||#{key}||'%'  ---> 여기서 key 값은 선택된 파라미터형 값 

![image-20200213104200730](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200213104200730.png)

7. insert 에 id 값을 주고 싶으면 **< select Key >** 태그를 사용한다!!

   order="before"는 insert 실행 전에 < select Key > 먼저 실행하라는 수행문

   즉 id 값을 먼저 생성해서 insert 할 데이터에 사용한다



