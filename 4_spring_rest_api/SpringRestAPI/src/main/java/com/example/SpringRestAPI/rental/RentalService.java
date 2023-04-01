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
        rentalRepository.save(new Rental(book, reader,rentDate));
        return 0;
    }

    @Override
    public int returnBook(int bookID) {
        Book book = booksService.getBookObj(bookID);
        if (book == null) return 2;
        if (!isBookRented(bookID)) return 1;
        rentalRepository.deleteByBook_Id(bookID);
        return 0;

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
