from rest_framework import serializers
from django.contrib.auth.hashers import make_password
from user.models import User, Role
import re


class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User

        fields = [
            "id",
            "name",
            "lastname",
            "phone",
            "email",
            "password",
            "dni",
            "img",
            "emailVerified",
            "google",
            "role",
            "created_at",
            "updated_at",
        ]

        read_only_fields = [
            "created_at",
            "updated_at",
            "emailVerified",
            "google",
            "id",
        ]

    def validate_email(self, value):
        expresion = r"^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$"
        if not re.match(expresion, value):
            raise serializers.ValidationError("El email no es válido")
        if User.objects.filter(email=value).exists():
            raise serializers.ValidationError("El email ya se encuentra registrado")
        return value

    def validate_dni(self, value):
        if User.objects.filter(dni=value).exists():
            raise serializers.ValidationError("El dni ya se encuentra registrado")
        return value
    

    def validate_phone(self, value):
        expresion = r"^\d{9}$"
        if not re.match(expresion, value):
            raise serializers.ValidationError("El número de teléfono no es válido")
        return value

    def validate_password(self, value):
        expresion = r"^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$"
        if not re.match(expresion, value):
            raise serializers.ValidationError(
                "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número"
            )
        value = make_password(value)

        return value
    
    def validate_role(self, value):
        ADMIN_ROLE = Role.objects.get(id=1)
        if value != ADMIN_ROLE:
            raise serializers.ValidationError("El rol no es válido")
        return value
    
