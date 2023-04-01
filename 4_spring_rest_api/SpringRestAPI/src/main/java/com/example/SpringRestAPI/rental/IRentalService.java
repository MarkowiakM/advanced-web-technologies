package com.example.SpringRestAPI.rental;

import org.springframework.data.domain.Pageable;

public interface IRentalService {

    int rentBook(RentalDTO rentalDTO);
    int returnBook(int bookID);
    boolean isBookRented(int bookID);
    RentedReaderDTO getReaderRental(int readerID, Pageable pageable);
}
