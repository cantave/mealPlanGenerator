<template>
    <div>
        <MealPlan />
    </div>
</template>

<script>
import MealPlan from '@/components/MealPlan.vue';
import { mapGetters, mapActions } from 'vuex';

export default {
    name: 'MealPlanView',
    components: {
        MealPlan
    },
    computed: {
        ...mapGetters(['userId'])
    },
    created() {
        this.fetchUserMealPlan(this.userId);
    },
    methods: {
        ...mapActions(['fetchUserMealPlan'])
    }
};
</script>