# Generated by Django 2.1.15 on 2020-06-25 04:28

from django.db import migrations
import imagekit.models.fields


class Migration(migrations.Migration):

    dependencies = [
        ('posts', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='post',
            name='image',
            field=imagekit.models.fields.ProcessedImageField(upload_to='media'),
        ),
    ]
