package com.example.SpringRestAPI.books;

import org.springframework.data.repository.CrudRepository;

public interface IBookRepository extends CrudRepository<Book, Integer> {
}
