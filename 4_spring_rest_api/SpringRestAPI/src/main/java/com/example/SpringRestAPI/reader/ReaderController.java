package com.example.SpringRestAPI.reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReaderController {
    @Autowired
    IReaderService readerService;

    @RequestMapping(value = "/get/readers", method = RequestMethod.GET)
    public ResponseEntity<Object> getReaders(){
        return new ResponseEntity<>(readerService.getReaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/reader/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getReader(@PathVariable int id) {
        return new ResponseEntity<>(readerService.getReader(id), HttpStatus.OK);
    }
    @RequestMapping(value = "/add/reader", method = RequestMethod.POST)
    public ResponseEntity<Object> addReader(@RequestBody Reader reader) {
        readerService.addReader(reader);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/update/reader", method = RequestMethod.POST)
    public ResponseEntity<Object> updateReader(@RequestBody Reader reader) {
        if (readerService.updateReader(reader))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/delete/reader/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delereReader(@PathVariable int id) {
        if (readerService.removeReader(id))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
