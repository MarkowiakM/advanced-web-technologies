<script lang="ts">
import { defineComponent } from 'vue';
import type { Table } from '../types';

export default defineComponent({
  props: {
    table: {
      type: Object as () => Table
    },
    editItem: {
      type: Object as () => (item: Object) => void
    },
    deleteItem: {
      type: Object as () => (item: Object) => void
    }
  }
});
</script>

<template>
  <v-table>
    <thead>
      <tr>
        <th v-for="header in [...table!.headers, ' ']" :key="header" class="text-left">
          {{ header }}
        </th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(item, itemIdx) in [...table!.items]" :key="itemIdx">
        <td v-for="([, value], valueIdx) in Object.entries(item)" :key="itemIdx + valueIdx">
          {{ value }}
        </td>
        <td :key="itemIdx" class="text-right">
          <v-icon class="mr-5 text-amber" @click="editItem!(item)">mdi-pencil</v-icon>
          <v-icon class="ml-5 text-deep-orange-darken-4" @click="deleteItem!(item)"
            >mdi-delete</v-icon
          >
        </td>
      </tr>
    </tbody>
  </v-table>
</template>
