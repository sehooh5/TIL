# Generated by Django 3.0.7 on 2020-06-22 05:48

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('movies', '0002_comment'),
    ]

    operations = [
        migrations.RenameField(
            model_name='comment',
            old_name='article',
            new_name='movie',
        ),
    ]
