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
        MealPlan,
    },
    computed: {
        ...mapGetters(['userId']),
    },
    watch: {
        userId(newUserId) {
            if (newUserId) {
                this.fetchUserMealPlans(newUserId);
            }
        },
    },
    created() {
        if (this.userId) {
            this.fetchUserMealPlans(this.userId);
        }
    },
    methods: {
        ...mapActions(['fetchUserMealPlans']),
    },
};
</script>