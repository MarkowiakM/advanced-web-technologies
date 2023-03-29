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
//    private static final List<Reader> readersRepo = new ArrayList<>();

//    static{
//        readersRepo.add(new Reader(1, "Paulina", "Drzazga"));
//        readersRepo.add(new Reader(2, "Maria", "Markowiak"));
//    }
    @Override
    public Collection<Reader> getReaders() {
        //return readersRepo;
        return readerRepository.findAll();
    }

    @Override
    public Reader getReader(int id) {
//        return readersRepo.stream()
//                .filter(r -> r.getId() == id)
//                .findAny()
//                .orElse(null);
        return readerRepository.findById(id)
                .orElse(null);
    }

    @Override
    public void addReader(ReaderInputDTO readerDTO) {
        Reader reader = readerDTO.toReader();
//        readersRepo.add(reader);
        readerRepository.save(reader);
    }

    @Override
    public boolean updateReader(int id, ReaderInputDTO reader) {
        Reader r = readerRepository.findById(id)
                .orElse(null);
        if (r != null){
            r.setName(reader.getName());
            r.setSurname(reader.getSurname());
            return true;
        } else {
            return false;
        }
//        for (Reader r : readersRepo) {
//            if (r.getId() == id) {
//                r.setName(reader.getName());
//                r.setSurname(reader.getSurname());
//                return true;
//            }
//        }
//        return false;
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
        
//        for (Reader r : readersRepo) {
//            if (r.getId() == id) {
//                readersRepo.remove(r);
//                return true;
//            }
//        }
//        return false;
    }
}
