# Name Matcher Spring Boot Application

Mini project, to take a name, for example “Joe Bloggs” and match it to a name which exists in the database. If there is a match, return a response of “MATCHED”. If there isn’t an exact match, return “NO_MATCH”.

In addition, it was required to include unit and Cucumber tests. 



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
I have included an application.yml in the XYZ package to use port 16692, as the default 8080 was in use. This can be changed, or removed. 

The base URL is: 
```aidl
http://localhost:15692/
```

### Hello World Endpoint
```
http://localhost:15692/HelloWorld
```
No variations, this will return a response as follows: 

```aidl
{
"message": "Hello World"
}
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
{
"Match_found": "MATCHED",
"Firstname_lastname": "addi_baron"
}
```

Or, if the name is not in the DB": 
```aidl
{
"Match_found": "NOT_MATCHED",
"Firstname_lastname": "addi_baron"
}
```



- sort structure/refactor 
- separate stepDefs 
- sort application into separate package/directory from tests 