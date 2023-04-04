<script lang="ts">
import { defineComponent, ref, type Ref } from 'vue';
import type { Reader } from '../../types';
import ItemsTable from './ItemsTable.vue';
import ReadersForm from '../forms/ReadersForm.vue';

export default defineComponent({
  data: () => ({
    ENDPOINT: 'http://localhost:7070/readers',
    currentPage: 1,
    pages: 1,
    readersAmount: 0,
    size: 10,
    readers: ref([]) as Ref<Reader[]>,
    showAlert: false
  }),
  watch: {
    currentPage: function () {
      this.readReaders();
    }
  },
  created() {
    this.readReaders();
    this.readReadersAmount();
  },
  name: 'ReadersList',
  components: { ItemsTable, ReadersForm },
  methods: {
    createReader(form: { name: string; surname: string }) {
      const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name: form.name, surname: form.surname })
      };
      console.log(form);
      fetch(this.ENDPOINT, requestOptions).then(
        () => (this.readReaders(), this.readReadersAmount())
      );
    },
    async readReaders() {
      const res = (await fetch(
        `${this.ENDPOINT}?page=${this.currentPage - 1}&size=${this.size}`
      )) as any;
      this.readers = await res.json();
    },
    async readReadersAmount() {
      const res = (await fetch(`${this.ENDPOINT}/amount`)) as any;
      this.readersAmount = (await res.json()).amount;
      this.pages = this.readersAmount / this.size + (this.readersAmount % this.size === 0 ? 0 : 1);
    },
    updateReader(form: { name: string; surname: string; id: number }) {
      console.log('putting');
      const requestOptions = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name: form.name, surname: form.surname })
      };
      console.log(form);
      fetch(this.ENDPOINT + '/' + form.id, requestOptions).then(() => this.readReaders());
    },
    deleteReader(reader: Reader) {
      fetch(this.ENDPOINT + '/' + reader.id, { method: 'DELETE' }).then((res) => {
        if (res.status === 409) {
          this.showAlert = true;
        } else {
          this.readReaders(), this.readReadersAmount();
        }
        console.log(res);
      });
    }
  }
});
</script>

<template>
  <h2 class="bg-teal-accent-4 text-white px-6 py-3 mb-6">
    <span>Readers</span>
    <readers-form class="float-right my-auto" mode="add" @onSubmit="createReader"></readers-form>
  </h2>
  <items-table
    :table="{ headers: ['id', 'name', 'surname'], items: readers }"
    :edit-item="updateReader"
    :delete-item="deleteReader"
    edit-form="readers-form"
    class="mb-6"
  ></items-table>
  <v-pagination v-model="currentPage" :length="pages" class="mt-6"></v-pagination>
  <v-alert
    v-if="showAlert"
    closable
    type="error"
    title="WRR"
    text="Nie mozna usunac czytelnika!"
  ></v-alert>
</template>
