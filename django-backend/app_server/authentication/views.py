from rest_framework.decorators import api_view
from rest_framework.response import Response
from app_server.serializers import UserSerializer
from rest_framework.authtoken.models import Token
from rest_framework import status


# Create your views here.
@api_view(["POST"])
def register(request):
    serializer = UserSerializer(data=request.data)
    if serializer.is_valid():
        user = serializer.save()

        return Response(
            {"user": serializer.data},
            status=status.HTTP_201_CREATED,
        )

    return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
