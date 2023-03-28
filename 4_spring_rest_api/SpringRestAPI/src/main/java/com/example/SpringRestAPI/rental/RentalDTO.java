package com.example.SpringRestAPI.rental;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RentalDTO {
    private int bookID;
    private int readerID;
    private String SQLDate;
}
