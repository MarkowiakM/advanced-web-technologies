package com.example.SpringRestAPI.author;

import com.example.SpringRestAPI.books.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuthorService implements IAuthorService{
    @Autowired
    IAuthorRepository authorRepository;

    @Override
    public Collection<AuthorDTO> getAuthors(Pageable pageable) {
        Collection<AuthorDTO> authors = new ArrayList<>();
        for (Author a: authorRepository.findAll(pageable)){
            authors.add(AuthorDTO.fromAuthor(a));
        }
        return authors;
    }

    @Override
    public AuthorWithBooksOutputDTO getAuthor(int id) {
        Author foundAuthor = authorRepository.findById(id).orElse(null);
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
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public void addAuthor(AuthorInputDTO authorDTO) {
        Author author = authorDTO.toAuthor();
        authorRepository.save(author);
    }

    @Override
    public boolean removeAuthor(int id) {
        Author a = authorRepository.findById(id).orElse(null);
        if (a != null) {
            authorRepository.delete(a);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateAuthor(int id, AuthorInputDTO authorDTO) {
        Author a = authorRepository.findById(id).orElse(null);
        if (a != null) {
            a.setName(authorDTO.getName());
            a.setSurname(authorDTO.getSurname());
            authorRepository.save(a);
            return true;
        } else {
            return false;
        }
    }
}
