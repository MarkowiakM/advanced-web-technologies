package com.example.SpringRestAPI.rental;

import com.example.SpringRestAPI.books.Book;
import com.example.SpringRestAPI.reader.Reader;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @ManyToOne
    private Book book;
    @ManyToOne
    private Reader reader;
    private LocalDateTime rentingDate;

    public Rental(Book book, Reader reader, LocalDateTime rentingDate) {
        this.book = book;
        this.reader = reader;
        this.rentingDate = rentingDate;
    }
}
