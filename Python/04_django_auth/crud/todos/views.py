from django.shortcuts import render, redirect
from django.contrib.auth.decorators import login_required
from .forms import TodoForm
from .models import Todo
# Create your views here.


@login_required
def index(request):
    form = TodoForm()
    #todos = Todo.objects.all()
    context = {
        'form': form,
        #    'todos': todos,
    }
    return render(request, 'todos/index.html', context)


# request 안에 user, POST 등등 정보가 다 들어가있다
# 가져와서 사용하면 된다
@login_required
def create(request):
    form = TodoForm(request.POST)
    if form.is_valid():
        todo = form.save(commit=False)  # 누가 작성했는데 정보 입력위해 보류
        todo.user = request.user  # request 의 변수 user 에 정보가 들어가있다
        todo.save()
        return redirect('todos:index')


def delete(request, pk):
    todo = Todo.objects.get(pk=pk)
    todo.delete()
    return redirect('todos:index')
