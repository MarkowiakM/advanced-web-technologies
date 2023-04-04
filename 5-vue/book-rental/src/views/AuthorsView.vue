<script lang="ts">
import { defineComponent } from 'vue';
import AuthorsList from '../components/lists/AuthorsList.vue';
export default defineComponent({
  name: 'AuthorsView',
  components: { AuthorsList }
});
</script>

<template>
  <v-container>
    <authors-list class="table"></authors-list>
  </v-container>
</template>

<style>
.table {
  width: 90vw;
  margin: 30px auto;
}
</style>
