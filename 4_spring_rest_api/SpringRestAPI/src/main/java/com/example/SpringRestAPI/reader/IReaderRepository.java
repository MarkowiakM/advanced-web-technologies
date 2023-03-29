package com.example.SpringRestAPI.reader;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReaderRepository extends JpaRepository<Reader, Integer> {
}
