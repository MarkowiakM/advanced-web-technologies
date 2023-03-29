package com.example.SpringRestAPI.author;

import com.example.SpringRestAPI.books.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
        books = new ArrayList<>();
    }

    public void addBook(Book book){
        books.add(book);
    }
}
