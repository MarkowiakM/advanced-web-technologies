<script lang="ts">
import type { Author, Book, Reader } from '@/src/types';
import { type Ref, ref } from 'vue';

export default {
  data: () => ({
    ENDPOINT_READERS: 'http://localhost:7070/readers',
    ENDPOINT_BOOKS: 'http://localhost:7070/books/notRented',
    readers: ref([]) as Ref<Reader[]>,
    books: ref([]) as Ref<Book[]>,
    dialog: false,
    form: {
      reader: '',
      book: '',
      date: ''
    }
  }),
  props: {
    mode: {
      type: Object as () => 'add' | 'edit'
    },
    defaultValue: {
      type: Object as () => Book
    }
  },
  created() {
    this.readAuthors();
    this.readBooks();
  },
  methods: {
    async readAuthors() {
      const res = (await fetch(`${this.ENDPOINT_READERS}?page=0&size=1000`)) as any;
      this.readers = await res.json();
    },
    async readBooks() {
      const res = (await fetch(`${this.ENDPOINT_BOOKS}?page=0&size=1000`)) as any;
      this.books = await res.json();
    },
    getIdFromString(item: string) {
      return item.split(' - ').pop();
    },
    parseReaders() {
      return this.readers
        .map((reader) => reader.surname + ' ' + reader.name + ' - ' + reader.id)
        .sort();
    },
    parseAuthors(authors: Author[]) {
      return authors
        .reduce((auth, author) => auth + ', ' + author.name + ' ' + author.surname, '')
        .slice(1);
    },
    parseBooks() {
      return this.books
        .map((book) => book.title + ' ' + this.parseAuthors(book.authors) + ' - ' + book.id)
        .sort();
    },
    normalizeInputs() {
      this.form.reader = '';
      this.form.book = '';
    },
    onSubmit() {
      this.dialog = false;
      this.$emit('onSubmit', {
        bookID: this.getIdFromString(this.form.book),
        readerID: this.getIdFromString(this.form.reader),
        date: new Date().toISOString()
      });
      this.normalizeInputs();
    },
    onCancel() {
      this.normalizeInputs();
      this.dialog = false;
    }
  }
};
</script>
<template>
  <v-row>
    <v-dialog v-model="dialog" persistent width="1024">
      <template v-slot:activator="{ props }">
        <v-icon :class="mode === 'add' ? 'text-white' : 'text-amber'" v-bind="props">{{
          mode === 'add' ? 'mdi-plus' : 'mdi-pencil'
        }}</v-icon>
      </template>
      <v-card>
        <form v-on:submit.prevent="">
          <v-card-title class="mt-6 ml-6">
            <span class="text-h5">{{ mode === 'add' ? 'Add new book' : 'Edit book' }}</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-row>
                <v-col cols="12" sm="6">
                  <v-autocomplete
                    :items="parseReaders()"
                    label="Readers"
                    v-model="form.reader"
                  ></v-autocomplete>
                </v-col>
                <v-col cols="12" sm="6">
                  <v-autocomplete
                    :items="parseBooks()"
                    label="Books"
                    v-model="form.book"
                  ></v-autocomplete>
                </v-col>
              </v-row>
            </v-container>
            <small>*indicates required field</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn class="text-deep-orange-darken-4" variant="text" @click="onCancel()">
              Cancel
            </v-btn>
            <v-btn class="text-teal" variant="text" @click="onSubmit()"> Save </v-btn>
          </v-card-actions>
        </form>
      </v-card>
    </v-dialog>
  </v-row>
</template>
