<template>
    <div>
        <h2 v-if="recipe.name">{{ recipe.name }}</h2>
        <div v-if="recipe.name">
            <p>{{ recipe.description }}</p>
            <h3>Ingredients</h3>
            <ul>
                <li v-for="(ingredient, index) in recipe.ingredients" :key="index">
                    {{ ingredient }} - {{ recipe.measures[index] }}</li>
            </ul>
            <h3>Instructions</h3>
            <ul>
                <li v-for="(step, index) in splitInstructions(recipe.instructions)" :key="index">{{ step }}</li>
            </ul>
            <router-link v-if="recipe.id" :to="{ name: 'EditRecipeView', params: { id: recipe.id } }">Edit</router-link>
        </div>
    </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';

export default {
    computed: {
        ...mapGetters(['recipe'])
    },
    methods: {
        ...mapActions(['fetchRecipeById']),
        splitInstructions(instructions) {
            if (!instructions) {
                return [];
            }
            return instructions.split(/\r?\n\d+ |\r?\n/).filter(Boolean);
        }
    },
    created() {
        const id = this.$route.params.id;
        this.fetchRecipeById(id);
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