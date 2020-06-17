from django import forms
from .models import Article


# class ArticleForm(forms.Form):
#     title = forms.CharField(max_length=20)
#     # textField 가 존재하지 않는다. 대신 widget 사용
#     content = forms.CharField(widget=forms.Textarea)


class ArticleForm(forms.ModelForm):

    # Meta : ArticleForm 클래스에 대한 정보를 작성하는 곳 ( Django 문법 )
    class Meta:
        model = Article
        fields = ['title', 'content', ]
