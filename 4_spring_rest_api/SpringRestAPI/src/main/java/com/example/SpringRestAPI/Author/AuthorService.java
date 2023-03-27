package com.example.SpringRestAPI.Author;

import com.example.SpringRestAPI.Books.Book;
import com.example.SpringRestAPI.Books.DTOBookOutput;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuthorService implements IAuthorService{

    private static final List<Author> authorsRepo = new ArrayList<>();

    static {
        authorsRepo.add(new Author(1,"Henryk", "Sienkiewicz"));
        authorsRepo.add(new Author(2,"Stanisław", "Reymont"));
        authorsRepo.add(new Author(3,"Adam", "Mickiewicz"));
    }
    @Override
    public Collection<Author> getAuthors() {
        return authorsRepo;
    }

    @Override
    public DTOAuthorWithBooksOutput getAuthor(int id) {
        Author foundAuthor = authorsRepo.stream()
                .filter(a -> a.getId() == id)
                .findAny()
                .orElse(null);
        if (foundAuthor == null) return null;
        List<DTOBookOutput> booksDTO = new ArrayList<>();
        for (Book b:foundAuthor.getBooks())
            booksDTO.add(DTOBookOutput.fromBook(b));
        return DTOAuthorWithBooksOutput.fromAuthor(foundAuthor, booksDTO);
    }

    @Override
    public Author getAuthorObj(int id) {
        return authorsRepo.stream()
                .filter(a -> a.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void addAuthor(Author author) {
        authorsRepo.add(author);
    }

    @Override
    public boolean removeAuthor(int id) {
        for (Author a : authorsRepo) {
            if (a.getId() == id) {
                authorsRepo.remove(a);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateAuthor(Author author) {
        for (Author a : authorsRepo) {
            if (a.getId() == author.getId()) {
                authorsRepo.remove(a);
                authorsRepo.add(author);
                return true;
            }
        }
        return false;
    }
}
