package com.example.SpringRestAPI.books;

import com.example.SpringRestAPI.author.DTOAuthor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DTOBookWithAuthorOutput{
    private int id;
    private String title;
    private int pages;
    List<DTOAuthor> authors;

    public static DTOBookWithAuthorOutput fromBook(Book b, List<DTOAuthor> authors){
        return new DTOBookWithAuthorOutput(b.getId(), b.getTitle(), b.getPages(), authors);
    }

}
