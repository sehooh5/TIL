from django.shortcuts import render, redirect
from .forms import QuestionForm, AnswerForm
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
        answer_form = AnswerForm()
        context = {
            'question_form': question_form,
            'answer_form': answer_form,
        }
        return render(request, 'questions/form.html', context)


def detail(request, question_pk):
    if request.method == 'POSt':
        pass
    else:
