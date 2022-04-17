#!/bin/bash

while ! nc -z db 3306 ; do
    echo "Waiting for the MySQL Server"
    sleep 3
done

python3 django/manage.py runserver 0.0.0.0:8000