<template>
  <v-navigation-drawer expand-on-hover rail>
    <v-list>
      <v-list-item
        prepend-avatar="https://randomuser.me/api/portraits/women/11.jpg"
        title="Maria Paulina"
        subtitle="maria.paulina@gmail.com"
      ></v-list-item>
    </v-list>

    <v-divider></v-divider>

    <v-list density="compact" nav class="d-flex flex-column">
      <v-list-item prepend-icon="mdi-home" title="Home" value="home" to="/"></v-list-item>
      <v-divider></v-divider>
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
