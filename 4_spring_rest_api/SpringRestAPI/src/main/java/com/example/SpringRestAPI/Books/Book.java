package com.example.SpringRestAPI.Books;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Book {
    private int id;
    private String title;
    private String author;
    int pages;

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPages(int pages) { this.pages = pages; }

}
