from rest_framework import serializers
from DBApp.models import Person

class PersonSerializer(serializers.ModelSerializer):
    class Meta:
        model = Person
        fields = ('Id', 'CNP', 'Nume', 'DataNastere')