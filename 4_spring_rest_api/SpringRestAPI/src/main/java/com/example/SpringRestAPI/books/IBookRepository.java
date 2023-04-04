package com.example.SpringRestAPI.books;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface IBookRepository extends JpaRepository<Book, Integer> {
//    @Query("select B.id, B.title, B.pages from Book as B left join Rental as R on B.id = R.book.id where R.book = null")
//    Page<Book> findNotRented(Pageable pageable);
    Page<Book> findBooksByRentalsEmpty (Pageable pageable);
}
