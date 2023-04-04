<script lang="ts">
import { defineComponent, ref, type Ref } from 'vue';
import type { Book } from '../../types';
import ItemsTable from './ItemsTable.vue';
import BooksForm from '../forms/BooksForm.vue';

export default defineComponent({
  data: () => ({
    ENDPOINT: 'http://localhost:7070/books',
    currentPage: 1,
    pages: 2,
    size: 10,
    books: ref([]) as Ref<Book[]>
  }),
  name: 'BooksList',
  components: { ItemsTable, BooksForm },
  watch: {
    currentPage: function () {
      this.readBooks();
    }
  },
  created() {
    this.readBooks();
  },
  methods: {
    createBook(form: { title: string; authorIDs: number[]; pages: number }) {
      const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title: form.title, authorsIDs: form.authorIDs, pages: form.pages })
      };
      fetch(this.ENDPOINT, requestOptions).then(() => this.readBooks());
    },
    async readBooks() {
      const res = (await fetch(this.ENDPOINT)) as any;
      this.books = await res.json();
    },
    updateBook(form: { title: string; authorIDs: number[]; pages: number; id: number }) {
      const requestOptions = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title: form.title, authorsIDs: form.authorIDs, pages: form.pages })
      };
      fetch(this.ENDPOINT + '/' + form.id, requestOptions).then(() => this.readBooks());
    },
    deleteBook(book: Book) {
      fetch(this.ENDPOINT + '/' + book.id, { method: 'DELETE' }).then((res) => {
        console.log(res), this.readBooks();
      });
    },
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
    }
  }
});
</script>

<template>
  <h2 class="bg-teal-accent-4 text-white px-6 py-3 mb-6">
    <span>Books</span>
    <books-form class="float-right my-auto" mode="add" @onSubmit="createBook"></books-form>
  </h2>
  <items-table
    :table="{ headers: ['id', 'title', 'pages', 'authors'], items: parseJSONBooks(books) }"
    :edit-item="updateBook"
    :delete-item="deleteBook"
    edit-form="books-form"
     class="mb-6"
  ></items-table>
  <v-pagination v-model="currentPage" :length="pages"  class="mt-6"></v-pagination>
</template>
