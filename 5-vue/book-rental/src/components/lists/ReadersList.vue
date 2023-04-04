<script setup lang="ts">
import { defineComponent, ref, type Ref } from 'vue';
import type { Reader } from '../../types';
import ItemsTable from './ItemsTable.vue';

const ENDPOINT = 'http://localhost:7070/readers';

const readers: Ref<Reader[]> = ref([]);

const createReader = (): void => {};

async function readReaders() {
  const res = (await fetch(ENDPOINT)) as any;
  readers.value = await res.json();
}
const updateReader = (): void => {};

const deleteReader = (reader: Reader): void => {
  fetch(ENDPOINT + '/' + reader.id, { method: 'DELETE' }).then((res) => {
    console.log(res), readReaders();
  });
};

readReaders();
</script>

<script lang="ts">
export default defineComponent({
  name: 'ReadersList',
  components: { ItemsTable },
  methods: {
    createReader() {},
    editReader() {},
    deleteReader() {}
  }
});
</script>

<template>
  <h2 class="bg-teal-accent-4 text-white px-6 py-3 mb-6">
    Readers <v-icon class="text-white float-right" @click="createReader()">mdi-plus</v-icon>
  </h2>
  <items-table :table="{ headers: ['id', 'name', 'surname'], items: readers }" :delete-item="deleteReader"></items-table>
</template>
