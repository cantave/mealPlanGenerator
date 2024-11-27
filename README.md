# MealPlanGenerator

A fullstack meal plan generator application using Java, Spring Boot, PostgreSQL, and Vue.js.

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

- Access the application at http://localhost:8081 for the backend and http://localhost:8080 for the frontend (default Vue.jsport).
- Use the application to add recipes, generate meal plans, and manage your dietary preferences.

## API Endpoints

### Recipes
- **POST /api/recipes** - Add a new recipe
- **GET /api/recipes** - Fetch all recipes
- **GET /api/recipes/{id}** - Fetch a recipe by its ID
- **PUT /api/recipes/{id}** - Update a recipe by its ID
- **DELETE /api/recipes/{id}** - Delete a recipe by its ID
- **GET /api/recipes/fetch-and-save** - Fetch recipes from TheMealDB API and save to the database

### Meal Plans
- **POST /api/mealplans** - Generate a meal plan
- **GET /api/mealplans/{userId}** - Fetch a user’s meal plans

### User Authentication
- **POST /api/auth/register** - Register a new user
- **POST /api/auth/login** - Log in a user

### User Profiles
- **GET /api/userprofiles/{userId}** - Fetch user profile by user ID
- **PUT /api/userprofiles/{userId}** - Update user profile by user ID

## Contributing

- Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License

- This project is licensed under the MIT License. 
