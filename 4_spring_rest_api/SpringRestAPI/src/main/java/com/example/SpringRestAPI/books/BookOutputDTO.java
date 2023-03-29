package com.example.SpringRestAPI.books;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookOutputDTO {
    private int id;
    private String title;
    private int pages;
    public static BookOutputDTO fromBook(Book b){
        return new BookOutputDTO(b.getId(), b.getTitle(), b.getPages());
    }
}
