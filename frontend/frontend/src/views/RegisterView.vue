<template>
    <div>
        <h2>Register</h2>
        <form @submit.prevent="register">
            <input v-model="firstName" placeholder="First Name">
            <input v-model="lastName" placeholder="Last Name">
            <input v-model="username" placeholder="Username" />
            <input v-model="password" type="password" placeholder="Password" />
            <button type="submit">Register</button>
        </form>
    </div>
</template>

<script>
import { register } from '@/services/authService';
    export default {
        data() {
            return {
                username: '',
                password: '',
                firstName: '',
                lastName: ''
            };
        },
        methods: {
            register() {
                register({username: this.username, password: this.password, firstName: this.firstName, lastName: this.lastName})
                .then(() => {
                    console.log('Registered successfully');
                    this.$router.push('/login');
                })
                .catch(error => {
                    console.error('Registration failed:', error);
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
    input, button {
        margin-bottom: 0.5em;
        padding: 0.5em;
    }
</style>