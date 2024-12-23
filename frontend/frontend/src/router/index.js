import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/views/HomeView.vue";
import LoginView from "@/views/LoginView.vue";
import RegisterView from "@/views/RegisterView.vue";
import AddRecipeView from "@/views/AddRecipeView.vue";
import RecipeListView from "@/views/RecipeListView.vue";
import EditRecipeView from "@/views/EditRecipeView.vue";
import RecipeDetailView from "@/views/RecipeDetailView.vue";
import MealPlanFormView from "@/views/MealPlanFormView.vue";
import MealPlanView from "@/views/MealPlanView.vue";
import UserProfileView from "@/views/UserProfileView.vue";
import UserProfileForm from "@/components/UserProfileForm.vue";
import MealSearchView from "@/views/MealSearchView.vue";

const routes = [
  {
    path: "/",
    name: "HomeView",
    component: HomeView,
  },

  {
    path: "/login",
    name: "LoginView",
    component: LoginView,
  },

  {
    path: "/register",
    name: "RegisterView",
    component: RegisterView,
  },

  {
    path: "/add-recipe",
    name: "AddRecipeView",
    component: AddRecipeView,
  },
  {
    path: "/recipes",
    name: "RecipeListView",
    component: RecipeListView,
  },
  {
    path: "/recipes/:id",
    name: "RecipeDetailView",
    component: RecipeDetailView,
  },
  {
    path: "/recipes/edit/:id",
    name: "EditRecipeView",
    component: EditRecipeView,
  },
  {
    path: "/generate-meal-plan",
    name: "MealPlanFormView",
    component: MealPlanFormView,
  },
  {
    path: "/meal-plan/:userId",
    name: "MealPlanView",
    component: MealPlanView,
    props: true,
  },
  {
    path: "/profile",
    name: "UserProfileView",
    component: UserProfileView,
  },
  {
    path: "/profile/edit",
    name: "UserProfileForm",
    component: UserProfileForm,
  },
  {
    path: "/meal-search",
    name: "MealSearchView",
    component: MealSearchView,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
