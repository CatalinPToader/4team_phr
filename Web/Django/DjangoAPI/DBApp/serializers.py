from rest_framework import serializers
from DBApp.models import Useri, Medici, Pacienti, Asistenti, Programare, IstoricMedical

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = Useri
        fields = ('Password', 'Email')


class MediciSerializer(serializers.ModelSerializer):
    class Meta:
        model = Medici
        fields = ('Id', 'Nume', 'Prenume', 'CNP', 
                'Email', 'Telefon', 'Specialitate')


class PacientiSerializer(serializers.ModelSerializer):
    class Meta:
        model = Pacienti
        fields = ('Id', 'Nume', 'Prenume', 'CNP', 
                'Email', 'Telefon', 'ID_Medic', 'Pending_Delete')


class AsistentiSerializer(serializers.ModelSerializer):
    class Meta:
        model = Asistenti
        fields = ('Id', 'Nume', 'Prenume', 'CNP', 
                'Email', 'Telefon', 'ID_Medic')


class ProgramareSerializer(serializers.ModelSerializer):
    class Meta:
        model = Programare
        fields = ('Id', 'Data', 'Ora', 'Specialitate', 
                'ID_Medic', 'ID_Pacient')

class IstoricMedicalSerializer(serializers.ModelSerializer):
    class Meta:
        model = IstoricMedical
        fields = ('Id', 'Nume_Fisier', 'ID_Pacient')
        
class SignupSerializer(serializers.ModelSerializer):
    class Meta:
        model1 = Useri
        fields1 = ('Email', 'Password')
        model2 = Pacienti
        fields2 = ('Id', 'Nume', 'Prenume', 'CNP', 
                'Email', 'Telefon', 'ID_Medic', 'Pending_Delete')