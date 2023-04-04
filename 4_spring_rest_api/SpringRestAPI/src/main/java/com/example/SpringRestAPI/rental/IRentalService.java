package com.example.SpringRestAPI.rental;

import org.springframework.data.domain.Pageable;

public interface IRentalService {

    RentalStatus rentBook(RentalDTO rentalDTO);
    RentalStatus returnBook(int bookID);
    boolean isBookRented(int bookID);
    RentedReaderDTO getReaderRental(int readerID, Pageable pageable);
}
