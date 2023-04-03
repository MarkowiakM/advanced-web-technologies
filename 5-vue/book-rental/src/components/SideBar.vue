<script lang="ts">
import { defineComponent } from 'vue';

type User = {
  avatar: string;
  name: string;
  mail: string;
};

const MOCK_USER: User = {
  avatar: 'https://randomuser.me/api/portraits/women/11.jpg',
  name: 'Maria Paulina',
  mail: 'maria.paulina@gmail.com'
};

export default defineComponent({
  data() {
    return {
      user: MOCK_USER
    };
  },
  methods: {
    setLayout(layout: string) {
      this.$store.commit('setLayout', layout);
    }
  }
});
</script>

<template>
  <v-navigation-drawer expand-on-hover rail>
    <v-list>
      <v-list-item
        v-bind:prepend-avatar="user.avatar"
        v-bind:title="user.name"
        v-bind:subtitle="user.mail"
      ></v-list-item>
    </v-list>

    <v-divider></v-divider>

    <v-list density="compact" nav class="d-flex flex-column">
      <v-list-item
        prepend-icon="mdi-home"
        title="Home"
        value="home"
        to="/"
        @click="setLayout('simple-layout')"
      ></v-list-item>
      <v-list-item prepend-icon="mdi-book" title="Books" value="books" to="/books"></v-list-item>
      <v-list-item
        prepend-icon="mdi-account"
        title="Authors"
        value="authors"
        to="/authors"
      ></v-list-item>
      <v-list-item
        prepend-icon="mdi-account-multiple"
        title="Users"
        value="users"
        to="/users"
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
