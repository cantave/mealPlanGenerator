import axios from "axios";
import { jwtDecode } from 'jwt-decode';
import { updateToken } from './apiService';
import { EventBus } from "@/eventBus";

const API_URL = 'http://localhost:8081/api/auth';

export const login = async (credentials) => { 
    const response = await axios.post(`${API_URL}/login`, credentials); 
    const token = response.data.jwt; 
    localStorage.setItem('user-token', token);
    const decodedToken = jwtDecode(token);
    console.log('Decoded token:', decodedToken); 
    if (decodedToken.userId) {
        localStorage.setItem('user-id', decodedToken.userId); 
        EventBus.emit('user-logged-in', decodedToken.userId);
    } else {
        console.error('userId not found in token');
    }
    updateToken();
    return response.data;
};

export const register = (user) => {
    return axios.post(`${API_URL}/register`, user);
};

export const isAuthenticated = () => {
    const token = localStorage.getItem('user-token');
    if (!token) {
        console.log('No token found');
        return false;
    }
    try {
        const { exp } = jwtDecode(token);
        return !!exp && exp > Date.now() / 1000;
    } catch (e) {
        console.error('Invalid token:', e);
        return false;
    }
};

export const logout = () => {
    localStorage.removeItem('user-token');
    localStorage.removeItem('user-id');
    updateToken();
    EventBus.emit('user-logged-out');
};
