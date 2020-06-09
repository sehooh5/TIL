### lotto : random and request modules

import random # 내부 모듈
# pip install requests를 terminal에서 사용하여 requests 모듈을 설치한다!!
import requests # 크롤링하기 위함 !!(내부 모듈 x -> 설치 해야함)

numbers = range(1, 46)
print(list(numbers))
print('-----lotto-----')
pick = random.sample(numbers, 6)
print(pick)

lotto_url = 'https://dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=914'

response = requests.get(lotto_url)
# print(response) # <Response [200]> -> 객체
# print(dir(response))
# print(response.content) # json형식!
#print(response.json()) # json형식이 아니고dict형식이다.
lotto_info = response.json()

## 가져오는 방법 두가지
bonus_num = lotto_info['bnusNo'] # by key
bonus_num = lotto_info.get('bnusNo') # 
print(bonus_num)

winner = []

for idx in range(1,7):
    winner.append(lotto_info.get(f'drwtNo{idx}'))

print(winner)
print(len(set(winner)-set(pick)))
# pick winner 비교
win = len(set(winner)-set(pick))
bwin = len(set(pick) & {bonus_num})
print(bwin)

if win == 0:
    print('로또 1등 당첨!! 추카추카')
elif win == 1 | bwin == 1:
    print('로또 2등 당첨!! 그래도 추카')
elif win == 1:
    print('로또 3등 당첨!! 한 개만 더 맞히지 ㅎ')
elif win == 2:
    print('로또 4등 당첨!! 두 개만 더 맞히지 ㅎ')
elif win == 3:
    print('로또 5등 당첨!! 엿바꿔먹어라~')
elif win > 3:
    print('꽝이다!!!흑')
