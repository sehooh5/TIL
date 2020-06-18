# Django Form

- https://docs.djangoproject.com/ko/2.1/topics/forms/ : 참고자료 (2.1/ko)

- 모델 작성하는 형식과 거의 비슷하다

  ```python
  from django import forms
  
  class NameForm(forms.Form):
      your_name = forms.CharField(label='Your name', max_length=100)
  ```

- <mark>**코드의 반복을 줄여준다**</mark>

- form 내 field들 , field 배치, widget, label 유효한 값 등을 정의하고 비유효한 field에 관련된 에러메시지를 결정한다

- 직접 form 태그를 작성하는 것보다 유효한 데이터에 요구되는 여러 동작을 올바르게 하기 위해서 제공하는 기능





## Model Form

- Django가 해당하는 모델에서 양식에 필요한 모든 정보를 이미 정의한다
- Meta 정보를 통해 어떤 model을 정의하는지 이미 알고 있기 때문에 검증이 끝나면 바로 save() 가 가능하다

- 우리가 작성한 Model 기준으로 자동으로 작성해준다

  1. `forms.py` 에 `models.py` 를 import 해준다

     ```python
     from .models import Article
     ```

  2. `forms.py` 에 class 를 추가해주는데 훨씬 간단하다

     ```python
     class ArticleForm(forms.ModelForm):
     
         class Meta:
             model = Article()
             fields = ['title', 'content', ]
     ```

     -  Meta : ArticleForm 클래스에 대한 정보를 작성하는 곳 ( Django 문법 )

---



## 사용법

- `TextField` 가 없다 : `widget`사용

  ```python
  content = forms.CharField(widget=forms.Textarea)
  ```

  ( `widget` 참고 : https://docs.djangoproject.com/en/3.0/ref/forms/widgets/)

- 작성 후 `views.py` 에서 import 해서 사용

  ```python
  from .forms import ArticleForm
  
  # new 변경
  def new(request):
      form = ArticleForm()
      context = {
          'form': form
      }
      return render(request, 'articles/new.html', context)
  ```

- `template` 수정

  ```python
  {% extends 'base.html' %}
  
  {% block content %}
      <h1>NEW</h1>
      <form action="{% url 'articles:create'%}" method="POST">
          {% csrf_token %}
        	# 중간 입력 부분을 삭제하고 입력
          # as_ul, as_table, as_p = p태그로 묶어줌
          {{ form.as_p }} 
          <button class="btn btn-primary">제출</button>
      </form>
  {% endblock  %}
  ```

- `views.py` 에서 `new.html` 에서 전달 받은 내용 create에 전달하여 작성

  ```python
  def create(request):
      # ArticleForm 의 내용인 title, content 가 form 에 저장된다
      form = ArticleForm(request.POST)
      # 유효성 검사 : is_valid() = TRUE = 안전한 데이터
      if form.is_valid():
          form.save()
          return redirect('articles:detail', article.pk)
      else:
          return redirect('articles:new')
  ```

- create+new기능을 합치기 & update+edit

  - GET : 수정,작성 페이지(html)을 렌더링 해주면 됨
  - POST : 수정,작성 내용을 작성한다

  ```python
  def create(request):
  
      # create 와 new를 한 개의 함수로 작성
  
      # POST 일 때=create 기능
      if request.method == 'POST':
          # ArticleForm 의 내용인 title, content 가 form 에 저장된다
          form = ArticleForm(request.POST)
          # 유효성 검사 : is_valid() = TRUE = 안전한 데이터
          if form.is_valid():
              article = form.save()
              return redirect('articles:detail', article.pk)
          else:
              return redirect('articles:new')
      # GET 일때(= new 기능) 혹은 다른 method
      else:
          form = ArticleForm()
      # 특이하게 이렇게 빼놨다 (유효성 검사 때문)
      # <form 의 두가지 형태>
      # form1 : POST 로 들어갔지만 유효성 검사에서 튕겨저 나온 것, error message 포함
      # form2 : else 로 들어갔다 나온 GET의 form
      context = {
          'form': form,
      }
      return render(request, 'articles/new.html', context)
    
    
  
    
  ```

  - <mark>작성 후 두개로 나뉘었던 것들 다 지워줘야함</mark>
    - urls - 삭제
    - views - 삭제
    - templates - 삭제

## View Decorator(심화: 좀더 단단한 서버 만들기)

- 함수에 추가기능을 부여하는 기능

  1. `views.py` 에 import : 

     ```
     from django.views.decorators.http import require_http_methods, require_POST
     ```

     

     `from django.views.decorators.http import require_http_methods`

  2. 원하는 함수에 Decorator 추가 : 

     ```
     @require_http_methods(['GET', 'POST'])
     @require_POST
     ```

     



## Widget 으로 커스터마이징

- `forms.py` 에 Form 클래스 안에서 수정

  ```python
  class ArticleForm(forms.ModelForm):
      # widget 사용하여 속성 값 부여 및 변경
      # 변수명은 models 에 있는 fields 명을 꼭 사용해야함!
      title = forms.CharField(
          label='제목',
          widget=forms.TextInput(
              # Dictionary 구조 사용하여 속성값 주기
              attrs={
                  'class': 'my-title',
                  'placeholder': '제목을 입력하세요.',
              }
          )
      )
      content = forms.CharField(
          label='내용',
          widget=forms.Textarea(
              attrs={
                  'class': 'my-content',
                  'placeholder': '내용을 입력하세요',
                  'cols': 30,
                  'rows': 5,
              }
          )
      )
  
      # Meta : ArticleForm 클래스에 대한 정보를 작성하는 곳 ( Django 문법 )
      class Meta:
          model = Article
          fields = ['title', 'content', ]  
          # 전체 fields 설정 = '__all__'
  ```

  

## Form 커스터마이징

- templates 에서 form 을 직접 쪼개서 사용

  ```html
  <h3>Version 1</h3>
          {{ form.as_p }}
  
          <hr>
  
          <h3>Version 2</h3>
          <div>
              {{ form.title.errors }}
              {{ form.title.label_tag }}
              {{ form.title }}
          </div>
          <div>
              {{ form.content.errors }}
              {{ form.content.label_tag }}
              {{ form.content }}
          </div>
  
          <hr>
  **Form Control**
          <h3>Version 3</h3>
          {% for field in form %}
              <div class="form-group">
              {{ field.label_tag }}
              {{ field }}
              </div>
          {% endfor %}
  ```

- `forms.py` 의 widget에 class에   `form-control` 추가해서 변경해줌

  ```python
  title = forms.CharField(
          label='제목',
          widget=forms.TextInput(
              # Dictionary 구조 사용하여 속성값 주기
              attrs={
                  'class': 'my-title form-control',
                  'placeholder': '제목을 입력하세요.',
              }
          )
      )
  ```

- 참고 : https://getbootstrap.com/docs/4.5/components/forms/#form-controls



### Library 다운받아 사용

**bootstrap4 다운로드해서 사용 :** 

- `pip install django-bootstrap4`

- setting.py - INSTALLED_APP에 추가 : 'bootstrap4'

- template 에 load 시켜주기 : `{% load bootstrap4 %}`

  ```html
  {% extends 'base.html' %}
  {% load bootstrap4 %}
  
  {% block content %}
      <h1>UPDATE</h1>
      <!-- form 에 action 이 없으면 본인의 주소로 다시 연결되므로 생략 가능 -->
      <form action="" method="POST">
          {% csrf_token %}
      {% bootstrap_form form %}
      {% buttons %}
          <button type="submit" class="btn btn-primary">Submit</button>
      {% endbuttons %}
      </form>
  {% endblock  %}
  ```

- 참고 : https://pypi.org/project/django-bootstrap4/







### review_django_form_01

- 명령어

  ```
  cd review_django_form01
  django_admin startproject form
  cd form
  python manage.py runserver(확인)
  python manage.py startapp feeds
  ```

- setting 변경해주기

  ```python
  INSTALLED_APPS = [
      'feeds', # 추가
  ]
  # 언어 및 시간 변경까지
  ```

  

- 1. templates 나누어주기

  - templates
    - project 에
      - templates 만 추가
    - app 에
      - templates/feeds

  

  2. `urls.py` 작성

  - project

    ```python
    from django.urls import path, include # include추가
    urlpatterns = [
      	.
      	.	# feed/ 추가
        path('feeds/', include('feeds.urls')), 
    ]
    ```

  

  - app : 기본 import 및 **app_name 추가**

    ```python
    from django.urls import path
    from . import views
    
    app_name = 'feeds'
    urlpatterns = [
    
    ]
    ```



- `views.py` 에 미리 redirect 를 import 해주기

  ```python
  from django.shortcuts import render, redirect
  ```



- Templates 상속시키기

  - `base.html` 작성 : {% block %} 처리, Bootstrap 적용

    ```html
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        {% block css %}
        {% endblock  %}
    </head>
    <body>
        {% block content %}
        {% endblock  %}
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    </body>
    </html>
    ```

    

  - `setting.py` - `TEMPLATES` - `DIRS` 에   추가

    ```python
    os.path.join(BASE_DIR, 'form', 'templates')
    ```



- ORM 시작 - `model.py` 작성

  ```
  
  ```

  

