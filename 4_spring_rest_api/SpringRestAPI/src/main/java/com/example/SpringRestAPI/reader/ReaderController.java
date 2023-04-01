package com.example.SpringRestAPI.reader;

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
public class ReaderController {
    @Autowired
    IReaderService readerService;

    @RequestMapping(value = "/readers", method = RequestMethod.GET)
    public ResponseEntity<Object> getReaders(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){
        Integer pageParam = page.orElse(0);
        Integer sizeParam = size.orElse(10);
        Pageable pageable = PageRequest.of(pageParam, sizeParam);
        Collection<Reader> readers = readerService.getReaders(pageable);
        if (!readers.isEmpty())
            return new ResponseEntity<>(readers, HttpStatus.OK);
        else
            return new ResponseEntity<>(new ErrorDTO("No readers found"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/readers/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getReader(@PathVariable int id) {
        Reader reader = readerService.getReader(id);
        if (reader != null)
            return new ResponseEntity<>(reader, HttpStatus.OK);
        else
            return new ResponseEntity<>(new ErrorDTO("The reader does not exist"), HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/readers", method = RequestMethod.POST)
    public ResponseEntity<Object> addReader(@RequestBody ReaderInputDTO reader) {
        readerService.addReader(reader);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/readers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateReader(@PathVariable int id, @RequestBody ReaderInputDTO reader) {
        if (readerService.updateReader(id, reader))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(new ErrorDTO("The reader does not exist"), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/readers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteReader(@PathVariable int id) {
        if (readerService.removeReader(id))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(new ErrorDTO("The reader does not exist"), HttpStatus.NOT_FOUND);
    }
}
