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
    private Book book;
    private Reader reader;
    private Date rentingDate;

}
