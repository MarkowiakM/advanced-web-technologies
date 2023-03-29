package com.example.SpringRestAPI.rental;

public interface IRentalService {

    int rentBook(RentalDTO rentalDTO);
    int returnBook(int bookID);
    boolean isBookRented(int bookID);
    RentedReaderDTO getReaderRental(int readerID);
}
