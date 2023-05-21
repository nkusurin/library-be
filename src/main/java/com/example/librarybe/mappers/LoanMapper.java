package com.example.librarybe.mappers;

import com.example.librarybe.dtos.LoanDto;
import com.example.librarybe.models.Book;
import com.example.librarybe.models.Loan;
import com.example.librarybe.services.BookService;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    private final BookService bookService;

    public LoanMapper(BookService bookService) {
        this.bookService = bookService;
    }

    public LoanDto toLoanDto(Loan loan) {
        LoanDto loanDto = new LoanDto();
        loanDto.setId(loan.getId());
        loanDto.setType(loan.getType());
        loanDto.setBorrowed(loan.getBorrowed());
        loanDto.setReturned(loan.getReturned());
        loanDto.setBookId(loan.getBook().getId());
        return loanDto;
    }

    public Loan toLoan(LoanDto loanDto) {
        Loan loan = new Loan();
        loan.setId(loanDto.getId());
        loan.setType(loanDto.getType());
        loan.setBorrowed(loanDto.getBorrowed());
        loan.setReturned(loanDto.getReturned());
        // Set the book using the bookId
        Book book = bookService.getBookById(loanDto.getBookId());
       //  Book book = new Book();
        // book.setId(loanDto.getBookId());
        loan.setBook(book);
        return loan;
    }
}
