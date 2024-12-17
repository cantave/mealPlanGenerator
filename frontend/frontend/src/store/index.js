import { createStore } from "vuex";
import {
  getRecipes,
  getRecipeById,
  updateRecipe,
  getUserProfile,
  getMealPlans,
  deleteMealPlan,
  deleteUserProfile,
  addRecipe,
} from "@/services/apiService";
import { EventBus } from "@/eventBus";
import {
  getMealById,
  getRandomMeal,
  searchMealsByName,
  filterByMainIngredient,
  filterByCategory,
  filterByArea,
} from "@/services/mealDbService";

const store = createStore({
  state: {
    userId: localStorage.getItem("user-id") || null,
    recipes: [],
    recipe: {
      name: '',
      description: '',
      category: '',
      area: '',
      mealThumb: '',
      youtube: '',
      ingredients: [],
      measures: [],
      instructions: '',
    },
    userProfile: {
      userImage: null,
      firstName: "",
      lastName: "",
    },
    mealPlans: [],
    meals: [],
    randomMeal: null,
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
    updateRecipeField(state, {field, value}){
      state.recipe[field] = value;
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
    setMeals(state, meals) {
      //console.log('Setting meals:', meals);
      state.meals = meals;
    },
    setRandomMeal(state, meal) {
      state.randomMeal = meal;
    },
  },
  actions: {
    async addRecipe({commit}, recipe) {
      try {
        const response = await addRecipe(recipe);
        commit('setRecipe', response);
        return response;
      } catch (error) {
        console.error('Error adding recipe:', error.message);
      }
    },
    async fetchMealsByName({ commit }, mealName) {
      try {
        const meals = await searchMealsByName(mealName);
        //console.log('Fetched meals:', meals);
        commit("setMeals", meals);
      } catch (error) {
        console.error("Error fetching meals by name:", error.message);
      }
    },
    async fetchMealById({ commit }, mealId) {
      try {
        const meal = await getMealById(mealId);
        commit("setRecipe", meal);
      } catch (error) {
        console.error("Error fetching meal by ID:", error.message);
      }
    },
    async fetchRandomMeal({ commit }) {
      try {
        const meal = await getRandomMeal();
        commit("setMeals", [meal]);
      } catch (error) {
        console.error("Error fetching random meal:", error.message);
      }
    },
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
    async deleteUserProfile({ commit }, userId) {
      try {
        await deleteUserProfile(userId);
        commit("clearUserId");
        EventBus.emit("user-logged-out");
      } catch (error) {
        console.error("Error deleting user profile:", error.message);
      }
    },

    async filterMealsByIngredient({ commit }, ingredient) {
      try {
        const meals = await filterByMainIngredient(ingredient);
        commit("setMeals", meals);
      } catch (error) {
        console.error("Error filtering meals by ingredient:", error.message);
      }
    },

    async filterMealsByCategory({ commit }, category) {
      try {
        const meals = await filterByCategory(category);
        commit("setMeals", meals);
      } catch (error) {
        console.error("Error filtering meals by category:", error.message);
      }
    },

    async filterMealsByArea({ commit }, area) {
      try {
        const meals = await filterByArea(area);
        commit("setMeals", meals);
      } catch (error) {
        console.error("Error filtering meals by area:", error.message);
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
    meals: (state) => state.meals,
    randomMeal: (state) => state.randomMeal,
  },
});

export default store;
