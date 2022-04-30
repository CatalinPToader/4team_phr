from django.shortcuts import render
from django.views.decorators.csrf import csrf_exempt
from rest_framework.parsers import JSONParser
from django.http.response import JsonResponse


from DBApp.models import Person
from DBApp.serializers import PersonSerializer

# Create your views here.


@csrf_exempt
def personAPI(request, id=0):
    #add method
    if request.method=='GET':
        person = Person.objects.all()
        person_serializer = PersonSerializer(person, many=True)
        return JsonResponse(person_serializer.data, safe=False)

    #add method
    elif request.method=='POST':
        person_data = JSONParser().parse(request)
        person_serializer = PersonSerializer(data=person_data)
        if person_serializer.is_valid():
            person_serializer.save()
            return JsonResponse("Added Successfully", safe=False)
        return JsonResponse("Failed to Add", safe=False)
    
    #update method
    elif request.method=='PUT':
        person_data = JSONParser().parse(request)
        person = Person.objects.get(Id=person_data['Id'])
        person_serializer = PersonSerializer(person, data=person_data)
        if person_serializer.is_valid():
            return JsonResponse("Updated Successfully", safe=False)
        JsonResponse("Failed to Update", safe=False)

    #delete method
    elif request.method=='DELETE':
        person = Person.objects.get(Id=id)
        person.delete()
        return JsonResponse("Deleted Successfully", safe=False)
    




