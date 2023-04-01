package com.example.SpringRestAPI.reader;

import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface IReaderService {

    Collection<Reader> getReaders(Pageable pageable);
    Reader getReader(int id);
    void addReader(ReaderInputDTO reader);
    boolean updateReader(int id, ReaderInputDTO reader);
    boolean removeReader(int id);
}
