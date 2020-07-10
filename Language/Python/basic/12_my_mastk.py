## 공적 마스크 API : JSON 
import requests
from pprint import pprint

### JSON viewr (Chrome) 다운받으면 JSON 파일 읽을 때 좋음!
## API 사용시 참고 사이트 들어가보면 좋다
URL = 'https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/storesByAddr/json'

parms = '?address=서울특별시 강남구 역삼동'



response = requests.get(URL+parms)
# print(response) # 200 응답
# print(response.content) 
# print(URL+parms)
# print(response.json()) # dic 형태로 만들어줌 
stores = response.json().get('stores')[:10]
# print(stores)

for store in stores:
    # pprint(store) # 더 깔끔하게 출력
    sname = store.get('name')
    sstat = store.get('remain_stat')
    if sstat == 'plenty':
        color = 'green'
    elif sstat == 'some':
        color = 'yellow'
    elif sstat == 'few':
        color = 'red'
    elif sstat == 'few':
        color = 'grey'
    print(sname+color)
