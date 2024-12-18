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
                    <label>
                        <span>Select Meal Plan:</span>
                        <select v-model="mealPlanIds[meal.idMeal]">
                            <option v-for="plan in mealPlans" :value="plan.id" :key="plan.id">{{ formatDate(plan.date)
                                }}</option>
                        </select>
                    </label>
                    <label>
                        <span>Select Meal Time:</span>
                        <select v-model="mealTimes[meal.idMeal]">
                            <option value="breakfast">Breakfast</option>
                            <option value="lunch">Lunch</option>
                            <option value="dinner">Dinner</option>
                        </select>
                    </label>
                    <button @click="addToMealPlan(meal)">Add to Meal Plan</button>
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
            mealTimes: {},
            mealPlanIds: {}
        };
    },
    computed: {
        ...mapGetters(['meals', 'mealPlans']),
    },
    created(){
            this.fetchUserMealPlans();
    },
    methods: {
        ...mapActions(['fetchMealsByName', 'fetchRandomMeal', 'addMealToMealPlan', 'fetchUserMealPlans']),
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
        formatMealForBackend(meal) {
            const ingredients = this.getIngredients(meal);
            return {
                name: meal.strMeal,
                description: `${meal.strCategory} - ${meal.strArea}`,
                category: meal.strCategory,
                area: meal.strArea,
                mealThumb: meal.strMealThumb,
                youtube: meal.strYoutube,
                ingredients,
                instructions: meal.strInstructions,
            };
        },
        addToMealPlan(meal) {
            const formattedMeal = this.formatMealForBackend(meal);
            const mealTime = this.mealTimes[meal.idMeal];
            const mealPlanId = this.mealPlanIds[meal.idMeal];
            //const mealPlanId = this.$store.getters.selectedMealPlanId;
            if (mealPlanId) {
                this.addMealToMealPlan({ meal: formattedMeal, mealTime, mealPlanId });
            } else {
                alert("Please select a meal plan first.");
            }
        },
        formatDate(date) {
            const options = { year: 'numeric', month: 'long', day: 'numeric' };
            return new Date(date).toLocaleDateString(undefined, options);
        }
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