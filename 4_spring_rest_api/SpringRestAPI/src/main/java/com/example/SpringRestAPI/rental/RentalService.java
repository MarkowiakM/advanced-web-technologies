package com.example.SpringRestAPI.rental;

import com.example.SpringRestAPI.author.AuthorService;
import com.example.SpringRestAPI.author.IAuthorService;
import com.example.SpringRestAPI.books.*;
import com.example.SpringRestAPI.reader.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentalService implements IRentalService{

    @Autowired
    IRentalRepository rentalRepository;
    private static final List<Rental> rentalsRepo = new ArrayList<>();
    private static final IBooksService booksService = new BooksService();
    private static final IAuthorService authorsService = new AuthorService();
    private static final IReaderService readerService = new ReaderService();

    @Override
    public int rentBook(RentalDTO rentalDTO) {
        if (isBookRented(rentalDTO.getBookID())) return 1;
        Reader reader = readerService.getReader(rentalDTO.getReaderID());
        Book book = booksService.getBookObj(rentalDTO.getBookID());
        if (reader == null) return 2;
        if (book == null) return 3;
        LocalDateTime rentDate;
        try {
            rentDate = LocalDateTime.parse(rentalDTO.getDate());
        } catch (Exception e){
            return 4;
        }
        rentalsRepo.add(new Rental(book, reader,rentDate));
        return 0;
    }

    @Override
    public int returnBook(int bookID) {
        if (booksService.getBook(bookID) == null) return 2;
        if (!isBookRented(bookID)) return 1;
        rentalsRepo.removeIf(r -> r.getBook().getId() == bookID);
        return 0;

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

    @Override
    public RentedReaderDTO getReaderRental(int readerID) {
        Reader reader = readerService.getReader(readerID);
        List<RentedBookDTO> bookRentalsDTO = new ArrayList<>();
        for (Rental r :rentalsRepo){
            if (r.getReader().getId() == readerID){
                Book b = r.getBook();
                bookRentalsDTO.add(new RentedBookDTO(
                        BookWithAuthorOutputDTO.fromBook(
                                b,
                                authorsService.getAuthorsDTOOfBook(b.getAuthors())
                        ),
                        r.getRentingDate())
                );
            }
        }

        return new RentedReaderDTO(reader, bookRentalsDTO);
    }
}
