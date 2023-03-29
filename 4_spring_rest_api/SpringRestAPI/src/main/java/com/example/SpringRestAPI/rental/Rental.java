package com.example.SpringRestAPI.rental;

import com.example.SpringRestAPI.books.Book;
import com.example.SpringRestAPI.reader.Reader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class Rental {
    private int id;
    private Book book;
    private Reader reader;
    private Date rentingDate;

    public Rental(Book book, Reader reader, Date rentingDate) {
        this.book = book;
        this.reader = reader;
        this.rentingDate = rentingDate;
    }
}
