<script lang="ts">
import { defineComponent, ref, type Ref } from 'vue';
import type { Reader } from '../../types';
import ItemsTable from './ItemsTable.vue';
import ReadersForm from '../forms/ReadersForm.vue';

export default defineComponent({
  data: () => ({
    ENDPOINT: 'http://localhost:7070/readers',
    currentPage: 1,
    pages: 2,
    size: 10,
    readers: ref([]) as Ref<Reader[]>
  }),
  watch: {
    currentPage: function () {
      this.readReaders();
    }
  },
  created() {
    this.readReaders();
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
      fetch(this.ENDPOINT, requestOptions).then((res) => this.readReaders());
    },
    async readReaders() {
      const res = (await fetch(this.ENDPOINT)) as any;
      this.readers = await res.json();
    },
    updateReader(form: { name: string; surname: string; id: number }) {
      console.log('putting');
      const requestOptions = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name: form.name, surname: form.surname })
      };
      console.log(form);
      fetch(this.ENDPOINT + '/' + form.id, requestOptions).then((res) => this.readReaders());
    },
    deleteReader(reader: Reader) {
      fetch(this.ENDPOINT + '/' + reader.id, { method: 'DELETE' }).then((res) => {
        console.log(res), this.readReaders();
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
</template>
