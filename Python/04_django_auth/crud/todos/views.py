from django.shortcuts import render

# Create your views here.


def index(request):
    return render(request, 'todos/index.html')


def create(request):
    pass
