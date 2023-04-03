<script lang="ts">
import { defineComponent } from 'vue';
import type { User } from '../store';

export default defineComponent({
  methods: {
    setLayout(layout: string) {
      this.$store.commit('setLayout', layout);
    }
  },
  props: {
    user: {
      type: Object as () => User
    }
  }
});
</script>

<template>
  <v-toolbar density="compact" class="bg-teal">
    <v-app-bar-nav-icon to="/books" @click="setLayout('app-layout')"></v-app-bar-nav-icon>
    <v-toolbar-title>Book Rental</v-toolbar-title>
    <v-spacer></v-spacer>
    <v-btn icon to="/books" @click="setLayout('app-layout')">
      <v-icon>mdi-book-open-variant</v-icon>
    </v-btn>
    <v-list-item v-bind:prepend-avatar="user!.avatar"></v-list-item>
  </v-toolbar>
</template>
