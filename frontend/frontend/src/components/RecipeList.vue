<template>
    <div>
        <h2>Recipe List</h2>
        <ul>
            <li v-for="recipe in recipes" :key="recipe.id">
                {{ recipe.name }} - {{ recipe.ingredients.join(',') }}
            </li>
        </ul>
    </div>
</template>

<script>
import { getRecipes } from '@/services/apiService';

    export default {
        data() {
            return {
                recipes: []
            };
        },
        async created() {
            try{
                const response =await getRecipes();
                this.recipes = response;
            } catch (error) {
                console.error('Error fetching recipes:', error);
            }
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