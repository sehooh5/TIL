from django import forms
from .models import Question, Answer


class QuestionForm(forms.ModelForm):

    class Meta:
        model = Question
        fields = '__all__'
        exclude = ('user',)


class AnswerForm(forms.ModelForm):

    CHOICES = [
        ('a', 'RED'),
        ('b', 'Blue'),
    ]

    choice = forms.ChoiceField(choices=CHOICES)

    class Meta:
        model = Answer
        fields = '__all__'
        exclude = ('user', 'question',)
