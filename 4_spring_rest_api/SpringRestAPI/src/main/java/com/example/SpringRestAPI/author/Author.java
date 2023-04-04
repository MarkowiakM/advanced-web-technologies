package com.example.SpringRestAPI.author;

import com.example.SpringRestAPI.books.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    private String surname;
    @ManyToMany(mappedBy = "authors", cascade = CascadeType.PERSIST)
    private List<Book> books;

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
        books = new ArrayList<>();
    }

    public void addBook(Book book){
        books.add(book);
    }
}
