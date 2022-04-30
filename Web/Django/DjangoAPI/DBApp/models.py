from django.db import models

# Create your models here.


class Person(models.Model):
    Id = models.AutoField(primary_key=True)
    CNP = models.IntegerField()
    Nume = models.CharField(max_length=100)
    DataNastere = models.DateField
    