package com.example.librarybe.services;

import com.example.librarybe.dtos.LoanDto;
import com.example.librarybe.mappers.LoanMapper;
import com.example.librarybe.models.Loan;
import com.example.librarybe.repositories.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;

    public LoanService(LoanRepository loanRepository, LoanMapper loanMapper) {
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;

    }

    public List<LoanDto> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        return loans.stream()
                .map(loanMapper::toLoanDto)
                .collect(Collectors.toList());
    }

    public LoanDto createLoan(LoanDto loanDto) {
        Loan loan = loanMapper.toLoan(loanDto);
        Loan savedLoan = loanRepository.save(loan);
        return loanMapper.toLoanDto(savedLoan);
    }

    public List<LoanDto> getLoansForBook(Long id) {
        List<Loan> loans = loanRepository.findByBookId(id);

        List<LoanDto> loanDtos = new ArrayList<>();
        for (Loan loan : loans) {
            LoanDto loanDto = new LoanDto();
            loanDto.setId(loan.getId());
            loanDto.setType(loan.getType());
            loanDto.setBorrowed(loan.getBorrowed());
            loanDto.setReturned(loan.getReturned());
            loanDto.setBookId(loan.getBook().getId());

            loanDtos.add(loanDto);
        }
        return loanDtos;
    }

    public LoanDto getLoanById(Long id) {
        Optional<Loan> loanOptional = loanRepository.findById(id);

        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            return loanMapper.toLoanDto(loan);
        }

        return null;
    }

    public LoanDto updateLoan(Long id, LoanDto loanDto) {
        Optional<Loan> loanOptional = loanRepository.findById(id);

        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            // Update the loan properties based on the provided loanDto
            loan.setType(loanDto.getType());
            loan.setBorrowed(loanDto.getBorrowed());
            loan.setReturned(loanDto.getReturned());
            // Save the updated loan
            Loan updatedLoan = loanRepository.save(loan);
            return loanMapper.toLoanDto(updatedLoan);
        }

        return null;
    }

}
