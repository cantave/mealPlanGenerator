import axios from "axios";

const API_URL = "http://localhost:8081/api";

const getToken = () => localStorage.getItem('user-token');

const axiosInstance = axios.create({
  baseURL: API_URL,
  headers: {
    Authorization: `Bearer ${getToken()}`,
    'Content-Type': 'application/json'
  }
});

export const updateToken = () => {
  const token = getToken();
  axiosInstance.defaults.headers.Authorization = token ? `Bearer ${token}` : '';
};

export const addRecipe = (recipe) => {
  return axiosInstance.post('/recipes', recipe);
};

export const getRecipes = () => {
  return axiosInstance.get('/recipes');
};

export const generateMealPlan = (mealPlan) => {
  return axiosInstance.post('/meal-plan', mealPlan);
};

export const getMealPlans = (userId) => {
  return axiosInstance.get(`/mealplans/${userId}`);
};

export const getRecipeById = (id) => {
  return axiosInstance.get(`/recipes/${id}`);
};

export const updateRecipe = (id, recipe) => {
  return axiosInstance.put(`/recipes/${id}`, recipe);
};

export const getUserProfile = (id) => {
  return axiosInstance.get(`/user/${id}`);
};

export const updateUserProfile = (id, userDetails) => {
  return axiosInstance.put(`/user/${id}`, userDetails);
};
