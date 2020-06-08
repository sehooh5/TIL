## 1. set : 집합
# 순서가 없는 자료구조
# 집합의 요소는 uniqueㅎ다. 중복을 허용하지 않는다.
# 순서가 없으므로 set 는 요소의 위치나 삽입 순서를 기록하지 않는다
# 따라서 set 는 indexing, slicing 또는 기타 시퀀스와 유사한 동작을 지원하지 않는다.
# 수학에서의 집합과 동일하게 처리된다.

set_a = {1,2,3}
set_b = {3,6,9}

print(set_a - set_b) # 차집합
print(set_a | set_b) # 합집합
print(set_a & set_b) # 교집합

set_c = set()

set_c = {1,1,1} # {1}


## 2. dictionary
# dictionary는 key 와 value가 쌍으로 이루어져 있으며, 궁극의 자료구조라 한다. (JSON 과 동일한 형태)
# key 중복이 되어선 안되며 중복되면 나중에 작성된 key 값으로 설정된다
# key 는 불변한 모든 것이 가능. immutable = (str, int, float, boolean, tuple, range)
# value 는 list, dict 를 포함한 모든 타입이 가능하다

# dictionary 생성방법
dict_a = {}
dict_b = dict()
print(type(dict_a))
print(type(dict_b))

dict_a = {1:1 ,2: 2,3: 3, 'c': 4}
print(dict_a)

phone_book = {
    '서울' : '02',
    '경기' : '031',
    '대전' : '042'
    }
print(phone_book['서울'])

# 실행 가능한 함수 보여주는 함수 : dir()
print(dir(dict))
# 공식문서의 설명 내용을 보여줌 : help
print(help(dict))

print(phone_book.values()) # =dict_values(['02','031','041']) : type = dict_values but [] 이므로 list처럼 사용
