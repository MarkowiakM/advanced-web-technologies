package com.example.SpringRestAPI.rental;

import com.example.SpringRestAPI.infoDTOs.ErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RentalController {

    @Autowired
    IRentalService rentalService;

    @RequestMapping(value = "/rentals", method = RequestMethod.POST)
    public ResponseEntity<Object> rentBook(@RequestBody RentalDTO rentalDTO){
        switch (rentalService.rentBook(rentalDTO)){
            case 0: {
                System.out.println("The book has been rented");
                return new ResponseEntity<>(HttpStatus.OK);
            } case 1:{
                return new ResponseEntity<>(new ErrorDTO("The book is already rented"), HttpStatus.NOT_FOUND);
            } case 2:{
                return new ResponseEntity<>(new ErrorDTO("The reader does not exist"), HttpStatus.NOT_FOUND);
            } case 3:{
                return new ResponseEntity<>(new ErrorDTO("The book does not exist"), HttpStatus.NOT_FOUND);
            } case 4: {
                return new ResponseEntity<>(new ErrorDTO("Incorrect date format"), HttpStatus.NOT_FOUND);
            } default: {
                return new ResponseEntity<>(new ErrorDTO("Unexpected error"), HttpStatus.NOT_FOUND);
            }
        }
    }

    @RequestMapping(value = "/rentals/{bookID}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> returnBook(@PathVariable int bookID){
        switch (rentalService.returnBook(bookID)) {
            case 0: {
                System.out.println("The book has been returned");
                return new ResponseEntity<>(HttpStatus.OK);
            } case 1: {
                System.out.println("Cannot return the book");
                return new ResponseEntity<>(new ErrorDTO("The rental does not exist"), HttpStatus.NOT_FOUND);
            } case 2: {
                return new ResponseEntity<>(new ErrorDTO("The book does not exist"), HttpStatus.NOT_FOUND);
            } default: {
                return new ResponseEntity<>(new ErrorDTO("Unexpected error"), HttpStatus.NOT_FOUND);
            }
        }
    }

    @RequestMapping(value = "/rentals/{readerID}", method = RequestMethod.GET)
    public ResponseEntity<Object> getReaderRentals(@PathVariable int readerID){
        RentedReaderDTO rentedReaderDTO = rentalService.getReaderRental(readerID);
        if (!rentedReaderDTO.getRentedBooks().isEmpty() && rentedReaderDTO.getReader() != null)
            return new ResponseEntity<>(rentedReaderDTO, HttpStatus.OK);
        else if (rentedReaderDTO.getRentedBooks().isEmpty()) {
            return new ResponseEntity<>(new ErrorDTO("The reader has not rented any book."), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new ErrorDTO("The reader does not exist."), HttpStatus.NOT_FOUND);
        }
    }

}