package com.example.librarybe.controllers;

import com.example.librarybe.dtos.BookDto;
import com.example.librarybe.dtos.LoanDto;
import com.example.librarybe.dtos.PublisherDto;
import com.example.librarybe.mappers.PublisherMapper;
import com.example.librarybe.models.Book;
import com.example.librarybe.models.Publisher;
import com.example.librarybe.services.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
    private PublisherService publisherService;
    private PublisherMapper publisherMapper;

    public PublisherController(PublisherService publisherService, PublisherMapper publisherMapper) {
        this.publisherService = publisherService;
        this.publisherMapper = publisherMapper;
    }

    @GetMapping
    public List<PublisherDto> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @PostMapping("/add-publisher")
    public ResponseEntity<PublisherDto> createPublisher(@RequestBody PublisherDto publisherDto) {
        PublisherDto createdPublisher = publisherService.createPublisher(publisherDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPublisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherDto> updatePublisherById(
            @PathVariable("id") Long id,
            @RequestBody PublisherDto updatedPublisherDto
    ) {
        try {
            PublisherDto updatedPublisher = publisherService.updatePublisher(id, updatedPublisherDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedPublisher);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/search")
    public List<PublisherDto> searchPublishers(@RequestParam("query") String query) {
        return publisherService.searchPublishers(query);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PublisherDto> getPublisherById(@PathVariable("id") Long id) {
        Publisher publisher = new Publisher();
        try {
            publisher = publisherService.getPublisherById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (publisher != null) {
            return ResponseEntity.status(HttpStatus.OK).body(publisherMapper.toPublisherDto(publisher));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PublisherDto> deletePublisherById(@PathVariable("id") Long id) {
        try {
            publisherService.deletePublisherById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
