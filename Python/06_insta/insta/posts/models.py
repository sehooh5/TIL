from django.db import models
from django.conf import settings
from imagekit.models import ProcessedImageField
from imagekit.processors import ResizeToFill, ResizeToFit, ResizeCanvas, ResizeToCover

# < 충돌이 일어나는 이유 >
# user, like_user 가 모두 post_set 을 만들어 User 와 연결하려한다
# 1. post_set from user(FK) : 어떤 유저가 작성한 글들
# 2. post_set from like_user(M2M) : 어떤 유저가 좋아요를 누른 글들 -> like_posts
# 두 개 이름이 같아서 충돌이 난다
# < 충돌 변경해주는 방법 >
# 두 개의 이름을 변경해준다


class Post(models.Model):
    content = models.CharField(max_length=200)
    #image = models.ImageField()
    # 작성한 사람을 저장
    user = models.ForeignKey(settings.AUTH_USER_MODEL,  # user 정보 가져오는 곳
                             on_delete=models.CASCADE)
    created_at = models.DateTimeField(auto_now_add=True)
    image = ProcessedImageField(upload_to='media',
                                processors=[ResizeToCover(500, 500)],
                                format='JPEG',
                                options={'quality': 80})
    # 좋아요 버튼을 누른 사람들을 저장
    # 여기서 like_posts는 User에 저장되는 컬럼명이 된다(기존 post_set)
    like_users = models.ManyToManyField(
        settings.AUTH_USER_MODEL, related_name='like_posts')

    class Meta:
        # 정렬해주는 기능, 기본이 id 순으로 정렬하는데 - 값 주면 역순
        ordering = ['-id']
