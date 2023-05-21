package com.example.librarybe.services;

import com.example.librarybe.dtos.GenreDto;
import com.example.librarybe.mappers.GenreMapper;
import com.example.librarybe.models.Genre;
import com.example.librarybe.models.Publisher;
import com.example.librarybe.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreService(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;

    }

    public List<GenreDto> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return genres.stream()
                .map(genreMapper::toGenreDto)
                .collect(Collectors.toList());
    }

    public Genre getGenreById(Long id) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        return optionalGenre.orElse(null);
    }
}
