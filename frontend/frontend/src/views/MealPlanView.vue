<template>
    <div>
        <MealPlan />
    </div>
</template>

<script>
import MealPlan from '@/components/MealPlan.vue';

export default {
    name: 'MealPlanView',
    components: {
        MealPlan
    },
    props: ['userId']
};
</script>