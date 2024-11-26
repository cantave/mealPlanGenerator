<template>
    <nav>
      <router-link :to="{ name: 'HomeView' }">Home</router-link>
      <router-link v-if="isAuthenticated" :to="{ name: 'AddRecipeView' }">Add Recipe</router-link>
      <router-link :to="{ name: 'RecipeListView' }">Recipes</router-link>
      <router-link v-if="isAuthenticated" :to="{ name: 'MealPlanFormView' }">Generate Meal Plan</router-link>
      <router-link v-if="isAuthenticated && userId" :to="{ name: 'MealPlanView', params: { userId: userId } }">View Meal Plan</router-link>
      <router-link v-if="!isAuthenticated" :to="{ name: 'LoginView' }">Login</router-link>
      <router-link v-if="!isAuthenticated" :to="{ name: 'RegisterView' }">Register</router-link>
      <router-link v-if="isAuthenticated" :to="{ name: 'UserProfileView' }">Profile</router-link>
      <button v-if="isAuthenticated" @click="logout">Logout</button>
    </nav>
  </template>
  
  <script>
  import { mapGetters, mapActions } from 'vuex';
  
  export default {
    name: 'NavBar',
    computed: {
      ...mapGetters(['userId']),
      isAuthenticated() {
        return !!this.userId;
      }
    },
    methods: {
      ...mapActions(['logout']),
      logout() {
        this.logout();
        this.$router.push('/login');
      }
    }
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
  
  button {
    color: #333;
    background: none;
    border: none;
    cursor: pointer;
    padding: 0.5rem 1rem;
    border-radius: 4px;
    transition: background-color 0.3s;
  }
  
  button:hover {
    background-color: #e0e0e0;
  }
  </style>
  