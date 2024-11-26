<template>
    <div>
        <h2>Generate Meal Plan</h2>
        <form @submit.prevent="generatePlan">
            <input v-model="userId" placeholder="User ID" />
            <input v-model="date" type="date" />
            <button type="submit">Generate Meal Plan</button>
        </form>
        <div v-if="generatedMealPlan">
            <p>Meal plan generated successfully!</p>
            <router-link :to="{name: 'MealPlan', params: {userId: userId}}">View Meal Plan</router-link>
        </div>
    </div>
</template>

<script>
import { generateMealPlan } from '@/services/apiService';

    export default {
        data() {
            return {
                userId: '',
                date: '',
                generatedMealPlan: null
            };
        },
        methods: {
            generatePlan() {
                const mealPlan = {
                    userId: this.userId,
                    date: this.date,
                    recipes: [] //enhance to inculde selected recipes
                };
                generateMealPlan(mealPlan).then(response => {
                    console.log('Meal plan generated:', response.data);
                }).catch(error => {
                    console.error('Error generating meal plan:', error);
                });
            }
        }   
    };
</script>

<style scoped>
    h2 {
        color: #333;
    }
    form {
        display: flex;
        flex-direction: column;
    }
    input, button {
        margin-bottom: 0.5em;
        padding: 0.5em;
    }
</style>