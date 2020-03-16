<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리포트 페이지</title>
</head>
<body>
<pre>
고객님께서 선택하신 (상권코드명(quarter테이블의 area_coname) 상권의 (서비스업종명(service테이블의 serv_coname ) ) 업종 분석 정보에 대해 안내 드리겠습니다.

## sales_1 테이블

- 1) month_sal_money 2) month_sal_num

a 업종의 월매출 금액은 ${requestScope.month_money} 이고 월 매출 수는 2) 입니다.

- 1) wday_sal_money 2) wkend_sal_money

a 업종의 주중(월-금) 매출 금액은 1) 이고.  주말(토,일) 매출 금액은 2) 입니다.

- 1) m_sal_money 2) w_sal_money

a 업종의 남성 매출 금액은 1) 이고, 여성 매출 금액은 2) 입니다.



## sales_2 테이블

- 1) sal_money_10 2) sal_money_20 3) sal_money_30 4) sal_money_40 5) sal_money_50 6) sal_money_60

a 업종의 연령별 매출 금액에 대한 정보 입니다.

10대 매출 금액 : 1)

20대 매출 금액 : 2)

30대 매출 금액: 3)

40대 매출 금액: 4)

50대 매출 금액: 5)

60대 매출 금액: 6)



## sales_3 테이블



1) wday_sal_num 2)wkend_sal_num 3)m_sal_num 4)w_sal_num

a 업종의 주중(월-금) 매출 수는 1)이고, 주말(토-일) 매출 수는 2) 입니다.

a 업종의 남성 매출 수는 1) 이고, 여성 매출 수는 2) 입니다.



## sales_4 테이블

1) sal_num_10 2) sal_num_20 3) sal_num_30 4) sal_num_40 5) sal_num_50 6) sal_num_60

10대 매출 수 : 1)

20대 매출 수 : 2)

30대 매출 수: 3)

40대 매출 수: 4)

50대 매출 수: 5)

60대 매출 수: 6)





# 
</pre>
</body>
</html>