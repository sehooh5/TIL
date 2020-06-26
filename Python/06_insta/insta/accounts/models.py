from django.db import models
from django.contrib.auth.models import AbstractUser
from django.conf import settings
# Create your models here.


class User(AbstractUser):
    follow = models.ManyToManyField(
        settings.AUTH_USER_MODEL, related_name='follower')
    # user_set 이 자동 생성되는데, 직관적으로 follower 로 변경해준다
