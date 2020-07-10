from django.db import models

# Create your models here.


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
