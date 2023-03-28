package com.example.SpringRestAPI.rental;

import com.example.SpringRestAPI.books.BookWithAuthorOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class RentedBookDTO {
    private BookWithAuthorOutputDTO book;
    private Date rentalDate;
}
