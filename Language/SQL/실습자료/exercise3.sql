--�������ϸ� : exercise3.sql
-- scott_create_table.sql �� �����Ͽ� scott ���� �������� �����Ű�� 
-- course1 �� couse2 ���̺��� ������ �Ŀ� �����Ѵ�.
desc course1;
-- 1. ���տ����� �ǽ� 

-- 1.1 course1 �� course2 �� ��� �����ϴ� �л����� �̸�, ��ȭ ��ȣ �׸��� 
--    ���̸� ����Ͻÿ�.
  select name,phone,age
  from course1
  intersect
  select name,phone,age
  from course2; 
-- 1.2 course1 �̵� course2 �� �����ϴ� �л����� �̸�, ��ȭ ��ȣ �׸��� 
--    ���̸� ����ϴµ� ���̰� ���� ������ ����Ͻÿ�.
 select name,phone,age
  from course1
  union
   select name,phone,age
  from course2
  order by age desc;
-- 1.3 course1�� ���������� course2 �� �������� �ʴ� �л����� �̸��� ����Ͻÿ�.
 select name,phone,age
  from course1
  minus
   select name,phone,age
  from course2;
-- 1.4 course2�� ���������� course1 �� �������� �ʴ� �л����� �̸��� ��ȭ��ȣ�� 
--    �̸� ������ ����Ͻÿ�.
 select name,phone,age
  from course2
  minus
   select name,phone,age
  from course1;
--2. ��� ������ ������ ����� ���Ͻÿ�. 
 --      (�Ҽ��� ���� ��°�ڸ������� ��Ÿ���� ��°�ڸ� ���ʹ� �����Ͻÿ�)

	--���� ���
   --            -----------
	--   x
select trunc(avg(sal),2) "���� ���"
from emp;
--3. ��� �������� �޴� Ŀ�̼��� ���� ���Ͻÿ�.
--	Ŀ�̼� ��
               -----------
--	  x
select sum(comm) "Ŀ�̼� ��"
from emp;
--4. �� ���޺��� �ѿ����� ����ϵ� �ѿ����� ���� ������ ����Ͻÿ�.

--	���޸�	�ѿ���
	----------------------------
--	xxxx          xxx
--	 :	   :
select job ���޸�, sum(sal) �ѿ���
from emp
group by job
order by sum(sal) ;

--5. �� �μ����� �ٹ��ϴ� �������� ����� �˰�ʹ�. ���� �������� ����ϴ� SQL
--    �� �ۼ��Ͻÿ� .

--	�μ�����	   �������
--	----------------------------
--	����          1��
--	30�� �μ�   6��
--	20�� �μ�   4��
--	10�� �μ�   3��
select 
nvl2(to_char(deptno),to_char(deptno)||'�� �μ�','����') �μ�����
, count(*) �������
from emp                                         
group by deptno
order by deptno desc;

--6. �⵵���� ����� �Ի��ߴ��� �˰�ʹ�. ���� �������� ����ϴ� SQL
--    �� �ۼ��Ͻÿ� . (���� �Ի��� ������ ���)

--	�Ի�⵵ 	   �Ի� �ο���
	----------------------------
--	1981��	   10��
--	1987��	   2��
--	1982��	   1��
--	1980��	   1��
select 
to_char(hiredate,'yyyy"��"') �Ի�⵵
,count(*) "�Ի� �ο���"
from emp
group by to_char(hiredate,'yyyy"��"')
order by count(*) desc;



--7. ��ü �������� ���ڸ� �μ������� ���� �������� ����ϴ� SQL
--    �� �ۼ��Ͻÿ� .
--
--	2019�� 12�� 27�� �������� 14���� �ٹ����Դϴ�.
select sysdate ������, count(*) "���� �ٹ� �ο�" 
from emp;

--8. ���޺� �ѿ����� ����ϵ�, ������ 'MANAGER'�� �������� �����Ͻÿ�. 
--   �׸��� �� �ѿ����� 5000���� ū ���޿� �ѿ��޸� ����Ͻÿ�.

--	���޸�	�ѿ���
	----------------------------
--	xxxx          xxx
--	 :	   :
select job ���޸�, sum(sal) �ѿ���
from emp
where job != 'MANAGER'
and (select sum(sal ) from emp)>5000
group by job;
--9. 30�� �μ��� ������ ����� ����� �˻��Ѵ�.
-- ��������� ����+Ŀ�̼�(null�̸� 0���� ���)�̸� 
-- ��� ����� �Ҽ��� ���� �� �ڸ�(�ݿø�)���� ���ϵ� ������� ����Ѵ�.
--              ������       ��տ���
              ---------------------------
  --            xxxx         xxx.xx
--	 :	    :
select 
job ������, 
round(avg(sal+nvl(comm,0)), 2) ��տ���
from emp
group by job;
--10. ���� �Ի��ο��� ���� �������� ����ϴ� SQL �� �ۼ��Ͻÿ� . 
--     �Ի������ ���������̸� �Ի��ο��� 2�� �̻��� ��쿡�� ����Ѵ�.
--	02��	2��
--	04��	2��
--	05��	2��
--	09��	2��
--	12��	3��
select to_char(hiredate,'mm"��"') �Ի��, 
count(*)||'��' �Ի��ο�
from emp
group by to_char(hiredate,'mm"��"')
having  count(*) >= 2
order by to_char(hiredate,'mm"��"') ;
