from django.shortcuts import render, redirect
from .models import Pages
# Create your views here.


def index(request):
    pages = Pages.objects.all()
    context = {
        'pages': pages,
    }
    return render(request, 'pages/index.html', context)


def new(request):
    return render(request, 'pages/new.html')


def create(request):
    title = request.POST.get('title')
    writer = request.POST.get('writer')
    content = request.POST.get('content')
    pages = Pages(title=title, writer=writer, content=content)
    pages.save()
    return redirect('pages:index')


def detail(request, pk):
    pages = Pages.objects.get(pk=pk)
    context = {
        'pages': pages,
    }
    return render(request, 'pages/detail.html', context)


def delete(request, pk):
    pages = Pages.objects.get(pk=pk)
    if(request.method == 'POST'):
        pages.delete()
        return redirect('pages:index')
    else:
        return redirect('pages:detail', pk)


def edit(request, pk):
    pages = Pages.objects.get(pk=pk)
    context = {
        'pages': pages,
    }
    return render(request, 'pages/edit.html', context)


def update(request, pk):
    pages = Pages.objects.get(pk=pk)
    pages.title = request.POST.get('title')
    pages.writer = request.POST.get('writer')
    pages.content = request.POST.get('content')
    pages.save()
    return redirect('pages:detail', pk)
