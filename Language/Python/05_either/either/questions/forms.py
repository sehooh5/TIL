from django import forms
from .models import Question, Answer


class QuestionForm(forms.ModelForm):

    class Meta:
        model = Question
        fields = '__all__'
        exclude = ('user',)


class AnswerForm(forms.ModelForm):

    CHOICES = [
        ('a', 'RED'),  # 앞 : DB 에 들어가는 값
        ('b', 'Blue'),  # 뒤 : 클라이언트에 보이는 값
    ]

    choice = forms.ChoiceField(choices=CHOICES)

    class Meta:
        model = Answer
        fields = '__all__'
        exclude = ('user', 'question',)
