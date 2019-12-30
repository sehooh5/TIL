desc emp;
select* from emp;

select initcap(ename) as ����̸� from emp;

select ename ����̸�, substr(ename,2,4) "2-4����" from emp;

select length(ename) �̸����ڰ��� from emp;

select ename "��� �̸�", lower(substr(ename, 1,1)) "�ձ���", lower(substr(ename,-1)) "������ ����" from emp;

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

select to_char(trunc(sal*50,-2), '999,999,999')||'��' as ����� from emp;

select ename, 
case 
        when comm is null then '����'
        else '����'
        end as ��������
,comm
from emp;

select ename, deptno, 
decode(deptno
            ,10, 'A'
            ,20,'B'
            ,30,'C'
            ,'����'
            )   as �μ���
from emp;

select ename, sal 
from emp
where sal >= 1000
and job='SALESMAN';

select sysdate, sysdate+10 from dual;

select to_char ( sysdate, 'yyyy"��" mm"��" dd"��" hh"��" mi"��"') from dual;

select ename �̸�, sal ���޿�, (sal+200)*12 as ���� from emp;

select ename �̸�, sal ���޿�,
case
            when sal<1000 then 'A'
            when sal>1000 and sal <2000 then 'B'
            when sal>2000 and sal <3000 then 'C'
            when sal>3000 and sal <4000 then 'D'
            else 'E'
            end as �ڵ�
from emp;

select ename �̸�, deptno �μ���ȣ,
decode(deptno,
            10, 'A'
            ,20,'B'
            ,30,'C'
            ,40,'E'
            ,'F') as �ڵ�
from emp;

select ename
from emp
where ename like'_A%';

select ename
, round(months_between(sysdate, hiredate)) as "MONTHS WORKED"
from emp;

select ename ����̸�, trunc(sysdate-hiredate)||'��' from emp;

select ename �̸�, job ����, hiredate �Ի��� 
from emp
-- where to_char(hiredate, 'yyyy') = '1981'
where hiredate like '81%'
order by hiredate asc;

select to_char(to_date('19910208', 'yyyymmdd'),'day') "���� ����"from dual;

select trunc(MONTHS_BETWEEN
       (sysdate,
        TO_DATE('08-02-1991','MM-DD-YYYY') )) ������
  FROM DUAL;
  
  select ename, sal,
  NVL(to_char(comm), '����') as Ŀ�̼�
  from emp;
  
  select ename
    from emp
  where ename like 'A%' and ename not like '%N';
  where 
  
  select ename, hiredate
            ,to_char ( round(hiredate,'yyyy'))
from emp;

select count(comm), count(*) from emp;

