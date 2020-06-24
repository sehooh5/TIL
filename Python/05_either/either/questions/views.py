from django.shortcuts import render, redirect, get_object_or_404  # NEW!
from .forms import QuestionForm, AnswerForm
from .models import Question, Answer


def index(request):
    return render(request, 'questions/index.html')


def create(request):
    if request.method == 'POST':
        form = QuestionForm(request.POST)
        if form.is_valid():
            question = form.save(commit=False)
            question.user = request.user
            question.save()
            return redirect('questions:detail', question.pk)
    else:
        form = QuestionForm()
    context = {
        'form': form,
    }
    return render(request, 'questions/form.html', context)


def detail(request, question_pk):
    # question = Question.objects.get(pk=question_pk)

    # get_object_or_404()
    # 1. Model  2. pk
    # 추가기능 : object 가 없으면 404 를 반환한다 (고객에게 잘못이 있음을 반환)
    question = get_object_or_404(Question, pk=question_pk)
    answer_form = AnswerForm()
    context = {
        'question': question,
        'answer_form': answer_form,
    }
    return render(request, 'questions/detail.html', context)


def answer_create(request, question_pk):
    answer_form = AnswerForm(request.POST)
    if answer_form.is_valid():
        answer_form.save()
