# employee-service

This employee-service application has two REST API endpoints:
1st Endpoint: Get Employee Details
```
This endpoint list the employee details for the requested employee Id

URI Endpoint: GET http://host:port/api/v1/employees/{employeeId}
```
Example:
```
URI: localhost:8080/api/v1/employees/12231

Response:
{
    "empId": 12231,
    "firstName": "Aliko1",
    "lastName": "Dangote4",
    "gender": "male",
    "title": "Mr",
    "address": {
        "street": "3 Market Street",
        "city": "San Francisco",
        "state": "CA",
        "postCode": 1123
    }
}

```
2nd Endpoint: Update Employee

```
This service updates any field of the employee.

URI Endpoint: PATCH : http://host:port/api/v1/employees/{employeeId}
```
Example:
```
URI: localhost:8080/api/v1/employees/12231

Request Body:

{
    "firstName": "Aliko1",
    "lastName": "Dangote4",
    "gender": "male",
    "title": "Mr",
    "address": {
        "street": "3 Market Street",
        "city": "San Francisco",
        "state": "CA",
        "postCode": 1123
    }
}

Response:
{
    "empId": 12231,
    "firstName": "Aliko1",
    "lastName": "Dangote4",
    "gender": "male",
    "title": "Mr",
    "address": {
        "street": "3 Market Street",
        "city": "San Francisco",
        "state": "CA",
        "postCode": 1123
    }
}
```
Note:
```
The application is basic auth enabled, there are two users of the system as listed below
 - USERS - Can access the GET endpoints of the service
 - ADMIN - Can access all the endpoints of the service

 Please user the Authorization header while calling the endpoints

 User deatils listed below
 1) USERS - username : user
 			password : password

 2) ADMIN - username : admin
            password : password
```


To Run the App:

To run the application you can go to the HOME directory and follow the below steps:

```
- Build the project 
 ./mvnw install 

- To run the project you can run the below command

 java -jar /target/employee-service-0.0.1-SNAPSHOT.jar


```

Other alternatives to Run the app

```
You can import it as  a maven project into any IDE(Intellij or Eclipse)

Run it as a springboot application 


```

Start up the H2 DB console

```
You can see the database console by following link

http://localhost:8080/h2-console

```

NOTE: 
```
I have included the Postman collection under the folder Postman_Collection

EmployeeService.postman_collection.json
```