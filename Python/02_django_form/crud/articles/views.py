from django.shortcuts import render, redirect
from django.views.decorators.http import require_http_methods, require_POST
from .models import Article
from .forms import ArticleForm

# Create your views here.


def index(request):
    articles = Article.objects.all()
    context = {
        'articles': articles,
    }
    return render(request, 'articles/index.html', context)


# def new(request):
#     form = ArticleForm()
#     context = {
#         'form': form,
#     }
#     return render(request, 'articles/new.html', context)

# GET 메서드는 url이 변경되지 않는다
# ex) index 로 돌아가야하는데 계속 create url을 보냄
# POST 를 사용해야한다

# ModelForm 사용

@require_http_methods(['GET', 'POST'])
def create(request):

    # create 와 new를 한 개의 함수로 작성

    # POST 일 때=create 기능
    if request.method == 'POST':
        # ArticleForm 의 내용인 title, content 가 form 에 저장된다
        form = ArticleForm(request.POST)
        # 유효성 검사 : is_valid() = TRUE = 안전한 데이터
        if form.is_valid():
            # form form 쓸때 : model에 없을 때
            # title = form.cleaned_data.get('title') ~~~content 등등

            # model form 쓰면 자동 작성 후 save() 만
            article = form.save()
            return redirect('articles:detail', article.pk)
    # GET 일때(= new 기능) 혹은 다른 method
    else:
        form = ArticleForm()
    # 특이하게 이렇게 빼놨다 (유효성 검사 때문)
    # form1 : POST 로 들어갔지만 유효성 검사에서 튕겨저 나온 것, error message 포함
    # form2 : else 로 들어갔다 나온 GET의 form
    context = {
        'form': form,
    }
    return render(request, 'articles/create.html', context)


# def create(request):
#     # 1. new 에서 보낸 데이터 받기
#     title = request.POST.get('title')
#     content = request.POST.get('content')

#     # 2. db 에 저장 // 저장 전에 데이터가 유효한지 검증하고자하면 (3)번 방법 불가
#     # (1)
#     # article = Article()
#     # article.title = title
#     # article.content = content
#     # article.save()

#     # (2)
#     article = Article(title=title, content=content)
#     article.save()

#     # (3)
#     # Article.objects.create(title=title, content=content)

#     return redirect('articles:detail', article.pk)


def detail(request, pk):
    # pk1 : Article 이 갖고있는 pk  /   pk2: url 로 받아온 variable pk
    article = Article.objects.get(pk=pk)
    context = {
        'article': article,
    }
    return render(request, 'articles/detail.html', context)

# 요청이 GET인지 POST 인지 확인하는 방법 추가


@require_POST  # 무조건 POST만 받아라 아니면 405 error
def delete(request, pk):
    article = Article.objects.get(pk=pk)
    article.delete()
    return redirect('articles:index')

# 기본적으로 create 의 new 와 비슷한 기능
# def edit(request, pk):
#     article = Article.objects.get(pk=pk)
#     context = {
#         'article': article,
#     }
#     return render(request, 'articles/edit.html', context)


@require_http_methods(['GET', 'POST'])
def update(request, pk):
    article = Article.objects.get(pk=pk)
    # else 먼저 작성해줘야함
    if request.method == 'POST':
        # 첫 번째 인자 =  data, 두 번째 = 기존 data(안쓰면 새로운글 생성)
        form = ArticleForm(request.POST, instance=article)
        if form.is_valid():
            form.save()
            return redirect('articles:detail', article.pk)  # pk 는 처음 선언에서 사용가능
    else:
        form = ArticleForm(instance=article)
    context = {
        'form': form,
    }
    return render(request, 'articles/update.html', context)
    # 1. edit 에서 보낸 데이터 받기
    # title = request.POST.get('title')
    # content = request.POST.get('content')
    # # 2. 받아오는 값으로 변경 후 저장
    # article.title = title
    # article.content = content
    # article.save()
    # return redirect('articles:detail', article.pk)
