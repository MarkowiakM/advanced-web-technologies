package com.example.SpringRestAPI.author;

import org.springframework.data.repository.CrudRepository;

public interface IAuthorRepository extends CrudRepository<Author, Integer> {
}
