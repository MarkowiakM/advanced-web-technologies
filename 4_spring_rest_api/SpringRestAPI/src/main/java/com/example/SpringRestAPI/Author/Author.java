package com.example.SpringRestAPI.Author;

import com.example.SpringRestAPI.Books.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class Author {
    private int id;
    private String name;
    private String surname;
    private List<Book> books;

    public Author(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        books = new ArrayList<>();
    }

    public void addBook(Book book){
        books.add(book);
    }
}
