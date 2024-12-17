import axios from "axios";

const API_URL = "https://www.themealdb.com/api/json/v1/1/";

export const searchMealsByName = async (mealName) => {
  try {
    const response = await axios.get(`${API_URL}search.php?s=${mealName}`);
    return response.data.meals;
  } catch (error) {
    console.error("Error searching meals:", error.message);
  }
};

export const getMealById = async (mealId) => {
  try {
    const response = await axios.get(`${API_URL}lookup.php?i=${mealId}`);
    return response.data.meals[0];
  } catch (error) {
    console.error("Error getting meal details:", error.message);
  }
};

export const getRandomMeal = async () => {
  try {
    const response = await axios.get(`${API_URL}random.php`);
    return response.data.meals[0];
  } catch (error) {
    console.error("Error getting random meal:", error.message);
  }
};

export const filterByMainIngredient = async (ingredient) => {
  try {
    const response = await axios.get(`${API_URL}filter.php?i=${ingredient}`);
    return response.data.meals;
  } catch (error) {
    console.error("Error filtering by ingredient:", error.message);
  }
};

export const filterByCategory = async (category) => {
  try {
    const response = await axios.get(`${API_URL}filter.php?c=${category}`);
    return response.data.meals;
  } catch (error) {
    console.error("Error filtering by category:", error.message);
  }
};

export const filterByArea = async (area) => {
  try {
    const response = await axios.get(`${API_URL}filter.php?a=${area}`);
    return response.data.meals;
  } catch (error) {
    console.error("Error filtering by area:", error.message);
  }
};
