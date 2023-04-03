<script lang="ts">
import { defineComponent } from 'vue';
import ImgGallery from '../components/ImgGallery.vue';

export default defineComponent({
  name: 'HomeView',
  components: { ImgGallery },
  methods: {
    setLayout(layout: string) {
      this.$store.commit('setLayout', layout);
    }
  },
  created() {
    this.setLayout('home-layout');
  }
});
</script>
<template>
  <img-gallery></img-gallery>
  <!-- <v-btn color="secondary" @click="setLayout('app-layout')">App Layout</v-btn> -->
  d
</template>
