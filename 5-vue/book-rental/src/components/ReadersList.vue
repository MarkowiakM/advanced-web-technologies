<script setup lang="ts">
import { defineComponent, ref, type Ref } from 'vue';
import type { Reader } from '../types';
import ItemsTable from './ItemsTable.vue';

const ENDPOINT = 'http://localhost:7070/readers';

const readers: Ref<Reader[]> = ref([]);

async function fetchReaders() {
  const res = (await fetch(ENDPOINT)) as any;
  readers.value = await res.json();
}

fetchReaders();
</script>

<script lang="ts">
export default defineComponent({
  name: 'ReadersList',
  components: { ItemsTable }
});
</script>

<template>
  <items-table :table="{ headers: ['id', 'name', 'surname'], items: readers }"></items-table>
</template>
