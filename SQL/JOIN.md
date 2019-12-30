# JOIN

## 오라클 조인

- 조인은 가로연결, 집합연산자는 세로연결

- 조인 연결 조건

  ```sql
  --기본 조인(크로스 조인)
  select * from emp, dept (연결할 테이블들 나열...)
             where emp.deptno = dept.deptno; (where로 조건식 주기)
  
  --left outer
  select * from emp, dept 
             where emp.deptno = dept.deptno(+); ((+)로 null행 추가해주기)
             
  --right outer           
  select * from emp, dept 
             where emp.deptno(+) = dept.deptno; ((+)로 null행 추가해주기)
             
  --양쪽에 (+)는 지원하지 않음
  select * from emp, dept 
             where emp.deptno(+) = dept.deptno(+); --(X, 불가)
  			
  --양쪽 전부 다 뽑아내기
  select * from emp, dept 
             where emp.deptno = dept.deptno(+)		
  union
  select * from emp, dept 
             where emp.deptno(+) = dept.deptno;
  
  --별칭주기
  select * from emp E, dept D
  			where E.deptno = D.deptno(+); 
  
  --조건식을 다양하게 줄수 있다.
  select emp.empno,emp.ename,emp.sal,salgrade.grade
  from emp, salgrade
  where emp.sal between salgrade.losal and salgrade.hisal;
  ```

- 셀프조인

  ```sql
  select e1.ename, e2.ename
  from emp e1, emp e2
  where e1.mgr = e2.empno;
  
  --셀프 조인 ////여기서 KING 뽑아내려면 어떻게? nvl과 (+)를 매니저 테이블에 줌 
  select e.ename 이름 , nvl(m.ename, '회사대빵') 매니저
  from emp e, emp m
  where e.mgr = m.empno(+);
  ```

  

## NC 조인

```sql
[방법1] : 조인에 사용할 컬럼명이 같을때
select
from 테이블1 join 테이블2 using (조인에 사용할 컬럼명)
where 행에 대한 조건

[방법2] : 조인에 사용할 컬럼명들이 다를때
select
from 테이블1 join 테이블2 on (조인 조건식)
where 행에 대한 조건
---크로스 조인, 내츄럴 조인 때는 using, on 사용하지 않아도 된다

[방법3] : 아우터조인 left
select
from 테이블1 left join 테이블2 using (조인에 사용할 컬럼명) 또는 on (조인 조건식)
where 행에 대한 조건 ----왼쪽 테이블이 기준으로 다 내보낸다

[방법4] : 아우터조인 right
select
from 테이블1 right join 테이블2 using (조인에 사용할 컬럼명) 또는 on (조인 조건식)
where 행에 대한 조건  ----오른쪽 테이블이 기준으로 다 내보낸다

[방법5] : 아우터조인 full
select
from 테이블1 full join 테이블2 using (조인에 사용할 컬럼명) 또는 on (조인 조건식)
where 행에 대한 조건  ----양쪽 테이블 다 내보낸다

[내츄럴 조인]
조인 대상이 되는 두 테이블에 이름과 자료형이 같은 열을 찾아 그 열을 기준으로 등가조인
(단, 등가조인만 가능하다)
select * 
from 테이블1 natural join 테이블2

```

