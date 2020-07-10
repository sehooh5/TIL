from django.db import models
from django.contrib.auth.models import AbstractUser
from django.conf import settings
from imagekit.processors import ResizeToFill, ResizeToFit, ResizeCanvas, ResizeToCover
from imagekit.models import ProcessedImageField
# Create your models here.


# follow 기능을 위해 추가함
class User(AbstractUser):
    follow = models.ManyToManyField(
        settings.AUTH_USER_MODEL, related_name='follower')
    # user_set 이 자동 생성되는데, 직관적으로 follower 로 변경해준다

    # (image) 컬럼이 새로 생기면 default 값을 꼭 입력해줘야한다
    image = ProcessedImageField(upload_to='accounts',
                                processors=[ResizeToFill(300, 300)],
                                format='JPEG',
                                options={'quality': 80},
                                default='default.png'
                                )
