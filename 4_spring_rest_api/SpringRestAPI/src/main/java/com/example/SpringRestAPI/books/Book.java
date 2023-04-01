package com.example.SpringRestAPI.books;

import com.example.SpringRestAPI.author.Author;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String title;
    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
    private List<Author> authors;
    private int pages;

    public Book(String title, List<Author> authors, int pages) {
        this.title = title;
        this.pages = pages;
        if (authors == null)
            this.authors = new ArrayList<>();
        else {
            this.authors = authors;
            for (Author a : this.authors)
                a.addBook(this);
        }
    }

    public void setTitle(String title) { this.title = title; }
    public void setPages(int pages) { this.pages = pages; }
    public void addAuthor(Author author){
        this.authors.add(author);
    }

}
