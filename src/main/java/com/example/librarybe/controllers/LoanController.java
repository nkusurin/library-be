package com.example.librarybe.controllers;

import com.example.librarybe.dtos.BookDto;
import com.example.librarybe.dtos.LoanDto;
import com.example.librarybe.models.Book;
import com.example.librarybe.services.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public ResponseEntity<List<LoanDto>> getAllLoans() {
        return ResponseEntity.status(HttpStatus.OK).body(loanService.getAllLoans());
    }

    @PostMapping("/add-loan")
    public ResponseEntity<LoanDto> createLoan(@RequestBody LoanDto loanDto) {
        LoanDto createdLoan = loanService.createLoan(loanDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLoan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<LoanDto>> getLoanForBook(@PathVariable("id") Long id) {
        List<LoanDto> loans = loanService.getLoansForBook(id);

        if (loans.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(loans);
    }

    @GetMapping("/get-loan/{id}")
    public ResponseEntity<LoanDto> getLoanById(@PathVariable("id") Long id) {
        LoanDto loan = loanService.getLoanById(id);

        if (loan == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(loan);
    }

    @PutMapping("/edit-loan/{id}")
    public ResponseEntity<LoanDto> updateLoanById(
            @PathVariable("id") Long id,
            @RequestBody LoanDto loanDto
    ) {
        LoanDto updatedLoan = loanService.updateLoan(id, loanDto);

        if (updatedLoan == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedLoan);
    }

}


