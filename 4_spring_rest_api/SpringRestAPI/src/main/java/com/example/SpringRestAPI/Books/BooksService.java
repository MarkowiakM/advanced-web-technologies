package com.example.SpringRestAPI.Books;

import com.example.SpringRestAPI.Author.Author;
import com.example.SpringRestAPI.Author.AuthorService;
import com.example.SpringRestAPI.Author.DTOAuthorOutput;
import com.example.SpringRestAPI.Author.IAuthorService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
@Service
@Configurable
public class BooksService implements IBooksService {
    private static List<Book> booksRepo = new ArrayList<>();
    //@Autowired
    private static IAuthorService authorService = new AuthorService();

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
    public Collection<Book> getBooks() {
        return booksRepo;
    }

    @Override
    public DTOBookWithAuthorOutput getBook(int id){
        Book foundBook = booksRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(null);
        if (foundBook == null) return null;
        List<DTOAuthorOutput> authorsDTO = new ArrayList<>();
        for (Author a:foundBook.getAuthors())
            authorsDTO.add(DTOAuthorOutput.fromAuthor(a));
        return DTOBookWithAuthorOutput.fromBook(foundBook, authorsDTO);
    }

    @Override
    public void addBook(DTOBookInput book) {
        ArrayList<Author> authors = new ArrayList<>();
        for (int idA : book.getAuthorsIDs()){
            Author a = authorService.getAuthorObj(idA);
            if (a != null)
                authors.add(a);
        }
        booksRepo.add(new Book(book.getId(), book.getTitle(), authors, book.getPages()));
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
    public boolean updateBook(DTOBookInput book) {
        for (Book b : booksRepo) {
            if (b.getId() == book.getId()) {
                booksRepo.remove(b);
                ArrayList<Author> authors = new ArrayList<>();
                for (int idA : book.getAuthorsIDs()){
                    authors.add(authorService.getAuthorObj(idA));
                }
                booksRepo.add(new Book(book.getId(), book.getTitle(), authors, book.getPages()));
                return true;
            }
        }
        return true;
    }


}
