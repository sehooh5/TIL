from django.contrib import admin
from .models import Status



class StatusAdmin(admin.ModelAdmin):
    list_display = ['api', 'updated_time', 'status']
    list_editable = ['status']



# Register your models here.
admin.site.register(Status, StatusAdmin)
