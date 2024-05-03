from django.db import models


# Create your models here.
class Role(models.Model):
    name = models.CharField(max_length=100)

    class Meta:
        db_table = "role"

    def __str__(self):
        return self.name


class User(models.Model):
    name = models.CharField(max_length=100)
    lastname = models.CharField(max_length=100)
    phone = models.CharField(max_length=100)
    email = models.EmailField(max_length=100)
    password = models.CharField(max_length=100)
    dni = models.CharField(max_length=8)
    img = models.CharField(max_length=100, null=True)
    emailVerified = models.BooleanField(default=False)
    google = models.BooleanField(default=False)
    role = models.ForeignKey(Role, on_delete=models.CASCADE)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    class Meta:
        db_table = "user"

