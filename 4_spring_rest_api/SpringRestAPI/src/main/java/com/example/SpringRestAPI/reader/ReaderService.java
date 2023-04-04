package com.example.SpringRestAPI.reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.example.SpringRestAPI.reader.ReaderStatus.*;

@Service
public class ReaderService implements IReaderService{
    @Autowired
    IReaderRepository readerRepository;

    @Override
    public Collection<Reader> getReaders(Pageable pageable) {
        return readerRepository.findAll(pageable).getContent();
    }

    @Override
    public Reader getReader(int id) {
        return readerRepository.findById(id)
                .orElse(null);
    }

    @Override
    public void addReader(ReaderInputDTO readerDTO) {
        Reader reader = readerDTO.toReader();
        readerRepository.save(reader);
    }

    @Override
    public ReaderStatus updateReader(int id, ReaderInputDTO reader) {
        Reader r = readerRepository.findById(id)
                .orElse(null);
        if (r != null){
            r.setName(reader.getName());
            r.setSurname(reader.getSurname());
            readerRepository.save(r);
            return OK;
        } else {
            return READER_DOES_NOT_EXIST;
        }
    }

    @Override
    public ReaderStatus removeReader(int id) {
        Reader r = readerRepository.findById(id).orElse(null);
        if (r != null) {
            try {
                readerRepository.delete(r);
                return OK;
            } catch (Exception e) {
                return READER_HAS_RENTED_BOOKS;
            }
        } else {
            return READER_DOES_NOT_EXIST;
        }

    }
}
