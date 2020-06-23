from django.db import models
from django.conf import settings  # user 를 FK로 사용하기위해 import


class Todo(models.Model):

    content = models.CharField(max_length=100)
    due_date = models.DateField()
    user = models.ForeignKey(settings.AUTH_USER_MODEL,  # 변수로 가져옴
                             on_delete=models.CASCADE)
