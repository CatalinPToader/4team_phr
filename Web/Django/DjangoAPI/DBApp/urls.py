from django.conf.urls import url
from DBApp import views

urlpatterns=[
    url(r'^person/$', views.personAPI),
    url(r'^person/([0-9]+)$', views.personAPI)
]