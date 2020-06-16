### slicing

## 여러가지 타입의 변수사용하는 변수명 _ 사용(스네이크 케이스)
sample_list = list(range(0,31))
print(sample_list)
print(sample_list[1:3]) # [1,2] 
print(sample_list[10:-1]) # [10~29]
print(sample_list[0:len(sample_list): 3] ) # len() : list 의 길이 / 마지막은 step
print(sample_list[:: 3] ) # 생략하면 처음과 끝
print(sample_list[::-1]) # 뒤에서부터 역순으로 출력
print(sample_list[1:1]) # 출력 안됨