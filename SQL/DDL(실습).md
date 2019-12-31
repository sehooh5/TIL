# DDL(실습)

```sql
select * from visitor2;

create table visitor2 (
    name varchar2(18)
    ,writedate date
    ,memo varchar2(180)
);
insert into visitor2
    values ('천재' , sysdate , '나는야천재');
    
insert into visitor2
    values ('바보' , sysdate , '나는야바보');
    
update visitor2	--틀려서 업데이트
set writedate=to_date('171225','rrmmdd')
where name = '바보';

insert into visitor2
    values ('홍홍' , sysdate , '나는야홍홍');
    
update visitor2
set writedate = to_date('990811','rrmmdd')
where name = '홍홍';

commit;--commit 꼭 해야 연동이 가능하다

create table member(
    m_id    VARCHAR2(12) primary key
    ,m_pwd VARCHAR2(12) not null
    ,name VARCHAR2(18) not null
   , joindate date default sysdate
);

create table news(
    id number(8) primary key
    ,writer VARCHAR2(18)
    ,title VARCHAR2(120)
    ,content VARCHAR2(900)
    ,writedate date
    ,cnt number(8)
);

create table meeting(
    id number(8) primary key
    ,name VARCHAR2(18)
    ,title VARCHAR2(40)
    ,meetingdate date
);

create table imgtest(
    name VARCHAR2(18) primary key
    ,imgcontent blob                                               
);

select * from user_constraints
where table_name = 'NEWS';

```

