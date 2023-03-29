package com.example.SpringRestAPI.author;

import java.util.Collection;
import java.util.List;

public interface IAuthorService {
    Collection<AuthorDTO> getAuthors();
    AuthorWithBooksOutputDTO getAuthor(int id);
    List<AuthorDTO> getAuthorsDTOOfBook(List<Author> authors);
    Author getAuthorObj(int id);
    void addAuthor(AuthorInputDTO author);
    boolean removeAuthor(int id);
    boolean updateAuthor(int id, AuthorInputDTO author);
}
