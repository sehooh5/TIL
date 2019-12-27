# SQL 함수 실습

```
--1. 모든 테이블의 리스트를 출력하시오.
select * from tab;
--2. emp 테이블과 dept 테이블의 구조를 출력하시오.
desc emp;
desc dept;
--3. 사원(emp)테이블에서 모든 데이터를 출력하시오.
select * from emp;
--4. 사원테이블에서 사원번호, 사원이름, 월급을 출력하시오.
select empno, ename, sal from emp;
--5. 사원테이블에서 월급열만 뽑는데 중복된 데이터가 없게 출력하시오.
select distinct sal from emp;
--6. 사원테이블에서 사원이름과 월급을 출력하는데 각각의 컬럼명을
-- "이 름","월 급"으로 바꿔서 출력하시오. 단, ALIAS에 공백 추가
select ename "이 름", sal "월 급" from emp;
--7. 사원테이블에서 사원이름, 월급을 뽑고, 월급과 커미션을  더한 값을
-- 출력하는데 컬럼명을 '실급여'이라고 해서 출력하시오.
-- 단, NULL값은 나타나지 않게 하시오.(커미션 안받는사람제외)
select ename, sal, sal+comm 실급여
from emp
where comm is not null ;
--8. 사원테이블에서 'FORD'이라는 사원의 사원번호, 이름, 월급을 출력하시오.
select empno, ename, sal
from emp
where ename = 'FORD';
--9. 사원테이블에서 직위가 'SALESMAN'인 사원의 사원번호, 이름, 직위를
-- 출력하시오.
select empno, ename, job
from emp
where job  = 'SALESMAN';
--10. 사원테이블에서 사원번호가 7499, 7521, 7654인 사원의 사원번호, 이름
-- 월급을 출력하시오.
select empno, ename, sal
from emp
where empno in  (7499,7521,7654);
--11. 사원테이블에서 월급이 1500에서 3000사이인 사원의 사원번호, 이름,
-- 월급을 출력하시오.
select empno, ename, sal
from emp
where sal between 1500 and 3000; 
--12. 사원 테이블에서 매니저가 없는 직원의 이름
select ename
from emp
where mgr is null;
--13. 사원테이블에서 월급이 많은 순으로 모든 정보를 출력하시오.
select * from emp
order by sal desc ;
--14. 사원테이블에서 직원의 이름과 입사일을 출력하는데 최근에 입사한 순으로 출력하시오.
select ename, hiredate from emp
order by hiredate desc;
--15. 사원 테이블에서 30번 부서에 근무하는 직원들의 이름을 출력하는데
-- 입사한지 오래된 순으로 출력하시오.
select ename from emp
where deptno = 30
order by hiredate asc;

select ename, length(ename) from emp;

select substr(ename,1,2) "1-2", substr(ename,3,5) from emp;

select instr ( 'Hello, oracle!', 'l',2,2) as instr from dual;

select * from emp
where instr(job, 'S')>0;

SELECT TO_CHAR(SYSDATE, 'WW') FROM DUAL;
```



```
desc emp;
select* from emp;

select initcap(ename) as 사원이름 from emp;

select ename 사원이름, substr(ename,2,4) "2-4문자" from emp;

select length(ename) 이름문자개수 from emp;

select ename "사원 이름", lower(substr(ename, 1,1)) "앞글자", lower(substr(ename,-1)) "마지막 글자" from emp;

select lower(substr(ename,1,1))||' '||
lower(
    case 
    when length(ename)>=10 then substr(ename,10) 
    when length(ename)>=9 then substr(ename,9) 
     when length(ename)>=8 then substr(ename,8) 
      when length(ename)>=7 then substr(ename,7) 
       when length(ename)>=6 then substr(ename,6) 
        when length(ename)>=5 then substr(ename,5) 
         when length(ename)>=4 then substr(ename,4) 
          when length(ename)>=3 then substr(ename,3)
          end
    ) 
from emp;

select round(3456.78,1) from dual;

select to_char(trunc(sal*50,-2), '999,999,999')||'원' as 계산결과 from emp;

select ename, 
case 
        when comm is null then '미정'
        else '설정'
        end as 설정여부
,comm
from emp;

select ename, deptno, 
decode(deptno
            ,10, 'A'
            ,20,'B'
            ,30,'C'
            ,'없당'
            )   as 부서명
from emp;

select ename, sal 
from emp
where sal >= 1000
and job='SALESMAN';

select sysdate, sysdate+10 from dual;

select to_char ( sysdate, 'yyyy"년" mm"월" dd"일" hh"시" mi"분"') from dual;

select ename 이름, sal 월급여, (sal+200)*12 as 연봉 from emp;

select ename 이름, sal 월급여,
case
            when sal<1000 then 'A'
            when sal>1000 and sal <2000 then 'B'
            when sal>2000 and sal <3000 then 'C'
            when sal>3000 and sal <4000 then 'D'
            else 'E'
            end as 코드
from emp;

select ename 이름, deptno 부서번호,
decode(deptno,
            10, 'A'
            ,20,'B'
            ,30,'C'
            ,40,'E'
            ,'F') as 코드
from emp;

select ename
from emp
where ename like'_A%';

select ename
, round(months_between(sysdate, hiredate)) as "MONTHS WORKED"
from emp;

select ename 사원이름, trunc(sysdate-hiredate)||'일' from emp;

select ename 이름, job 직위, hiredate 입사일 
from emp
-- where to_char(hiredate, 'yyyy') = '1981'
where hiredate like '81%'
order by hiredate asc;

select to_char(to_date('19910208', 'yyyymmdd'),'day') "생일 요일"from dual;

select trunc(MONTHS_BETWEEN
       (sysdate,
        TO_DATE('08-02-1991','MM-DD-YYYY') )) 개월수
  FROM DUAL;
  
  select ename, sal,
  NVL(to_char(comm), '미정') as 커미션
  from emp;
  
  select ename
    from emp
  where ename like 'A%' and ename not like '%N';
  where 
  
  select ename, hiredate
            ,to_char ( round(hiredate,'yyyy'))
from emp;
```

```

```

--제출파일명 : exercise3.sql
-- scott_create_table.sql 을 복사하여 scott 계정 영역에서 수행시키고 
-- course1 과 couse2 테이블을 생성한 후에 진행한다.
desc course1;
-- 1. 집합연산자 실습 

-- 1.1 course1 과 course2 를 모두 수강하는 학생들의 이름, 전화 번호 그리고 
--    나이를 출력하시오.
  select name,phone,age
  from course1
  intersect
  select name,phone,age
  from course2; 
-- 1.2 course1 이든 course2 든 수강하는 학생들의 이름, 전화 번호 그리고 
--    나이를 출력하는데 나이가 많은 순으로 출력하시오.
 select name,phone,age
  from course1
  union
   select name,phone,age
  from course2
  order by age desc;
-- 1.3 course1은 수강하지만 course2 는 수강하지 않는 학생들의 이름을 출력하시오.
 select name,phone,age
  from course1
  minus
   select name,phone,age
  from course2;
-- 1.4 course2는 수강하지만 course1 은 수강하지 않는 학생들의 이름과 전화번호를 
--    이름 순으로 출력하시오.
 select name,phone,age
  from course2
  minus
   select name,phone,age
  from course1;
--2. 모든 직원들 월급의 평균을 구하시오. 
 --      (소수점 이하 둘째자리까지만 나타내고 셋째자리 부터는 절삭하시오)

	--월급 평균
--            -----------
	--   x
select trunc(avg(sal),2)
from emp;
--3. 모든 직원들이 받는 커미션의 합을 구하시오.
--	커미션 합
               -----------
--	  x
select sum(comm)
from emp;



--4. 각 직급별로 총월급을 출력하되 총월급이 낮은 순으로 출력하시오.

--	직급명	총월급
	----------------------------
--	xxxx          xxx
--	 :	   :
select job 직급명, sum(sal) 총월급
from emp
group by job
order by sum(sal) ;

--5. 각 부서에서 근무하는 직원들의 명수를 알고싶다. 다음 형식으로 출력하는 SQL
--    을 작성하시오 .

--	부서정보	   직원명수
--	----------------------------
--	미정          1명
--	30번 부서   6명
--	20번 부서   4명
--	10번 부서   3명
select 
nvl2(to_char(deptno),to_char(deptno)||'번 부서','미정') 부서정보
, count(deptno)
from emp                                         
group by deptno
order by deptno desc;

--6. 년도별로 몇명이 입사했는지 알고싶다. 다음 형식으로 출력하는 SQL
--    을 작성하시오 . (많이 입사한 순으로 출력)

--	입사년도 	   입사 인원수
	----------------------------
--	1981년	   10명
--	1987년	   2명
--	1982년	   1명
--	1980년	   1명
select 
to_char(hiredate,'yyyy"년"')
,count(*) "입사 인원수"
from emp
group by to_char(hiredate,'yyyy"년"')
order by count(*) desc;



--7. 전체 직원들의 숫자를 부서정보를 다음 형식으로 출력하는 SQL
--    을 작성하시오 .
--
--	2019년 12월 27일 기준으로 14명이 근무중입니다.
select sysdate 기준일, count(*) "현재 근무 인원" 
from emp;

--8. 직급별 총월급을 출력하되, 직급이 'MANAGER'인 직원들은 제외하시오. 
--   그리고 그 총월급이 5000보다 큰 직급와 총월급만 출력하시오.

--	직급명	총월급
----------------------------
--	xxxx          xxx
--	 :	   :
select job 직급명, sum(sal)
from emp
where job != 'MANAGER'
and (select sum(sal ) from emp)>5000
group by job;
--9. 30번 부서의 업무별 년봉의 평균을 검색한다.
-- 연봉계산은 월급+커미션(null이면 0으로 계산)이며 
-- 출력 양식은 소수점 이하 두 자리(반올림)까지 통일된 양식으로 출력한다.
--              업무명       평균월급
              ---------------------------
  --            xxxx         xxx.xx
--	 :	    :
select 
job 업무명, 
round(avg(sal+nvl(comm,0)), 2) 평균월급
from emp
group by job;
--10. 월별 입사인원을 다음 형식으로 출력하는 SQL 을 작성하시오 . 
--     입사월별로 오름차순이며 입사인원이 2명 이상인 경우에만 출력한다.
--	02월	2명
--	04월	2명
--	05월	2명
--	09월	2명
--	12월	3명
select to_char(hiredate,'mm"월"') 입사월, 
count(*)||'명' 입사인원
from emp
group by to_char(hiredate,'mm"월"')
having  count(*) >= 2
order by to_char(hiredate,'mm"월"') ;