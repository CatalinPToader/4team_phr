# Generated by Django 3.1 on 2022-04-30 11:41

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Person',
            fields=[
                ('Id', models.AutoField(primary_key=True, serialize=False)),
                ('CNP', models.IntegerField()),
                ('Nume', models.CharField(max_length=100)),
            ],
        ),
    ]