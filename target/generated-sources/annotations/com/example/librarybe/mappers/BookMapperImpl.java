package com.example.librarybe.mappers;

import com.example.librarybe.dtos.BookDto;
import com.example.librarybe.models.Book;
import com.example.librarybe.models.Loan;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-21T20:14:21+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.4.1 (Amazon.com Inc.)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDto toBookDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setId( book.getId() );
        bookDto.setIsbn( book.getIsbn() );
        bookDto.setTitle( book.getTitle() );
        bookDto.setDescription( book.getDescription() );
        bookDto.setAvailability( book.getAvailability() );
        bookDto.setGenre( book.getGenre() );
        bookDto.setPublisher( book.getPublisher() );
        List<Loan> list = book.getLoans();
        if ( list != null ) {
            bookDto.setLoans( new ArrayList<Loan>( list ) );
        }

        return bookDto;
    }

    @Override
    public Book toBook(BookDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( bookDto.getId() );
        book.setIsbn( bookDto.getIsbn() );
        book.setTitle( bookDto.getTitle() );
        book.setDescription( bookDto.getDescription() );
        book.setAvailability( bookDto.getAvailability() );
        book.setGenre( bookDto.getGenre() );
        book.setPublisher( bookDto.getPublisher() );
        List<Loan> list = bookDto.getLoans();
        if ( list != null ) {
            book.setLoans( new ArrayList<Loan>( list ) );
        }

        return book;
    }
}
