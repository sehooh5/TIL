# Subquery

- #####  기본 구문

  ```sql
  select 컬럼명 or *
  
  from 테이블명
  
  where 컬럼 = 정해진 값, 이미 알고 있는 값
  ```

  

- ##### 서브쿼리

  where 절의 비교값을 모를 수 있을 때 사용

  ```sql
  select 컬럼명 or *
  from 테이블명
  where 컬럼 = (select 명령 = 서브쿼리)
  
  ex) ADMAS 의 월급보다 많이 받는 사람들의 정보
  select * 
  from emp
  where sal > (select sal from emp where ename = 'ADAMS');
  ```

  

- 만약 두가지 값이 있을때는 아래와 같이 사용

- ```sql
  >ALL, >ANY, <ALL, <ANY, IN, NOT IN, =ANY(IN과 같은 의미)
  
  select * 
  from emp
  where sal > (select sal from emp where ename = 'ADAMS' and 'JONES');
  ----사용 불가
  
  select * 
  from emp
  where sal > 
   	ALL(select sal from emp where ename = 'ADAMS' or ename = 'JONES');
   	ANY(select sal from emp where ename = 'ADAMS' or ename = 'JONES');
   		----ALL 은 둘 보다 크다! ANY 는 둘 중 한명보다 크다!
   		
  subquer의 where 절 <ANY (10,5,7,6) ----9,4,8 전부 다 됨
  				  <ALL (10,5,7,6) ----6보다 작은값
  ```

- select 절에 등장하는 컬럼들이 여러 테이블에서 가져올 때는 **조인**

  ​														한테이블로 충분하다? **서브쿼리**