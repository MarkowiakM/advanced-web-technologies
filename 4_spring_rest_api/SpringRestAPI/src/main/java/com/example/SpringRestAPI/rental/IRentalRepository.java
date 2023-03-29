package com.example.SpringRestAPI.rental;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Transactional
@Repository
public interface IRentalRepository extends JpaRepository<Rental, Integer> {

    Optional<Rental> findByBookId(Integer bookID);
    void deleteByBook_Id(int bookID);
}
