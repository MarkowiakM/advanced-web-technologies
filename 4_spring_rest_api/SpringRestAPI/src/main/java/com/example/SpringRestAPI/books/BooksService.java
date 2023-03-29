package com.example.SpringRestAPI.books;

import com.example.SpringRestAPI.author.Author;
import com.example.SpringRestAPI.author.AuthorService;
import com.example.SpringRestAPI.author.AuthorDTO;
import com.example.SpringRestAPI.author.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
@Service
public class BooksService implements IBooksService {
    @Autowired
    IBookRepository bookRepository;
    private static final List<Book> booksRepo = new ArrayList<>();
    private static final IAuthorService authorService = new AuthorService();

    static {
        Author author1 = authorService.getAuthorObj(1);
        Author author2 = authorService.getAuthorObj(2);
        Author author3 = authorService.getAuthorObj(3);

        booksRepo.add(new Book(1,"Potop",
                new ArrayList<>(Arrays.asList(author1)), 578));
        booksRepo.add(new Book(2,"Wesele",
                new ArrayList<>(Arrays.asList(author2)), 150));
        booksRepo.add(new Book(3,"Dziady",
                new ArrayList<>(Arrays.asList(author3)), 292));
    }
    @Override
    public Collection<BookWithAuthorOutputDTO> getBooks() {
        List<BookWithAuthorOutputDTO> books = new ArrayList<>();
        for (Book b: booksRepo){
            List<AuthorDTO> authors = authorService.getAuthorsDTOOfBook(b.getAuthors());
            books.add(BookWithAuthorOutputDTO.fromBook(b, authors));
        }
        return books;
    }

    @Override
    public BookWithAuthorOutputDTO getBook(int id){
        Book foundBook = booksRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(null);
        if (foundBook == null) return null;
        List<AuthorDTO> authorsDTO = new ArrayList<>();
        for (Author a:foundBook.getAuthors())
            authorsDTO.add(AuthorDTO.fromAuthor(a));
        return BookWithAuthorOutputDTO.fromBook(foundBook, authorsDTO);
    }

    @Override
    public Book getBookObj(int id) {
        return booksRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void addBook(BookInputDTO book) {
        ArrayList<Author> authors = new ArrayList<>();
        for (int idA : book.getAuthorsIDs()){
            Author a = authorService.getAuthorObj(idA);
            if (a != null)
                authors.add(a);
        }
        booksRepo.add(new Book(book.getTitle(), authors, book.getPages()));
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
    public boolean updateBook(int id, BookInputDTO book) {
        for (Book b : booksRepo) {
            if (b.getId() == id) {
                b.setPages(book.getPages());
                b.setTitle(book.getTitle());
                b.getAuthors().removeAll(b.getAuthors());
                for (int idA : book.getAuthorsIDs()){
                    b.addAuthor(authorService.getAuthorObj(idA));
                }
                return true;
            }
        }
        return false;
    }


}
