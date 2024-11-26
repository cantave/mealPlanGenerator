<template>
    <nav>
        <router-link :to="{name: 'HomeView'}">Home</router-link>
        <router-link :to="{name: 'AddRecipeView'}">Add Recipe</router-link>
        <router-link :to="{name: 'RecipeListView'}">Recipes</router-link>
        <router-link :to="{name: 'MealPlanFormView'}">Generate Meal Plan</router-link>
        <router-link :to="{name: 'MealPlanView', params: {userId: 1}}">View Meal Plan</router-link>
        <router-link :to="{name: 'LoginView'}">Login</router-link>
        <router-link :to="{name: 'RegisterView'}">Register</router-link>
    </nav>
</template>

<script>
    export default {
        name: 'NavBar',
        props: ['userId']
    };
</script>

<style scoped>
    nav {
        display: flex;
        justify-content: center;
        gap: 1rem;
        background-color: #f8f8f8;
        padding: 1rem;
        border-bottom: 1px solid #ddd;
    }

    nav a {
        text-decoration: none;
        color: #333;
        padding: 0.5rem 1rem;
        border-radius: 4px;
        transition: background-color 0.3s;
    }

    nav a:hover {
        background-color: #e0e0e0;
    }
</style>