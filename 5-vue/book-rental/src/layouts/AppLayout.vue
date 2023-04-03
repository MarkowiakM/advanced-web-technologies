<script lang="ts">
import SideBar from '../components/SideBar.vue';

export default {
  components: { SideBar },
};
</script>

<template>
  <v-layout>
    <side-bar></side-bar>
    <v-main>
      <router-view />
    </v-main>
  </v-layout>
</template>
