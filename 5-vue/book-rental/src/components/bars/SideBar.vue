<script lang="ts">
import { defineComponent } from 'vue';
import type { User } from '../../store/index';

export default defineComponent({
  props: {
    user: {
      type: Object as () => User
    }
  }
});
</script>

<template>
  <v-navigation-drawer expand-on-hover rail class="bg-teal">
    <v-list>
      <v-list-item
        v-bind:prepend-avatar="user!.avatar"
        v-bind:title="user!.name"
        v-bind:subtitle="user!.mail"
      ></v-list-item>
    </v-list>

    <v-divider></v-divider>

    <v-list density="compact" nav class="d-flex flex-column">
      <v-list-item prepend-icon="mdi-home" title="Home" value="home" to="/"></v-list-item>
      <v-list-item prepend-icon="mdi-book" title="Books" value="books" to="/books"></v-list-item>
      <v-list-item
        prepend-icon="mdi-account"
        title="Authors"
        value="authors"
        to="/authors"
      ></v-list-item>
      <v-list-item
        prepend-icon="mdi-account-multiple"
        title="Readers"
        value="Readers"
        to="/readers"
      ></v-list-item>
      <v-list-item
        prepend-icon="mdi-view-dashboard"
        title="Rentals"
        value="rentals"
        to="/rentals"
      ></v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>

<style lang="scss"></style>
