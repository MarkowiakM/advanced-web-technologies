<script setup lang="ts">
import { ref, type Ref } from 'vue';
import type { Book } from '../types';

const books: Ref<Book[]> = ref([]);
const ENDPOINT = 'http://localhost:7070/books';

async function fetchBooks() {
  const res = await fetch(ENDPOINT);
  const finalRes = await res.json();
  books.value = finalRes;
}

fetchBooks();
</script>

<template>
  <div v-for="book in books" :key="book.id">
    {{ book.title }}
    {{ book.authors }}
    {{ book.pages }}
  </div>
</template>
