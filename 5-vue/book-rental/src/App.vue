<script lang="ts">
import AppLayout from './layouts/AppLayout.vue';
import SimpleLayout from './layouts/SimpleLayout.vue';
export default {
  computed: {
    layout(): string {
      return this.$store.getters.layout;
    }
  },
  components: {
    'app-layout': AppLayout,
    'simple-layout': SimpleLayout
  }
};
</script>

<template>
  <v-app>
    <component v-bind:is="layout"></component>
  </v-app>
</template>
