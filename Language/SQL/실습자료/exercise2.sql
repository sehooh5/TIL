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

select count(comm), count(*) from emp;

