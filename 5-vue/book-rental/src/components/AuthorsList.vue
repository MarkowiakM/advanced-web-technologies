<script setup lang="ts">
import { defineComponent, ref, type Ref } from 'vue';
import type { Author } from '../types';
import ItemsTable from './ItemsTable.vue';

const ENDPOINT = 'http://localhost:7070/authors';

const authors: Ref<Author[]> = ref([]);

async function fetchAuthors() {
  const res = (await fetch(ENDPOINT)) as any;
  authors.value = await res.json();
}

fetchAuthors();
</script>

<script lang="ts">
export default defineComponent({
  name: 'AuthorsList',
  components: { ItemsTable },
  methods: {
    createAuthor() {},
    editAuthor() {},
    deleteAuthor() {}
  }
});
</script>

<template>
  <h2 class="bg-teal-accent-4 text-white px-6 py-3 mb-6">
    Authors <v-icon class="text-white float-right" @click="createAuthor()">mdi-plus</v-icon>
  </h2>
  <items-table :table="{ headers: ['id', 'name', 'surname'], items: authors }"></items-table>
</template>
