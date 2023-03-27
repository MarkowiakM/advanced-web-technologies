package com.example.SpringRestAPI.books;

import java.util.Collection;

public interface IBooksService {
    Collection<DTOBookWithAuthorOutput> getBooks();
    DTOBookWithAuthorOutput getBook(int id);
    void addBook(DTOBookInput book);
    boolean removeBook(int id);
    boolean updateBook(DTOBookInput book);

}