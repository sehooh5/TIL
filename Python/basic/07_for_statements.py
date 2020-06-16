# for
# for 문은 정해진 범위 내 시퀀스(문자열, 튜플, 리스트 같은) 혹은
# 반복 가능한 객체(iterable)의 요소들을 순차적으로 실행합니다

# structure
# for target_list in expression_list :  // expression_list(iterable) 를 반복
# 결과값

for num in [0, 1, 2, 3, 4]:
    print(num)

for num in range(100):
    print(num)


# list 에 한개의 요소 넣기 : list.append ()
result = []
for num in range(1, 31):
    if num % 2 == 0:
        result.append(num)
print(result)


# 차례로 열거하는 함수 : enumerate()
# 인덱스 변수를 하나 더 추가할 수 있다.
lunch = ['짜장면', '초밥', '탕수육']
for idx, menu in enumerate(lunch):
    print(idx, menu)


print(enumerate(lunch))  # 메모리 주소를 알려줌
print(type(enumerate(lunch)))  # class 'enumerate'
print(list(enumerate(lunch, start=1)))  # start 정해줄 수 있다
print(type(list(enumerate(lunch))[0]))
