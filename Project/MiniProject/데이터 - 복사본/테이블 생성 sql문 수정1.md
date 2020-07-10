# 사용자 생성

id flower 

password flower



# 모든 테이블 삭제하고 싶을 때

```sql
-- jung 0304 이건 실행하면 안되고 테이블 모두 삭제하고 싶을 때 사용하기
SELECT 'DROP TABLE "' || TABLE_NAME || '" CASCADE CONSTRAINTS;' FROM user_tables;
DROP TABLE "AREA" CASCADE CONSTRAINTS;
DROP TABLE "QUARTER" CASCADE CONSTRAINTS;
DROP TABLE "CUSTOMER" CASCADE CONSTRAINTS;
DROP TABLE "MANAGER" CASCADE CONSTRAINTS;
DROP TABLE "SERVICE" CASCADE CONSTRAINTS;
DROP TABLE "BOARD" CASCADE CONSTRAINTS;
DROP TABLE "LIVING_POPULATION" CASCADE CONSTRAINTS;
DROP TABLE "APARTMENT" CASCADE CONSTRAINTS;
DROP TABLE "STORE" CASCADE CONSTRAINTS;
DROP TABLE "JOB" CASCADE CONSTRAINTS;
DROP TABLE "AREA_SCOPE" CASCADE CONSTRAINTS;
DROP TABLE "CHANGE" CASCADE CONSTRAINTS;
DROP TABLE "AREA_C" CASCADE CONSTRAINTS;
DROP TABLE "AREA_M" CASCADE CONSTRAINTS;
DROP TABLE "BOARD_C" CASCADE CONSTRAINTS;
DROP TABLE "BORDER_M" CASCADE CONSTRAINTS;
DROP TABLE "CUSTOMER_M" CASCADE CONSTRAINTS;
```



# 프로젝트 테이블 생성 SQL문

```sql


# 프로젝트 테이블 생성 SQL문

​```sql
-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- area Table Create SQL
CREATE TABLE area
(
    area_id        INT             NOT NULL, 
    area_coname    VARCHAR2(60)    NOT NULL, 
    CONSTRAINT AREA_PK PRIMARY KEY (area_id)
)
/

COMMENT ON TABLE area IS '상권'
/

COMMENT ON COLUMN area.area_id IS '상권 코드'
/

COMMENT ON COLUMN area.area_coname IS '상권 코드명'
/


-- area Table Create SQL
CREATE TABLE quarter
(
    q_id       INT    NOT NULL, 
    area_id    INT    NOT NULL, 
    CONSTRAINT QUARTER_PK PRIMARY KEY (q_id, area_id)
)
/

COMMENT ON TABLE quarter IS '분기'
/

COMMENT ON COLUMN quarter.q_id IS '기준분기 코드'
/

COMMENT ON COLUMN quarter.area_id IS '상권 코드'
/

ALTER TABLE quarter
    ADD CONSTRAINT FK_quarter_area_id_area_area_i FOREIGN KEY (area_id)
        REFERENCES area (area_id)
/


-- area Table Create SQL
CREATE TABLE service
(
    serv_id        VARCHAR2(60)    NOT NULL, 
    serv_coname    VARCHAR2(60)    NOT NULL, 
    CONSTRAINT SERVICE_PK PRIMARY KEY (serv_id)
)
/

COMMENT ON TABLE service IS '서비스업종'
/

COMMENT ON COLUMN service.serv_id IS '서비스업종 코드'
/

COMMENT ON COLUMN service.serv_coname IS '서비스업종 코드명'
/


-- area Table Create SQL
CREATE TABLE sales_1
(
    area_id            INT             NOT NULL, 
    serv_id            VARCHAR2(60)    NOT NULL, 
    q_id               INT             NOT NULL, 
    month_sal_money    INT             NOT NULL, 
    month_sal_num      INT             NOT NULL, 
    wday_sal_money     INT             NOT NULL, 
    wkend_sal_money    INT             NOT NULL, 
    m_sal_money        INT             NOT NULL, 
    w_sal_money        INT             NOT NULL, 
    CONSTRAINT SALES_1_PK PRIMARY KEY (area_id, serv_id, q_id)
)
/

COMMENT ON TABLE sales_1 IS '상권_추정매출_1'
/

COMMENT ON COLUMN sales_1.area_id IS '상권 코드'
/

COMMENT ON COLUMN sales_1.serv_id IS '서비스업종 코드'
/

COMMENT ON COLUMN sales_1.q_id IS '기준분기 코드'
/

COMMENT ON COLUMN sales_1.month_sal_money IS '월 매출 금액'
/

COMMENT ON COLUMN sales_1.month_sal_num IS '월 매출 수'
/

COMMENT ON COLUMN sales_1.wday_sal_money IS '주중 매출 금액'
/

COMMENT ON COLUMN sales_1.wkend_sal_money IS '주말 매출 금액'
/

COMMENT ON COLUMN sales_1.m_sal_money IS '남성 매출 금액'
/

COMMENT ON COLUMN sales_1.w_sal_money IS '여성 매출 금액'
/

ALTER TABLE sales_1
    ADD CONSTRAINT FK_sales_1_area_id_quarter_are FOREIGN KEY (area_id, q_id)
        REFERENCES quarter (area_id, q_id)
/

ALTER TABLE sales_1
    ADD CONSTRAINT FK_sales_1_serv_id_service_ser FOREIGN KEY (serv_id)
        REFERENCES service (serv_id)
/


-- area Table Create SQL
CREATE TABLE customer
(
    cust_id       INT             NOT NULL, 
    cust_name     VARCHAR2(60)    NOT NULL, 
    cust_email    VARCHAR2(60)    NOT NULL, 
    cust_pw       VARCHAR2(60)    NOT NULL, 
    CONSTRAINT CUSTOMER_PK PRIMARY KEY (cust_id)
)
/

COMMENT ON TABLE customer IS '고객'
/

COMMENT ON COLUMN customer.cust_id IS '고객 코드'
/

COMMENT ON COLUMN customer.cust_name IS '고객 이름'
/

COMMENT ON COLUMN customer.cust_email IS '고객 이메일'
/

COMMENT ON COLUMN customer.cust_pw IS '고객 비밀번호'
/


-- area Table Create SQL
CREATE TABLE manager
(
    mana_id       INT             NOT NULL, 
    mana_email    VARCHAR2(60)    NOT NULL, 
    mana_pw       VARCHAR2(60)    NOT NULL, 
    CONSTRAINT MANAGER_PK PRIMARY KEY (mana_id)
)
/

CREATE SEQUENCE manager_SEQ
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER manager_AI_TRG
BEFORE INSERT ON manager 
REFERENCING NEW AS NEW FOR EACH ROW 
BEGIN 
    SELECT manager_SEQ.NEXTVAL
    INTO :NEW.mana_id
    FROM DUAL;
END;
/

--DROP TRIGGER manager_AI_TRG;
/

--DROP SEQUENCE manager_SEQ;
/

COMMENT ON TABLE manager IS '관리자'
/

COMMENT ON COLUMN manager.mana_id IS '관리자 코드'
/

COMMENT ON COLUMN manager.mana_email IS '관리자 이메일'
/

COMMENT ON COLUMN manager.mana_pw IS '관리자 비밀번호'
/


-- area Table Create SQL
CREATE TABLE board
(
    board_id     INT              NOT NULL, 
    title        VARCHAR2(60)     NOT NULL, 
    content      VARCHAR2(100)    NOT NULL, 
    writedate    DATE             NOT NULL, 
    cnt          INT              NOT NULL, 
    writer       VARCHAR2(60)     NOT NULL, 
    CONSTRAINT BOARD_PK PRIMARY KEY (board_id)
)
/

COMMENT ON TABLE board IS '게시판'
/

COMMENT ON COLUMN board.board_id IS '게시판 코드'
/

COMMENT ON COLUMN board.title IS '제목'
/

COMMENT ON COLUMN board.content IS '내용'
/

COMMENT ON COLUMN board.writedate IS '작성날짜'
/

COMMENT ON COLUMN board.cnt IS '조회수'
/

COMMENT ON COLUMN board.writer IS '작성자'
/


-- area Table Create SQL
CREATE TABLE living_population
(
    area_id           INT    NOT NULL, 
    q_id              INT    NOT NULL, 
    all_living_num    INT    NOT NULL, 
    CONSTRAINT LIVING_POPULATION_PK PRIMARY KEY (area_id, q_id)
)
/

COMMENT ON TABLE living_population IS '상권_상주인구'
/

COMMENT ON COLUMN living_population.area_id IS '상권 코드'
/

COMMENT ON COLUMN living_population.q_id IS '기준분기 코드'
/

COMMENT ON COLUMN living_population.all_living_num IS '총 상주인구 수'
/

ALTER TABLE living_population
    ADD CONSTRAINT FK_living_population_area_id_q FOREIGN KEY (area_id, q_id)
        REFERENCES quarter (area_id, q_id)
/


-- area Table Create SQL
CREATE TABLE apartment
(
    area_id      INT    NOT NULL, 
    q_id         INT    NOT NULL, 
    apart_num    INT    NOT NULL, 
    CONSTRAINT APARTMENT_PK PRIMARY KEY (area_id, q_id)
)
/

COMMENT ON TABLE apartment IS '상권_아파트'
/

COMMENT ON COLUMN apartment.area_id IS '상권 코드'
/

COMMENT ON COLUMN apartment.q_id IS '기준분기 코드'
/

COMMENT ON COLUMN apartment.apart_num IS '아파트단지 수'
/

ALTER TABLE apartment
    ADD CONSTRAINT FK_apartment_area_id_quarter_a FOREIGN KEY (area_id, q_id)
        REFERENCES quarter (area_id, q_id)
/


-- area Table Create SQL
CREATE TABLE store
(
    serv_id            VARCHAR2(60)    NOT NULL, 
    q_id               INT             NOT NULL, 
    area_id            INT             NOT NULL, 
    store_num          INT             NOT NULL, 
    sim_store_num      INT             NOT NULL, 
    start_rate         INT             NOT NULL, 
    start_store_num    INT             NOT NULL, 
    close_rate         INT             NOT NULL, 
    close_store_num    INT             NOT NULL, 
    CONSTRAINT STORE_PK PRIMARY KEY (serv_id, q_id, area_id)
)
/

COMMENT ON TABLE store IS '상권_점포'
/

COMMENT ON COLUMN store.serv_id IS '서비스업종 코드'
/

COMMENT ON COLUMN store.q_id IS '기준분기 코드'
/

COMMENT ON COLUMN store.area_id IS '상권 코드'
/

COMMENT ON COLUMN store.store_num IS '점포 수'
/

COMMENT ON COLUMN store.sim_store_num IS '유사업종 점포 수'
/

COMMENT ON COLUMN store.start_rate IS '개업 율(률)'
/

COMMENT ON COLUMN store.start_store_num IS '개업 점포 수'
/

COMMENT ON COLUMN store.close_rate IS '폐업 율(률)'
/

COMMENT ON COLUMN store.close_store_num IS '폐업 점포 수'
/

ALTER TABLE store
    ADD CONSTRAINT FK_store_serv_id_service_serv_ FOREIGN KEY (serv_id)
        REFERENCES service (serv_id)
/

ALTER TABLE store
    ADD CONSTRAINT FK_store_q_id_quarter_q_id FOREIGN KEY (q_id, area_id)
        REFERENCES quarter (q_id, area_id)
/


-- area Table Create SQL
CREATE TABLE job
(
    area_id        INT    NOT NULL, 
    q_id           INT    NOT NULL, 
    all_job_num    INT    NOT NULL, 
    CONSTRAINT JOB_PK PRIMARY KEY (area_id, q_id)
)
/

COMMENT ON TABLE job IS '상권_직장인구'
/

COMMENT ON COLUMN job.area_id IS '상권 코드'
/

COMMENT ON COLUMN job.q_id IS '기준분기 코드'
/

COMMENT ON COLUMN job.all_job_num IS '총 직장인구 수'
/

ALTER TABLE job
    ADD CONSTRAINT FK_job_area_id_quarter_area_id FOREIGN KEY (area_id, q_id)
        REFERENCES quarter (area_id, q_id)
/


-- area Table Create SQL
CREATE TABLE area_scope
(
    area_id    INT             NOT NULL, 
    x          INT             NOT NULL, 
    y          INT             NOT NULL, 
    gu_id      INT             NOT NULL, 
    gu_name    VARCHAR2(60)    NOT NULL, 
    CONSTRAINT AREA_SCOPE_PK PRIMARY KEY (area_id, gu_id)
)
/

COMMENT ON TABLE area_scope IS '상권_영역'
/

COMMENT ON COLUMN area_scope.area_id IS '상권 코드'
/

COMMENT ON COLUMN area_scope.x IS '엑스좌표'
/

COMMENT ON COLUMN area_scope.y IS '와이좌표'
/

COMMENT ON COLUMN area_scope.gu_id IS '구 코드'
/

COMMENT ON COLUMN area_scope.gu_name IS '구 이름'
/

ALTER TABLE area_scope
    ADD CONSTRAINT FK_area_scope_area_id_area_are FOREIGN KEY (area_id)
        REFERENCES area (area_id)
/


-- area Table Create SQL
CREATE TABLE change
(
    q_id             INT             NOT NULL, 
    area_id          INT             NOT NULL, 
    change_id        INT             NOT NULL, 
    change_coname    VARCHAR2(20)    NOT NULL, 
    oper_month       INT             NOT NULL, 
    close_month      INT             NOT NULL, 
    CONSTRAINT CHANGE_PK PRIMARY KEY (q_id, area_id)
)
/

COMMENT ON TABLE change IS '상권변화지표'
/

COMMENT ON COLUMN change.q_id IS '기준분기 코드'
/

COMMENT ON COLUMN change.area_id IS '상권 코드'
/

COMMENT ON COLUMN change.change_id IS '상권변화 코드'
/

COMMENT ON COLUMN change.change_coname IS '상권변화 코드명'
/

COMMENT ON COLUMN change.oper_month IS '운영 월'
/

COMMENT ON COLUMN change.close_month IS '폐업 월'
/

ALTER TABLE change
    ADD CONSTRAINT FK_change_q_id_quarter_q_id FOREIGN KEY (q_id, area_id)
        REFERENCES quarter (q_id, area_id)
/


-- area Table Create SQL
CREATE TABLE area_c
(
    cust_id    INT    NOT NULL, 
    area_id    INT    NOT NULL
)
/

COMMENT ON TABLE area_c IS '상권 고객 열람'
/

COMMENT ON COLUMN area_c.cust_id IS '고객 코드'
/

COMMENT ON COLUMN area_c.area_id IS '상권 코드'
/

ALTER TABLE area_c
    ADD CONSTRAINT FK_area_c_cust_id_customer_cus FOREIGN KEY (cust_id)
        REFERENCES customer (cust_id)
/

ALTER TABLE area_c
    ADD CONSTRAINT FK_area_c_area_id_area_area_id FOREIGN KEY (area_id)
        REFERENCES area (area_id)
/


-- area Table Create SQL
CREATE TABLE area_m
(
    area_id    INT    NOT NULL, 
    mana_id    INT    NOT NULL
)
/

COMMENT ON TABLE area_m IS '상권_관리'
/

COMMENT ON COLUMN area_m.area_id IS '상권 코드'
/

COMMENT ON COLUMN area_m.mana_id IS '관리자 코드'
/

ALTER TABLE area_m
    ADD CONSTRAINT FK_area_m_mana_id_manager_mana FOREIGN KEY (mana_id)
        REFERENCES manager (mana_id)
/

ALTER TABLE area_m
    ADD CONSTRAINT FK_area_m_area_id_area_area_id FOREIGN KEY (area_id)
        REFERENCES area (area_id)
/


-- area Table Create SQL
CREATE TABLE board_c
(
    cust_id     INT    NOT NULL, 
    board_id    INT    NOT NULL, 
    CONSTRAINT BOARD_C_PK PRIMARY KEY (cust_id, board_id)
)
/

COMMENT ON TABLE board_c IS '게시판_고객'
/

COMMENT ON COLUMN board_c.cust_id IS '고객 코드'
/

COMMENT ON COLUMN board_c.board_id IS '게시판 코드'
/

ALTER TABLE board_c
    ADD CONSTRAINT FK_board_c_cust_id_customer_cu FOREIGN KEY (cust_id)
        REFERENCES customer (cust_id)
/

ALTER TABLE board_c
    ADD CONSTRAINT FK_board_c_board_id_board_boar FOREIGN KEY (board_id)
        REFERENCES board (board_id)
/


-- area Table Create SQL
CREATE TABLE border_m
(
    mana_id     INT    NOT NULL, 
    board_id    INT    NOT NULL, 
    CONSTRAINT BORDER_M_PK PRIMARY KEY (mana_id, board_id)
)
/

COMMENT ON TABLE border_m IS '게시판_관리'
/

COMMENT ON COLUMN border_m.mana_id IS '관리자 코드'
/

COMMENT ON COLUMN border_m.board_id IS '게시판 코드'
/

ALTER TABLE border_m
    ADD CONSTRAINT FK_border_m_mana_id_manager_ma FOREIGN KEY (mana_id)
        REFERENCES manager (mana_id)
/

ALTER TABLE border_m
    ADD CONSTRAINT FK_border_m_board_id_board_boa FOREIGN KEY (board_id)
        REFERENCES board (board_id)
/


-- area Table Create SQL
CREATE TABLE customer_m
(
    cust_id    INT    NOT NULL, 
    mana_id    INT    NOT NULL, 
    CONSTRAINT CUSTOMER_M_PK PRIMARY KEY (cust_id, mana_id)
)
/

COMMENT ON TABLE customer_m IS '고객 관리'
/

COMMENT ON COLUMN customer_m.cust_id IS '고객 코드'
/

COMMENT ON COLUMN customer_m.mana_id IS '관리자 코드'
/

ALTER TABLE customer_m
    ADD CONSTRAINT FK_customer_m_cust_id_customer FOREIGN KEY (cust_id)
        REFERENCES customer (cust_id)
/

ALTER TABLE customer_m
    ADD CONSTRAINT FK_customer_m_mana_id_manager_ FOREIGN KEY (mana_id)
        REFERENCES manager (mana_id)
/


-- area Table Create SQL
CREATE TABLE sales_2
(
    area_id         INT             NOT NULL, 
    serv_id         VARCHAR2(60)    NOT NULL, 
    q_id            INT             NOT NULL, 
    sal_money_10    INT             NOT NULL, 
    sal_money_20    INT             NOT NULL, 
    sal_money_30    INT             NOT NULL, 
    sal_money_40    INT             NOT NULL, 
    sal_money_50    INT             NOT NULL, 
    sal_money_60    INT             NOT NULL, 
    CONSTRAINT SALES_2_PK PRIMARY KEY (area_id, serv_id, q_id)
)
/

COMMENT ON TABLE sales_2 IS '상권_추정매출_2'
/

COMMENT ON COLUMN sales_2.area_id IS '상권 코드'
/

COMMENT ON COLUMN sales_2.serv_id IS '서비스업종 코드'
/

COMMENT ON COLUMN sales_2.q_id IS '기준분기 코드'
/

COMMENT ON COLUMN sales_2.sal_money_10 IS '매출 금액 10'
/

COMMENT ON COLUMN sales_2.sal_money_20 IS '매출 금액 20'
/

COMMENT ON COLUMN sales_2.sal_money_30 IS '매출 금액 30'
/

COMMENT ON COLUMN sales_2.sal_money_40 IS '매출 금액 40'
/

COMMENT ON COLUMN sales_2.sal_money_50 IS '매출 금액 50'
/

COMMENT ON COLUMN sales_2.sal_money_60 IS '매출 금액 60'
/

ALTER TABLE sales_2
    ADD CONSTRAINT FK_sales_2_area_id_sales_1_are FOREIGN KEY (area_id, serv_id, q_id)
        REFERENCES sales_1 (area_id, serv_id, q_id)
/


-- area Table Create SQL
CREATE TABLE sales_3
(
    area_id          INT             NOT NULL, 
    serv_id          VARCHAR2(60)    NOT NULL, 
    q_id             INT             NOT NULL, 
    wday_sal_num     INT             NOT NULL, 
    wkend_sal_num    INT             NOT NULL, 
    m_sal_num        INT             NOT NULL, 
    w_sal_num        INT             NOT NULL, 
    CONSTRAINT SALES_3_PK PRIMARY KEY (area_id, serv_id, q_id)
)
/

COMMENT ON TABLE sales_3 IS '상권_추정매출_3'
/

COMMENT ON COLUMN sales_3.area_id IS '상권 코드'
/

COMMENT ON COLUMN sales_3.serv_id IS '서비스업종 코드'
/

COMMENT ON COLUMN sales_3.q_id IS '기준분기 코드'
/

COMMENT ON COLUMN sales_3.wday_sal_num IS '주중 매출 수'
/

COMMENT ON COLUMN sales_3.wkend_sal_num IS '주말 매출 수'
/

COMMENT ON COLUMN sales_3.m_sal_num IS '남성 매출 수'
/

COMMENT ON COLUMN sales_3.w_sal_num IS '여성 매출 수'
/

ALTER TABLE sales_3
    ADD CONSTRAINT FK_sales_3_area_id_sales_1_are FOREIGN KEY (area_id, serv_id, q_id)
        REFERENCES sales_1 (area_id, serv_id, q_id)
/


-- area Table Create SQL
CREATE TABLE sales_4
(
    area_id       INT             NOT NULL, 
    serv_id       VARCHAR2(60)    NOT NULL, 
    q_id          INT             NOT NULL, 
    sal_num_10    INT             NOT NULL, 
    sal_num_20    INT             NOT NULL, 
    sal_num_30    INT             NOT NULL, 
    sal_num_40    INT             NOT NULL, 
    sal_num_50    INT             NOT NULL, 
    sal_num_60    INT             NOT NULL, 
    CONSTRAINT SALES_4_PK PRIMARY KEY (area_id, serv_id, q_id)
)
/

COMMENT ON TABLE sales_4 IS '상권_추정매출_4'
/

COMMENT ON COLUMN sales_4.area_id IS '상권 코드'
/

COMMENT ON COLUMN sales_4.serv_id IS '서비스업종 코드'
/

COMMENT ON COLUMN sales_4.q_id IS '기준분기 코드'
/

COMMENT ON COLUMN sales_4.sal_num_10 IS '매출 수 10'
/

COMMENT ON COLUMN sales_4.sal_num_20 IS '매출 수 20'
/

COMMENT ON COLUMN sales_4.sal_num_30 IS '매출 수 30'
/

COMMENT ON COLUMN sales_4.sal_num_40 IS '매출 수 40'
/

COMMENT ON COLUMN sales_4.sal_num_50 IS '매출 수 50'
/

COMMENT ON COLUMN sales_4.sal_num_60 IS '매출 수 60'
/

ALTER TABLE sales_4
    ADD CONSTRAINT FK_sales_4_area_id_sales_1_are FOREIGN KEY (area_id, serv_id, q_id)
        REFERENCES sales_1 (area_id, serv_id, q_id)
/

```



# 시퀀스 생성

```
drop sequence board_SEQ;//자동생성 시퀀스 삭제
create sequence board_seq increment by 1 start with 1;
commit;
```



## view 생성

```sql
create or replace force view change_view as
select avg(oper_month) as oper_month_avg, avg(close_month) as close_month_avg from change;

create or replace force view job_view as
select avg(all_job_num) as all_job_num_avg from job;
```

