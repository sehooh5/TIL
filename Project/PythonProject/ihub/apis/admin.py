from django.contrib import admin
from .models import Api



class ApiAdmin(admin.ModelAdmin):
    list_display = ['pk', 'api_name', 'api_url', 'latest_modified_date','copyright', 'copyright_range', 'api_file', 'download_count']
    list_editable = ['api_name']


# Register your models here.
admin.site.register(Api, ApiAdmin)