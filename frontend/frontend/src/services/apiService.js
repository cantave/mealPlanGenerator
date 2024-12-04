import axios from "axios";

const API_URL = "http://localhost:8081/api";

const getToken = () => localStorage.getItem("user-token");

const axiosInstance = axios.create({
  baseURL: API_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

export const updateAxiosToken = () => {
  const token = getToken();
  axiosInstance.defaults.headers.Authorization = token ? `Bearer ${token}` : "";
};

export const updateToken = updateAxiosToken;

updateAxiosToken();

export const addRecipe = async (recipe) => {
  try {
    const response = await axiosInstance.post("/recipes", recipe);
    return response.data;
  } catch (error) {
    console.error("Error adding recipe:", error);
  }
};

export const getRecipes = async () => {
  try {
    const response = await axiosInstance.get("/recipes");
    return response.data;
  } catch (error) {
    console.error("Error fetching recipes:", error);
  }
};

export const generateMealPlan = async (mealPlan) => {
  try {
    const response = await axiosInstance.post("/mealplans", mealPlan);
    return response.data;
  } catch (error) {
    console.error("Error generating meal plan:", error);
    throw error;
  }
};

export const getMealPlans = async (userId) => {
  try {
    const response = await axiosInstance.get(`/mealplans/user/${userId}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching meal plans:", error);
  }
};

export const getMealPlanById = async (id) => {
  try {
    const response = await axiosInstance.get(`/mealplans/${id}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching meal plan:", error);
  }
};

export const updateMealPlan = async (id, mealPlanDetails) => {
  try {
    const response = await axiosInstance.post(
      `/mealplans/${id}`,
      mealPlanDetails
    );
    return response.data;
  } catch (error) {
    console.error("Error updating meal plan:", error);
  }
};

export const deleteMealPlan = async (id) => {
  try {
    await axiosInstance.delete(`/mealplans/${id}`);
  } catch (error) {
    console.error("Error deleting meal plan:", error);
  }
};

export const getRecipeById = async (id) => {
  try {
    const response = await axiosInstance.get(`/recipes/${id}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching recipe:", error);
  }
};

export const updateRecipe = async (id, recipe) => {
  try {
    const response = await axiosInstance.put(`/recipes/${id}`, recipe);
    return response.data;
  } catch (error) {
    console.error("Error updating recipe:", error);
  }
};

export const getUserProfile = async (id) => {
  try {
    const response = await axiosInstance.get(`/user/${id}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching user profile:", error);
  }
};

export const updateUserProfile = async (id, userDetails) => {
  try {
    const response = await axiosInstance.put(`/user/${id}`, userDetails);
    return response.data;
  } catch (error) {
    console.error("Error updating user profile:", error);
  }
};

export const deleteUserProfile = async (id) => {
  try {
    await axiosInstance.delete(`/user/${id}`);
  } catch (error) {
    console.error("Error deleting user profile:", error.message);
  }
};
