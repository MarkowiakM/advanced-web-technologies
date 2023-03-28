package com.example.SpringRestAPI.rental;

import com.example.SpringRestAPI.reader.Reader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RentedReaderDTO {
    private Reader reader;
    private List<RentedBookDTO> rentedBooks;

}
