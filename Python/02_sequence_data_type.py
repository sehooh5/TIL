### Sequence
# 데이터가 순서대로 나열된 형식
# 데이터에 번호를 붙여 나열
# 주의사항 : 순서대로 나열된 것이 '정렬된 것'은 아님
# 가변 시퀀스 : list // 불변 시퀀스 : tuple, str, range()

## list type : [], list()
my_list1 = [10, '문자열', True]
print(my_list1)
print(type(my_list1))
print(my_list1[1]) # index 로 접근할 수 있음..0부터 시작

## 2. tuple(튜플)
# 특징 : tuple 은 수정 불가능(불변,immutable)하고 읽을 수 밖에 없다
# 사용 : 직접 사용하기보다는 파이썬 내부에서 사용
my_tuple1 = (1,2)
print(my_tuple1)
print(type(my_tuple1))

# 어떻게 파이썬 내부에서 사용하고 있을까?
my_tuple2 = 1,2
print(my_tuple2)
print(type(my_tuple2))

x,y = 1,2
print(x)
print(y)

x,y = y,x
print(x)
print(y)

# 요소가 한개인 tuple 은 값 뒤에 ',' 붙여준다
single_tuple = ('hello',)
print(type(single_tuple))


## 3. range() 함수
# range는 숫자의 시퀀스를 나타내기 위해 사용
# list 나 tuple 에 비해 범위의 크기에 무관하게 항상 같은 양의 메모리를 사용한다
# 주의사항 : 
# range가 돌려준 객체(iterable : 반복 가능한 개체)는 리스트인 것 같지만, 리스트가 아니다. 
# 반복할 때 원하는 시퀀스 항목들을순서대로 돌려주는 객체이지만 실제로는 리스트를 만들지 않아서 
# 공간을 절약하는 원리이다.
print(type(range(1)))
print(range(10)) # range(0,10) : [0,1,2,3,4,5,6,7,8,9]
print(list(range(10)))
print(list(range(1,10,2))) # [1,3,5,7,9]

