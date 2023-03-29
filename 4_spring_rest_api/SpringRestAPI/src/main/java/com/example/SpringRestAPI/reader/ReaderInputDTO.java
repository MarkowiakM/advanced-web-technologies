package com.example.SpringRestAPI.reader;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReaderInputDTO {
    private String name;
    private String surname;

    public Reader toReader(){
        return new Reader(name, surname);
    }

}
