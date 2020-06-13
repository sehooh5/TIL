from django.urls import path
from . import views

urlpatterns = [
    path('index/', views.index),
    path('dinner/', views.dinner),
    path('pic/', views.pic),
    # 뒤에 동적 변수를 사용하고싶으면 <타입 : 변수명>
    # str 타입 명시는 생략 가능
    path('hello/<str:name>/', views.hello),
    path('intro/<name>/<int:age>/', views.intro),
    path('multiple/<int:x>/<int:y>/', views.multiple),
    path('dtl-practice/', views.dtl_practice),
    path('routing/<word>/', views.routing),
    path('throw/', views.throw),
    path('catch/', views.catch),
]
