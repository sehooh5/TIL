# either.io

- `either.io`  site 참고한 프로그램

1. `either` project 생성

2. `accounts` , `questions` app 생성

3. 각자 templates 생성 후 `base.html` 작성 및 bootstrap 설정

4. 프로젝트 `urls.py` 작성하여 각 app include 후 각자 `urls.py` 작성

5. signup 기능 `views.py` 작성 : <mark>customizing 추가</mark>하였음

   - 기본 : User - AbstractUser - AbstractBaseUser 식으로 상속 되어있고 UserCreationForm 도 연결되어서 사용이 가능했었다

   - 1안 : AbstractUser 를 상속받아 CustomUser 생성, UserCreationForm 또한 새로 만들어줘서 사용 (<mark>Django는 CustomUser 를 사용하길 권장</mark>)

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

       

   - 2안 : 저장하기 위한 새로운 class 를 만들어서 1 : 1 관계를 형성하여 만듬

6. 