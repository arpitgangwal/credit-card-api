**Credit-Card-Application**
This application can create and list all the application.
**Swagger UI** can be access via http://localhost:8080/swagger-ui.html/

**Validations** on the credit card add request body

**CreditCardNumber**: It should be valid number and nuh validation applied.
**Card Type**: Currently two type of card acceptable visa and master.
**ExpiryDate**: it should be in format of ("MM/dd/yyyy") and can be date before or same of credit-card/add request
**SecurityNumber**: it should be 3 digit long

**Running the application via docker**

**build docker image**

docker build -t imageName directorypath of project

**run docker image**

docker run -p 8080:8080 imageName

There are two end point

**Post: /credit-card/add**  which accept **CreditCard Json**
Return HTTP status 201 as created

**Get: /credit-card/list**  where Query Param page and **size** is **optional**

If we won't pass query param it return full results with HTTP Status 200
Else it will return Partial result with HttpStatus 206

**Runtime VM Param for starting application**

-Djasypt.encryptor.password=test

