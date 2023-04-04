<script lang="ts">
import SideBar from '../components/bars/SideBar.vue';
import type { User } from '../store';

export default {
  components: { SideBar },
  methods: {
    user(): User {
      return this.$store.getters.user;
    }
  }
};
</script>

<template>
  <v-layout class="d-flex">
    <side-bar :user="user()"></side-bar>
    <v-main>
      <router-view />
    </v-main>
  </v-layout>
</template>
