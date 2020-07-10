from django.shortcuts import render, redirect
from .forms import QuestionForm, AnswerForm
from .models import Question, Answer
# Create your views here.


def index(request):
    return render(request, 'questions/index.html')


def form(request):
    if request.method == 'POST':
        question_form = QuestionForm(request.POST)
        if question_form.is_valid():
            question = question_form.save(commit=False)
            question.user = request.user
            question.save()
            return redirect('questions:detail', question.pk)
    else:
        question_form = QuestionForm()
        context = {
            'question_form': question_form,
        }
        return render(request, 'questions/form.html', context)


def detail(request, question_pk):
    if request.method == 'POST':
        answer_form = AnswerForm(request.POST)
        if answer_form.is_valid():
            answer = answer_form.save(commit=False)
            answer.user = request.user
            answer.question = Question.objects.get(pk=question_pk)
            answer.save()
            return redirect('questions:detail', question_pk)
    else:
        question = Question.objects.get(pk=question_pk)
        answer_form = AnswerForm()
    context = {
        'question': question,
        'answer_form': answer_form,
    }
    return render(request, 'questions/detail.html', context)
