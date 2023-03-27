package com.example.SpringRestAPI.author;

import com.example.SpringRestAPI.books.DTOBookOutput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DTOAuthorWithBooksOutput {
    private int id;
    private String name;
    private String surname;
    private List<DTOBookOutput> books;

    public static DTOAuthorWithBooksOutput fromAuthor(Author a, List<DTOBookOutput> books){
        return new DTOAuthorWithBooksOutput(a.getId(), a.getName(), a.getSurname(), books);
    }
}
