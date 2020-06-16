from django.shortcuts import render, redirect
from .models import Article

# Create your views here.


def index(request):
    articles = Article.objects.all()
    context = {
        'articles': articles,
    }
    return render(request, 'articles/index.html', context)


def new(request):
    return render(request, 'articles/new.html')

# GET 메서드는 url이 변경되지 않는다
# ex) index 로 돌아가야하는데 계속 create url을 보냄
# POST 를 사용해야한다


def create(request):
    # 1. new 에서 보낸 데이터 받기
    title = request.POST.get('title')
    content = request.POST.get('content')

    # 2. db 에 저장 // 저장 전에 데이터가 유효한지 검증하고자하면 (3)번 방법 불가
    # (1)
    # article = Article()
    # article.title = title
    # article.content = content
    # article.save()

    # (2)
    article = Article(title=title, content=content)
    article.save()

    # (3)
    # Article.objects.create(title=title, content=content)

    return redirect('articles:detail', article.pk)


def detail(request, pk):
    # pk1 : Article 이 갖고있는 pk  /   pk2: url 로 받아온 variable pk
    article = Article.objects.get(pk=pk)
    context = {
        'article': article,
    }
    return render(request, 'articles/detail.html', context)

# 요청이 GET인지 POST 인지 확인하는 방법 추가


def delete(request, pk):
    print(request.method)
    article = Article.objects.get(pk=pk)
    if request.method == 'POST':
        article.delete()
        return redirect('articles:index')
    else:
        return redirect('articles:detail', article.pk)


# 기본적으로 create 의 new 와 비슷한 기능
def edit(request, pk):
    article = Article.objects.get(pk=pk)
    context = {
        'article': article,
    }
    return render(request, 'articles/edit.html', context)


def update(request, pk):
    article = Article.objects.get(pk=pk)
    # 1. edit 에서 보낸 데이터 받기
    title = request.POST.get('title')
    content = request.POST.get('content')
    # 2. 받아오는 값으로 변경 후 저장
    article.title = title
    article.content = content
    article.save()
    return redirect('articles:detail', article.pk)
