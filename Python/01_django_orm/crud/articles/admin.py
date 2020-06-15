from django.contrib import admin
from .models import Article

# Register your models here.


class ArticleAdmin(admin.ModelAdmin):
    list_display = ['pk', 'title', 'content', 'created_at']
    list_editable = ['title']  # admin이 관리페이지에서 바로 수정할 수 있음


# 1. models.py 의 Article class
# 2. admin.py(현재) 의 ArticleAdmin class
admin.site.register(Article, ArticleAdmin)  # admin site 에 등록
