package com.example.librarybe.dtos;


import com.example.librarybe.models.Book;

import java.util.List;

public class GenreDto {
    private Long id;
    private String name;


    public GenreDto() {


    }

    public GenreDto(Long id, String name, List<Book> books) {
        this.id = id;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
