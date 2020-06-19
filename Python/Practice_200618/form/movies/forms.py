from django import forms
from .models import Movie


class MovieForm(forms.ModelForm):

    title = forms.CharField(
        label='영화명',
        widget=forms.TextInput(
            attrs={
                'class': 'title form-control',
                'placeholder': '영화 제목을 입력하세요.',
            }
        ),
    )
    title_en = forms.CharField(
        label='영화명(영문)',
        widget=forms.TextInput(
            attrs={
                'class': 'title-en form-control',
                'placeholder': '영화 영문 제목을 입력하세요.',
            }
        ),
    )
    audience = forms.IntegerField(
        label='누적 관객수',
        widget=forms.NumberInput(
            attrs={
                'class': 'audience form-control',
            }
        ),
    )
    open_date = forms.DateField(
        label='개봉일',
        widget=forms.SelectDateWidget(
            attrs={
                'class': 'date form-control',
                'placeholder': '영화 개봉일을 입력하세요.',
            }
        ),
    )
    genre = forms.CharField(
        label='장르',
        widget=forms.TextInput(
            attrs={
                'class': 'genre form-control',
                'placeholder': '영화 장르를 입력하세요.',
            }
        ),
    )
    watch_grade = forms.CharField(
        label='관람등급',
        widget=forms.TextInput(
            attrs={
                'class': 'grade form-control',
                'placeholder': '영화 관람등급을 입력하세요',
            }
        ),
    )
    score = forms.FloatField(
        label='평점',
        widget=forms.NumberInput(
            attrs={
                'class': 'score form-control',
                'placeholder': '영화 평점을 입력하세요.',
            }
        ),
    )
    poster_url = forms.CharField(
        label='포스터 이미지 URL',
        widget=forms.TextInput(
            attrs={
                'class': 'url form-control',
                'placeholder': '영화 이미지 url을 입력하세요.',
            }
        ),
    )
    description = forms.CharField(
        label='영화 소개',
        widget=forms.Textarea(
            attrs={
                'class': 'descript form-control',
                'placeholder': '영화 소개를 해주세요.',
            }
        ),
    )

    class Meta:
        model = Movie
        fields = '__all__'
