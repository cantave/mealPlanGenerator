<template>
    <div>  
        <h2>Meal Plan</h2>
        <ul>
            <li v-for="recipe in mealPlan.recipes" :key="recipe.id">
                {{ recipe.name }} - {{ recipe.ingredients.join(',') }}
            </li>
        </ul>
    </div>
</template>

<script>
import { getMealPlans } from '@/services/apiService';

    export default {
        props: ['userId'],
        data() {
            return {
                mealPlan: {
                    recipes: []
                }
            };
        },
        created() {
            getMealPlans(this.userId).then(response => {
                this.mealPlan = response.data[0];
            }).catch(error => {
                console.error('Error fetching meal plan:', error);
            });
        }
    };
</script>

<style scoped>
    h2 {
        color: #333;
    }
    ul {
        list-style-type: none;
        padding: 0;
    }
    li {
        padding: 0.5em;
        background: #f9f9f9;
        margin-bottom: 0.5em;
        border: 1px solid #ddd;
    }
</style>