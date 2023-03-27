package com.example.SpringRestAPI.author;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DTOAuthor {
    private int id;
    private String name;
    private String surname;

    public static DTOAuthor fromAuthor(Author a){
        return new DTOAuthor(a.getId(), a.getName(), a.getSurname());
    }
    public Author toAuthor(){
        return new Author(this.id, this.name, this.surname);
    }
}
