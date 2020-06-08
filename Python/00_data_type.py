### day1
# `ctrl` + `shift` + `p` : 사용 가능한 command palette

print('Hello, world!')

number = 10
string = '문자열'
bools = True
print (number, string, bools)

## 숫자형 (1.int  2.float  3.complex)
# int
a = 3
type(a) # type 확인
print(type(a))

## bool
print(type(False))
# False = 0 / 0,0 / () / [] / {} / '' / None

## 문자형
greeting = 'hi'
name = 'harry'
print(greeting,name)
print(type(name))

## 사용자의 입력을 받는 함수 : input
# 숫자를 입력해도 문자열로 인식함
# age = input()
# print(age)
# print(type(age))

# 개행문자 없이 여러줄을 그대로 출력하는 방법 ("""  """)
print("""
개행 문자 없이
여러 줄을 그대로 출력이
가능합니다
""")

print(3 * 'hey' + 'you!')

# string interpolation
name = 'kim'
# 1. %-formatting
print('Hello,%s' % name)
# 2. .format()
print('Hello, {}'.format(name))
# 3. f-string (Literal String Interpolation) -v3.6 부터 사용
print(f'Hello, {name}')

pi = 3.141592
print(f'원주율은 {pi}. 반지름이 2일때 원의 넓이는 {pi*2*2}')