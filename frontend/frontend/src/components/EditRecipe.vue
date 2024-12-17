<template>
    <div>
        <h2>Edit Recipe</h2>
        <form @submit.prevent="editRecipe">
            <input v-model="name" placeholder="Recipe Name" />
            <textarea v-model="description" placeholder="Description" />
            <input v-model="category" placeholder="Category (e.g., Chicken, Pasta, etc...)" />
            <input v-model="area" placeholder="Area (e.g., Italian, Mexican, etc...)" />
            <input v-model="mealThumb" placeholder="Meal Thumbnail URL" />
            <input v-model="youtube" placeholder="YouTube URL" />
            <textarea v-model="ingredients" placeholder="Ingredients (comma-separated)" />
            <input v-model="measures" placeholder="Measures (comma-separated)" />
            <textarea v-model="instructions" placeholder="Instructions" />
            <button type="submit">Save Changes</button>
        </form>
    </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';

export default {
    computed: {
        ...mapGetters(['recipe'])
    },
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
    watch: {
        recipe: {
            immediate: true,
            handler(newRecipe) {
                if (newRecipe) {
                    this.name = newRecipe.name;
                    this.description = newRecipe.description;
                    this.category = newRecipe.category;
                    this.area = newRecipe.area;
                    this.mealThumb = newRecipe.mealThumb;
                    this.youtube = newRecipe.youtube;
                    this.ingredients = newRecipe.ingredients.join(', ');
                    this.measures = newRecipe.measures.join(',');
                    this.instructions = newRecipe.instructions;
                }
            }
        }
    },
    created() {
        const id = this.$route.params.id;
        this.fetchRecipeById(id);
    },
    methods: {
        ...mapActions(['fetchRecipeById', 'updateRecipeById']),
        async editRecipe() {
            const id = this.$route.params.id;
            const updatedRecipe = {
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
                await this.updateRecipeById({ id, updatedRecipe });
                this.$router.push(`/recipes/${id}`);
            } catch (error) {
                console.error('Error updating recipe:', error);
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