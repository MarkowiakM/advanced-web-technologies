package com.example.SpringRestAPI.rental;

import com.example.SpringRestAPI.author.IAuthorService;
import com.example.SpringRestAPI.books.*;
import com.example.SpringRestAPI.reader.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.SpringRestAPI.rental.RentalStatus.*;

@Service
public class RentalService implements IRentalService{

    @Autowired
    IRentalRepository rentalRepository;
    @Autowired
    IBooksService booksService;
    @Autowired
    IAuthorService authorsService;
    @Autowired
    IReaderService readerService;

    @Override
    public RentalStatus rentBook(RentalDTO rentalDTO) {
        if (isBookRented(rentalDTO.getBookID())) return BOOK_ALREADY_RENTED;
        Reader reader = readerService.getReader(rentalDTO.getReaderID());
        Book book = booksService.getBookObj(rentalDTO.getBookID());
        if (reader == null) return READER_DOES_NOT_EXIST;
        if (book == null) return BOOK_DOES_NOT_EXIST;
        LocalDateTime rentDate;
        try {
            rentDate = LocalDateTime.parse(rentalDTO.getDate());
        } catch (Exception e){
            return WRONG_DATE_FORMAT;
        }
        Rental rental = new Rental(book, reader,rentDate);
        book.setRental(rental);
        rentalRepository.save(rental);
        return OK;
    }

    @Override
    public RentalStatus returnBook(int bookID) {
        Book book = booksService.getBookObj(bookID);
        if (book == null) return BOOK_DOES_NOT_EXIST;
        if (!isBookRented(bookID)) return BOOK_NOT_RENTED;
        rentalRepository.deleteByBook_Id(bookID);
        return OK;

    }

    @Override
    public boolean isBookRented(int bookID) {
        return rentalRepository.findByBookId(bookID).orElse(null) != null;
    }

    @Override
    public RentedReaderDTO getReaderRental(int readerID, Pageable pageable) {
        Reader reader = readerService.getReader(readerID);
        List<RentedBookDTO> bookRentalsDTO = new ArrayList<>();
        for (Rental r : rentalRepository.findAll(pageable)){
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
