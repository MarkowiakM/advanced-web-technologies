<script lang="ts">
import { defineComponent } from 'vue';
import RentalsList from '../components/lists/RentalsList.vue';
export default defineComponent({
  name: 'RentalsView',
  components: { RentalsList }
});
</script>

<template>
  <v-container>
    <rentals-list class="table"></rentals-list>
  </v-container>
</template>

<style>
.table {
  width: 90vw;
  margin: 30px auto;
}
</style>
