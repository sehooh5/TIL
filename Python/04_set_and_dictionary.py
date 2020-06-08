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

set_c = {1,1,1} # {1}
