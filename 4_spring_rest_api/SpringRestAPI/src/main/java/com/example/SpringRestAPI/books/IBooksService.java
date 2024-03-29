package com.example.SpringRestAPI.books;

import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface IBooksService {
    Collection<BookWithAuthorOutputDTO> getBooks(Pageable pageable);
    BookWithAuthorOutputDTO getBook(int id);
    Book getBookObj(int id);
    BookStatus addBook(BookInputDTO book);
    BookStatus removeBook(int id);
    BookStatus updateBook(int id, BookInputDTO book);
    List<BookWithAuthorOutputDTO> getNotRentedBooks(Pageable pageable);
    long getAmountOfBooks();
}
