package com.example.SpringRestAPI.Books;

import com.example.SpringRestAPI.Author.DTOAuthorOutput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
