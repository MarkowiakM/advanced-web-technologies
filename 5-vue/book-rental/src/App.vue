<script lang="ts">
import AppLayout from './layouts/AppLayout.vue';
import HomeLayout from './layouts/HomeLayout.vue';
export default {
  computed: {
    layout(): string {
      return this.$store.getters.layout;
    }
  },
  components: {
    'app-layout': AppLayout,
    'home-layout': HomeLayout
  }
};
</script>

<template>
  <v-app>
    <component v-bind:is="layout"></component>
  </v-app>
</template>
