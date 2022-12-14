# Name Matcher Spring Boot Application

Project set to develop skills in BDD, unit tests, and Springboot. 

The project consists of two endpoints: 
1. A HelloWorld endpoint, returning `"Hello World"` 
2. A NameMatcher endpoint, searching a MySQL DB for a match in the DB and returning `MATCHED`, or `NOT_MATCHED`. 


<details>
    <summary>The project was split into the following tasks: </summary>
        
1. Create a Spring Boot application using Spring Initializr 
2. Create a Rest Controller
3. Create the Name Matcher endpoint
4. Create Cucumber Tests
5. Create a MySQL database
6. Set up JPA to interact with your database
7. Change your Name Matching endpoint 

</details>

## How to use 
I have included an application.yml in the XYZ package to use port 15692, as the default 8080 was in use. This can be changed, or removed. 

### Hello World Endpoint
```
http://localhost:15692/HelloWorld
```
No variations, this will return a response as follows: 

```aidl
{ "message": "Hello World"}
```

### Name Matcher
```
http://localhost:15692/NameMatcher/firstname_lastname
```
Following the above format, insert the relevant first name and last name. For example: 
```
http://localhost:15692/NameMatcher/addi_baron
```

This searches a table within the (MySQL) database, and returns the following if the name you supply is in the DB: 
```aidl
{ "Match_found": "MATCHED",
"Firstname_lastname": "addi_baron" }
```

Or, if the name is not in the DB": 
```aidl
{ "Match_found": "NOT_MATCHED",
"Firstname_lastname": "addi_baron" }
```

### Tests

A BDD layer can be found within src/test/resources/cucumber, and all can be run from src/test/java/cucumber/CucumberTest.java. 

Unit tests can be found, and run from, src/test/java/UnitTests.java. 
