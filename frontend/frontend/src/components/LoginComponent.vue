<template>
    <div>
        <h2>Login</h2>
        <form @submit.prevent="login">
            <input v-model="username" placeholder="Username" />
            <input v-model="password" type="password" placeholder="Password" />
            <button type="submit">Login</button>
        </form>
    </div>
</template>

<script>
import { login } from '@/services/authService';

export default {
    name: 'LoginComponent',
    data() {
        return {
            username: '',
            password: ''
        };
    },
    methods: {
        login() {
            login({ username: this.username, password: this.password })
                .then(user => {
                    console.log('Logged in:', user);
                    this.$router.push('/');
                })
                .catch(error => {
                    console.error('Login failed:', error);
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

input,
button {
    margin-bottom: 0.5em;
    padding: 0.5em;
}
</style>