import { createStore } from "vuex";
import {
  getRecipes,
  getRecipeById,
  updateRecipe,
  getUserProfile,
  getMealPlans,
} from "@/services/apiService";
import { EventBus } from "@/eventBus";

const store = createStore({
  state: {
    userId: localStorage.getItem("user-id") || null,
    recipes: [],
    recipe: {},
    userProfile: {
      userImage: null,
      firstName: "",
      lastName: "",
    },
    mealPlan: null,
  },
  mutations: {
    setUserId(state, userId) {
      state.userId = userId;
      localStorage.setItem("user-id", userId);
    },
    clearUserId(state) {
      state.userId = null;
      localStorage.removeItem("user-id");
    },
    setRecipes(state, recipes) {
      state.recipes = recipes;
    },
    setRecipe(state, recipe) {
      state.recipe = recipe;
    },
    setUserProfile(state, profile) {
      state.userProfile = profile;
    },
    setMealPlan(state, mealPlan) {
      state.mealPlan = mealPlan;
    },
  },
  actions: {
    async fetchRecipes({ commit }) {
      try {
        const recipes = await getRecipes();
        commit("setRecipes", recipes);
      } catch (error) {
        console.error("Error fetching recipes:", error);
      }
    },
    async fetchRecipeById({ commit }, id) {
      try {
        const recipe = await getRecipeById(id);
        commit("setRecipe", recipe);
      } catch (error) {
        console.error("Error fetching recipe:", error);
      }
    },
    async updateRecipeById({ commit, state }, { id, updatedRecipe }) {
      try {
        await updateRecipe(id, updatedRecipe);
        const updatedRecipes = state.recipes.map((recipe) =>
          recipe.id === id ? { ...recipe, ...updatedRecipe } : recipe
        );
        commit("setRecipes", updatedRecipes);
        commit("setRecipe", updatedRecipe);
      } catch (error) {
        console.error("Error updating recipe:", error);
      }
    },
    async fetchUserProfile({ commit, state }) {
      try {
        const profile = await getUserProfile(state.userId);
        commit("setUserProfile", profile);
      } catch (error) {
        console.error("Error fetching user profile:", error);
      }
    },
    async fetchUserMealPlan({ commit }, userId) {
      try {
        const mealPlan = await getMealPlans(userId);
        commit("setMealPlan", mealPlan);
      } catch (error) {
        console.error("Error fetching meal plan:", error);
      }
    },
    logout({ commit }) {
      commit("clearUserId");
      localStorage.removeItem("user-token");
      localStorage.removeItem("user-id");
      EventBus.emit("user-logged-out");
    },
  },
  getters: {
    userId: (state) => state.userId,
    isAuthenticated: (state) => !!state.userId,
    recipes: (state) => state.recipes,
    recipe: (state) => state.recipe,
    userProfile: (state) => state.userProfile,
    userImage: (state) => state.userProfile.userImage,
    userName: (state) =>
      `${state.userProfile.firstName} ${state.userProfile.lastName}`,
    mealPlan: (state) => state.mealPlan,
  },
});

export default store;
