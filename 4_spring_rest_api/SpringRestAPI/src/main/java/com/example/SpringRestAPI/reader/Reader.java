package com.example.SpringRestAPI.reader;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Reader {
    private int id;
    private String name;
    private String surname;

    public Reader(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
