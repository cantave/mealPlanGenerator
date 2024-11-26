import axios from "axios";

const API_URL = "http://localhost:8081/api";

export const addRecipe = (recipe) => {
  return axios.post(`${API_URL}/recipes`, recipe);
};

export const getRecipes = () => {
  return axios.get(`${API_URL}/recipes`);
};

export const generateMealPlan = (mealPlan) => {
  return axios.post(`${API_URL}/mealplans`, mealPlan);
};

export const getMealPlans = (userId) => {
  return axios.post(`${API_URL}/mealplans/${userId}`);
};

export const getRecipeById = (id) => {
  return axios.get(`${API_URL}/recipes/${id}`);
};

export const updateRecipe = (id, recipe) => {
  return axios.get(`${API_URL}/recipes/${id}`, recipe);
};
