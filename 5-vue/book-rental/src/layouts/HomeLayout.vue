<script lang="ts">
import NavBar from '../components/bars/NavBar.vue';
import type { User } from '../store';

export default {
  components: { NavBar },
  methods: {
    user(): User {
      return this.$store.getters.user;
    }
  }
};
</script>

<template>
  <v-app>
    <nav-bar :user="user()"></nav-bar>
    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>
