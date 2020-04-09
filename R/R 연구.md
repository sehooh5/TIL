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

13. closer 타입 오류 뜨면 처음부터 다시 초기화시키고 구동시키기

14. POST 방식 사용 할 때는 **httr**패키지 사용해야 한다

15. **컬럼명** 설정하는 방법

    ```
    
    ```

16. write 와 write.table 차이점 : write 는 글들만 저장 table 은 table 형식으로 

17. **apply** 에 대해서(day8, 프린트물 8page 참고)

18. 변수들 카운트 하는 방법

    - summary
    - table
    
19. **substr** : 문자 쪼갤 수 있는 기능

