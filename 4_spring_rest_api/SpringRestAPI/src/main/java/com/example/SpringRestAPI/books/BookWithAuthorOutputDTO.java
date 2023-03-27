package com.example.SpringRestAPI.books;

import com.example.SpringRestAPI.author.AuthorDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BookWithAuthorOutputDTO {
    private int id;
    private String title;
    private int pages;
    List<AuthorDTO> authors;

    public static BookWithAuthorOutputDTO fromBook(Book b, List<AuthorDTO> authors){
        return new BookWithAuthorOutputDTO(b.getId(), b.getTitle(), b.getPages(), authors);
    }

}
