### while

# while 문은 조건식이 참인 경우 반복적으로 코드를 실행
# 조건문이 false 가 안되면 무한루프가 되므로 종료 조건을 반드시 설정해주어야 한다.
n = 0

while n < 3 : 
    print(n)
    n = n + 1


a = ''

while a != '안녕': # a 가 '안녕'일 때까지 반복
    print('안녕이라고 할 때 까지 물어볼꺼야 ...')
    a = input('말해봐 : ')