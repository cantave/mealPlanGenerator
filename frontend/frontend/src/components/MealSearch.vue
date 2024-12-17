<template>
    <div>
        <h2>Search for Meals</h2>
        <input v-model="mealName" @keyup.enter="searchMeals" placeholder="Enter meal name">
        <button @click="searchMeals">Search</button>
        <button @click="fetchRandomMeal">Random</button>
        <div v-if="meals && meals.length">
            <h3>Search Results</h3>
            <ul>
                <li v-for="meal in meals" :key="meal.idMeal">
                    <h4>{{ meal.strMeal }}</h4>
                    <div class="img-container">
                        <img :src="meal.strMealThumb" alt="Meal Thumbnail">
                    </div>
                    <p><strong>Category:</strong>{{ meal.strCategory }}</p>
                    <p><strong>Area:</strong>{{ meal.strArea }}</p>
                    <div>
                        <p><strong>Ingredients:</strong></p>
                        <ul>
                            <li v-for="(ingredient, index) in getIngredients(meal)" :key="index">{{ ingredient }}</li>
                        </ul>
                    </div>
                    <div>
                        <p><strong>Instructions:</strong></p>
                        <ul>
                            <li v-for="(step, index) in splitInstructions(meal.strInstructions)" :key="index">{{ step
                                }}</li>
                        </ul>
                    </div>
                    
                    <a :href="meal.strYoutube" target="_blank">Watch Video</a>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
export default {
    data() {
        return {
            mealName: '',
        };
    },
    computed: {
        ...mapGetters(['meals']),
    },
    methods: {
        ...mapActions(['fetchMealsByName', 'fetchRandomMeal']),
        searchMeals() {
            //console.log('Searching for meals:', this.mealName)
            if (this.mealName) {
                this.fetchMealsByName(this.mealName);
            }
        },
        splitInstructions(instructions) {
            if (!instructions) {
                return [];
            }
            return instructions.split(/\r?\n\d+ |\r?\n/).filter(Boolean);
        },
        getIngredients(meal) {
            const ingredients = [];
            for (let i = 1; i <= 20; i++) {
                const ingredient = meal[`strIngredient${i}`];
                const measure = meal[`strMeasure${i}`];
                if (ingredient) {
                    ingredients.push(`${ingredient} - ${measure}`);
                }
            }
            return ingredients;
        },
    },
};
</script>

<style scoped>
h2,
h3,
h4 {
    color: #333;
}

input,
button {
    padding: 0.5em;
    font-size: 1em;
    margin: 0.5em 0;
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

.img-container {
    text-align: center;
}

img {
    display: block;
    margin: 0 auto;
    width: 150px;
}

a {
    color: #1e90ff;
    text-decoration: none;
}
</style>