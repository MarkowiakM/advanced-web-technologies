package com.example.SpringRestAPI.books;

import java.util.Collection;

public interface IBooksService {
    Collection<BookWithAuthorOutputDTO> getBooks();
    BookWithAuthorOutputDTO getBook(int id);
    Book getBookObj(int id);
    void addBook(BookInputDTO book);
    boolean removeBook(int id);
    boolean updateBook(BookInputDTO book);

}
