<script setup lang="ts">
import { defineComponent, ref, type Ref } from 'vue';
import type { Book } from '../../types';
import ItemsTable from './ItemsTable.vue';
import BooksForm from '../forms/BooksForm.vue';

const ENDPOINT = 'http://localhost:7070/books';

const books: Ref<Book[]> = ref([]);

const createBook = (form: { title: string; authorIDs: number[]; pages: number }): void => {
  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ title: form.title, authorsIDs: form.authorIDs, pages: form.pages })
  };
  fetch(ENDPOINT, requestOptions).then((res) => readBooks());
};

async function readBooks() {
  const res = (await fetch(ENDPOINT)) as any;
  books.value = await res.json();
}

const updateBook = (form: {
  title: string;
  authorIDs: number[];
  pages: number;
  id: number;
}): void => {
  const requestOptions = {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ title: form.title, authorsIDs: form.authorIDs, pages: form.pages })
  };
  fetch(ENDPOINT + '/' + form.id, requestOptions).then((res) => readBooks());
};

const deleteBook = (book: Book): void => {
  fetch(ENDPOINT + '/' + book.id, { method: 'DELETE' }).then((res) => {
    console.log(res), readBooks();
  });
};

readBooks();
</script>

<script lang="ts">
export default defineComponent({
  name: 'BooksList',
  components: { ItemsTable, BooksForm },
  methods: {
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
  ></items-table>
</template>
