package com.example.SpringRestAPI.author;

import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface IAuthorService {
    Collection<AuthorDTO> getAuthors(Pageable pageable);
    AuthorWithBooksOutputDTO getAuthor(int id);
    List<AuthorDTO> getAuthorsDTOOfBook(List<Author> authors);
    Author getAuthorObj(int id);
    void addAuthor(AuthorInputDTO author);
    AuthorStatus removeAuthor(int id);
    AuthorStatus updateAuthor(int id, AuthorInputDTO author);
    long getAmountOfAuthors();
}
