<script lang="ts">
import { defineComponent, ref, type Ref } from 'vue';
import type { Author } from '../../types';
import ItemsTable from './ItemsTable.vue';
import AuthorsForm from '../forms/AuthorsForm.vue';

export default defineComponent({
  data: () => ({
    ENDPOINT: 'http://localhost:7070/authors',
    currentPage: 1,
    pages: 1,
    authorsAmount: 0,
    size: 10,
    authors: ref([]) as Ref<Author[]>
  }),
  name: 'AuthorsList',
  components: { ItemsTable, AuthorsForm },
  watch: {
    currentPage: function () {
      this.readAuthors();
    }
  },
  methods: {
    createAuthor(form: { name: string; surname: string }) {
      const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name: form.name, surname: form.surname })
      };
      console.log(form);
      fetch(this.ENDPOINT, requestOptions).then(() => {
        this.readAuthors(), this.readAauthorsAmount();
      });
    },
    async readAuthors() {
      const res = (await fetch(
        `${this.ENDPOINT}?page=${this.currentPage - 1}&size=${this.size}`
      )) as any;
      this.authors = await res.json();
    },
    async readAauthorsAmount() {
      const res = (await fetch(`${this.ENDPOINT}/amount`)) as any;
      this.authorsAmount = (await res.json()).amount;
      this.pages = this.authorsAmount / this.size + (this.authorsAmount % this.size === 0 ? 0 : 1);
    },
    async updateAuthor(form: { name: string; surname: string; id: number }) {
      console.log('putting');
      const requestOptions = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name: form.name, surname: form.surname })
      };
      console.log(form);
      fetch(this.ENDPOINT + '/' + form.id, requestOptions).then(() => this.readAuthors());
    },
    deleteAuthor(author: Author): void {
      fetch(this.ENDPOINT + '/' + author.id, { method: 'DELETE' }).then((res) => {
        console.log(res), this.readAuthors(), this.readAauthorsAmount();
      });
    }
  },
  created() {
    this.readAuthors();
    this.readAauthorsAmount();
  }
});
</script>

<template>
  <h2 class="bg-teal-accent-4 text-white px-6 py-3 mb-6">
    <span>Authors</span>
    <authors-form class="float-right my-auto" mode="add" @onSubmit="createAuthor"></authors-form>
  </h2>
  <items-table
    :table="{ headers: ['id', 'name', 'surname'], items: authors }"
    :edit-item="updateAuthor"
    :delete-item="deleteAuthor"
    edit-form="authors-form"
    class="mb-6"
  ></items-table>
  <v-pagination v-model="currentPage" v-model:length="pages" class="mt-6"></v-pagination>
</template>
