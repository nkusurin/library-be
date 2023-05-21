package com.example.librarybe.mappers;

import com.example.librarybe.dtos.BookDto;
import com.example.librarybe.models.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toBookDto(Book book);
    Book toBook(BookDto bookDto);
}
