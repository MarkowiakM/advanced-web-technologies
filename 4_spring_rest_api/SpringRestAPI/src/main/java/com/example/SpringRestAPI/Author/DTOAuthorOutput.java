package com.example.SpringRestAPI.Author;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DTOAuthorOutput {
    private int id;
    private String name;
    private String surname;

    public static DTOAuthorOutput fromAuthor(Author a){
        return new DTOAuthorOutput(a.getId(), a.getName(), a.getSurname());
    }
}
