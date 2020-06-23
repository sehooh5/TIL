from django.shortcuts import render, redirect
from django.contrib.auth.forms import UserCreationForm, AuthenticationForm
from django.contrib.auth import login as auth_login  # 이름 바꿔줄 수 있음
from django.contrib.auth import logout as auth_logout

# Create your views here.


def signup(request):
    if request.method == 'POST':
        form = UserCreationForm(request.POST)
        if form.is_valid:
            form.save()
            return redirect('accounts:login')
    else:
        form = UserCreationForm()  # ModelForm
        context = {
            'form': form,
        }
        return render(request, 'accounts/signup.html', context)


def login(request):
    if request.method == 'POST':
        # 1, 2 번  args 가 필수
        form = AuthenticationForm(request, data=request.POST)
        if form.is_valid():
            # auth_logint : 1. request, 2. user=get_user()
            auth_login(request, form.get_user())
            return redirect('todos:index')
    else:
        form = AuthenticationForm()
        context = {
            'form': form,
        }
        return render(request, 'accounts/login.html', context)


def logout(request):
    auth_logout(request)
    return redirect('todos:index')
