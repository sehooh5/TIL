from django.shortcuts import render, redirect
from .forms import PostForm
from django.contrib.auth.decorators import login_required

# Create your views here.


def index(request):
    return render(request, 'posts/index.html')


@login_required
def create(request):
    if request.method == 'POST':
        # request 값에서 FILES를 찾아주는 request.FILES
        form = PostForm(request.POST, request.FILES)
        # 에러 확인하면, file data가 포스트에 없는 거 확인 할 수 있는데, 그래서 두번째 아규먼트로 request.FILES 파일 데이터 저장하는 부분 넣어주어야함
        if form.is_valid():
            post = form.save(commit=False)
            post.user = request.user
            # user 정보를 넣을때 문제가 되는 것이, 로그인 한 상태가 아니라면 anonymouseuser 떠서 에러 뜨는데,
            # 이것을 방지해주기 위해 login_required decorator 추가 시켜줌
            post.save()
            return redirect('posts:index')

    else:
        form = PostForm()
    context = {
        'form': form
    }
    return render(request, 'posts/form.html', context)
