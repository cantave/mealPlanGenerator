<template>
  <div id="app">
    <NavBar />
    <router-view />
  </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue';
import { mapGetters, mapActions } from 'vuex';

export default {
  name: 'App',
  components: {
    NavBar
  },
  computed: {
    ...mapGetters(['userId'])
  },
  created() {
    console.log('Fetched userId:', this.userId);
    if (!this.userId && localStorage.getItem('user-id')) {
      this.setUserId(localStorage.getItem('user-id'));
    }

    this.$store.subscribe((mutation) => {
      if (mutation.type === 'setUserId') {
        this.$router.push('/profile');
      }
    });
  },
  methods: {
    ...mapActions(['setUserId', 'clearUserId'])
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
