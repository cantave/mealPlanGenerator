<template>
    <div>
        <h2 v-if="userProfile.firstName && userProfile.lastName">
            {{ userProfile.firstName }} {{ userProfile.lastName }}'s Meal Plan
        </h2>
        <div>
            <label for="meal-plan-select">Select a Meal Plan:</label>
            <select id="meal-plan-select" v-model="selectedMealPlanId" @change="onMealPlanChange">
                <option v-for="plan in mealPlans" :value="plan.id" :key="plan.id">{{ formatDate(plan.date) }}</option>
            </select>
        </div>
        <div v-if="selectedMealPlan">
            <h3>Breakfast</h3>
            <ul>
                <li v-for="item in selectedMealPlan.breakfast" :key="item.id">
                    <router-link :to="{ name: 'RecipeDetailView', params: { id: item.id } }">
                        <span>{{ item.name }}</span>
                    </router-link>
                    <span @click="removeItemFromMealPlan('breakfast', item)">
                        <img :src="trashBin" alt="trash">
                    </span>
                </li>

            </ul>
            <h3>Lunch</h3>
            <ul>
                <li v-for="item in selectedMealPlan.lunch" :key="item">
                    <router-link :to="{ name: 'RecipeDetailView', params: { id: item.id } }">
                        <span>{{ item.name }}</span>
                    </router-link>
                    <span @click="removeItemFromMealPlan('lunch', item)">
                        <img :src="trashBin" alt="trash">
                    </span>
                </li>
            </ul>
            <h3>Dinner</h3>
            <ul>
                <li v-for="item in selectedMealPlan.dinner" :key="item">
                    <router-link :to="{ name: 'RecipeDetailView', params: { id: item.id } }">
                        <span>{{ item.name }}</span>
                    </router-link>
                    <span @click="removeItemFromMealPlan('dinner', item)">
                        <img :src="trashBin" alt="trash">
                    </span>
                </li>
            </ul>
            <h3>Recipes</h3>
            <ul>
                <li v-for="recipe in selectedMealPlan.recipes" :key="recipe.id">
                    <router-link :to="{ name: 'RecipeDetailView', params: { id: recipe.id } }">
                        <span>{{ recipe.name }} - {{ recipe.description }}</span>
                    </router-link>
                    <span @click="removeRecipeFromMealPlan(selectedMealPlan.id, recipe.id)">
                        <img :src="trashBin" alt="trash">
                    </span>
                </li>
            </ul>
            <button id="delete" @click="deleteSelectedMealPlan">Delete Meal Plan</button>
        </div>
        <div v-else>
            <p>Loading...</p>
        </div>
    </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import trashBin from '@/assets/trash-bin.png';
import { updateMealPlan } from '@/services/apiService';

export default {
    data() {
        return {
            selectedMealPlanId: null,
        };
    },
    computed: {
        ...mapGetters(['mealPlans', 'userId', 'userProfile']),
        selectedMealPlan() {
            return this.mealPlans.find((plan) => plan.id === this.selectedMealPlanId);
        },
        trashBin() {
            return trashBin;
        }
    },
    watch: {
        userId(newUserId) {
            if (newUserId) {
                this.$store.dispatch('fetchUserMealPlans', newUserId);
                this.$store.dispatch('fetchUserProfile', newUserId);
            }
        },
    },
    created() {
        if (this.userId) {
            this.$store.dispatch('fetchUserMealPlans', this.userId);
            this.$store.dispatch('fetchUserProfile', this.userId);
        }
    },
    methods: {
        ...mapActions(['deleteMealPlan', 'fetchUserMealPlans', 'fetchUserProfile', 'removeRecipeFromMealPlan']),
        async deleteSelectedMealPlan() {
            if (confirm('Are you sure you want to delete this meal plan?')) {
                await this.deleteMealPlan(this.selectedMealPlanId);
                this.selectedMealPlanId = null;
            }
        },
        onMealPlanChange() {
            console.log('Selected Meal Plan ID:', this.selectedMealPlanId);
        },
        formatDate(date) {
            const options = { year: 'numeric', month: 'long', day: 'numeric' };
            return new Date(date).toLocaleDateString(undefined, options);
        },
        async removeItemFromMealPlan(mealTime, item) {
            const mealPlan = this.selectedMealPlan;
            const updatedMealPlan = { ...mealPlan };
            updatedMealPlan[mealTime] = updatedMealPlan[mealTime].filter(mealItem => mealItem !== item);
            await updateMealPlan(updatedMealPlan.id, updatedMealPlan);
            this.$store.commit('updateMealPlan', updatedMealPlan);
        },
        async removeRecipeFromMealPlan(mealPlanId, recipeId) {
            await this.removeRecipeFromMealPlan({ mealPlanId, recipeId });
        },
    },
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
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0.5em;
    background: #f9f9f9;
    margin-bottom: 0.5em;
    border: 1px solid #ddd;
}

#delete {
    margin-top: 1em;
    padding: 0.5em;
    background-color: #ff4d4d;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

#delete:hover {
    background-color: #ff3333;
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