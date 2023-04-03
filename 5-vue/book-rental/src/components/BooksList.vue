<script setup lang="ts">
import { defineComponent, ref, type Ref } from 'vue';
import type { Book } from '../types';
import ItemsTable from './ItemsTable.vue';

const ENDPOINT = 'http://localhost:7070/books';

const books: Ref<Book[]> = ref([]);

async function fetchBooks() {
  const res = (await fetch(ENDPOINT)) as any;
  books.value = await res.json();
}

fetchBooks();
</script>

<script lang="ts">
export default defineComponent({
  name: 'BooksList',
  components: { ItemsTable },
  methods: {
    parseJSONBooks(books: Book[]) {
      return books.map((book) => {
        return {
          id: book.id,
          title: book.title,
          pages: book.pages,
          authors: book.authors
            .reduce((auth, author) => auth + ', ' + author.name + ' ' + author.surname, '')
            .slice(1)
        };
      });
    },
    createBook() {},
    editBook() {},
    deleteBook() {}
  }
});
</script>

<template>
  <h2 class="bg-teal-accent-4 text-white px-6 py-3 mb-6">
    Books <v-icon class="text-white float-right" @click="createBook()">mdi-plus</v-icon>
  </h2>
  <items-table
    :table="{ headers: ['id', 'title', 'pages', 'authors'], items: parseJSONBooks(books) }"
  ></items-table>
</template>
