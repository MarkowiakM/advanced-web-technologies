package com.example.SpringRestAPI.books;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface IBookRepository extends JpaRepository<Book, Integer> {
    Page<Book> findBooksByRentalIsNull (Pageable pageable);
}
