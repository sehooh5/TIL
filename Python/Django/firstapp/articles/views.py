# Django import style guide
# 1. standard library
# 2. 3rd party library (외부 설치 ex.request)
# 3. django
# 4. local django

import random
from django.shortcuts import render

# Create your views here.


def index(request):
    # import 된 render 함수의 필수인자 두개 사용 1. request 2. template_name
    # template 는 templates 폴더 안에 있다고 자동으로 인식해서 주소 안써줘도됨
    return render(request, 'index.html')


def dinner(request):  # 무조건 request를 넣어줌
    foods = ['보쌈', '치킨', '햇반', '단무지', '김밥']
    pick = random.choice(foods)
    context = {
        'pick': pick,  # ''인용 부호 안에 있는게 보내는 키 값
    }
    # render 의 세번째 context = dic 값을 같이 보내줌
    return render(request, 'dinner.html', context)


def pic(request):
    id = random.choice(range(1000))
    pic = f'http://i.picsum.photos/id/{id}/200/300.jpg'  # 사진 랜덤으로 뽑아주는 url
    context = {
        'pic': pic,
    }
    return render(request, 'pic.html', context)
