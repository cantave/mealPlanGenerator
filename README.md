# MealPlanGenerator

A fullstack meal plan generator application using Java, Spring Boot, PostgreSQL, and Vue.js. This application allows users to create personalized meal plans based on their dietary preferences and caloric needs. Users can manage recipes through adding, updating, and deleting options, and generate meal plans according to specific nutritional constraints. The app features user authentication with JWT for secure access, and includes functionalities for managing user profiles.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Introduction

MealPlanGenerator is a fullstack application designed to help users create and manage meal plans. The backend is built with Java, Spring Boot, and PostgreSQL, while the frontend is developed using Vue.js.

## Features

- Add and manage recipes
- Generate meal plans based on user preferences
- View meal plans and recipes
- Simple and intuitive user interface
- **NEW**: Fetch and save recipes from TheMealDB API
- **NEW**: Add meals to specific meal times (breakfast, lunch, dinner) in meal plans

## Technologies Used

- **Backend**: Java, Spring Boot, Spring Data JPA, Spring Security, PostgreSQL
- **Frontend**: Vue.js, Axios
- **Tools**: IntelliJ IDEA, VS Code, pgAdmin4, Postman

## Installation

### Backend

1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/mealPlanGenerator.git
2. Navigate to backend directory
   ```sh
   cd mealPlanGenerator/backend
3. Set up the PostgreSQL database and update application.properties with your database credentials.
4. Run the Spring Boot application:
   ```sh
   ./mvnw spring-boot:run

### Frontend

1. Navigate to the frontend directory:
   ``sh
   cd mealPlanGenerator/frontend
2. Install dependencies:
   ```sh
   npm install
3. Start the Vue.js application:
   ```sh
   npm run serve

### Usage

- Access the application at http://localhost:8081 for the backend and http://localhost:8082 for the frontend (default Vue.jsport).
- Use the application to add recipes, generate meal plans, and manage your dietary preferences.

## API Endpoints

### Recipes
- **POST /api/recipes** - Add a new recipe
- **GET /api/recipes** - Fetch all recipes
- **GET /api/recipes/{id}** - Fetch a recipe by its ID
- **PUT /api/recipes/{id}** - Update a recipe by its ID
- **DELETE /api/recipes/{id}** - Delete a recipe by its ID
- **Frontend Integration:** - Fetch recipes from TheMealDB API to display them in the application

### Meal Plans
- **POST /api/mealplans** - Generate a meal plan
- **GET /api/mealplans/{userId}** - Fetch a user’s meal plans

### User Authentication
- **POST /api/auth/register** - Register a new user
- **POST /api/auth/login** - Log in a user


### User Profiles
- **GET /api/user/{userId}** - Fetch user profile by user ID
- **PUT /api/user/{userId}** - Update user profile by user ID
- **DELETE /api/user/{userId}** - Delete user profile by user ID

## Next Steps

### Phase 9: Debugging and Testing
- **Unit Testing**:
  - Write unit tests for all backend services and controllers.
  - Implement unit tests for frontend components.
- **Integration Testing**:
  - Write integration tests for backend and frontend interactions.
  - Ensure that all API endpoints work correctly with the frontend.
- **End-to-End Testing**:
  - Implement end-to-end tests to verify the entire workflow of the application.
  - Use tools like Selenium or Cypress for automated testing.
- **Debugging**:
  - Identify and fix any bugs found during testing.
  - Ensure the application functions as expected across different browsers and devices.
 
### Phase 10: Deployment
- **Backend Deployment**:
   - Deploy the backend on Heroku or Render.
- **Frontend Deployment**:
   - Deploy the frontend on Netlify.

### Phase 11: UI/UX Enhancements
- **Improve UI/UX Design**:
  - Enhance the user interface for a more intuitive and visually appealing experience.
  - Improve accessibility to ensure the application is usable by everyone.
- **User Feedback**:
  - Collect and incorporate user feedback to continuously improve the application.

These phases outline the next steps for your project, focusing on integrating TheMealDB API, debugging and testing, and enhancing the UI/UX design.

## Contributing

- Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License

- This project is licensed under the MIT License. 
