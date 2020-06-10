from django.shortcuts import render

# Create your views here.


def index(request):
    # import 된 render 함수의 필수인자 두개 사용 1. request 2. template_name
    # template 는 templates 폴더 안에 있다고 자동으로 인식해서 주소 안써줘도됨
    return render(request, 'index.html')
