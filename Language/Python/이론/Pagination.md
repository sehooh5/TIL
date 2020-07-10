# Pagination

- 페이지를 나누는 기능
- Paginator class, function 사용
- [공식 페이지 참고](https://docs.djangoproject.com/en/3.0/topics/pagination/)



#### Paginator import

- `views.py` 에 추가

``` python
from django.core.paginator import Paginator
from .models import Article

# 원하는 페이지에 사용
articles = Article.objects.all()
paginator = Paginator(articles, 5) # 1. data  2. 보여주고싶은 개수
page_number = request.GET.get('page') # get요청의 page 값
articles = paginator.get_page(page_number) # 해당 page의 내용들만 query set 으로 저장해서 보여줌
```



#### 페이징 index 

- `index.html` 수정

```html
  <div class="pagination">
    <span class="step-links">
        {% if articles.has_previous %}
            <a href="?page=1">&laquo; first</a>
            <a href="?page={{ articles.previous_page_number }}">previous</a>
        {% endif %}

        <span class="current">
            Page {{ articles.number }} of {{ articles.paginator.num_pages }}.
        </span>

        {% if articles.has_next %}
            <a href="?page={{ articles.next_page_number }}">next</a>
            <a href="?page={{ articles.paginator.num_pages }}">last &raquo;</a>
        {% endif %}
    </span>
  </div>
```





#### 페이지 번호 버튼 추가

- bootstrap4 library 사용
- `settings.py` 에 `bootstrap4` 추가
- 로드 후 사용

```python
{% load bootstrap4 %}

# 맨 밑에 추가
# 위에서 사용한 것은 삭제해줘도 좋다
{% bootstrap_pagination articles %}
```





#### fixture 기능 사용

- [장고 공식문서](https://docs.djangoproject.com/en/3.0/ref/django-admin/)

- dumpdata 만들고 json 파일로 저장

  ```
  django-admin dumpdata [app_label[.ModelName] [app_label[.ModelName] ...]]
  
  ex)
  python manage.py dumpdate --indent=2 articles.Article > articles.json
  ```

- 장고는 자동으로 fixtures 폴더를 인식함..앱 안에 만들어주기

- db를 지우고 다시 migrate : 구조만 잡힘

- loaddata : dumpdata 와 반대 개념을 사용하여 데이터 삽입

  ```
  python manage.py loaddata articles.json
  ```

  

