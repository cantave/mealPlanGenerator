import { createStore } from 'vuex';

const store = createStore({
  state: {
    userId: localStorage.getItem('user-id') || null
  },
  mutations: {
    setUserId(state, userId) {
      state.userId = userId;
      localStorage.setItem('user-id', userId);
    },
    clearUserId(state) {
      state.userId = null;
      localStorage.removeItem('user-id');
    }
  },
  actions: {
    login({ commit }, userId) {
      commit('setUserId', userId);
    },
    logout({ commit }) {
      commit('clearUserId');
    }
  },
  getters: {
    userId: state => state.userId
  }
});

export default store;
