# Django Form

- https://docs.djangoproject.com/ko/2.1/topics/forms/ : 참고자료 (2.1/ko)

- 모델 작성하는 형식과 거의 비슷하다

  ```python
  from django import forms
  
  class NameForm(forms.Form):
      your_name = forms.CharField(label='Your name', max_length=100)
  ```

- <mark>**코드의 반복을 줄여준다**</mark>

## Model Form

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

- create 기능과 new기능을 합쳐서 줄여주기

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
    - urls - new 삭제
    - views - new 삭제
    - templates - 삭제



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

  



