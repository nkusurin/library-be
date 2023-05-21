package com.example.librarybe.dtos;

import java.util.Date;

public class LoanDto {

    private Long id;
    private String type;
    private Date borrowed;
    private Date returned;

    private Long bookId;


    public LoanDto(){

    }

    public LoanDto(Long id, String type, Date borrowed, Date returned, Long book) {
        this.id = id;
        this.type = type;
        this.borrowed = borrowed;
        this.returned = returned;
        this.bookId = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(Date borrowed) {
        this.borrowed = borrowed;
    }

    public Date getReturned() {
        return returned;
    }

    public void setReturned(Date returned) {
        this.returned = returned;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
