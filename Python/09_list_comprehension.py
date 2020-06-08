### list_comprehension
# 반복을 통해 리스트에 어떠한 것을 담는 경우 한 줄로 줄여 쓰는 것
# 단순히 반복문을 한 줄로 작성하는 것이 아님
# 시퀀스의 요소들 전부 또는 일부를 처리하고, 그 결과를 리스트로 돌려주는 간결한 방법
# 장점 : 
# 1.간결함   2.성능(일반화의 위험성)   3.표현력(Pythonic)
# 단점 : 
# 남용하면 안된py다 (ex. for + if else 사용할 때)

my_list = []

for x in range(10):
    my_list.append(x ** 2)
print(my_list)


my_newlist = [x**2 for x in range(10)] # 위 코드와 동일하다
my_newlist2 = list(x**2 for x in range(10))

print(my_newlist)
print(my_newlist2)


## list comprehension with if statements
numbers = list(range(10,100,10))
print(numbers)

my_numbers_1 = []
for number in numbers:
    if number %4 == 0:
        my_numbers_1.append(number)

my_numbers_1 = [number for number in numbers if number % 4 == 0]
print(my_numbers_1)

## 조건 표현식 (with if else) : 순서가 바뀐다
# if 의 왼쪽에 if 결과값 / else 오른쪽에 else 결과값
# true_value <if> 조건식 <else> false_value
my_numbers_2 = []
for number in numbers:
    if number >= 50:
        my_numbers_2.append(number + 7)
    else:
        my_numbers_2.append(number + 5)

my_numbers_2 = [number + 7 if number >= 50 else number + 5 for number in numbers]
print(my_numbers_2)


# 구구단 .. 가독성은 좋지 않다
gugu = []
for a in range(2, 10):
    for b in range(1, 10):
        gugu.append(a * b)
print(gugu)

print([a * b for a in range(2, 10) for b in range(1, 10)])