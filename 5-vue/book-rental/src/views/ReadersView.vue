<script lang="ts">
import { defineComponent } from 'vue';
import ReadersList from '../components/ReadersList.vue';

export default defineComponent({
  name: 'ReadersView',
  components: { ReadersList }
});
</script>

<template>
  <v-container>
    <h2 class="bg-teal-accent-4 text-white px-6 py-3">Readers</h2>
    <readers-list class="table"></readers-list>
  </v-container>
</template>

<style>
.table {
  width: 90vw;
  margin: 30px auto;
}
</style>
