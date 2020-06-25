from django.urls import path
from . import views


app_name = 'questions'
urlpatterns = [
    path('', views.index, name='index'),
    path('form/', views.form, name='form'),
    path('detail/', views.detail, name='detail'),
]
