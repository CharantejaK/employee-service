# Steps to execute the service.

1. execute command "gradle build" using gradle 6.4 or above
2. run the jar using java 14 (alternatively run with Docker follow steps 3 and 4)
3. docker build --tag employee-service:1.0 .
4. docker run --publish 9090:9090 --detach --name bb employee-service:1.0

# Access the service at.
1. Save Employee Request 
http://localhost:9090/saveEmployee (HTTP Post Request )

    eg request :
    {
      "employee" : { "employeeId":"123",
      "firstName": "michael",
      "lastName": "waugh"
      }
    }
2. Get Employee Request 
http://localhost:9090/getEmployee?employeeId=123  (Get Request)

