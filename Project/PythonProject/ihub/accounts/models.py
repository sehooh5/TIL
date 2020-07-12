from django.db import models
from django.contrib.auth.models import AbstractUser
from django.conf import settings
from imagekit.models import ProcessedImageField
from imagekit.processors import ResizeToFit, ResizeToFill, ResizeCanvas


# Create your models here.
class User(AbstractUser):
    pass
