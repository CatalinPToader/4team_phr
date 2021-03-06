from django.shortcuts import render
from django.views.decorators.csrf import csrf_exempt
from rest_framework.parsers import JSONParser
from django.http.response import JsonResponse


from DBApp.models import Useri, Medici, Pacienti, Asistenti, Programare, IstoricMedical
from DBApp.serializers import UserSerializer, MediciSerializer, PacientiSerializer, AsistentiSerializer
from DBApp.serializers import ProgramareSerializer, IstoricMedicalSerializer
# Create your views here.


@csrf_exempt
def userAPI(request):
    #add method
    if request.method=='GET':
        useri = Useri.objects.all()
        useri_serializer = UserSerializer(useri, many=True)
        return JsonResponse(useri_serializer.data, safe=False)

    #add method
    elif request.method=='POST':
        useri_data = JSONParser().parse(request)
        useri_serializer = UserSerializer(data=useri_data)
        if useri_serializer.is_valid():
            useri_serializer.save()
            return JsonResponse("Added Successfully", safe=False)
        return JsonResponse("Failed to Add", safe=False)
    
    #update method
    elif request.method=='PUT':
        useri_data = JSONParser().parse(request)
        useri = Useri.objects.get(Email=useri_data['Email'])
        useri_serializer = UserSerializer(useri, data=useri_data)
        if useri_serializer.is_valid():
            useri_serializer.save()
            return JsonResponse("Updated Successfully", safe=False)
        JsonResponse("Failed to Update", safe=False)

    #delete method
    elif request.method=='DELETE':
        useri_data = JSONParser().parse(request)
        useri = Useri.objects.get(Email=useri_data['Email'])
        useri.delete()
        return JsonResponse("Deleted Successfully", safe=False)
    


@csrf_exempt
def medicAPI(request):
    #add method
    if request.method=='GET':
        medici = Medici.objects.all()
        medici_serializer = MediciSerializer(medici, many=True)
        return JsonResponse(medici_serializer.data, safe=False)

    #add method
    elif request.method=='POST':
        medici_data = JSONParser().parse(request)
        medici_serializer = MediciSerializer(data=medici_data)
        if medici_serializer.is_valid():
            medici_serializer.save()
            return JsonResponse("Added Successfully", safe=False)
        return JsonResponse("Failed to Add", safe=False)
    
    #update method
    elif request.method=='PUT':
        medici_data = JSONParser().parse(request)
        medici = Medici.objects.get(Id=medici_data['Id'])
        medici_serializer = MediciSerializer(medici, data=medici_data)
        if medici_serializer.is_valid():
            medici_serializer.save()
            return JsonResponse("Updated Successfully", safe=False)
        JsonResponse("Failed to Update", safe=False)

    #delete method
    elif request.method=='DELETE':
        medici_data = JSONParser().parse(request)
        medici = Medici.objects.get(Id=medici_data['Id'])
        medici.delete()
        return JsonResponse("Deleted Successfully", safe=False)


@csrf_exempt
def pacientAPI(request):
    #add method
    if request.method=='GET':
        pacienti = Pacienti.objects.all()
        pacienti_serializer = PacientiSerializer(pacienti, many=True)
        return JsonResponse(pacienti_serializer.data, safe=False)

    #add method
    elif request.method=='POST':
        pacienti_data = JSONParser().parse(request)
        pacienti_serializer = PacientiSerializer(data=pacienti_data)
        if pacienti_serializer.is_valid():
            pacienti_serializer.save()
            return JsonResponse("Added Successfully", safe=False)
        return JsonResponse("Failed to Add", safe=False)
    
    #update method
    elif request.method=='PUT':
        pacienti_data = JSONParser().parse(request)
        pacienti = Pacienti.objects.get(Id=pacienti_data['Id'])
        pacienti_serializer = PacientiSerializer(pacienti, data=pacienti_data)
        if pacienti_serializer.is_valid():
            pacienti_serializer.save()
            return JsonResponse("Updated Successfully", safe=False)
        JsonResponse("Failed to Update", safe=False)

    #delete method
    elif request.method=='DELETE':
        pacienti_data = JSONParser().parse(request)
        pacienti = Pacienti.objects.get(Id=pacienti_data['Id'])
        pacienti.delete()
        return JsonResponse("Deleted Successfully", safe=False)


@csrf_exempt
def asistentAPI(request):
    #add method
    if request.method=='GET':
        asistenti = Asistenti.objects.all()
        asistenti_serializer = AsistentiSerializer(asistenti, many=True)
        return JsonResponse(asistenti_serializer.data, safe=False)

    #add method
    elif request.method=='POST':
        asistenti_data = JSONParser().parse(request)
        asistenti_serializer = AsistentiSerializer(data=asistenti_data)
        if asistenti_serializer.is_valid():
            asistenti_serializer.save()
            return JsonResponse("Added Successfully", safe=False)
        return JsonResponse("Failed to Add", safe=False)
    
    #update method
    elif request.method=='PUT':
        asistenti_data = JSONParser().parse(request)
        asistenti = Asistenti.objects.get(Id=asistenti_data['Id'])
        asistenti_serializer = AsistentiSerializer(asistenti, data=asistenti_data)
        if asistenti_serializer.is_valid():
            asistenti_serializer.save()
            return JsonResponse("Updated Successfully", safe=False)
        JsonResponse("Failed to Update", safe=False)

    #delete method
    elif request.method=='DELETE':
        asistenti_data = JSONParser().parse(request)
        asistenti = Asistenti.objects.get(Id=asistenti_data['Id'])
        asistenti.delete()
        return JsonResponse("Deleted Successfully", safe=False)


@csrf_exempt
def programareAPI(request):
    #add method
    if request.method=='GET':
        programare = Programare.objects.all()
        programare_serializer = ProgramareSerializer(programare, many=True)
        return JsonResponse(programare_serializer.data, safe=False)

    #add method
    elif request.method=='POST':
        programare_data = JSONParser().parse(request)
        programare_serializer = ProgramareSerializer(data=programare_data)
        if programare_serializer.is_valid():
            programare_serializer.save()
            return JsonResponse("Added Successfully", safe=False)
        return JsonResponse("Failed to Add", safe=False)
    
    #update method
    elif request.method=='PUT':
        programare_data = JSONParser().parse(request)
        programare = Programare.objects.get(Id=programare_data['Id'])
        programare_serializer = ProgramareSerializer(programare, data=programare_data)
        if programare_serializer.is_valid():
            programare_serializer.save()
            return JsonResponse("Updated Successfully", safe=False)
        JsonResponse("Failed to Update", safe=False)

    #delete method
    elif request.method=='DELETE':
        programare_data = JSONParser().parse(request)
        programare = Programare.objects.get(Id=programare_data['Id'])
        programare.delete()
        return JsonResponse("Deleted Successfully", safe=False)


@csrf_exempt
def istoricMedicalAPI(request):
    #add method
    if request.method=='GET':
        istoricMedical = IstoricMedical.objects.all()
        istoricMedical_serializer = IstoricMedicalSerializer(istoricMedical, many=True)
        return JsonResponse(istoricMedical_serializer.data, safe=False)

    #add method
    elif request.method=='POST':
        istoricMedical_data = JSONParser().parse(request)
        istoricMedical_serializer = IstoricMedicalSerializer(data=istoricMedical_data)
        if istoricMedical_serializer.is_valid():
            istoricMedical_serializer.save()
            return JsonResponse("Added Successfully", safe=False)
        return JsonResponse("Failed to Add", safe=False)
    
    #update method
    elif request.method=='PUT':
        istoricMedical_data = JSONParser().parse(request)
        istoricMedical = IstoricMedical.objects.get(Id=istoricMedical_data['Id'])
        istoricMedical_serializer = IstoricMedicalSerializer(istoricMedical, data=istoricMedical_data)
        if istoricMedical_serializer.is_valid():
            istoricMedical_serializer.save()
            return JsonResponse("Updated Successfully", safe=False)
        JsonResponse("Failed to Update", safe=False)

    #delete method
    elif request.method=='DELETE':
        istoricMedical_data = JSONParser().parse(request)
        istoricMedical = IstoricMedical.objects.get(Id=istoricMedical_data['Id'])
        istoricMedical.delete()
        return JsonResponse("Deleted Successfully", safe=False)


def get_user_type(email):
    if Medici.objects.filter(Email=email).exists():
        return "medic"
    if Asistenti.objects.filter(Email=email).exists():
        return "asistent"
    if Pacienti.objects.filter(Email=email).exists():
        return "pacient"

@csrf_exempt
def loginAPI(request):
    # post login
    if request.method == 'POST':
        login_data = JSONParser().parse(request)
        
        # check if request is formatted correctly
        if not 'Email' in login_data or not 'Password' in login_data:
            return JsonResponse("Malformed request", safe=False)
        
        # save email
        email = login_data['Email']
        
        # check for existing cookie
        email_in_cookie = request.COOKIES.get("4team_phr_email", False)
        if email_in_cookie:
            if request.get_signed_cookie("4team_phr_login", salt=email_in_cookie):
                return JsonResponse("Already Logged In", safe=False)
        
        # otherwise validate data
        if Useri.objects.filter(Email=email).exists():
            login = Useri.objects.get(Email=email)
            user_serializer = UserSerializer(login, data=login_data)
            
            if user_serializer.is_valid():
                response = JsonResponse("Login Success", safe=False)
                
                # Set cookies
                response.set_cookie("4team_phr_email", email)
                
                user_type = get_user_type(email)
                response.set_signed_cookie("4team_phr_login", user_type, salt=email)
                return response
        
        # return generic error response
        return JsonResponse("Email or password wrong", safe=False)

@csrf_exempt
def signupAPI(request):
    if request.method == 'POST':
        signup_data = JSONParser().parse(request)
        
        if not Useri.objects.filter(Email=signup_data['Email']).exists():
            
            user_serializer = UserSerializer(data=signup_data)
            pacient_serializer = PacientiSerializer(data=signup_data)
            if user_serializer.is_valid():
                user_serializer.save()
                if pacient_serializer.is_valid():
                    pacient_serializer.save()
                    return JsonResponse("Registered Successfully", safe=False)
                else:
                    Useri.objects.filter(Email=signup_data['Email']).delete()
                    return JsonResponse("Invalid phone number or CNP", safe=False)
            
            return JsonResponse("Invalid email or password", safe=False)
                    
        return JsonResponse("Email already exists", safe=False)
