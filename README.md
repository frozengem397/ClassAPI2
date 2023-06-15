# Class Registration Web Application

This project is a small class registration web application API developed using Java Spring Boot and MongoDB. The purpose of this project is to practice and showcase the usage of Java Spring Boot framework in combination with MongoDB for building a web application.

The application provides API endpoints to manage classes and plans. Users can perform various operations such as retrieving all classes, retrieving a single class by its ID, creating a new class, updating an existing class, deleting a class, retrieving all plans, getting a user's plan, registering for a class, and deleting a class from a plan.

The API is deployed on Azure and can be accessed using the following base URLs:


**_Due to the Azure account issue, I no longer pay for the monthly fee (over 100$), so the link might not be able to be open._**

- Class API: [https://kimclass.azurewebsites.net/api/v1/classes](https://kimclass.azurewebsites.net/api/v1/classes)
- Plan API: [https://kimclass.azurewebsites.net/api/v1/plan](https://kimclass.azurewebsites.net/api/v1/plan)

Make sure to replace `{courseId}` and `{userId}` with the appropriate values when making requests to the API.

Feel free to explore and interact with the API endpoints to manage classes and plans.

## Note
Currently, due to lack of time, I'm still developing the FrontEnd, the FrontEnd is current halfway already, it allows users to add class, delete class, update class, search class. And also the users are able to register for class, but it has some requirements for different class, like whether the user has taken prereq class
or the class is added into the class collection. Still need to modify the UI and update Endpoints.


## Deployment

The application is deployed on Azure, allowing easy access to the API endpoints. You can use the provided base URLs to interact with the API and perform class registration operations.

To get started, follow the installation and usage instructions below:

1. Clone the repository.
2. Install the required dependencies using Maven.
3. Configure the MongoDB connection details in the application properties file.
4. Build and run the application using Spring Boot.
5. Access the API endpoints using the provided base URLs.

Make sure you have Java, Maven, and a MongoDB instance set up before running the application.

## Class Endpoints

### Get all classes

- **URL:** `/api/v1/classes`
- **Method:** `GET`
- **Description:** Retrieve all classes.
- **Response:** Returns a list of classes.
- **Example:** [https://kimclass.azurewebsites.net/api/v1/classes](https://kimclass.azurewebsites.net/api/v1/classes)

### Get a single class

- **URL:** `/api/v1/classes/{courseId}`
- **Method:** `GET`
- **Description:** Retrieve a single class by its course ID.
- **PathVariable:**
  - `{courseId}`: The unique identifier of the class.
- **Response:** Returns the details of the class.
- **Example:** [https://kimclass.azurewebsites.net/api/v1/classes/CSCI101](https://kimclass.azurewebsites.net/api/v1/classes/CSCI101)

### Create a class

- **URL:** `/api/v1/classes`
- **Method:** `POST`
- **Description:** Create a new class.
- **Request Body:** Provide the details of the class in the request body.
- **Response:** Returns the created class.
- **Example:** [https://kimclass.azurewebsites.net/api/v1/classes](https://kimclass.azurewebsites.net/api/v1/classes)

### Update a class

- **URL:** `/api/v1/classes/{courseId}`
- **Method:** `PUT`
- **Description:** Update an existing class by its course ID.
- **PathVariable:**
  - `{courseId}`: The unique identifier of the class to be updated.
- **Request Body:** Provide the updated details of the class in the request body.
- **Response:** Returns the updated class.
- **Example:** [https://kimclass.azurewebsites.net/api/v1/classes/CSCI101](https://kimclass.azurewebsites.net/api/v1/classes/CSCI101)

### Delete a class

- **URL:** `/api/v1/classes/{courseId}`
- **Method:** `DELETE`
- **Description:** Delete a class by its course ID.
- **PathVariable:**
  - `{courseId}`: The unique identifier of the class to be deleted.
- **Response:** Returns a success message if the class is deleted successfully.
- **Example:** [https://kimclass.azurewebsites.net/api/v1/classes/CSCI101](https://kimclass.azurewebsites.net/api/v1/classes/CSCI101)

## Plan Endpoints

### Get all plans

- **URL:** `/api/v1/plan`
- **Method:** `GET`
- **Description:** Retrieve all plans.
- **Response:** Returns a list of plans.
- **Example:** [https://kimclass.azurewebsites.net/api/v1/plan](https://kimclass.azurewebsites.net/api/v1/plan)

### Get a user's

 plan

- **URL:** `/api/v1/plan/{userId}`
- **Method:** `GET`
- **Description:** Retrieve a user's plan by their user ID.
- **PathVariable:**
  - `{userId}`: The unique identifier of the user.
- **Response:** Returns the user's plan details.
- **Example:** [https://kimclass.azurewebsites.net/api/v1/plan/123456](https://kimclass.azurewebsites.net/api/v1/plan/123456)

### Register for a class

- **URL:** `/api/v1/plan/{userId}`
- **Method:** `POST`
- **Description:** Register a user for a class and add it to their plan.
- **PathVariable:**
  - `{userId}`: The unique identifier of the user.
- **Request Body:** Provide the details of the class to be registered in the request body.
- **Response:** Returns the registered class in the user's plan.
- **Example:** [https://kimclass.azurewebsites.net/api/v1/plan/123456](https://kimclass.azurewebsites.net/api/v1/plan/123456)

### Delete a class from a plan

- **URL:** `/api/v1/plan/{userId}/{id}`
- **Method:** `DELETE`
- **Description:** Remove a class from a user's plan by its ID.
- **PathVariable:**
  - `{userId}`: The unique identifier of the user.
  - `{id}`: The unique identifier of the class to be removed.
- **Response:** Returns a success message if the class is successfully removed from the plan.
- **Example:** [https://kimclass.azurewebsites.net/api/v1/plan/123456/789012](https://kimclass.azurewebsites.net/api/v1/plan/123456/789012)


Feel free to explore and interact with the API endpoints to manage classes and plans. Please contact me for any questions!
