package com.example.SpringRestAPI.author;

import java.util.Collection;
import java.util.List;

public interface IAuthorService {
    Collection<DTOAuthor> getAuthors();
    DTOAuthorWithBooksOutput getAuthor(int id);
    List<DTOAuthor> getAuthorsDTOOfBook(List<Author> authors);
    Author getAuthorObj(int id);
    void addAuthor(DTOAuthor author);
    boolean removeAuthor(int id);
    boolean updateAuthor(DTOAuthor author);
}
