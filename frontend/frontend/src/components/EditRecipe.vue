<template>
    <div>
        <h2>Edit Recipe</h2>
        <form @submit.prevent="editRecipe">
            <input v-model="name" placeholder="Recipe Name" />
            <textarea v-model="ingredients" placeholder="Ingredients (comma-separated)" />
            <textarea v-model="instructions" placeholder="Instructions" />
            <button type="submit">Save Changes</button>
        </form>
    </div>
</template>

<script>
import { getRecipeById, updateRecipe } from '@/services/apiService';

export default {
    data() {
        return {
            name: '',
            ingredients: '',
            instructions: ''
        };
    },
    created() {
        const id = this.$route.params.id;
        getRecipeById(id).then(response => {
            const recipe = response.data;
            this.name = recipe.name;
            this.ingredients = recipe.ingredients.join(', ');
            this.instructions = recipe.instructions;
        }).catch(error => {
            console.error('Error fetching recipe:', error);
        });
    },
    methods: {
        editRecipe() {
            const id = this.$route.params.id;
            const updatedRecipe = {
                name: this.name,
                ingredients: this.ingredients.split(',').map(ingredient => ingredient.trim()),
                instructions: this.instructions
            };
            updateRecipe(id, updatedRecipe).then(() => {
                this.$router.push(`/recipes/${id}`);
            }).catch(error => {
                console.error('Error updating recipe:', error);
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

input,
textarea,
button {
    margin-bottom: 0.5em;
    padding: 0.5em;
}
</style>