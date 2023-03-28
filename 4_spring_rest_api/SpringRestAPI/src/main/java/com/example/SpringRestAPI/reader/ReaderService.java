package com.example.SpringRestAPI.reader;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ReaderService implements IReaderService{

    private static final List<Reader> readersRepo = new ArrayList<>();

    static{
        readersRepo.add(new Reader(1, "Paulina", "Drzazga"));
        readersRepo.add(new Reader(2, "Maria", "Markowiak"));
    }
    @Override
    public Collection<Reader> getReaders() {
        return readersRepo;
    }

    @Override
    public Reader getReader(int id) {
        return readersRepo.stream()
                .filter(r -> r.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void addReader(Reader reader) {
        readersRepo.add(reader);
    }

    @Override
    public boolean updateReader(Reader reader) {
        for (Reader r : readersRepo) {
            if (r.getId() == reader.getId()) {
                readersRepo.remove(r);
                readersRepo.add(reader);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeReader(int id) {
        for (Reader r : readersRepo) {
            if (r.getId() == id) {
                readersRepo.remove(r);
                return true;
            }
        }
        return false;
    }
}
