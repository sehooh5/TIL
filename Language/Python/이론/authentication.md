# authetication

참고 : [04_django_auth](https://github.com/sehooh5/TIL/tree/master/Python/04_django_auth/crud)

- 기본적으로 Create 와 비슷하다
- `models.py`  를 작성하지 않은채 `migrate` 를 실행하면 auth 가 자동으로 생긴다
  - django 는 이미 AbstractUser 라는 모델을 만들어서 사용하고 있다(git 참고)
- `forms.py` 또한 작성 되어있는 것을 참고하여 사용한다
  - [django doc 참고 : **class** **UserCreationForm**](https://docs.djangoproject.com/en/1.8/_modules/django/contrib/auth/forms/)
  - `from django.contrib.auth.forms import UserCreationForm` : 장고가 만들어 놓은 form 을 사용한다
- i18n : internationlization

- cookies - sessionid (작업자 도구에서 확인 가능)
  - 원래는 브라우저에 저장햇는데 그것은 cookies / 서버에 저장하면 session
  - sqlite3 - database - SQLITE EXPLORER - django_session : session_key 확인
  - cookies 의 sessionid 와 session_key 비교해서 똑같으면 good
- base.html 에 항상 사용할 것 적용
- signup 만 저장하는 기능이고 signin/logout 은 session 활용





## 로그인을 해야만 사용가능하게 설정하기

- 원하는 views.py의 함수에 `decorator` 추가 : `@login_required`

- index에 달아주면 처음에 로그인을 해야만 index로 출입이 가능하다

  ```python
  from django.contrib.auth.decorators import login_required
  ```



# 04_django_auth

### 1개의 프로젝트(crud) 2개의 기능 app(accounts, todos)로 구성

- 회원가입과 로그인을 위해 Django가 제공하는 Form

  ```python
  from django.contrib.auth.forms import 
  										UserCreationForm, AuthenticationForm
  # UserVreationForm : 회원가입 Form
  # AuthenticationForm : 로그인 Form
  ```

- 로그인, 아웃을 위해 Django 가 제공하는 함수

  ```python
  from django.contrib.auth import login as auth_login
  from django.contrib.auth import logout as auth_logout
  ```

  

