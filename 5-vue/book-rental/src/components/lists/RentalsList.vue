<script setup lang="ts">
import { defineComponent, ref, type Ref } from 'vue';
import type { Reader, Rental, RentedBook } from '../../types';
import ItemsTable from './ItemsTable.vue';
import RentalsForm from '../forms/RentalsForm.vue';

const ENDPOINT_RENTALS = 'http://localhost:7070/rentals';
const ENDPOINT_READERS = 'http://localhost:7070/readers';

type BookData = {
  id: number;
  rentalDate: string;
  title: string;
  authors: string;
};
const rentals: Ref<Rental[]> = ref([]);

const parseBookData = ({ book, rentalDate }: RentedBook) => {
  const date = new Date(rentalDate);
  return {
    id: book.id,
    rentalDate: `${date.getDay()}.${date.getMonth()}.${date.getFullYear()}`,
    title: book.title,
    authors: book.authors
      .reduce((auth, author) => auth + ', ' + author.name + ' ' + author.surname, '')
      .slice(1)
  } as BookData;
};
const createRental = (form: { bookID: number; readerID: number; date: string }): void => {
  console.log(form);
  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ bookID: form.bookID, readerID: form.readerID, date: form.date })
  };
  console.log(JSON.stringify({ bookID: form.bookID, readerID: form.readerID, date: form.date }));

  fetch(ENDPOINT_RENTALS, requestOptions).then(
    () => readRentals(getReaders()),
    (err) => console.log(err)
  );
};

const getReaders = (): Reader[] => {
  return rentals.value.map(({ reader }) => reader);
};
async function readReaders() {
  const res = (await fetch(ENDPOINT_READERS)) as any;
  rentals.value = (await res.json()).map((reader: Reader) => {
    return { reader: reader, rentedBooks: [] as RentedBook[] } as Rental;
  });
}

async function readRental(reader: Reader): Promise<Rental> {
  const res = await fetch(ENDPOINT_RENTALS + '/' + reader.id);
  return (await res.json()) as Rental;
}

async function readRentals(readers: Reader[]) {
  readers.forEach((reader) => {
    readRental(reader).then(
      (rental) => {
        rentals.value.find((rental) => rental.reader.id === reader.id)!.rentedBooks =
          rental.rentedBooks;
      },
      (err) => console.log(err)
    );
  });
}

const deleteRental = (book: BookData): void => {
  fetch(ENDPOINT_RENTALS + '/' + book.id, { method: 'DELETE' }).then((res) => {
    console.log(res), readRentals(getReaders());
  });
};

readReaders().then(() => readRentals(getReaders()));
</script>

<script lang="ts">
export default defineComponent({
  name: 'ReadersList',
  components: { ItemsTable, RentalsForm }
});
</script>

<template>
  <h2 class="bg-teal-accent-4 text-white px-6 py-3 mb-6">
    <span>Rentals</span>
    <rentals-form class="float-right my-auto" mode="add" @onSubmit="createRental"></rentals-form>
  </h2>
  <v-expansion-panels multiple>
    <v-expansion-panel v-for="rental in rentals" :key="rental.reader.id">
      <v-expansion-panel-title>{{
        rental.reader.name + ' ' + rental.reader.surname
      }}</v-expansion-panel-title>
      <v-expansion-panel-text
        ><items-table
          :table="{
            headers: ['book id', 'rental date', 'book title', 'authors'],
            items: rental.rentedBooks.map(parseBookData)
          }"
          :delete-item="deleteRental"
        ></items-table>
      </v-expansion-panel-text>
    </v-expansion-panel>
  </v-expansion-panels>
</template>
