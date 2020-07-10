# 05_either

- `either.io`  site 참고한 프로그램
- 참고 : [05_either](https://github.com/sehooh5/TIL/tree/master/Python/05_either/either)

1. `either` project 생성

2. `accounts` , `questions` app 생성

3. 각자 templates 생성 후 `base.html` 작성 및 bootstrap 설정

4. 프로젝트 `urls.py` 작성하여 각 app include 후 각자 `urls.py` 작성

5. signup 기능 `views.py` 작성 : <mark>customizing 추가</mark>하였음

   - 기본 : User - AbstractUser - AbstractBaseUser 식으로 상속 되어있고 UserCreationForm 도 연결되어서 사용이 가능했었다

   - 1안 : AbstractUser 를 상속받아 CustomUser 생성, UserCreationForm 또한 새로 만들어줘서 사용 (<mark>Django는 처음부터 CustomUser 를 사용하길 권장</mark>)

     - `models.py` 에 새로운 CustomUser 사용

     - ```python
       from django.contrib.auth.models import AbstractUser
       # 기존 User 를 대체할 User 생성
       class User(AbstractUser):  
           phone = models.CharField(max_length=20)
       ```

     - `settings.py` 맨 아래 추가하여 원래 User 를 대체

       ```python
       AUTH_USER_MODEL = 'accounts.User'
       ```

     - `forms.py` 를 작성해주는데 UserCreationForm 을 상속하여 사용

       ```python
       from django.contrib.auth.fomrs import UserCreationForm
       from .models import User
       from django.contrib.auth import get_user_model
       
       # get_user_model() => AUTH_USER_MODEL 에 적용시킨 모델 클래스를 반환해줌
       class CustomUserCreationForm(UserCreationForm):
          class Meta: # Meta 로 Form의 방향을 새User로 지정
               model = get_user_model()
       ```

     - 작성한 CustomUserCreationForm을 `views.py`에서 사용

   - 2안 : 저장하기 위한 새로운 class 를 만들어서 1 : 1 관계를 형성하여 만듬

6. choices 기능 사용하기

   ![img](https://user-images.githubusercontent.com/58541635/85501571-9ef0a080-b620-11ea-9f08-f19e1b01215b.png)

   - `models.py` 작성

     ```python
     from django.db import models
     from django.conf import settings
     # settings 에 AUTH_USER_MODEL 이 있다 이걸 user 정보로 사용
     
     
     # 사용자가 질문하고 대답할 수 있는 페이지를 만들것
     class Question(models.Model):
         title = models.CharField(max_length=100)
         answer_a = models.CharField(max_length=100)
         answer_b = models.CharField(max_length=100)
         user = models.ForeignKey(settings.AUTH_USER_MODEL,
                                  on_delete=models.CASCADE)
     
     
     # ForeignKey 가 2개인 모델
     # User(1) - Question, Answer(N)
     # Question(1) - Answer(N)
     class Answer(models.Model):
         choice = models.CharField(max_length=100)  # 선택지를 두 개만 주는 기능
         question = models.ForeignKey(Question, on_delete=models.CASCADE)  # 1-N관계
         user = models.ForeignKey(settings.AUTH_USER_MODEL,
                                  on_delete=models.CASCADE)
     
     ```

     





### include를 사용하여 해당 html(반대방법)

- 작성 후 불러와서 include 로 적용

  ```python
  {% include 'nav.html' %}
  ```



### url_name 을 가져와 사용하는 방법

```python
# html파일에서
    {% if request.resolver_match.url_name == 'signup' %}
```



### 로그인 시 이미 로그인 되어있으면 index 로 , 로그인 user 정보 (get_user())

```python
# views.py 에서
if request.user.is_authenticated: # 인증되었으면 index 로 넘겨
        return redirect('questions:index')
    if request.method == 'POST':
        form = AuthenticationForm(request, request.POST)
        if form.is_valid():
            user = form.get_user() # user 정보 가져요기
            auth_login(request, user)
            return redirect('questions:index')
```



### 