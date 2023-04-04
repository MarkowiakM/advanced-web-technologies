import type { Author } from './Author';

export type Book = {
  id: number;
  title: string;
  pages: number;
  authors: Author[];
};
