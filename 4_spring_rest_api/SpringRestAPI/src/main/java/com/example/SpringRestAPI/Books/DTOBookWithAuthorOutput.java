package com.example.SpringRestAPI.Books;

import com.example.SpringRestAPI.Author.DTOAuthorOutput;
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
    List<DTOAuthorOutput> authors;

    public static DTOBookWithAuthorOutput fromBook(Book b, List<DTOAuthorOutput> authors){
        return new DTOBookWithAuthorOutput(b.getId(), b.getTitle(), b.getPages(), authors);
    }

}
