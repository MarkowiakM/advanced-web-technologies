import type { Book } from './Book';
import type { Reader } from './Reader';

export type RentedBook = {
  book: Book;
  rentalDate: string;
};

export type Rental = {
  reader: Reader;
  rentedBooks: RentedBook[];
};
