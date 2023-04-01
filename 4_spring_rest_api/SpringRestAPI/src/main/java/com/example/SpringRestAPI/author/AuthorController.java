package com.example.SpringRestAPI.author;

import com.example.SpringRestAPI.infoDTOs.ErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
public class AuthorController {

    @Autowired
    IAuthorService authorService;
    @RequestMapping(value = "/authors/{page}/{size}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAuthors(@PathVariable(required = false) int page, @PathVariable(required = false) int size){
        Pageable pagination = PageRequest.of(page, size);
        Collection<AuthorDTO> authors = authorService.getAuthors(pagination);
        if (!authors.isEmpty())
            return new ResponseEntity<>(authors, HttpStatus.OK);
        else
            return new ResponseEntity<>(new ErrorDTO("No authors in database"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/authors/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAuthor(@PathVariable int id) {
        AuthorWithBooksOutputDTO author = authorService.getAuthor(id);
        if (author != null) {
            return new ResponseEntity<>(author, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorDTO("The author does not exists"), HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/authors", method = RequestMethod.POST)
    public ResponseEntity<Object> addAuthor(@RequestBody AuthorInputDTO author) {
        authorService.addAuthor(author);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/authors/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateAuthor(@PathVariable int id, @RequestBody AuthorInputDTO author) {
        if (authorService.updateAuthor(id, author))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(new ErrorDTO("The Author does not exist."), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/authors/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteAuthor(@PathVariable int id) {
        if (authorService.removeAuthor(id))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(new ErrorDTO("The Author does not exist."), HttpStatus.NOT_FOUND);
    }
}
