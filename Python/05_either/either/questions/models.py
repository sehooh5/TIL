from django.db import models
from django.conf import settings
# settings 에 AUTH_USER_MODEL 이 있다 이걸 user 정보로 사용


# 사용자가 질문하고 대답할 수 있는 페이지를 만들것
class Question(models.Model):
    title = models.CharField(max_length=100)
    answer_a = models.CharField(max_length=100)
    answer_b = models.CharField(max_length=100)
    user = models.ForeignKey(settings.AUTH_USER_MODEL,
                             on_delete=models.CASCADE)


# ForeignKey 가 2개인 모델
# User(1) - Question, Answer(N)
# Question(1) - Answer(N)
class Answer(models.Model):
    choice = models.CharField(max_length=100)  # 선택지를 두 개만 주는 기능
    question = models.ForeignKey(Question, on_delete=models.CASCADE)  # 1-N관계
    user = models.ForeignKey(settings.AUTH_USER_MODEL,
                             on_delete=models.CASCADE)
