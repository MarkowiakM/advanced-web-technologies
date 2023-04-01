package com.example.SpringRestAPI.books;

import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface IBooksService {
    Collection<BookWithAuthorOutputDTO> getBooks(Pageable pageable);
    BookWithAuthorOutputDTO getBook(int id);
    Book getBookObj(int id);
    int addBook(BookInputDTO book);
    int removeBook(int id);
    int updateBook(int id, BookInputDTO book);

}
