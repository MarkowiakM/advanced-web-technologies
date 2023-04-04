<script lang="ts">
import { defineComponent } from 'vue';
import type { Table } from '../../types';
import AuthorsForm from '../forms/AuthorsForm.vue';
export default defineComponent({
  components: { AuthorsForm },
  props: {
    table: {
      type: Object as () => Table
    },
    editItem: {
      type: Function
    },
    deleteItem: {
      type: Function
    },
    editForm: String,
    formTitle: String
  },
  created() {
    console.log(this.editItem);
    console.log('from created');
    
    
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
        <td :key="itemIdx" class="text-right" width="100px">
          <component
            :is="editForm"
            class="float-left my-auto"
            mode="edit"
            :title="formTitle"
            @onSubmit="editItem"
            :defaultValue="item"
          ></component>
          <v-icon class="text-deep-orange-darken-4" @click="deleteItem!(item)">mdi-delete</v-icon>
        </td>
      </tr>
    </tbody>
  </v-table>
</template>
