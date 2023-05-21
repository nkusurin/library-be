package com.example.librarybe.mappers;

import com.example.librarybe.dtos.GenreDto;
import com.example.librarybe.models.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface GenreMapper {
    GenreDto toGenreDto(Genre genre);
    Genre toGenre(GenreDto genreDto);
}
