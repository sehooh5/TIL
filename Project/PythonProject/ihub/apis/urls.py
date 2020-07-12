from django.urls import path
from . import views

app_name = 'apis'
urlpatterns = [
    path('', views.index, name='index'),
    path('<int:pk>/detail/', views.detail, name='detail'),
    path('search/<search_string>/', views.search, name='search'),
    path('search/<search_string>/list/', views.search_list, name='search_list'),
    path('<int:pk>/status/', views.status, name='status'),
    path('<int:pk>/download/', views.download, name='download'),
    path('<int:pk>/graph/', views.graph, name='graph'),
    path('about/', views.about, name='about'),
    path('totalapis/', views.totalapis, name='totalapis')
]

