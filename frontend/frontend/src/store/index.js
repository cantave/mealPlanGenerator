import { createStore } from "vuex";
import {
  getRecipes,
  getRecipeById,
  updateRecipe,
  getUserProfile,
  getMealPlans,
  deleteMealPlan,
  deleteUserProfile,
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
    mealPlans: [],
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
    setMealPlans(state, mealPlans) {
      state.mealPlans = mealPlans;
    },
    removeMealPlan(state, mealPlanId) {
      state.mealPlans = state.mealPlans.filter(
        (plan) => plan.id !== mealPlanId
      );
    },
  },
  actions: {
    async fetchRecipes({ commit }) {
      try {
        const recipes = await getRecipes();
        commit("setRecipes", recipes);
      } catch (error) {
        console.error("Error fetching recipes:", error.message);
      }
    },
    async fetchRecipeById({ commit }, id) {
      try {
        const recipe = await getRecipeById(id);
        commit("setRecipe", recipe);
      } catch (error) {
        console.error("Error fetching recipe:", error.message);
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
        console.error("Error updating recipe:", error.message);
      }
    },
    async fetchUserProfile({ commit }) {
      try {
        const profile = await getUserProfile(this.state.userId);
        commit("setUserProfile", profile);
      } catch (error) {
        console.error("Error fetching user profile:", error.message);
      }
    },
    async fetchUserMealPlans({ commit }, userId) {
      try {
        const mealPlans = await getMealPlans(userId);
        commit("setMealPlans", mealPlans);
      } catch (error) {
        console.error("Error fetching meal plans:", error.message);
      }
    },
    async deleteMealPlan({ commit }, mealPlanId) {
      try {
        await deleteMealPlan(mealPlanId);
        commit("removeMealPlan", mealPlanId);
      } catch (error) {
        console.error("Error deleting meal plan:", error.message);
      }
    },
    async deleteUserProfile({commit}, userId){
      try {
        await deleteUserProfile(userId);
        commit("clearUserId");
        EventBus.emit("user-logged-out");
      } catch (error) {
        console.error("Error deleting user profile:", error.message);
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
    mealPlans: (state) => state.mealPlans,
  },
});

export default store;
