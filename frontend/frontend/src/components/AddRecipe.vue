<template>
    <h2>Add Recipe</h2>
    <form @submit.prevent="submitRecipe">
        <input v-model="name" placeholder="Recipe Name" />
        <input v-model="description" placeholder="Description" />
        <input v-model="category" placeholder="Category (e.g., Chicken, Pasta, etc...)" />
        <input v-model="area" placeholder="Area (e.g., Italian, Mexican, etc...)" />
        <input v-model="mealThumb" placeholder="Meal Thumbnail URL" />
        <input v-model="youtube" placeholder="YouTube URL" />
        <input v-model="ingredients" placeholder="Ingredients (comma-separated)" />
        <input v-model="measures" placeholder="Measures (comma-separated)" />
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
            description: '',
            category: '',
            area: '',
            mealThumb: '',
            youtube: '',
            ingredients: '',
            measures: '',
            instructions: '',
        };
    },
    methods: {
        async submitRecipe() {
            const recipe = {
                name: this.name,
                description: this.description,
                category: this.category,
                area: this.area,
                mealThumb: this.mealThumb,
                youtube: this.youtube,
                ingredients: this.ingredients.split(',').map(ingredient => ingredient.trim()),
                measures: this.measures.split(',').map(measure => measure.trim()),
                instructions: this.instructions
            };
            try {
                await addRecipe(recipe);
                this.$router.push('/recipes');
            } catch (error) {
                console.error('Error adding recipe:', error);
            }
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

input,
textarea,
button {
    margin-bottom: 0.5em;
    padding: 0.5em;
}
</style>