<template>
    <div>
        <h1>Meal Search</h1>
        <MealSearch />
    </div>
</template>

<script>
import MealSearch from '@/components/MealSearch.vue';

export default {
    name: 'MealSearchView',
    components: {
        MealSearch,
    },
}
</script>

<style scoped>
h1 {
    color: #333;
}
</style>