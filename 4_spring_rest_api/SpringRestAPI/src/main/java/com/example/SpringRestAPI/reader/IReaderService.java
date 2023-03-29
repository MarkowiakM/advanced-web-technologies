package com.example.SpringRestAPI.reader;

import java.util.Collection;

public interface IReaderService {

    Collection<Reader> getReaders();
    Reader getReader(int id);
    void addReader(ReaderInputDTO reader);
    boolean updateReader(int id, ReaderInputDTO reader);
    boolean removeReader(int id);
}
