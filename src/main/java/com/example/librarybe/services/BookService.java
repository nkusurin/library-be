package com.example.librarybe.services;

import com.example.librarybe.dtos.BookDto;
import com.example.librarybe.dtos.PublisherDto;
import com.example.librarybe.mappers.BookMapper;
import com.example.librarybe.models.Book;
import com.example.librarybe.models.Publisher;
import com.example.librarybe.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;


    public BookService(BookRepository bookRepository, BookMapper bookMapper) {

        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::toBookDto)
                .collect(Collectors.toList());
    }

    public Book getBookById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public BookDto createBook(BookDto bookDto) {
        Book book = bookMapper.toBook(bookDto);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toBookDto(savedBook);
    }

    public List<BookDto> searchBooks(String query) {
        query = query.toLowerCase();
        List<Book> matchingBooks = new ArrayList<>();

        List<Book> allBooks = bookRepository.findAll();
        for(Book b: allBooks){
            if(b.getTitle().toLowerCase().contains(query) || b.getIsbn().toLowerCase().contains(query) || b.getAvailability().toLowerCase().contains(query)
                    || b.getGenre().getName().toLowerCase().contains(query) ||
                    b.getPublisher().getName().toLowerCase().contains(query) || b.getDescription().toLowerCase().contains(query)){
                matchingBooks.add(b);
            }
        }

        return matchingBooks.stream()
                .map(bookMapper::toBookDto)
                .collect(Collectors.toList());
    }
    public BookDto updateBook(Long id, BookDto bookDto) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();

            // Update the fields of the existing publisher with the updated values
            existingBook.setGenre(bookDto.getGenre());
            existingBook.setDescription(bookDto.getDescription());
            existingBook.setAvailability(bookDto.getAvailability());
            existingBook.setTitle(bookDto.getTitle());
            existingBook.setIsbn(bookDto.getIsbn());
            existingBook.setLoans(bookDto.getLoans());
            existingBook.setId(bookDto.getId());
            existingBook.setPublisher(bookDto.getPublisher());

            Book updatedBook = bookRepository.save(existingBook);
            return bookMapper.toBookDto(updatedBook);
        } else {
            throw new IllegalArgumentException("Book not found with ID: " + id);
        }
    }

}
