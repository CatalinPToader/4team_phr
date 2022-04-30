from django.db import models

# Create your models here.


class Useri(models.Model):
    Email = models.CharField(max_length=30, primary_key=True)
    Password = models.CharField(max_length=10)

class Medici(models.Model):
    Id = models.AutoField(primary_key=True)
    Nume = models.CharField(max_length=50)
    Prenume = models.CharField(max_length=50)
    CNP = models.CharField(max_length=50)
    Email = models.ForeignKey(Useri, on_delete=models.CASCADE)
    Telefon = models.CharField(max_length=10)
    Specialitate = models.CharField(max_length=50)

    
class Pacienti(models.Model):
    Id = models.AutoField(primary_key=True)
    Nume = models.CharField(max_length=50)
    Prenume = models.CharField(max_length=50)
    CNP = models.CharField(max_length=50)
    Email = models.ForeignKey(Useri, on_delete=models.CASCADE)
    Telefon = models.CharField(max_length=10)
    ID_Medic =  models.ForeignKey(Medici, on_delete=models.CASCADE)
    Pending_Delete = models.BooleanField()

class Asistenti(models.Model):
    Id = models.AutoField(primary_key=True)
    Nume = models.CharField(max_length=50)
    Prenume = models.CharField(max_length=50)
    CNP = models.CharField(max_length=50)
    Email = models.ForeignKey(Useri, on_delete=models.CASCADE)
    Telefon = models.CharField(max_length=10)
    ID_Medic = models.ForeignKey(Medici, on_delete=models.CASCADE)

class Programare(models.Model):
    Id = models.AutoField(primary_key=True)
    Data = models.DateField()
    Ora = models.TimeField()
    Specialitate = models.CharField(max_length=50)
    ID_Medic = models.ForeignKey(Medici, on_delete=models.CASCADE)
    ID_Pacient = models.ForeignKey(Pacienti, on_delete=models.CASCADE)

class IstoricMedical(models.Model):
    Id = models.AutoField(primary_key=True)
    Nume_Fisier = models.CharField(max_length=100)
    ID_Pacient = models.ForeignKey(Pacienti, on_delete=models.CASCADE)
