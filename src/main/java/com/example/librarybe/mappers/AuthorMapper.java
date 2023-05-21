package com.example.librarybe.mappers;

import com.example.librarybe.dtos.AuthorDto;
import com.example.librarybe.models.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto toAuthorDto(Author author);
    Author toAuthor(AuthorDto authorDto);
}
