package com.example.SpringRestAPI.rental;

import org.springframework.data.repository.CrudRepository;

public interface IRentalRepository extends CrudRepository<Rental, Integer> {
}
