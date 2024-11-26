import axios from "axios";
import jwtDecode from 'vue-jwt-decode';

const API_URL = 'http://localhost:8081/api/auth';

export const login = (credentials) => {
    return axios.post(`${API_URL}/login`, credentials).then(response => {
        const token = response.data.token;
        localStorage.setItem('user-token', token);
        return jwtDecode(token);
    });
};

export const register = (user) => {
    return axios.post(`${API_URL}/register`,user);
};

export const isAuthenticated = () => {
    const token = localStorage.getItem('user-token');
    return !!token && !jwtDecode(token).exp < Date.now() / 1000;
};

export const logout = () => {
    localStorage.removeItem('user-token');
};