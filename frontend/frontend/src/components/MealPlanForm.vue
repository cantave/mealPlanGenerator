<template>
    <div>
        <h2>Generate Meal Plan</h2>
        <form @submit.prevent="validateAndSubmit">
            <label>
                <span>User ID:</span>
                <input v-model="mealPlan.userId" type="number" placeholder="Enter your user ID" required />
            </label>
            <label>
                <span>Date:</span>
                <input v-model="mealPlan.date" type="date" required />
            </label>
            <label>
                <span>Breakfast:</span>
                <textarea v-model="mealPlan.breakfast" type="text"
                    placeholder="Enter breakfast items, separated by commas" />
            </label>
            <label>
                <span>Lunch:</span>
                <textarea v-model="mealPlan.lunch" type="text" placeholder="Enter lunch items, separated by commas" />
            </label>
            <label>
                <span>Dinner:</span>
                <textarea v-model="mealPlan.dinner" type="text" placeholder="Enter dinner items, separated by commas" />
            </label>
            <label>
                <span>Select Recipes:</span>
                <select v-model="mealPlan.recipes" multiple>
                    <option v-for="recipe in recipes" :value="recipe.id" :key="recipe.id">{{ recipe.name }}</option>
                </select>
            </label>
            <label>
                <span>Meal Preferences:</span>
                <textarea v-model="mealPreferences"
                    placeholder="Enter meal preferences (e.g., vegetarian, gluten-free)"></textarea>
            </label>
            <button type="submit">Generate Meal Plan</button>
            <div v-if="formErrors.length">
                <p><b>Please correct the following error(s):</b></p>
                <ul>
                    <li v-for="error in formErrors" :key="error">{{ error }}</li>
                </ul>
            </div>
        </form>
        <div v-if="generatedMealPlan">
            <p>Meal plan generated successfully!</p>
            <router-link :to="{ name: 'MealPlanView', params: { userId: mealPlan.userId } }">View Meal
                Plan</router-link>
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
            mealPlan: {
                userId: '',
                date: '',
                breakfast: '',
                lunch: '',
                dinner: '',
                recipes: [],
            },
            mealPreferences: '',
            recipes: [],
            generatedMealPlan: null,
            formErrors: []
        };
    },
    async created() {
        try {
            const response = await getRecipes();
            this.recipes = response;
            this.mealPlan.userId = this.userId;
        } catch (error) {
            console.error('Error fetching recipes:', error);
        }
    },
    methods: {
        async validateAndSubmit() {
            this.formErrors = [];
            if (!this.mealPlan.userId) this.formErrors.push("User ID is required.");
            if (!this.mealPlan.date) this.formErrors.push("Date is required.");
            if (this.mealPlan.recipes.length === 0) this.formErrors.push("At lease one recipe must be selected.");
            if (this.formErrors.length) return;

            await this.generatePlan();
        },
        async generatePlan() {
            const mealPlan = {
                ...this.mealPlan,
                breakfast: this.mealPlan.breakfast ? this.mealPlan.breakfast.split(',').map((item) => item.trim()) : [],
                lunch: this.mealPlan.lunch ? this.mealPlan.lunch.split(',').map((item) => item.trim()) : [],
                dinner: this.mealPlan.dinner ? this.mealPlan.dinner.split(',').map((item) => item.trim()) : [],
                mealPreferences: this.mealPreferences ? this.mealPreferences.split(',').map((pref) => pref.trim()) : [],
                recipes: this.mealPlan.recipes.map(id => this.recipes.find(recipe => recipe.id === id))
            };
            try {
                const response = await generateMealPlan(mealPlan);
                this.generatedMealPlan = response;
                this.$router.push({ name: 'MealPlanView', params: { userId: this.mealPlan.userId } });
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