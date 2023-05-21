package com.example.librarybe.services;

import com.example.librarybe.dtos.AuthorDto;
import com.example.librarybe.mappers.AuthorMapper;
import com.example.librarybe.models.Author;
import com.example.librarybe.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;

    }

    public List<AuthorDto> getAllAuthors() {
        List<Author> genres = authorRepository.findAll();
        return genres.stream()
                .map(authorMapper::toAuthorDto)
                .collect(Collectors.toList());
    }
}
