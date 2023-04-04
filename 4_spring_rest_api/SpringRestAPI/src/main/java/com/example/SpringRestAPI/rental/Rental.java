package com.example.SpringRestAPI.rental;

import com.example.SpringRestAPI.books.Book;
import com.example.SpringRestAPI.reader.Reader;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @OneToOne(optional = false)
    private Book book;
    @ManyToOne(optional = false)
    private Reader reader;
    private OffsetDateTime rentingDate;

    public Rental(Book book, Reader reader, OffsetDateTime rentingDate) {
        this.book = book;
        this.reader = reader;
        this.rentingDate = rentingDate;
    }
}
