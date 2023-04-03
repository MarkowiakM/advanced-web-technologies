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
  components: { ItemsTable }
});
</script>

<template>
  <items-table :table="{ headers: ['id', 'name', 'surname'], items: authors }"></items-table>
</template>
