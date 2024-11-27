<template>
    <div>
        <h2>Recipe List</h2>
        <ul>
            <li v-for="recipe in recipes" :key="recipe.id">
                <router-link :to="{ name: 'RecipeDetailView', params: { id: recipe.id } }">{{ recipe.name
                    }}</router-link>
            </li>
        </ul>
    </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';

export default {
    computed: {
        ...mapGetters(['recipes'])
    },
    methods: {
        ...mapActions(['fetchRecipes'])
    },
    created() {
        this.fetchRecipes();
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

a {
    text-decoration: none;
    color: #333;
}

a:hover {
    color: #000;
}
</style>