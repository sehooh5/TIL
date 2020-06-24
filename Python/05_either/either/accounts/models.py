from django.db import models
from django.contrib.auth.models import AbstractUser

# 기존 User 를 대체할 User 생성


class User(AbstractUser):
    phone = models.CharField(max_length=20)
