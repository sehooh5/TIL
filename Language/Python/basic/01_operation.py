### 연산지

print('--- and ---')
print(True and True)
print(True and False)
print(False and True)
print(False and False)
print('--- or ---') # (block)ctrl + d : 블럭 같은 내용 아래 블럭
print(True or True)
print(True or False)
print(False or True)
print(False or False)
print('--- not ---')
print(not True)
print(not 0)

## 단축평가
# 첫번째 값이 확실 할 때, 두번째 값은 확인하지 않음
# 조건문에서 뒷 부부을 판단하지 않아도 되기 때문에 속도 향상
print('--- 단축평가 ---')
vowels = 'aeiou'
print(('a' and 'b') in vowels)
print(('b' and 'a') in vowels)
print('a' and 'b') # b 가 출력 // a 는 True 여서 뒤로 넘어가서 b 까지 확인하고 b 출력

# and 는 둘 다 True 일 경우만 True 이기 때문에
# 첫번째 값이 True 라도 두번째 값을 확인해야 한다.
print(3 and 5) # 5
print(3 and 0) # 0
print(0 and 3) # 0
print(0 and 0) # 0

print(5 or 3) # 3
print(3 or 0) # 3
print(0 or 3) # 3
print(0 or 0) # 0 (오른쪽 0)

## Containment Test : in 연산자
# 요소가 속해있는지 여부를 확인 할 수 있다.
print('--- in ---')
print('a' in 'apple')
print(1 in [1,2,3]) # [] : list