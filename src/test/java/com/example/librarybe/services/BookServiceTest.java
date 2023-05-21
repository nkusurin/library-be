package com.example.librarybe.services;

import com.example.librarybe.mappers.BookMapper;
import com.example.librarybe.models.Book;
import com.example.librarybe.repositories.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    @Mock
    BookRepository bookRepository;
    @Mock
    BookMapper bookMapper;

    @InjectMocks
    BookService bookService;

    @Test
    public void getBookById() {
        // Given
        Book bookMock = new Book();
        bookMock.setId(1L);
        bookMock.setIsbn("123");

        // When
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(bookMock));

        // Then
        Book book = bookService.getBookById(1L);
        Assert.assertEquals("123", book.getIsbn());
    }
}
