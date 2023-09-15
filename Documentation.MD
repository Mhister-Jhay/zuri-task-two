# RESTful Person API

This project is a simple RESTful API that allows CRUD (Create, Read, Update, Delete) operations on a "person" resource. It interfaces with a database to store and retrieve person data.

## Table of Contents
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Usage](#usage)
    - [API Endpoints](#api-endpoints)
    - [Dynamic Parameter Handling](#dynamic-parameter-handling)
- [Testing](#testing)
- [Database Modeling](#database-modeling)

## Getting Started

### Prerequisites
- [Java](https://www.java.com/) (JDK 8 or higher)
- [Maven](https://maven.apache.org/)
- A relational database (e.g., MySQL, PostgreSQL)
- [Git](https://git-scm.com/)

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/Mhister-Jhay/zuri-task-two.git
   cd zuri-task-two

2. Build the project using Maven: 
    ```bash
   mvn clean install
   
3. Start the project using Maven:
    ```bash
   mvn spring-boot:run
   
The application will now be running on:  http://localhost:8512
The deployed application is also running on: https://zuri-task-two-msgq.onrender.com


## Usage
### API Endpoints

The API provides the following endpoints:

- POST /api:
    - Create a new person by providing the person's name in the request body.

- GET /api/{personId}:
    - Retrieve details of a person by their ID.

- PUT /api/{personId}:
    - Update details of an existing person by ID.

- DELETE /api/{personId}
    - Remove a person by their ID.

#### Create a New Person

To create a new person, make a POST request to /api with a JSON request body containing the person's name:
    

        POST /api
        Content-Type: application/json
        
        {
            "name": "John Doe"
        }


Response:

        {
            "id": 1
            "name": "John Doe"
        }


#### Retrieve Details of a person

To retrieve details of a person, make a GET request to /api/{personId} replacing {personId} with the Id of the person:


        GET /api/1
        Content-Type: application/json
        


Response:

        {
            "id": 1
            "name": "John Doe"
        }

#### Update Details of a person

To update details of a person, make a PUT request to /api/{personId} replacing {personId} with the Id of the person, and a JSON request body containing the person's name:


        PUT /api/1
        Content-Type: application/json
        {
            "name": "Moses Bliss"
        }




Response:

        {
            "id": 1
            "name": "Moses Bliss"
        }

#### Delete Details of a person

To delete details of a person, make a DELETE request to /api/{personId} replacing {personId} with the Id of the person:


        DELETE /api/1
        Content-Type: application/json



Response:

        {
            "Person with id 1 Deleted Successfully"
        }

### Dynamic Parameter Handling
#### Retrieve Details of a person dynamically

The details of a person can also be retrieved using the name attribute by adding the name value as a request parameter to the endpoint as seen below:


        GET /api?name=John Doe
        Content-Type: application/json



Response:

        {
            "id": 1
            "name": "John Doe"
        }
#### Delete Details of a person

The details of a person can also be deleted using the name attribute by adding the name value as a request parameter to the endpoint as seen below:


        DELETE /api?name=John Doe
        Content-Type: application/json



Response:

        {
            "Person with id 1 Deleted Successfully"
        }

## Testing
Automated tests are available in the test package of this project. A test script, written in Java using the JUnit framework, is already located in the test package. This is the link to the test package: https://github.com/Mhister-Jhay/zuri-task-two/tree/main/src/test/java/org/jhay To run the tests, use the following Maven command:
        
        mvn test

This command will execute the test script, and the results will be displayed in the terminal.

### Test Scenarios

The test cases cover various scenarios, including:

- Creating a new person
    - With valid name
    - With blank name
    - With invalid name format
    - With missing required fields

- Retrieving person details
    - By valid ID
    - By invalid ID
    - By non-existent ID

- Updating person details
    - With valid name
    - With blank name
    - With invalid name
    - For non-existent person

- Deleting a person
    - By valid ID
    - By invalid ID
    - For non-existent person


## Database Modeling
The database used in this project consists of a single table named "person" with two columns: "id" (primary key) and "name" (unique).

The link to the UML diagram: https://github.com/Mhister-Jhay/zuri-task-two/blob/main/src/main/resources/UML_task-2-Zuri.png

The link to the ERD diagram: https://github.com/Mhister-Jhay/zuri-task-two/blob/main/src/main/resources/ERD_task-2-Zuri.png

The link to the Documentation: https://github.com/Mhister-Jhay/zuri-task-two/blob/main/Documentation.MD

