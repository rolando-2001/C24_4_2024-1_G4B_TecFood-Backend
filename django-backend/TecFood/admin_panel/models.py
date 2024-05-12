from django.db import models

# Create your models here.
class Role(models.Model):
    role_id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=30)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

class User(models.Model):
    user_id = models.AutoField(primary_key=True)
    first_name = models.CharField(max_length=255)
    last_name = models.CharField(max_length=255)
    phone_number = models.IntegerField()
    email = models.EmailField(unique=True)
    password = models.CharField(max_length=255)
    dni = models.IntegerField(null=True)
    img_url = models.CharField(max_length=255, null=True)
    is_google_account = models.BooleanField(default=False)
    is_verified_email = models.BooleanField(default=False)
    role = models.ForeignKey(Role, on_delete=models.RESTRICT)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)