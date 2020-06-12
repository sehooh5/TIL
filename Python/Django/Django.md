# Django

- 서버를 개발하기 위한 프레임 워크



## Django 의 구조

- `Django` 는 MVC와 비슷하지만 Model - Template - View : **MTV**
- M : 데이터 관리  //  T : 사용자가 보는 화면  //  V : 중간 관리자



## Django 설치 및 실행

- 설치 : 

  ```
  pip install django==2.1.15
  ```

- 설치 확인: 

  ```
  pip list
  ```

- 장고 프로젝트 만들기(처음에만 사용) : 

  ```
  django-admin startproject firstapp
  ```

- 서버 작동 : 

  ```
  python manage.py runserver
  ```

- 앱 생성 : 

  ```
  python manage.py startapp [app name]
  ```

- 명령어 기본 : 

  ```
  python manage.py ~~~~~~~
  ```



## 프로젝트 기본 구성 내용

- `__init__.py` : 해당 디렉토리를 패키지화 해주는 파일 (ex. import firstapp 가능)
- `settings.py` : 모든 설정을 관리해주는 파일
- `urls.py` : 요청 url
- `wsgi.py` : 배포할 때 사용
- `admin.py` : 관리자용 페이지를 커스터마이징 하는 파일
- `apps.py` : app 의 정보가 작성된 곳. 절대! 수정하지 않음
- `models.py` : app 에서 database 즉 `model` 을 정의하는 곳
- `test.py` : test code 를 작성하는 곳
- `views.py` : view들이 정의 되는 곳..다양한 함수 작성 (중간 관리자-젤 중요)



## Django 프로젝트 특징

- **app 들의 집합** (한개의 프로젝트에 여러 app들이 존재함)
- 실제 요청을 처리하고 페이지를 보여주고 하는 것들은 이 app들의 역할
- app은 하나의 역할 및 **기능 단위**로 쪼개는 것이 일반적
- 작은 규모의 서비스에서는 세부적으로 나누지 는 않는다
- app 이름은 **복수형**으로 하는 것이 권장된다



## Django 프로젝트 진행 과정

1. 프로젝트 생성

2. 앱 생성

3. 프로젝트에 앱 등록 및 수정 : `settings.py` 파일 에서

   ```python
   INSTALLED_APPS = [
   	# 1. local apps : 최상단에 우리가 만든 앱
     # 2. 3rd party app
     # 3. django apps : 기본으로 작성된 애들
     'articles', #app 이름
     'django.contrib.admin',
     'django.contrib.auth',
     'django.contrib.contenttypes',
     'django.contrib.sessions',
     'django.contrib.messages',
     'django.contrib.staticfiles', # 마지막 콤마 : 트레일링 콤마, 장고에서만 사용......파이썬에서는 사용할 수 없음
   ]
   
   # 아래처럼 변경해줘
   LANGUAGE_CODE = 'ko-kr' # 한글 적용
   
   TIME_ZONE = 'Asia/Seoul' # 서울로 시간 맞춰줌
   ```

4. `urls.py` 작성 : 

   ```python
   # articles app의 views.py import 해주기
   from articles import views
   
   urlpatterns = [
       path('admin/', admin.site.urls),
       path('index/', views.index),
       path('dinner/', views.dinner),
       path('pic/', views.pic),
       # 뒤에 동적 변수를 사용하고싶으면 <타입 : 변수명>
       # str 타입 명시는 생략 가능
       path('hello/<str:name>/', views.hello),
       path('intro/<name>/<int:age>/', views.intro),
       path('multiple/<int:x>/<int:y>/', views.multiple),
       path('dtl-practice/', views.dtl_practice),
       path('routing/<word>/', views.routing),
   ]
   ```

5. `views.py` 작성 : 

   ```python
   # Django import style guide
   # 1. standard library
   # 2. 3rd party library (외부 설치 ex.request)
   # 3. django
   # 4. local django
   
   import random
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
   
   ```

6. `template ` 작성 : app안에 존재하는 `templates`에  작성되야함 

   ```python
   <!DOCTYPE html>
   <html lang="en">
   <head>
       <meta charset="UTF-8">
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
       <title>dtl practice</title>
   </head>
   <body>
       <h3>1. 반복문</h3>
       <!-- 사용자들에게 보여지지 않는것은 {% 를 사용한다 -->
       <!-- for, if 문이 열리면 endfor 로 닫아줘야 한다 -->
   
       {% comment %} {% for food in foods %}
           <p>{{ food }}</p>
       {% endfor %}  {% endcomment %}
       {% for food in foods %}
           <p>{{ forloop.counter }} {{ food }}</p>
       {% endfor %} 
   
       {% for element in empty_list %}
           <p> {{ element }} </p>
       {% empty %}
           <p>지금 아무것도 없네요</>
       {% endfor %}
   
       <h3>2. 조건문</h3>
       {% if '짜장면' in foods %}
           <p>짜장면 맛잇겟드아</p>
       {% endif %}
   
       {% for food in foods %}
           {% if forloop.first %}
               <p>짜장면보단 짬뽕이지</p>
           {% else %}
               <p>{{ food }}</p>
           {% endif %}
       {% endfor %}
   
       <h3>3. length filter</h3>
       {% for food in foods %}
           {% if food|length > 2 %} <!-- 길이를 재주는 필터 : |뒤에 length-->
               <p>메뉴 이름이 너무 길어요</p>
           {% else %}
               <p>{{ food }}, {{ food|length }}</p>
           {% endif %}
       {% endfor %}
   
       <h3>4. lorem ipsum</h3> <!-- deleting dummy data : lorem + tab -->
           <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Assumenda tenetur facere, a, accusantium molestiae perspiciatis minus ab laboriosam tempore porro cum nesciunt consectetur distinctio voluptate quibusdam accusamus fugiat natus quos!</p>
           <p>Lorem ipsum dolor sit.</p>
           <p>{% lorem %}</p>
           <hr>
           <p>{% lorem 3 w %}</p>   
           <p>{% lorem 3 w random %}</p> 
   
       <h3>5. 글자수 제한</h3>
           <p>{{ messages|truncatewords:3 }} </p> <!-- truncatewords 글자수 제한 필터 -->
           <p>{{ messages|truncatechars:10 }} </p>  
   
       <h3>6. 글자 관련 필터</h3>
           <p>{{ 'ABC'|lower }}</p>
           <p>{{ messages|title }}</p>
           <p>{{ messages|capfirst }}</p> <!-- 맨 앞글자만 capital -->
           <p>{{ foods|random }}</p> <!-- sequence 에서 random 출력 -->
   
       <h3>7. 연산</h3>
           <p>{{ 4|add:6 }} </p>
   
       <h3>8. 날짜표현</h3>
           <p>view 에서 계산 후 출력 : {{ datetime_now }}</p> 
           <p>DATETIME_FORMAT : {% now "DATETIME_FORMAT" %}</p> <!-- DATETIME_FORMAT 바꿔줄수 잇다 -->
           <p>DATE_FORMAT : {% now "DATE_FORMAT" %}</p>
           <p>SHORT_DATETIME_FORMAT : {% now "SHORT_DATETIME_FORMAT" %}</p>
           <p>SHORT_DATE_FORMAT : {% now "SHORT_DATE_FORMAT" %}</p>
   </body>
   </html>
   ```

   

![image-20200610104612830](/Users/seho/Library/Application Support/typora-user-images/image-20200610104612830.png)







## 검색 기능(구글)

- `url` 을 직접 치는 것이 아닌 검색창에 직접 입력해준다

- 검색창을 개발자도구로 보면  `form` 태그로 구성되어있고 `action=/search` 로 내용을 보내는 것을 알 수 있다

- 검색창의 `input` 태그를 보면 `name=q` 속성을 확인 할 수 있는데 쿼리문에

   `q=검색내용` 인 것을 확인할 수 있다

- `View` 함수가 두 개 필요하다

  - 사용자로부터 입력을 받는 `form` 을 보여주는 `View_1` : `Throw`
  - 입력된 데이터를 받아서 처리하는 `View_2` : `Catch`

- `form` 속성에서 중요한 것 **어디로** 보낼 것 인지: `action` 속성

- **어떤 방식**으로 보낼지 : `method` 속성

- **어떤 데이터**를 보낼지 : `input` 태그, `type` 속성

- 데이터의 **이름은 어떻게 붙일지** : `name` 속성

- **제출** : `input` 태그의 `type=submit` 속성



## HTTP Method

### GET

- 이 요청은 서버로부터 정보를 조회하는데 사용된다
- 서버의 데이터나 상태를 변경시키지 않기 때문에 단순 조회(html)할 때 사용