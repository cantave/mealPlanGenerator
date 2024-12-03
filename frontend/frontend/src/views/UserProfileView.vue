<template>
    <div>
        <UserProfile />
    </div>
</template>

<script>
import UserProfile from '@/components/UserProfile.vue';
import { mapActions } from 'vuex';

export default {
    name: 'UserProfileView',
    components: {
        UserProfile
    },
    created() {
        this.fetchUserProfile();
    },
    methods: {
        ...mapActions(['fetchUserProfile'])
    }
};
</script>
