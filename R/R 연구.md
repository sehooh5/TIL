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

9. CSS 선택자 잘 알아두기 (자료 만듬)

10. CSS 선택자로 안될때?

    : xpath 를 사용 (ex..태그가 없는 콘텐츠 가져오고싶을때)

11. cbind & rbind 차이점 잘 알아두기

    : cbind = 열단위 저장

    : rbind = 행단위 저장

12. 바인딩 할 때 for 나가고 싶으면 **rbind** 로 기존 파일 덮어씌워서 저장

