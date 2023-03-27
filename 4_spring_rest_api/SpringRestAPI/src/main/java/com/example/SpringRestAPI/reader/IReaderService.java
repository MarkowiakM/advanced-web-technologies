package com.example.SpringRestAPI.reader;

import java.util.Collection;

public interface IReaderService {

    Collection<Reader> getReaders();
    Reader getReader(int id);
    void addReader(Reader reader);
    boolean updateReader(Reader reader);
    boolean removeReader(int id);
}
