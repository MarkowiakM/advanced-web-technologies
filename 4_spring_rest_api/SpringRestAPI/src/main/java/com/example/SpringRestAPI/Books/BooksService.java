package com.example.SpringRestAPI.Books;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
public class BooksService implements IBooksService {
    private static List<Book> booksRepo = new ArrayList<>();

    static {
        booksRepo.add(new Book(1,"Potop", "Henryk Sienkiewicz", 936));
        booksRepo.add(new Book(2,"Wesele", "Stanisław Reymont", 150));
        booksRepo.add(new Book(3,"Dziady", "Adam Mickiewicz", 292));
    }
    @Override
    public Collection<Book> getBooks() {
        return booksRepo;
    }

    @Override
    public Book getBook(int id){
        return booksRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void addBook(Book book) {
        booksRepo.add(book);
    }

    @Override
    public boolean removeBook(int id) {
        for (Book b : booksRepo) {
            if (b.getId() == id) {
                booksRepo.remove(b);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateBook(Book book) {
        for (Book b : booksRepo) {
            if (b.getId() == book.getId()) {
                booksRepo.remove(b);
                booksRepo.add(book);
                return true;
            }
        }
        return true;
    }


}
