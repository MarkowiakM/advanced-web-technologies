package com.example.SpringRestAPI.author;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthorDTO {
    private int id;
    private String name;
    private String surname;

    public static AuthorDTO fromAuthor(Author a){
        return new AuthorDTO(a.getId(), a.getName(), a.getSurname());
    }
}
