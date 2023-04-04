<script setup lang="ts">
import { defineComponent, ref, type Ref } from 'vue';
import type { Author } from '../../types';
import ItemsTable from './ItemsTable.vue';
import AuthorsForm from '../forms/AuthorsForm.vue';

const ENDPOINT = 'http://localhost:7070/authors';

const authors: Ref<Author[]> = ref([]);

const createAuthor = (form: { name: string; surname: string }): void => {
  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ name: form.name, surname: form.surname })
  };
  console.log(form);
  fetch(ENDPOINT, requestOptions).then((res) => readAuthors());
};

async function readAuthors() {
  const res = (await fetch(ENDPOINT)) as any;
  authors.value = await res.json();
}

const updateAuthor = (form: { name: string; surname: string; id: number }): void => {
  console.log('putting');
  const requestOptions = {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ name: form.name, surname: form.surname })
  };
  console.log(form);
  fetch(ENDPOINT + '/' + form.id, requestOptions).then((res) => readAuthors());
};

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
  components: { ItemsTable, AuthorsForm }
});
</script>

<template>
  <h2 class="bg-teal-accent-4 text-white px-6 py-3 mb-6">
    <span>Authors</span>
    <authors-form
      class="float-right my-auto"
      mode="add"
      @onSubmit="createAuthor"
    ></authors-form>
  </h2>
  <items-table
    :table="{ headers: ['id', 'name', 'surname'], items: authors }"
    :edit-item="updateAuthor"
    :delete-item="deleteAuthor"
    edit-form="authors-form"
  ></items-table>
</template>
