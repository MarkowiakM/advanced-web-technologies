<script setup lang="ts">
import { defineComponent, ref, type Ref } from 'vue';
import type { Reader } from '../../types';
import ItemsTable from './ItemsTable.vue';
import ReadersForm from '../forms/ReadersForm.vue';

const ENDPOINT = 'http://localhost:7070/readers';

const readers: Ref<Reader[]> = ref([]);

const createReader = (form: { name: string; surname: string }): void => {
  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ name: form.name, surname: form.surname })
  };
  console.log(form);
  fetch(ENDPOINT, requestOptions).then((res) => readReaders());
};

async function readReaders() {
  const res = (await fetch(ENDPOINT)) as any;
  readers.value = await res.json();
}

const updateReader = (form: { name: string; surname: string; id: number }): void => {
  console.log('putting');
  const requestOptions = {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ name: form.name, surname: form.surname })
  };
  console.log(form);
  fetch(ENDPOINT + '/' + form.id, requestOptions).then((res) => readReaders());
};

const deleteReader = (reader: Reader): void => {
  fetch(ENDPOINT + '/' + reader.id, { method: 'DELETE' }).then((res) => {
    console.log(res), readReaders();
  });
};

readReaders();
</script>

<script lang="ts">
export default defineComponent({
  name: 'ReadersList',
  components: { ItemsTable, ReadersForm }
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
  ></items-table>
</template>
