# Django Form

- https://docs.djangoproject.com/ko/2.1/topics/forms/ : 참고자료 (2.1/ko)

- 모델 작성하는 형식과 거의 비슷하다

  ```python
  from django import forms
  
  class NameForm(forms.Form):
      your_name = forms.CharField(label='Your name', max_length=100)
  ```

  

