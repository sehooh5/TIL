# 관계설정

- 댓글 기능 실행
- 1-N 관계 설정 
  1. 다른 모델의 PK를 FK로 가져오기
  2. 관계설정 테이블도 만들어주기



## models.py 작성

- 댓글 기능 추가

```python
from django.db import models

# Create your models here.


class Article(models.Model):
    title = models.CharField(max_length=20)
    content = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)


class Comment(models.Model):
    content = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    # 첫번째 변수 : 원하는 클래스, 두번째 변수(필수) : 위에 있는 클래스 지칭
    article = models.ForeignKey(Article, on_delete=models.CASCADE)

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



## admin 수정

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

