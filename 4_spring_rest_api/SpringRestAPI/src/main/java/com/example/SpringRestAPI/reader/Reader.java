package com.example.SpringRestAPI.reader;

import com.example.SpringRestAPI.rental.Rental;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    private String surname;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Rental> rentals;

    public Reader(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
