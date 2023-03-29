package com.example.SpringRestAPI.author;

import com.example.SpringRestAPI.books.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuthorService implements IAuthorService{
    @Autowired
    IAuthorRepository authorRepository;
    private static final List<Author> authorsRepo = new ArrayList<>();

    static {
        authorsRepo.add(new Author(1,"Henryk", "Sienkiewicz"));
        authorsRepo.add(new Author(2,"Stanisław", "Reymont"));
        authorsRepo.add(new Author(3,"Adam", "Mickiewicz"));
    }
    @Override
    public Collection<AuthorDTO> getAuthors() {
        Collection<AuthorDTO> authors = new ArrayList<>();
        for (Author a: authorsRepo){
            authors.add(AuthorDTO.fromAuthor(a));
        }
        return authors;
    }

    @Override
    public AuthorWithBooksOutputDTO getAuthor(int id) {
        Author foundAuthor = authorsRepo.stream()
                .filter(a -> a.getId() == id)
                .findAny()
                .orElse(null);
        if (foundAuthor == null) return null;
        List<BookOutputDTO> booksDTO = new ArrayList<>();
        for (Book b:foundAuthor.getBooks())
            booksDTO.add(BookOutputDTO.fromBook(b));
        return AuthorWithBooksOutputDTO.fromAuthor(foundAuthor, booksDTO);
    }

    @Override
    public List<AuthorDTO> getAuthorsDTOOfBook(List<Author> authors){
        List<AuthorDTO> dtoAuthorList= new ArrayList<>();
        for (Author a : authors){
            dtoAuthorList.add(AuthorDTO.fromAuthor(a));
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
    public void addAuthor(AuthorInputDTO authorDTO) {
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
    public boolean updateAuthor(int id, AuthorInputDTO authorDTO) {
        for (Author a : authorsRepo) {
            if (a.getId() == id) {
                a.setName(authorDTO.getName());
                a.setSurname(authorDTO.getSurname());
                return true;
            }
        }
        return false;
    }
}
