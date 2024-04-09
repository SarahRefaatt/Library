# Library
Books Library

---

## Book Controller

The `BookController` class defines RESTful endpoints to manage book resources.

### Endpoints

#### 1. Add Book
- **HTTP Method**: POST
- **Path**: /api/book/add
- **Description**: Adds a new book to the system.
- **Request Body**: Book object representing the book to be added.
- **Response**: None

#### 2. Get All Books
- **HTTP Method**: GET
- **Path**: /api/book/get_all
- **Description**: Retrieves a list of all books available in the system.
- **Response**: List of Book objects.

#### 3. Find Book by ID
- **HTTP Method**: GET
- **Path**: /api/book/get_id
- **Description**: Retrieves a book by its unique identifier.
- **Query Parameter**: `id` (Long) - ID of the book to be retrieved.
- **Response**: Book object representing the requested book.

#### 4. Update Book
- **HTTP Method**: PUT
- **Path**: /api/book/update
- **Description**: Updates an existing book with new information.
- **Request Body**: Book object representing the updated information of the book.
- **Response**: Book object representing the updated book.

#### 5. Delete Book
- **HTTP Method**: DELETE
- **Path**: /api/book/delete
- **Description**: Deletes a book from the system.
- **Query Parameter**: `id` (Long) - ID of the book to be deleted.
- **Response**: None

### Usage

- Use the provided endpoints to manage books in the system.
- Send HTTP requests to the specified endpoints to perform CRUD operations on books.
- Ensure proper authentication and authorization mechanisms are in place to protect the endpoints if needed.


---

## Patron Controller


### Endpoints

#### 1. Add Patron
- **HTTP Method**: POST
- **Path**: /api/patron/add
- **Description**: Adds a new patron to the system.
- **Request Body**: Patron object representing the patron to be added.
- **Response**: None

#### 2. Get All Patrons
- **HTTP Method**: GET
- **Path**: /api/patron/get_all
- **Description**: Retrieves a list of all patrons available in the system.
- **Response**: List of Patron objects.

#### 3. Find Patron by ID
- **HTTP Method**: GET
- **Path**: /api/patron/get_id
- **Description**: Retrieves a patron by its unique identifier.
- **Query Parameter**: `id` (Long) - ID of the patron to be retrieved.
- **Response**: Patron object representing the requested patron.

#### 4. Update Patron
- **HTTP Method**: PUT
- **Path**: /api/patron/update
- **Description**: Updates an existing patron with new information.
- **Request Body**: Patron object representing the updated information of the patron.
- **Response**: Patron object representing the updated patron.

#### 5. Delete Patron
- **HTTP Method**: DELETE
- **Path**: /api/patron/delete
- **Description**: Deletes a patron from the system.
- **Query Parameter**: `id` (Long) - ID of the patron to be deleted.
- **Response**: None

---

## Borrowing Record Controller


### Endpoints

#### 1. Borrow Book
- **HTTP Method**: POST
- **Path**: /api/borrow/book/patron
- **Description**: Records a borrowing event where a patron borrows a book.
- **Request Parameters**:
  - `bookId` (Long) - ID of the book being borrowed.
  - `patronId` (Long) - ID of the patron borrowing the book.
- **Response**: None

#### 2. Return Book
- **HTTP Method**: PUT
- **Path**: /api/borrow/book/patron
- **Description**: Records a return event where a patron returns a borrowed book.
- **Request Parameters**:
  - `bookId` (Long) - ID of the book being returned.
  - `patronId` (Long) - ID of the patron returning the book.
- **Response**: None

---

These controllers provide endpoints to manage patrons and borrowing records in the system. Use the provided endpoints to perform CRUD operations and manage borrowing activities for books and patrons. Ensure proper authentication and authorization mechanisms are in place to protect the endpoints if needed.

--- 

---
## Authentication using JWT

## Authentication Controller

The `AuthenticationController` class provides RESTful endpoints for user authentication and registration.

### Endpoints

#### 1. Authenticate User
- **HTTP Method**: POST
- **Path**: /api/token
- **Description**: Authenticates a user by validating the provided email and password. Generates a JWT token upon successful authentication.
- **Request Body**: AuthenticationRequest object containing the email and password of the user.
- **Response**: JWT token if authentication is successful; error message otherwise.

#### 2. Register User
- **HTTP Method**: POST
- **Path**: /api/register
- **Description**: Registers a new user in the system.
- **Request Body**: RegisterDto object containing the username and password of the user to be registered.
- **Response**: Success message if registration is successful; error message if username is already taken.

### Dependencies

- `UserRepository`: Repository for managing user entities.
- `RoleRepository`: Repository for managing role entities.
- `PasswordEncoder`: Used for encoding user passwords securely.
- `AuthenticationManager`: Spring Security's authentication manager for authenticating users.
- `UserDao`: Data access object for user-related operations.
- `JwtTokenUtil`: Utility class for generating JWT tokens.

### Usage

- Use the provided endpoints for user authentication and registration.
- Send HTTP requests to the specified endpoints with appropriate request bodies to authenticate users and register new users.
- Ensure proper authentication and authorization mechanisms are in place to protect the endpoints if needed.


### Postman for generating token
  {
        "email":"user",
        "password":"user"
    }



# ApiExceptionHandler Class

The `ApiExceptionHandler` class is designed to handle different types of exceptions that may occur during the execution of your API. Below is the breakdown of the exception handling implemented in this class:

## 1. `handleApiResponseException`

- **Description**: This method handles instances of `ApiRequestException`.
- **Functionality**: It constructs an `ApiException` object with details about the exception and returns a `ResponseEntity` with a status code of 400 (Bad Request) along with the constructed `ApiException` object.

## 2. `handleBusinessException`

- **Description**: This method handles instances of `NoSuchElementException`.
- **Functionality**: It constructs a `Map` containing error details and returns a `ResponseEntity` with a status code of 500 (Internal Server Error) along with the error map.

## 3. `handleInvalidValidArgument`

- **Description**: This method handles instances of `ConstraintViolationException`.
- **Functionality**: It constructs a `Map` containing error details and returns it. The response status is set to 500 (Internal Server Error).

## Overall Functionality

This class provides centralized exception handling for your API, ensuring consistent error responses are returned to the clients. By handling exceptions appropriately, it provides meaningful feedback to users and maintains the robustness of your application. Proper exception handling is a good practice to ensure smooth operation and user satisfaction.