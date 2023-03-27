package com.example.SpringRestAPI.Books;

import java.util.Collection;

public interface IBooksService {
    Collection<Book> getBooks();
    Book getBook(int id);
    void addBook(Book book);
    boolean removeBook(int id);
    boolean updateBook(Book book);

}
