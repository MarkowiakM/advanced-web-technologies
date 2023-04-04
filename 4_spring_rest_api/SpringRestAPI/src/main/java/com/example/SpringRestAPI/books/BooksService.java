package com.example.SpringRestAPI.books;

import com.example.SpringRestAPI.author.Author;
import com.example.SpringRestAPI.author.AuthorDTO;
import com.example.SpringRestAPI.author.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.example.SpringRestAPI.books.BookStatus.*;

@Service
public class BooksService implements IBooksService {
    @Autowired
    IBookRepository bookRepository;
    @Autowired
    IAuthorService authorService;

    @Override
    public Collection<BookWithAuthorOutputDTO> getBooks(Pageable pageable) {
        List<BookWithAuthorOutputDTO> books = new ArrayList<>();
        for (Book b: bookRepository.findAll(pageable)){
            List<AuthorDTO> authors = authorService.getAuthorsDTOOfBook(b.getAuthors());
            books.add(BookWithAuthorOutputDTO.fromBook(b, authors));
        }
        return books;
    }

    @Override
    public BookWithAuthorOutputDTO getBook(int id){
        Book foundBook = bookRepository.findById(id).orElse(null);
        if (foundBook == null) return null;
        List<AuthorDTO> authorsDTO = new ArrayList<>();
        for (Author a:foundBook.getAuthors())
            authorsDTO.add(AuthorDTO.fromAuthor(a));
        return BookWithAuthorOutputDTO.fromBook(foundBook, authorsDTO);
    }

    @Override
    public Book getBookObj(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public BookStatus addBook(BookInputDTO book) {
        ArrayList<Author> authors = new ArrayList<>();
        for (int idA : book.getAuthorsIDs()){
            Author a = authorService.getAuthorObj(idA);
            if (a != null)
                authors.add(a);
            else
                return AUTHOR_DOES_NOT_EXIST;
        }
        bookRepository.save(new Book(book.getTitle(), authors, book.getPages()));
        return OK;
    }

    @Override
    public BookStatus removeBook(int id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null){
            try {
                bookRepository.delete(book);
                return OK;
            } catch (Exception e){
                return BOOK_IS_RENTED;
            }
        } else {
            return BOOK_DOES_NOT_EXIST;
        }
    }

    @Override
    public BookStatus updateBook(int id, BookInputDTO bookDTO) {
        Book b = bookRepository.findById(id).orElse(null);
        if (b != null) {
            b.setPages(bookDTO.getPages());
            b.setTitle(bookDTO.getTitle());
            b.getAuthors().removeAll(b.getAuthors());
            for (int idA : bookDTO.getAuthorsIDs()){
                Author a = authorService.getAuthorObj(idA);
                if (a != null)
                    b.addAuthor(a);
                else
                    return AUTHOR_DOES_NOT_EXIST;
            }
            bookRepository.save(b);
            return OK;
        }
        return BOOK_DOES_NOT_EXIST;
    }


}
