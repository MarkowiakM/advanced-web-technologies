package com.example.SpringRestAPI.reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ReaderService implements IReaderService{
    @Autowired
    IReaderRepository readerRepository;

    @Override
    public Collection<Reader> getReaders() {
        return readerRepository.findAll();
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
    public boolean updateReader(int id, ReaderInputDTO reader) {
        Reader r = readerRepository.findById(id)
                .orElse(null);
        if (r != null){
            r.setName(reader.getName());
            r.setSurname(reader.getSurname());
            readerRepository.save(r);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeReader(int id) {
        Reader r = readerRepository.findById(id).orElse(null);
        if (r != null) {
            readerRepository.delete(r);
            return true;
        } else {
            return false;
        }

    }
}
