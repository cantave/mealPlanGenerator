<template>
    <div>
        <h2>{{ recipe.name }}</h2>
        <div>
            <h3>Ingredients</h3>
            <ul>
                <li v-for="ingredient in recipe.ingredients" :key="ingredient"> {{ ingredient }}</li>
            </ul>
            <h3>Instructions</h3>
            <p>{{ recipe.instructions }}</p>
            <router-link :to="{ name: 'EditRecipeView', params: { id: recipe.id } }">Edit</router-link>
        </div>
    </div>
</template>

<script>
import { getRecipeById } from '@services/apiService';

export default {
    data() {
        return {
            recipe: {}
        };
    },
    async created() {
        const id = this.$route.params.id;
        try {
            const response = await getRecipeById(id);
            this.recipe = response;
        } catch (error) {
            console.error('Error fetching recipe:', error);
        }
    }
};
</script>

<style scoped>
h2,
h3 {
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