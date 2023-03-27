package com.example.SpringRestAPI.rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RentalController {

    @Autowired
    IRentalService rentalService;

    @RequestMapping(value = "/add/rental", method = RequestMethod.POST)
    public ResponseEntity<Object> rentBook(@RequestBody RentalDTO rentalDTO){
        if (rentalService.rentBook(rentalDTO)) {
            System.out.println("The book has been rented");
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            System.out.println("Cannot rent the book");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/delete/rental/{bookID}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> returnBook(@PathVariable int bookID){
        if (rentalService.returnBook(bookID)) {
            System.out.println("The book has been returned");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            System.out.println("Cannot return the book");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
