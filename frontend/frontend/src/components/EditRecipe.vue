<template>
    <div>
        <h2>Edit Recipe</h2>
        <form @submit.prevent="editRecipe">
            <input v-model="name" placeholder="Recipe Name" />
            <textarea v-model="description" placeholder="Description" />
            <textarea v-model="ingredients" placeholder="Ingredients (comma-separated)" />
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
            ingredients: '',
            instructions: '',
            description: ''
        };
    },
    watch: {
        recipe: {
            immediate: true,
            handler(newRecipe) {
                if (newRecipe) {
                    this.name = newRecipe.name;
                    this.description = newRecipe.description;
                    this.ingredients = newRecipe.ingredients.join(', ');
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
                ingredients: this.ingredients.split(',').map((ingredient) => ingredient.trim()),
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