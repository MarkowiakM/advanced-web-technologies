package com.example.SpringRestAPI.Books;

import com.example.SpringRestAPI.Author.Author;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Book {
    private int id;
    private String title;
    private List<Author> authors;
    private int pages;

    public Book(int id, String title, List<Author> authors, int pages) {
        this.id = id;
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
