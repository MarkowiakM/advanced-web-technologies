package com.example.SpringRestAPI.Books;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DTOBookOutput {
    private int id;
    private String title;
    private int pages;

    public static DTOBookOutput fromBook(Book b){
        return new DTOBookOutput(b.getId(), b.getTitle(), b.getPages());
    }
}
