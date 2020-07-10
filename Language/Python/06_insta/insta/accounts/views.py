from django.shortcuts import render, redirect, get_object_or_404
from django.contrib.auth.forms import UserCreationForm, AuthenticationForm
from .forms import CustomUserCreationForm
from django.contrib.auth import login as auth_login
from django.contrib.auth import logout as auth_logout
from django.contrib.auth import get_user_model
from django.contrib.auth.decorators import login_required


# Create your views here.
def signup(request):
    if request.user.is_authenticated:
        return redirect('posts:index')

    if request.method == 'POST':
        form = CustomUserCreationForm(request.POST, request.FILES)
        if form.is_valid():
            user = form.save()
            auth_login(request, user)
            return redirect('posts:index')
    else:
        form = CustomUserCreationForm()
    context = {
        'form': form,
    }
    return render(request, 'accounts/form.html', context)


def login(request):
    if request.user.is_authenticated:
        return redirect('posts:index')

    if request.method == 'POST':
        form = AuthenticationForm(request, request.POST)
        if form.is_valid():
            user = form.get_user()
            auth_login(request, user)
            return redirect('posts:index')

    else:
        form = AuthenticationForm()
    context = {
        'form': form
    }
    return render(request, 'accounts/form.html', context)


def logout(request):
    auth_logout(request)
    return redirect('accounts:login')


def profile(request, username):
    # User = get_user_model()
    # user_profile = User.objects.filter(username=username)

    user_profile = get_object_or_404(get_user_model(), username=username)
    context = {
        'user_profile': user_profile,
    }
    return render(request, 'accounts/profile.html', context)


@login_required
def follow(request, user_pk):
    # 누가  누구를  팔로우 할 것인가
    me = request.user  # 누가 : 로그인한 사람
    you = get_object_or_404(get_user_model(), pk=user_pk)

    if me == you:
        return redirect('posts:index')

    if me in you.follower.all():  # you 의 자동 생성된 follower에 속해있으면
        # follower 해제
        you.follower.remove(me)  # = me.follow.remove(you)
    else:
        # follower 추가
        you.follower.add(me)  # = me.follow.add(you)
    return redirect('accounts:profile', you.username)
