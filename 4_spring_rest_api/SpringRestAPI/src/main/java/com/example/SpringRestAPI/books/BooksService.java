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
    public int addBook(BookInputDTO book) {
        ArrayList<Author> authors = new ArrayList<>();
        for (int idA : book.getAuthorsIDs()){
            Author a = authorService.getAuthorObj(idA);
            if (a != null)
                authors.add(a);
            else
                return -1;
        }
        bookRepository.save(new Book(book.getTitle(), authors, book.getPages()));
        return 0;
    }

    @Override
    public int removeBook(int id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null){
            try {
                bookRepository.delete(book);
                return 0;
            } catch (Exception e){
                return 2;
            }
        } else {
            return 1;
        }
    }

    @Override
    public int updateBook(int id, BookInputDTO bookDTO) {
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
                    return 1;
            }
            bookRepository.save(b);
            return 0;
        }
        return 2;
    }


}
