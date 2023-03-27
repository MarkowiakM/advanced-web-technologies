package com.example.SpringRestAPI.Author;

import java.util.Collection;

public interface IAuthorService {
    Collection<Author> getAuthors();
    DTOAuthorWithBooksOutput getAuthor(int id);
    Author getAuthorObj(int id);
    void addAuthor(Author author);
    boolean removeAuthor(int id);
    boolean updateAuthor(Author author);
}
