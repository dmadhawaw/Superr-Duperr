# SuperrDuperr - ToDoListApplication

The ToDoListApplication will be able to perform below service operations.

- Add items to a list
- Mark an item as Completed
- Ability to delete items
- Ability to restore items
- Support for multiple lists [Type - WORK/PERSONAL]
- Ability to tag items within a list - [Added entity field 'Priority']
- Ability to add reminders to items - [Added reminder duration entity field along with the priority]

In preceding ToDoItem controller class, has defined a number of RESTful URIs as follows to operate with the resource 'ToDoItem' and it's extended entity 'ToDoItemWork'.

- /todoItems 				- HTTP Get # Get all ToDo items [all types ]
- /workTodoItems 			- HTTP Get # Get all ToDo items - work type
- /todoItems/{todoId} 		- HTTP Get # Get a ToDoItem for a given Id
- /workTodoItems/{todoId} 	- HTTP Get # Get a ToDoItem for a given Id
- /workTodoItems/{todoId} 	- HTTP Put # to update status from PENDING to COMPLETED.
- /workTodoItems/{todoId} 	- HTTP Patch # to update the status based on the date created and current status. To move the item to the INACTIVE list.
- /workTodoItems/{todoId} 	- HTTP Delete # to delete item - ToDoItemWork
- /newTodoItem 				- HTTP Post # Get a ToDoItemWork for a given Id
- /workTodoItems/{ids} 		- HTTP Put # restore inactive list of ToDo items

### Swagger 2

Swagger 2 is an open-source project and has been used in this application to describe and document RESTful APIs.
Please see the following end-points using Swagger UI.

- http://localhost:8080/swagger-ui.html#/

## git setup

You can clone using the https url with the following command:
    git clone https://github.com/dmadhawaw/Superr-Duperr.git
    
## Setup & Run microservice

This application has been developed  in spring boot micro-service architecture and has been used in-memory H2 database. The database migration scripts can be found in the below folder path:

This application can be run  as a spring-boot JAR file or Docker container (-> Docker Setup).

### DB migration scripts : /todolist_app/src/main/resources/db/migration

- V1__create_schema.sql - This DDL script will create main database schema [ToDoItem].
- V2__insert_schema.sql - This DML script will generate the required initial test data for the application.
- Spring-boot version - 2.5.1
- Java version - 1.8
- Junit version - 5

Once application is cloned in to the local folder , then you will have to run the below commands in the /SuperrDuperr folder:

> mvn clean install - ‘this will download all the dependencies into the m2 repository. It compiles all Java sources and package them into one JAR file.’
> java -jar target/todoapp-0.0.1-SNAPSHOT.jar - this will invoke the JAR file and will launch the application.

### Docker Setup

The superr-duperr microservice container image has been uploaded into the DockerHub.

To run the service please execute below command:

### Run service from DockerHub
> docker run -p 8080:8080 todo-list-service/todo-list-service-v1 //TODO


## To Build the container locally 
> docker build -t todo-list-service/todo-list-service-v1 .
> docker run -p 8080:8080 todo-list-service/todo-list-service-v1 //Correct

To access the application : 
> http://localhost:8080/


### ToDoListApplication

ToDoListApplication application mainly has 'TodoItemController' which is the main end point has exposed as REST API and application logic has been written in the service level and data will be retrieved via JPA data repositories, apart from the JPA entity classes has been defined and database scripts to generate initial ToDoItem list has been setup to instantiate at the time of application loading via FlyWay. The exception handling for mainly resource 'NOT_FOUND' & 'BAD_REQUEST' has been implemented via the Spring Controller Advice class.

- TodoItemController RESTful API has been exposed and each request will be delegated to the ToDoItemService and will manipulate the request integrating required data components and data will be retrieved accordingly.
- API Controller Advice has been implemented to manipulate exception handling for HttpStatus code NOT_FOUND & BAD_REQUEST. A custom exception has been implemented to manage ‘ResourceNotFoundException’.
- Request parameter has been validated using the Java validation framework and hence Controller Advice has been implemented to trigger any ‘ConstraintViolationException’ and HttpStatsu BAD_REQUES will be thrown.
- ApiResponse and ErrorResponse has been declared to manipulate the Api errors. It mainly contains error response details and status code.
- Lombok has been used to mock some of the entities in the application.

### Unit Testing
There are two testing targets: unit and integration.
The unit testing lives under SuperrDuperr/src/test and has been used mockito framework to mock the required components:

TodoItem Controller Unit Test : 
This tests will test the ToDoItem Controller end points in REST patterns using MockMvc URL mapping. This test will mock the required service components and data repository components.

ToDoItem Service Unit Test : 
This test will just test the ToDoItem/ToDoItemWork service component unit service implementation. In this case all the dependent data access components will be mocked. In this test also we instruct to JUnit to NOT to load any web environment or any controller components.

### Integration Testing
TodoItem Controller Integration Test : This test will tests the ToItem REST controller in REST style.

TodoItem Service Integration Test : this interacts between ToDo item service and it’s data dependencies. This tests doesn’t run any controller components, instead will isolate ToDoItem service and data components. This test connects to a real data source.

### TODO & Assumptions
- Since the current application has been implemented for first release , transaction handling should be implemented for REST idempotent methods.
- Request Filter Wrapper can be implemented to extend mainly for POST/PUT/ http methods.
- In this application main implementation is central to the ToDoItem Controller and ToDoItem service. As a better design approach to handle multiple list based on the ToDo Item List type can be separated as a separate Rest Controller and service accordingly.
- In this application assume user is already logged in and these REST end points will be mapped based on the user authorization and it's scope(Role).
- Assume the user login security will be placed and the user is already authenticated and authorized.

### Postman-Collection
The file POSTMAN collection has defined all the end-points URL tested with request body details.
> superr-duperr-service-v1.postman_collection.json

