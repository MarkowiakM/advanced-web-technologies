<script lang="ts">
import type { Author } from '@/src/types';

export default {
  data: () => ({
    dialog: false,
    form: {
      name: '',
      surname: '',
      id: -1
    }
  }),
  props: {
    mode: {
      type: Object as () => 'add' | 'edit'
    },
    title: String,
    defaultValue: {
      type: Object as () => Author
    }
  },
  created() {
    if (this.defaultValue) {
      this.form.name = this.defaultValue.name;
      this.form.surname = this.defaultValue.surname;
      this.form.id = this.defaultValue.id;
    }
  },
  methods: {
    clearInputs() {
      this.form.name = '';
      this.form.surname = '';
      this.form.id = -1;
    },
    onSubmit() {
      this.dialog = false;
      this.$emit('onSubmit', this.form);
      if (this.mode === 'add') {
        this.clearInputs();
      }
    },
    onCancel() {
      if (this.mode === 'add') {
        this.clearInputs();
      }
      this.dialog = false;
    }
  }
};
</script>

<template>
  <v-row>
    <v-dialog v-model="dialog" persistent width="1024">
      <template v-slot:activator="{ props }">
        <v-icon :class="mode === 'add' ? 'text-white' : 'text-amber'" v-bind="props">{{
          mode === 'add' ? 'mdi-plus' : 'mdi-pencil'
        }}</v-icon>
      </template>
      <v-card>
        <form v-on:submit.prevent="">
          <v-card-title class="mt-6 ml-6">
            <span class="text-h5">{{ title }}</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-row>
                <v-col cols="12">
                  <v-text-field label="Name*" required v-model="form.name"></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-text-field label="Surname*" required v-model="form.surname"></v-text-field>
                </v-col>
              </v-row>
            </v-container>
            <small>*indicates required field</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn class="text-deep-orange-darken-4" variant="text" @click="onCancel()">
              Cancel
            </v-btn>
            <v-btn class="text-teal" variant="text" @click="onSubmit()"> Save </v-btn>
          </v-card-actions>
        </form>
      </v-card>
    </v-dialog>
  </v-row>
</template>
