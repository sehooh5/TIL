# 1-N 관계설정

### 예제 및 실습 :  [03_django_many_to_one](https://github.com/sehooh5/TIL/tree/master/Python/03_django_many_to_one/crud) / [Practice_200618](https://github.com/sehooh5/TIL/tree/master/Python/Practice_200618/form)

- 댓글 기능 실행
- 1-N 관계 설정 
  1. 다른 모델의 PK를 FK로 가져오기
  2. 관계설정 테이블도 만들어주기
- **개인적인 주의점 정리**
  - <mark>models.py 추가해주면 다시 makemigrations 부터 해주기</mark>
  - <mark>1에서 가져오는 N에 사용하는 FK값은 1전체 값들을 받는 것 과 같으며, 우리가 기존에 FK로 사용하던 1의 ID값은 'XXX_id로 사용할 수 있다'</mark>
  - <mark>1에서 사용되는 N의 값들은 '000_set'을 가져와 사용할 수 있다</mark>
  - <mark>1-N 관계에서 1에게는 '000_set', N 에게는 'XXX_id 는 자동생성 된다'</mark>
    - <mark>여기서 '000', 'XXX' 는 각 상대방의 class 명 </mark>
  - <mark>어떤 코드를 작성할 때 통일성을 주도록 하면 좋다 (ex. url 작성 순서)</mark>
  - <mark>context 로 1을 전달받으면 '000_set'으로 N 값들을 가져올 수있다</mark>
  - <mark>작성한 Form 을 사용하려면 views.py의 context에 저장해서 사용하면 된다</mark>
  - <mark>Form에 작성된 내용을 저장하고 Create하려면 유효한지 확인해줘야 한다 : is_valid()</mark>
  - <mark>Form 에 작성 내용 중 exclude로 FK를 배제시켜뒀다</mark>
    - <mark>저장 시 사용 못하므로 저장하되 commit 전 상태로 보류시두는 commit=False 를 변수에 넣어주어 보류시켜두고</mark>
    - <mark>N의 FK값을 따로 넣어줘야한다 (ex.         comment.movie = movie)</mark>



## models.py 작성

- 댓글 기능 추가

```python
class Article(models.Model):
    title = models.CharField(max_length=20)
    content = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    # comment_set = 자동 생성


class Comment(models.Model):
    content = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    # 첫번째 변수 : 원하는 클래스, 두번째 변수(필수) : 위에 있는 클래스 지칭
    # on_delete = 1 - N 에 관계에서 1이 삭제 되었을 때 어떻게 할껀지?
    # CASCADE = 자연스럽게 지워버리는 기능
    article = models.ForeignKey(Article, on_delete=models.CASCADE)
    # article_id =  자동생성, 우리가 기존에 생각하던 그 FK
```



## shell_plus로 데이터 추가

```shell
In [8]: a= Article.objects.get(pk=1)   # 게시글 작성 후 불러오기                                                 

In [9]: a                                                                               
Out[9]: <Article: Article object (1)>

In [10]: a.title                                                                        
Out[10]: 'Hello'

In [11]: a.content                                                                      
Out[11]: 'hihi'

In [13]: a.created_at                                                                   
Out[13]: datetime.datetime(2020, 6, 22, 1, 9, 37, 505725, tzinfo=<UTC>)

## 댓글 작성
# 여기서 두번째 변수는 FK로 여기서는 Article객체 전체를 가져와 FK로 저장한다
# 여기서 article_id 를 자동으로 만들어주는데 우리가 기존에 사용하던 형태의 FK
# 여기서 Article객체는 comment_set이라는 Query Set을 만들어줘서
# 1-N 의 형태의 관계를 형성해준다
In [14]: Comment.objects.create(content="reply1_1", article=a)                          
Out[14]: <Comment: Comment object (1)> 

In [15]: Comment.objects.create(content="reply1_1", article=a)                          
Out[15]: <Comment: Comment object (2)>

In [16]: Comment.objects.create(content="reply1_2", article=a)                          
Out[16]: <Comment: Comment object (3)>

In [17]: c= Comment.objects.get(pk=1)                                                   

In [18]: c.content                                                                      
Out[18]: 'reply1_1'

In [19]: c.created_at                                                                   
Out[19]: datetime.datetime(2020, 6, 22, 1, 15, 55, 49123, tzinfo=<UTC>)

In [20]: c.article.title                                                                
Out[20]: 'Hello'
```



## templates 작성

```python
# 원래 CRUD 의 create 기능은 두개의 url 이 필요한데 기존에 한개로 합침
# 하지만 댓글의 Get 방식은 detail.html 에 보여주므로 상관없다
```



## urls, views 작성

```python
# urls.py
    path('<int:pk>/comments/create/', views.comment_create, name='comment_create'),
```

```python
# views.py
def comment_create(request, pk):
    article = Article.objects.get(pk=pk)
    content = request.POST.get('content')

    Comment.objects.create(article=article, content=content)
    return redirect('articles:detail', article.pk)
```



## admin 작성하여 확인하기

```python
from django.contrib import admin
from .models import Article, Comment #  Comment 추가


class ArticleAdmin(admin.ModelAdmin):
    list_display = ['pk', 'title', 'content', 'created_at', ]
    list_editable = ['title']


admin.site.register(Article, ArticleAdmin)
admin.site.register(Comment) # 추가

# admin site에 등록(register) 하겠다.

```



## 다시 template 수정

## (comment_set 으로 댓글 불러오기)

```python
{% for comment in article.comment_set.all %}
    <p>
      {{ comment.content }}
    </p>
  {% endfor %}
```



## delete 기능 추가(urls, views, template)

```python
# urls.py
path('<int:article_pk>/comment/<int:comment_pk>/delete',
         views.comment_delete, name='comment_delete'),
```

```python
# views.py
@require_POST
def comment_delete(request, article_pk, comment_pk):
    comment = Comment.objects.get(pk=comment_pk)
    comment.delete()
    return redirect('articles:detail', article_pk)
```

```python
# template datail.html
{% for comment in article.comment_set.all %}
    <p>
      {{ comment.content }}
      <form action="{% url 'articles:comment_delete' comment.pk %}" method="POST">
        {% csrf_token %}
        <button class="btn btn-danger">delete</button>
      </form>
    </p>
  {% endfor %}
```



## ModelForm을 사용하여 댓글 추가하기

## forms.py 추가

```python
class CommentForm(forms.ModelForm):

    class Meta:
        model = Comment
        fields = '__all__'
        exclude = ('article',) 
        # () = tuple (iterable 반복 가능하다 // 수정 불가능)
        # [] = list (iterable 반복 가능하다 // 수정가능)
```



## views

```python
# detail에
def detail(request, pk):
    article = Article.objects.get(pk=pk)
    form = CommentForm() # form 추가
    context = {
        'article': article,
        'form': form, # 추가
    }
    return render(request, 'articles/detail.html', context)
  
  
# comment_create 도 ModelForm을 사용한것으로 변환
@require_POST
def comment_create(request, pk):
    article = Article.objects.get(pk=pk)
    #{ content = request.POST.get('content')
    # Comment.objects.create(article=article, content=content)}
    form = CommentForm(request.POST)
    if form.is_valid():
        # commit=False : 새로나온 변수 (객체는 만들어져있지만 DB에 반영이 안된 상태)
        comment = form.save(commit=False)  # = Comment object (None)
        comment.article = article
        comment.save()
        return redirect('articles:detail', article.pk)
```