<template>
    <div>
        <h2 v-if="mealPlan">Meal Plan for User {{ mealPlan.userId }}</h2>
        <div v-if="mealPlan">
            <h3>Breakfast</h3>
            <ul>
                <li v-for="item in mealPlan.breakfast" :key="item">{{ item }}</li>
            </ul>
            <h3>Lunch</h3>
            <ul>
                <li v-for="item in mealPlan.lunch" :key="item">{{ item }}</li>
            </ul>
            <h3>Dinner</h3>
            <ul>
                <li v-for="item in mealPlan.dinner" :key="item">{{ item }}</li>
            </ul>
        </div>
        <div v-else>
            <p>Loading...</p>
        </div>
    </div>
</template>

<script>
import { getMealPlans } from '@/services/apiService';

export default {
    data() {
        return {
            mealPlan: null
        };
    },
    created() {
        const userId = this.$route.params.userId;
        getMealPlans(userId).then(response => {
            this.mealPlan = response.data;
        }).catch(error => {
            console.error('Error fetching meal plan:', error);
        });
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