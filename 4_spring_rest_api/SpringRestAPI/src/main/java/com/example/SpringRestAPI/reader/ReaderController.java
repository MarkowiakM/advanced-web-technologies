package com.example.SpringRestAPI.reader;

import com.example.SpringRestAPI.infoDTOs.ErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ReaderController {
    @Autowired
    IReaderService readerService;

    @RequestMapping(value = "/readers", method = RequestMethod.GET)
    public ResponseEntity<Object> getReaders(){
        Collection<Reader> readers = readerService.getReaders();
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
