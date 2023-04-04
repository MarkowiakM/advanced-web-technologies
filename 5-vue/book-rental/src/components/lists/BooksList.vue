<script lang="ts">
import { defineComponent, ref, type Ref } from 'vue';
import type { Book } from '../../types';
import ItemsTable from './ItemsTable.vue';
import BooksForm from '../forms/BooksForm.vue';

export default defineComponent({
  data: () => ({
    ENDPOINT: 'http://localhost:7070/books',
    currentPage: 1,
    pages: 1,
    size: 10,
    booksAmount: 0,
    books: ref([]) as Ref<Book[]>,
    showAlert: false
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
    this.readBooksAmount();
  },
  methods: {
    createBook(form: { title: string; authorIDs: number[]; pages: number }) {
      const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title: form.title, authorsIDs: form.authorIDs, pages: form.pages })
      };
      fetch(this.ENDPOINT, requestOptions).then(() => (this.readBooks(), this.readBooksAmount()));
    },
    async readBooks() {
      const res = (await fetch(
        `${this.ENDPOINT}?page=${this.currentPage - 1}&size=${this.size}`
      )) as any;
      this.books = await res.json();
    },
    async readBooksAmount() {
      const res = (await fetch(`${this.ENDPOINT}/amount`)) as any;
      this.booksAmount = (await res.json()).amount;
      this.pages = this.booksAmount / this.size + (this.booksAmount % this.size === 0 ? 0 : 1);
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
        if (res.status === 409) {
          this.showAlert = true;
        } else {
          console.log(res), this.readBooks(), this.readBooksAmount();
        }
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
  <v-pagination v-model="currentPage" v-model:length="pages" class="mt-6"></v-pagination>
  <v-alert
    v-if="showAlert"
    closable
    type="error"
    title="Error"
    text="This book is rented"
  ></v-alert>
</template>
