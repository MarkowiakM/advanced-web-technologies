package com.example.SpringRestAPI.author;

import com.example.SpringRestAPI.books.BookOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AuthorWithBooksOutputDTO {
    private int id;
    private String name;
    private String surname;
    private List<BookOutputDTO> books;

    public static AuthorWithBooksOutputDTO fromAuthor(Author a, List<BookOutputDTO> books){
        return new AuthorWithBooksOutputDTO(a.getId(), a.getName(), a.getSurname(), books);
    }
}
