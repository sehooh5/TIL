from django.db import models

# Create your models here.

# Github Django에 가보면 Models 폴더에 base.py에 Model 을 상속받아 사용한다


class Article(models.Model):
    # 컬럼명 = 변수명 // id는 자동으로 작성
    # 변수의 속성 정의해주기 : models가 갖고있음 CharField
    title = models.CharField(max_length=20)
    # TextField 는 길이제한이 없음
    content = models.TextField()
    # 날짜+시간 타입
    # auto_now_add : 최초 생성 일자 = insert시 에만 적용되고 수정되지 않음
    # auto_now : 최종 수정일자, django ORM이 save 할때마다 현재 날자와 시간으로 갱신
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
