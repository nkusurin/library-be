package com.example.librarybe.controllers;

import com.example.librarybe.dtos.GenreDto;
import com.example.librarybe.dtos.PublisherDto;
import com.example.librarybe.mappers.GenreMapper;
import com.example.librarybe.models.Genre;
import com.example.librarybe.models.Publisher;
import com.example.librarybe.services.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private GenreService genreService;
    private GenreMapper genreMapper;

    public GenreController(GenreService genreService, GenreMapper genreMapper) {
        this.genreService = genreService;
        this.genreMapper = genreMapper;
    }

    @GetMapping
    public List<GenreDto> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> getGenreById(@PathVariable("id") Long id) {
        Genre genre = new Genre();
        try {
            genre = genreService.getGenreById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (genre != null) {
            return ResponseEntity.status(HttpStatus.OK).body(genreMapper.toGenreDto(genre));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
