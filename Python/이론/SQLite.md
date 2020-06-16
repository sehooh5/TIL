# SQLite

## ORM

- *OOP 프로그래밍에서 RDBMS를 연동 할 때, 데이터 베이스와 OOP 프로그래밍 언어간의 호환되지 않는 데이터를 변환하는 프로그래밍 ( OOP : 객체 지향  )

### 장점

- 비록 SQL 문법을 몰라도 Python 을 알고 있다면 ORM 이 중간에서 처리해주기 때문에 DB 연동이 가능하다
- SQL 의 절차적인 접근이 아니니 객체 지향적인 접근으로 인해 **생산성**이 증가한다

### 단점

- ORM 만으로 완전한 서비스를 구현하는데에는 어렵다.



## Model 작성 단계

- `models.py`에 class 를 작성해서 **데이터베이스 틀** 잡아주기
- `python manage.py makemigrations` 실행 :  **설계도** 만들기
  - migrations 폴더 안에 작성됨 : sql 해석 전에 python 으로 된 설계도
- `python manage.py sqlmigrate articles 0001` : 설계도 확인(app + 번호만)
- `python manage.py migrate` : **데이터 테이블 만들기**



## ipython

- 다운로드 : `pip install ipython`
- 그냥 실행 : `ipython`
- 장고에서 실행 : `python manage.py shell`
- Django extension 설치 : `pip install django-extensions`
  - setting.py 에 INSTALLED_APPS 에 등록 :  'django_extensions',
  - <mark>shell plus 사용</mark> : `python manage.py shell_plus` (이거로 실행해야댐!)



## SQLite 

- extension 다운로드
- `ctrl+shift+p` 에서 `Sqlite : opne database`



## ORM 조작

- `Article.objects.all()` : objects 는 함수를 사용할 수 있게하는 매니저 역할
  - `: dir(Article.objects) ` 로 사용 가능한 함수 확인 가능

### Objects

- models.py 에 작성한 클래스를 불러와서 사용할 때 DB 와의 인터페이스 역할을 하는 manager
- Python class(python) ----- objects(manager) ----- DB(SQL)

### QuerySet

- `<QuerySet []>` : objects 매니저를 사용하여 복수의 데이터를 가져오는 함수를 사용할 때 반환되는 객체 타입
- 단일 객체는 Query (class 의 인스턴스로 반환)
- query(질문)를 DB 에게 보내서 글을 조회하거나 생성, 수정, 삭제
- query를 보내는 언어를 활용해서 DB에게 데이터에 대한 조작을 실행



## CRUD

##### READ

``` python
# 모든 객체 조회
Article.objects.all()

# 특정 객체 조회
Article.objects.get(pk=1)

# 특정 조건 객체 가져오기
Article.objects.filter(title='first')
Article.objects.filter(title='first', content='내용')

#내림차순
Article.objects.order_by('-pk')

#LIKE
Article.objects.filter(title__contains = 'fi') # %fi%
Article.objects.filter(title__startswith = 'fi') # fi%
Article.objects.filter(title__endswithcontains = '!') # %!
```

- .get()을 사용할 때 
  - 해당 객체가 없으면 DoesNotExist 에러가 발생
  - 여러개의 경우에 MultileObjectReturned 에러가 발생
- 이와 같은 특징 때문에 unique한 속성을 가지고 있는 데이터에 사용해야 함 (pk : 100% unique보장, pk=id)



##### CREATE 

- SQL문을 ORM으로 대체해서 사용

``` python
# 1
article = Article()
article.title = 'first'
article.content = 'django!'
article.save()

# 2
article = Article(title='second', content='django!!')
article.save()

# 3 - 인스턴스 생성하지 않음!! ->> 내부적으로 save 해줌
Article.objects.create(title='third', content='django!!!')
```



##### UPDATE

``` python
article = Article.objects.get(pk=1)
article.title = 'edit title'
article.save()
```

- save() 꼭 해줘야 함



##### DELETE

``` python
article = Article.objects.get(pk=1)
article.delete()
```



#### admin 슈퍼 계정 생성

1. python manage.py createsuperuser

   ``` python
   $ python manage.py createsuperuser
   사용자 이름 (leave blank to use 'user'): admin
   이메일 주소: 
   Password: password
   Password (again): password
   비밀번호가 너무 일상적인 단어입니다.
   Bypass password validation and create user anyway? [y/N]: y
   Superuser created successfully.
   ```

   - 이메일 주소 입력하지 않아도 됨
   - password는 본인이 하고 싶은 password

   - <mark>계정 또한 데이터이기 떄문에 반드시 migrate 작업 후에 관리자 계정을 생성해야 함</mark>

2. admin.py작성

   ``` python
   from django.contrib import admin
   from .models import Article
   
   # Register your models here.
   admin.site.register(Article)
   # admin site에 등록(register)하겠다. -> 자동완성 안됨
   ```

3. 서버 들어가서 쿼리문에 `/admin` 추가해주면 admin 페이지 들어감



### 데이터 베이스 초기화

1. migrations 에 있는 넘버링된 파일들 삭제
2. db.sqlite3 까지 삭제



## Create할 때 인덱스 보여주기(POST)

### POST 사용

#### articles app/crud 에서 

- 사용자는 Django 에게 'html 파일을 줘!(GET)'가 아니라 '~한 레코드(글)을 생성해줘!!(POST)'이기 때문에 http method POST를 사용해야 한다.
- 데이터는 URL에 직접 노출되서는 안된다.
- 우리가 URL에 접근하는 방식은 모두 GET
- query의 형태를 통해 DB 구조(schema)를 유추할 수 있고 이는 보안적인 측면에서 매우 취약하다
- **DB를 조작**하는 친구는 GET이 아닌 **POST**!! 왜? 중요한 요청이기 때문에 최소한의 신원 확인이 필요!

### 사용방법

- templates, views 모두 POST 로 변경

- 승인을 위한 토큰이 필요함!!!
  - form태그 안에 : **{% *csrf_token* %}** 를 사용해준다!!!!
- `views.py` 에 render 가 아닌 **redirect** 를 import 하고 사용





## 상세페이지 만들기(Read-detail)

- `urls.py` 에 path 추가해주는데 `pk` 를 `int` 형으로 주고 받게 해줌

  ```python
  path('<int:pk>/', views.detail, name='detail'),
  ```

- `views.py` 에 pk를 받아와서 데이터 베이스의 값을 받아와서 context 로 전달

- template 에서 값들 받아오기

  ```python
  {% block content %}
      <h1>DETAIL</h1>
      <h2>{{ article.pk }} 번 글</h2>
      <hr>
      <p>제목 : {{article.title}}</p>
      <p>내용 : {{article.content}}</p>
      <p>작성시각 : {{article.created_at}}</p>
      <p>수정시각 : {{article.updated_at}}</p>
      <hr>
      <a href="{% url 'articles:index' %}">back</a>
  {% endblock %}
  ```



### Index 페이지에 Detail 연결시키기

- template 에서 a 태그 추가

  ```python
  # path('<int:pk>/', views.detail, name='detail'),
  # {% url 'app_name:view_name' article.pk %}
  
  <a href="{% url 'articles:detail' article.pk %}">DETAIL</a>
  ```

- 참고

  https://docs.djangoproject.com/ko/3.0/ref/templates/builtins/ + url 검색



