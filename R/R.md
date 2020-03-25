# R

Rstudy.Rproj 파일을 더블클릭해 R 스튜디오 기동 or 시작 프로그램에서 실행



#### 알아두기

1. vector 와 factor 의 차이점

2. data.frame

3. day1 -> 64-75 잘 모르겟 && ||, names

4. 행 추가하는방법  

   열 추가하는 방법 : 테이블명$컬럼명 <- 대입하고 싶은 변수들

   데이터 바꾸는 방법 등등

5. 행만 추출할 때 조건 줘서 출력하는 방법

   : emp[emp**$ename==**"KING" ,  ]   or    **subset**(emp,emp$ename=="KING") 

   열만 추출할 때

   : subset(emp,**select**=c("ename"))

6. **order 함수 개념 잘 알기**

7. 행렬곱 공부해보기

8. for구문 제대로 알기! ex) lab_06 - exam6

9. 함수식(데이터,**na.rm=T**) = NA 제외하고 식 실행

   해결 : min(x,na.rm = T)

10. 벡터 각각 만들어주는 함수 만들기

    해결 : append함수로 벡터에 계속 데이터 넣어주기

11. table 함수 잘 알아두기

    해결 : 빈도수 처리해주는 기능