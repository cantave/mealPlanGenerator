<template>
  <nav>
    <router-link class="nav-link" :to="{ name: 'HomeView' }">Home</router-link>
    <router-link v-if="isAuthenticated" class="nav-link" :to="{ name: 'MealSearchView' }">Search Meals</router-link>
    <router-link v-if="isAuthenticated" class="nav-link" :to="{ name: 'AddRecipeView' }">Add Recipe</router-link>
    <router-link v-if="isAuthenticated" class="nav-link" :to="{ name: 'RecipeListView' }">Recipes</router-link>
    <router-link v-if="isAuthenticated" class="nav-link" :to="{ name: 'MealPlanFormView' }">Generate Meal
      Plan</router-link>
    <router-link v-if="isAuthenticated && userId" class="nav-link"
      :to="{ name: 'MealPlanView', params: { userId: userId } }">View Meal Plan</router-link>
    <router-link v-if="!isAuthenticated" class="nav-link" :to="{ name: 'LoginView' }">Login</router-link>
    <router-link v-if="!isAuthenticated" class="nav-link" :to="{ name: 'RegisterView' }">Register</router-link>
    <router-link v-if="isAuthenticated" class="nav-link" :to="{ name: 'UserProfileView' }">Profile</router-link>
    <button v-if="isAuthenticated" class="nav-link" @click="handleLogout">Logout</button>
  </nav>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import { EventBus } from '@/eventBus';

export default {
  name: 'NavBar',
  computed: {
    ...mapGetters(['isAuthenticated', 'userId']),
  },
  mounted() {
    EventBus.on('user-logged-in', this.updateUserStatus);
    EventBus.on('user-logged-out', this.updateUserStatus);
  },
  methods: {
    ...mapActions(['logout']),
    handleLogout() {
      this.logout();
      this.$router.push('/');
    },
    updateUserStatus() {
      this.$forceUpdate();
    }
  },
  beforeUnmount() {
    EventBus.off('user-logged-in', this.updateUserStatus);
    EventBus.off('user-logged-out', this.updateUserStatus);
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

.nav-link {
  text-decoration: none;
  color: #333;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  transition: background-color 0.3s;
  background: none;
  border: none;
  cursor: pointer;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
}

.nav-link:hover {
  background-color: #e0e0e0;
}
</style>
idea