package com.example.SpringRestAPI.books;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BookInputDTO {
    private int id;
    private String title;
    private List<Integer> authorsIDs;
    private int pages;
}
