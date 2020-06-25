# File upload (N-N)



- N:M 관계설정 (좋아요)
- 사진 업로드
- 좋아요



## [Insta 만들기]

1. git pull 해서 06_insta

2. migrate

3. runserver ,  /posts 확인

4. `models.py` - Posts

   ```python
   from django.db import models
   from django.conf import settings
   
   # Create your models here.
   class Post(models.Model):
       content = models.CharField(max_length=200)
       image = models.ImageField()
       user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE)
       created_at = models.DateTimeField(auto_now_add=True)
   ```

   

- 작성하고 저장하면 에러 뜨는데, **pip install pillow**  라이브러리 설치


5. makemigrations , migrate

6. <nav.html> - insta 에 글쓰기 링크 추가하기 

   ```html
   <nav class="nav d-flex justify-content-center my-4">
     {% if user.is_authenticated %}
   <a class="nav-link" href="{% url 'posts:create' %}">글쓰기</a>
   <a class="nav-link" href="{% url 'accounts:logout' %}">로그아웃</a>
   ```

   

7. <urls.py> - post에 글쓰기 path 설정

   ```python
    path('create/', views.create, name='create'),
   ```

   

8. <forms.py>-post에  form 작성

   ```python
   from django import forms
   from .models import Post
   
   class PostForm(forms.ModelForm):
       class Meta:
           model = Post
           #fields = '__all__'
           exclude = ('user',)
   ```

   

9. <views.py> - post 글쓰기  함수 작성

   ```python
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

    ```html
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

    ```python
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

    ```html
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

    ```python
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

    

16. card 형식의 html 만들어서 include 해줌 : card.html

    ```
    <div class="card" style="width: 18rem;">
      <img src="..." class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">Card title</h5>
        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
        <a href="#" class="btn btn-primary">Go somewhere</a>
      </div>
    </div>
    ```



17. views.py 에 index를 Post 모델 가져올 수 있게 변경

    ```python
    def index(request):
        posts = Post.objects.all()
        context = {
            'posts': posts
        }
        return render(request, 'posts/index.html', context)
    ```

18. index.html 수정 : for 문

    ```python
    {% extends 'base.html' %}
    {% block content %}
      {% for post in posts %}
          {% include 'posts/card.html' %}
      {% endfor %}
    {% endblock %}
    ```

19. card.html 수정 : post 내용 및 이미지 경로

    ```python
    <div class="card" style="width: 18rem;">
    <!--***post.image(column 명).url = /media/파일경로 가 나옴 -->
      <img src="{{ post.image.url }}" class="card-img-top" alt="...">
      <div class="card-body">
        {% comment %} <h5 class="card-title">Card title</h5> {% endcomment %}
        <p class="card-text">{{ post.content }}</p>
        <a href="#" class="btn btn-primary">Go somewhere</a>
      </div>
    </div>
    ```

20. bootstrap **Grid** 적용

    - row 설정을 보여주는 페이지에서 설정 한 후
    - col 설정을 칸을 차지할 내용에 설정해줌

    ```python
    # index.html - row 설정
    <div class="row"> 
    
    # card.html - col 설정
    <div class="card col-6 m-3" style="width: 18rem;"> 
    ```

21. 인스타그램처럼 꾸미기

    - font awesome 사이트에서 `base.html` 에 추가해주기(icon 사용)

      ```html
      <script src="https://kit.fontawesome.com/a43482d4c5.js" crossorigin="anonymous"></script>
      ```

    - card.html

    ```html
    <div class="card col-6 m-3" style="width: 18rem;">
    <!--post.image(column 명).url = /media/파일경로 가 나옴 -->
      <h5 class="card-header">
        <img src="https://lh3.googleusercontent.com/proxy/1k4GKg9JmioAHSgOuRZ64CHT3jcS5iixQnZvJTGExli9CygbrqPaYH5DevH5fa3SqlNd-JWiHrg7FQCjKVkqpvWhblo6cX4qOCHfOdr7erk9dVrBK_uCgkF1c5JHOGljVeUZ1n9M4ZWCdtU07AGi36UvCn51GVoXPWijZlumjgB61ZgL0ei_m7OAQLJmdKWIX-Ozy8-mngo8pPsc-R9jSHK98yM6mUxTBkj7jlWz5T9yuYsUX198JrsCuKUrzrbBoi71rZ84dfLBGz7GP-LIJj0RgWOA80DWb_vgI-BR-PV5RRnBEAvaNDWApXEdf5JdutZt4ZT1iRvzqN-C-2WsbJlctaxc4Jy0ZZCdFf2-zMDB9DdJOB-1SQtDO2DlcFEaFs5_HPA_rQSKV4IyQd51GC_WB0WykOM" width="50px" height="50px" class="rounded-circle">
        {{ post.user.username }}
      </h5>
      <img src="{{ post.image.url }}" class="card-img-top" width="300px" height="300px">
      <div class="card-body">
        {% comment %} <h5 class="card-title">Card title</h5> {% endcomment %}
        <p class="card-text">{{ post.content }}</p>
        <p class="card-text">{{ post.created_at }}</p>
      </div>
    </div>
    ```

22. **image resizing** : django image kit

    - 현업에서는 각 화면별로 이미지를 리사이징해서 분류해놓고 사용한다

    ```python
    1. download
    pip intall django-imagekit
    
    2. settings.py 수정 : INATALLED_APP 추가
    'imagekit',
    
    3. models.py 에 추가, image field 변경
    from imagekit.models import ProcessedImageField
    from imagekit.processors import ResizeToFill, ResizeToFit, ResizeCanvas, ResizeToCover # 다양한 종류의 설정
    
    image = ProcessedImageField(upload_to='media',
                                    processors=[ResizeToFill(500, 500)],# ResizeToFill : 자르고 사이즈 맞춰줌
                                    format='JPEG',
                                    options={'quality': 80})
    ```

23. **좋아요 기능** : 버튼을 누르면 링크를 통해 <mark>**N:M 관계**</mark> 설정

    - *N:M= (1:N) + (M:1)*
    - 실제 django 는 새로운 테이블을 만들어서 처리한다(중계 모델)
      - 컬럼명 : user_id, post_id

    ```python
    1. models.py 수정 : 좋아요를 저장한 사람에 대한 칼럼 추가
    2. 여기서 두 user 의 post_set 의 이름이 겹쳐서 이름을 변경해준다
    
    from django.db import models
    from django.conf import settings
    from imagekit.models import ProcessedImageField
    from imagekit.processors import ResizeToFill, ResizeToFit, ResizeCanvas, ResizeToCover
    
    # < 충돌이 일어나는 이유 >
    # user, like_user 가 모두 post_set 을 만들어 User 와 연결하려한다
    # 1. post_set from user(FK) : 어떤 유저가 작성한 글들
    # 2. post_set from like_user(M2M) : 어떤 유저가 좋아요를 누른 글들 -> like_posts
    # 두 개 이름이 같아서 충돌이 난다
    # < 충돌 변경해주는 방법 >
    # 두 개의 이름을 변경해준다
    
    
    class Post(models.Model):
        content = models.CharField(max_length=200)
        #image = models.ImageField()
        # 작성한 사람을 저장
        user = models.ForeignKey(settings.AUTH_USER_MODEL,  # user 정보 가져오는 곳
                                 on_delete=models.CASCADE)
        created_at = models.DateTimeField(auto_now_add=True)
        image = ProcessedImageField(upload_to='media',
                                    processors=[ResizeToCover(500, 500)],
                                    format='JPEG',
                                    options={'quality': 80})
        # 좋아요 버튼을 누른 사람들을 저장
        # 여기서 like_posts는 User에 저장되는 컬럼명이 된다(기존 post_set)
        like_users = models.ManyToManyField(
            settings.AUTH_USER_MODEL, related_name='like_posts')
    
        class Meta:
            # 정렬해주는 기능, 기본이 id 순으로 정렬하는데 - 값 주면 역순
            ordering = ['-id']
    
    ```

24. 좋아요 버튼을 누르고 링크를 적용 시켜줌 : card.html 

    ```python
    <a href="{% url 'posts:like' post.id %}"><i class="far fa-heart"></i></a>
    ```

25. 좋아요 함수 like 로직 만들기 : views.py

    ```python
    def like(request, post_pk):
        # 누가? 에 대한 정보
        user = request.user
        # 몇 번 글? 에 대한 정보
        post = get_object_or_404(Post, pk=post_pk)
        # user.like_posts = user 가 좋아요 버튼을 누른 게시물들
        # user.like_users = post에 좋아요 버튼을 누른 유저들
        # x in y(list) = x가 y 에 속하면 True
        if post in user.like_posts.all():
            # 이미 좋아요를 누른경우 -> 제거
            user.like_posts.remove(post)
        else:
            # 아직 좋아요를 안누른경우 -> 추가
            user.like_posts.add(post)
        return redirect('posts:index')
    ```

26. 좋아요 아이콘 눌림과 안눌림 구분하여 변경 : card.html

    ```python
    {% if post in user.like_posts.all %}
          <a href="{% url 'posts:like' post.id %}"><i class="fas fa-heart" style="color:red"></i></a>
        {% else %}
          <a href="{% url 'posts:like' post.id %}"><i class="far fa-heart" style="color:black"></i></a>
        {% endif %}
    ```

27. Follow기능 추가

    ```python
    
    ```

28. views.py 에 index를 Post 모델 가져올 수 있게 변경

    ```python
    
    ```

29. views.py 에 index를 Post 모델 가져올 수 있게 변경

    ```python
    
    ```

30. views.py 에 index를 Post 모델 가져올 수 있게 변경

    ```python
    
    ```

31. views.py 에 index를 Post 모델 가져올 수 있게 변경

    ```python
    
    ```

32. 