# 🛒 Shopping cart kata
The implementation of this Shopping cart kata REST API is a task part of the application process for a Software Developer at Spareroom.

## 🗒️ The Task
To implement a simple checkout system where there are four products available, each with a price per unit. 
Some products have a special price when bought in certain quantities (e.g. 3 of product A costs 140, not 150). 
The checkout system consumes a data source like [this](https://spareroom.github.io/recruitment/docs/cart-kata/data-set-1.json), and returns the subtotal when queried.

## ✨ Implementation
The Shopping Cart system was implemented as a REST API using [Java](https://www.oracle.com/java/technologies/downloads/#java21) and Spring Boot. 
Additionally, [Maven](https://maven.apache.org/) is required to run this API. 
Unit Test cases were written using JUnit, and the endpoints were documented using Swagger following the OpenAPI spec.
These tests cover 80% lines of code. Coverage report for the tests can be found in a separate repo [here](https://github.com/eymaal/spareroom-shopping-cart-kata-test-report).

## ⚡ Running this API
Navigate to the directory and the run the following commands:

```mvn clean install```

```mvn spring-boot:run```

The server is configured to run on port 8080. 
Once the server has started, details about the endpoints can be found [here](http://localhost:8080/swagger-ui/index.html).
Alternatively, the checkout endpoint can be reached [here](localhost:8080/api/v1/checkout).
The URL to the datasource is configured in `\src\main\resources\application.properties`.

_Edit:_ command ```mvn clean``` was updated to ```mvn clean install``` on 07/04. ```mvn clean install``` will run the added unit test cases too. 
