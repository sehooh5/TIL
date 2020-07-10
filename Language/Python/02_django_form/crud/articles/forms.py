from django import forms
from .models import Article


# class ArticleForm(forms.Form):
#     title = forms.CharField(max_length=20)
#     # textField 가 존재하지 않는다. 대신 widget 사용
#     content = forms.CharField(widget=forms.Textarea)


class ArticleForm(forms.ModelForm):
    # widget 사용하여 속성 값 부여 및 변경
    # 변수명은 models 에 있는 fields 명을 꼭 사용해야함!
    title = forms.CharField(
        label='제목',
        widget=forms.TextInput(
            # Dictionary 구조 사용하여 속성값 주기
            attrs={
                'class': 'my-title form-control',
                'placeholder': '제목을 입력하세요.',
            }
        )
    )
    content = forms.CharField(
        label='내용',
        widget=forms.Textarea(
            attrs={
                'class': 'my-content form-control',
                'placeholder': '내용을 입력하세요',
                'cols': 30,
                'rows': 5,
            }
        )
    )

    # Meta : ArticleForm 클래스에 대한 정보를 작성하는 곳 ( Django 문법 )
    class Meta:
        model = Article
        fields = ['title', 'content', ]  # 전체 fields 설정 = '__all__'
        excludes = ['title', ]  # 해당 fields 제외하고 사용하기
