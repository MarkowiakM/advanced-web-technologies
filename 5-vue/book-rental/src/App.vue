<script lang="ts">
import SideBar from './components/SideBar.vue';

export default {
  components: { SideBar },
  name: 'App',
  data: () => ({
    items: [
      { title: 'Dashboard', icon: 'mdi-view-dashboard' },
      { title: 'Account', icon: 'mdi-account-box' },
      { title: 'Settings', icon: 'mdi-cog' }
    ]
  })
};
</script>

<template>
  <VApp>
    <v-layout>
      <side-bar></side-bar>
      <v-main>
        <router-view />
      </v-main>
    </v-layout>
  </VApp>
</template>

<style>
  a {
    text-decoration: none;
    color: black;
  }
</style>