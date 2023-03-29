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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
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
