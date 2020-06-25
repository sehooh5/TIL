# File upload (N-N)



- N-N 관계설정
- 사진 업로드
- 좋아요



## [Insta 만들기]

1. git pull 해서 06_insta

2. migrate

3. runserver ,  /posts 확인

4. <models.py > - posts

   ```
   from django.db import models
   from django.conf import settings
   
   # Create your models here.
   class Post(models.Model):
       content = models.CharField(max_length=200)
       image = models.ImageField()
       user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE)
       created_at = models.DateTimeField(auto_now_add=True)
   ```

   

- 작성하고 저장하면 에러 뜨는데, 

  **pip install pillow**  라이브러리 설치

5. makemigrations , migrate

6. <nav.html> - insta 에 글쓰기 링크 추가하기 

   ```
   <nav class="nav d-flex justify-content-center my-4">
     {% if user.is_authenticated %}
       <a class="nav-link" href="{% url 'posts:create' %}">글쓰기</a>
       <a class="nav-link" href="{% url 'accounts:logout' %}">로그아웃</a>
   ```

   

7. <urls.py> - post에 글쓰기 path 설정

   ```
    path('create/', views.create, name='create'),
   ```

   

8. <forms.py>-post에  form 작성

   ```
   from django import forms
   from .models import Post
   
   class PostForm(forms.ModelForm):
       class Meta:
           model = Post
           #fields = '__all__'
           exclude = ('user',)
   ```

   

9. <views.py> - post 글쓰기  함수 작성

   ```
   def create(request):
       if request.method == 'POST':
           pass
       else:
           form = PostForm()
       context = {
           'form': form
       }
       return render(request, 'posts/form.html', context)
   ```

   

10. <form.html> - post  출력되는 부분 작성

    ```
    {% extends 'base.html' %}
    {% load bootstrap4 %}
    {% block content %}
        <form action="" method="POST" >
            {% csrf_token %}
            {% bootstrap_form form %}
            <!--부트스트랩 사용해서 form 출력-->
            <button class ='btn btn-primary'>저장</button>
        </form>
    {% endblock  %}
    ```

    

11. <views.py>-post 에 POST 요청 부분에  raise 구문 추가

    raise? 오류 뜨는거 확인할라고 추가하심

    ```
    def create(request):
        if request.method == 'POST':
            raise 
            #일부러 오류뜨는거 확인할라고 작성하신것
            pass
        else:
            form = PostForm()
        context = {
            'form': form
        }
        return render(request, 'posts/form.html', context)
    ```

    - raise 구문 작성후,  글쓰고  파일 업로드 해보고 오류를 확인하면 ,

      FILES 부문에 No Files data 뜸

      ![캡처](https://user-images.githubusercontent.com/63486972/85642189-bd5fa600-b6cb-11ea-8ae3-341079bb9f37.PNG)

12. <form.html> - post 에 enctype="multipart/form-data" 추가 후 글 작성후, 에러 확인하면

    파일 data 전송된거 확인할 수 있음

    ```
    {% extends 'base.html' %}
    {% load bootstrap4 %}
    {% block content %}
        <form action="" method="POST" enctype="multipart/form-data">
        <!--enctype="multipart/form-data" : 파일 data 전송-->
            {% csrf_token %}
            {% bootstrap_form form %}
            <!--부트스트랩 사용해서 form 출력-->
            <button class ='btn btn-primary'>저장</button>
        </form>
    {% endblock  %}
    ```

    

13. Media 저장용 `settings.py` 내용 추가

    ```
    # url 이름 정해주는 것(사람들에게 보여지는)
    MEDIA_URL = '/media/'
    # 실제 저장 장소
    MEDIA_ROOT = os.path.join(BASE_DIR, 'media')
    ```

    

14. `views.py` 에 deco 추가 및 create 수정

    ```python
    from django.contrib.auth.decorators import login_required
    
    
    
    @login_required
    def create(request):
        if request.method == 'POST':
            # request 값에서 FILES를 찾아주는 request.FILES
            form = PostForm(request.POST, request.FILES)
            # 에러 확인하면, file data가 포스트에 없는 거 확인 할 수 있는데, 그래서 두번째 아규먼트로 request.FILES 파일 데이터 저장하는 부분 넣어주어야함
            if form.is_valid():
                post = form.save(commit=False)
                post.user = request.user
                # user 정보를 넣을때 문제가 되는 것이, 로그인 한 상태가 아니라면 anonymouseuser 떠서 에러 뜨는데,
                # 이것을 방지해주기 위해 login_required decorator 추가 시켜줌
                post.save()
                return redirect('posts:index')
    
        else:
            form = PostForm()
        context = {
            'form': form
        }
        return render(request, 'posts/form.html', context)
    ```

15. media 에 대한 경로 설정 : `/insta/urls.py`

    - 기본적으로  `http://127.0.0.1:8000/media/KakaoTalk.png` 이렇게 뜸
    - ~/media/ 구조이면 실행을 요청하게 경로를 설정해줘야 한다
    - 이제 admin 페이지에서 파일을 누르면 브라우저에서 확인이 가능하다

    ```python
    from django.conf.urls.static import static
    from django.conf import settings
    
    urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
    
    ```

    

