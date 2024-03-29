package com.example.SpringRestAPI.books;

import com.example.SpringRestAPI.infoDTOs.AmountDTO;
import com.example.SpringRestAPI.infoDTOs.ErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.example.SpringRestAPI.books.BookStatus.OK;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
public class BooksController {
    @Autowired
    IBooksService booksService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseEntity<Object> getBooks(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){
        Integer pageParam = page.orElse(0);
        Integer sizeParam = size.orElse(10);
        Pageable pageable = PageRequest.of(pageParam, sizeParam);
        Collection<BookWithAuthorOutputDTO> books = booksService.getBooks(pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getBook(@PathVariable int id) {
        BookWithAuthorOutputDTO book = booksService.getBook(id);
        if (book != null)
            return new ResponseEntity<>(book, HttpStatus.OK);
        else
            return new ResponseEntity<>(new ErrorDTO("The book does not exist"), HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public ResponseEntity<Object> addBook(@RequestBody BookInputDTO book) {
        if (booksService.addBook(book) == OK)
            return new ResponseEntity<>(HttpStatus.CREATED);
        else
            return new ResponseEntity<>(new ErrorDTO("One of the authors does not exist."), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateBook(@PathVariable int id, @RequestBody BookInputDTO book) {
        switch (booksService.updateBook(id, book)) {
            case OK -> {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            case AUTHOR_DOES_NOT_EXIST -> {
                return new ResponseEntity<>(new ErrorDTO("One of the authors does not exist"), HttpStatus.NOT_FOUND);
            }
            case BOOK_DOES_NOT_EXIST -> {
                return new ResponseEntity<>(new ErrorDTO("The book does not exist"), HttpStatus.NOT_FOUND);
            }
            default -> {
                return new ResponseEntity<>(new ErrorDTO("Unexpected error"), HttpStatus.NOT_FOUND);
            }
        }
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBook(@PathVariable int id) {
        switch (booksService.removeBook(id)) {
            case OK -> {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            case BOOK_DOES_NOT_EXIST -> {
                return new ResponseEntity<>(new ErrorDTO("The book does not exist"), HttpStatus.NOT_FOUND);
            }
            case BOOK_IS_RENTED -> {
                return new ResponseEntity<>(new ErrorDTO("Cannot delete rented book."), HttpStatus.CONFLICT);
            }
            default -> {
                return new ResponseEntity<>(new ErrorDTO("Unexpected error"), HttpStatus.NOT_FOUND);
            }
        }
    }

    @RequestMapping(value = "/books/amount", method = RequestMethod.GET)
    public ResponseEntity<Object> getAmountOfBook() {
        return new ResponseEntity<>(new AmountDTO(booksService.getAmountOfBooks()), HttpStatus.OK);
    }

    @RequestMapping(value = "/books/notRented", method = RequestMethod.GET)
    public ResponseEntity<Object> getNotRentedBooks(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){
        Integer pageParam = page.orElse(0);
        Integer sizeParam = size.orElse(10);
        Pageable pageable = PageRequest.of(pageParam, sizeParam);
        List<BookWithAuthorOutputDTO> books = booksService.getNotRentedBooks(pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}