<script setup lang="ts">
const ENDPOINT_AUTHORS = 'http://localhost:7070/authors';
const authors: Ref<Author[]> = ref([]);

async function readAuthors() {
  const res = (await fetch(ENDPOINT_AUTHORS)) as any;
  authors.value = await res.json();
}

const parseAuthors = () => {
  return authors.value
    .map((author) => author.surname + ' ' + author.name + ' - ' + author.id)
    .sort();
};
readAuthors();
</script>

<script lang="ts">
import type { Author, Book } from '@/src/types';
import { type Ref, ref } from 'vue';

export default {
  data: () => ({
    ENDPOINT_BOOK: 'http://localhost:7070/books',
    book: { title: '', authors: [] as Author[], pages: 0, id: -1 },
    dialog: false,
    form: {
      title: '',
      authors: [] as string[],
      pages: 0,
      id: -1
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
    this.setDefaultFormValue();
    if (this.mode === 'edit' && this.defaultValue) {
      this.readBook(this.defaultValue.id);
    }
  },
  methods: {
    async readBook(id: number) {
      const res = (await fetch(this.ENDPOINT_BOOK + '/' + id)) as any;
      this.book = await res.json();
      this.form.authors = this.book.authors
        .map((author) => author.surname + ' ' + author.name + ' - ' + author.id)
        .sort();
      this.form.pages = this.book.pages;
      this.form.title = this.book.title;
      this.form.id = this.book.id;
    },
    getIdFromString(author: string) {
      return author.split(' - ').pop();
    },
    setDefaultFormValue() {
      if (this.book) {
        this.form.title = this.book.title;
        this.form.authors = this.book.authors
          .map((author) => author.surname + ' ' + author.name + ' - ' + author.id)
          .sort();
        this.form.pages = this.book.pages;
        this.form.id = this.book.id;
      }
    },
    normalizeInputs() {
      if (this.mode === 'add') {
        this.form.title = '';
        this.form.authors = [];
        this.form.pages = 0;
        this.form.id = -1;
      } else {
        this.setDefaultFormValue();
      }
    },
    onSubmit() {
      this.dialog = false;
      this.$emit('onSubmit', {
        title: this.form.title,
        authorIDs: this.form.authors.map((author) => Number(this.getIdFromString(author))),

        pages: this.form.pages,
        id: this.form.id
      });
      console.log(this.form.authors.map((author) => Number(this.getIdFromString(author))));
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
                <v-col cols="12">
                  <v-text-field
                    label="Book Title*"
                    hint="full title"
                    persistent-hint
                    v-model="form.title"
                    required
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="6">
                  <v-text-field
                    v-model="form.pages"
                    type="number"
                    label="Pages*"
                    required
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="6">
                  <v-autocomplete
                    :items="parseAuthors()"
                    label="Authors"
                    v-model="form.authors"
                    multiple
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
