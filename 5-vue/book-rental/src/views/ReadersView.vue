<script lang="ts">
import { defineComponent } from 'vue';
import ReadersList from '../components/lists/ReadersList.vue';

export default defineComponent({
  name: 'ReadersView',
  components: { ReadersList }
});
</script>

<template>
  <v-container>
    <readers-list class="table"></readers-list>
  </v-container>
</template>

<style>
.table {
  width: 90vw;
  margin: 30px auto;
}
</style>
