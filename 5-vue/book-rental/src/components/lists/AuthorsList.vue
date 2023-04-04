<script setup lang="ts">
import { defineComponent, ref, type Ref } from 'vue';
import type { Author } from '../../types';
import ItemsTable from './ItemsTable.vue';

const ENDPOINT = 'http://localhost:7070/authors';

const authors: Ref<Author[]> = ref([]);

const createAuthor = (): void => {};

async function readAuthors() {
  const res = (await fetch(ENDPOINT)) as any;
  authors.value = await res.json();
}

const updateAuthor = (): void => {};

const deleteAuthor = (author: Author): void => {
  fetch(ENDPOINT + '/' + author.id, { method: 'DELETE' }).then((res) => {
    console.log(res), readAuthors();
  });
};

readAuthors();
</script>

<script lang="ts">
export default defineComponent({
  name: 'AuthorsList',
  components: { ItemsTable }
});
</script>

<template>
  <h2 class="bg-teal-accent-4 text-white px-6 py-3 mb-6">
    Authors <v-icon class="text-white float-right" @click="createAuthor()">mdi-plus</v-icon>
  </h2>
  <items-table
    :table="{ headers: ['id', 'name', 'surname'], items: authors }"
    :delete-item="deleteAuthor"
  ></items-table>
</template>
