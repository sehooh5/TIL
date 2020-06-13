from django.urls import path
from . import views

app_name = 'pages'  # app이름 할당시킴
urlpatterns = [
    path('', views.index, name='index'),  # 주소는 pages/
]
