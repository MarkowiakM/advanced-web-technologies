package com.example.SpringRestAPI.author;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthorInputDTO {
    private String name;
    private String surname;

    public Author toAuthor(){
        return new Author(this.name, this.surname);
    }
}
