package com.example.SpringRestAPI.books;

import com.example.SpringRestAPI.infoDTOs.ErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class BooksController {
    @Autowired
    IBooksService booksService;

    @RequestMapping(value = "/get/books", method = RequestMethod.GET)
    public ResponseEntity<Object> getBooks(){
        Collection<BookWithAuthorOutputDTO> books = booksService.getBooks();
        if (!books.isEmpty())
            return new ResponseEntity<>(books, HttpStatus.OK);
        else
            return new ResponseEntity<>(new ErrorDTO("No book found"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/get/book/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getBook(@PathVariable int id) {
        BookWithAuthorOutputDTO book = booksService.getBook(id);
        if (book != null)
            return new ResponseEntity<>(book, HttpStatus.OK);
        else
            return new ResponseEntity<>(new ErrorDTO("The book does not exist"), HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/add/book", method = RequestMethod.POST)
    public ResponseEntity<Object> addBook(@RequestBody BookInputDTO book) {
        booksService.addBook(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/book", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateBook(@RequestBody BookInputDTO book) {
        if (booksService.updateBook(book))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(new ErrorDTO("The book does not exist"), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/delete/book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBook(@PathVariable int id) {
        if (booksService.removeBook(id))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(new ErrorDTO("The book does not exist"),HttpStatus.NOT_FOUND);
    }
}