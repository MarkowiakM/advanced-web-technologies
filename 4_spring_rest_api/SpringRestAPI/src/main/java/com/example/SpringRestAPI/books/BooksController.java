package com.example.SpringRestAPI.books;

import com.example.SpringRestAPI.infoDTOs.ErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

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
        if (!books.isEmpty())
            return new ResponseEntity<>(books, HttpStatus.OK);
        else
            return new ResponseEntity<>(new ErrorDTO("No book found"), HttpStatus.NO_CONTENT);
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
        booksService.addBook(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateBook(@PathVariable int id, @RequestBody BookInputDTO book) {
        if (booksService.updateBook(id, book))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(new ErrorDTO("The book does not exist"), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBook(@PathVariable int id) {
        if (booksService.removeBook(id))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(new ErrorDTO("The book does not exist"),HttpStatus.NOT_FOUND);
    }
}