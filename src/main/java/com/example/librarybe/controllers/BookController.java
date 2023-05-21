package com.example.librarybe.controllers;

import com.example.librarybe.dtos.BookDto;
import com.example.librarybe.dtos.CreateBookDto;
import com.example.librarybe.dtos.PublisherDto;
import com.example.librarybe.mappers.BookMapper;
import com.example.librarybe.models.Book;
import com.example.librarybe.services.BookService;
import com.example.librarybe.services.GenreService;
import com.example.librarybe.services.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.CacheRequest;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;
    private BookMapper bookMapper;
    private GenreService genreService;

    private PublisherService publisherService;


    public BookController(BookService bookService, BookMapper bookMapper, GenreService genreService, PublisherService publisherService) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
        this.genreService = genreService;
        this.publisherService = publisherService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long id) {
        Book book = new Book();
        try {
            book = bookService.getBookById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (book != null) {
            return ResponseEntity.status(HttpStatus.OK).body(bookMapper.toBookDto(book));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable("id") Long id) {
        try {
            bookService.deleteBookById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/add-book")
    public ResponseEntity<BookDto> createBook(@RequestBody CreateBookDto createBookDto) {
        BookDto bookDto = new BookDto();
        bookDto.setId(createBookDto.getId());
        bookDto.setAvailability(createBookDto.getAvailability());
        bookDto.setIsbn(createBookDto.getIsbn());
        bookDto.setDescription(createBookDto.getDescription());
        bookDto.setTitle(createBookDto.getTitle());
        bookDto.setGenre(genreService.getGenreById(createBookDto.getGenreId()));
        bookDto.setPublisher(publisherService.getPublisherById(createBookDto.getPublisherId()));
        BookDto createdBook = bookService.createBook(bookDto);

       return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @GetMapping("/search")
    public List<BookDto> searchBooks(@RequestParam("query") String query) {
        return bookService.searchBooks(query);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBookById(
            @PathVariable("id") Long id,
            @RequestBody CreateBookDto updatedBookDto
    ) {
        try {
            BookDto bookDto = new BookDto();
            bookDto.setId(updatedBookDto.getId());
            bookDto.setAvailability(updatedBookDto.getAvailability());
            bookDto.setIsbn(updatedBookDto.getIsbn());
            bookDto.setDescription(updatedBookDto.getDescription());
            bookDto.setTitle(updatedBookDto.getTitle());
            bookDto.setGenre(genreService.getGenreById(updatedBookDto.getGenreId()));
            bookDto.setPublisher(publisherService.getPublisherById(updatedBookDto.getPublisherId()));

            BookDto updatedBook = bookService.updateBook(id, bookDto);

            return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
