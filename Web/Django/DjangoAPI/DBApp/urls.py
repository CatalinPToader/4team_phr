from django.conf.urls import url
from DBApp import views

urlpatterns=[
    url(r'^user/$', views.userAPI),
    url(r'^user/([0-9]+)$', views.userAPI),

    url(r'^medic/$', views.medicAPI),
    url(r'^medic/([0-9]+)$', views.userAPI),

    url(r'^pacient/$', views.pacientAPI),
    url(r'^pacient/([0-9]+)$', views.pacientAPI),

    url(r'^asistent/$', views.asistentAPI),
    url(r'^asistent/([0-9]+)$', views.asistentAPI),

    url(r'^programare/$', views.programareAPI),
    url(r'^programare/([0-9]+)$', views.programareAPI),

    url(r'^istoricMedical/$', views.istoricMedicalAPI),
    url(r'^istoricMedical/([0-9]+)$', views.istoricMedicalAPI),
    
    url(r'^login/$', views.loginAPI),
    url(r'^signup/$', views.signupAPI)
]