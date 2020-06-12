# Django import style guide
# 1. standard library
# 2. 3rd party library (외부 설치 ex.request)
# 3. django
# 4. local django

import random
from pprint import pprint
from datetime import datetime  # datetime : 날짜 주관하는 패키지 및 파일
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


def hello(request, name):  # url 에서 전달받을 변수
    context = {
        'name': name,
    }
    return render(request, 'hello.html', context)


def intro(request, name, age):
    context = {
        'name': name,
        'age': age,
    }
    return render(request, 'intro.html', context)


def multiple(request, x, y):
    result = x * y
    context = {
        'x': x,
        'y': y,
        'result': result,
    }
    return render(request, 'multiple.html', context)


def dtl_practice(request):
    foods = ['짜장면', '짬뽕', '차돌짬뽕', '콩국수']
    empty_list = []
    messages = 'hello my name is python which has super power language'
    datetime_now = datetime.now()
    context = {
        'foods': foods,
        'empty_list': empty_list,
        'messages': messages,
        'datetime_now': datetime_now,
    }
    return render(request, 'dtl_practice.html', context)


def routing(request, word):
    # reverse_word = ''
    # for char in word:
    #     reverse_word = char + reverse_word
    # if word == reverse_word:
    #     result = '회문'
    # else:
    #     result = '회문이 아니다'
    reverse_word = word[::-1]
    if word == word[::-1]:
        result = '회문'
    else:
        result = '회문이 아니다'
    context = {
        'result': result,
        'word': word,
        'reverse_word': reverse_word,
    }
    return render(request, 'routing.html', context)


def throw(request):
    return render(request, 'throw.html')


def catch(request):
    msg = request.GET.get('message')
    msg2 = request.GET.get('second')
    context = {
        'msg': msg,
        'msg2': msg2,
    }
    # print(request.GET.get('message'))  # dic 객체에서 key 값으로 불러오는 방법 dic객체.get
    return render(request, 'catch.html', context)
