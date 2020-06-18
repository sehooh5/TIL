from django.db import models

# Create your models here.


class feed(models.Model):
    EMOTIONS = (
        ('HP', '즐거움'),
        ('BD', '나쁨'),
        ('SD', '슬픔'),
        ('NO', '모르겠음'),
    )
    emotion = models.CharField(max_length=1, choices=EMOTIONS)
    title = models.CharField(max_length=50)
    content = models.TextChoices()
    created_at = models.DateTimeField(auto_now_add=True)
    updated = models.DateTimeField(auto_now=True)
