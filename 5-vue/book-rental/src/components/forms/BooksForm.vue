<script lang="ts">
import type { Author } from '@/src/types';

export default {
  data: () => ({
    dialog: false
  }),
  props: {
    title: String,
    book: {
      type: Object as () => Author
    }
  }
};
</script>

<template>
  <v-row>
    <v-dialog v-model="dialog" persistent width="1024">
      <template v-slot:activator="{ props }">
        <v-icon class="text-white" v-bind="props">mdi-plus</v-icon>
      </template>
      <v-card>
        <v-card-title class="mt-6 ml-6">
          <span class="text-h5">{{ title }}</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field label="Name*" required></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field label="Surname*" required></v-text-field>
              </v-col>

            </v-row>
          </v-container>
          <small>*indicates required field</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn class="text-deep-orange-darken-4" variant="text" @click="dialog = false">
            Cancel
          </v-btn>
          <v-btn class="text-teal" variant="text" @click="dialog = false"> Save </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>
