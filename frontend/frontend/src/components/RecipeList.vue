<template>
    <div>
        <h2>Recipe List</h2>
        <ul>
            <li v-for="recipe in recipes" :key="recipe.id">
                <router-link :to="{ name: 'RecipeDetailView', params: { id: recipe.id } }">{{ recipe.name
                    }}</router-link>
                <span @click="deleteRecipe(recipe.id)">
                    <img :src="trashBin" alt="trash-bin">
                </span>
            </li>
        </ul>
    </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import trashBin from '@/assets/trash-bin.png';

export default {
    computed: {
        ...mapGetters(['recipes']),
        trashBin() {
            return trashBin;
        },
    },
    methods: {
        ...mapActions(['fetchRecipes', 'deleteRecipe'])
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
    display: flex;
    justify-content: space-between;
    align-items: center;
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

img {
    width: 20px;
    height: auto;
    transition: filter 0.3s ease;
}

img:hover {
    filter: invert(24%) sepia(77%) saturate(5000%) hue-rotate(1deg) brightness(96%) contrast(97%);
}
</style>