<script lang="ts">
import { defineComponent } from 'vue';
import BooksList from '../components/BooksList.vue';

export default defineComponent({
  name: 'BooksView',
  components: { BooksList },
  methods: {
    setLayout(layout: string) {
      this.$store.commit('setLayout', layout);
    }
  },
  created() {
    this.setLayout('app-layout');
  }
});
</script>

<template>
  <v-container>
    <books-list class="table"></books-list>
  </v-container>
</template>

<style scoped>
.table {
  width: 90vw;
  margin: 30px auto;
}
</style>
