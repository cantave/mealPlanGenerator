<template>
    <div>
        <h2>User Profile</h2>
        <form @submit.prevent="updateProfile">
            <label>
                <span>First Name:</span>
                <input v-model="firstName" type="text" placeholder="Enter your first name" required />
            </label>
            <label>
                <span>Last Name:</span>
                <input v-model="lastName" type="text" placeholder="Enter your last name" required />
            </label>
            <label>
                <span>Email:</span>
                <input v-model="email" type="email" placeholder="Enter your email" required />
            </label>
            <label>
                <span>Meal Preferences</span>
                <textarea v-model="mealPreferences"
                    placeholder="Enter meal preferences (e.g., vegetarian, gluten-free)"></textarea>
            </label>
            <button type="submit">Update Profile</button>
        </form>
    </div>
</template>

<script>
import { getUserProfile, updateUserProfile } from '@/services/apiService';
export default {
    data() {
        return {
            firstName: '',
            lastName: '',
            email: '',
            mealPreferences: ''
        };
    },
    created() {
        this.fetchUserProfile();
    },
    methods: {
        fetchUserProfile() {
            const userId = this.$route.params.id;
            getUserProfile(userId).then(response => {
                const user = response.data;
                this.firstName = user.firstName;
                this.lastName = user.lastName;
                this.email = user.email;
                this.mealPreferences = user.mealPreferences;
            }).catch(error => {
                console.error('Error fetching user profile:', error);
            });
        },
        updateProfile() {
            const userId = this.$route.params.id;
            const userDetails = {
                firstName = this.firstName,
                lastName = this.lastName,
                email: this.email,
                mealPreference: this.mealPreferences
            };
            updateUserProfile(userId, userDetails).then(() => {
                alert('Profile updated successfully!');
            }).catch(error => {
                console.error('Error updating profile:', error);
            });
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
button {
    padding: 0.5em;
    font-size: 1em;
}

button {
    margin-top: 1em;
    padding: 0.5em;
    background-color: #4caf50;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

button:hover {
    background-color: #45a049;
}
</style>
