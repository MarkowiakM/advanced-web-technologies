<script lang="ts">
import { defineComponent } from 'vue';
export default defineComponent({
  name: 'HomeView',
  components: {},
  methods: {
    setLayout(layout: string) {
      this.$store.commit('setLayout', layout);
    }
  }
});
</script>
<template>
  <div>home</div>
  <v-btn color="secondary" @click="setLayout('app-layout')">App Layout</v-btn>
</template>
