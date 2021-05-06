
# Microservices: Using Spring Cloud Netflex, Apache ActiveMQ 5, and Spring Boot

The demo project is to build a small e-commerce application with two separate services: Product Service and Cart Service.

## Components
Following components will be part of the system:

 1. **Service registry (Eureka)**: where all services will register themselves
 
 2. **Spring Cloud Gateway**: that will redirect all requests to the corresponding microservice
 
 3. **Message broker (ActiveMQ 5)**: publish/consume messages
 
 4. **Product Service**: publish API to create a new product
 
 5. **Cart Service**: consume the new product created by Product Service and store in its own database.
  
## Start the service registry (Eureka)
To run the Eureka service with Maven, run the following command in the root folder.
```
mvnw spring-boot:run -pl eureka-server
```
Check Eureka running on:
```
http://localhost:9000/
```

## Start the Message Broker (ActiveMQ 5)
To start the Message broker, download [ActiveMQ 5](http://activemq.apache.org/), once downloaded, unzip the file. then go to the bin folder and run: 
```
activemq start 
```

Access the web console
```
http://127.0.0.1:8161/admin/queues.jsp
username: admin / password: admin
```

## Product Service, Cart Service, Gateway
All services can be started in the editor IntelliJ. Following APIs are exposed:

1. Create a new product:
```
http://localhost:8888/product/addOne
```

2. Send product to message broker
```
http://localhost:8888/product/sendToCart/1
```

3. Verify if the product is consumed by Cart Service
```
http://localhost:8888/cart/getProducts
```
