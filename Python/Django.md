# Django

- 서버를 개발하기 위한 프레임 워크



## Django 의 구조

- MVC - 소프트웨어 디자인 패턴
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

- 장고파일 만들기 : 

  ```
  django-admin startproject firstapp
  ```

- 서버 작동 : 

  ```
  python manage.py runserver
  ```



## 구성 내용

- `__init__.py` : 해당 디렉토리를 패키지화 해주는 파일 (ex. import firstapp 가능)
- `settings.py` : 모든 설정을 관리해주는 파일
- `urls.py` : 요청 url
- `wsgi.py` : 배포할 때 사용