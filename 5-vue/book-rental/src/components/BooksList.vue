<script setup lang="ts">
import { ref } from 'vue';

const bookList = ref([]);

async function getData() {
  const res = await fetch('http://localhost:7070/books');
  const finalRes = await res.json();
  bookList.value = finalRes;
}

getData();
</script>

<template>
  <div v-for="book in bookList" :key="book.id">
    {{ book.title }}
    {{ book.authors }}
    {{ book.pages }}
  </div>
</template>
