package com.example.SpringRestAPI.Books;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
@NoArgsConstructor
class Author {
    String name;
    String surname;
    ArrayList<Book> books;

}
