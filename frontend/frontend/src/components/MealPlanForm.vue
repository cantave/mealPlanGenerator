<template>
    <div>
        <h2>Generate Meal Plan</h2>
        <form @submit.prevent="generatePlan">
            <label>
                <span>User ID:</span>
                <input v-model="userId" type="number" placeholder="Enter your user ID" required />
            </label>
            <label>
                <span>Date:</span>
                <input v-model="date" type="date" required />
            </label>
            <label>
                <span>Select Recipes:</span>
                <select v-model="selectedRecipes" multiple>
                    <option v-for="recipe in recipes" :value="recipe.id" :key="recipe.id">{{ recipe.name }}</option>
                </select>
            </label>
            <label>
                <span>Meal Preferences:</span>
                <textarea v-model="mealPreferences"
                    placeholder="Enter meal preferences (e.g., vegetarian, gluten-free)"></textarea>
            </label>
            <button type="submit">Generate Meal Plan</button>
        </form>
        <div v-if="generatedMealPlan">
            <p>Meal plan generated successfully!</p>
            <router-link :to="{ name: 'MealPlan', params: { userId: userId } }">View Meal Plan</router-link>
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex';
import { getRecipes, generateMealPlan } from '@/services/apiService';

export default {
    computed: {
        ...mapGetters(['userId'])
    },
    data() {
        return {
            date: '',
            selectedRecipes: [],
            mealPreferences: '',
            recipes: [],
            generatedMealPlan: null
        };
    },
    async created() {
        try {
            const response = await getRecipes();
            this.recipes = response;
        } catch (error) {
            console.error('Error fetching recipes:', error);
        }
    },
    methods: {
        async generatePlan() {
            const mealPlan = {
                userId: this.userId,
                date: this.date,
                recipes: this.recipes,
                mealPreferences: this.mealPreferences.split(',').map(pref => pref.trim())
            };
            try {
                const response = await generateMealPlan(mealPlan);
                this.generatedMealPlan = response;
                this.$router.push(`/meal-plan/${this.userId}`);
            } catch (error) {
                console.error('Error generating meal plan:', error);
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

label {
    margin-bottom: 1em;
}

label span {
    display: block;
    margin-bottom: 0.5em;
}

input,
textarea,
select,
button {
    padding: 0.5em;
    font-size: 1em;
}

button {
    margin-top: 1em;
    padding: 0.5em;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

button:hover {
    background-color: #45a049;
}
</style>