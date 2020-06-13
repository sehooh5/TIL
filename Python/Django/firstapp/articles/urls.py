from django.urls import path
from . import views

app_name = 'articles'
urlpatterns = [
    path('', views.index, name='articles'),
    path('dinner/', views.dinner, name='dinner'),
    path('pic/', views.pic, name='pic'),
    # 뒤에 동적 변수를 사용하고싶으면 <타입 : 변수명>
    # str 타입 명시는 생략 가능
    path('hello/<str:name>/', views.hello, name='hello'),
    path('intro/<name>/<int:age>/', views.intro, name='intro'),
    path('multiple/<int:x>/<int:y>/', views.multiple, name='multiple'),
    path('dtl-practice/', views.dtl_practice, name='dtl-practice'),
    path('routing/<word>/', views.routing, name='routing'),
    path('throw/', views.throw, name='throw'),
    path('catch/', views.catch, name='catch'),
    path('lotto-throw/', views.lotto_throw, name='lotto-throw'),
    path('lotto-catch/', views.lotto_catch, name='lotto-catch'),
    path('artii/', views.artii, name='artii'),
    path('artii-result/', views.artii_result, name='artii-result'),
]
