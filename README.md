***How to start the application :***

go in the root folder, and launch the database with the following command:

- docker-compose up

start the application by running :
- ./mvnw spring-boot:run or running directly with your ide

an agent user is created at application initialization with the following credentials :
- username : admin
- password : admin

you can access all our routes by accessing the swagger at http://localhost:8080/swagger-ui/index.html
when trying to access a route log in as the agent previously specified.


you can test our routes also by identifying as a customer with the following credentials
- username: customer
- password: customer

You can check the github repo : https://github.com/LeyDece/CarRental