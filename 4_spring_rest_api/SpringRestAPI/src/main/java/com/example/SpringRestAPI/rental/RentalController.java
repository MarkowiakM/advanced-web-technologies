package com.example.SpringRestAPI.rental;

import com.example.SpringRestAPI.infoDTOs.ErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
public class RentalController {

    @Autowired
    IRentalService rentalService;

    @RequestMapping(value = "/rentals", method = RequestMethod.POST)
    public ResponseEntity<Object> rentBook(@RequestBody RentalDTO rentalDTO){
        switch (rentalService.rentBook(rentalDTO)) {
            case OK -> {
                System.out.println("The book has been rented");
                return new ResponseEntity<>(HttpStatus.OK);
            }
            case BOOK_ALREADY_RENTED -> {
                return new ResponseEntity<>(new ErrorDTO("The book is already rented"), HttpStatus.NOT_FOUND);
            }
            case READER_DOES_NOT_EXIST -> {
                return new ResponseEntity<>(new ErrorDTO("The reader does not exist"), HttpStatus.NOT_FOUND);
            }
            case BOOK_DOES_NOT_EXIST -> {
                return new ResponseEntity<>(new ErrorDTO("The book does not exist"), HttpStatus.NOT_FOUND);
            }
            case WRONG_DATE_FORMAT -> {
                return new ResponseEntity<>(new ErrorDTO("Incorrect date format"), HttpStatus.NOT_FOUND);
            }
            default -> {
                return new ResponseEntity<>(new ErrorDTO("Unexpected error"), HttpStatus.NOT_FOUND);
            }
        }
    }

    @RequestMapping(value = "/rentals/{bookID}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> returnBook(@PathVariable int bookID){
        switch (rentalService.returnBook(bookID)) {
            case OK -> {
                System.out.println("The book has been returned");
                return new ResponseEntity<>(HttpStatus.OK);
            }
            case READER_DOES_NOT_EXIST -> {
                System.out.println("Cannot return the book");
                return new ResponseEntity<>(new ErrorDTO("The rental does not exist"), HttpStatus.NOT_FOUND);
            }
            case BOOK_DOES_NOT_EXIST -> {
                return new ResponseEntity<>(new ErrorDTO("The book does not exist"), HttpStatus.NOT_FOUND);
            }
            default -> {
                return new ResponseEntity<>(new ErrorDTO("Unexpected error"), HttpStatus.NOT_FOUND);
            }
        }
    }

    @RequestMapping(value = "/rentals/{readerID}", method = RequestMethod.GET)
    public ResponseEntity<Object> getReaderRentals(@PathVariable int readerID, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){
        Integer pageParam = page.orElse(0);
        Integer sizeParam = size.orElse(10);
        Pageable pageable = PageRequest.of(pageParam, sizeParam);
        RentedReaderDTO rentedReaderDTO = rentalService.getReaderRental(readerID, pageable);
        if (rentedReaderDTO.getReader() != null )
            return new ResponseEntity<>(rentedReaderDTO, HttpStatus.OK);
        else
            return new ResponseEntity<>(new ErrorDTO("The reader does not exist."), HttpStatus.NOT_FOUND);
        }

}
