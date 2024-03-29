For Category
1.Open Postman.
2.Create a new request.
3.Set the request type to POST.
4.Enter the URL of your endpoint (e.g., http://localhost:8080/api/categories).
5.Switch to the "Body" tab.
6.Select "raw" and then choose "JSON" from the dropdown.
7.Paste the JSON code representing your category data into the body field.
8.Click on the "Send" button to send the request.

{
    "name": "Example Category"
}

For Products
1.Open Postman.
2.Create a new request.
3.Set the request type to POST.
4.Enter the URL of your endpoint (e.g., http://localhost:8080/api/products).
5.Switch to the "Body" tab.
6.Select "raw" and then choose "JSON" from the dropdown.
7.Paste the JSON code representing your category data into the body field.
8.Click on the "Send" button to send the request.

{
    "name": "Example Product",
    "price": 19.99,
    "category": {
        "id": 1,
        "name": "Example Category"
    }
}

//application.yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/practice?useSSL=false
    username: root
    password: root
  jpa:
    hibernate:
      ddl-auto: update
      dialect: org.hibernate.dialect.MySQLDialect

      
//application.properties
spring.application.name=NimapCrudOperationTask
spring.datasource.url=jdbc:mysql://localhost:3306/practice
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

//Dependencies
1.Spring Web
2.MsSql Driver
3.Spring Data JPA
