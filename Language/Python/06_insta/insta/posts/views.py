from django.shortcuts import render, redirect, get_object_or_404
from .forms import PostForm
from .models import Post
from django.contrib.auth.decorators import login_required
from django.http import JsonResponse
# Create your views here.


def index(request):
    posts = Post.objects.all()
    context = {
        'posts': posts,
    }
    return render(request, 'posts/index.html', context)


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
        'form': form,
    }
    return render(request, 'posts/form.html', context)

# create 와 비슷하지만 조금 다르다


def like(request, post_pk):
    # 누가? 에 대한 정보
    user = request.user
    # 몇 번 글? 에 대한 정보
    post = get_object_or_404(Post, pk=post_pk)
    # user.like_posts = user 가 좋아요 버튼을 누른 게시물들
    # user.like_users = post에 좋아요 버튼을 누른 유저들
    # x in y(list) = x가 y 에 속하면 True
    if post in user.like_posts.all():
        # 이미 좋아요를 누른경우 -> 제거
        user.like_posts.remove(post)
        liked = False
    else:
        # 아직 좋아요를 안누른경우 -> 추가
        user.like_posts.add(post)
        liked = True
    context = {
        'msg': '좋아요 기능이 동작했습니다',
        'liked': liked,
    }
    return JsonResponse(context)  # html 응답을 Json 응답으로 변경해줌
