package com.example.SpringRestAPI.rental;

import com.example.SpringRestAPI.books.BookWithAuthorOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class RentedBookDTO {
    private BookWithAuthorOutputDTO book;
    private LocalDateTime rentalDate;
}
