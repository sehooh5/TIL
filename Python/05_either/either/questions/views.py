from django.shortcuts import render
from .forms import QuestionForm, AnswerForm


def index(request):
    return render(request, 'questions/index.html')


def create(request):
    pass
