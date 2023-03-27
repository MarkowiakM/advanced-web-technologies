package com.example.SpringRestAPI.rental;

public interface IRentalService {

    boolean rentBook(RentalDTO rentalDTO);
    boolean returnBook(int bookID);
    boolean isBookRented(int bookID);
}
