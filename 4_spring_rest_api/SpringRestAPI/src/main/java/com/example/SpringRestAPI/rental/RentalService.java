package com.example.SpringRestAPI.rental;

import com.example.SpringRestAPI.books.*;
import com.example.SpringRestAPI.reader.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

@Service
public class RentalService implements IRentalService{

    private static final List<Rental> rentalsRepo = new ArrayList<>();
    private static final IBooksService booksService = new BooksService();
    private static final IReaderService readerService = new ReaderService();

    @Override
    public boolean rentBook(RentalDTO rentalDTO) {
        if (isBookRented(rentalDTO.getBookID())) return false;
        Reader reader = readerService.getReader(rentalDTO.getReaderID());
        Book book = booksService.getBookObj(rentalDTO.getBookID());
        Date rentDate;
        try {
            rentDate = Date.valueOf(rentalDTO.getSQLDate());
        } catch (Exception e){
            return false;
        }
        rentalsRepo.add(new Rental(book, reader,rentDate));
        return true;
    }

    @Override
    public boolean returnBook(int bookID) {
        if (!isBookRented(bookID)) return false;
        rentalsRepo.removeIf(r -> r.getBook().getId() == bookID);
        return true;

    }

    @Override
    public boolean isBookRented(int bookID) {
        for (Rental r : rentalsRepo){
            if (r.getBook().getId() == bookID){
                return true;
            }
        }
        return false;
    }
}
