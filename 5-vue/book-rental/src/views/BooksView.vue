<script lang="ts">
import { defineComponent } from 'vue';
import BooksList from '../components/BooksList.vue';

export default defineComponent({
  name: 'BooksView',
  components: { BooksList }
});
</script>

<template>
  <div>books</div>
  <books-list></books-list>
</template>
