<template>
    <h2>Add Recipe</h2>
    <form @submit.prevent="submitRecipe">
        <input v-model="name" placeholder="Recipe Name" />
        <input v-model="ingredients" placeholder="Ingredients (comma-separated)"/>
        <textarea v-model="instructions" placeholder="Instructions"></textarea>
        <button type="submit">Add Recipe</button>
    </form>
</template>

<script>
import { addRecipe } from '@services/apiService';

    export default {
        data() {
            return {
                name: '',
                ingredients: '',
                instructions: ''
            };
        },
        methods: {
            submitRecipe() {
                const recipe = {
                    name: this.name,
                    ingredients: this.ingredients.split(','),
                    instructions: this.instructions
                };
                addRecipe(recipe).then(response => {
                    console.log('Recipe added:', response.data);
                }).catch(error => {
                    console.error('Error adding recipe:', error);
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
</style>