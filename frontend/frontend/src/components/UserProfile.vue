<template>
    <div class="user-profile">
        <div class="profile-image">
            <img :src="userImage || placeholderImage" alt="User Image" />
        </div>
        <h2>
            {{ userName }}
        </h2>
        <router-link :to="{ name: 'UserProfileForm' }">Edit Profile</router-link>
    </div>
</template>

<script>
import { mapGetters } from 'vuex';
import placeholderImage from '@/assets/user.png';

export default {
    name: 'UserProfile',
    computed: {
        ...mapGetters({
            userImage: 'userImage',
            userName: 'userName'
        }),
        placeholderImage() {
            return placeholderImage;
        }
    },
    created() {
        console.log('UserProfile component loaded');
    }
};
</script>

<style lang="scss" scoped>
.user-profile {
    text-align: center;
    margin-top: 20px;
}

.profile-image {
    width: 150px;
    height: 150px;
    margin: 0 auto;
    border-radius: 8px;
    overflow: hidden;
}

.profile-image img {
    width: 100%;
    height: auto;
}

.placeholder-image {
    width: 100%;
    height: 100%;
    background-color: #c0c0c0;
}
</style>