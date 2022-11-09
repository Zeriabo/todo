# todo
Spring boot TODO application 

# Description 
Application that requires users to be logged in before they can call the APIs. One user can create multiple todo items and one todo item can only belong to a single user

# language 
Java 11 or above

# Framework 
Spring Boot

# Database 
PostgreSQL

# Endpoints

* POST /api/v1/signup: Sign up as an user of the system, using email & password
* POST /api/v1/signin: Sign in using email & password. The system will return the JWT token that can be used to call the APIs that follow
* PUT /api/v1/changePassword: Change user’s password
* GET /api/v1/todos?status=[status]: Get a list of todo items. Optionally, a status query param can be included to return only items of specific status. If not present, return all items
* POST /api/v1/todos: Create a new todo item
* PUT /api/v1/todos/:id: Update a todo item
* DELETE /api/v1/todos/:id: Delete a todo item
