from django.urls import path
from . import views

app_name = 'pages'  # app이름 할당시킴 / app_name 은 약속된 변수명
urlpatterns = [
    path('', views.index, name='index'),  # 주소를 이렇게 사용할 수 있음 pages/
]
