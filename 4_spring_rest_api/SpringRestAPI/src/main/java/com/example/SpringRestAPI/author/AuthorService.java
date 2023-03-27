package com.example.SpringRestAPI.author;

import com.example.SpringRestAPI.books.*;
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
    public Collection<DTOAuthor> getAuthors() {
        Collection<DTOAuthor> authors = new ArrayList<>();
        for (Author a: authorsRepo){
            authors.add(DTOAuthor.fromAuthor(a));
        }
        return authors;
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
    public List<DTOAuthor> getAuthorsDTOOfBook(List<Author> authors){
        List<DTOAuthor> dtoAuthorList= new ArrayList<>();
        for (Author a : authors){
            dtoAuthorList.add(DTOAuthor.fromAuthor(a));
        }
        return dtoAuthorList;
    }

    @Override
    public Author getAuthorObj(int id) {
        return authorsRepo.stream()
                .filter(a -> a.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void addAuthor(DTOAuthor authorDTO) {
        Author author = authorDTO.toAuthor();
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
    public boolean updateAuthor(DTOAuthor authorDTO) {
        Author author = authorDTO.toAuthor();
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
