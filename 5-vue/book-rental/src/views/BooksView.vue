<script lang="ts">
import { defineComponent } from 'vue';
import BooksList from '../components/BooksList.vue';

export default defineComponent({
  name: 'BooksView',
  components: { BooksList }
});
</script>

<template>
  <v-container>
    <h2 class="bg-teal-accent-4 text-white px-6 py-3">Books</h2>
    <books-list class="table"></books-list>
  </v-container>
</template>

<style scoped>
.table {
  width: 90vw;
  margin: 30px auto;
}
</style>
