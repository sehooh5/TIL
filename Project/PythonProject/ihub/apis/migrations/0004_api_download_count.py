# Generated by Django 3.0.7 on 2020-07-05 10:52

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('apis', '0003_remove_api_download_count'),
    ]

    operations = [
        migrations.AddField(
            model_name='api',
            name='download_count',
            field=models.IntegerField(default=0),
        ),
    ]
