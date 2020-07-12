from django.contrib.auth.forms import UserCreationForm
from django.contrib.auth import get_user_model
from django import forms
from .models import User
# get_user_model => AUTH_USER_MODEL에 적용시킨 모델 클래스

class CustomUserCreationForm(UserCreationForm):
    username = forms.CharField(
        label='사용자 ID', 
        required=True,
        widget=forms.TextInput(
            attrs={
                'class': 'form-control',
                'placeholder': '아이디를 입력해주세요.',
                'ime-mode': 'disabled'
            }
        )
    )
    password1 = forms.CharField(
        label='비밀번호', 
        required=True,
        widget=forms.PasswordInput(
            attrs={
                'class': 'form-control',
                'placeholder': '비밀번호를 입력해주세요.',
                'ime-mode': 'disabled'
            }
        )
    )
    password2 = forms.CharField(
        label='비밀번호 재입력',
        required=True,
        widget=forms.PasswordInput(
            attrs={
                'class': 'form-control',
                'placeholder': '비밀번호를 다시 입력해주세요.',
                'ime-mode': 'disabled'
            }
        )
    )

    email = forms.EmailField(
        label='이메일', 
        required=True,
        widget=forms.EmailInput(
            attrs={
                'class': 'form-control',
                'placeholder': 'aaa@naver.com'
            }
        )    
    )

    class Meta:
        # 연결하고 싶은 모델이 들어간다
        model = get_user_model()
        fields = ('username', 'password1', 'password2', 'email', )
